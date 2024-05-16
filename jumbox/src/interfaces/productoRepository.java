package interfaces;

import java.util.List;

import modelos.producto;

public interface productoRepository {

List<producto> getAllUsers(); 
    
    producto getUserById(int id); 
    
    void addUser(producto user); 
    
    void updateUser(producto user); 
    
    void deleteUser(int id); 
}
