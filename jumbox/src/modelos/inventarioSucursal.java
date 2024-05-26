package modelos;

import java.util.LinkedList;

public class InventarioSucursal {
	private int idInventario;
	private LinkedList<DetalleInventario> listaInventario = new LinkedList<>();
	
	public InventarioSucursal(int idInventario, LinkedList<DetalleInventario> listaInventario) {
		super();
		this.idInventario = idInventario;
		this.listaInventario = listaInventario;
	}

	public int getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}

	public LinkedList<DetalleInventario> getListaInventario() {
		return listaInventario;
	}

	public void setListaInventario(LinkedList<DetalleInventario> listaInventario) {
		this.listaInventario = listaInventario;
	}
}
