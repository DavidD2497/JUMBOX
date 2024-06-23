package modelos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import controladores.DetalleDepositoControlador;
import controladores.DetallePedidoControlador;
import controladores.PedidoControlador;

public class AdminDeposito extends Empleado {
	private String tipo;
<<<<<<< HEAD

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



	public static String registrarSalidaDepositoGeneral( int idProducto, int cantidadSalida) {
	    if (cantidadSalida <= 0) {
	        //JOptionPane.showMessageDialog(null, "La cantidad de salida debe ser mayor que cero.");
	        return "La cantidad de salida debe ser mayor que cero.";
	    }

	    DetalleDepositoControlador detalleDepositoControlador = new DetalleDepositoControlador();

	    if (!detalleDepositoControlador.existeProducto( idProducto)) {
	        //JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el inventario de la sucursal.");
	        return "no existe en el inventario de la sucursal";
	    }

	    int cantidadDisponible = detalleDepositoControlador.getCantidadDisponible( idProducto);

	    if (cantidadDisponible >= cantidadSalida) {
	        int cantidadTotal = cantidadDisponible - cantidadSalida;
	        detalleDepositoControlador.actualizarCantidadProducto( idProducto, cantidadTotal);
	        //OptionPane.showMessageDialog(null, "Salida de " + cantidadSalida + " unidades del producto con ID: " + idProducto + " registrada con éxito.");
	        return "registrada con éxito.";
	    } else {
	        //JOptionPane.showMessageDialog(null, "No hay suficiente inventario para sacar " + cantidadSalida + " unidades del producto con ID: " + idProducto);
	        return "No hay suficiente inventario para sacar unidades del producto";
	    }
	}
	
	public static boolean SolicitudDePedido(int idPedido) {
		PedidoControlador pedidoControlador = new PedidoControlador();
		Pedido pedido = pedidoControlador.getPedidoById(idPedido);
		DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();
		List<DetallePedido> detallesPedido = detallePedidoControlador.getDetallePedidoByIdPedido(idPedido);

=======

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

	public static String registrarSalidaDepositoGeneral( int idProducto, int cantidadSalida) {
	    if (cantidadSalida <= 0) {
	        //JOptionPane.showMessageDialog(null, "La cantidad de salida debe ser mayor que cero.");
	        return "La cantidad de salida debe ser mayor que cero.";
	    }

	    DetalleDepositoControlador detalleDepositoControlador = new DetalleDepositoControlador();

	    if (!detalleDepositoControlador.existeProducto( idProducto)) {
	        //JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el inventario de la sucursal.");
	        return "no existe en el inventario de la sucursal";
	    }

	    int cantidadDisponible = detalleDepositoControlador.getCantidadDisponible( idProducto);

	    if (cantidadDisponible >= cantidadSalida) {
	        int cantidadTotal = cantidadDisponible - cantidadSalida;
	        detalleDepositoControlador.actualizarCantidadProducto( idProducto, cantidadTotal);
	        //OptionPane.showMessageDialog(null, "Salida de " + cantidadSalida + " unidades del producto con ID: " + idProducto + " registrada con éxito.");
	        return "registrada con éxito.";
	    } else {
	        //JOptionPane.showMessageDialog(null, "No hay suficiente inventario para sacar " + cantidadSalida + " unidades del producto con ID: " + idProducto);
	        return "No hay suficiente inventario para sacar unidades del producto";
	    }
	}

	public static boolean registrarSalidaDepositoGeneral(int idDepositoGeneral, int idProducto, int cantidadSalida) {
	    if (cantidadSalida <= 0) {
	        //JOptionPane.showMessageDialog(null, "La cantidad de salida debe ser mayor que cero.");
	        return false;
	    }

	    DetalleDepositoControlador detalleDepositoControlador = new DetalleDepositoControlador();

	    if (!detalleDepositoControlador.existeProducto(idProducto)) {
	        //JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el inventario de la sucursal.");
	        return false;
	    }

	    int cantidadDisponible = detalleDepositoControlador.getCantidadDisponible( idProducto);

	    if (cantidadDisponible >= cantidadSalida) {
	        int cantidadTotal = cantidadDisponible - cantidadSalida;
	        detalleDepositoControlador.actualizarCantidadProducto(idProducto, cantidadTotal);
	        //OptionPane.showMessageDialog(null, "Salida de " + cantidadSalida + " unidades del producto con ID: " + idProducto + " registrada con éxito.");
	        return true;
	    } else {
	        //JOptionPane.showMessageDialog(null, "No hay suficiente inventario para sacar " + cantidadSalida + " unidades del producto con ID: " + idProducto);
	        return false;
	    }
	}
	
	public static boolean SolicitudDePedido(int idPedido) {
		PedidoControlador pedidoControlador = new PedidoControlador();
		Pedido pedido = pedidoControlador.getPedidoById(idPedido);
		DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();
		List<DetallePedido> detallesPedido = detallePedidoControlador.getDetallePedidoByIdPedido(idPedido);

>>>>>>> origin/vicky
		String mensaje = "Información del Pedido:\n";
		for (DetallePedido detalle : detallesPedido) {
			mensaje += "ID Producto: " + detalle.getIdProducto() + "\n";
			mensaje += "Cantidad: " + detalle.getCantidad() + "\n";
			mensaje += "ID Pedido: " + pedido.getCodigoPedido() + "\n";
		}
		//JOptionPane.showMessageDialog(null, mensaje);

		int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar este pedido?", "Confirmar pedido", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sí", "No" }, null);

