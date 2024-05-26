package modelos;

import java.util.LinkedList;

public class Informe {
	private int idInforme;
	private LinkedList<DetalleInforme> listaInformes = new LinkedList<>();
	
	public Informe(int idInforme, LinkedList<DetalleInforme> listaInformes) {
		super();
		this.idInforme = idInforme;
		this.listaInformes = listaInformes;
	}

	public int getIdInforme() {
		return idInforme;
	}

	public void setIdInforme(int idInforme) {
		this.idInforme = idInforme;
	}

	public LinkedList<DetalleInforme> getListaInformes() {
		return listaInformes;
	}

	public void setListaInformes(LinkedList<DetalleInforme> listaInformes) {
		this.listaInformes = listaInformes;
	}
}
