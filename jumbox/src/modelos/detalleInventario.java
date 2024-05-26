package modelos;

public class DetalleInventario {
    private Producto producto;
    private int idDescuento;
    private int idDetalle;
    private int cantidad;

    public DetalleInventario(Producto producto, int idDescuento, int idDetalle, int cantidad) {
        this.producto = producto;
        this.idDescuento = idDescuento;
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

