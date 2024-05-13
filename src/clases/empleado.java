package clases;

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
	
	public void cargarEmpleados() {
	
		listaAdminSucursal.add(new adminSucursal("David", "1234", 1));
		listaAdminSucursal.add(new adminSucursal("Jorge", "6493", 2));
		listaAdminSucursal.add(new adminSucursal("Tomas", "Milito22", 3));
		listaAdminSucursal.add(new adminSucursal("Pedro", "pedrito01", 4));
		listaAdminSucursal.add(new adminSucursal("Christian", "Cristi894", 5));
		listaAdminSucursal.add(new adminSucursal("Victoria", "9101", 6));
		listaAdminDeposito.add(new adminDeposito("Federico", "5678", 1));
		listaAdminDeposito.add(new adminDeposito("Pablo", "Simon02", 2));
		listaAdminDeposito.add(new adminDeposito("Fito", "Fitito100", 3));
		listaAdminDeposito.add(new adminDeposito("Nicolas", "Nials12", 4));
		listaAdminDeposito.add(new adminDeposito("Julian", "Julito01", 5));
		listaCajero.add(new cajero("Peter", "1234",1));
		listaCajero.add(new cajero("Roberto", "Robson01",2));
		listaCajero.add(new cajero("Lucas", "Luquitas02",3));
		listaCajero.add(new cajero("German", "Intrepido100",4));
		listaCajero.add(new cajero("Alfredo", "Estudiante03",5));
	}
	
	public boolean iniciarSesion(int id, String pass, String tipoUsuario) {
	    switch (tipoUsuario) {
	        case "Administrador de sucursal":
	            for (adminSucursal admin : listaAdminSucursal) {
	                if (admin.getIdAdminSuc() == id && admin.getContraseña().equals(pass)) {
	                    return true;
	                }
	            }
	            break;
	        case "Administrador de depósito":
	            for (adminDeposito admin : listaAdminDeposito) {
	                if (admin.getIdAdminDepo() == id && admin.getContraseña().equals(pass)) {
	                    return true;
	                }
	            }
	            break;
	        case "Cajero":
	            for (cajero cajero : listaCajero) {
	                if (cajero.getIdCajero() == id && cajero.getContraseña().equals(pass)) {
	                    return true;
	                }
	            }
	            break;
	        default:
	            return false;
	    }
	    return false;
	}
	
	public void bienvenida() {
		JOptionPane.showMessageDialog(null, "Bienvenido");
	}
	
	public void registroEntradaSalida() {
		
	}
}


