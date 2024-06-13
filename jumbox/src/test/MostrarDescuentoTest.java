package test;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import modelos.AdminSucursal;
import modelos.Descuento;

public class MostrarDescuentoTest {
	private AdminSucursal adminSucursal;

	@Test
	public void testMostrarDescuento_ListaNoVacia() {
		Descuento descuento = new Descuento(1, 10,4);
		adminSucursal.getDescuentos().add(descuento);

		assertFalse(adminSucursal.getDescuentos().isEmpty());
	}
}
