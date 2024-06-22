package modelos;

public class DetalleDeposito {
	private int idDetalle;
	private int idDepositoGeneral;
	private int idProducto;
	private int cantidad;
	
	public DetalleDeposito(int idProducto, int idDepositoGeneral, int cantidad) {
		super();
		this.idDepositoGeneral = idDepositoGeneral;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getIdDepositoGeneral() {
		return idDepositoGeneral;
	}

	public void setIdDepositoGeneral(int idDepositoGeneral) {
		this.idDepositoGeneral = idDepositoGeneral;
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
