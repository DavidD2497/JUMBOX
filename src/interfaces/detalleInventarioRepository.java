package interfaces;

import java.util.List;

public interface detalleInventarioRepository {

	    List<detalleInventario> getAllUsers(); // llama a todos los usuarios de la bdd
	    
	    detalleInventario getUserById(int id); //llama solo a uno, por su id
	    
	    void addUser(detalleInventario user); //añade usuarios a la bdd
	    
	    void updateUser(detalleInventario user); //actualiza los usuarios de la bdd
	    
	    void deleteUser(int id); //eliminar usuarios de la bdd
}
