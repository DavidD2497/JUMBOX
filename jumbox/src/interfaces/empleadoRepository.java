package interfaces;

import java.util.List;

import modelos.empleado;

public interface empleadoRepository {

    List<empleado> getAllUsers(); 
    
    empleado getUserById(int id); 
    
    void addUser(empleado user); 
    
    void updateUser(empleado user); 
    
    void deleteUser(int id); 
	
}
