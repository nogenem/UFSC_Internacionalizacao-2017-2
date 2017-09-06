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

public class ProdutosVendidosGUI {
	
	private JLabel labelNome, labelDescricao, labelLanceMin,
		labelPrecoVendido, labelApelidoLeiloador, labelApelidoComprador, labelDataLimite;
	private JList<Object> list;
	
	private DateFormat dateFormat;
	
	public ProdutosVendidosGUI(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public void mostrarJanela(final JFrame parent, final MercadoLeilao mercado) {
		final JFrame frame = new JFrame();
		frame.setTitle("Produtos Vendidos");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 600, 339);
		frame.setLocationRelativeTo(parent);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		final JScrollPane spLeft = new JScrollPane();
		Dimension max = spLeft.getMaximumSize();
		spLeft.setPreferredSize(new Dimension(210, (int)max.getHeight()));
		frame.getContentPane().add(spLeft, BorderLayout.WEST);
		
		@SuppressWarnings("unchecked")
		final List<ProdutoLeilao> produtosVendidos = (List<ProdutoLeilao>) mercado.getProdutosVendidos();
		list = new JList<>(produtosVendidos.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		spLeft.setViewportView(list);
		
		final JScrollPane spCenter = new JScrollPane();
		frame.getContentPane().add(spCenter, BorderLayout.CENTER);
		
		final JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		centerPanel.setLayout(new GridLayout(7, 1, 0, 0));
		spCenter.setViewportView(centerPanel);
		
		labelNome = new JLabel();
		centerPanel.add(labelNome);
		labelDescricao = new JLabel();
		centerPanel.add(labelDescricao);
		labelLanceMin = new JLabel();
		centerPanel.add(labelLanceMin);
		labelPrecoVendido = new JLabel();
		centerPanel.add(labelPrecoVendido);
		labelApelidoLeiloador = new JLabel();
		centerPanel.add(labelApelidoLeiloador);
		labelApelidoComprador = new JLabel();
		centerPanel.add(labelApelidoComprador);
		labelDataLimite = new JLabel();
		centerPanel.add(labelDataLimite);
		
		this.atualizaLabels();
		
		frame.setVisible(true);
	}
	
	private void atualizaLabels() {
		String nome = "", desc = "", lanceMin = "", precoVendido = "", 
				apelidoLeiloador = "", apelidoComprador = "", dataLimite = "";
		final ProdutoLeilao produtoSelecionado = (ProdutoLeilao)list.getSelectedValue();
		if(produtoSelecionado != null) {
			nome = produtoSelecionado.getNome();
			desc = produtoSelecionado.getDescricao();
			lanceMin = produtoSelecionado.getLanceMinimo() + "";
			precoVendido = produtoSelecionado.getValorUltimoLance() +"";
			apelidoLeiloador = produtoSelecionado.getApelidoLeiloador();
			apelidoComprador = produtoSelecionado.getApelidoComprador();
			dataLimite = dateFormat.format(produtoSelecionado.getDataLimite());
		}
		
		labelNome.setText("Nome:  " + nome);
        labelDescricao.setText("Descrição:  " + desc);
        labelLanceMin.setText("Lance mínimo:  R$" + lanceMin);
        labelPrecoVendido.setText("Preço vendido: R$" + precoVendido);
        labelApelidoLeiloador.setText("Apelido Leiloador:  " + apelidoLeiloador);
        labelApelidoComprador.setText("Apelido Comprador:  " + apelidoComprador);
        labelDataLimite.setText("Data limite:  " + dataLimite);
	}
}
