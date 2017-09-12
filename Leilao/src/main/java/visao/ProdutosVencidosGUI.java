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

public class ProdutosVencidosGUI extends ParentGUI {
	
	private JLabel lblNome, lblDescricao, lblLanceMin, 
		lblApelidoLeiloador, lblDataLimite;
	private JList<Object> list;
	
	private DateFormat dateFormat;
	
	public ProdutosVencidosGUI(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	@Override
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame.setTitle(i18n.getString("produtosVencidosGUI.titulo"));
		currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		currentFrame.setSize(600, 339);
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		///////////////////////////////////////////////////
		final JScrollPane spLeft = new JScrollPane();
		final Dimension max = spLeft.getMaximumSize();
		spLeft.setPreferredSize(new Dimension(210, (int)max.getHeight()));
		currentFrame.getContentPane().add(spLeft, BorderLayout.WEST);
		
		@SuppressWarnings("unchecked")
		final List<ProdutoLeilao> produtosVencidosENaoVendidos = 
			(List<ProdutoLeilao>) mercado.getProdutosVencidosENaoVendidos();
		list = new JList<>(produtosVencidosENaoVendidos.toArray());
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
		centerPanel.setLayout(new GridLayout(5, 1, 0, 0));
		spCenter.setViewportView(centerPanel);
		
		lblNome = new JLabel();
		centerPanel.add(lblNome);
		lblDescricao = new JLabel();
		centerPanel.add(lblDescricao);
		lblLanceMin = new JLabel();
		centerPanel.add(lblLanceMin);
		lblApelidoLeiloador = new JLabel();
		centerPanel.add(lblApelidoLeiloador);
		lblDataLimite = new JLabel();
		centerPanel.add(lblDataLimite);
		
		///////////////////////////////////////////////////
		if(list.getModel().getSize() > 0)
			list.setSelectedIndex(0);
		else
			atualizaLabels();
	}
	
	private void atualizaLabels() {
		String nome = "", desc = "", lanceMin = "", apelidoLeiloador = "", dataLimite = "";
		final ProdutoLeilao produtoSelecionado = (ProdutoLeilao)list.getSelectedValue();
		if(produtoSelecionado != null) {
			nome = produtoSelecionado.getNome();
			desc = produtoSelecionado.getDescricao();
			lanceMin = produtoSelecionado.getLanceMinimo() + "";
			apelidoLeiloador = produtoSelecionado.getApelidoLeiloador();
			dataLimite = dateFormat.format(produtoSelecionado.getDataLimite());
		}
		
		lblNome.setText(i18n.getString("produtosVencidosGUI.nome") +" "+ nome);
		lblDescricao.setText(i18n.getString("produtosVencidosGUI.descricao") +" "+ desc);
		lblLanceMin.setText(i18n.getString("produtosVencidosGUI.lance_minimo") +" R$"+ lanceMin);
		lblApelidoLeiloador.setText(i18n.getString("produtosVencidosGUI.apelido_leiloador") +" "+ apelidoLeiloador);
		lblDataLimite.setText(i18n.getString("produtosVencidosGUI.data_limite") +" "+ dataLimite);
	}
}
