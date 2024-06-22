package modelos;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import controladores.DescuentoControlador;
import controladores.InventarioSucursalControlador;
import controladores.ProductoControlador;

public class AdminSucursal extends Empleado {
	private int idAdminSuc;
	private Producto producto;
	private Descuento descuento;
	private static LinkedList<Descuento> descuentos = new LinkedList<>();
	private String tipo;

	public AdminSucursal(String nombre, String email, String contraseña) {
		super(nombre, email, contraseña);
		this.tipo = "AdminSucursal";
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
	}

	public void setIdAdminSuc(int idAdminSuc) {
		this.idAdminSuc = idAdminSuc;
	}

	public int getId() {
		return idAdminSuc;
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
				descuentoControlador.addDescuento(desc);

				double precioDescuentoVencimiento = producto.getPrecio() * (1 - (desc.getPorcentajeDesc() / 100.0));
				ProductoControlador productoControlador = new ProductoControlador();
				productoControlador.updateProducto(producto.getIdProducto(), precioDescuentoVencimiento);

				descuentoControlador.addDescuento(desc);

				JOptionPane.showMessageDialog(null,
						"Se ha aplicado un descuento al producto " + this.producto.getNombreProducto()
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

		JOptionPane.showMessageDialog(null, mensaje, "Lista de Descuentos", JOptionPane.PLAIN_MESSAGE);

	}

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
	}

}
