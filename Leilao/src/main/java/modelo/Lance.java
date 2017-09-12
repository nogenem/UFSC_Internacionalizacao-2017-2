package modelo;

import java.io.Serializable;

import util.I18n;


public class Lance implements Serializable {

	private static final long serialVersionUID = 1L;
	private Double valorDoLance;
	private Usuario donoDoLance;
	private ProdutoLeilao produtoQueRecebeuOLance;
	
	public Lance(Double valorDoLance, Usuario donoDoLance) {
		this.valorDoLance = valorDoLance;
		this.donoDoLance = donoDoLance;
	}
	
	
	public Double getValorDoLance() {
		return this.valorDoLance;
	}
	
	public String getApelidoDonoDoLance() {
		return this.donoDoLance.getApelido();
	}
	
	public String getNomeDonoDoLance() {
		return this.donoDoLance.getNome();
	}
	
	public ProdutoLeilao getProdutoQueRecebeuOLance() {
		return this.produtoQueRecebeuOLance;
	}
	
	public String getNomeProdutoQueRecebeuOLance() {
		return this.produtoQueRecebeuOLance.getNome();
	}
	
	public void setProdutoQueRecebeuOLance(ProdutoLeilao produto) {
		this.produtoQueRecebeuOLance = produto;
	}
	
	public String toString() {//TODO remover esse 'lance no produto' !? 
		return I18n.getInstance().getString("lance.lance_no_produto") +
				" "+ this.produtoQueRecebeuOLance.getNome();
	}
}
