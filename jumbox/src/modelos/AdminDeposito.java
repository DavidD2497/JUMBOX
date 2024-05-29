package modelos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import controladores.DetalleDepositoControlador;
import controladores.DetallePedidoControlador;
import controladores.PedidoControlador;
import modelos.Pedido;

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

	public boolean SolicitudDePedido(int idPedido) {
		PedidoControlador pedidoControlador = new PedidoControlador();
		Pedido pedido = pedidoControlador.getPedidoById(idPedido);
		DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();
		List<DetallePedido> detallesPedido = detallePedidoControlador.getDetallePedidoByIdPedido(idPedido);

		String mensaje = "Información del Pedido:\n";
		for (DetallePedido detalle : detallesPedido) {
			mensaje += "ID Producto: " + detalle.getIdProducto() + "\n";
			mensaje += "Cantidad: " + detalle.getCantidad() + "\n";
			mensaje += "ID Pedido: " + pedido.getCodigoPedido() + "\n";
		}
		JOptionPane.showMessageDialog(null, mensaje);

		int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar este pedido?", "Confirmar pedido",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sí", "No" }, null);

		if (respuesta == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "El pedido ha sido aceptado");
		} else {
			pedidoControlador.deletePedido(idPedido);
			detallePedidoControlador.deleteDetallesByIdPedido(idPedido);
			JOptionPane.showMessageDialog(null, "El pedido ha sido rechazado y eliminado");
			return false;
		}

		return true;
	}

}
