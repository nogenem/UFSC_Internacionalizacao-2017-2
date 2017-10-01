package util;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.swing.text.NumberFormatter;

public class DadosDeLocalidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Locale locale;
	private NumberFormat numFormat;
	private NumberFormatter numFormatter;
	transient private DateTimeFormatter dateFormatter;//DateTimeFormatter não é Serializable...
	
	public DadosDeLocalidade(Locale locale) {
		this.locale = locale;
		this.numFormat = NumberFormat.getInstance(locale);
		this.numFormatter = new NumberFormatter(numFormat);
		this.dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(this.locale);
	}
	
	public Locale getLocale() {
		return this.locale;
	}
	
	public NumberFormat getNumberFormat() {
		return this.numFormat;
	}
	
	public NumberFormatter getNumberFormatter() {
		return this.numFormatter;
	}
	
	public DateTimeFormatter getDateFormatter() {
		if(this.dateFormatter == null)//DateTimeFormatter não é Serializable ...
			this.dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(this.locale);
		return this.dateFormatter;
	}
	
	public void setFusoHorarioDoFormatadorDeData(ZoneId fuso) {
		this.dateFormatter = this.getDateFormatter().withZone(fuso);
	}
}
