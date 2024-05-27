package modelos;

public class DetalleInforme {
	private int idDetalle;
	private int idVenta;
	private int idInventario;
	
	public DetalleInforme(int idDetalle, int idVenta, int idInventario) {
		super();
		this.idDetalle = idDetalle;
		this.idVenta = idVenta;
		this.idInventario = idInventario;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}
}
