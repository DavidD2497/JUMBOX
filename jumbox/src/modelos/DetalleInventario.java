package modelos;

public class DetalleInventario {
	private int idProducto;
	private int idDescuento;
	private int idDetalle;
	private int cantidad;
	
	public DetalleInventario(int idProducto, int idDescuento, int idDetalle, int cantidad) {
		super();
		this.idProducto = idProducto;
		this.idDescuento = idDescuento;
		this.idDetalle = idDetalle;
		this.cantidad = cantidad;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(int idDescuento) {
		this.idDescuento = idDescuento;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
