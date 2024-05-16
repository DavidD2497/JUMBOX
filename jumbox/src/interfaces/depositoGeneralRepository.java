package interfaces;

import java.util.List;

import modelos.depositoGeneral;

public interface depositoGeneralRepository {
    List<depositoGeneral> getAllUsers();
    
    depositoGeneral getUserById(int id);
    
    void addUser(depositoGeneral user);
    
    void updateUser(depositoGeneral user);
    
    void deleteUser(int id);
}
