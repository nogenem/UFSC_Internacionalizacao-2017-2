package interfaces;

import java.util.Date;
import java.util.List;

public interface IMercadoLeilao {
	public void cadastrarUsuario(String nome, String endereco, String email, String apelido) throws Exception;

	public void cadastrarProduto(String nome, String descricao, Double valorMinimo, String apelidoLeiloador, Date dataLimite) throws Exception;

	public List<? extends ILeiloavel> getProdutosEmLeilao();

	public List<? extends IVendido> getProdutosVendidos();

	public void daLance(String nomeProduto, String apelidoComprador, Double valorLance) throws Exception;

	/* Exceção se o Apelido não for encontrado */
	public List<? extends ILeiloavel> getProdutosQueDeuLance(String apelido) throws Exception;

	public IUsuario getUsuarioPor(String apelido) throws Exception;

	public List<? extends ILeiloavel> getProdutosVencidosENaoVendidos();

}