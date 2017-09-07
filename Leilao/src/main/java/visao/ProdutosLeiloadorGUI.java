package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exceptions.UsuarioNaoCadastradoException;
import interfaces.IUsuario;
import modelo.MercadoLeilao;
import modelo.ProdutoLeilao;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

public class ProdutosLeiloadorGUI extends ParentGUI {
	
	private JLabel lblNome, lblDescricao, lblLanceMin,
		lblUltimoLance, lblApelidoLeiloador, lblDataLimite;
	private JComboBox<Object> comboBox;
	private JList<Object> list;
	
	private DateFormat dateFormat;
	
	public ProdutosLeiloadorGUI(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	@Override
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame.setTitle("Ver Produtos de um Leiloador");
		currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		currentFrame.setSize(600, 339);
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		///////////////////////////////////////////////////
		final JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new MigLayout("", "[grow]", "[]5[]"));
		currentFrame.getContentPane().add(upperPanel, BorderLayout.NORTH);
		
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
		
		///////////////////////////////////////////////////
		comboBox.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuarioSelecionado = (Usuario) comboBox.getSelectedItem();
				List<ProdutoLeilao> produtosDoLeiloador = null;
				try {
					produtosDoLeiloador = mercado.retornaProdutosDeUmLeiloador(usuarioSelecionado.getApelido());
				} catch (UsuarioNaoCadastradoException e) {
					e.printStackTrace();
				}
				list.setListData(produtosDoLeiloador.toArray());
				
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
		lblDescricao.setText("Descrição:  " + desc);
		lblLanceMin.setText("Lance mínimo:  R$" + lanceMin);
		lblUltimoLance.setText("Ultimo lance: R$" + ultimoLance);
		lblApelidoLeiloador.setText("Apelido leiloador:  " + apelidoLeiloador);
		lblDataLimite.setText("Data limite:  " + dataLimite);
	}
}
