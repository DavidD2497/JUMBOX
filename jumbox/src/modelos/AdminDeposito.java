package modelos;

import javax.swing.JOptionPane;
import controladores.DetalleDepositoControlador;

public class AdminDeposito extends Empleado {
	private String tipo;

	public AdminDeposito(String nombre, String email, String contraseña) {
		super(nombre, email, contraseña);
		this.tipo = "AdminDeposito";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public static boolean registrarSalidaDepositoGeneral(int idDepositoGeneral, int idProducto, int cantidadSalida) {
	    if (cantidadSalida <= 0) {
	        //JOptionPane.showMessageDialog(null, "La cantidad de salida debe ser mayor que cero.");
	        return false;
	    }

	    DetalleDepositoControlador detalleDepositoControlador = new DetalleDepositoControlador();

	    if (!detalleDepositoControlador.existeProducto(idDepositoGeneral, idProducto)) {
	        //JOptionPane.showMessageDialog(null, "El ID " + idProducto + " no existe en el inventario de la sucursal.");
	        return false;
	    }

	    int cantidadDisponible = detalleDepositoControlador.getCantidadDisponible(idDepositoGeneral, idProducto);

	    if (cantidadDisponible >= cantidadSalida) {
	        int cantidadTotal = cantidadDisponible - cantidadSalida;
	        detalleDepositoControlador.actualizarCantidadProducto(idDepositoGeneral, idProducto, cantidadTotal);
	        //OptionPane.showMessageDialog(null, "Salida de " + cantidadSalida + " unidades del producto con ID: " + idProducto + " registrada con éxito.");
	        return true;
	    } else {
	        //JOptionPane.showMessageDialog(null, "No hay suficiente inventario para sacar " + cantidadSalida + " unidades del producto con ID: " + idProducto);
	        return false;
	    }
	}

}