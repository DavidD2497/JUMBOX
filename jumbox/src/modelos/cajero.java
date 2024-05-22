package modelos;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class cajero extends empleado {
    private int idCajero;
    private inventarioSucursal inventarioSucursal;

    public cajero(String nombre, String contraseña, int idCajero, inventarioSucursal inventarioSucursal) {
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
        LinkedList<detalleInventario> listaInventario = inventarioSucursal.getListaInventario();
        for (detalleInventario detalle : listaInventario) {
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


