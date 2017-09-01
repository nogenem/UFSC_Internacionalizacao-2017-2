package exceptions;

public class UsuarioNaoCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoCadastradoException() {
		super("O usuario nao esta cadastrado.");
	}
}
