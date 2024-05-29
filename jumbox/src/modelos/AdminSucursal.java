package modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import controladores.DetalleInventarioControlador;
import controladores.DetallePedidoControlador;
import controladores.PedidoControlador;
import controladores.ProductoControlador;

public class AdminSucursal extends Empleado {
	int idProducto;
	int cantidadEntrada;
	private String tipo;
	private Producto producto;
	private Descuento descuento;
	private LinkedList<Descuento> descuentos = new LinkedList<>();

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

		if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, idProducto)) {
			// JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el
			// inventario de la sucursal.");
			return false;
		}

		int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, idProducto);

		int cantidadTotal = cantidadDisponible + cantidadEntrada;
		detalleInventarioControlador.actualizarCantidadProducto(idInventarioSucursal, idProducto, cantidadTotal);
		// JOptionPane.showMessageDialog(null, "Entrada de " + cantidadEntrada + "
		// unidades al producto "
		// +detalleInventarioControlador.getNombreProducto(idProducto) + " registrada
		// con éxito.");
		return true;

	}

	public static boolean solicitarPedido(LinkedList<DetallePedido> listaDetalle, LocalDate fechaEntrega) {
		ProductoControlador productoControlador = new ProductoControlador();
		if (listaDetalle.isEmpty() || fechaEntrega == null) {
			JOptionPane.showMessageDialog(null, "Complete todos los datos para hacer el pedido");
			return false;
		}

		for (DetallePedido detalle : listaDetalle) {
			if (productoControlador.getProductoById(detalle.getIdProducto()) == null) {
				JOptionPane.showMessageDialog(null, "El producto con ID " + detalle.getIdProducto() + " no existe.");

				return false;
			}

		}

		if (fechaEntrega.isBefore(LocalDate.now().plusDays(2))) {
			JOptionPane.showMessageDialog(null, "La fecha ingresada debe ser posterior a la fecha actual");
			return false;
		}
		PedidoControlador pedidoControlador = new PedidoControlador();
		Pedido nuevoPedido = new Pedido(fechaEntrega);
		pedidoControlador.addPedido(nuevoPedido);int idPedido= pedidoControlador.obtenerUltimoIdPedido();
		DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();
		
		for (DetallePedido detalle : listaDetalle) {
			detalle.setIdPedido(idPedido);
			detallePedidoControlador.addDetallePedido(detalle);
			
		}
		JOptionPane.showMessageDialog(null, "Pedido creado correctamente");
		return true;

	}

	public void creaDescuento() {

	}

	public void generarInforme() {

	}

	public String crearDescuentoVencimiento(Producto productoa) {
		LocalDate fechaActual = LocalDate.now();
		long diasHastaVencimiento = productoa.getFechaVencimiento().until(fechaActual, ChronoUnit.DAYS);

		if (diasHastaVencimiento <= 14) {
			int cantDescuento;
			do {
				try {
					cantDescuento = Integer.parseInt(
							JOptionPane.showInputDialog("Ingrese el porcentaje de descuento (entre 5% y 95%)"));
					if (cantDescuento > 95 || cantDescuento < 5) {
						JOptionPane.showMessageDialog(null, "El porcentaje de descuento debe estar entre 5% y 95%");
					} else {
						Descuento desc = new Descuento(1, cantDescuento);

						DescuentoControlador descuentoControlador = new DescuentoControlador();
						descuentoControlador.addDescuento(desc);

						double precioDescuentoVencimiento = this.producto.getPrecio()
								* (1 - (desc.getPorcentajeDesc() / 100.0));
						this.producto.setPrecio(precioDescuentoVencimiento);

						descuentos.add(desc);

						JOptionPane.showMessageDialog(null,
								"Se ha aplicado un descuento al producto " + this.producto.getNombreProducto()
										+ " por su fecha de vencimiento que está cerca de la actual");
						return "Se aplicó el descuento";
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Ingrese un valor válido");
					cantDescuento = -1;
				}
			} while (cantDescuento < 95 && cantDescuento > 5);
		} else {
			JOptionPane.showMessageDialog(null, "El producto " + this.producto.getNombreProducto()
					+ " no cumple con los requisitos de tiempo para aplicar un descuento en este momento");
			return "No cumple con los requisitos";
		}
		return "Error al aplicar el descuento";
	}

	public String editarDescuento(int indiceDescuento) {
		if (indiceDescuento < 0 || indiceDescuento >= descuentos.size()) {
			JOptionPane.showMessageDialog(null, "Porcentaje inválido");
			return "El descuento no es válido";
		}

		Descuento descuento = descuentos.get(indiceDescuento);
		int nuevoPorcentaje;
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

	public void mostrarDescuento() {
		if (descuentos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay descuentos disponibles.");
			return;
		}

		String mensaje = "Descuentos aplicados:\n";
		for (Descuento descuento : descuentos) {
			mensaje += "Porcentaje: " + descuento.getPorcentajeDesc() + "%\n";
		}

		JOptionPane.showMessageDialog(null, mensaje, "Lista de Descuentos", JOptionPane.PLAIN_MESSAGE);

	}

	public String eliminarDescuento() {
		if (descuentos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay descuentos disponibles para eliminar");
			return "No hay descuentos disponibles para eliminar";
		}

		String indiceEliminar;
		do {
			indiceEliminar = JOptionPane.showInputDialog("Ingrese el índice del descuento que desea eliminar");
			try {
				int indiceDescuento = Integer.parseInt(indiceEliminar);
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
