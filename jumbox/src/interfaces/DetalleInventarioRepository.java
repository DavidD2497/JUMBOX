package interfaces;

import java.util.List;
import modelos.DetalleInventario;

public interface DetalleInventarioRepository {

    List<DetalleInventario> getAllDetalleInventarios(); // Obtiene todos los detalles del inventario de la base de datos
    
    DetalleInventario getDetalleInventarioById(int id); // Obtiene un detalle del inventario por su ID
    
    void addDetalleInventario(DetalleInventario detalleInventario); // Agrega un detalle del inventario a la base de datos
    
    void updateDetalleInventario(DetalleInventario detalleInventario); // Actualiza un detalle del inventario en la base de datos
    
    void deleteDetalleInventario(int id); // Elimina un detalle del inventario de la base de datos
}
