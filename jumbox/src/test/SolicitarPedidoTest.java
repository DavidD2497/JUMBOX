package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;
import java.time.LocalDate;
import modelos.AdminSucursal;

import modelos.DetallePedido;

import controladores.PedidoControlador;

public class SolicitarPedidoTest {

	@Test
	public void testSolicitarPedido_Exito() {
		LinkedList<DetallePedido> listaDetalle = new LinkedList<>();
		PedidoControlador pedidoControlador = new PedidoControlador();

		listaDetalle.add(new DetallePedido(1, 10, 0));
		listaDetalle.add(new DetallePedido(1, 100, 0));
		listaDetalle.add(new DetallePedido(4, 18, 0));
		listaDetalle.add(new DetallePedido(2, 12, 0));
		listaDetalle.add(new DetallePedido(3, 14, 0));
		LocalDate fechaEntrega = LocalDate.now().plusDays(5);
		boolean resultado = AdminSucursal.solicitarPedido(listaDetalle, fechaEntrega);
		assertTrue(resultado);

	}

	@Test
	public void testSolicitarPedido_ProductoNoExiste() {
		LinkedList<DetallePedido> listaDetalle = new LinkedList<>();
		PedidoControlador pedidoControlador = new PedidoControlador();

		listaDetalle.add(new DetallePedido(9, 18, 0));
		listaDetalle.add(new DetallePedido(2, 12, 0));
		listaDetalle.add(new DetallePedido(3, 14, 0));
		LocalDate fechaEntrega = LocalDate.now().plusDays(5);
		boolean resultado = AdminSucursal.solicitarPedido(listaDetalle, fechaEntrega);
		assertFalse(resultado);

	}

	@Test
	public void testSolicitarPedido_ListaVacia() {
		LinkedList<DetallePedido> listaDetalle = new LinkedList<>();
		LocalDate fechaEntrega = LocalDate.now().plusDays(5);
		boolean resultado = AdminSucursal.solicitarPedido(listaDetalle, fechaEntrega);
		assertFalse(resultado);

	}

	@Test
	public void testSolicitarPedido_FechaNula() {
		LinkedList<DetallePedido> listaDetalle = new LinkedList<>();
		listaDetalle.add(new DetallePedido(1, 2, 3));
		LocalDate fechaEntrega = null;
		boolean resultado = AdminSucursal.solicitarPedido(listaDetalle, fechaEntrega);
		assertFalse(resultado);

	}
	
	@Test
    public void testSolicitarPedido_FechaAnterior() {
        LinkedList<DetallePedido> listaDetalle = new LinkedList<>();
        listaDetalle.add(new DetallePedido(1, 2, 3));
          LocalDate fecha = LocalDate.of(2024, 5, 30);
        boolean resultado = AdminSucursal.solicitarPedido(listaDetalle, fecha);
        assertFalse(resultado);
    }

}