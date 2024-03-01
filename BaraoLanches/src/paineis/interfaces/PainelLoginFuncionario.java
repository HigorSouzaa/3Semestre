package paineis.interfaces;

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

import sistemas.ConexaoBancoDados;

public class PainelLoginFuncionario extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel telaFundo;
    private JTextField txtEmail;
    private JButton btVoltar, btLogar;
    private JTextField txtCpf;
    
    public PainelLoginFuncionario() {
        setBounds(0, 0, 900, 700);
        setLayout(null);
        iniciarComponentes();
        criarEventos();
        criarEventos2();
        
        
   }

	private void iniciarComponentes() {
		 // campo pra Cpf
		 txtCpf = new JTextField();
		 txtCpf.setFont(new Font("Arial", Font.PLAIN, 23));
		 txtCpf.setColumns(10);
		 txtCpf.setBorder(null);
		 txtCpf.setBounds(104, 541, 272, 31);
		 add(txtCpf);
		 
		 // campo pra Email
		 txtEmail = new JTextField();
		 txtEmail.setBorder(null);
		 txtEmail.setFont(new Font("Arial", Font.PLAIN, 23));
		 txtEmail.setBounds(104, 366, 272, 31);
		 add(txtEmail);
		 txtEmail.setColumns(10);
			
		 // botao voltar para o main
		 btVoltar = new JButton("");
		 btVoltar.setIcon(new ImageIcon(PainelLoginFuncionario.class.getResource("/arquivos/BtVoltar.png")));
		 btVoltar.setFocusPainted(false);
		 btVoltar.setBorderPainted(false);
		 btVoltar.setContentAreaFilled(false);
		 btVoltar.setBounds(0, 11, 66, 55);
		 add(btVoltar);
		 
		 // Botao Admistração
		 btLogar = new JButton("");
		 btLogar.setIcon(new ImageIcon(PainelLoginFuncionario.class.getResource("/arquivos/BtLogar.png")));
		 btLogar.setFocusPainted(false);
		 btLogar.setBorderPainted(false);
		 btLogar.setContentAreaFilled(false);
		 btLogar.setBounds(542, 522, 230, 70);
		 add(btLogar);
		 
		 // jlabel de background
		 telaFundo = new JLabel("");
		 telaFundo.setIcon(new ImageIcon(PainelLoginFuncionario.class.getResource("/arquivos/TelaLoginFuncionario.png")));
		 telaFundo.setBounds(0, 0, 884, 661);
		 add(telaFundo);
		
	}

	private void criarEventos() {
		// ação para voltar pra painel principal
		btVoltar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {	
		 		limpaTela();
		 		PainelTelaPrincipal painelPrincipal = new PainelTelaPrincipal();
		 		// Adiciona o novo painel a tela
				add(painelPrincipal);
				revalidate();
				repaint();

		 	}
		});
		
		// ação para no menu adm
		btLogar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if (txtCpf.getText().isEmpty() || txtEmail.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, preencha usuário e senha", "Alerta", JOptionPane.WARNING_MESSAGE);
		        } else {
		        	Connection conexao = ConexaoBancoDados.conectar();
					// comando do banco de dados;
					String sqlQuery = "SELECT * FROM funcionarios WHERE CPF = ? OR Email = ?";
					try (PreparedStatement ps = conexao.prepareStatement(sqlQuery)) {
						ps.setString(1, txtCpf.getText());
						ps.setString(2, txtEmail.getText());
						
						ResultSet rs = ps.executeQuery();
						
						if (rs.next()) {
							// remove os conteudo da tela
							removeAll();
							revalidate();
							repaint();
							PainelFuncionario painelFun = new PainelFuncionario();
							// Adiciona o novo painel a tela
							add(painelFun);
							revalidate();
							repaint();							
						} else {
							JOptionPane.showMessageDialog(null, "Funcionario não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
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
	}
	
	private void criarEventos2() {
		// Efeito de hover para o botão Voltar
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
		
		// Efeito de hover para o botão Logar
		btLogar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btLogar.setIcon(new ImageIcon(PainelLoginFuncionario.class.getResource("/arquivos/HoverBtLogar.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btLogar.setIcon(new ImageIcon(PainelLoginFuncionario.class.getResource("/arquivos/BtLogar.png")));
			}
		});
	}
	
	public void limpaTela() {
		// remove os conteudo da tela
		removeAll();
		revalidate();
		repaint();
	}
}
