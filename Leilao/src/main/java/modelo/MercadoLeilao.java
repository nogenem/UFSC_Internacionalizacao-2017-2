package modelo;

import interfaces.ILeiloavel;
import interfaces.IMercadoLeilao;
import interfaces.IUsuario;
import interfaces.IVendido;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class MercadoLeilao implements IMercadoLeilao, Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Map<String, Usuario> usuarios;
	private List<ProdutoLeilao> produtosEmLeilao;
	private List<ProdutoLeilao> produtosVendidos;
	private List<ProdutoLeilao> produtosVencidosENaoVendidos;
	
	public MercadoLeilao() {
		this.usuarios = new HashMap<String, Usuario>();
		this.produtosEmLeilao = new ArrayList<ProdutoLeilao>();
		this.produtosVendidos = new ArrayList<ProdutoLeilao>();
		this.produtosVencidosENaoVendidos = new ArrayList<ProdutoLeilao>();
	}
	
	
	public void cadastrarUsuario(String nome, String endereco, String email, String apelido) throws Exception {
		if(verificaSeOUsuarioJaExiste(apelido)) {
			throw new Exception("Ja existe um usuario com este apelido.");
		}
		else {
			Usuario usuario = new Usuario(apelido, nome);
			usuario.setEndereco(endereco);
			usuario.setEmail(email);
			this.usuarios.put(apelido, usuario);
		}
	}

	
	public void cadastrarProduto(String nome, String descricao, Double lanceMinimo, String apelidoLeiloador, Date dataLimite) throws Exception {
		if(!verificaSeOProdutoJaExiste(nome) && verificaSeOUsuarioJaExiste(apelidoLeiloador)) {
			Usuario leiloador = usuarios.get(apelidoLeiloador);
			ProdutoLeilao produto = new ProdutoLeilao(nome, descricao, lanceMinimo, leiloador);
			produto.setDataLimite(dataLimite);
			produtosEmLeilao.add(produto);
			leiloador.setBemOfertado(produto);
		}
		else
			throw new Exception("O produto ja existe ou o leiloador nao esta cadastrado.");
	}
	
	
	public List<? extends ILeiloavel> getProdutosEmLeilao() {
		atualizarListasDeProdutos();
		List<ILeiloavel> retornoProdutosEmLeilao = new ArrayList<ILeiloavel>();
		retornoProdutosEmLeilao.addAll(this.produtosEmLeilao);
		return retornoProdutosEmLeilao;
	}
	
	
	public List<? extends ILeiloavel> getProdutosVencidosENaoVendidos() {
		atualizarListasDeProdutos();
		List<ILeiloavel> retornoProdutosVencidos = new ArrayList<ILeiloavel>();
		retornoProdutosVencidos.addAll(this.produtosVencidosENaoVendidos);
		return retornoProdutosVencidos;
	}
	

	public List<? extends IVendido> getProdutosVendidos() {
		atualizarListasDeProdutos();
		List<IVendido> retornoProdutosVendidos = new ArrayList<IVendido>();
		retornoProdutosVendidos.addAll(this.produtosVendidos);
		return retornoProdutosVendidos;
	}
	
	public List<IUsuario> getUsuariosCadastrados() {
		List<IUsuario> retornoUsuariosCadastrados = new ArrayList<IUsuario>();
		retornoUsuariosCadastrados.addAll(this.usuarios.values());
		return retornoUsuariosCadastrados;
	}
	

	public void daLance(String nomeProduto, String apelidoComprador, Double valorLance) throws Exception {
		atualizarListasDeProdutos();
		Lance lance = new Lance(valorLance, (Usuario)this.getUsuarioPor(apelidoComprador));
		ProdutoLeilao produto = produtosEmLeilao.get(pesquisaIndexProdutoEmLeilaoViaNome(nomeProduto));
		Double lanceMinimo = produto.getLanceMinimo();
		Double lanceAtual = produto.getValorUltimoLance();
		if(verificaSeOUsuarioJaExiste(apelidoComprador) && valorLance >= lanceMinimo && valorLance > lanceAtual) {
			produto.recebaLance(lance);
			lance.setProdutoQueRecebeuOLance(produto);
		}
		else
			throw new Exception("O valor do lance eh inferior ao necessario ou o comprador nao esta cadastrado.");
	}
	
	public List<Lance> retornaTodosOsLancesEfetuados() {
		List<Lance> retornoLances = new ArrayList<Lance>();
		retornoLances.addAll(retornaTodosOsLancesEfetuadosEmProdutosEmLeilao());
		retornoLances.addAll(retornaTodosOsLancesEfetuadosEmProdutosVendidos());
		return retornoLances;
	}
	
	public List<Lance> retornaLancesDeUmUsuario(String apelidoUsuario) throws Exception {
		if(!verificaSeOUsuarioJaExiste(apelidoUsuario))
			throw new Exception("O usuario nao esta cadastrado.");
		List<Lance> retornoLances = new ArrayList<Lance>();
		retornoLances.addAll(retornaLancesDeUmUsuarioEmProdutosAindaEmLeilao(apelidoUsuario));
		retornoLances.addAll(retornaLancesDeUmUsuarioEmProdutosVendidos(apelidoUsuario));
		return retornoLances;
	}
	
	public List<ProdutoLeilao> retornaProdutosDeUmLeiloador(String apelidoUsuario) throws Exception {
		atualizarListasDeProdutos();
		if(!verificaSeOUsuarioJaExiste(apelidoUsuario))
			throw new Exception("O usuario nao esta cadastrado.");
		List<ProdutoLeilao> retornoProdutos = new ArrayList<ProdutoLeilao>();
		retornoProdutos.addAll(retornaProdutosEmLeilaoPorUmUsuario(apelidoUsuario));
		retornoProdutos.addAll(retornaProdutosVendidosPorUmUsuario(apelidoUsuario));
		retornoProdutos.addAll(retornaProdutosVencidosMasNaoVendidosPorUmUsuario(apelidoUsuario));
		return retornoProdutos;
	}
	
	
	public List<? extends ILeiloavel> getProdutosQueDeuLance(String apelidoUsuario) throws Exception {
		atualizarListasDeProdutos();
		if(!verificaSeOUsuarioJaExiste(apelidoUsuario))
			throw new Exception("O usuario nao esta cadastrado.");
		else {
			List<ILeiloavel> produtosQueDeuLance = new ArrayList<ILeiloavel>();
			produtosQueDeuLance.addAll(getProdutosEmLeilaoQueDeuLance(apelidoUsuario));
			produtosQueDeuLance.addAll(getProdutosVendidosQueDeuLance(apelidoUsuario));
			return produtosQueDeuLance;
		}
	}
	
	
	public IUsuario getUsuarioPor(String apelido) throws Exception {
		return this.usuarios.get(apelido);
	}

	
