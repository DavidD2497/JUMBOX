package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.junit.Test;
import java.time.LocalDate;
import modelos.AdminSucursal;

import modelos.DetallePedido;

import controladores.PedidoControlador;

public class mostrarPedidoTest {

	@Test
	public void testSolicitarPedido_Exito() {
		PedidoControlador pedidoControlador= new PedidoControlador();
		JOptionPane.showMessageDialog(null, pedidoControlador.getAllPedidos());
		
	}

	
	
	

}
