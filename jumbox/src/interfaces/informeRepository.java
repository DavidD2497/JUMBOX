package interfaces;

import java.util.List;

import modelos.informe;

public interface informeRepository {

    List<informe> getAllUsers(); 
    
    informe getUserById(int id); 
    
    void addUser(informe user); 
    
    void updateUser(informe user); 
    
    void deleteUser(int id); 
	
}
