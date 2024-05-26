package interfaces;

import java.util.List;

import modelos.Cajero;

public interface CajeroRepository {

    List<Cajero> getAllUsers(); 
    
    Cajero getUserById(int id);
    
    void addUser(Cajero user);
    
    void updateUser(Cajero user); 
    
    void deleteUser(int id);
}