		if (respuesta == JOptionPane.YES_OPTION) {
			
			//JOptionPane.showMessageDialog(null, "El pedido ha sido aceptado");
			return true;
		} else {
			pedidoControlador.deletePedido(idPedido);
			detallePedidoControlador.deleteDetallesByIdPedido(idPedido);
			//JOptionPane.showMessageDialog(null, "El pedido ha sido rechazado y eliminado");
			return false;
		}

	}
<<<<<<< HEAD
	
    public static boolean definirFechaEntrega(int idPedido, LocalDate nuevaFechaEntrega) {
        if (nuevaFechaEntrega == null) {
            //JOptionPane.showMessageDialog(null, "La nueva fecha de entrega no puede ser nula.");
            return false;
        }

        LocalDate hoy = LocalDate.now();
        if (nuevaFechaEntrega.isBefore(hoy)) {
            //JOptionPane.showMessageDialog(null, "La nueva fecha de entrega no puede ser anterior a la fecha actual.");
            return false;
        }

        PedidoControlador pedidoControlador = new PedidoControlador();
        Pedido pedido = pedidoControlador.getPedidoById(idPedido);

        if (pedido == null) {
            //JOptionPane.showMessageDialog(null, "El pedido con ID: " + idPedido + " no existe.");
            return false;
        }

        pedidoControlador.actualizarFechaEntrega(idPedido, nuevaFechaEntrega);
        //JOptionPane.showMessageDialog(null, "Fecha de entrega actualizada exitosamente.");
        return true;
    }
	
=======
	
	 public static boolean definirFechaEntrega(int idPedido, LocalDate nuevaFechaEntrega) {
	        if (nuevaFechaEntrega == null) {
	            //JOptionPane.showMessageDialog(null, "La nueva fecha de entrega no puede ser nula.");
	            return false;
	        }

	        LocalDate hoy = LocalDate.now();
	        if (nuevaFechaEntrega.isBefore(hoy)) {
	            //JOptionPane.showMessageDialog(null, "La nueva fecha de entrega no puede ser anterior a la fecha actual.");
	            return false;
	        }

	        PedidoControlador pedidoControlador = new PedidoControlador();
	        Pedido pedido = pedidoControlador.getPedidoById(idPedido);

	        if (pedido == null) {
	            //JOptionPane.showMessageDialog(null, "El pedido con ID: " + idPedido + " no existe.");
	            return false;
	        }

	        pedidoControlador.actualizarFechaEntrega(idPedido, nuevaFechaEntrega);
	        //JOptionPane.showMessageDialog(null, "Fecha de entrega actualizada exitosamente.");
	        return true;
	    }
>>>>>>> origin/vicky
	
}
