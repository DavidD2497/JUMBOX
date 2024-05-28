package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import modelos.AdminSucursal;
import modelos.Producto;

public class AdminSucursal_Test {
	private AdminSucursal adminSucursal;
	private Producto producto;

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
}
