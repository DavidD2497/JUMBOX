package interfaces;

import java.util.List;

import modelos.AdminDeposito;

public interface AdminDepositoRepository {

    List<AdminDeposito> getAllUsers();
    
    AdminDeposito getUserById(int id);
    
    void addUser(AdminDeposito user);
    
    void updateUser(AdminDeposito user);
    
    void deleteUser(int id);
	
}
