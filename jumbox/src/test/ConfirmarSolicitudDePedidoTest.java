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
		AdminDeposito adminDeposito = new AdminDeposito("Vicky", "vicky@gmail.com", "1234", 1);
		boolean flag = false;
		int idPedido = 1;
		DetallePedido detallePedido = new DetallePedido(1, 5, 10);
		detallePedido.setIdPedido(idPedido);

		detallePedidoControlador.addDetallePedido(detallePedido);

		if (adminDeposito.SolicitudDePedido(idPedido)) {
			flag = true;
		}
		assertTrue(flag);
	}

	@Test
	public void pedidoEliminadoTest() {
		AdminDeposito adminDeposito2 = new AdminDeposito("Pablo", "pablo@gmail.com", "5678", 2);
		boolean flag = false;
		int idPedido = 2;

		DetallePedido detallePedido = new DetallePedido(2, 2, 0);
		detallePedido.setIdPedido(idPedido);

		detallePedidoControlador.addDetallePedido(detallePedido);

		if (!adminDeposito2.SolicitudDePedido(idPedido)) {
			flag = false;
		}

		assertFalse(flag);
	}
}
