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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.MercadoLeilao;
import modelo.ProdutoLeilao;

public class ProdutosVendidosGUI extends ParentGUI {
	
	private JLabel labelNome, labelDescricao, labelLanceMin,
		labelPrecoVendido, labelApelidoLeiloador, labelApelidoComprador, labelDataLimite;
	private JList<Object> list;
	
	private DateFormat dateFormat;
	
	public ProdutosVendidosGUI(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	@Override
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame.setTitle("Produtos Vendidos");
		currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		currentFrame.setSize(600, 339);
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		///////////////////////////////////////////////////
		final JScrollPane spLeft = new JScrollPane();
		Dimension max = spLeft.getMaximumSize();
		spLeft.setPreferredSize(new Dimension(210, (int)max.getHeight()));
		currentFrame.getContentPane().add(spLeft, BorderLayout.WEST);
		
		@SuppressWarnings("unchecked")
		final List<ProdutoLeilao> produtosVendidos = (List<ProdutoLeilao>) mercado.getProdutosVendidos();
		list = new JList<>(produtosVendidos.toArray());
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
		
		///////////////////////////////////////////////////
		if(list.getModel().getSize() > 0)
			list.setSelectedIndex(0);
		else
			atualizaLabels();
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
        labelApelidoLeiloador.setText("Apelido leiloador:  " + apelidoLeiloador);
        labelApelidoComprador.setText("Apelido comprador:  " + apelidoComprador);
        labelDataLimite.setText("Data limite:  " + dataLimite);
	}
}
