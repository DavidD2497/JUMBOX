package interfaces;

import java.util.List;

import modelos.detalleVenta;

public interface detalleVentaRepository {
	List<detalleVenta> getAllUsers();
    
	detalleVenta getUserById(int id);
    
    void addUser(detalleVenta user);
    
    void updateUser(detalleVenta user);
    
    void deleteUser(int id);
}
