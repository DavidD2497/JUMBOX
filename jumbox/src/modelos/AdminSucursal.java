package modelos;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import controladores.DetalleInventarioControlador;
import controladores.DetallePedidoControlador;
import controladores.PedidoControlador;
import controladores.ProductoControlador;
import controladores.InformeControlador;
import controladores.VentaControlador;
import controladores.DetalleInformeControlador;
import controladores.EntradaInventarioControlador;

public class AdminSucursal extends Empleado {
	int idProducto;
	int cantidadEntrada;
	private String tipo;
	private static PedidoControlador pedidoControlador = new PedidoControlador();
	private static VentaControlador ventaControlador = new VentaControlador();
	private static InformeControlador informeControlador = new InformeControlador();
	private static DetalleInformeControlador detalleControlador = new DetalleInformeControlador();
	private static EntradaInventarioControlador entradaControlador = new EntradaInventarioControlador();

	public AdminSucursal(String nombre, String email, String contraseña) {
		super(nombre, email, contraseña);
		this.tipo = "AdminSucursal";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static String registroEntradaProducto(int idInventarioSucursal, int idProducto, int cantidadEntrada) {
		LocalDate fechaEntrega = LocalDate.now();
		if (cantidadEntrada >= 1000) {
			// JOptionPane.showMessageDialog(null,"La cantidad de Entrada debe ser menor que
			// 1000.");

			return "Error";
		}

		if (cantidadEntrada <= 0) {
			// JOptionPane.showMessageDialog(null, "La cantidad de Entrada debe ser mayor
			// que cero.");
			return "Error";
		}

		DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
		EntradaInventarioControlador entradaControlador = new EntradaInventarioControlador();
		if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, idProducto)) {
			// JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el
			// inventario de la sucursal.");
			return "Error";
		}

		int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, idProducto);

		int cantidadTotal = cantidadDisponible + cantidadEntrada;
		EntradaInventario entrada = new EntradaInventario(idProducto, idInventarioSucursal, fechaEntrega,
				cantidadEntrada);
		entradaControlador.addEntradaInventario(entrada);
		detalleInventarioControlador.actualizarCantidadProducto(idInventarioSucursal, idProducto, cantidadTotal);
		// JOptionPane.showMessageDialog(null, "Entrada de " + cantidadEntrada + "
		// unidades al producto "
		// +detalleInventarioControlador.getNombreProducto(idProducto) + " registrada
		// con éxito.");
		return "correcto";

	}

	public static boolean solicitarPedido(LinkedList<DetallePedido> listaDetalle) {
		ProductoControlador productoControlador = new ProductoControlador();
		LocalDate fechaEntrega = LocalDate.now();
		JOptionPane.showMessageDialog(null, fechaEntrega);
		if (listaDetalle.isEmpty()) {
			// JOptionPane.showMessageDialog(null, "Complete todos los datos para hacer el
			// pedido");
			return false;
		}

		for (DetallePedido detalle : listaDetalle) {
			if (productoControlador.getProductoById(detalle.getIdProducto()) == null) {
				// JOptionPane.showMessageDialog(null, "El producto con ID " +
				// detalle.getIdProducto() + " no existe.");

				return false;
			}

		}

		// if (fechaEntrega.isBefore(LocalDate.now().plusDays(2))) {
		// JOptionPane.showMessageDialog(null, "La fecha ingresada debe ser posterior a
		// la fecha actual");
		// return false;
		// }
		PedidoControlador pedidoControlador = new PedidoControlador();
		Pedido nuevoPedido = new Pedido(fechaEntrega);
		pedidoControlador.addPedido(nuevoPedido);
		int idPedido = pedidoControlador.obtenerUltimoIdPedido();
		DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();

		for (DetallePedido detalle : listaDetalle) {
			detalle.setIdPedido(idPedido);
			detallePedidoControlador.addDetallePedido(detalle);

		}
		// JOptionPane.showMessageDialog(null, "Pedido creado correctamente");
		return true;

	}

	public static boolean crearInforme() {
		LocalDate fechaInforme = LocalDate.now();
		Informe informe = new Informe(fechaInforme);
		informeControlador.addInforme(informe);
		JOptionPane.showMessageDialog(null, fechaInforme);
		for (Pedido pedido : pedidoControlador.getAllPedidos()) {
			pedidoControlador.getPedidoById(j);
			JOptionPane.showMessageDialog(null, pedido.getFechaEntrega());

			if (pedido.getFechaEntrega().equals(fechaInforme)) {
				DetalleInforme detalle = new DetalleInforme(0, "Pedido", 0);
				detalle.setIdInforme(informeControlador.obtenerUltimoIdInforme());
				detalle.setIdTipo(pedido.getCodigoPedido());
				detalleControlador.addDetalleInforme(detalle);
			}
		}
		for (Venta venta : ventaControlador.getAllVentas()) {
			if (venta.getFechaVenta().equals(fechaInforme)) {
				DetalleInforme detalle = new DetalleInforme(0, "Venta", 0);
				detalle.setIdInforme(informeControlador.obtenerUltimoIdInforme());
				detalle.setIdTipo(venta.getIdVenta());
				detalleControlador.addDetalleInforme(detalle);
			}
		}
		for (EntradaInventario entrada : entradaControlador.getAllEntradasInventario()) {
			if (entrada.getFechaEntrada().equals(fechaInforme)) {
				DetalleInforme detalle = new DetalleInforme(0, "Entrada", 0);
				detalle.setIdInforme(informeControlador.obtenerUltimoIdInforme());
				detalle.setIdTipo(entrada.getIdEntrada());
				detalleControlador.addDetalleInforme(detalle);
			}
		}

		return true;
	}

	public static void mostrarPedido() {
		PedidoControlador pedidoControlador = new PedidoControlador();
		JOptionPane.showMessageDialog(null, pedidoControlador.getAllPedidos());
	}
}
