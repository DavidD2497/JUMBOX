package interfaces;

import java.util.List;

import modelos.detalleInventario;

public interface detalleInventarioRepository {

	    List<detalleInventario> getAllUsers();
	    
	    detalleInventario getUserById(int id);
	    
	    void addUser(detalleInventario user);
	    
	    void updateUser(detalleInventario user);
	    
	    void deleteUser(int id);
}
