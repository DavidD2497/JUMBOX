package modelos;

public class DetalleInforme {
	private int idDetalle;
	private int idVenta;
	private int idInforme;
	private int idInventario;
	private int idPedido;
	
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public DetalleInforme(int idVenta, int idInventario, int idInforme, int idPedido) {
		super();
		this.idVenta = idVenta;
		this.idInventario = idInventario;
		this.idInforme = idInforme;
		this.idPedido = idPedido;
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

	public int getIdInforme() {
		return idInforme;
	}

	public void setIdInforme(int idInforme) {
		this.idInforme = idInforme;
	}
	
}
