package clases;

import java.util.LinkedList;

public class venta {
	private int idVenta;
	private double montoTotal;
	private String tipoPago;
	private LinkedList<detalleVenta> listaVenta = new LinkedList<>();
	
	public venta(int idVenta, double montoTotal, String tipoPago, LinkedList<detalleVenta> listaVenta) {
		super();
		this.idVenta = idVenta;
		this.montoTotal = montoTotal;
		this.tipoPago = tipoPago;
		this.listaVenta = listaVenta;
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

	public LinkedList<detalleVenta> getListaVenta() {
		return listaVenta;
	}

	public void setListaVenta(LinkedList<detalleVenta> listaVenta) {
		this.listaVenta = listaVenta;
	}
}
