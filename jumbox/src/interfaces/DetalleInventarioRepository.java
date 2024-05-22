package interfaces;

import java.util.List;

import modelos.DetalleInventario;
public interface DetalleInventarioRepository {

	    List<DetalleInventario> getAllUsers();
	    
	    DetalleInventario getUserById(int id);
	    
	    void addUser(DetalleInventario user);
	    
	    void updateUser(DetalleInventario user);
	    
	    void deleteUser(int id);
}
