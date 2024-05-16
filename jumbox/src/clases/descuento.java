package clases;

public class descuento {
	private int idDescuento;
	private int porcentajeDesc;
	private String tipoDesc;
	
	public descuento(int idDescuento, int porcentajeDesc, String tipoDesc) {
		super();
		this.idDescuento = idDescuento;
		this.porcentajeDesc = porcentajeDesc;
		this.tipoDesc = tipoDesc;
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

	public String getTipoDesc() {
		return tipoDesc;
	}

	public void setTipoDesc(String tipoDesc) {
		this.tipoDesc = tipoDesc;
	}
}
