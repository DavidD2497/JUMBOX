package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import modelos.AdminSucursal;
import modelos.DetallePedido;
import modelos.Empleado;
import modelos.Informe;
import controladores.EmpleadoControlador;
import controladores.EntradaInventarioControlador;
import controladores.EntradaInventarioControlador;
public class CrearInformeTest{
	
	
	    @Test
	    public void EmpleadoBorradoCorrecto() {
	        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
	        EntradaInventarioControlador entradaControlador= new EntradaInventarioControlador();
	        boolean flag = false;

	        
	        LocalDate fechaInforme = LocalDate.now();
	        Informe informe = new Informe(fechaInforme);
			boolean resultado = AdminSucursal.crearInforme();;
			assertTrue(resultado);
	        assertEquals(true, flag);
	    }
	    
	   
	}


