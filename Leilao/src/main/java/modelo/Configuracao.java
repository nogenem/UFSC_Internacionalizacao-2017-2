package modelo;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import interfaces.IConfiguracao;
import util.DadosDeLocalidade;
import util.I18n;

public class Configuracao implements IConfiguracao, Serializable {
	
	private static final long serialVersionUID = 1L;
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
	
	public Locale getLocalidadeAtual() {
		return this.currentLocaleData.getLocale();
	}
	
	public void setLocalidadeAtual(String idNovaLocalidade) {
		this.currentLocaleData = I18n.getInstance().getDadosDeLocalidade(idNovaLocalidade);
	}
	
	public TimeZone getFusoHorarioAtual() {
		return this.currentTimeZone;
	}
	
	public void setFusoHorarioAtual(String idNovoFusoHorario) {
		this.currentTimeZone = TimeZone.getTimeZone(idNovoFusoHorario);
	}
	
	public DadosDeLocalidade getDadosDeLocalidadeAtuais() {
		return this.currentLocaleData;
	}
}
