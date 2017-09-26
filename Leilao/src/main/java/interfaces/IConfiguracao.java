package interfaces;

import java.time.ZoneId;
import java.util.Locale;

import util.DadosDeLocalidade;

public interface IConfiguracao {
	public Locale getLocalidadeAtual();
	public void setLocalidadeAtual(String idNovaLocalidade);
	public ZoneId getFusoHorarioAtual();
	public void setFusoHorarioAtual(String idNovoFusoHorario);
	public DadosDeLocalidade getDadosDeLocalidadeAtuais();
}
