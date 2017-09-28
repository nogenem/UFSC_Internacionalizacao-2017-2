package visao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import modelo.Configuracao;
import modelo.MercadoLeilao;
import util.DadosDeLocalidade;
import util.I18n;

public abstract class ParentGUI {
	
	protected static MaskFormatter editDateMask;
	protected static DateFormat editDateFormat;

	protected JFrame currentFrame;
	protected I18n i18n;
	
	protected DadosDeLocalidade locData;
	
	protected ParentGUI() {
		this.locData = Configuracao.getInstance().getDadosDeLocalidadeAtuais();
	}
	
	public final void mostrarJanela(final PrincipalGUI parent, final MercadoLeilao mercado) {
		i18n = I18n.getInstance();
		this.locData.atualizaFusoHorarioDoFormatadorDeData();
		
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
	
	protected String constroiLabelDeDataComExemplo(String txtBase) {
		txtBase  = "<html>"+txtBase;
		txtBase += "<br>Ex: " + editDateFormat.format(new Date());
		txtBase += "</html>";
		return txtBase;
	}
	
	protected JFormattedTextField constroiCampoFormatadoParaData() {
		return new JFormattedTextField(editDateMask);
	}
	
	static {
		editDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		editDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			editDateMask = new MaskFormatter("####/##/## ##:##");
			editDateMask.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
