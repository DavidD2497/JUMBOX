package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import controladores.DescuentoControlador;
import modelos.Descuento;
import modelos.Producto;
import modelos.AdminSucursal;

public class EditarDescuentoTest {
	private AdminSucursal adminSucursal;

	@Test
	public void testEditarDescuento() {
		Descuento descuento = new Descuento(15, 1);
		DescuentoControlador descuentoControlador = new DescuentoControlador();
		adminSucursal.getDescuentos().add(descuento);

		adminSucursal.editarDescuento(0, 20);

		assertEquals(20.0, (double) adminSucursal.getDescuentos().get(0).getPorcentajeDesc(), 0.001);

	}
}
