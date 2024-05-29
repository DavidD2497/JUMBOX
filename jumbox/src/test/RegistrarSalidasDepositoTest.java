package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import modelos.AdminDeposito;


public class RegistrarSalidasDepositoTest {

    @Test
    public void RegistrarSalidasDepositoCorrecto() {
    	
        boolean flag = false;
        
        if (AdminDeposito.registrarSalidaDepositoGeneral(1,1,1)) {
        	flag = true;
		}

        assertEquals(true, flag);
    }  
    
    @Test
    public void RegistrarSalidasDepositoCantidadMenorOIgualCero() {
        boolean flag = false;

        if (AdminDeposito.registrarSalidaDepositoGeneral(1, 1, 0)) {
            flag = true;
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void RegistrarSalidasDepositoNoExistente() {
        boolean flag = false;

        if (AdminDeposito.registrarSalidaDepositoGeneral(1, 999, 5)) {
            flag = true;
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void RegistrarSalidasDepositoCantidadMayorDisponible() {
        boolean flag = false;

        if (AdminDeposito.registrarSalidaDepositoGeneral(1, 1, 300)) {
            flag = true;
        }

        assertEquals(false, flag);
    }

}