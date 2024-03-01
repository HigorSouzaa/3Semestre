package paineis.interfaces;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import paineis.sistemas.PainelAddClienteVip;
import paineis.sistemas.PainelCadastroCardapio;
import paineis.sistemas.PainelCadastroCliente;
import paineis.sistemas.PainelCadastroFuncionario;
import paineis.sistemas.PainelCadastroPedido;
import paineis.sistemas.PainelFinalizarMesa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelAdministracao extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton btCadastraFun;
    private JButton btAdicionarCardapio,btVoltar, btFinalizarMesa, btAdicionarVip,btCadastrarCliente, btFazerPedido;

    public PainelAdministracao() {
        setBounds(0, 0, 900, 700);
        setLayout(null);
        iniciarComponentes();
        criarEventos();
        criarEventos2();
    }

    
	private void iniciarComponentes() {
		btVoltar = new JButton();
    	btVoltar.setIcon(new ImageIcon(PainelCadastroCliente.class.getResource("/arquivos/BtVoltar.png")));
		btVoltar.setContentAreaFilled(false);
		btVoltar.setBorderPainted(false);
		btVoltar.setBounds(10, 11, 66, 55);
		add(btVoltar);
		
        btAdicionarVip = new JButton("");
        btAdicionarVip.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtAdicionarClienteVip.png")));
        btAdicionarVip.setContentAreaFilled(false);
        btAdicionarVip.setBorderPainted(false);
        btAdicionarVip.setBounds(585, 526, 230, 70);
        add(btAdicionarVip);
        
        btFinalizarMesa = new JButton("");
        btFinalizarMesa.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtFinalizarMesa.png")));
        btFinalizarMesa.setContentAreaFilled(false);
        btFinalizarMesa.setBorderPainted(false);
        btFinalizarMesa.setBounds(82, 526, 230, 70);
        add(btFinalizarMesa);
        
        btCadastrarCliente = new JButton("");
        btCadastrarCliente.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtCadastrarCliente.png")));
        btCadastrarCliente.setContentAreaFilled(false);
        btCadastrarCliente.setBorderPainted(false);
        btCadastrarCliente.setBounds(82, 364, 230, 70);
        add(btCadastrarCliente);
        
        btFazerPedido = new JButton("");
        btFazerPedido.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtFazerPedido.png")));
        btFazerPedido.setContentAreaFilled(false);
        btFazerPedido.setBorderPainted(false);
        btFazerPedido.setBounds(82, 445, 230, 70);
        add(btFazerPedido);
        
        btAdicionarCardapio = new JButton("");
        btAdicionarCardapio.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtCadastrarCardapio.png")));
        btAdicionarCardapio.setContentAreaFilled(false);
        btAdicionarCardapio.setBorderPainted(false);
        btAdicionarCardapio.setBounds(585, 364, 230, 70);
        add(btAdicionarCardapio);
        
        btCadastraFun = new JButton("");
        btCadastraFun.setBounds(585, 445, 230, 70);
        btCadastraFun.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtCadastrarFun.png")));
        btCadastraFun.setContentAreaFilled(false);
        btCadastraFun.setBorderPainted(false);
        add(btCadastraFun);

        

        // jlabel como background
        JLabel telaFundo = new JLabel("");
        telaFundo.setBounds(0, 0, 900, 689);
        telaFundo.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/TelaAdmistração.png")));
        add(telaFundo);
    }

    private void criarEventos() {


        btCadastraFun.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// remove os conteudo da tela
				removeAll();
				revalidate();
				repaint();
				PainelCadastroFuncionario painelFuncionario = new PainelCadastroFuncionario();
				// Adiciona o novo painel a tela
				add(painelFuncionario);
				revalidate();
				repaint();

        	}
        });
    	
        btFinalizarMesa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// remove os conteudo da tela
				removeAll();
				revalidate();
				repaint();
				PainelFinalizarMesa painelFinMesa = new PainelFinalizarMesa();
				// Adiciona o novo painel a tela
				add(painelFinMesa);
				revalidate();
				repaint();

        	}
        });
    	
        btFazerPedido.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// remove os conteudo da tela
				removeAll();
				revalidate();
				repaint();
				PainelCadastroPedido painelPedido = new PainelCadastroPedido();
				// Adiciona o novo painel a tela
				add(painelPedido);
				revalidate();
				repaint();
        	}
        });
    	
        btAdicionarVip.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// remove os conteudo da tela
				removeAll();
				revalidate();
				repaint();
				PainelAddClienteVip painelClienteVip = new PainelAddClienteVip();
				// Adiciona o novo painel a tela
				add(painelClienteVip);
				revalidate();
				repaint();

        	}
        });
    	
    	btCadastrarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // remove os conteudo da tela
				removeAll();
				revalidate();
				repaint();
				PainelCadastroCliente cadastroCliente = new PainelCadastroCliente();
				// Adiciona o novo painel a tela
				add(cadastroCliente);
				revalidate();
				repaint();
            }
        });
    	
        btAdicionarCardapio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// remove os conteudo da tela
				removeAll();
				revalidate();
				repaint();
				PainelCadastroCardapio painelCardapio = new PainelCadastroCardapio();
				// Adiciona o novo painel a tela
				add(painelCardapio);
				revalidate();
				repaint();

        	}
        });
    	
    	btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// remove os conteudo da tela
				removeAll();
				revalidate();
				repaint();
				PainelLoginAdm loginAdm = new PainelLoginAdm();
				// Adiciona o novo painel a tela
				add(loginAdm);
				revalidate();
				repaint();

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
    	
    	btAdicionarVip.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btAdicionarVip.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/HoverBtAdicionarClienteVip.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btAdicionarVip.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtAdicionarClienteVip.png")));
    	    }
    	});

    	btFinalizarMesa.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btFinalizarMesa.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/HoverBtFinalizarMesa.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btFinalizarMesa.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtFinalizarMesa.png")));
    	    }
    	});

    	btCadastrarCliente.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btCadastrarCliente.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/HoverBtCadastrarCliente.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btCadastrarCliente.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtCadastrarCliente.png")));
    	    }
    	});

    	btFazerPedido.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btFazerPedido.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/HoverBtFazerPedido.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btFazerPedido.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtFazerPedido.png")));
    	    }
    	});

    	btAdicionarCardapio.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btAdicionarCardapio.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/HoverBtCadastrarCardapio.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btAdicionarCardapio.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtCadastrarCardapio.png")));
    	    }
    	});

    	btCadastraFun.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btCadastraFun.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/HoverBtCadastrarFun.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btCadastraFun.setIcon(new ImageIcon(PainelAdministracao.class.getResource("/arquivos/BtCadastrarFun.png")));
    	    }
    	});

	}

}
