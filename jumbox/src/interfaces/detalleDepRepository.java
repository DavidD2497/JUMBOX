package interfaces;

import modelos.detalleDeposito;

import java.util.List;

public interface detalleDepRepository {
	


		    List<detalleDeposito> getAllUsers(); // llama a todos los usuarios de la bdd
		    
		    detalleDeposito getUserById(int id); //llama solo a uno, por su id
		    
		    void addUser(detalleDeposito user); //añade usuarios a la bdd
		    
		    void updateUser(detalleDeposito user); //actualiza los usuarios de la bdd
		    
		    void deleteUser(int id); //eliminar usuarios de la bdd
		
	}
