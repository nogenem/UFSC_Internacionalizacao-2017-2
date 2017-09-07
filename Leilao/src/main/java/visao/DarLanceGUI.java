package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import exceptions.LanceInvalidoException;
import exceptions.ProdutoNaoCadastradoException;
import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;

public class DarLanceGUI extends ParentGUI {
	
	public DarLanceGUI() {}
	
	@Override
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame.setTitle("Dar Lance");
		currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		currentFrame.setSize(450, 339);
		currentFrame.getContentPane().setLayout(new MigLayout("al center center,fillx", 
				"[grow]", "[][]20[][]20[][]40[]"));
		
		final JLabel lblNome = new JLabel("Nome produto:");
		currentFrame.getContentPane().add(lblNome, "span,grow");
		
		final JTextField tfNome = new JTextField();
		currentFrame.getContentPane().add(tfNome, "span,grow,height 25::");
		tfNome.setColumns(10);
		
		final JLabel lblApelidoComprador = new JLabel("Apelido do comprador:");
		currentFrame.getContentPane().add(lblApelidoComprador, "span,grow");
		
		final JTextField tfApelidoComprador = new JTextField();
		currentFrame.getContentPane().add(tfApelidoComprador, "span,grow,height 25::");
		tfApelidoComprador.setColumns(10);
		
		final JLabel lblLance = new JLabel("Valor do lance:");
		currentFrame.getContentPane().add(lblLance, "span,grow");
		
		final JTextField tfLance = new JTextField();
		currentFrame.getContentPane().add(tfLance, "span,grow,height 25::");
		tfLance.setColumns(10);
		
		final JButton btnDarLance = new JButton("Dar lance");
		btnDarLance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomeProduto = tfNome.getText();
				String apelidoComprador = tfApelidoComprador.getText();
				
				try {
					Double valorLance = Double.parseDouble(tfLance.getText());
					mercado.daLance(nomeProduto, apelidoComprador, valorLance);
					currentFrame.dispose();
				} catch (NumberFormatException e) {
					parent.mostraMensagemDeAlerta(currentFrame, "O valor do lance deve ser um numero.");
					System.err.println(e.getMessage());
				} catch (LanceInvalidoException|ProdutoNaoCadastradoException e) {
					parent.mostraMensagemDeAlerta(currentFrame, e.getMessage());
				} 
			}
	    });
		currentFrame.getContentPane().add(btnDarLance, "span,grow,height 30::");
	}
}
