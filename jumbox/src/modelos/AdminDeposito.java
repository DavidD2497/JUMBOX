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

	public void confirmarSolicitudDePedido(LinkedList<DetallePedido> listaDetalle) {
		if (listaDetalle.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay detalles de pedido para confirmar.");
			return;
		}

		DetalleDepositoControlador detalleDepositoControlador = new DetalleDepositoControlador();
		boolean pedidoConfirmado = true;

		for (DetallePedido detalle : listaDetalle) {
			int idProducto = detalle.getIdProducto();
			int cantidadSolicitada = detalle.getCantidad();

			if (!detalleDepositoControlador.existeProducto(idProducto, cantidadSolicitada)) {
				pedidoConfirmado = false;
				JOptionPane.showMessageDialog(null, "El producto: " + idProducto + " no esta disponible");
				break;
			}
		}

		if (pedidoConfirmado) {
			PedidoControlador pedidoControlador = new PedidoControlador();
			Pedido nuevoPedido = new Pedido(LocalDate.now());
			pedidoControlador.addPedido(nuevoPedido);
			int codigoPedido = nuevoPedido.getCodigoPedido();
			pedidoControlador.actualizarEstadoPedido(codigoPedido, "Confirmado");
			JOptionPane.showMessageDialog(null,
					"El pedido fue confirmado. Todos los productos están disponibles");
		} else {
			JOptionPane.showMessageDialog(null,
					"No fue confirmado el pedido. Al menos uno de los productos solicitados no está disponible");
		}
	}

}
