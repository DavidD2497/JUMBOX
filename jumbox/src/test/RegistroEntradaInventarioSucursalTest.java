package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modelos.AdminSucursal;
import modelos.DetalleInventario;
import controladores.DetalleInventarioControlador;

public class RegistroEntradaInventarioSucursalTest {

	// ( int idInventarioSucursal, int idProducto, int cantidadEntrada )

	// Aca se prueba el registro de entrada correcto de la funcion RegistroEntradaProducto
	
	@Test
	public void EntradaCorrecto() {
		DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
		boolean flag = false;

		if (AdminSucursal.registroEntradaProducto(1, 1, 20)) {
			flag = true;

		}

		assertEquals(true, flag);

	}

	// Aca se prueba el registro de entrada con cantidad 0 de la funcion RegistroEntradaProducto
	
	@Test
	public void Cantidad0() {
		DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
		boolean flag = false;

		if (AdminSucursal.registroEntradaProducto(2, 2, 0)) {
			flag = true;

		}

		assertEquals(false, flag);

	}

	// Aca se prueba si el id del producto existe de la funcion RegistroEntradaProducto
	
	@Test
	public void ProductoInexistente() {
		DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
		boolean flag = false;

		if (AdminSucursal.registroEntradaProducto(10, 10, 20)) {
			flag = true;

		}

		assertEquals(false, flag);

	}

	// Aca se prueba el registro de entrada con cantidad >= 1000 de la funcion RegistroEntradaProducto
	
	@Test
	public void CantidadMayor1000() {
		DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();
		boolean flag = false;

		if (AdminSucursal.registroEntradaProducto(10, 10, 1000)) {
			flag = true;

		}

		assertEquals(false, flag);

	}

}
