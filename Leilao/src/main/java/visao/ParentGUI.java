package visao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import modelo.MercadoLeilao;

public abstract class ParentGUI {
	
	protected JFrame currentFrame;
	
	public final void mostrarJanela(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame = new JFrame();
		currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		currentFrame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent we) {
                parent.setVisible(true);
            }
        } );
		
		constroiFrame(parent, mercado);
		
		currentFrame.setLocationRelativeTo(parent);
		parent.setVisible(false);
		currentFrame.setVisible(true);
	}
	
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		//não faz nada, deve ser sobrescrito pelas classes filhas!
	}
}
