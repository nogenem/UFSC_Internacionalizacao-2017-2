package exceptions;

import util.I18n;

public class UsuarioNaoCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoCadastradoException() {
		super(I18n.getInstance().getString("exceptions.usuario_nao_cadastrado"));
	}
}
