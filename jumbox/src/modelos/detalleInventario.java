package modelos;

public class detalleInventario {
    private producto producto;
    private int idDescuento;
    private int idDetalle;
    private int cantidad;

    public detalleInventario(producto producto, int idDescuento, int idDetalle, int cantidad) {
        this.producto = producto;
        this.idDescuento = idDescuento;
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
    }

    public producto getProducto() {
        return producto;
    }

    public void setProducto(producto producto) {
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

