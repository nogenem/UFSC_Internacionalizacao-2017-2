package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import interfaces.IUsuario;
import modelo.Lance;
import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;

public class LancesUsuarioGUI {
	
	private JLabel lblNomeUsuario, lblNomeProduto, lblValorLance;
	private JComboBox<Object> comboBox;
	private JList<Object> list;
	
	public LancesUsuarioGUI() {}
	
	public void mostrarJanela(final JFrame parent, final MercadoLeilao mercado) {
		final JFrame frame = new JFrame();
		frame.setTitle("Ver Lances de um Usuário");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 600, 339);
		frame.setLocationRelativeTo(parent);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		///////////////////////////////////////////////////
		final JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new MigLayout("", "[grow]", "[]5[]"));
		frame.getContentPane().add(upperPanel, BorderLayout.NORTH);
		
		final JLabel label = new JLabel("Selecione um usuário:");
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
		frame.getContentPane().add(spLeft, BorderLayout.WEST);
		
		list = new JList<>();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spLeft.setViewportView(list);
		
		///////////////////////////////////////////////////
		final JScrollPane spCenter = new JScrollPane();
		frame.getContentPane().add(spCenter, BorderLayout.CENTER);
		
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
		
		//list.setSelectedIndex(0);
		this.atualizaLabels();
		
		frame.setVisible(true);
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
		
		lblNomeUsuario.setText("Nome do usuário:  " + nomeUsuario);
		lblNomeProduto.setText("Nome do produto:  " + nomeProduto);
		lblValorLance.setText("Valor do lance:  R$" + valorLance);
	}
}
