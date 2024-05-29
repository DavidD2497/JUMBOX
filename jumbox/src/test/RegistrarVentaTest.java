package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modelos.Cajero;
import modelos.DetalleVenta;
import controladores.ProductoControlador;
import controladores.VentaControlador;



public class RegistrarVentaTest {

    @Test
    public void RegistrarVentaCorrecto() {
        boolean resultado = false;

        int idInventarioSucursal = 1;
        
        String tipoPago = "Efectivo";
        
        VentaControlador ventaControlador = new VentaControlador();
        ProductoControlador	productoControlador = new ProductoControlador();
        
        int idVenta = ventaControlador.obtenerUltimoIdVenta() + 1;

        List<DetalleVenta> detallesVenta = new ArrayList<>();
        
        detallesVenta.add(new DetalleVenta(1,idVenta,productoControlador.getPrecioProductoById(1), 2));
        detallesVenta.add(new DetalleVenta(5,idVenta,productoControlador.getPrecioProductoById(2), 2));


        resultado = Cajero.registrarVenta(idInventarioSucursal, detallesVenta, tipoPago);

        assertEquals(true, resultado);
    }

}
