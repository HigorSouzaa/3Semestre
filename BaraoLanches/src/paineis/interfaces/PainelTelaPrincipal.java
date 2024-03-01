package paineis.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelTelaPrincipal extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btAdministracao, btFuncionario;
    private JLabel TelaMainBackGround;

    public PainelTelaPrincipal() {
    	setSize(900, 700);
        setLayout(null);
        iniciarComponentes();
        criarEventos();
        criarEventos2();
    }

    private void iniciarComponentes() {
        // Botao Funcionario
        btFuncionario = new JButton("");
        btFuncionario.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/BotaoFuncionario.png")));
        btFuncionario.setFocusPainted(false);
        btFuncionario.setContentAreaFilled(false);
        btFuncionario.setBorderPainted(false);
        btFuncionario.setBounds(152, 454, 230, 70);
        add(btFuncionario);

        // Botao Admistração
        btAdministracao = new JButton("");
        btAdministracao.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/BotaoAdm.png")));
        btAdministracao.setFocusPainted(false);
        btAdministracao.setBorderPainted(false);
        btAdministracao.setContentAreaFilled(false);
        btAdministracao.setBounds(152, 363, 230, 70);
        add(btAdministracao);

        // Jlabel como background
        TelaMainBackGround = new JLabel("");
        TelaMainBackGround.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/TelaMain.png")));
        TelaMainBackGround.setBounds(0, 0, 900, 700);
        add(TelaMainBackGround);
    }

    private void criarEventos() {
        // Ação dos botões ao clicar
    	btFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// remove os conteudo da tela
				removeAll();
				revalidate();
				repaint();
				PainelLoginFuncionario loginFuncionario = new PainelLoginFuncionario();
				// Adiciona o novo painel a tela
				add(loginFuncionario);
				revalidate();
				repaint();

			}
		});
    	
        btAdministracao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // apagando este painel
            	removeAll();
                revalidate();
                repaint();
                
                // abrindo outro painel
                PainelLoginAdm loginAdm = new PainelLoginAdm();
                add(loginAdm);
                revalidate();
        		repaint();
            }
        });
    }

    private void criarEventos2() {
        // Efeito de hover para o botão btAdministracao
        btAdministracao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btAdministracao.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/BotaoHoverAdm.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btAdministracao.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/BotaoAdm.png")));
            }
        });

        // Efeito de hover para o botão btFuncionario
        btFuncionario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btFuncionario.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/BotaoHoverFuncionario.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btFuncionario.setIcon(new ImageIcon(PainelTelaPrincipal.class.getResource("/arquivos/BotaoFuncionario.png")));
            }
        });
    }
}
