package clases;

import java.time.LocalDate;
import java.util.LinkedList;

public class pedido {
	private int codigoPedido;
	private LocalDate fechaEntrega;
	private LinkedList<detallePedido> listaPedidos = new LinkedList<>();
	
	public pedido(int codigoPedido, LocalDate fechaEntrega, LinkedList<detallePedido> listaPedidos) {
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

	public LinkedList<detallePedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(LinkedList<detallePedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
}
