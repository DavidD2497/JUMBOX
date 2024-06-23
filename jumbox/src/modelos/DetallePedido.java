package modelos;

public class DetallePedido {
	private int idDetalle;
	private int idPedido;
	private int idProducto;
	private int cantidad;
<<<<<<< HEAD
	
	public DetallePedido(int idProducto, int cantidad, int idPedido) {
=======
	private int idPedido;

	public DetallePedido(int idDetalle, int idProducto, int cantidad) {
>>>>>>> origin/vicky
		super();
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.idPedido = idPedido;
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

<<<<<<< HEAD
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
=======
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
>>>>>>> origin/vicky
}
