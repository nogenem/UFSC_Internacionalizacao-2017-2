package visao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;

public class DarLanceGUI {
	
	public DarLanceGUI() {}
	
	public void mostrarJanela(final JFrame parent, final MercadoLeilao mercado) {
		final JFrame frame = new JFrame();
		frame.setTitle("Dar Lance");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 339);
		frame.setLocationRelativeTo(parent);
		frame.getContentPane().setLayout(new MigLayout("al center center,fillx", 
				"[grow]", "[][]20[][]20[][]40[]"));
		
		final JLabel lblNome = new JLabel("Nome Produto:");
		frame.getContentPane().add(lblNome, "span,grow");
		
		final JTextField tfNome = new JTextField();
		frame.getContentPane().add(tfNome, "span,grow,height 25::");
		tfNome.setColumns(10);
		
		final JLabel lblApelidoComprador = new JLabel("Apelido do Comprador:");
		frame.getContentPane().add(lblApelidoComprador, "span,grow");
		
		final JTextField tfApelidoComprador = new JTextField();
		frame.getContentPane().add(tfApelidoComprador, "span,grow,height 25::");
		tfApelidoComprador.setColumns(10);
		
		final JLabel lblLance = new JLabel("Valor do Lance:");
		frame.getContentPane().add(lblLance, "span,grow");
		
		final JTextField tfLance = new JTextField();
		frame.getContentPane().add(tfLance, "span,grow,height 25::");
		tfLance.setColumns(10);
		
		final JButton btnDarLance = new JButton("Dar Lance");
		frame.getContentPane().add(btnDarLance, "span,grow,height 30::");
		
		frame.setVisible(true);
	}
}
