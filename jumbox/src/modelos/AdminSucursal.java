package modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

public class AdminSucursal extends Empleado{
	private int idAdminSuc;
	private Producto producto;
	private Descuento descuento;
	
	public AdminSucursal(String nombre, String contraseña, int idAdminSuc) {
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
	
	public String crearDescuentoVencimiento( Producto productoa) {
		LocalDate fechaActual = LocalDate.now();
		long	diasHastaVencimiento	 = productoa.getFechaVencimiento().until(fechaActual, ChronoUnit.DAYS);

		if (diasHastaVencimiento <= 14) {
			int cantDescuento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el porcentaje de descuento"));
			do {
				cantDescuento = Integer.parseInt(
						JOptionPane.showInputDialog("Ingrese un porcentaje de descuento válido (entre 5% y 95%)"));
				if (cantDescuento > 95 || cantDescuento < 5) {
					JOptionPane.showMessageDialog(null, "El porcentaje de descuento debe estar entre 5% y 95%");
				}
			} while (cantDescuento < 95 && cantDescuento > 5);
			Descuento desc = new Descuento(1, cantDescuento, "Por vencimiento cerca");
			double precioDescuentoVencimiento = this.producto.getPrecio() * (1 - (desc.getPorcentajeDesc() / 100.0));
			this.producto.setPrecio(precioDescuentoVencimiento);

			JOptionPane.showMessageDialog(null, "Se ha aplicado un descuento al producto "
					+ this.producto.getNombreProducto() + " por su fecha de vencimiento que está cerca de la actual.");
			return "Se aplicó el descuento";

		} else {
			JOptionPane.showMessageDialog(null, "El producto " + this.producto.getNombreProducto()
					+ " no cumple con los requisitos de tiempo para aplicar un descuento en este momento.");
			return "No cumple con los requisitos";
		}
	}
}
