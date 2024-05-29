package modelos;

import javax.swing.JOptionPane;
import controladores.DetalleDepositoControlador; 

public class AdminDeposito extends Empleado {
	private int idAdminDepo;
	private String tipo;

	public AdminDeposito(String nombre, String email, String contraseña, int idAdminDepo) {
		super(nombre, email, contraseña);
		this.idAdminDepo = idAdminDepo;
	}

	public int getIdAdminDepo() {
		return idAdminDepo;
	}

	public void setIdAdminDepo(int idAdminDepo) {
		this.idAdminDepo = idAdminDepo;
	}

	public int getId() {
		return idAdminDepo;
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

	AdminDeposito admin = new AdminDeposito("Jumbox Max", "prueba@gmail.com", "1234", 1);

	public void DatosDeposito() {
		JOptionPane.showMessageDialog(null, "Datos del Deposito: " + "/n Nombre: " + this.getNombre()
				+ "/n Contraseña: " + this.getContraseña() + "/n Id del Deposito: " + this.idAdminDepo);
	}

	public static boolean registrarSalidaDepositoGeneral(int idDepositoGeneral, int idProducto, int cantidadSalida) {
		if (cantidadSalida <= 0) {
			// JOptionPane.showMessageDialog(null, "La cantidad de salida debe ser mayor que
			// cero.");
			return false;
		}

		DetalleDepositoControlador detalleDepositoControlador = new DetalleDepositoControlador();

		if (!detalleDepositoControlador.existeProducto(idDepositoGeneral, idProducto)) {
			// JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el
			// inventario de la sucursal.");
			return false;
		}

		int cantidadDisponible = detalleDepositoControlador.getCantidadDisponible(idDepositoGeneral, idProducto);

		if (cantidadDisponible >= cantidadSalida) {
			int cantidadTotal = cantidadDisponible - cantidadSalida;
			detalleDepositoControlador.actualizarCantidadProducto(idDepositoGeneral, idProducto, cantidadTotal);
			// JOptionPane.showMessageDialog(null, "Salida de " + cantidadSalida + "
			// unidades del producto con ID: " + idProducto + " registrada con éxito.");
			return true;
		} else {
			// JOptionPane.showMessageDialog(null, "No hay suficiente inventario para sacar
			// " + cantidadSalida + " unidades del producto con ID: " + idProducto);
			return false;
		}
	}
	
	public void confirmarSolicitudDePedido () {
		
	}
}
