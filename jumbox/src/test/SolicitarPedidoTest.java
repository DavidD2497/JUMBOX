	package test;

	import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
	import java.util.LinkedList;
	import java.util.List;

	import org.junit.Test;
	import java.time.LocalDate;    
	import modelos.AdminSucursal;
	import modelos.Cajero;
	import modelos.DetallePedido;
	import controladores.ProductoControlador;
	import controladores.VentaControlador;
	import controladores.PedidoControlador;
	import modelos.AdminSucursal;



public class SolicitarPedidoTest {

	// ( int idInventarioSucursal, int idProducto, int cantidadEntrada )

	// Aca se prueba el registro de entrada correcto de la funcion RegistroEntradaProducto
	
	//@Test
	//public void EntradaCorrecto() {
		//DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
		//boolean flag = false;

		//if (AdminSucursal.registroEntradaProducto(1, 1, 20)) {
		//	flag = true;

		//}

		//assertEquals(true, flag);

	//}

	// Aca se prueba el registro de entrada con cantidad 0 de la funcion RegistroEntradaProducto
	
	@Test
	public void testSolicitarPedido_Exito() {
	    LinkedList<DetallePedido> listaDetalle = new LinkedList<>();
	    PedidoControlador pedidoControlador= new PedidoControlador();
	    
	   
	    
	    listaDetalle.add(new DetallePedido(1, 10, 0)); 
	    listaDetalle.add(new DetallePedido(1, 100, 0));
	    listaDetalle.add(new DetallePedido(4, 18, 0));
	    listaDetalle.add(new DetallePedido(2, 12, 0));
	    listaDetalle.add(new DetallePedido(3, 14, 0));// Suponiendo que el producto con ID 1 existe
	    LocalDate fechaEntrega = LocalDate.now().plusDays(5);
	    boolean resultado = AdminSucursal.solicitarPedido(listaDetalle, fechaEntrega);
	    assertTrue(resultado);
	    // Verificar que se muestra el mensaje "Pedido creado correctamente"
	}

	@Test
	public void testSolicitarPedido_ListaVacia() {
	    LinkedList<DetallePedido> listaDetalle = new LinkedList<>();
	    LocalDate fechaEntrega = LocalDate.now().plusDays(5);
	    boolean resultado = AdminSucursal.solicitarPedido(listaDetalle, fechaEntrega);
	    assertFalse(resultado);
	    // Verificar que se muestra el mensaje "Complete todos los datos para hacer el pedido"
	}

	
	
	@Test
	public void testSolicitarPedido_FechaNula() {
	    LinkedList<DetallePedido> listaDetalle = new LinkedList<>();
	    listaDetalle.add(new DetallePedido(1, 2, 3));
	    LocalDate fechaEntrega = null;
	    boolean resultado = AdminSucursal.solicitarPedido(listaDetalle, fechaEntrega);
	    assertFalse(resultado);
	    // Verificar que se muestra el mensaje "Complete todos los datos para hacer el pedido"
	}

	

	
	//    @Test
	  //  public void EntradaCorrecto() {
	   //     boolean resultado = false;
	        
	     //   ProductoControlador	productoControlador = new ProductoControlador();
	     //   LocalDate fecha=LocalDate.parse("2024-10-10");

	     //   LinkedList<DetallePedido> detallesPedido= new LinkedList<DetallePedido>();
	        
	     //   detallesPedido.add(new DetallePedido(1,10,1));
	      //  detallesPedido.add(new DetallePedido(2,50,1));


	       // resultado = AdminSucursal.solicitarPedido(detallesPedido, fecha);

	      //  assertEquals(true, resultado);
	   // }
	}


