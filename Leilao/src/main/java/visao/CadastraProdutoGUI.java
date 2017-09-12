package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import exceptions.CadastroProdutoException;
import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;

public class CadastraProdutoGUI extends ParentGUI {
	
	private DateFormat dateFormat;
	
	public CadastraProdutoGUI(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	@Override
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame.setTitle(i18n.getString("cadastraProdutoGUI.titulo"));
		currentFrame.setSize(450, 423);
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
		
		final JLabel lblLanceMin = new JLabel(i18n.getString("cadastraProdutoGUI.lance_minimo") +" (R$)");
		currentFrame.getContentPane().add(lblLanceMin, "span,grow");
		
		final JTextField tfLanceMin = new JTextField();
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
				Double valorMinimo = 0.0;

				try {
					valorMinimo = Double.parseDouble(tfLanceMin.getText());
					Date dataLimite = dateFormat.parse(data);
					mercado.cadastrarProduto(nome, descricao, valorMinimo, apelidoLeiloador, dataLimite);
					currentFrame.dispose();
				} catch (NumberFormatException e) {
					parent.mostraMensagemDeAlerta(currentFrame, i18n.getString("exceptions.lance_min_numero"));
					System.err.println("CadastraProdutoGUI:> " +e.getMessage());
				} catch (ParseException e) {
					parent.mostraMensagemDeAlerta(currentFrame, i18n.getString("exceptions.data_limite_invalida"));
					System.err.println("CadastraProdutoGUI:> " +e.getMessage());
				} catch (CadastroProdutoException e) {
					parent.mostraMensagemDeAlerta(currentFrame, e.getMessage());
				} 
			}
	    });
		currentFrame.getContentPane().add(btnCadastrar, "span,grow,height 30::");
	}
}
