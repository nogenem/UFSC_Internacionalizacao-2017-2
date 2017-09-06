package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import modelo.MercadoLeilao;
import modelo.ProdutoLeilao;

public class ProdutosEmLeilaoGUI {
	
	private JLabel lblNome, lblDescricao, lblLanceMin,
		lblUltimoLance, lblApelidoLeiloador, lblDataLimite;
	private JList<Object> list;
	
	private DateFormat dateFormat;
	
	public ProdutosEmLeilaoGUI(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public void mostrarJanela(final JFrame parent, final MercadoLeilao mercado) {
		final JFrame frame = new JFrame();
		frame.setTitle("Produtos em Leilão");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 600, 339);
		frame.setLocationRelativeTo(parent);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		final JScrollPane spLeft = new JScrollPane();
		final Dimension max = spLeft.getMaximumSize();
		spLeft.setPreferredSize(new Dimension(210, (int)max.getHeight()));
		frame.getContentPane().add(spLeft, BorderLayout.WEST);
		
		@SuppressWarnings("unchecked")
		final List<ProdutoLeilao> produtosEmLeilao = (List<ProdutoLeilao>) mercado.getProdutosEmLeilao();
		list = new JList<>(produtosEmLeilao.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		spLeft.setViewportView(list);
		
		final JScrollPane spCenter = new JScrollPane();
		frame.getContentPane().add(spCenter, BorderLayout.CENTER);
		
		final JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		centerPanel.setLayout(new GridLayout(6, 1, 0, 0));
		spCenter.setViewportView(centerPanel);
		
		lblNome = new JLabel();
		centerPanel.add(lblNome);
		lblDescricao = new JLabel();
		centerPanel.add(lblDescricao);
		lblLanceMin = new JLabel();
		centerPanel.add(lblLanceMin);
		lblUltimoLance = new JLabel();
		centerPanel.add(lblUltimoLance);
		lblApelidoLeiloador = new JLabel();
		centerPanel.add(lblApelidoLeiloador);
		lblDataLimite = new JLabel();
		centerPanel.add(lblDataLimite);
		
		this.atualizaLabels();
		
		frame.setVisible(true);
	}
	
	private void atualizaLabels() {
		String nome = "", desc = "", lanceMin = "", ultimoLance = "", apelidoLeiloador = "", dataLimite = "";
		final ProdutoLeilao produtoSelecionado = (ProdutoLeilao)list.getSelectedValue();
		if(produtoSelecionado != null) {
			nome = produtoSelecionado.getNome();
			desc = produtoSelecionado.getDescricao();
			lanceMin = produtoSelecionado.getLanceMinimo() + "";
			ultimoLance = produtoSelecionado.getValorUltimoLance() + "";
			apelidoLeiloador = produtoSelecionado.getApelidoLeiloador();
			dataLimite = dateFormat.format(produtoSelecionado.getDataLimite());
		}
		
		lblNome.setText("Nome:  " + nome);
		lblDescricao.setText("Descrição:  " + desc);
		lblLanceMin.setText("Lance mínimo:  R$" + lanceMin);
		lblUltimoLance.setText("Ultimo lance: R$" + ultimoLance);
		lblApelidoLeiloador.setText("Apelido Leiloador:  " + apelidoLeiloador);
		lblDataLimite.setText("Data limite:  " + dataLimite);
	}
}
