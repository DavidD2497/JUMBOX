package modelos;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Cajero extends Empleado {
    private int idCajero;
    private InventarioSucursal inventarioSucursal;
    

    public Cajero(String nombre, String contraseña, int idCajero, InventarioSucursal inventarioSucursal) {
        super(nombre, contraseña);
        this.idCajero = idCajero;
        this.inventarioSucursal = inventarioSucursal;
    }

    public int getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(int idCajero) {
        this.idCajero = idCajero;
    }

    public void registroSalidaInventario(int idProducto, int cantidadSalida) {
        LinkedList<DetalleInventario> listaInventario = inventarioSucursal.getListaInventario();
        for (DetalleInventario detalle : listaInventario) {
            if (detalle.getProducto().getIdProducto() == idProducto) {
                if (detalle.getCantidad() >= cantidadSalida) {
                    detalle.setCantidad(detalle.getCantidad() - cantidadSalida);
                    JOptionPane.showMessageDialog(null, "Se ha registrado la salida de " + cantidadSalida + " unidad/es del producto: " + detalle.getProducto().getNombreProducto());
                } else {
                    JOptionPane.showMessageDialog(null, "No hay suficiente inventario para el producto: " + detalle.getProducto().getNombreProducto());
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Producto no encontrado en el inventario.");
    }
}


