package interfaces;

import java.util.List;
import modelos.InventarioSucursal;

public interface InventarioSucursalRepository {

    List<InventarioSucursal> getAllInventarioSucursal(); // Obtiene todos los inventarios de sucursales de la base de datos

    InventarioSucursal getInventarioSucursalById(int id); // Obtiene un inventario de sucursal por su ID

    void addInventarioSucursal(InventarioSucursal inventarioSucursal); // Agrega un inventario de sucursal a la base de datos

    void updateInventarioSucursal(InventarioSucursal inventarioSucursal); // Actualiza un inventario de sucursal en la base de datos

    void deleteInventarioSucursal(int id); // Elimina un inventario de sucursal de la base de datos
}
