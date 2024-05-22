package interfaces;

import java.util.List;

import modelos.Descuento;
import modelos.DetalleDeposito;

public interface DescuentoRepository {

	List<Descuento> getAllUsers(); // llama a todos los usuarios de la bdd

	Descuento getUserById(int id); // llama solo a uno, por su id

	void addUser(Descuento user); // añade usuarios a la bdd

	void updateUser(Descuento user); // actualiza los usuarios de la bdd

	void deleteUser(int id); // eliminar usuarios de la bdd

}
