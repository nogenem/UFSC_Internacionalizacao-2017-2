package exceptions;

public class LanceInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public LanceInvalidoException() {
		super();
	}
	
	public LanceInvalidoException(String mensagem) {
		super(mensagem);
	}
}
