package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modelos.AdminSucursal;
import modelos.Descuento;

public class EliminarDescuentoTest {
	private AdminSucursal adminSucursal;

	@Test
	public void testEliminarDescuento() {
		Descuento descuento = new Descuento(1, 10,3);
		adminSucursal.getDescuentos().add(descuento);

		adminSucursal.eliminarDescuento(0);

		assertTrue(adminSucursal.getDescuentos().isEmpty());
	}
}
