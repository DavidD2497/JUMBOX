package interfaces;

import java.util.List;
import modelos.DetalleVenta;

public interface DetalleVentaRepository {

    List<DetalleVenta> getAllDetalleVentas(); // Obtiene todos los detalles de ventas de la base de datos

    DetalleVenta getDetalleVentaById(int id); // Obtiene un detalle de venta por su ID

    void addDetalleVenta(DetalleVenta detalleVenta); // Agrega un detalle de venta a la base de datos

    void updateDetalleVenta(DetalleVenta detalleVenta); // Actualiza un detalle de venta en la base de datos

    void deleteDetalleVenta(int id); // Elimina un detalle de venta de la base de datos
}
