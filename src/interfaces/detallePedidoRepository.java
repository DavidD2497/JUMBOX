package interfaces;

import java.util.List;

import modelos.detallePedido;

public interface detallePedidoRepository {
	List<detallePedido> getAllUsers();
	
	detallePedido getUserById(int id);
    
    void addUser(detallePedido user);
    
    void updateUser(detallePedido user);
    
    void deleteUser(int id);
}
