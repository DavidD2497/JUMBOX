package modelos;

import javax.swing.JOptionPane;

public class AdminDeposito extends Empleado {
	private String tipo;

	public AdminDeposito(String nombre, String email, String contraseña) {
		super(nombre, email, contraseña);
		this.tipo = "AdminDeposito";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void registroEntradaSalida() {

	}

	public void armarPedido() {

	}

	public void validarDatos() {

	}

}