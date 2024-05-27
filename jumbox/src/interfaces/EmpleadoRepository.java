package interfaces;

import java.util.List;

import modelos.Empleado;
public interface EmpleadoRepository {

    List<Empleado> getAllUsers(); 
    
    Empleado getUserById(int id); 
    
    void addUser(Empleado user); 
    
    void updateUser(Empleado user); 
    
    void deleteUser(int id); 
	
}
