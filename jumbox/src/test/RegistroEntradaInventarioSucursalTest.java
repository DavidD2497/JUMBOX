package test;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modelos.AdminSucursal;
import modelos.DetalleInventario;
import controladores.DetalleInventarioControlador;

public class RegistroEntradaInventarioSucursalTest {



	   @Test
	    public void RegistroEntradaCorrecto() {
	        DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
	        boolean flag = false;

	       
	            if (AdminSucursal.registroEntradaProducto(2, 2, 20)) {
	                flag = true;
	              
	            
	        }

	        assertEquals(true, flag);
	        
		
	    }
	}

