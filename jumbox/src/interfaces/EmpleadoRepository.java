package interfaces;

import java.util.List;

import modelos.Empleado;

public interface EmpleadoRepository {

    List<Empleado> getAllUsers(); 
    
    void addUser(Empleado user); 
    
    void updateUser(String email, Empleado updatedEmpleado); 
    
    void deleteUser(String email); 
    
    Empleado getUserByEmail(String email);
    
    Empleado getUserByEmailAndPassword(String email, String contraseña);
	
}
