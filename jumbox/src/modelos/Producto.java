package modelos;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Producto {
	private int idProducto;
	private String nombreProducto;
	private String categoria;
	private double precio;
	private LocalDate fechaVencimiento;
	private List<Descuento> descuentosAplicados;

	public Producto(int idProducto, String nombreProducto, String categoria, double precio,
			LocalDate fechaVencimiento) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.categoria = categoria;
		this.precio = precio;
		this.fechaVencimiento = fechaVencimiento;
        this.descuentosAplicados = new ArrayList<>(); // Inicializar la lista de descuentos
	}

	public Producto(String nombreProducto, LocalDate fechaVencimiento, double precio) {
		this.nombreProducto = nombreProducto;
		this.fechaVencimiento = fechaVencimiento;
		this.precio = precio;
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

	public boolean tieneDescuentoActivo() {
	    System.out.println("Verificando descuentos activos para el producto: " + this.getNombreProducto());
	    
	    if (this.descuentosAplicados != null) {
	        for (Descuento descuento : this.descuentosAplicados) {
	            System.out.println("Descuento aplicado: " + descuento.getIdDescuento());
	            if (descuento.isActivo()) {
	                System.out.println("Descuento activo encontrado para el producto: " + this.getNombreProducto());
	                return true;
	            }
	        }
	    } else {
	        System.out.println("No se encontraron descuentos aplicados para el producto: " + this.getNombreProducto());
	    }
	    
	    return false;
	}
	
}
