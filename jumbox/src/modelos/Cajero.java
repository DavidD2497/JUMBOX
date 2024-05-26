package modelos;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import controladores.VentaControlador;
import controladores.DetalleVentaControlador;
import controladores.InventarioSucursalControlador;
import controladores.DetalleInventarioControlador;

public class Cajero extends Empleado {
    private int idCajero;
    private InventarioSucursal inventarioSucursal;
    private LinkedList<Venta> listaVentas = new LinkedList<>();

    public Cajero(String nombre, String email, String contraseña, int idCajero) {
        super(nombre, email, contraseña);
        this.idCajero = idCajero;
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
    
    public int getId() {
        return idCajero;
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

        DetalleVentaControlador detalleVentaControlador = new DetalleVentaControlador();
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



