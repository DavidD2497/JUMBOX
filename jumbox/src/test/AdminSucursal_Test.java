package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import modelos.AdminSucursal;
import modelos.Producto;
import modelos.Descuento;

public class AdminSucursal_Test {
	private AdminSucursal adminSucursal;

	@Before
	public void setUp() {
		adminSucursal = new AdminSucursal("Nombre", "email@example.com", "contraseña", 1);
	}

	@Test
	public void GuardarDescuentoVencimiento_DescuentoAplicado() {
		LocalDate fechaVencimiento = LocalDate.now().plusDays(14);
		Producto producto = new Producto("Producto", fechaVencimiento, 100.0);
		adminSucursal.setProducto(producto);

		assertTrue(adminSucursal.crearDescuentoVencimiento(producto).equals("Se aplicó el descuento"));
	}

	@Test
	public void GuardarDescuentoVencimiento_NoSeAplicoDescuento() {
		LocalDate fechaVencimiento = LocalDate.now().plusDays(20);
		Producto producto = new Producto("Producto", fechaVencimiento, 100.0);
		adminSucursal.setProducto(producto);

		assertFalse(adminSucursal.crearDescuentoVencimiento(producto).equals("Se aplicó el descuento"));
	}

	@Test
	public void testEditarDescuento_DescuentoEditadoCorrectamente() {
		Descuento descuento = new Descuento(1, 10);
		adminSucursal.getDescuentos().add(descuento);

		adminSucursal.editarDescuento(0, 20);

		assertEquals(20.0, (double) adminSucursal.getDescuentos().get(0).getPorcentajeDesc(), 0);

	}

	@Test
	public void testMostrarDescuento_ListaDescuentosNoVacia() {
		Descuento descuento = new Descuento(1, 10);
		adminSucursal.getDescuentos().add(descuento);

		assertFalse(adminSucursal.getDescuentos().isEmpty());
	}

	@Test
	public void testEliminarDescuento_DescuentoEliminadoCorrectamente() {
		Descuento descuento = new Descuento(1, 10);
		adminSucursal.getDescuentos().add(descuento);

		adminSucursal.eliminarDescuento("0");

		assertTrue(adminSucursal.getDescuentos().isEmpty());
	}
}