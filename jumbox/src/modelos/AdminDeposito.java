package modelos;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import controladores.DetalleDepositoControlador;
import controladores.PedidoControlador;

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



	public static boolean registrarSalidaDepositoGeneral(int idDepositoGeneral, int idProducto, int cantidadSalida) {
	    if (cantidadSalida <= 0) {
	        //JOptionPane.showMessageDialog(null, "La cantidad de salida debe ser mayor que cero.");
	        return false;
	    }

	    DetalleDepositoControlador detalleDepositoControlador = new DetalleDepositoControlador();

	    if (!detalleDepositoControlador.existeProducto(idDepositoGeneral, idProducto)) {
	        //JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el inventario de la sucursal.");
	        return false;
	    }

	    int cantidadDisponible = detalleDepositoControlador.getCantidadDisponible(idDepositoGeneral, idProducto);

	    if (cantidadDisponible >= cantidadSalida) {
	        int cantidadTotal = cantidadDisponible - cantidadSalida;
	        detalleDepositoControlador.actualizarCantidadProducto(idDepositoGeneral, idProducto, cantidadTotal);
	        //OptionPane.showMessageDialog(null, "Salida de " + cantidadSalida + " unidades del producto con ID: " + idProducto + " registrada con éxito.");
	        return true;
	    } else {
	        //JOptionPane.showMessageDialog(null, "No hay suficiente inventario para sacar " + cantidadSalida + " unidades del producto con ID: " + idProducto);
	        return false;
	    }
	}
	
	public void confirmarSolicitudDePedido(LinkedList<DetallePedido> listaDetalle, LocalDate fechaEntrega) {
		if (listaDetalle.isEmpty()) {
			//JOptionPane.showMessageDialog(null, "Error. Se creó una lista vacía.");
			return;
		}

		DetalleDepositoControlador detalleDepositoControlador = new DetalleDepositoControlador();
		boolean pedidoConfirmado = true;

		for (DetallePedido detalle : listaDetalle) {
			int idProducto = detalle.getIdProducto();
			int cantidadSolicitada = detalle.getCantidad();

			if (!detalleDepositoControlador.existeProducto(idProducto, cantidadSolicitada)) {
				pedidoConfirmado = false;
				//JOptionPane.showMessageDialog(null,"Error. El producto con ID " + idProducto + " no está disponible en cantidad suficiente.");
				break;
			}
		}

		if (pedidoConfirmado) {
			if (fechaEntrega == null) {
				//JOptionPane.showMessageDialog(null, "Error. No se ingresó la fecha de entrega.");
				return;
			}

			LocalDate fechaActual = LocalDate.now();
			if (!fechaEntrega.isAfter(fechaActual.plusDays(2))) {
				//JOptionPane.showMessageDialog(null,"Error. La fecha de entrega debe ser al menos 2 días posterior a la fecha actual.");
				return;
			}

			PedidoControlador pedidoControlador = new PedidoControlador();
			Pedido nuevoPedido = new Pedido(fechaEntrega);
			pedidoControlador.addPedido(nuevoPedido);
			int codigoPedido = nuevoPedido.getCodigoPedido();
			pedidoControlador.actualizarEstadoPedido(codigoPedido, "Confirmado");
			//JOptionPane.showMessageDialog(null, "Solicitud de Pedido con éxito.");
		} else {
			//JOptionPane.showMessageDialog(null,"Error. No fue confirmado el pedido. Al menos uno de los productos solicitados no está disponible.");
		}
	}

}