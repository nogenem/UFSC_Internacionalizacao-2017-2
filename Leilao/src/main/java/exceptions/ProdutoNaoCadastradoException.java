package exceptions;

public class ProdutoNaoCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ProdutoNaoCadastradoException() {
		super("O produto nao esta cadastrado.");
	}
}
