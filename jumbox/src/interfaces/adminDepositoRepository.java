package interfaces;

import java.util.List;

import modelos.adminDeposito;

public interface adminDepositoRepository {

    List<adminDeposito> getAllUsers();
    
    adminDeposito getUserById(int id);
    
    void addUser(adminDeposito user);
    
    void updateUser(adminDeposito user);
    
    void deleteUser(int id);
	
}
