package interfaces;

import java.util.List;

import modelos.venta;

public interface ventaRepository {

	
List<venta> getAllUsers(); 
    
venta getUserById(int id); 
    
    void addUser(venta user); 
    
    void updateUser(venta user); 
    
    void deleteUser(int id); 
}
