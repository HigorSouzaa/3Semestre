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

import paineis.interfaces.PainelAdministracao;
import paineis.interfaces.PainelFuncionario;
import paineis.interfaces.PainelTelaPrincipal;
import sistemas.ConexaoBancoDados;

public class PainelCadastroClienteFun extends JPanel {

    /**
     * Create Jpanel 
     * Cadastro Cliente
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
    private JTextField txtDataNasc;
    private JTextField txtEndereco;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JButton btPesquisarCliente, btCadastrar, btVoltar, btDeletarCliente;

    public PainelCadastroClienteFun() {
        setLayout(null);
        setBounds(0, 0, 900, 700);
        iniciarComponentes();
        criarEventos();
        criarEventos2();
    }

    private void iniciarComponentes() {
    	
    	btDeletarCliente = new JButton("");
    	btDeletarCliente.setIcon(new ImageIcon(PainelCadastroClienteFun.class.getResource("/arquivos/BtDeletarCliente.png")));
    	btDeletarCliente.setContentAreaFilled(false);
    	btDeletarCliente.setBorderPainted(false);
    	btDeletarCliente.setBounds(605, 574, 230, 70);
    	add(btDeletarCliente);
    	
    	btVoltar = new JButton();
    	btVoltar.setIcon(new ImageIcon(PainelCadastroClienteFun.class.getResource("/arquivos/BtVoltar.png")));
		btVoltar.setContentAreaFilled(false);
		btVoltar.setBorderPainted(false);
		btVoltar.setBounds(10, 11, 66, 55);
		add(btVoltar);
    	
    	btCadastrar = new JButton("");
		btCadastrar.setIcon(new ImageIcon(PainelCadastroClienteFun.class.getResource("/arquivos/BtCadastrarCliente.png")));
		btCadastrar.setContentAreaFilled(false);
		btCadastrar.setBorderPainted(false);
		btCadastrar.setBounds(59, 574, 230, 70);
		add(btCadastrar);
		    	
    	btPesquisarCliente = new JButton("");
		btPesquisarCliente.setIcon(new ImageIcon(PainelCadastroClienteFun.class.getResource("/arquivos/BtPesquisarCliente.png")));
		btPesquisarCliente.setContentAreaFilled(false);
		btPesquisarCliente.setBorderPainted(false);
		btPesquisarCliente.setBounds(341, 574, 230, 70);
		add(btPesquisarCliente);
    	
    	txtCpf = new JTextField();
		txtCpf.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCpf.setColumns(10);
		txtCpf.setBorder(null);
		txtCpf.setBounds(142, 411, 170, 20);
		add(txtCpf);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBorder(null);
		txtEmail.setBounds(491, 519, 170, 20);
		add(txtEmail);
		
		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTelefone.setColumns(10);
		txtTelefone.setBorder(null);
		txtTelefone.setBounds(142, 519, 170, 20);
		add(txtTelefone);
		
		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEndereco.setColumns(10);
		txtEndereco.setBorder(null);
		txtEndereco.setBounds(491, 411, 170, 20);
		add(txtEndereco);
		
		txtDataNasc = new JTextField();
		txtDataNasc.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDataNasc.setColumns(10);
		txtDataNasc.setBorder(null);
		txtDataNasc.setBounds(491, 303, 170, 20);
		add(txtDataNasc);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNome.setBorder(null);
		txtNome.setBounds(142, 303, 170, 20);
		add(txtNome);
		txtNome.setColumns(10);
		
		JLabel Label_BackGround = new JLabel("");
		Label_BackGround.setFont
		(new Font("Arial", Font.PLAIN, 15));
		Label_BackGround.setIcon(new ImageIcon(PainelCadastroClienteFun.class.getResource("/arquivos/TelaCadastroCliente.png")));
		Label_BackGround.setBounds(0, 0, 881, 661);
		add(Label_BackGround);
		
    }

    private void criarEventos() {
    	btDeletarCliente.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        if (txtCpf.getText().isEmpty() && txtDataNasc.getText().isEmpty() && txtEmail.getText().isEmpty() && txtEndereco.getText().isEmpty() && txtEndereco.getText().isEmpty() && txtNome.getText().isEmpty() && txtTelefone.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor pesquise o cliente antes de apagalo", "Alert!!!!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					// Solicitação de confirmação ao usuário
	    	        int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar o cliente?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
	    	        
	    	        if (confirmacao == JOptionPane.YES_OPTION) {
	    	            String cpf = txtCpf.getText();
	    	            
	    	            Connection conexao = ConexaoBancoDados.conectar();
	    	            String deleteCliente = "DELETE FROM cliente WHERE cpf = ?";
	    	            
	    	            try (PreparedStatement ps = conexao.prepareStatement(deleteCliente)) {
	    	                ps.setString(1, cpf);
	    	                
	    	                int linhasAfetadas = ps.executeUpdate();
	    	                
	    	                if (linhasAfetadas > 0) {
	    	                    JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    	                    // Limpa os campos após a exclusão
	    	                    txtNome.setText("");
	    	                    txtDataNasc.setText("");
	    	                    txtCpf.setText("");
	    	                    txtEmail.setText("");
	    	                    txtTelefone.setText("");
	    	                    txtEndereco.setText("");
	    	                } else {
	    	                    JOptionPane.showMessageDialog(null, "Erro ao deletar cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
	    	                }
	    	            } catch (SQLException ex) {
	    	                ex.printStackTrace();
	    	                JOptionPane.showMessageDialog(null, "Erro ao deletar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	    	            } finally {
	    	                ConexaoBancoDados.fechar(conexao);
	    	            }
	    	        }
				}
    	    }
    	});

    	
    	btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = txtCpf.getText();
				String nome = txtNome.getText();
				String dataNasc = txtDataNasc.getText();
				String telefone = txtTelefone.getText();
				String endereco = txtEndereco.getText();
				String email = txtEmail.getText();
				
				
				// Verificar campos vazios
                if (nome.isEmpty() || cpf.isEmpty() || dataNasc.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Alert!!!", JOptionPane.WARNING_MESSAGE);
                } else {

                	Connection conexao = ConexaoBancoDados.conectar();
                	String insertCliente = "INSERT INTO cliente (cpf, nome, dataNascimento, telefone, endereco, email) VALUES (?, ?, ?, ?, ?, ?)";
                	try (PreparedStatement ps = conexao.prepareStatement(insertCliente)) {
                		ps.setString(1, cpf);
                		ps.setString(2, nome);
                		ps.setString(3, dataNasc);
                		ps.setString(4, telefone);
                		ps.setString(5, endereco);
                		ps.setString(6, email);
                		
                		int linhasAfetadas = ps.executeUpdate();
    		            
    		            if (linhasAfetadas > 0) {
    	                	JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
    		                txtNome.setText("");
    		                txtDataNasc.setText("");
    		                txtCpf.setText("");
    		                txtEmail.setText("");
    		                txtTelefone.setText("");
    		                txtEndereco.setText("");
    		              
    		                
    		            } else {
    		                JOptionPane.showMessageDialog(null, "Erro ao cadastrar Cliente!!!.", "Erro", JOptionPane.ERROR_MESSAGE);
    		            }

					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					} finally {
						ConexaoBancoDados.fechar(conexao);
					}
                               
                }
			}
		});
    	
    	btPesquisarCliente.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String cpf = JOptionPane.showInputDialog("Informe o CPF para pesquisar cliente");
		        
		        
		        Connection conexao = ConexaoBancoDados.conectar();
                String consultaSql = "SELECT * FROM cliente WHERE cpf = ?";
                try (PreparedStatement ps = conexao.prepareStatement(consultaSql)) {
                    ps.setString(1, cpf);
                    ResultSet resultSet = ps.executeQuery();
                    
                    if (resultSet.next()) {
                        // Cliente encontrado, colocar eles nos textFild mais antes limpa os campos
                    	txtNome.setText("");
		                txtDataNasc.setText("");
		                txtCpf.setText("");
		                txtEmail.setText("");
		                txtTelefone.setText("");
		                txtEndereco.setText("");
		                
                    	txtNome.setText(resultSet.getString("nome"));
                    	txtDataNasc.setText(resultSet.getString("dataNascimento"));
                    	txtTelefone.setText(resultSet.getString("telefone"));
                    	txtEndereco.setText(resultSet.getString("endereco"));
                    	txtEmail.setText(resultSet.getString("email"));
                    	txtCpf.setText(resultSet.getString("cpf"));


                    
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }
                    
                    
                    
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    ConexaoBancoDados.fechar(conexao);
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
    	// Efeito de hover para o botão Voltar
    	btDeletarCliente.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseEntered(MouseEvent e) {
    	    	btDeletarCliente.setIcon(new ImageIcon(PainelCadastroClienteFun.class.getResource("/arquivos/HoverBtDeletarCliente.png")));
    		}
    		@Override
    		public void mouseExited(MouseEvent e) {
    	    	btDeletarCliente.setIcon(new ImageIcon(PainelCadastroClienteFun.class.getResource("/arquivos/BtDeletarCliente.png")));
    		}
    	});
    	
    	btVoltar.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			btVoltar.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/HoverBtVoltar.png")));
    		}
    		@Override
    		public void mouseExited(MouseEvent e) {
    			btVoltar.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/BtVoltar.png")));
    		}
    	});
    	
    	btCadastrar.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			btCadastrar.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/HoverBtCadastrarCliente.png")));
    		}
    		@Override
    		public void mouseExited(MouseEvent e) {
    			btCadastrar.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/BtCadastrarCliente.png")));
    		}
    	});
    	
    	btPesquisarCliente.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			btPesquisarCliente.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/HoverBtPesquisarCliente.png")));
    		}
    		@Override
    		public void mouseExited(MouseEvent e) {
    			btPesquisarCliente.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/BtPesquisarCliente.png")));
    		}
    	});
	}
}
