package interfaces;

import java.util.List;
import modelos.DetallePedido;

public interface DetallePedidoRepository {

    List<DetallePedido> getAllDetallePedidos(); // Obtiene todos los detalles de pedidos de la base de datos
    
    DetallePedido getDetallePedidoById(int id); // Obtiene un detalle de pedido por su ID
    
    void addDetallePedido(DetallePedido detallePedido); // Agrega un detalle de pedido a la base de datos
    
    void updateDetallePedido(DetallePedido detallePedido); // Actualiza un detalle de pedido en la base de datos
    
    void deleteDetallePedido(int id); // Elimina un detalle de pedido de la base de datos
}
