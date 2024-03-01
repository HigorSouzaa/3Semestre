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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classes.ClienteVIP;
import paineis.interfaces.PainelAdministracao;
import sistemas.ConexaoBancoDados;

public class PainelAddClienteVip extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCpf;
    private JTextField txtNivelVip;
    private JButton btAdicionarVip, btVoltar;

    public PainelAddClienteVip() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(0, 0, 900, 700);
        initComponents();
        createEvents();
        criarEventos2();
    }

    private void initComponents() {
        txtNivelVip = new JTextField();
        txtNivelVip.setHorizontalAlignment(SwingConstants.CENTER);
        txtNivelVip.setFont(new Font("Arial", Font.PLAIN, 15));
        txtNivelVip.setColumns(10);
        txtNivelVip.setBorder(null);
        txtNivelVip.setBounds(513, 411, 170, 20);
        add(txtNivelVip);

        txtCpf = new JTextField();
        txtCpf.setHorizontalAlignment(SwingConstants.CENTER);
        txtCpf.setFont(new Font("Arial", Font.PLAIN, 15));
        txtCpf.setColumns(10);
        txtCpf.setBorder(null);
        txtCpf.setBounds(217, 410, 170, 20);
        add(txtCpf);

        btAdicionarVip = new JButton("");
        btAdicionarVip.setIcon(new ImageIcon(PainelAddClienteVip.class.getResource("/arquivos/BtAdicionarClienteVip.png")));
        btAdicionarVip.setContentAreaFilled(false);
        btAdicionarVip.setBorderPainted(false);
        btAdicionarVip.setBounds(356, 511, 230, 70);
        add(btAdicionarVip);

        btVoltar = new JButton("");
        btVoltar.setIcon(new ImageIcon(PainelAddClienteVip.class.getResource("/arquivos/BtVoltar.png")));
        btVoltar.setContentAreaFilled(false);
        btVoltar.setBorderPainted(false);
        btVoltar.setBounds(10, 11, 66, 55);
        add(btVoltar);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PainelAddClienteVip.class.getResource("/arquivos/TelaAdicionarVip.png")));
        lblNewLabel.setBounds(0, 0, 881, 661);
        add(lblNewLabel);
    }

    private void createEvents() {
    	btAdicionarVip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCpf.getText();
                int nivelVIP = Integer.parseInt(txtNivelVip.getText());

                // Realizar a busca no banco de dados pelo CPF
                Connection conexao = ConexaoBancoDados.conectar();
                String consultaSql = "SELECT * FROM cliente WHERE CPF = ?";
                try (PreparedStatement stmt = conexao.prepareStatement(consultaSql)) {
                    stmt.setString(1, cpf);
                    ResultSet resultSet = stmt.executeQuery();

                    if (resultSet.next()) {
                        String nome = resultSet.getString("Nome");
                        String datNasc = resultSet.getString("DataNascimento");
                        String telefone = resultSet.getString("Telefone");
                        String endereco = resultSet.getString("Endereco");
                        String email = resultSet.getString("Email");

                        ClienteVIP clienteVIP = new ClienteVIP(nome, datNasc, cpf, telefone, endereco, email, nivelVIP);
                        double desconto = clienteVIP.calcularDesconto(nivelVIP);

                        JOptionPane.showMessageDialog(null, "Cliente VIP criado:\n" + clienteVIP.mostrarDados() + "Desconto: " + desconto);
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
				PainelAdministracao painelAdm = new PainelAdministracao();
				// Adiciona o novo painel a tela
				add(painelAdm);
				revalidate();
				repaint();
            }
        });
    }
    
    private void criarEventos2() {
    	btAdicionarVip.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btAdicionarVip.setIcon(new ImageIcon(PainelAddClienteVip.class.getResource("/arquivos/HoverBtAdicionarClienteVip.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btAdicionarVip.setIcon(new ImageIcon(PainelAddClienteVip.class.getResource("/arquivos/BtAdicionarClienteVip.png")));
    	    }
    	});

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
    }
}
