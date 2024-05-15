package interfaces;

import java.util.List;

public interface detallePedidoRepository {
	List<detallePedido> getAllUsers(); // llama a todos los usuarios de la bdd
    
	detallePedido getUserById(int id); //llama solo a uno, por su id
    
    void addUser(detallePedido user); //añade usuarios a la bdd
    
    void updateUser(detallePedido user); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
}
