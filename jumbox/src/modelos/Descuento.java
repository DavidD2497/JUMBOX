package modelos;

public class Descuento {
	private int idDescuento;
	private double porcentajeDesc;

	public Descuento(double porcentajeDesc) {
		super();
		this.porcentajeDesc = porcentajeDesc;
	}

	public int getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(int idDescuento) {
		this.idDescuento = idDescuento;
	}

	public double getPorcentajeDesc() {
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(double porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}
}
