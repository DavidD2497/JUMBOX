package interfaces;

import java.util.List;

import modelos.adminSucursal;

public interface adminSucursalRepository {

    List<adminSucursal> getAllUsers(); // llama a todos los usuarios de la bdd
    
    adminSucursal getUserById(int id); //llama solo a uno, por su id
    
    void addUser(adminSucursal user); //añade usuarios a la bdd
    
    void updateUser(adminSucursal user); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
}
