package visao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;

public class CadastraUsuarioGUI {
	
	public CadastraUsuarioGUI() {}
	
	public void mostrarJanela(final JFrame parent, final MercadoLeilao mercado) {
		final JFrame frame = new JFrame();
		frame.setTitle("Cadastrar Usuário");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 381);
		frame.setLocationRelativeTo(parent);
		frame.getContentPane().setLayout(new MigLayout("al center center,fillx", 
				"[grow]", "[][]20[][]20[][]20[][]40[]"));
		
		final JLabel lblNome = new JLabel("Nome Usuário:");
		frame.getContentPane().add(lblNome, "span,grow");
		
		final JTextField tfNome = new JTextField();
		frame.getContentPane().add(tfNome, "span,grow,height 25::");
		tfNome.setColumns(10);
		
		final JLabel lblEndereco = new JLabel("Endereço Usuário:");
		frame.getContentPane().add(lblEndereco, "span,grow");
		
		final JTextField tfEndereco = new JTextField();
		frame.getContentPane().add(tfEndereco, "span,grow,height 25::");
		tfEndereco.setColumns(10);
		
		final JLabel lblEmail = new JLabel("E-mail Usuário:");
		frame.getContentPane().add(lblEmail, "span,grow");
		
		final JTextField tfEmail = new JTextField();
		frame.getContentPane().add(tfEmail, "span,grow,height 25::");
		tfEmail.setColumns(10);
		
		final JLabel lblApelidoUsuario = new JLabel("Apelido Usuário:");
		frame.getContentPane().add(lblApelidoUsuario, "span,grow");
		
		final JTextField tfApelidoUsuario = new JTextField();
		frame.getContentPane().add(tfApelidoUsuario, "span,grow,height 25::");
		tfApelidoUsuario.setColumns(10);
		
		final JButton btnCadastrar = new JButton("Cadastrar");
		frame.getContentPane().add(btnCadastrar, "span,grow,height 30::");
		
		frame.setVisible(true);
	}
}
