package interfaces;

import java.util.List;

import modelos.InventarioSucursal;
public interface InventarioSucursalRepository {

    List<InventarioSucursal> getAllUsers(); 
    
    InventarioSucursal getUserById(int id); 
    
    void addUser(InventarioSucursal user); 
    
    void updateUser(InventarioSucursal user); 
    
    void deleteUser(int id); 
	
}
