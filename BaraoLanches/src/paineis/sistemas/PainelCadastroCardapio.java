package paineis.sistemas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import paineis.interfaces.PainelAdministracao;
import sistemas.ConexaoBancoDados;


public class PainelCadastroCardapio extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNumCardapio;
    private JTextField txtNomePedido;
    private JTextField txtValor;
    private JButton btAdicionarCardapio, btVoltar;

    public PainelCadastroCardapio() {
        setLayout(null);
        setBounds(0, 0, 900, 700);

        initComponents();
        createEvents();
        createEvents2();
        
    }

    

	private void initComponents() {
        btAdicionarCardapio = new JButton("");
        btAdicionarCardapio.setIcon(new ImageIcon(PainelCadastroCardapio.class.getResource("/arquivos/BtAdicionarCardapio.png")));
        btAdicionarCardapio.setContentAreaFilled(false);
        btAdicionarCardapio.setBorderPainted(false);
        btAdicionarCardapio.setBounds(349, 470, 230, 70);
        add(btAdicionarCardapio);

        btVoltar = new JButton("");
        btVoltar.setIcon(new ImageIcon(PainelCadastroCardapio.class.getResource("/arquivos/BtVoltar.png")));
        btVoltar.setContentAreaFilled(false);
        btVoltar.setBorderPainted(false);
        btVoltar.setBounds(10, 11, 66, 55);
        add(btVoltar);

        txtNomePedido = new JTextField();
        txtNomePedido.setHorizontalAlignment(SwingConstants.CENTER);
        txtNomePedido.setFont(new Font("Arial", Font.PLAIN, 15));
        txtNomePedido.setColumns(10);
        txtNomePedido.setBorder(null);
        txtNomePedido.setBounds(366, 342, 170, 20);
        add(txtNomePedido);

        txtValor = new JTextField();
        txtValor.setHorizontalAlignment(SwingConstants.CENTER);
        txtValor.setFont(new Font("Arial", Font.PLAIN, 15));
        txtValor.setColumns(10);
        txtValor.setBorder(null);
        txtValor.setBounds(658, 342, 170, 20);
        add(txtValor);

        txtNumCardapio = new JTextField();
        txtNumCardapio.setHorizontalAlignment(SwingConstants.CENTER);
        txtNumCardapio.setFont(new Font("Arial", Font.PLAIN, 15));
        txtNumCardapio.setColumns(10);
        txtNumCardapio.setBorder(null);
        txtNumCardapio.setBounds(70, 341, 170, 20);
        add(txtNumCardapio);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PainelCadastroCardapio.class.getResource("/arquivos/TelaCadastroCardapio.png")));
        lblNewLabel.setBounds(0, 0, 881, 661);
        add(lblNewLabel);
    }

    private void createEvents() {
    	btAdicionarCardapio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtNomePedido.getText().isEmpty() || txtNumCardapio.getText().isEmpty() || txtValor.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        int numPedido = Integer.parseInt(txtNumCardapio.getText());
                        double valor = Double.parseDouble(txtValor.getText());
                        String nomePedido = txtNomePedido.getText();

                        Connection conexao = ConexaoBancoDados.conectar();
                        String insertCardapio = "INSERT INTO cardapio (NumeroCardapio, NomeDoPedido, Valor) VALUES (?,?,?)";

                        try (PreparedStatement ps = conexao.prepareStatement(insertCardapio)) {
                            ps.setInt(1, numPedido);
                            ps.setString(2, nomePedido);
                            ps.setDouble(3, valor);
                            int linhasAfetadas = ps.executeUpdate();

                            if (linhasAfetadas > 0) {
                                System.out.println("Dados inseridos com sucesso!");
                                JOptionPane.showMessageDialog(null, "Cardápio cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                txtNomePedido.setText("");
                                txtNumCardapio.setText("");
                                txtValor.setText("");
                            } else {
                                System.out.println("Falha ao inserir dados.");
                                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o cardápio!", "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cardápio: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        } finally {
                            ConexaoBancoDados.fechar(conexao);
                        }
                    } catch (NumberFormatException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Revise se o número do cardápio e o valor são números válidos!", "Alerta", JOptionPane.WARNING_MESSAGE);
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
				PainelAdministracao painelAdm = new PainelAdministracao();
				// Adiciona o novo painel a tela
				add(painelAdm);
				revalidate();
				repaint();
            }
        });
    }
    
    private void createEvents2() {
    	btVoltar.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btVoltar.setIcon(new ImageIcon(PainelFinalizarMesa.class.getResource("/arquivos/HoverBtVoltar.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btVoltar.setIcon(new ImageIcon(PainelFinalizarMesa.class.getResource("/arquivos/BtVoltar.png")));
    	    }
    	});

    	btAdicionarCardapio.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btAdicionarCardapio.setIcon(new ImageIcon(PainelCadastroCardapio.class.getResource("/arquivos/HoverBtAdicionarCardapio.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btAdicionarCardapio.setIcon(new ImageIcon(PainelCadastroCardapio.class.getResource("/arquivos/BtAdicionarCardapio.png")));
    	    }
    	});

	}
}
