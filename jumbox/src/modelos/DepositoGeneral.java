package modelos;

import java.util.LinkedList;

public class DepositoGeneral {
	private int idDeposito;
	private LinkedList<DetalleDeposito> listaDeposito = new LinkedList<>();
	
	public DepositoGeneral(int idDeposito, LinkedList<DetalleDeposito> listaDeposito) {
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

	public LinkedList<DetalleDeposito> getListaDeposito() {
		return listaDeposito;
	}

	public void setListaDeposito(LinkedList<DetalleDeposito> listaDeposito) {
		this.listaDeposito = listaDeposito;
	}
	
	
}
