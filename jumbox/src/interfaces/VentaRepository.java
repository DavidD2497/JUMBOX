package interfaces;

import java.util.List;

import modelos.Venta;

public interface VentaRepository {
	List<Venta> getAllUsers();

	Venta getUserById(int id);

	void addUser(Venta user);

	void updateUser(Venta user);

	void deleteUser(int id);
}
