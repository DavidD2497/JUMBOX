package interfaces;

import java.util.List;

import modelos.detalleInforme;

public interface detalleInformeRepository {


	    List<detalleInforme> getAllUsers(); // llama a todos los usuarios de la bdd
	    
	    detalleInforme getUserById(int id); //llama solo a uno, por su id
	    
	    void addUser(detalleInforme user); //añade usuarios a la bdd
	    
	    void updateUser(detalleInforme user); //actualiza los usuarios de la bdd
	    
	    void deleteUser(int id); //eliminar usuarios de la bdd
	
}
