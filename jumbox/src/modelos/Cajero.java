package modelos;

import javax.swing.JOptionPane;
import controladores.VentaControlador;
import controladores.DetalleInventarioControlador;

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



	public static boolean registrarSalidaProductoInventarioSuc(int idInventarioSucursal, int idProducto, int cantidadSalida) {
	    if (cantidadSalida <= 0) {
	        //JOptionPane.showMessageDialog(null, "La cantidad de salida debe ser mayor que cero.");
	        return false;
	    }

	    DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();

	    if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, idProducto)) {
	        //JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el inventario de la sucursal.");
	        return false;
	    }

	    int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, idProducto);

	    if (cantidadDisponible >= cantidadSalida) {
	        int cantidadTotal = cantidadDisponible - cantidadSalida;
	        detalleInventarioControlador.actualizarCantidadProducto(idInventarioSucursal, idProducto, cantidadTotal);
	        //JOptionPane.showMessageDialog(null, "Salida de " + cantidadSalida + " unidades del producto con ID: " + idProducto + " registrada con éxito.");
	        return true;
	    } else {
	        //JOptionPane.showMessageDialog(null, "No hay suficiente inventario para sacar " + cantidadSalida + " unidades del producto con ID: " + idProducto);
	        return false;
	    }
	}

	
	
    
    public void registrarVenta(LinkedList<DetalleVenta> detalles, String tipoPago) {

        InventarioSucursalControlador inventarioSucursalControlador = new InventarioSucursalControlador();
        VentaControlador ventaControlador = new VentaControlador();

        double montoTotal = 0;
        boolean suficienteInventario = true;


        for (DetalleVenta detalleVenta : detalles) {
            int idProducto = detalleVenta.getIdProducto();
            int cantidadVenta = detalleVenta.getCantidad();
            int cantidadDisponible = inventarioSucursalControlador.getCantidadDisponible(idProducto);
            if (cantidadDisponible < cantidadVenta) {
                JOptionPane.showMessageDialog(null, "No hay suficiente inventario para el producto con ID: " + idProducto);
                suficienteInventario = false;
                break;
            }
        }


        if (suficienteInventario) {
            for (DetalleVenta detalleVenta : detalles) {
                montoTotal += detalleVenta.getMonto();
               
                inventarioSucursalControlador.actualizarCantidadProducto(detalleVenta.getIdProducto(), detalleVenta.getCantidad());
            }


            Venta nuevaVenta = new Venta(0, montoTotal, tipoPago, detalles);
            
            ventaControlador.addVenta(nuevaVenta);


            JOptionPane.showMessageDialog(null, "Venta registrada con éxito. Monto total: " + montoTotal);
        }
    }

}



