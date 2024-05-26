package modelos;

import javax.swing.JOptionPane;

public class AdminDeposito extends empleado{
	private int idAdminDepo;

	public AdminDeposito(String nombre, String contraseña, int idAdminDepo) {
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
	
	public void armarPedido() {
		
	}
}
