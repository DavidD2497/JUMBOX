package interfaces;
	import java.util.List;

import modelos.descuento;
import modelos.detalleDeposito;
public interface descuentoRepository {

    List<descuento> getAllUsers(); // llama a todos los usuarios de la bdd
    
    descuento getUserById(int id); //llama solo a uno, por su id
    
    void addUser(descuento user); //añade usuarios a la bdd
    
    void updateUser(descuento user); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd

}
