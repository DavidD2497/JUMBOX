package modelos;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import controladores.VentaControlador;
import controladores.InventarioSucursalControlador;

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



	public void registrarSalidaProducto(int idProducto, int cantidadSalida) {
        InventarioSucursalControlador inventarioSucursalControlador = new InventarioSucursalControlador();

        int cantidadDisponible = inventarioSucursalControlador.getCantidadDisponible(idProducto);

        if (cantidadDisponible >= cantidadSalida) {
        	int cantidadTotal = cantidadDisponible - cantidadSalida;
            inventarioSucursalControlador.actualizarCantidadProducto(idProducto, cantidadTotal);
            JOptionPane.showMessageDialog(null, "Salida de " + cantidadSalida + " unidades del producto con ID: " + idProducto + " registrada con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "No hay suficiente inventario para sacar " + cantidadSalida + " unidades del producto con ID: " + idProducto);
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



