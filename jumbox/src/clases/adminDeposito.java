package clases;

import javax.swing.JOptionPane;

public class adminDeposito extends empleado{
	private int idAdminDepo;
	private String nombre;
	private String contraseña;
	

	public adminDeposito(String nombre, String contraseña, int idAdminDepo, int nombre2, int contraseña2) {
		super(nombre, contraseña);
		this.idAdminDepo = idAdminDepo;
		this.contraseña = contraseña;
		this.nombre = nombre;
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

	public adminDeposito(String nombre, String contraseña, int idAdminDepo) {
		super(nombre, contraseña);
		this.idAdminDepo = idAdminDepo;
	}

	public int getIdAdminDepo() {
		return idAdminDepo;
	}

	public void setIdAdminDepo(int idAdminDepo) {
		this.idAdminDepo = idAdminDepo;
	}
	
	public void bienvenida() {
		JOptionPane.showMessageDialog(null, "¡Bienvenido " + this.getNombre() + "! Ha iniciado sesión como Administrador de depósito");
	}
	
	public void registroEntradaSalida() {
		
	}
	
	adminDeposito admin = new adminDeposito("Jumbox Max", "1234", 1);
	
	public void DatosDeposito() {
		JOptionPane.showMessageDialog(null, "Datos del Deposito: " + "/n Nombre: " + this.nombre + "/n Contraseña: " +  this.contraseña + "/n Id del Deposito: " +  this.idAdminDepo);
	}
	
	public void armarPedido() {
		
	}
}
