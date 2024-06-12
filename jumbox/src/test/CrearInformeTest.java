package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import controladores.*;
import modelos.*;

public class CrearInformeTest {

    private InformeControlador informeControlador;
    private PedidoControlador pedidoControlador;
    private VentaControlador ventaControlador;
    private EntradaInventarioControlador entradaControlador;
    private DetalleInformeControlador detalleControlador;
    private DetallePedidoControlador detPedidoControlador;

    @Before
    public void setUp() {
        informeControlador = new InformeControlador();
        pedidoControlador = new PedidoControlador();
        ventaControlador = new VentaControlador();
        entradaControlador = new EntradaInventarioControlador();
        detalleControlador = new DetalleInformeControlador();
    }

    @Test
    public void informeCreadoCorrectamente() {
        // Crear y agregar el informe
       
        
    	boolean resultado = AdminSucursal.crearInforme();

        assertEquals(true,resultado);
    }
}