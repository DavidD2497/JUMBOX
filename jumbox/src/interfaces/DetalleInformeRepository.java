package interfaces;

import java.util.List;

import modelos.DetalleInforme;
public interface DetalleInformeRepository {
	    List<DetalleInforme> getAllUsers(); // llama a todos los usuarios de la bdd
	    
	    DetalleInforme getUserById(int id); //llama solo a uno, por su id
	    
	    void addUser(DetalleInforme user); //añade usuarios a la bdd
	    
	    void updateUser(DetalleInforme user); //actualiza los usuarios de la bdd
	    
	    void deleteUser(int id); //eliminar usuarios de la bdd
	
}