///////////////////////////////////   METODOS PRIVADOS   ///////////////////////////////////
	
	
	private void atualizarListasDeProdutos() {
		for(int i=0; i<produtosEmLeilao.size(); i++) {
			ProdutoLeilao produto = produtosEmLeilao.get(i);
			atualizaSeFoiVendido(produto);
			atualizaSeVenceuENaoFoiVendido(produto);
		}
	}
	
	private void atualizaSeFoiVendido(ProdutoLeilao produto) {
		int qtdLances = produto.retornaTodosOsLancesFeitosNesseProduto().size();
		if(produto.dataDoProdutoExpirou() && qtdLances > 0) {
			produtosVendidos.add(produto);
			String apelidoDonoDoLance = produto.getLanceMaisRecente().getApelidoDonoDoLance();
			Usuario comprador = usuarios.get(apelidoDonoDoLance);
			comprador.setBemComprado(produto);
			produto.setComprador(comprador);
			produtosEmLeilao.remove(produto);
		}
	}
	
	private void atualizaSeVenceuENaoFoiVendido(ProdutoLeilao produto) {
		int qtdLances = produto.retornaTodosOsLancesFeitosNesseProduto().size();
		if(produto.dataDoProdutoExpirou() && qtdLances == 0) {
			produtosVencidosENaoVendidos.add(produto);
			produtosEmLeilao.remove(produto);
		}
	}
	
	
	private boolean verificaSeOProdutoJaExiste(String nome) {
		return verificaSeExisteEntreOsEmLeilao(nome) || verificaSeExisteEntreOsVendidos(nome) || 
				verificaSeExisteEntreOsVencidosENaoVendidos(nome);
	}
	
	private boolean verificaSeExisteEntreOsEmLeilao(String nome) {
		for(int i=0; i<produtosEmLeilao.size(); i++) {
			if(nome.equalsIgnoreCase(produtosEmLeilao.get(i).getNome())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean verificaSeExisteEntreOsVendidos(String nome) {
		for(int i=0; i<produtosVendidos.size(); i++) {
			if(nome.equalsIgnoreCase(produtosVendidos.get(i).getNome())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean verificaSeExisteEntreOsVencidosENaoVendidos(String nome) {
		for(int i=0; i<produtosVencidosENaoVendidos.size(); i++) {
			if(nome.equalsIgnoreCase(produtosVencidosENaoVendidos.get(i).getNome())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean verificaSeOUsuarioJaExiste(String apelidoUsuario) {
		//|| apelidoUsuario.contains("_")
		return usuarios.containsKey(apelidoUsuario);
	}
	
	private Integer pesquisaIndexProdutoEmLeilaoViaNome(String nomeProduto) throws Exception {
		for(int i=0; i<produtosEmLeilao.size(); i++) {
			if(nomeProduto.equalsIgnoreCase(produtosEmLeilao.get(i).nome()))
				return i;
		}
		throw new Exception("Nao existe produto cadastrado com esse nome.");
	}
	
	private List<Lance> retornaLancesDeUmUsuarioEmProdutosAindaEmLeilao(String apelidoUsuario) {
		List<Lance> retornoLances = new ArrayList<Lance>();
		for(int i=0; i<produtosEmLeilao.size(); i++) {
			retornoLances.addAll(produtosEmLeilao.get(i).verificaLancesEfetuadosPorUmUsuario(apelidoUsuario));
		}
		return retornoLances;
	}
	
	private List<Lance> retornaLancesDeUmUsuarioEmProdutosVendidos(String apelidoUsuario) {
		List<Lance> retornoLances = new ArrayList<Lance>();
		for(int i=0; i<produtosVendidos.size(); i++) {
			retornoLances.addAll(produtosVendidos.get(i).verificaLancesEfetuadosPorUmUsuario(apelidoUsuario));
		}
		return retornoLances;
	}
	
	private List<Lance> retornaTodosOsLancesEfetuadosEmProdutosEmLeilao() {
		List<Lance> retornoLances = new ArrayList<Lance>();
		for(int i=0; i<produtosEmLeilao.size(); i++) {
			retornoLances.addAll(produtosEmLeilao.get(i).retornaTodosOsLancesFeitosNesseProduto());
		}
		return retornoLances;
	}
	
	private List<Lance> retornaTodosOsLancesEfetuadosEmProdutosVendidos() {
		List<Lance> retornoLances = new ArrayList<Lance>();
		for(int i=0; i<produtosVendidos.size(); i++) {
			retornoLances.addAll(produtosVendidos.get(i).retornaTodosOsLancesFeitosNesseProduto());
		}
		return retornoLances;
	}
	
	private List<ProdutoLeilao> retornaProdutosEmLeilaoPorUmUsuario(String apelidoUsuario) {
		List<ProdutoLeilao> retornoProdutos = new ArrayList<ProdutoLeilao>();
		for(int i=0; i<produtosEmLeilao.size(); i++) {
			if(apelidoUsuario.equals(produtosEmLeilao.get(i).getApelidoLeiloador()))
				retornoProdutos.add(produtosEmLeilao.get(i));
		}
		return retornoProdutos;
	}
	
	private List<ProdutoLeilao> retornaProdutosVendidosPorUmUsuario(String apelidoUsuario) {
		List<ProdutoLeilao> retornoProdutos = new ArrayList<ProdutoLeilao>();
		for(int i=0; i<produtosVendidos.size(); i++) {
			if(apelidoUsuario.equals(produtosVendidos.get(i).getApelidoLeiloador()))
				retornoProdutos.add(produtosVendidos.get(i));
		}
		return retornoProdutos;
	}
	
	private List<ProdutoLeilao> retornaProdutosVencidosMasNaoVendidosPorUmUsuario(String apelidoUsuario) {
		List<ProdutoLeilao> retornoProdutos = new ArrayList<ProdutoLeilao>();
		for(int i=0; i<produtosVencidosENaoVendidos.size(); i++) {
			if(apelidoUsuario.equals(produtosVencidosENaoVendidos.get(i).getApelidoLeiloador()))
				retornoProdutos.add(produtosVencidosENaoVendidos.get(i));
		}
		return retornoProdutos;
	}
	
	
	private boolean verificaSeDeuLanceNesseProduto(String apelidoComprador, ProdutoLeilao produto) {
		List<Lance> lances = produto.retornaTodosOsLancesFeitosNesseProduto();
		for(int i=0; i<lances.size(); i++) {
			if(lances.get(i).getApelidoDonoDoLance().equalsIgnoreCase(apelidoComprador))
				return true;
		}
		return false;
	}
	
	
	private List<? extends ILeiloavel> getProdutosEmLeilaoQueDeuLance(String apelido) {
		List<ILeiloavel> retornoProdutos = new ArrayList<ILeiloavel>();
		for(int i=0; i<produtosEmLeilao.size(); i++) {
			ProdutoLeilao produto = produtosEmLeilao.get(i);
			if(verificaSeDeuLanceNesseProduto(apelido, produto))
				retornoProdutos.add(produto);
		}
		return retornoProdutos;
	}
	
	private List<? extends ILeiloavel> getProdutosVendidosQueDeuLance(String apelido) {
		List<ILeiloavel> retornoProdutos = new ArrayList<ILeiloavel>();
		for(int i=0; i<produtosVendidos.size(); i++) {
			ProdutoLeilao produto = produtosVendidos.get(i);
			if(verificaSeDeuLanceNesseProduto(apelido, produto))
				retornoProdutos.add(produto);
		}
		return retornoProdutos;
	}
}