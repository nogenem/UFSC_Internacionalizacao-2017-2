package modelo;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Locale;

import interfaces.IConfiguracao;
import util.DadosDeLocalidade;
import util.I18n;

public class Configuracao implements IConfiguracao, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static Configuracao instance;
	
	private DadosDeLocalidade currentLocaleData;
	//https://www.mkyong.com/java/java-display-list-of-timezone-with-gmt/
	private ZoneId currentTimeZone;
	
	private Configuracao() {
		Locale _default = Locale.getDefault();
		I18n i18n = I18n.getInstance();
		
		if(!i18n.possuiLocale(_default.toString())) {
			System.out.println("> Default sem traducao, voltando para pt_BR.");
			_default = new Locale("pt", "BR");
		}
		i18n.carregaBundle(_default);
		
		this.currentLocaleData = i18n.getDadosDeLocalidade(_default.toString());
		this.currentTimeZone = ZoneId.systemDefault();
	}
	
	public static Configuracao getInstance() {
		if(instance == null) {
			instance = (Configuracao) FabricaDeConfiguracao.montar();
			if(instance == null)
				instance = new Configuracao();
		}
		return instance;
	}
	
	public Locale getLocalidadeAtual() {
		return this.currentLocaleData.getLocale();
	}
	
	public void setLocalidadeAtual(String idNovaLocalidade) {
		this.currentLocaleData = I18n.getInstance().getDadosDeLocalidade(idNovaLocalidade);
	}
	
	public ZoneId getFusoHorarioAtual() {
		return this.currentTimeZone;
	}
	
	public void setFusoHorarioAtual(String idNovoFusoHorario) {
		this.currentTimeZone = ZoneId.of(idNovoFusoHorario);
	}
	
	public DadosDeLocalidade getDadosDeLocalidadeAtuais() {
		return this.currentLocaleData;
	}
}
