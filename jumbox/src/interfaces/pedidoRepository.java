package interfaces;

import java.util.List;

import modelos.pedido;

public interface pedidoRepository {

	
List<pedido> getAllUsers(); 
    
    pedido getUserById(int id); 
    
    void addUser(pedido user); 
    
    void updateUser(pedido user); 
    
    void deleteUser(int id); 
}
