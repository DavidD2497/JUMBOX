package interfaces;

import java.util.List;

import modelos.DetalleDeposito;

public interface DetalleDepRepository {

	List<DetalleDeposito> getAllUsers(); // llama a todos los usuarios de la bdd

	DetalleDeposito getUserById(int id); // llama solo a uno, por su id

	void addUser(DetalleDeposito user); // añade usuarios a la bdd

	void updateUser(DetalleDeposito user); // actualiza los usuarios de la bdd

	void deleteUser(int id); // eliminar usuarios de la bdd

}
