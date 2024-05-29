package modelos;

import javax.swing.JOptionPane;
import controladores.DetalleInventarioControlador;

public class AdminSucursal extends Empleado {
	int idProducto;
	int cantidadEntrada;
	private String tipo;

	public AdminSucursal(String nombre, String email, String contraseña) {
		super(nombre, email, contraseña);
		this.tipo = "AdminSucursal";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static boolean registroEntradaProducto(int idInventarioSucursal, int idProducto, int cantidadEntrada) {
		
		 if (cantidadEntrada>=1000 ) {
			 // JOptionPane.showMessageDialog(null,"La cantidad de Entrada debe ser menor que 1000.");
			 
	            return false;
	        }
		 
	    if (cantidadEntrada <= 0) {
	        // JOptionPane.showMessageDialog(null, "La cantidad de Entrada debe ser mayor que cero.");
	        return false;
	    }

	    DetalleInventarioControlador detalleInventarioControlador = new DetalleInventarioControlador();

	    if (!detalleInventarioControlador.existeProducto(idInventarioSucursal, idProducto)) {
	        // JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el inventario de la sucursal.");
	        return false;
	    }

	    int cantidadDisponible = detalleInventarioControlador.getCantidadDisponible(idInventarioSucursal, idProducto);

	    
	        int cantidadTotal = cantidadDisponible + cantidadEntrada;
	        detalleInventarioControlador.actualizarCantidadProducto(idInventarioSucursal, idProducto, cantidadTotal);
	        // JOptionPane.showMessageDialog(null, "Entrada de " + cantidadEntrada + " unidades al producto " +detalleInventarioControlador.getNombreProducto(idProducto)  + " registrada con éxito.");
	        return true;
	   
	}
	

	public void CrearSolicitudPedido() {

	}
	
}
