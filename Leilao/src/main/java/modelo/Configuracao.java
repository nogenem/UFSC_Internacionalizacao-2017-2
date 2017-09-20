package modelo;

import java.util.Locale;
import java.util.TimeZone;

import interfaces.IConfiguracao;
import util.DadosDeLocalidade;
import util.I18n;

public class Configuracao implements IConfiguracao {
	
	private DadosDeLocalidade currentLocaleData;
	//https://www.mkyong.com/java/java-display-list-of-timezone-with-gmt/
	private TimeZone currentTimeZone;
	
	public Configuracao() {
		Locale _default = Locale.getDefault();
		I18n i18n = I18n.getInstance();
		
		if(!i18n.possuiLocale(_default.toString())) {
			System.out.println("> Default sem traducao, voltando para pt_BR.");
			_default = new Locale("pt", "BR");
		}
		i18n.carregaBundle(_default);
		
		this.currentLocaleData = i18n.getDadosDeLocalidade(_default.toString());
		this.currentTimeZone = TimeZone.getDefault();
	}
	
	public Locale getCurrentLocale() {
		return this.currentLocaleData.getLocale();
	}
	
	public void setCurrentLocale(String newLocaleID) {
		this.currentLocaleData = I18n.getInstance().getDadosDeLocalidade(newLocaleID);
	}
	
	public TimeZone getCurrentTimeZone() {
		return this.currentTimeZone;
	}
	
	public void setCurrentTimeZone(String newTimeZoneID) {
		this.currentTimeZone = TimeZone.getTimeZone(newTimeZoneID);
	}
}
