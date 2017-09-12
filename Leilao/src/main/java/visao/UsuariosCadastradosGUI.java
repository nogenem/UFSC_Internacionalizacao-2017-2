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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import interfaces.IUsuario;
import modelo.MercadoLeilao;

public class UsuariosCadastradosGUI extends ParentGUI {
	
	private JLabel lblNome, lblApelido, lblEndereco,
		lblEmail;
	private JList<Object> list;
	
	public UsuariosCadastradosGUI() {}
	
	@Override
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame.setTitle(i18n.getString("usuariosCadastradosGUI.titulo"));
		currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		currentFrame.setSize(600, 339);
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		///////////////////////////////////////////////////
		final JScrollPane spLeft = new JScrollPane();
		final Dimension max = spLeft.getMaximumSize();
		spLeft.setPreferredSize(new Dimension(210, (int)max.getHeight()));
		currentFrame.getContentPane().add(spLeft, BorderLayout.WEST);
		
		final List<IUsuario> usuariosCadastrados = mercado.getUsuariosCadastrados();
		list = new JList<>(usuariosCadastrados.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				atualizaLabels();
            }
        });
		spLeft.setViewportView(list);
		
		///////////////////////////////////////////////////
		final JScrollPane spCenter = new JScrollPane();
		currentFrame.getContentPane().add(spCenter, BorderLayout.CENTER);
		
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
		
		///////////////////////////////////////////////////
		if(list.getModel().getSize() > 0)
			list.setSelectedIndex(0);
		else
			atualizaLabels();
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
		
		lblNome.setText(i18n.getString("usuariosCadastradosGUI.nome") +" "+ nome);
        lblApelido.setText(i18n.getString("usuariosCadastradosGUI.apelido") +" "+ apelido);
        lblEndereco.setText(i18n.getString("usuariosCadastradosGUI.endereco") +" "+ endereco);
        lblEmail.setText(i18n.getString("usuariosCadastradosGUI.email") +" "+ email);
	}
}
