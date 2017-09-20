package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import interfaces.IConfiguracao;

public abstract class FabricaDeConfiguracao implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static String FILE_NAME = "arquivoDeConfiguracao";

	public static IConfiguracao montar() {
		Configuracao config = null;
		try {
			FileInputStream arquivo = new FileInputStream(FILE_NAME);
			ObjectInputStream objLeitura = new ObjectInputStream(arquivo);
			config = (Configuracao) objLeitura.readObject();
			objLeitura.close();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("> Erro ao carregar dados de configuracao! Inicializando os dados do zero...");
			System.err.println(">\t"+e.getMessage());
			config = new Configuracao();
		}
		return config;
	}
	
	public static void desmontar(IConfiguracao config) {
		try {
			FileOutputStream arquivo = new FileOutputStream(FILE_NAME);
			ObjectOutputStream objGravacao = new ObjectOutputStream(arquivo);
			objGravacao.writeObject(config);
			objGravacao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
