package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
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
import modelo.MercadoLeilao;
import modelo.ProdutoLeilao;
import net.miginfocom.swing.MigLayout;

public class ProdutosLeiloadorGUI {
	
	private JLabel lblNome, lblDescricao, lblLanceMin,
		lblUltimoLance, lblApelidoLeiloador, lblDataLimite;
	private JComboBox<Object> comboBox;
	private JList<Object> list;
	
	private DateFormat dateFormat;
	
	public ProdutosLeiloadorGUI(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public void mostrarJanela(final JFrame parent, final MercadoLeilao mercado) {
		final JFrame frame = new JFrame();
		frame.setTitle("Ver Produtos de um Leiloador");
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
		centerPanel.setLayout(new GridLayout(6, 1, 0, 0));
		spCenter.setViewportView(centerPanel);
		
		lblNome = new JLabel("Nome:  ");
		centerPanel.add(lblNome);
		lblDescricao = new JLabel("Descrição:  ");
		centerPanel.add(lblDescricao);
		lblLanceMin = new JLabel("Lance mínimo: R$ ");
		centerPanel.add(lblLanceMin);
		lblUltimoLance = new JLabel("Ultimo lance: R$ ");
		centerPanel.add(lblUltimoLance);
		lblApelidoLeiloador = new JLabel("Apelido Leiloador:  ");
		centerPanel.add(lblApelidoLeiloador);
		lblDataLimite = new JLabel("Data limite:  ");
		centerPanel.add(lblDataLimite);
		
		//list.setSelectedIndex(0);
		this.atualizaLabels();
		
		frame.setVisible(true);
	}
	
	private void atualizaLabels() {
		String nome = "", desc = "", lanceMin = "", ultimoLance = "", apelidoLeiloador = "", dataLimite = "";
		if(list != null) {
			final ProdutoLeilao produtoSelecionado = (ProdutoLeilao)list.getSelectedValue();
			if(produtoSelecionado != null) {
				nome = produtoSelecionado.getNome();
				desc = produtoSelecionado.getDescricao();
				lanceMin = produtoSelecionado.getLanceMinimo() + "";
				ultimoLance = produtoSelecionado.getValorUltimoLance() + "";
				apelidoLeiloador = produtoSelecionado.getApelidoLeiloador();
				dataLimite = dateFormat.format(produtoSelecionado.getDataLimite());
			}
		}
		
		lblNome.setText("Nome:  " + nome);
		lblDescricao.setText("Descricao:  " + desc);
		lblLanceMin.setText("Lance minimo:  R$" + lanceMin);
		lblUltimoLance.setText("Ultimo lance: R$" + ultimoLance);
		lblApelidoLeiloador.setText("Apelido Leiloador:  " + apelidoLeiloador);
		lblDataLimite.setText("Data limite:  " + dataLimite);
	}
}
