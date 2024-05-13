package clases;

import java.util.LinkedList;

public class informe {
	private int idInforme;
	private LinkedList<detalleInforme> listaInformes = new LinkedList<>();
	
	public informe(int idInforme, LinkedList<detalleInforme> listaInformes) {
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

	public LinkedList<detalleInforme> getListaInformes() {
		return listaInformes;
	}

	public void setListaInformes(LinkedList<detalleInforme> listaInformes) {
		this.listaInformes = listaInformes;
	}
}
