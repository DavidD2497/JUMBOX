package interfaces;

import java.util.List;

import modelos.Informe;
public interface InformeRepository {

    List<Informe> getAllUsers(); 
    
    Informe getUserById(int id); 
    
    void addUser(Informe user); 
    
    void updateUser(Informe user); 
    
    void deleteUser(int id); 
	
}
