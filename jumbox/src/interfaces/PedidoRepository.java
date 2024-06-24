package interfaces;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import modelos.DetallePedido;
import modelos.Pedido;

public interface PedidoRepository {

	List<Pedido> getAllPedidos(); // Obtiene todos los pedidos de la base de datos

	Pedido getPedidoById(int id); // Obtiene un pedido por su ID

	void addPedido(Pedido pedido); // Agrega un pedido a la base de datos

	void updatePedido(Pedido pedido); // Actualiza un pedido en la base de datos

	void deletePedido(int id); // Elimina un pedido de la base de datos

	int obtenerUltimoIdPedido();

	void actualizarEstadoPedido(int codigoPedido, String estado);

	void actualizarFechaEntrega(int codigoPedido, LocalDate nuevaFechaEntrega);

	boolean editarPedido(int idDetalle, int idPedido);
	
	public void updateIdPedido(int codigo,int idPedido);
}
