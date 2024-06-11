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
	private static EntradaInventarioControlador entradaControlador= new EntradaInventarioControlador();
	
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

	public static boolean registroEntradaProducto(int idInventarioSucursal, int idProducto, int cantidadEntrada) {
		LocalDate fechaEntrega = LocalDate.now();
		if (cantidadEntrada >= 1000) {
			// JOptionPane.showMessageDialog(null,"La cantidad de Entrada debe ser menor que
			// 1000.");

			return false;
		}

		if (cantidadEntrada <= 0) {
			// JOptionPane.showMessageDialog(null, "La cantidad de Entrada debe ser mayor
			// que cero.");
			return false;
		}

		DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
		EntradaInventarioControlador entradaControlador = new EntradaInventarioControlador();
		if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, idProducto)) {
			// JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el
			// inventario de la sucursal.");
			return false;
		}

		int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, idProducto);

		int cantidadTotal = cantidadDisponible + cantidadEntrada;
		EntradaInventario entrada= new EntradaInventario(idProducto, idInventarioSucursal, fechaEntrega, cantidadEntrada);
		entradaControlador.addEntradaInventario(entrada);
		detalleInventarioControlador.actualizarCantidadProducto(idInventarioSucursal, idProducto, cantidadTotal);
		// JOptionPane.showMessageDialog(null, "Entrada de " + cantidadEntrada + "
		// unidades al producto "
		// +detalleInventarioControlador.getNombreProducto(idProducto) + " registrada
		// con éxito.");
		return true;

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

	public static void crearInforme() {
		LocalDate fechaInforme = LocalDate.now();
		Informe informe = new Informe(fechaInforme);
		informeControlador.addInforme(informe);
		for (int i = 0; i < pedidoControlador.getAllPedidos().size(); i++) {
			pedidoControlador.getPedidoById(i).getFechaEntrega().equals(informe.getFechaInforme());
			detalleControlador.addDetalleInforme(new DetalleInforme(informe.getIdInforme(), "Pedido", i));

		}
		for (int i = 0; i < ventaControlador.getAllVentas().size(); i++) {
			ventaControlador.getVentaById(i).getFechaVenta().equals(informe.getFechaInforme());
			detalleControlador.addDetalleInforme(new DetalleInforme(informe.getIdInforme(), "Venta", i));
		}
		for (int i = 0; i < entradaControlador.getAllEntradasInventario().size(); i++) {
			entradaControlador.getEntradaInventarioById(i).getFechaEntrada().equals(informe.getFechaInforme());
			detalleControlador.addDetalleInforme(new DetalleInforme(informe.getIdInforme(), "Entrada", i));
		}
	}

	public static void mostrarPedido() {
		PedidoControlador pedidoControlador = new PedidoControlador();
		JOptionPane.showMessageDialog(null, pedidoControlador.getAllPedidos());
	}
}
