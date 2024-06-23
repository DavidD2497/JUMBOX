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

    public static boolean registrarSalidaProductoInventarioSuc(int idInventarioSucursal, int idProducto, int cantidadSalida) {
        if (cantidadSalida <= 0) {
            return false;
        }

        DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();

        if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, idProducto)) {
            return false;
        }

        int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, idProducto);

        if (cantidadDisponible >= cantidadSalida) {
            int cantidadTotal = cantidadDisponible - cantidadSalida;
            detalleInventarioControlador.actualizarCantidadProducto(idInventarioSucursal, idProducto, cantidadTotal);
            return true;
        } else {
            return false;
        }
    }

    public static boolean registrarVenta(int idInventarioSucursal, List<DetalleVenta> detallesVenta, String tipoPago) {
        if (idInventarioSucursal == 0 || detallesVenta.isEmpty() || tipoPago.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados.");
            return false;
        }

        DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
        boolean todosDisponibles = true;

        for (DetalleVenta detalle : detallesVenta) {
            if (detalle.getCantidad() <= 0) {
                todosDisponibles = false;
                //JOptionPane.showMessageDialog(null, "La cantidad de cada producto debe ser mayor que cero.");
                break;
            }
            if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, detalle.getIdProducto())) {
                todosDisponibles = false;
                //JOptionPane.showMessageDialog(null, "No se puede registrar la venta porque no existe el producto en esta sucursal");
                break;
            } else {
                int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, detalle.getIdProducto());
                if (cantidadDisponible < detalle.getCantidad()) {
                    todosDisponibles = false;
                    //JOptionPane.showMessageDialog(null, "No se puede registrar la venta porque no están disponibles la cantidad requerida en el inventario de la sucursal, la cantidad total disponible es " + detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, detalle.getIdProducto()));
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
                detalleInventarioControlador.actualizarCantidadProducto(idInventarioSucursal, detalle.getIdProducto(), cantidadTotal);
            }

            Venta venta = new Venta(montoTotal, tipoPago);
            VentaControlador ventaControlador = new VentaControlador();
            ventaControlador.addVenta(venta);
            


            for (DetalleVenta detalle : detallesVenta) {
                detalleVentaControlador.addDetalleVenta(detalle);
            }

            //JOptionPane.showMessageDialog(null, "Venta registrada con éxito. Monto total: " + montoTotal);
            return true;
        } else {
            return false;
        }
    }

}