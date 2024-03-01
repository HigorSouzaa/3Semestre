package paineis.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import paineis.sistemas.PainelCadastroCliente;
import paineis.sistemas.PainelCadastroClienteFun;
import paineis.sistemas.PainelCadastroPedidoFun;
import paineis.sistemas.PainelFinalizarMesaFun;

public class PainelFuncionario extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton btVoltar, btFinalizarMesa,btCadastrarCliente, btFazerPedido;

    public PainelFuncionario() {
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
        
        btFinalizarMesa = new JButton("");
        btFinalizarMesa.setIcon(new ImageIcon(PainelFuncionario.class.getResource("/arquivos/BtFinalizarMesa.png")));
        btFinalizarMesa.setContentAreaFilled(false);
        btFinalizarMesa.setBorderPainted(false);
        btFinalizarMesa.setBounds(82, 526, 230, 70);
        add(btFinalizarMesa);
        
        btCadastrarCliente = new JButton("");
        btCadastrarCliente.setIcon(new ImageIcon(PainelFuncionario.class.getResource("/arquivos/BtCadastrarCliente.png")));
        btCadastrarCliente.setContentAreaFilled(false);
        btCadastrarCliente.setBorderPainted(false);
        btCadastrarCliente.setBounds(82, 364, 230, 70);
        add(btCadastrarCliente);
        
        btFazerPedido = new JButton("");
        btFazerPedido.setIcon(new ImageIcon(PainelFuncionario.class.getResource("/arquivos/BtFazerPedido.png")));
        btFazerPedido.setContentAreaFilled(false);
        btFazerPedido.setBorderPainted(false);
        btFazerPedido.setBounds(82, 445, 230, 70);
        add(btFazerPedido);

        

        // jlabel como background
        JLabel telaFundo = new JLabel("");
        telaFundo.setBounds(0, 0, 900, 689);
        telaFundo.setIcon(new ImageIcon(PainelFuncionario.class.getResource("/arquivos/TelaFuncionario.png")));
        add(telaFundo);
    }

    private void criarEventos() {
    	
        btFinalizarMesa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// remove os conteudo da tela
				removeAll();
				revalidate();
				repaint();
				PainelFinalizarMesaFun painelFinMesa = new PainelFinalizarMesaFun();
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
				PainelCadastroPedidoFun painelPedido = new PainelCadastroPedidoFun();
				// Adiciona o novo painel a tela
				add(painelPedido);
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
				PainelCadastroClienteFun cadastroCliente = new PainelCadastroClienteFun();
				// Adiciona o novo painel a tela
				add(cadastroCliente);
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
				PainelLoginFuncionario loginFun = new PainelLoginFuncionario();
				// Adiciona o novo painel a tela
				add(loginFun);
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

    	btFinalizarMesa.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btFinalizarMesa.setIcon(new ImageIcon(PainelFuncionario.class.getResource("/arquivos/HoverBtFinalizarMesa.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btFinalizarMesa.setIcon(new ImageIcon(PainelFuncionario.class.getResource("/arquivos/BtFinalizarMesa.png")));
    	    }
    	});

    	btCadastrarCliente.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btCadastrarCliente.setIcon(new ImageIcon(PainelFuncionario.class.getResource("/arquivos/HoverBtCadastrarCliente.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btCadastrarCliente.setIcon(new ImageIcon(PainelFuncionario.class.getResource("/arquivos/BtCadastrarCliente.png")));
    	    }
    	});

    	btFazerPedido.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btFazerPedido.setIcon(new ImageIcon(PainelFuncionario.class.getResource("/arquivos/HoverBtFazerPedido.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btFazerPedido.setIcon(new ImageIcon(PainelFuncionario.class.getResource("/arquivos/BtFazerPedido.png")));
    	    }
    	});

	}

}
