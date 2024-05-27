package interfaces;

import java.util.List;
import modelos.Venta;

public interface VentaRepository {

    List<Venta> getAllVentas(); // Obtiene todas las ventas de la base de datos

    Venta getVentaById(int id); // Obtiene una venta por su ID

    void addVenta(Venta venta); // Agrega una venta a la base de datos

    void updateVenta(Venta venta); // Actualiza una venta en la base de datos

    void deleteVenta(int id); // Elimina una venta de la base de datos
}
