package modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class AdminSucursal extends Empleado {
	private int idAdminSuc;
	private Producto producto;
	private Descuento descuento;
	private LinkedList<Descuento> descuentos = new LinkedList<>();
	private InventarioSucursal inventarioSucursal;

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

	public void registroEntrada(int idProducto, int cantidadEntrada) {
	      LinkedList<DetalleInventario> listaInventario = inventarioSucursal.getListaInventario();
	        for (DetalleInventario detalle : listaInventario) {
	            if (detalle.getProducto().getIdProducto() == idProducto) {
	            		detalle.setCantidad(detalle.getCantidad() + cantidadEntrada);
	                    JOptionPane.showMessageDialog(null, "Se ha registrado la entrada de " + cantidadEntrada + " unidad/es del producto: " + detalle.getProducto().getNombreProducto());
	                break;
	            }
	        }
	    }
		
		
	

	public void CrearSolicitudPedido() {

		
		
		
	}
	
	public void MostrarSolicitudPedido() {

		
		
		
	}


	

	public String crearDescuentoVencimiento(Producto productoa) {
		LocalDate fechaActual = LocalDate.now();
		long diasHastaVencimiento = productoa.getFechaVencimiento().until(fechaActual, ChronoUnit.DAYS);

		if (diasHastaVencimiento <= 14) {
			int cantDescuento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el porcentaje de descuento"));
			do {
				boolean flag= true;
				do {
					
					try {
						cantDescuento = Integer.parseInt(
								JOptionPane.showInputDialog("Ingrese un porcentaje de descuento válido (entre 5% y 95%)"));
						if (cantDescuento > 95 || cantDescuento < 5) {
							JOptionPane.showMessageDialog(null, "El porcentaje de descuento debe estar entre 5% y 95%");
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Ingrese un valor válido");
						cantDescuento = -1;
						flag=false;
					}
				} while (flag==false);
			} while (cantDescuento < 95 && cantDescuento > 5);
			Descuento desc = new Descuento(1, cantDescuento);
			double precioDescuentoVencimiento = this.producto.getPrecio() * (1 - (desc.getPorcentajeDesc() / 100.0));
			this.producto.setPrecio(precioDescuentoVencimiento);
			descuentos.add(desc);

			JOptionPane.showMessageDialog(null, "Se ha aplicado un descuento al producto "
					+ this.producto.getNombreProducto() + " por su fecha de vencimiento que está cerca de la actual");
			return "Se aplicó el descuento";

		} else {
			JOptionPane.showMessageDialog(null, "El producto " + this.producto.getNombreProducto()
					+ " no cumple con los requisitos de tiempo para aplicar un descuento en este momento");
			return "No cumple con los requisitos";
		}
	}

	public String editarDescuento(int indiceDescuento) {
		if (indiceDescuento < 0 || indiceDescuento >= descuentos.size()) {
			JOptionPane.showMessageDialog(null, "Porcentaje inválido");
			return "El descuento no es válido";
		}

		Descuento descuento = descuentos.get(indiceDescuento);
		int nuevoPorcentaje = obtenerNuevoPorcentaje();
		if (nuevoPorcentaje == -1) {
			JOptionPane.showMessageDialog(null, "Ingrese correctamente el descuento");
			return "El porcentaje de descuento debe estar entre 5% y 95%";
		}

		descuento.setPorcentajeDesc(nuevoPorcentaje);
		JOptionPane.showMessageDialog(null, "El descuento fue editado correctamente");
		return "El descuento fue editado correctamente";
	}

	private int obtenerNuevoPorcentaje() {
		int nuevoPorcentaje;
		do {
			try {
				nuevoPorcentaje = Integer
						.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de descuento que quiere modificar"));
				if (nuevoPorcentaje < 5 || nuevoPorcentaje > 95) {
					return -1;
				}
			} catch (NumberFormatException e) {
				return -1;
			}
		} while (nuevoPorcentaje < 5 || nuevoPorcentaje > 95);
		return nuevoPorcentaje;
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

		String indiceEliminar = JOptionPane.showInputDialog("Ingrese el índice del descuento que desea eliminar");
		try {
			int indiceDescuento = Integer.parseInt(indiceEliminar);
			if (indiceDescuento < 0 || indiceDescuento >= descuentos.size()) {
				JOptionPane.showMessageDialog(null, "El descuento no es válido");
				return "El descuento no es válido";
			}

			descuentos.remove(indiceDescuento);
			JOptionPane.showMessageDialog(null, "El descuento fue eliminado correctamente");
			return "El descuento fue eliminado correctamente";
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingrese un número válido para el índice del descuento");
			return "Ingrese un número válido para el índice del descuento";
		}
	}

}
