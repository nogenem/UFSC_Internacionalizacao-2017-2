package visao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
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

	protected JFrame currentFrame;
	protected I18n i18n;
	protected DateFormat editDateFormat;
	
	protected DadosDeLocalidade locData;
	
	protected ParentGUI() {
		this.locData = Configuracao.getInstance().getDadosDeLocalidadeAtuais();
		this.editDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	}
	
	public final void mostrarJanela(final PrincipalGUI parent, final MercadoLeilao mercado) {
		i18n = I18n.getInstance();
		
		// Atualiza fuso horario dos formatters
		ZoneId fuso = Configuracao.getInstance().getFusoHorarioAtual();
		this.locData.setFusoHorarioDoFormatadorDeData(fuso);
		this.editDateFormat.setTimeZone(TimeZone.getTimeZone(fuso));
		
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
		String ex = I18n.getInstance().getString("abrev_exemplo");
		txtBase  = "<html>"+txtBase;
		txtBase += "<br>" +ex+ " "+ this.locData.getNumberFormat().format(11000.55);
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
		String ex = I18n.getInstance().getString("abrev_exemplo");
		txtBase  = "<html>"+txtBase;
		Date date = new GregorianCalendar(2017, 10, 30, 14, 35).getTime();
		txtBase += "<br>" +ex+ " "+ editDateFormat.format(date);
		txtBase += "</html>";
		return txtBase;
	}
	
	protected JFormattedTextField constroiCampoFormatadoParaData() {
		return new JFormattedTextField(editDateMask);
	}
	
	static {
		try {
			editDateMask = new MaskFormatter("####/##/## ##:##");
			editDateMask.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
