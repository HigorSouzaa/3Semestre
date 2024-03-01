package paineis.interfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import classes.SuperUser;

public class PainelLoginAdm extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel telaFundo;
    private JPasswordField txtSenha;
    private JTextField txtUsuario;
    private JButton btVoltar, btLogar;
    private SuperUser User = new SuperUser();
    
    public PainelLoginAdm() {
        setBounds(0, 0, 900, 700);
        setLayout(null);
        iniciarComponentes();
        criarEventos();
        criarEventos2();
        
        
   }



	private void iniciarComponentes() {
		 
		 // campo pra senha
		 txtSenha = new JPasswordField();
		 txtSenha.setFont(new Font("Arial", Font.PLAIN, 23));
		 txtSenha.setBorder(null);
		 txtSenha.setBounds(104, 541, 272, 31);
		 add(txtSenha);
		 
		 // campo pra usuario
		 txtUsuario = new JTextField();
		 txtUsuario.setBorder(null);
		 txtUsuario.setFont(new Font("Arial", Font.PLAIN, 23));
		 txtUsuario.setBounds(104, 366, 272, 31);
		 add(txtUsuario);
		 txtUsuario.setColumns(10);
			
		 // botao voltar para o main
		 btVoltar = new JButton("");
		 btVoltar.setIcon(new ImageIcon(PainelLoginAdm.class.getResource("/arquivos/BtVoltar.png")));
		 btVoltar.setFocusPainted(false);
		 btVoltar.setBorderPainted(false);
		 btVoltar.setContentAreaFilled(false);
		 btVoltar.setBounds(0, 11, 66, 55);
		 add(btVoltar);
		 
		 // Botao Admistração
		 btLogar = new JButton("");
		 btLogar.setIcon(new ImageIcon(PainelLoginAdm.class.getResource("/arquivos/BtLogar.png")));
		 btLogar.setFocusPainted(false);
		 btLogar.setBorderPainted(false);
		 btLogar.setContentAreaFilled(false);
		 btLogar.setBounds(542, 522, 230, 70);
		 add(btLogar);
		 
		 // jlabel de background
		 telaFundo = new JLabel("");
		 telaFundo.setIcon(new ImageIcon(PainelLoginAdm.class.getResource("/arquivos/TelaLoginAdminstracao.png")));
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
		        if (txtUsuario.getText().isEmpty() || txtSenha.getPassword().length == 0) {
		            JOptionPane.showMessageDialog(null, "Por favor, preencha usuário e senha", "Alerta", JOptionPane.WARNING_MESSAGE);
		        } else {
		            if (txtSenha.getText().equals(User.getPassword()) && txtUsuario.getText().equals(User.getUser())) {
		                removeAll();
		                revalidate();
		                repaint();
		                PainelAdministracao painelAdm = new PainelAdministracao();
		                add(painelAdm);
		                revalidate();
		                repaint();
		            } else {
		                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos", "Alerta", JOptionPane.ERROR_MESSAGE);
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
				btLogar.setIcon(new ImageIcon(PainelLoginAdm.class.getResource("/arquivos/HoverBtLogar.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btLogar.setIcon(new ImageIcon(PainelLoginAdm.class.getResource("/arquivos/BtLogar.png")));
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
