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
        
        String tipoPago = "efectivo";
        
        VentaControlador ventaControlador = new VentaControlador();
        ProductoControlador	productoControlador = new ProductoControlador();
        
        int idVenta = ventaControlador.obtenerUltimoIdVenta() + 1;

        List<DetalleVenta> detallesVenta = new ArrayList<>();
        
        detallesVenta.add(new DetalleVenta(1,idVenta,productoControlador.getPrecioProductoById(1), 2));
        detallesVenta.add(new DetalleVenta(5,idVenta,productoControlador.getPrecioProductoById(5), 4));


        resultado = Cajero.registrarVenta(idInventarioSucursal, detallesVenta, tipoPago);

        assertEquals(true, resultado);
    }

    @Test
    public void RegistrarVentaCampoVacio() {
        boolean resultado = false;

        int idInventarioSucursal = 1;
        
        String tipoPago = "";
        
        VentaControlador ventaControlador = new VentaControlador();
        ProductoControlador	productoControlador = new ProductoControlador();
        
        int idVenta = ventaControlador.obtenerUltimoIdVenta() + 1;

        List<DetalleVenta> detallesVenta = new ArrayList<>();
        
        detallesVenta.add(new DetalleVenta(1,idVenta,productoControlador.getPrecioProductoById(1), 2));
        detallesVenta.add(new DetalleVenta(5,idVenta,productoControlador.getPrecioProductoById(5), 4));


        resultado = Cajero.registrarVenta(idInventarioSucursal, detallesVenta, tipoPago);

        assertEquals(false, resultado);
    }
    
    @Test
    public void RegistrarVentaProductoInexistenteEnSurcusal() {
        boolean resultado = false;

        int idInventarioSucursal = 1;
        
        String tipoPago = "efectivo";
        
        VentaControlador ventaControlador = new VentaControlador();
        ProductoControlador	productoControlador = new ProductoControlador();
        
        int idVenta = ventaControlador.obtenerUltimoIdVenta() + 1;

        List<DetalleVenta> detallesVenta = new ArrayList<>();
        
        detallesVenta.add(new DetalleVenta(1,idVenta,productoControlador.getPrecioProductoById(1), 2));
        detallesVenta.add(new DetalleVenta(4,idVenta,productoControlador.getPrecioProductoById(5), 4));


        resultado = Cajero.registrarVenta(idInventarioSucursal, detallesVenta, tipoPago);

        assertEquals(false, resultado);
    }
    
    @Test
    public void RegistrarVentaProductoCantidadNoDisponible() {
        boolean resultado = false;

        int idInventarioSucursal = 1;
        
        String tipoPago = "efectivo";
        
        VentaControlador ventaControlador = new VentaControlador();
        ProductoControlador	productoControlador = new ProductoControlador();
        
        int idVenta = ventaControlador.obtenerUltimoIdVenta() + 1;

        List<DetalleVenta> detallesVenta = new ArrayList<>();
        
        detallesVenta.add(new DetalleVenta(1,idVenta,productoControlador.getPrecioProductoById(1), 2));
        detallesVenta.add(new DetalleVenta(5,idVenta,productoControlador.getPrecioProductoById(5), 400));


        resultado = Cajero.registrarVenta(idInventarioSucursal, detallesVenta, tipoPago);

        assertEquals(false, resultado);
    }
    
    @Test
    public void RegistrarVentaProductoCantidadMenorOIgualACero() {
        boolean resultado = false;

        int idInventarioSucursal = 1;
        
        String tipoPago = "efectivo";
        
        VentaControlador ventaControlador = new VentaControlador();
        ProductoControlador	productoControlador = new ProductoControlador();
        
        int idVenta = ventaControlador.obtenerUltimoIdVenta() + 1;

        List<DetalleVenta> detallesVenta = new ArrayList<>();
        
        detallesVenta.add(new DetalleVenta(1,idVenta,productoControlador.getPrecioProductoById(1), 2));
        detallesVenta.add(new DetalleVenta(5,idVenta,productoControlador.getPrecioProductoById(5), 0));


        resultado = Cajero.registrarVenta(idInventarioSucursal, detallesVenta, tipoPago);

        assertEquals(false, resultado);
    }
    
    
}
