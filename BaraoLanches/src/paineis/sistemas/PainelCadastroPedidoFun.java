package paineis.sistemas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import paineis.interfaces.PainelAdministracao;
import paineis.interfaces.PainelFuncionario;
import sistemas.ConexaoBancoDados;

public class PainelCadastroPedidoFun extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8114678492451606955L;
	private JTextField txtNumPedido;
    private JTextField txtQuant;
    private JTextField txtNumMesa;
    private JButton btFazerPedido, btVoltar;

    public PainelCadastroPedidoFun() {
        setLayout(null);
        setBounds(0, 0, 900, 700);
        iniciarComponentes();
        criarEventos();
        criarEventos2();
    }

    private void iniciarComponentes() {
        txtQuant = new JTextField();
        txtQuant.setHorizontalAlignment(SwingConstants.CENTER);
        txtQuant.setFont(new Font("Arial", Font.PLAIN, 15));
        txtQuant.setColumns(10);
        txtQuant.setBorder(null);
        txtQuant.setBounds(357, 365, 170, 20);
        add(txtQuant);

        txtNumMesa = new JTextField();
        txtNumMesa.setHorizontalAlignment(SwingConstants.CENTER);
        txtNumMesa.setFont(new Font("Arial", Font.PLAIN, 15));
        txtNumMesa.setColumns(10);
        txtNumMesa.setBorder(null);
        txtNumMesa.setBounds(64, 365, 170, 20);
        add(txtNumMesa);

        btFazerPedido = new JButton("");
        btFazerPedido.setIcon(new ImageIcon(PainelCadastroPedidoFun.class.getResource("/arquivos/BtFazerPedido.png")));
        btFazerPedido.setContentAreaFilled(false);
        btFazerPedido.setBorderPainted(false);
        btFazerPedido.setBounds(335, 490, 230, 70);
        add(btFazerPedido);

        btVoltar = new JButton("");
        btVoltar.setIcon(new ImageIcon(PainelCadastroPedidoFun.class.getResource("/arquivos/BtVoltar.png")));
        btVoltar.setContentAreaFilled(false);
        btVoltar.setBorderPainted(false);
        btVoltar.setBounds(10, 11, 66, 55);
        add(btVoltar);

        txtNumPedido = new JTextField();
        txtNumPedido.setHorizontalAlignment(SwingConstants.CENTER);
        txtNumPedido.setFont(new Font("Arial", Font.PLAIN, 15));
        txtNumPedido.setColumns(10);
        txtNumPedido.setBorder(null);
        txtNumPedido.setBounds(594, 364, 170, 20);
        add(txtNumPedido);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setToolTipText("");
        lblNewLabel.setIcon(new ImageIcon(PainelCadastroPedidoFun.class.getResource("/arquivos/TelaCadastroPedidos.png")));
        lblNewLabel.setBounds(0, 0, 881, 661);
        add(lblNewLabel);
    }

    private void criarEventos() {
        btFazerPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (txtNumMesa.getText().isEmpty() && txtNumPedido.getText().isEmpty() && txtQuant.getText().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "Preencha todos os campos!!", "Alet!!", JOptionPane.INFORMATION_MESSAGE);
            	} else {
					try {
	            		int numPedido = Integer.parseInt(txtNumPedido.getText());
	                    int quantidade = Integer.parseInt(txtQuant.getText());
	                    int numMesa = Integer.parseInt(txtNumMesa.getText());
	            		
	            		Connection conexao = ConexaoBancoDados.conectar();
	    				// comando do banco de dados;
	    				String SelectQuery = "SELECT NomeDoPedido, Valor FROM cardapio WHERE NumeroCardapio = ?";
	    				try (PreparedStatement selectPs = conexao.prepareStatement(SelectQuery)) {
	    					selectPs.setInt(1, numPedido);
	    					ResultSet rs = selectPs.executeQuery();
	    					// Se houver resultados, obter os valores
	    		            if (rs.next()) {
	    		                String nomePedido = rs.getString("NomeDoPedido");
	    		                double valorPedido = rs.getDouble("Valor");

	    		                //vai calcular o valor total do pedido
	    		                double valorTotal = valorPedido * quantidade;
	    		                 
	    		                //depois Inserir os dados na tabela 'mesa'
	    		                String insertQuery = "INSERT INTO mesa (NumeroCardapio, NomeDoPedido, Quantidade, NumMesa, ValorTotal) VALUES (?, ?, ?, ?, ?)";
	    		                PreparedStatement insertPs= conexao.prepareStatement(insertQuery);
	    		                insertPs.setInt(1, numPedido);
	    		                insertPs.setString(2, nomePedido);
	    		                insertPs.setInt(3, quantidade);
	    		                insertPs.setInt(4, numMesa);
	    		                insertPs.setDouble(5, valorTotal);
	    		                
	    		                int linhasAfetadas = insertPs.executeUpdate();
	        					if (linhasAfetadas > 0) {
	        						JOptionPane.showMessageDialog(null, "Comando executado com sucesso!!!", "Sucesso",
	        								JOptionPane.INFORMATION_MESSAGE);
	        						txtNumPedido.setText("");
	        						txtQuant.setText("");
	        						txtNumMesa.setText("");
	        					} else {
	        						JOptionPane.showMessageDialog(null, "Erro ao exucutar comando!!!.", "Erro",
	        								JOptionPane.ERROR_MESSAGE);
	        					}
	    		            } else {
	    		                JOptionPane.showMessageDialog(null, "Número de pedido inválido!");
	    		            }
	    				} catch (SQLException ex) {
	    					ex.printStackTrace();
	    					JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	    				} finally {
	    					ConexaoBancoDados.fechar(conexao);
	    				}
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Informe um numero valido", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
                	
            }
        });

        btVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // remove os conteudo da tela
				removeAll();
				revalidate();
				repaint();
				PainelFuncionario painelFun = new PainelFuncionario();
				// Adiciona o novo painel a tela
				add(painelFun);
				revalidate();
				repaint();

            }
        });
    }
    
    private void criarEventos2() {
    	// ação de animação nos botoes
        btFazerPedido.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
                btFazerPedido.setIcon(new ImageIcon(PainelCadastroPedidoFun.class.getResource("/arquivos/HoverBtFazerPedido.png")));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
                btFazerPedido.setIcon(new ImageIcon(PainelCadastroPedidoFun.class.getResource("/arquivos/BtFazerPedido.png")));
        	}
        });
        
        btVoltar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
                btVoltar.setIcon(new ImageIcon(PainelCadastroPedidoFun.class.getResource("/arquivos/HoverBtVoltar.png")));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
                btVoltar.setIcon(new ImageIcon(PainelCadastroPedidoFun.class.getResource("/arquivos/BtVoltar.png")));
        	}
        });
    }
}
