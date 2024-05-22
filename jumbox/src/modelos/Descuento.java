package modelos;

public class Descuento {
	private int idDescuento;
	private int porcentajeDesc;
	
	public Descuento(int idDescuento, int porcentajeDesc) {
		super();
		this.idDescuento = idDescuento;
		this.porcentajeDesc = porcentajeDesc;
	}

	public int getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(int idDescuento) {
		this.idDescuento = idDescuento;
	}

	public int getPorcentajeDesc() {
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(int porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}


}
