package modelos;

import java.time.LocalDate;

import controladores.PedidoControlador;

public class Pedido {
    private int codigoPedido;
    private LocalDate fechaEntrega;

    public Pedido(LocalDate fechaEntrega) {
        super();
        this.fechaEntrega = fechaEntrega;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void definirFechaEntrega(LocalDate nuevaFechaEntrega, PedidoControlador pedidoControlador) {
        if (nuevaFechaEntrega != null && pedidoControlador != null) {
            this.setFechaEntrega(nuevaFechaEntrega);
            pedidoControlador.updatePedido(this);
        }
    }
}
