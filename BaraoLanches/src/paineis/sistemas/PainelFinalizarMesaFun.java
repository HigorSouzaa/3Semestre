package paineis.sistemas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classes.Mesa;
import paineis.interfaces.PainelAdministracao;
import paineis.interfaces.PainelFuncionario;
import sistemas.ConexaoBancoDados;

public class PainelFinalizarMesaFun extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Mesa> mesas = new ArrayList<>();
    private JPanel panelMesa;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JButton btFinalizarMesa;
    private JButton btVisualizarMesa;
    private JButton btVoltar, btFechar;


    public PainelFinalizarMesaFun() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(0, 0, 900, 700);
        initComponents();
        createEvents();
        criarEventos2();
    }

    private void initComponents() {
        
        btFinalizarMesa = new JButton("");
        btFinalizarMesa.setBounds(101, 451, 230, 70);
        add(btFinalizarMesa);
        btFinalizarMesa.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/BtFinalizarMesa.png")));
        btFinalizarMesa.setContentAreaFilled(false);
        btFinalizarMesa.setBorderPainted(false);
        panelMesa = new JPanel();
        panelMesa.setBackground(Color.decode("#690303"));
        panelMesa.setBounds(427, 269, 418, 355);
        panelMesa.setLayout(null);
//        add(panelMesa);
        
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 58, 402, 235);
        panelMesa.add(scrollPane);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setForeground(new Color(255, 255, 255));
        textArea.setFont(new Font("Arial", Font.PLAIN, 17));
        textArea.setBackground(Color.decode("#91413F"));
        scrollPane.setViewportView(textArea);
        
        btFechar = new JButton("");
        btFechar.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/BtFechar.png")));
        btFechar.setContentAreaFilled(false);
        btFechar.setBorderPainted(false);
        btFechar.setBounds(10, 11, 48, 35);
        panelMesa.add(btFechar);
        
        btVisualizarMesa = new JButton("");
        btVisualizarMesa.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/BtVisualizarMesa.png")));
        btVisualizarMesa.setContentAreaFilled(false);
        btVisualizarMesa.setBorderPainted(false);
        btVisualizarMesa.setBounds(101, 359, 230, 70);
        add(btVisualizarMesa);
        
        btVoltar = new JButton("");
        btVoltar.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/BtVoltar.png")));
        btVoltar.setContentAreaFilled(false);
        btVoltar.setBorderPainted(false);
        btVoltar.setBounds(10, 11, 66, 55);
        add(btVoltar);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/TelaFinalizarMesa.png")));
        lblNewLabel.setBounds(0, 0, 881, 661);
        add(lblNewLabel);
    }

    private void createEvents() {
    	btFechar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		remove(panelMesa);
        	}
        });
        
    	btFinalizarMesa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int numMesa = Integer.parseInt(JOptionPane.showInputDialog("Informe o número da mesa a finalizar:"));
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja finalizar a mesa " + numMesa + "?");
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        Connection conexao = ConexaoBancoDados.conectar();
                        // comando do banco de dados;
                        String deleteQuery = "DELETE FROM mesa WHERE NumMesa = ?";
                        try (PreparedStatement ps = conexao.prepareStatement(deleteQuery)) {
                            ps.setInt(1, numMesa);
                            int rowsAffected = ps.executeUpdate();
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Mesa " + numMesa + " finalizada com sucesso!");
                                textArea.setText(""); // Limpa o textArea após a exclusão
                            } else {
                                JOptionPane.showMessageDialog(null, "Nenhuma mesa encontrada com o número " + numMesa);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        } finally {
                            ConexaoBancoDados.fechar(conexao);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Insira um número válido para a mesa", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        btVisualizarMesa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
            	try {
            		int numMesa = Integer.parseInt(JOptionPane.showInputDialog("Informe o numero da mesa a visualizar."));
            		add(panelMesa);
            		Connection conexao = ConexaoBancoDados.conectar();
					// comando do banco de dados;
            		String SelectQuery = "SELECT * FROM mesa WHERE NumMesa = ?";
					try (PreparedStatement ps = conexao.prepareStatement(SelectQuery)) {
						ps.setInt(1, numMesa);
						
						ResultSet rs = ps.executeQuery();
						
						mesas.clear();
						while (rs.next()) {
							int numCardapio = rs.getInt("NumeroCardapio");
							int quantidade = rs.getInt("Quantidade");
							String nomeCardapio = rs.getString("NomeDoPedido");
							double valoTotal = rs.getDouble("ValorTotal");
							
							Mesa mesa = new Mesa(numMesa, numCardapio, quantidade, valoTotal, nomeCardapio);
							mesas.add(mesa);
						}
					
				        textArea.setText(("Numero \tNome \tQuantidade \tValor Total\n"));
				        double valorTotalMesa = 0;
				        for (Mesa mesa : mesas) {
							textArea.append(mesa.mostrarDados());;
							valorTotalMesa += mesa.getValorTotal();
						}
				        textArea.append("\n "+"\n\t" + "               Valor total da mesa: R$"+ valorTotalMesa);
				        
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					} finally {
						ConexaoBancoDados.fechar(conexao);
					}
            		
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Insira numeros validos", "Erro", JOptionPane.ERROR_MESSAGE);
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
    	btFinalizarMesa.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btFinalizarMesa.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/HoverBtFinalizarMesa.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btFinalizarMesa.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/BtFinalizarMesa.png")));
    	    }
    	});

    	btVisualizarMesa.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btVisualizarMesa.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/HoverBtVisualizarMesa.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btVisualizarMesa.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/BtVisualizarMesa.png")));
    	    }
    	});
    	
    	btVoltar.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btVoltar.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/HoverBtVoltar.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btVoltar.setIcon(new ImageIcon(PainelFinalizarMesaFun.class.getResource("/arquivos/BtVoltar.png")));
    	    }
    	});


    }
}
