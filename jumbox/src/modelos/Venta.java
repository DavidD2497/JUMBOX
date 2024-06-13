package modelos;

import java.time.LocalDate;

public class Venta {
	private int idVenta;
	private double montoTotal;
	private String tipoPago;
	private LocalDate fechaVenta;
    public Venta(double montoTotal, String tipoPago, LocalDate fechaVenta) {
        super();
        this.montoTotal = montoTotal;
        this.tipoPago = tipoPago;
        this.fechaVenta=fechaVenta;
    }

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
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
}
