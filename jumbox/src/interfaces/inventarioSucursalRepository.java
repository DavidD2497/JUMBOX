package interfaces;

import java.util.List;

import modelos.inventarioSucursal;

public interface inventarioSucursalRepository {

    List<inventarioSucursal> getAllUsers(); 
    
    inventarioSucursal getUserById(int id); 
    
    void addUser(inventarioSucursal user); 
    
    void updateUser(inventarioSucursal user); 
    
    void deleteUser(int id); 
	
}
