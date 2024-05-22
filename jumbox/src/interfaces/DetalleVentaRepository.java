package interfaces;

import java.util.List;

import modelos.DetalleVenta;
public interface DetalleVentaRepository {
	List<DetalleVenta> getAllUsers();
    
	DetalleVenta getUserById(int id);
    
    void addUser(DetalleVenta user);
    
    void updateUser(DetalleVenta user);
    
    void deleteUser(int id);
}
