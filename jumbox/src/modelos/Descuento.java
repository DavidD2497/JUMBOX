package modelos;

public class Descuento {
	private int idDescuento;
	private int porcentajeDesc;
	private Producto producto;
	int idProducto;

	public Descuento(int idDescuento, int porcentajeDesc, int idProducto) {
		
		this.porcentajeDesc = porcentajeDesc;
		this.idProducto = idProducto;
	}

	public Descuento(int porcentajeDescuento, Producto producto) {
		this.porcentajeDesc = porcentajeDescuento;
		this.producto = producto;
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

	public void setPorcentajeDesc(int porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	
}
