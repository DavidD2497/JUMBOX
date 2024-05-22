package interfaces;

import java.util.List;

import modelos.DetallePedido;

public interface DetallePedidoRepository {
	List<DetallePedido> getAllUsers();
	
	DetallePedido getUserById(int id);
    
    void addUser(DetallePedido user);
    
    void updateUser(DetallePedido user);
    
    void deleteUser(int id);
}
