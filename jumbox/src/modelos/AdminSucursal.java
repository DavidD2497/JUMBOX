package modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import controladores.DescuentoControlador;
import controladores.InventarioSucursalControlador;

public class AdminSucursal extends Empleado {
	private int idAdminSuc;
	private Producto producto;
	private Descuento descuento;
	private LinkedList<Descuento> descuentos = new LinkedList<>();

	public AdminSucursal(String nombre, String email, String contraseña, int idAdminSuc) {
		super(nombre, email, contraseña);
		this.idAdminSuc = idAdminSuc;
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
