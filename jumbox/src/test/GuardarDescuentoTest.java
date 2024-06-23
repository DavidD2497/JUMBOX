package test;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import modelos.Producto;
import modelos.AdminSucursal;

public class GuardarDescuentoTest {
	private AdminSucursal adminSucursal;

	@Test
	public void GuardarDescuentoVencimiento_DescuentoAplicado() {
		LocalDate fechaVencimiento = LocalDate.now().plusDays(14);
		Producto producto = new Producto("Producto", fechaVencimiento, 100.0);
		int cantDescuento = 10;
		adminSucursal.setProducto(producto);

		assertTrue(adminSucursal.crearDescuentoVencimiento(producto, cantDescuento).equals("Se aplicó el descuento"));
	}

	@Test
	public void GuardarDescuentoVencimiento_NoSeAplicoDescuento() {
		LocalDate fechaVencimiento = LocalDate.now().plusDays(20);
		Producto producto = new Producto("Producto", fechaVencimiento, 100.0);
		int cantDescuento = 10;
		adminSucursal.setProducto(producto);

		assertTrue(adminSucursal.crearDescuentoVencimiento(producto, cantDescuento).equals("Se aplicó el descuento"));
	}
}
