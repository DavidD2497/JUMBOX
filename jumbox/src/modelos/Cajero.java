package modelos;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Cajero extends Empleado {
    private int idCajero;
    private InventarioSucursal inventarioSucursal;
    private LinkedList<Venta> listaVentas = new LinkedList<>();

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

    public LinkedList<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(LinkedList<Venta> listaVentas) {
        this.listaVentas = listaVentas;
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
                break;
            }
        }
    }
    

    public void registrarVenta(LinkedList<DetalleVenta> detalles, String tipoPago) {
        double montoTotal = 0;
        boolean suficienteInventario = true;


        for (DetalleVenta detalleVenta : detalles) {
            int idProducto = detalleVenta.getIdProducto();
            int cantidadVenta = detalleVenta.getCantidad();
            LinkedList<DetalleInventario> listaInventario = inventarioSucursal.getListaInventario();

            for (DetalleInventario detalleInventario : listaInventario) {
                if (detalleInventario.getProducto().getIdProducto() == idProducto) {
                    if (detalleInventario.getCantidad() < cantidadVenta) {
                        JOptionPane.showMessageDialog(null, "No hay suficiente inventario para el producto: " + detalleInventario.getProducto().getNombreProducto());
                        suficienteInventario = false;
                        break;
                    }
                }
            }
        }

        if (suficienteInventario) {
            for (DetalleVenta detalleVenta : detalles) {
                int idProducto = detalleVenta.getIdProducto();
                int cantidadVenta = detalleVenta.getCantidad();
                LinkedList<DetalleInventario> listaInventario = inventarioSucursal.getListaInventario();

                for (DetalleInventario detalleInventario : listaInventario) {
                    if (detalleInventario.getProducto().getIdProducto() == idProducto) {
                        detalleInventario.setCantidad(detalleInventario.getCantidad() - cantidadVenta);
                        montoTotal += detalleVenta.getMonto();
                        break;
                    }
                }
            }

            int idVenta = listaVentas.size() + 1;
            Venta nuevaVenta = new Venta(idVenta, montoTotal, tipoPago, detalles);
            listaVentas.add(nuevaVenta);

            JOptionPane.showMessageDialog(null, "Venta registrada con éxito. Monto total: " + montoTotal);
        }
        
    }

}



