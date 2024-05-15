package clases;

import java.util.LinkedList;

public class depositoGeneral {
	private int idDeposito;
	private LinkedList<detalleDeposito> listaDeposito = new LinkedList<>();
	
	public depositoGeneral(int idDeposito, LinkedList<detalleDeposito> listaDeposito) {
		super();
		this.idDeposito = idDeposito;
		this.listaDeposito = listaDeposito;
	}

	public int getIdDeposito() {
		return idDeposito;
	}

	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}

	public LinkedList<detalleDeposito> getListaDeposito() {
		return listaDeposito;
	}

	public void setListaDeposito(LinkedList<detalleDeposito> listaDeposito) {
		this.listaDeposito = listaDeposito;
	}
	
	
}
