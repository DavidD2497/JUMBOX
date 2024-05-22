package interfaces;

import java.util.List;

import modelos.Producto;

public interface ProductoRepository {
List<Producto> getAllUsers(); 
 
    Producto getUserById(int id); 
    
    void addUser(Producto user); 
    
    void updateUser(Producto user); 
    
    void deleteUser(int id); 
}
