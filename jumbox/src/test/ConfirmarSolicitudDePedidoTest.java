package test;

import static org.junit.Assert.*;

import org.junit.Test;
import controladores.DetallePedidoControlador;
import controladores.PedidoControlador;
import modelos.AdminDeposito;
import modelos.DetallePedido;

public class ConfirmarSolicitudDePedidoTest {
	PedidoControlador pedidoControlador = new PedidoControlador();
	DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();

	@Test
	public void PedidoAceptadoTest() {

		boolean flag = false;
		int idPedido = 1;
		DetallePedido detallePedido = new DetallePedido(1, 5, 10);
		detallePedido.setIdPedido(idPedido);

		detallePedidoControlador.addDetallePedido(detallePedido);

		boolean resultado = AdminDeposito.SolicitudDePedido(idPedido);

		assertEquals(true,resultado);
	}

	@Test
	public void pedidoEliminadoTest() {

		int idPedido = 2;

		DetallePedido detallePedido = new DetallePedido(2, 2, 0);
		detallePedido.setIdPedido(idPedido);

		detallePedidoControlador.addDetallePedido(detallePedido);
		
		boolean resultado = AdminDeposito.SolicitudDePedido(idPedido);


		assertEquals(false,resultado);
	}
}
