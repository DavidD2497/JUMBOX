package modelos;


public class InventarioSucursal {
	private int idInventario;
	private String ubicacion;
	
	public InventarioSucursal() {
		super();
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}

}
