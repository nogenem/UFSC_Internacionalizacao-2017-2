package exceptions;

import util.I18n;

public class CarregaMercadoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CarregaMercadoException() {
		super(I18n.getInstance().getString("exceptions.carregar_mercado"));
	}
}
