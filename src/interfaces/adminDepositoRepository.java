package interfaces;

import java.util.List;

import modelos.adminDeposito;

public interface adminDepositoRepository {

    List<adminDeposito> getAllUsers(); // llama a todos los usuarios de la bdd
    
    adminDeposito getUserById(int id); //llama solo a uno, por su id
    
    void addUser(adminDeposito user); //añade usuarios a la bdd
    
    void updateUser(adminDeposito user); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
	
}
