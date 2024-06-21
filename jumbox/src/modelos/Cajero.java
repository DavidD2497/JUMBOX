package modelos;

import java.time.LocalDate;
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

    public static String registrarSalidaProductoInventarioSuc(int idInventarioSucursal, int idProducto, int cantidadSalida) {
        if (cantidadSalida <= 0) {
            return "No puedes registrar salidas menores a 0";
        }

        DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();

        if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, idProducto)) {
            return "No existe ese producto en esta sucursal";
        }

        int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, idProducto);

        if (cantidadDisponible >= cantidadSalida) {
            int cantidadTotal = cantidadDisponible - cantidadSalida;
            detalleInventarioControlador.actualizarCantidadProducto(idInventarioSucursal, idProducto, cantidadTotal);
            return "Salida registrada correctamente";
        } else {
            return "No hay suficiente cantidad, la cantidad total actual es " + cantidadDisponible;
        }
    }

    public static String registrarVenta(int idInventarioSucursal, List<DetalleVenta> detallesVenta, String tipoPago) {
        if (idInventarioSucursal == 0 || detallesVenta.isEmpty() || tipoPago.isEmpty()) {
            return "Todos los campos deben ser completados.";
        }

        DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
        boolean todosDisponibles = true;

        for (DetalleVenta detalle : detallesVenta) {
            if (detalle.getCantidad() <= 0) {
                todosDisponibles = false;
                return "La cantidad de cada producto debe ser mayor que cero.";
            }
            if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, detalle.getIdProducto())) {
                todosDisponibles = false;
                return "No se puede registrar la venta porque no existe el producto en esta sucursal";
            } else {
                int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, detalle.getIdProducto());
                if (cantidadDisponible < detalle.getCantidad()) {
                    todosDisponibles = false;
                    return "No se puede registrar la venta porque no están disponibles la cantidad requerida en el inventario de la sucursal, la cantidad total disponible es " + detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, detalle.getIdProducto());
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
            LocalDate fechaVenta = LocalDate.now();
            Venta venta = new Venta(montoTotal, tipoPago, fechaVenta);
            VentaControlador ventaControlador = new VentaControlador();
            ventaControlador.addVenta(venta);
            


            for (DetalleVenta detalle : detallesVenta) {
                detalleVentaControlador.addDetalleVenta(detalle);
            }

            return "Venta registrada con éxito";
        } else {
            return "No todos los productos estan disponibles en el inventario";
        }
    }

}

