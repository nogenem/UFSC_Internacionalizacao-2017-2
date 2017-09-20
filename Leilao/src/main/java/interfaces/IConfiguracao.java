package interfaces;

import java.util.Locale;
import java.util.TimeZone;

import util.DadosDeLocalidade;

public interface IConfiguracao {
	public Locale getLocalidadeAtual();
	public void setLocalidadeAtual(String idNovaLocalidade);
	public TimeZone getFusoHorarioAtual();
	public void setFusoHorarioAtual(String idNovoFusoHorario);
	public DadosDeLocalidade getDadosDeLocalidadeAtuais();
}
