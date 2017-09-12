package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exceptions.UsuarioNaoCadastradoException;
import interfaces.IUsuario;
import modelo.Lance;
import modelo.MercadoLeilao;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

public class LancesUsuarioGUI extends ParentGUI {
	
	private JLabel lblNomeUsuario, lblNomeProduto, lblValorLance;
	private JComboBox<Object> comboBox;
	private JList<Object> list;
	
	public LancesUsuarioGUI() {}
	
	@Override
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame.setTitle(i18n.getString("lancesUsuarioGUI.titulo"));
		currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		currentFrame.setSize(600, 339);
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		///////////////////////////////////////////////////
		final JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new MigLayout("", "[grow]", "[]5[]"));
		currentFrame.getContentPane().add(upperPanel, BorderLayout.NORTH);
		
		final JLabel label = new JLabel(i18n.getString("lancesUsuarioGUI.selecione_usuario"));
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		upperPanel.add(label, "cell 0 0");
		
		final List<IUsuario> usuariosCadastrados = mercado.getUsuariosCadastrados();
		comboBox = new JComboBox<>(usuariosCadastrados.toArray());
		//comboBox.setLocale(new Locale("pt", "BR"));
		comboBox.setMinimumSize(new Dimension(210, 25));
		upperPanel.add(comboBox, "cell 0 1");
		
		///////////////////////////////////////////////////
		final JScrollPane spLeft = new JScrollPane();
		final Dimension max = spLeft.getMaximumSize();
		spLeft.setPreferredSize(new Dimension(210, (int)max.getHeight()));
		currentFrame.getContentPane().add(spLeft, BorderLayout.WEST);
		
		list = new JList<>();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				atualizaLabels();
            }
        });
		spLeft.setViewportView(list);
		
		///////////////////////////////////////////////////
		final JScrollPane spCenter = new JScrollPane();
		currentFrame.getContentPane().add(spCenter, BorderLayout.CENTER);
		
		final JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		centerPanel.setLayout(new GridLayout(3, 1, 0, 0));
		spCenter.setViewportView(centerPanel);
		
		lblNomeUsuario = new JLabel();
		centerPanel.add(lblNomeUsuario);
		lblNomeProduto = new JLabel();
		centerPanel.add(lblNomeProduto);
		lblValorLance = new JLabel();
		centerPanel.add(lblValorLance);
		
		///////////////////////////////////////////////////
		comboBox.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuarioSelecionado = (Usuario) comboBox.getSelectedItem();
				List<Lance> lancesDoUsuario = null;
				try {
					lancesDoUsuario = mercado.retornaLancesDeUmUsuario(usuarioSelecionado.getApelido());
				} catch (UsuarioNaoCadastradoException e) {
					e.printStackTrace();
				}
				list.setListData(lancesDoUsuario.toArray());
				
				if(list.getModel().getSize() > 0)
					list.setSelectedIndex(0);
				else
					atualizaLabels();
			}
		});
		if(comboBox.getItemCount() > 0)
			comboBox.setSelectedIndex(0);
		else
			atualizaLabels();
	}
	
	private void atualizaLabels() {
		String nomeUsuario = "", nomeProduto = "", valorLance = "";
		if(list != null) {
			final Lance lanceSelecionado = (Lance)list.getSelectedValue();
			if(lanceSelecionado != null) {
				nomeUsuario = lanceSelecionado.getNomeDonoDoLance();
				nomeProduto = lanceSelecionado.getNomeProdutoQueRecebeuOLance();
				valorLance = lanceSelecionado.getValorDoLance() + "";
			}
		}
		
		lblNomeUsuario.setText(i18n.getString("lancesUsuarioGUI.nome_usuario") + " "+ nomeUsuario);
		lblNomeProduto.setText(i18n.getString("lancesUsuarioGUI.nome_produto") +" "+ nomeProduto);
		lblValorLance.setText(i18n.getString("lancesUsuarioGUI.valor_lance") +" R$"+ valorLance);
	}
}
