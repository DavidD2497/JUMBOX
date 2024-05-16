package interfaces;

import java.util.List;

import modelos.cajero;

public interface cajeroRepository {

    List<cajero> getAllUsers(); 
    
    cajero getUserById(int id);
    
    void addUser(cajero user);
    
    void updateUser(cajero user); 
    
    void deleteUser(int id);
}
