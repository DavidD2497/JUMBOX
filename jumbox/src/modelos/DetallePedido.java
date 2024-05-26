package modelos;

public class DetallePedido {
	private int idDetalle;
	private int idProducto;
	private int cantidad;
	
	public DetallePedido(int idDetalle, int idProducto, int cantidad) {
		super();
		this.idDetalle = idDetalle;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
