package modelos;

import java.time.LocalDate;
import java.util.LinkedList;

public class Pedido {
	private int codigoPedido;
	private LocalDate fechaEntrega;
	private LinkedList<DetallePedido> listaPedidos = new LinkedList<>();
	
	public Pedido(int codigoPedido, LocalDate fechaEntrega, LinkedList<DetallePedido> listaPedidos) {
		super();
		this.codigoPedido = codigoPedido;
		this.fechaEntrega = fechaEntrega;
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

	public LinkedList<DetallePedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(LinkedList<DetallePedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
}
