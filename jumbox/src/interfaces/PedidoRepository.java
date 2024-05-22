package interfaces;

import java.util.List;

import modelos.Pedido;
public interface PedidoRepository {

	List<Pedido> getAllUsers();

	Pedido getUserById(int id);

	void addUser(Pedido user);

	void updateUser(Pedido user);

	void deleteUser(int id);
}
