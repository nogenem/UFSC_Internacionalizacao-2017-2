package exceptions;

public class CadastroUsuarioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CadastroUsuarioException() {
		super();
	}
	
	public CadastroUsuarioException(String mensagem) {
		super(mensagem);
	}
}
