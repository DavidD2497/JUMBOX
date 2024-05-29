package modelos;

import java.util.List;

import javax.swing.JOptionPane;
import controladores.DetalleVentaControlador;
import controladores.DetalleInventarioControlador;
import controladores.VentaControlador;

public class Cajero extends Empleado {
	private String tipo;

	public Cajero(String nombre, String email, String contraseña) {
		super(nombre, email, contraseña);
		this.tipo = "Cajero";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static boolean registrarSalidaProductoInventarioSuc(int idInventarioSucursal, int idProducto,
			int cantidadSalida) {
		if (cantidadSalida <= 0) {
			// JOptionPane.showMessageDialog(null, "La cantidad de salida debe ser mayor que
			// cero.");
			return false;
		}

		DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();

		if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, idProducto)) {
			// JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el
			// inventario de la sucursal.");
			return false;
		}

		int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, idProducto);

		if (cantidadDisponible >= cantidadSalida) {
			int cantidadTotal = cantidadDisponible - cantidadSalida;
			detalleInventarioControlador.actualizarCantidadProducto(idInventarioSucursal, idProducto, cantidadTotal);
			// JOptionPane.showMessageDialog(null, "Salida de " + cantidadSalida + "
			// unidades del producto con ID: " + idProducto + " registrada con éxito.");
			return true;
		} else {
			// JOptionPane.showMessageDialog(null, "No hay suficiente inventario para sacar
			// " + cantidadSalida + " unidades del producto con ID: " + idProducto);
			return false;
		}
	}

	public static boolean registrarVenta(int idInventarioSucursal, List<DetalleVenta> detallesVenta, String tipoPago) {

	    if (idInventarioSucursal == 0 || detallesVenta.isEmpty() || tipoPago.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados.");
	        return false;
	    }

	    DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
	    boolean todosDisponibles = true;

	    for (DetalleVenta detalle : detallesVenta) {
	        if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, detalle.getIdProducto())) {
	            todosDisponibles = false;
	            JOptionPane.showMessageDialog(null,
	                    "No se puede registrar la venta porque uno o más productos no están disponibles");
	            break;
	        } else {
	            int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal,
	                    detalle.getIdProducto());
	            if (cantidadDisponible < detalle.getCantidad()) {
	                todosDisponibles = false;
	                JOptionPane.showMessageDialog(null,
	                        "No se puede registrar la venta porque no están disponibles en la cantidad requerida en el inventario de la sucursal.");
	                break;
	            }
	        }
	    }

	    if (todosDisponibles) {
	        double montoTotal = 0.0;
	        DetalleVentaControlador detalleVentaControlador = new DetalleVentaControlador();
	        for (DetalleVenta detalle : detallesVenta) {
	            montoTotal += (detalle.getMonto() * detalle.getCantidad());
	            int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, detalle.getIdProducto());
	            int cantidadTotal = cantidadDisponible - detalle.getCantidad();
	            detalleInventarioControlador.actualizarCantidadProducto(idInventarioSucursal, detalle.getIdProducto(),cantidadTotal);
	            detalleVentaControlador.addDetalleVenta(detalle);
	        }

	        Venta venta = new Venta(montoTotal, tipoPago);
	        VentaControlador ventaControlador = new VentaControlador();
	        ventaControlador.addVenta(venta);

	        JOptionPane.showMessageDialog(null, "Venta registrada con éxito. Monto total: " + montoTotal);
	        return true;
	    } else {
	        return false;
	    }
	}
}
