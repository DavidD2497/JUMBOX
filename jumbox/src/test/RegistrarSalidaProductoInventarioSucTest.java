package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import modelos.Cajero;


public class RegistrarSalidaProductoInventarioSucTest {

    @Test
    public void RegistrarSalidaProductoInventarioSucCorrecto() {
    	
        boolean flag = false;
        
        if (Cajero.registrarSalidaProductoInventarioSuc(1,1,1)) {
        	flag = true;
		}

        assertEquals(true, flag);
    }  
    
    @Test
    public void RegistrarSalidaProductoInventarioSucCantidadMenorOIgualCero() {
        boolean flag = false;

        if (Cajero.registrarSalidaProductoInventarioSuc(1, 1, 0)) {
            flag = true;
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void RegistrarSalidaProductoInventarioSucIdProductoNoExistente() {
        boolean flag = false;

        if (Cajero.registrarSalidaProductoInventarioSuc(1, 999, 5)) {
            flag = true;
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void RegistrarSalidaProductoInventarioSucCantidadMayorDisponible() {
        boolean flag = false;

        if (Cajero.registrarSalidaProductoInventarioSuc(1, 1, 300)) {
            flag = true;
        }

        assertEquals(false, flag);
    }

}
