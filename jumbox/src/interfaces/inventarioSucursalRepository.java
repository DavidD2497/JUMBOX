package interfaces;

import java.util.List;
import modelos.InventarioSucursal;

public interface InventarioSucursalRepository {

    List<InventarioSucursal> getAllInventarioSucursal(); 

    InventarioSucursal getInventarioSucursalById(int id); 

    void addInventarioSucursal(InventarioSucursal inventarioSucursal); 

    void updateInventarioSucursal(InventarioSucursal inventarioSucursal);

    void deleteInventarioSucursal(int id);
    
}