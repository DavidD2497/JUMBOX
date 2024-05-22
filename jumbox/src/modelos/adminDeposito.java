package modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

public class adminDeposito extends empleado {
	private int idAdminDepo;
	

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

	
	public void registroEntradaSalida() {

	}

	public void armarPedido() {

	}

	public void validarDatos() {

	}

	adminDeposito admin = new adminDeposito("Jumbox Max", "1234", 1);

	public void DatosDeposito() {
		JOptionPane.showMessageDialog(null, "Datos del Deposito: " + "/n Nombre: " + this.getNombre()
				+ "/n Contraseña: " + this.getContraseña() + "/n Id del Deposito: " + this.idAdminDepo);
	}

	
}