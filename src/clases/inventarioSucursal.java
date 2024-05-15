package clases;

import java.util.LinkedList;

public class inventarioSucursal {
	private int idInventario;
	private LinkedList<detalleInventario> listaInventario = new LinkedList<>();
	
	public inventarioSucursal(int idInventario, LinkedList<detalleInventario> listaInventario) {
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

	public LinkedList<detalleInventario> getListaInventario() {
		return listaInventario;
	}

	public void setListaInventario(LinkedList<detalleInventario> listaInventario) {
		this.listaInventario = listaInventario;
	}
}
