package modelos;

import java.sql.Date;
import java.time.LocalDate;
<<<<<<< HEAD
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
=======


import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import controladores.DescuentoControlador;
import controladores.InventarioSucursalControlador;
import controladores.ProductoControlador;
import controladores.DetalleInventarioControlador;
import controladores.EntradaInventarioControlador;

public class AdminSucursal extends Empleado {
	private int idAdminSuc;
	private Producto producto;
	private Descuento descuento;
	private static LinkedList<Descuento> descuentos = new LinkedList<>();
	private String tipo;
>>>>>>> origin/vicky

	public AdminSucursal(String nombre, String email, String contraseña) {
		super(nombre, email, contraseña);
		this.tipo = "AdminSucursal";
<<<<<<< HEAD
=======
	}

	public AdminSucursal() {
		super("", "", "");
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getIdAdminSuc() {
		return idAdminSuc;
>>>>>>> origin/vicky
	}

	public String getTipo() {
		return tipo;
	}

<<<<<<< HEAD
	public void setTipo(String tipo) {
		this.tipo = tipo;
=======
	public int getId() {
		return idAdminSuc;
>>>>>>> origin/vicky
	}

	public static String registroEntradaProducto(int idInventarioSucursal, int idProducto, int cantidadEntrada) {
		LocalDate fechaEntrega = LocalDate.now();
		if (cantidadEntrada >= 1000) {
			// JOptionPane.showMessageDialog(null,"La cantidad de Entrada debe ser menor que
			// 1000.");

			return "Error";
<<<<<<< HEAD
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
=======
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

	
	public void RegistroEntrada(int idProducto, int cantidadEntrada) {

		InventarioSucursalControlador inventarioSucursalControlador = new InventarioSucursalControlador();
		int cantidadDisponible = inventarioSucursalControlador.getCantidadDisponible(idProducto);
		int cantidadTotal = cantidadDisponible + cantidadEntrada;
		inventarioSucursalControlador.actualizarCantidadProducto(idProducto, cantidadTotal);
		JOptionPane.showMessageDialog(null, "Entrada de " + cantidadEntrada + " unidades del producto con ID: "
				+ idProducto + " registrada con éxito.");
	}

	public void SolicitarPedido() {

	}

	public void MostrarSolicitudPedido() {

	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public LinkedList<Descuento> getDescuentos() {
		return descuentos;
	}

	public String crearDescuentoVencimiento(Producto producto, int cantDescuento) {
	    LocalDate fechaActual = LocalDate.now();
	    long diasHastaVencimiento = producto.getFechaVencimiento().until(fechaActual, ChronoUnit.DAYS);

	    if (diasHastaVencimiento <= 14) {
	        try {
	            Descuento desc = new Descuento(cantDescuento, producto);

	            DescuentoControlador descuentoControlador = new DescuentoControlador();
	            descuentoControlador.addDescuento(desc); // Insertar descuento solo una vez

	            double precioDescuentoVencimiento = producto.getPrecio() * (1 - (desc.getPorcentajeDesc() / 100.0));
	            ProductoControlador productoControlador = new ProductoControlador();
	            productoControlador.updateProducto(producto.getIdProducto(), precioDescuentoVencimiento);

	            JOptionPane.showMessageDialog(null,
	                    "Se ha aplicado un descuento al producto " + producto.getNombreProducto()
	                            + " por su fecha de vencimiento que está cerca de la actual");

	            return "Se aplicó el descuento";
	        } catch (Exception e) {
	            return "Error al aplicar el descuento";
	        }
	    }
	    return "Error al aplicar el descuento";
	}

	public String editarDescuento(int indiceDescuento, int nuevoPorcentaje) {
		if (indiceDescuento < 0 || indiceDescuento >= descuentos.size()) {
			JOptionPane.showMessageDialog(null, "Porcentaje inválido");
			return "El descuento no es válido";
		}

		Descuento descuento = descuentos.get(indiceDescuento);
		do {
			try {
				nuevoPorcentaje = Integer.parseInt(
						JOptionPane.showInputDialog("Ingrese el nuevo porcentaje de descuento (entre 5% y 95%)"));
				if (nuevoPorcentaje < 5 || nuevoPorcentaje > 95) {
					JOptionPane.showMessageDialog(null, "El porcentaje de descuento debe estar entre 5% y 95%");
				} else {
					descuento.setPorcentajeDesc(nuevoPorcentaje);
					DescuentoControlador descuentoControlador = new DescuentoControlador();
					descuentoControlador.updateDescuento(descuento);

					JOptionPane.showMessageDialog(null, "El descuento fue editado correctamente");
					return "El descuento fue editado correctamente";
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingrese un valor válido");
				nuevoPorcentaje = -1;
			}
		} while (nuevoPorcentaje < 5 || nuevoPorcentaje > 95);

		return "Error al editar el descuento";
	}

	public void mostrarDescuento(String mensaje) {
		if (descuentos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay descuentos disponibles.");
			return;
		}

		mensaje = "Descuentos aplicados:\n";
		for (Descuento descuento : descuentos) {
			mensaje += "Porcentaje: " + descuento.getPorcentajeDesc() + "%\n";
		}
>>>>>>> origin/vicky

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

<<<<<<< HEAD
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

=======
	public String eliminarDescuento(int indiceEliminar) {
		if (descuentos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay descuentos disponibles para eliminar");
			return "No hay descuentos disponibles para eliminar";
		}

		do {
			indiceEliminar = Integer
					.parseInt(JOptionPane.showInputDialog("Ingrese el índice del descuento que desea eliminar"));
			try {
				int indiceDescuento = indiceEliminar;
				if (indiceDescuento < 0 || indiceDescuento >= descuentos.size()) {
					JOptionPane.showMessageDialog(null, "El descuento no es válido");
				} else {
					descuentos.remove(indiceDescuento);
					DescuentoControlador descuentoControlador = new DescuentoControlador();
					descuentoControlador.deleteDescuento(indiceDescuento);
					JOptionPane.showMessageDialog(null, "El descuento fue eliminado correctamente");
					return "El descuento fue eliminado correctamente";
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingrese un número válido para el índice del descuento");
			}
		} while (true);
>>>>>>> origin/vicky
	}

	public static boolean crearInforme() {
		LocalDate fechaInforme = LocalDate.now();
		Informe informe = new Informe(fechaInforme);
		informeControlador.addInforme(informe);
		JOptionPane.showMessageDialog(null, fechaInforme);
		for (Pedido pedido : pedidoControlador.getAllPedidos()) {
			
		

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
