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

public class PainelCadastroFuncionario extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
    private JTextField txtDataNasc;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JTextField txtEndereco;
    private JTextField txtHabilitacao;
    private JTextField txtCargo;
    private JButton btCadastrarFuncionarios, btVoltar;

    public PainelCadastroFuncionario() {
        setLayout(null);
        setBounds(0, 0, 900, 700);
        initComponents();
        createEvents();
        criarEventos2();
    }

    private void initComponents() {
        btCadastrarFuncionarios = new JButton("");
        btCadastrarFuncionarios.setIcon(new ImageIcon(PainelCadastroFuncionario.class.getResource("/arquivos/BtCadastrarFun.png")));
        btCadastrarFuncionarios.setContentAreaFilled(false);
        btCadastrarFuncionarios.setBorderPainted(false);
        btCadastrarFuncionarios.setBounds(556, 503, 230, 70);
        add(btCadastrarFuncionarios);
          
        txtEndereco = new JTextField();
		txtEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		txtEndereco.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEndereco.setColumns(10);
		txtEndereco.setBorder(null);
		txtEndereco.setBounds(577, 419, 170, 20);
		add(txtEndereco);
		
		txtHabilitacao = new JTextField();
		txtHabilitacao.setHorizontalAlignment(SwingConstants.CENTER);
		txtHabilitacao.setFont(new Font("Arial", Font.PLAIN, 15));
		txtHabilitacao.setColumns(10);
		txtHabilitacao.setBorder(null);
		txtHabilitacao.setBounds(306, 523, 170, 20);
		add(txtHabilitacao);
		
		txtTelefone = new JTextField();
		txtTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTelefone.setColumns(10);
		txtTelefone.setBorder(null);
		txtTelefone.setBounds(43, 419, 170, 20);
		add(txtTelefone);
		
		txtCargo = new JTextField();
		txtCargo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCargo.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCargo.setColumns(10);
		txtCargo.setBorder(null);
		txtCargo.setBounds(43, 523, 170, 20);
		add(txtCargo);
		
		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBorder(null);
		txtEmail.setBounds(306, 419, 170, 20);
		add(txtEmail);
		
		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNome.setColumns(10);
		txtNome.setBorder(null);
		txtNome.setBounds(43, 322, 170, 20);
		add(txtNome);
		
		txtCpf = new JTextField();
		txtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		txtCpf.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCpf.setColumns(10);
		txtCpf.setBorder(null);
		txtCpf.setBounds(577, 322, 170, 20);
		add(txtCpf);
				
		txtDataNasc = new JTextField();
		txtDataNasc.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataNasc.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDataNasc.setColumns(10);
		txtDataNasc.setBorder(null);
		txtDataNasc.setBounds(306, 322, 170, 20);
		add(txtDataNasc);

		
		btVoltar = new JButton("");
		btVoltar.setIcon(new ImageIcon(PainelCadastroFuncionario.class.getResource("/arquivos/BtVoltar.png")));
		btVoltar.setContentAreaFilled(false);
		btVoltar.setBorderPainted(false);
		btVoltar.setBounds(10, 11, 66, 55);
		add(btVoltar);
       
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PainelCadastroFuncionario.class.getResource("/arquivos/TelaCadastroFuncionario.png")));
        lblNewLabel.setBounds(0, 0, 881, 661);
        add(lblNewLabel);
    }

    private void createEvents() {

        btCadastrarFuncionarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cargo = txtCargo.getText();
                String nome = txtNome.getText();
                String dataNascimento = txtDataNasc.getText();
                String cpf = txtCpf.getText();
                String email = txtEmail.getText();
                String telefone = txtTelefone.getText();
                String endereco = txtEndereco.getText();
                String habilitacao = txtHabilitacao.getText();

                if (cargo.isEmpty() || nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || dataNascimento.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios (Cargo, Nome, CPF, Data de Nascimento e Email).", "Alert!!!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Connection conexao = ConexaoBancoDados.conectar();
                String insertFuncionario = "INSERT INTO Funcionarios (Nome, DataNascimento, CPF, Email, Telefone, Endereco, Habilitacao, Cargo) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement ps = conexao.prepareStatement(insertFuncionario)) {
                    ps.setString(1, nome);
                    ps.setString(2, dataNascimento);
                    ps.setString(3, cpf);
                    ps.setString(4, email);
                    ps.setString(5, telefone);
                    ps.setString(6, endereco);
                    ps.setString(7, habilitacao);
                    ps.setString(8, cargo);

                    int linhasAfetadas = ps.executeUpdate();

                    if (linhasAfetadas > 0) {
                        JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        limparCampos();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar Funcionario!!!.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionarios: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
    	
    	btCadastrarFuncionarios.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseEntered(MouseEvent e) {
    	        btCadastrarFuncionarios.setIcon(new ImageIcon(PainelCadastroFuncionario.class.getResource("/arquivos/HoverBtCadastrarFun.png")));
    	    }

    	    @Override
    	    public void mouseExited(MouseEvent e) {
    	        btCadastrarFuncionarios.setIcon(new ImageIcon(PainelCadastroFuncionario.class.getResource("/arquivos/BtCadastrarFun.png")));
    	    }
    	});

    }
    private void limparCampos() {
        txtNome.setText("");
        txtDataNasc.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
        txtHabilitacao.setText("");
        txtCargo.setText("");
    }
}
