package clases;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

public class adminDeposito extends empleado {
	private int idAdminDepo;
	private producto Producto;
	private descuento Descuento;

    
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
		JOptionPane.showMessageDialog(null,
				"¡Bienvenido " + this.getNombre() + "! Ha iniciado sesión como Administrador de depósito");
	}

	public void registroEntradaSalida() {

	}

	public void armarPedido() {

	}

	public void validarDatos() {

	}
	
	adminDeposito admin = new adminDeposito("Jumbox Max", "1234", 1);

	public void DatosDeposito() {
		JOptionPane.showMessageDialog(null, "Datos del Admin de Deposito: " + "/n Nombre: " + this.getNombre() + "/n Contraseña: " +  this.getContraseña() + "/n Id del Deposito: " +  this.idAdminDepo);
	}
	
	public void crearDescuentoVencimiento() {
		LocalDate fechaActual = LocalDate.now();
		long diasHastaVencimiento = this.Producto.getFechaVencimiento().until(fechaActual, ChronoUnit.DAYS);

		if (diasHastaVencimiento <= 14) {
			int cantDescuento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el porcentaje de descuento"));
			Descuento.setPorcentajeDesc(cantDescuento);
			descuento desc = new descuento(1, cantDescuento, "Por vencimiento cerca");
			double precioDescuentoVencimiento = this.Producto.getPrecio() * (1 - (desc.getPorcentajeDesc() / 100.0));
			this.Producto.setPrecio(precioDescuentoVencimiento);
	
			JOptionPane.showMessageDialog(null, "Se ha aplicado un descuento al producto "
					+ this.Producto.getNombreProducto() + " por su fecha de vencimiento que está cerca de la actual.");
		} else {
			JOptionPane.showMessageDialog(null, "El producto " + this.Producto.getNombreProducto()
					+ " no cumple con los requisitos de tiempo para aplicar un descuento en este momento.");
		}
	}
}
