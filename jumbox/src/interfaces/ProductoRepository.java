package interfaces;

import java.util.List;
import modelos.Producto;

public interface ProductoRepository {

    List<Producto> getAllProductos(); // Obtiene todos los productos de la base de datos

    Producto getProductoById(int id); // Obtiene un producto por su ID

    void addProducto(Producto producto); // Agrega un producto a la base de datos

    void updateProducto(Producto producto); // Actualiza un producto en la base de datos

    void deleteProducto(int id); // Elimina un producto de la base de datos
}
