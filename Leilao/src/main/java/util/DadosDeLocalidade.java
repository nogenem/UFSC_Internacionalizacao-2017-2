package util;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.text.NumberFormatter;

public class DadosDeLocalidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Locale locale;
	private NumberFormat numFormat;
	private NumberFormatter numFormatter;
	
	public DadosDeLocalidade(Locale locale) {
		this.locale = locale;
		this.numFormat = NumberFormat.getInstance(locale);
		this.numFormatter = new NumberFormatter(numFormat);
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
}
