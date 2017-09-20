package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import exceptions.CadastroProdutoException;
import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;
import util.DadosDeLocalidade;

public class CadastraProdutoGUI extends ParentGUI {
	
	public CadastraProdutoGUI(DadosDeLocalidade locData, DateFormat dateFormat) {
		super(locData, dateFormat);
	}
	
	@Override
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame.setTitle(i18n.getString("cadastraProdutoGUI.titulo"));
		currentFrame.setSize(450, 435);
		currentFrame.getContentPane().setLayout(new MigLayout("al center center,fillx", 
				"[grow]", "[][]20[][]20[][]20[][]20[][]30[]"));
		
		final JLabel lblNome = new JLabel(i18n.getString("cadastraProdutoGUI.nome_produto"));
		currentFrame.getContentPane().add(lblNome, "span,grow");
		
		final JTextField tfNome = new JTextField();
		currentFrame.getContentPane().add(tfNome, "span,grow,height 25::");
		tfNome.setColumns(10);
		
		final JLabel lblDescricao = new JLabel(i18n.getString("cadastraProdutoGUI.descricao_produto"));
		currentFrame.getContentPane().add(lblDescricao, "span,grow");
		
		final JTextField tfDescricao = new JTextField();
		currentFrame.getContentPane().add(tfDescricao, "span,grow,height 25::");
		tfDescricao.setColumns(10);
		
		String lanceMinTxt = i18n.getString("cadastraProdutoGUI.lance_minimo") +" (R$)";
		lanceMinTxt = this.constroiLabelDeNumeroComExemplo(lanceMinTxt);
		
		final JLabel lblLanceMin = new JLabel(lanceMinTxt);
		currentFrame.getContentPane().add(lblLanceMin, "span,grow");
		
		final JFormattedTextField tfLanceMin = this.constroiCampoFormatadoParaNumeros();
		currentFrame.getContentPane().add(tfLanceMin, "span,grow,height 25::");
		tfLanceMin.setColumns(10);
		
		final JLabel lblApelidoLeiloador = new JLabel(i18n.getString("cadastraProdutoGUI.apelido_leiloador"));
		currentFrame.getContentPane().add(lblApelidoLeiloador, "span,grow");
		
		final JTextField tfApelidoLeiloador = new JTextField();
		currentFrame.getContentPane().add(tfApelidoLeiloador, "span,grow,height 25::");
		tfApelidoLeiloador.setColumns(10);
		
		final JLabel lblDataLimite = new JLabel(i18n.getString("cadastraProdutoGUI.data_limite"));
		currentFrame.getContentPane().add(lblDataLimite, "span,grow");
		
		final JTextField tfDataLimite = new JTextField();
		currentFrame.getContentPane().add(tfDataLimite, "span,grow,height 25::");
		tfDataLimite.setColumns(10);
		
		final JButton btnCadastrar = new JButton(i18n.getString("cadastraProdutoGUI.btn_cadastrar"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = tfNome.getText();
				String descricao = tfDescricao.getText();
				String apelidoLeiloador = tfApelidoLeiloador.getText();
				String data = tfDataLimite.getText();

				try {
					Double valorMinimo = locData.getNumberFormat()
							.parse(tfLanceMin.getText()).doubleValue();
					Date dataLimite = dateFormat.parse(data);
					mercado.cadastrarProduto(nome, descricao, valorMinimo, apelidoLeiloador, dataLimite);
					currentFrame.dispose();
				} catch (ParseException e) {
					String msg = e.getMessage();
					if(msg.startsWith("Unparseable number")) {
						parent.mostraMensagemDeAlerta(currentFrame, i18n.getString("exceptions.lance_min_numero"));
					}else if(msg.startsWith("Unparseable date")) {
						parent.mostraMensagemDeAlerta(currentFrame, i18n.getString("exceptions.data_limite_invalida"));
					}else {
						e.printStackTrace();
					}
				} catch (CadastroProdutoException e) {
					parent.mostraMensagemDeAlerta(currentFrame, e.getMessage());
				} 
			}
	    });
		currentFrame.getContentPane().add(btnCadastrar, "span,grow,height 30::");
	}
}
