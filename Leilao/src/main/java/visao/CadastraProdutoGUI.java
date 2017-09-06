package visao;

import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;

public class CadastraProdutoGUI {
	
	@SuppressWarnings("unused")
	private DateFormat dateFormat;//XXX vai ser usado em algum ponto
	
	public CadastraProdutoGUI(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public void mostrarJanela(final JFrame parent, final MercadoLeilao mercado) {
		final JFrame frame = new JFrame();
		frame.setTitle("Cadastrar Produto");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 423);
		frame.setLocationRelativeTo(parent);
		frame.getContentPane().setLayout(new MigLayout("al center center,fillx", 
				"[grow]", "[][]20[][]20[][]20[][]20[][]30[]"));
		
		final JLabel lblNome = new JLabel("Nome Produto:");
		frame.getContentPane().add(lblNome, "span,grow");
		
		final JTextField tfNome = new JTextField();
		frame.getContentPane().add(tfNome, "span,grow,height 25::");
		tfNome.setColumns(10);
		
		final JLabel lblDescricao = new JLabel("Descrição do Produto:");
		frame.getContentPane().add(lblDescricao, "span,grow");
		
		final JTextField tfDescricao = new JTextField();
		frame.getContentPane().add(tfDescricao, "span,grow,height 25::");
		tfDescricao.setColumns(10);
		
		final JLabel lblLanceMin = new JLabel("Lance Mínimo:");
		frame.getContentPane().add(lblLanceMin, "span,grow");
		
		final JTextField tfLanceMin = new JTextField();
		frame.getContentPane().add(tfLanceMin, "span,grow,height 25::");
		tfLanceMin.setColumns(10);
		
		final JLabel lblApelidoLeiloador = new JLabel("Apelido do Leiloador:");
		frame.getContentPane().add(lblApelidoLeiloador, "span,grow");
		
		final JTextField tfApelidoLeiloador = new JTextField();
		frame.getContentPane().add(tfApelidoLeiloador, "span,grow,height 25::");
		tfApelidoLeiloador.setColumns(10);
		
		final JLabel lblDataLimite = new JLabel("Data Limite:");
		frame.getContentPane().add(lblDataLimite, "span,grow");
		
		final JTextField tfDataLimite = new JTextField();
		frame.getContentPane().add(tfDataLimite, "span,grow,height 25::");
		tfDataLimite.setColumns(10);
		
		final JButton btnCadastrar = new JButton("Cadastrar");
		frame.getContentPane().add(btnCadastrar, "span,grow,height 30::");
		
		frame.setVisible(true);
	}
}
