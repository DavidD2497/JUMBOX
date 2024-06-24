package modelos;

import java.time.LocalDate;
import java.util.LinkedList;

public class Pedido {
	private int codigoPedido;
	private LocalDate fechaEntrega;
	private int idInventario;
	private String estado="Pendiente";

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Pedido(LocalDate fechaEntrega, int idInventario, String estado) {
		super();
		this.fechaEntrega = fechaEntrega;
		this.idInventario = idInventario;
		this.estado=estado;
	}

	public int getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
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
