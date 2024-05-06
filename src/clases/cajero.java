package clases;

import javax.swing.JOptionPane;

public class cajero extends empleado{
	private int idCajero;

	public cajero(String nombre, String contraseña, int idCajero) {
		super(nombre, contraseña);
		this.idCajero = idCajero;
	}

	public int getIdCajero() {
		return idCajero;
	}

	public void setIdCajero(int idCajero) {
		this.idCajero = idCajero;
	}
	
	public void bienvenida() {
		JOptionPane.showMessageDialog(null, "¡Bienvenido " + this.getNombre() + "! Ha iniciado sesión como Cajero");
	}
	
	public void registroEntradaSalida() {
		
	}
}
