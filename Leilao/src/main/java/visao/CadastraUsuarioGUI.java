package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import exceptions.CadastroUsuarioException;
import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;

public class CadastraUsuarioGUI extends ParentGUI {
	
	public CadastraUsuarioGUI() {}
	
	@Override
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame.setTitle(i18n.getString("cadastraUsuarioGUI.titulo"));
		currentFrame.setSize(450, 381);
		currentFrame.getContentPane().setLayout(new MigLayout("al center center,fillx", 
				"[grow]", "[][]20[][]20[][]20[][]40[]"));
		
		final JLabel lblNome = new JLabel(i18n.getString("cadastraUsuarioGUI.nome_usuario"));
		currentFrame.getContentPane().add(lblNome, "span,grow");
		
		final JTextField tfNome = new JTextField();
		currentFrame.getContentPane().add(tfNome, "span,grow,height 25::");
		tfNome.setColumns(10);
		
		final JLabel lblEndereco = new JLabel(i18n.getString("cadastraUsuarioGUI.endereco_usuario"));
		currentFrame.getContentPane().add(lblEndereco, "span,grow");
		
		final JTextField tfEndereco = new JTextField();
		currentFrame.getContentPane().add(tfEndereco, "span,grow,height 25::");
		tfEndereco.setColumns(10);
		
		final JLabel lblEmail = new JLabel(i18n.getString("cadastraUsuarioGUI.email_usuario"));
		currentFrame.getContentPane().add(lblEmail, "span,grow");
		
		final JTextField tfEmail = new JTextField();
		currentFrame.getContentPane().add(tfEmail, "span,grow,height 25::");
		tfEmail.setColumns(10);
		
		final JLabel lblApelidoUsuario = new JLabel(i18n.getString("cadastraUsuarioGUI.apelido_usuario"));
		currentFrame.getContentPane().add(lblApelidoUsuario, "span,grow");
		
		final JTextField tfApelidoUsuario = new JTextField();
		currentFrame.getContentPane().add(tfApelidoUsuario, "span,grow,height 25::");
		tfApelidoUsuario.setColumns(10);
		
		final JButton btnCadastrar = new JButton(i18n.getString("cadastraUsuarioGUI.btn_cadastrar"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = tfNome.getText();
				String endereco = tfEndereco.getText();
				String email = tfEmail.getText();
				String apelido = tfApelidoUsuario.getText();
				
				try {
					mercado.cadastrarUsuario(nome, endereco, email, apelido);
					currentFrame.dispose();
				} catch (CadastroUsuarioException e) {
					parent.mostraMensagemDeAlerta(currentFrame, e.getMessage());
				}
			}
	    });
		currentFrame.getContentPane().add(btnCadastrar, "span,grow,height 30::");
	}
}
