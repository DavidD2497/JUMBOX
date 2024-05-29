package modelos;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import controladores.DetalleDepositoControlador;
import controladores.PedidoControlador;

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

	public void confirmarSolicitudDePedido(LinkedList<DetallePedido> listaDetalle, LocalDate fechaEntrega) {
		if (listaDetalle.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error. Se creó una lista vacía.");
			return;
		}

		DetalleDepositoControlador detalleDepositoControlador = new DetalleDepositoControlador();
		boolean pedidoConfirmado = true;

		for (DetallePedido detalle : listaDetalle) {
			int idProducto = detalle.getIdProducto();
			int cantidadSolicitada = detalle.getCantidad();

			if (!detalleDepositoControlador.existeProducto(idProducto, cantidadSolicitada)) {
				pedidoConfirmado = false;
				JOptionPane.showMessageDialog(null,
						"Error. El producto con ID " + idProducto + " no está disponible en cantidad suficiente.");
				break;
			}
		}

		if (pedidoConfirmado) {
			if (fechaEntrega == null) {
				JOptionPane.showMessageDialog(null, "Error. No se ingresó la fecha de entrega.");
				return;
			}

			LocalDate fechaActual = LocalDate.now();
			if (!fechaEntrega.isAfter(fechaActual.plusDays(2))) {
				JOptionPane.showMessageDialog(null,
						"Error. La fecha de entrega debe ser al menos 2 días posterior a la fecha actual.");
				return;
			}

			PedidoControlador pedidoControlador = new PedidoControlador();
			Pedido nuevoPedido = new Pedido(fechaEntrega);
			pedidoControlador.addPedido(nuevoPedido);
			int codigoPedido = nuevoPedido.getCodigoPedido();
			pedidoControlador.actualizarEstadoPedido(codigoPedido, "Confirmado");
			JOptionPane.showMessageDialog(null, "Solicitud de Pedido con éxito.");
		} else {
			JOptionPane.showMessageDialog(null,
					"Error. No fue confirmado el pedido. Al menos uno de los productos solicitados no está disponible.");
		}
	}

}
