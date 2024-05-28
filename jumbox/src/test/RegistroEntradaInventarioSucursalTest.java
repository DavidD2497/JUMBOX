package test;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modelos.AdminSucursal;
import modelos.DetalleInventario;
import controladores.DetalleInventarioControlador;

public class RegistroEntradaInventarioSucursalTest {



	   @Test
	    public boolean RegistroEntradaCorrecto() {
	        DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
	        boolean flag = false;

	        for (DetalleInventario admin : detalleInventarioControlador.getAllDetalleInventarios()) {
	            if (AdminSucursal.registroEntradaProducto(1, 1, 20)) {
	                flag = true;
	                break;
	            }
	        }

	        assertEquals(true, flag);
	        
			return flag;
	    }
	}

