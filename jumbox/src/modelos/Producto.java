package modelos;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import controladores.DescuentoControlador;

public class Producto {
	private int idProducto;
	private String nombreProducto;
	private String categoria;
	private double precio;
	private LocalDate fechaVencimiento;
<<<<<<< HEAD
	
	public Producto(String nombreProducto, String categoria, double precio,
=======
	private List<Descuento> descuentosAplicados;

	public Producto(int idProducto, String nombreProducto, String categoria, double precio,
>>>>>>> origin/vicky
			LocalDate fechaVencimiento) {
		super();
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

	public static boolean tieneDescuentoActivo(Producto producto) {
        System.out.println("Verificando descuentos activos para el producto: " + producto.getNombreProducto());

        DescuentoControlador descuentoControlador = new DescuentoControlador();
        List<Descuento> descuentosAplicados = descuentoControlador.getAllDescuentos();

        if (descuentosAplicados != null) {
            for (Descuento descuento : descuentosAplicados) {
                System.out.println("Descuento aplicado: " + descuento.getIdDescuento());
                if (producto.getIdProducto()==descuento.getIdProducto()) {
                    System.out.println("Descuento activo encontrado para el producto: " + producto.getNombreProducto());
                    return true;
                }
            }
        } else {
            System.out.println("No se encontraron descuentos aplicados para el producto: " + producto.getNombreProducto());
        }

        return false;
    }
	
}
