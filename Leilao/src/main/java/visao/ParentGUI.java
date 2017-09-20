package visao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import modelo.MercadoLeilao;
import util.DadosDeLocalidade;
import util.I18n;

public abstract class ParentGUI {
	
	protected JFrame currentFrame;
	protected I18n i18n;
	
	protected DadosDeLocalidade locData;
	protected DateFormat dateFormat;
	
	protected ParentGUI() {}
	
	protected ParentGUI(DadosDeLocalidade locData) {
		this(locData, null);
	}
	
	protected ParentGUI(DadosDeLocalidade locData, DateFormat dateFormat) {
		this.locData = locData;
		this.dateFormat = dateFormat;
	}
	
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
	
	protected String constroiLabelDeNumeroComExemplo(String txtBase) {
		txtBase  = "<html>"+txtBase;
		txtBase += "<br>Ex: " + this.locData.getNumberFormat().format(11000.55);
		txtBase += "</html>";
		return txtBase;
	}
	
	protected JFormattedTextField constroiCampoFormatadoParaNumeros() {
		JFormattedTextField field = new JFormattedTextField();
		NumberFormatter numFormatter = this.locData.getNumberFormatter();
		field.setFormatterFactory(new DefaultFormatterFactory(numFormatter,numFormatter,numFormatter));
		return field;
	}
}
