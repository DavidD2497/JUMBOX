package modelos;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import controladores.DetalleInventarioControlador;
import controladores.DetallePedidoControlador;
import controladores.PedidoControlador;
import controladores.ProductoControlador;

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
	

	public static boolean solicitarPedido(LinkedList<DetallePedido> listaDetalle) {
        ProductoControlador productoControlador = new ProductoControlador();
        LocalDate fechaEntrega=LocalDate.now();
        JOptionPane.showMessageDialog(null, fechaEntrega);
        if (listaDetalle.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Complete todos los datos para hacer el pedido");
            return false;
        }

        for (DetallePedido detalle : listaDetalle) {
            if (productoControlador.getProductoById(detalle.getIdProducto()) == null) {
                //JOptionPane.showMessageDialog(null, "El producto con ID " + detalle.getIdProducto() + " no existe.");

                return false;
            }

        }

     //   if (fechaEntrega.isBefore(LocalDate.now().plusDays(2))) {
            //JOptionPane.showMessageDialog(null, "La fecha ingresada debe ser posterior a la fecha actual");
         //   return false;
      //  }
        PedidoControlador pedidoControlador = new PedidoControlador();
        Pedido nuevoPedido = new Pedido(fechaEntrega);
        pedidoControlador.addPedido(nuevoPedido);int idPedido= pedidoControlador.obtenerUltimoIdPedido();
        DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();

        for (DetallePedido detalle : listaDetalle) {
            detalle.setIdPedido(idPedido);
            detallePedidoControlador.addDetallePedido(detalle);

        }
        //JOptionPane.showMessageDialog(null, "Pedido creado correctamente");
        return true;

    }
	
	
	
	public void crearInforme() {
		
		
		
		
	}
	
	public void mostrarPedido() {
        PedidoControlador pedidoControlador= new PedidoControlador();
        JOptionPane.showMessageDialog(null, pedidoControlador.getAllPedidos());
    }
}
