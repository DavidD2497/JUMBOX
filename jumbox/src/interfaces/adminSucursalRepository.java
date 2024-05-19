package interfaces;

import java.util.List;

import modelos.adminSucursal;

public interface adminSucursalRepository {

    List<adminSucursal> getAllUsers();
    
    adminSucursal getUserById(int id);
    
    void addUser(adminSucursal user);
    
    void updateUser(adminSucursal user);
    
    void deleteUser(int id);
}
