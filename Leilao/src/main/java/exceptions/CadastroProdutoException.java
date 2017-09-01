package exceptions;

public class CadastroProdutoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CadastroProdutoException() {
		super();
	}
	
	public CadastroProdutoException(String mensagem) {
		super(mensagem);
	}
}
