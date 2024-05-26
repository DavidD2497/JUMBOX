package interfaces;

import java.util.List;

import modelos.AdminSucursal;

public interface AdminSucursalRepository {

    List<AdminSucursal> getAllUsers();
    
    AdminSucursal getUserById(int id);
    
    void addUser(AdminSucursal user);
    
    void updateUser(AdminSucursal user);
    
    void deleteUser(int id);
}
