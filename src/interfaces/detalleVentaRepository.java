package interfaces;

import java.util.List;

public interface detalleVentaRepository {
	List<detalleVenta> getAllUsers(); // llama a todos los usuarios de la bdd
    
	detalleVenta getUserById(int id); //llama solo a uno, por su id
    
    void addUser(detalleVenta user); //añade usuarios a la bdd
    
    void updateUser(detalleVenta user); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
}
