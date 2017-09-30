package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import exceptions.CarregaMercadoException;
import interfaces.IMercadoLeilao;

public abstract class FabricaDeMercado implements Serializable {
	
	private final static String FILE_NAME = "arquivoDoMercado";
	private static final long serialVersionUID = 1L;
	
	public static IMercadoLeilao montar() throws CarregaMercadoException {
		try {
			FileInputStream arquivo = new FileInputStream(FILE_NAME);
			ObjectInputStream objLeitura = new ObjectInputStream(arquivo);
			MercadoLeilao mercado = (MercadoLeilao) objLeitura.readObject();
			objLeitura.close();
			
			return mercado;
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("> " +e.getMessage());
			throw new CarregaMercadoException();
		}
	}
	
	public static void desmontar(IMercadoLeilao mercado) {
		try {
			FileOutputStream arquivo = new FileOutputStream(FILE_NAME);
			ObjectOutputStream objGravacao = new ObjectOutputStream(arquivo);
			objGravacao.writeObject(mercado);
			objGravacao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
