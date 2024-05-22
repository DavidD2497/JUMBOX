package modelos;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class cajero extends empleado {
    private int idCajero;
<<<<<<< Updated upstream:jumbox/src/modelos/cajero.java
    private inventarioSucursal inventarioSucursal;

    public cajero(String nombre, String contraseña, int idCajero, inventarioSucursal inventarioSucursal) {
=======
    private InventarioSucursal inventarioSucursal;
    private LinkedList<Venta> listaVentas = new LinkedList<>();
    

    public Cajero(String nombre, String contraseña, int idCajero) {
>>>>>>> Stashed changes:jumbox/src/modelos/Cajero.java
        super(nombre, contraseña);
        this.idCajero = idCajero;
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
    
    public void registrarVenta(LinkedList<DetalleVenta> detalles, String tipoPago) {
        double montoTotal = 0;

        for (DetalleVenta detalleVenta : detalles) {
            int idProducto = detalleVenta.getIdProducto();
            int cantidadVenta = detalleVenta.getCantidad();
            LinkedList<DetalleInventario> listaInventario = inventarioSucursal.getListaInventario();

            for (DetalleInventario detalleInventario : listaInventario) {
                if (detalleInventario.getProducto().getIdProducto() == idProducto) {
                    if (detalleInventario.getCantidad() >= cantidadVenta) {
                        detalleInventario.setCantidad(detalleInventario.getCantidad() - cantidadVenta);
                        montoTotal += detalleVenta.getMonto();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay suficiente inventario para el producto: " + detalleInventario.getProducto().getNombreProducto());
                        return;
                    }
                }
            }
        }

        int idVenta = listaVentas.size() + 1;
        Venta nuevaVenta = new Venta(idVenta, montoTotal, tipoPago, detalles);
        listaVentas.add(nuevaVenta);

        JOptionPane.showMessageDialog(null, "Venta registrada con éxito. Monto total: " + montoTotal);
    }
}


