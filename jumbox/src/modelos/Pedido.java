package modelos;

import java.time.LocalDate;
import java.util.LinkedList;

public class Pedido {
    private int codigoPedido;
    private LocalDate fechaEntrega;
    private int idInventario; // Se supone que es el ID del inventario asociado al pedido
    private String estado; // Estado del pedido
    private LinkedList<DetallePedido> listaPedidos; // Lista de detalles del pedido

    public Pedido(int codigoPedido, LocalDate fechaEntrega, int idInventario, String estado, LinkedList<DetallePedido> listaPedidos) {
        this.codigoPedido = codigoPedido;
        this.fechaEntrega = fechaEntrega;
        this.idInventario = idInventario;
        this.estado = estado;
        this.listaPedidos = listaPedidos;
    }

    public Pedido(LocalDate fechaEntrega, int idInventario, String estado, LinkedList<DetallePedido> listaPedidos) {
        this.fechaEntrega = fechaEntrega;
        this.idInventario = idInventario;
        this.estado = estado;
        this.listaPedidos = listaPedidos;
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

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LinkedList<DetallePedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(LinkedList<DetallePedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
}

