package modelos;

public class DetalleVenta {
	private int idProducto;
	private int idVenta;
	private double monto;
	private int cantidad;
	private int idDetalle;
	
	public DetalleVenta(int idProducto, int idVenta, double monto, int cantidad, int idDetalle) {
		super();
		this.idProducto = idProducto;
		this.idVenta = idVenta;
		this.monto = monto;
		this.cantidad = cantidad;
		this.idDetalle = idDetalle;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
}
