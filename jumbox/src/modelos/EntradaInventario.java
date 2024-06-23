package modelos;

import java.time.LocalDate;
import java.util.Date;

public class EntradaInventario {
    private int idEntrada;
    private int idProducto;
    private int idInventario;
    private LocalDate fechaEntrada;
    private int cantidad;

    public EntradaInventario(int idProducto, int idInventario, LocalDate fechaEntrada, int cantidad) {
        this.idProducto = idProducto;
        this.idInventario = idInventario;
        this.fechaEntrada = fechaEntrada;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/vicky
