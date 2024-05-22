package modelos;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class empleado {
	private String nombre;
	private String contraseña;
	private LinkedList<adminSucursal> listaAdminSucursal = new LinkedList<>();
	private LinkedList<adminDeposito> listaAdminDeposito = new LinkedList<>();
	private LinkedList<cajero> listaCajero = new LinkedList<>();
	
	public empleado(String nombre, String contraseña) {
		this.nombre = nombre;
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public LinkedList<adminSucursal> getListaAdminSucursal() {
		return listaAdminSucursal;
	}

	public void setListaAdminSucursal(LinkedList<adminSucursal> listaAdminSucursal) {
		this.listaAdminSucursal = listaAdminSucursal;
	}

	public LinkedList<adminDeposito> getListaAdminDeposito() {
		return listaAdminDeposito;
	}

	public void setListaAdminDeposito(LinkedList<adminDeposito> listaAdminDeposito) {
		this.listaAdminDeposito = listaAdminDeposito;
	}

	public LinkedList<cajero> getListaCajero() {
		return listaCajero;
	}

	public void setListaCajero(LinkedList<cajero> listaCajero) {
		this.listaCajero = listaCajero;
	}
	
}


