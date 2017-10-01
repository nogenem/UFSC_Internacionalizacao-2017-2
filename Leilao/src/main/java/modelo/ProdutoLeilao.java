package modelo;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import interfaces.IVendido;

public class ProdutoLeilao extends Produto implements IVendido, Serializable  {

	private static final long serialVersionUID = 12L;
	
	private Date dataLimite;
	private Double lanceMinimo;
	private List<Lance> lancesEfetuados;
	private Usuario leiloador;
	private Usuario comprador;
 
	public ProdutoLeilao(String nome, String descricao, Double lanceMinimo, Usuario leiloador) {
		super(nome, descricao);
		this.lanceMinimo = lanceMinimo;
		this.leiloador = leiloador;
		this.lancesEfetuados = new ArrayList<Lance>();
	}
		
	public Date getDataLimite() {
		return this.dataLimite;
	}
	
	public void setDataLimite(Date data) {
		this.dataLimite = data;
	}
	
	public Double getLanceMinimo() {
		return this.lanceMinimo;
	}
	
	public List<Lance> retornaTodosOsLancesFeitosNesseProduto() {
		return this.lancesEfetuados;
	}
	
	public void recebaLance(Lance lance) {
			this.lancesEfetuados.add(lance);
	}
	
	public boolean dataDoProdutoExpirou() {
		ZoneId zonaAtual = Configuracao.getInstance().getFusoHorarioAtual();
		ZonedDateTime now = Instant.now().atZone(zonaAtual);
		ZonedDateTime limite = this.dataLimite.toInstant().atZone(zonaAtual);
		return now.compareTo(limite) > 0;
	}
	
	public List<Lance> verificaLancesEfetuadosPorUmUsuario(String apelidoUsuario) {
		List<Lance> retornoDeLances = new ArrayList<Lance>();
		for(int i=0; i<lancesEfetuados.size(); i++) {
			if(apelidoUsuario.equals(lancesEfetuados.get(i).getApelidoDonoDoLance())) {
				retornoDeLances.add(lancesEfetuados.get(i));
			}
		}
		return retornoDeLances;
	}

	public String getNome() {
		return this.nome();
	}
	
	public String getDescricao() {
		return this.descricao();
	}
	
	public String getApelidoLeiloador() {
		return this.leiloador.getApelido();
	}
	
	public Double getValorUltimoLance() {
		try {
			int index = this.lancesEfetuados.size()-1;
			return this.lancesEfetuados.get(index).getValorDoLance();
		}catch(Exception e) {
//			e.printStackTrace();
			return 0.0;
		}
		
	}
	
	public Lance getLanceMaisRecente() {
		try {
			int index = this.lancesEfetuados.size()-1;
			return this.lancesEfetuados.get(index);
		}catch(Exception e) {
//			e.printStackTrace();
			return new Lance(0.0, new Usuario("", ""));
		}
	}
	
	public String getApelidoComprador() {
		return this.comprador.getApelido();
	}
	
	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}
	
	public String toString() {
		return this.nome();
	}
}