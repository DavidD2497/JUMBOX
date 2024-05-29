package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.LinkedList;

import modelos.DetallePedido;

public class ConfirmarSolicitudDePedidoTest {
	private LinkedList<DetallePedido> listaDetalle;
	private LocalDate fechaEntrega;

	@Test
	public void RegistrarSalidaProductoInventarioSucCorrecto() {

		boolean flag = false;

		if (Cajero.registrarSalidaProductoInventarioSuc(1, 1, 1)) {
			flag = true;
		}

		assertEquals(true, flag);
	}

	public void setUp() {
		listaDetalle = new LinkedList<>();
		fechaEntrega = LocalDate.now().plusDays(3); 
	}
	
	
}
