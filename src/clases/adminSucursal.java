package clases;

import javax.swing.JOptionPane;

public class adminSucursal extends empleado{
	private int idAdminSuc;

	public adminSucursal(String nombre, String contraseña, int idAdminSuc) {
		super(nombre, contraseña);
		this.idAdminSuc = idAdminSuc;
	}
	
	public int getIdAdminSuc() {
		return idAdminSuc;
	}



	public void setIdAdminSuc(int idAdminSuc) {
		this.idAdminSuc = idAdminSuc;
	}
	
	public void bienvenida() {
		JOptionPane.showMessageDialog(null, "¡Bienvenido " + this.getNombre() + "! Ha iniciado sesión como Administrador de surcursal");
	}
	
	public void registroEntradaSalida() {
		
	}
	public void solicitarPedido() {
		
	}
	public void creaDescuento() {
		
	}
	public void generarInforme() {
		
	}
}
