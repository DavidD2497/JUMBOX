package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modelos.Empleado;
import controladores.EmpleadoControlador;
import javax.swing.JOptionPane;
import controladores.InventarioSucursalControlador;

public class RegistroEntradaTest {


	 @Test
	 public void RegistroEntrada(int idProducto, int cantidadEntrada) {
		    
		    InventarioSucursalControlador inventarioSucursalControlador = new InventarioSucursalControlador();
		    int cantidadDisponible = inventarioSucursalControlador.getCantidadDisponible(idProducto);
		    if (cantidadEntrada == 0) {
		        
		        JOptionPane.showMessageDialog(null, "No se va a registrar ninguna entrada si la cantidad es 0");
		    }
		    
		    int cantidadTotal = cantidadDisponible + cantidadEntrada;
		    inventarioSucursalControlador.actualizarCantidadProducto(idProducto, cantidadTotal);
		    
		    JOptionPane.showMessageDialog(null, "Entrada de " + cantidadEntrada + " unidades del producto con ID: " + idProducto 
		    + " registrada con éxito.");
		
		}
	
	




}