package modelos;

import java.time.LocalDate;

public class Producto {
	private int idProducto;
	private String nombreProducto;
	private String categoria;
	private double precio;
	private LocalDate fechaVencimiento;
	
	public Producto(int idProducto, String nombreProducto, String categoria, double precio,
			LocalDate fechaVencimiento) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.categoria = categoria;
		this.precio = precio;
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}
