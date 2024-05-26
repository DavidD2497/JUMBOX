package modelos;

import java.util.LinkedList;
import java.util.List;

public class Venta {
	private int idVenta;
	private double montoTotal;
	private String tipoPago;
	private LinkedList<DetalleVenta> listaVenta = new LinkedList<>();
	
	public Venta(int idVenta, double montoTotal, String tipoPago, List<DetalleVenta> detallesVenta) {
		super();
		this.idVenta = idVenta;
		this.montoTotal = montoTotal;
		this.tipoPago = tipoPago;
		this.listaVenta = new LinkedList<>(listaVenta);
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public LinkedList<DetalleVenta> getListaVenta() {
		return listaVenta;
	}

	public void setListaVenta(LinkedList<DetalleVenta> listaVenta) {
		this.listaVenta = listaVenta;
	}
}
