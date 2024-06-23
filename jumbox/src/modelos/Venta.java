package modelos;

public class Venta {
	private int idVenta;
	private double montoTotal;
	private String tipoPago;
	
    public Venta(double montoTotal, String tipoPago) {
        super();
        this.montoTotal = montoTotal;
        this.tipoPago = tipoPago;
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