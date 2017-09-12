package visao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import modelo.MercadoLeilao;
import util.I18n;

public abstract class ParentGUI {
	
	protected JFrame currentFrame;
	protected I18n i18n;
	
	public final void mostrarJanela(final PrincipalGUI parent, final MercadoLeilao mercado) {
		i18n = I18n.getInstance();
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
