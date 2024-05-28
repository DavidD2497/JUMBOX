package modelos;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class AdminDeposito extends Empleado {
	private int idAdminDepo;
	private LinkedList<DetalleInventario> listaInventario = new LinkedList<>();

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

	public LinkedList<DetalleInventario> getListaInventario() {
		return listaInventario;
	}

	public void setListaInventario(LinkedList<DetalleInventario> listaInventario) {
		this.listaInventario = listaInventario;
	}

	public void registroSalidaInventario(int idProducto, int cantidadSalida) {
		for (DetalleInventario detalle : listaInventario) {
			if (detalle.getProducto().getIdProducto() == idProducto) {
				if (detalle.getCantidad() >= cantidadSalida) {
					detalle.setCantidad(detalle.getCantidad() - cantidadSalida);
					JOptionPane.showMessageDialog(null, "Se ha registrado la salida de " + cantidadSalida + " unidad/es del producto: " + detalle.getProducto().getNombreProducto());
				} else {
					JOptionPane.showMessageDialog(null, "No hay suficiente inventario para el producto: " + detalle.getProducto().getNombreProducto());
				}
				break;
			}
		}
	}

	public void armarPedido() {
		// Lógica para armar pedido
	}

	public void validarDatos() {
		// Lógica para validar datos
	}

	AdminDeposito admin = new AdminDeposito("Jumbox Max", "prueba@gmail.com", "1234", 1);

	public void DatosDeposito() {
		JOptionPane.showMessageDialog(null, "Datos del Deposito: " + "\n Nombre: " + this.getNombre()
				+ "\n Contraseña: " + this.getContraseña() + "\n Id del Deposito: " + this.idAdminDepo);
	}
}
