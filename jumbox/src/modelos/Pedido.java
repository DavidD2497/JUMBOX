package modelos;

import java.time.LocalDate;
import java.util.LinkedList;

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

}
