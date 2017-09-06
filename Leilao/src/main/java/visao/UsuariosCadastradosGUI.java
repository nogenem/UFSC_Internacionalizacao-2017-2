package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import interfaces.IUsuario;
import modelo.MercadoLeilao;

public class UsuariosCadastradosGUI {
	
	private JLabel lblNome, lblApelido, lblEndereco,
		lblEmail;
	private JList<Object> list;
	
	public UsuariosCadastradosGUI() {}
	
	public void mostrarJanela(final JFrame parent, final MercadoLeilao mercado) {
		final JFrame frame = new JFrame();
		frame.setTitle("Usuários Cadastrados");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 600, 339);
		frame.setLocationRelativeTo(parent);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		final JScrollPane spLeft = new JScrollPane();
		final Dimension max = spLeft.getMaximumSize();
		spLeft.setPreferredSize(new Dimension(210, (int)max.getHeight()));
		frame.getContentPane().add(spLeft, BorderLayout.WEST);
		
		final List<IUsuario> usuariosCadastrados = mercado.getUsuariosCadastrados();
		list = new JList<>(usuariosCadastrados.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		spLeft.setViewportView(list);
		
		final JScrollPane spCenter = new JScrollPane();
		frame.getContentPane().add(spCenter, BorderLayout.CENTER);
		
		final JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		centerPanel.setLayout(new GridLayout(4, 1, 0, 0));
		spCenter.setViewportView(centerPanel);
		
		lblNome = new JLabel();
		centerPanel.add(lblNome);
		lblApelido = new JLabel();
		centerPanel.add(lblApelido);
		lblEndereco = new JLabel();
		centerPanel.add(lblEndereco);
		lblEmail = new JLabel();
		centerPanel.add(lblEmail);
		
		this.atualizaLabels();
		
		frame.setVisible(true);
	}
	
	private void atualizaLabels() {
		String nome = "", apelido = "", endereco = "", email = "";
		final IUsuario usuarioSelecionado = (IUsuario) list.getSelectedValue();
		if(usuarioSelecionado != null) {
			nome = usuarioSelecionado.getNome();
			apelido = usuarioSelecionado.getApelido();
			endereco = usuarioSelecionado.getEndereco();
			email = usuarioSelecionado.getEmail();
		}
		
		lblNome.setText("Nome:  " + nome);
        lblApelido.setText("Apelido:  " + apelido);
        lblEndereco.setText("Endereço:  " + endereco);
        lblEmail.setText("E-mail:  " + email);
	}
}
