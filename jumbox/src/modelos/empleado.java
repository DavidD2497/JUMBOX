package modelos;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public abstract class Empleado {
	private String nombre;
    private String email;
    private String contraseña;
	private LinkedList<AdminSucursal> listaAdminSucursal = new LinkedList<>();
	private LinkedList<AdminDeposito> listaAdminDeposito = new LinkedList<>();
	private LinkedList<Cajero> listaCajero = new LinkedList<>();
	
	public Empleado(String nombre, String email, String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public LinkedList<AdminSucursal> getListaAdminSucursal() {
		return listaAdminSucursal;
	}

	public void setListaAdminSucursal(LinkedList<AdminSucursal> listaAdminSucursal) {
		this.listaAdminSucursal = listaAdminSucursal;
	}

	public LinkedList<AdminDeposito> getListaAdminDeposito() {
		return listaAdminDeposito;
	}

	public void setListaAdminDeposito(LinkedList<AdminDeposito> listaAdminDeposito) {
		this.listaAdminDeposito = listaAdminDeposito;
	}

	public LinkedList<Cajero> getListaCajero() {
		return listaCajero;
	}

	public void setListaCajero(LinkedList<Cajero> listaCajero) {
		this.listaCajero = listaCajero;
	}
	
	public abstract int getId();
	
}


