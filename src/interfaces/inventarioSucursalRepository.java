package interfaces;

import java.util.List;

import modelos.inventarioSucursal;

public interface inventarioSucursalRepository {

    List<inventarioSucursal> getAllUsers(); // llama a todos los usuarios de la bdd
    
    inventarioSucursal getUserById(int id); //llama solo a uno, por su id
    
    void addUser(inventarioSucursal user); //añade usuarios a la bdd
    
    void updateUser(inventarioSucursal user); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
	
}
