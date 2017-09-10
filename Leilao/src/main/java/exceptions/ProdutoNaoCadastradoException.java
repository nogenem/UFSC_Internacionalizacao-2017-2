package exceptions;

import util.I18n;

public class ProdutoNaoCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ProdutoNaoCadastradoException() {
		super(I18n.getInstance().getString("exceptions.produto_nao_cadastrado"));
	}
}
