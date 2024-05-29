package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.DetallePedido;

import interfaces.DetallePedidoRepository;

public class DetallePedidoControlador implements DetallePedidoRepository {
    private final Connection connection;

    public DetallePedidoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<DetallePedido> getAllDetallePedidos() {
        List<DetallePedido> detallesPedidos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_pedido");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idDetalle = resultSet.getInt("id_detalle_pedido");
                int idPedido = resultSet.getInt("id_pedido");
                int idProducto = resultSet.getInt("id_producto");
                int cantidad = resultSet.getInt("cantidad");
                
                DetallePedido detallePedido = new DetallePedido(idProducto, cantidad, idPedido);
                detallePedido.setIdDetalle(idDetalle);
                detallesPedidos.add(detallePedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallesPedidos;
    }

    @Override
    public DetallePedido getDetallePedidoById(int id) {
        DetallePedido detallePedido = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_pedido WHERE id_detalle_pedido = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idPedido = resultSet.getInt("id_pedido");
                int idProducto = resultSet.getInt("id_producto");
                int cantidad = resultSet.getInt("cantidad");
                
                detallePedido = new DetallePedido(idProducto, cantidad, idPedido);
                detallePedido.setIdDetalle(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallePedido;
    }

    @Override
    public void addDetallePedido( int idProducto, int cantidad, int idPedido) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO detalle_pedido (id_producto, cantidad, id_pedido) VALUES (?, ?, ?)");
            statement.setInt(1, idProducto);
            statement.setInt(2, cantidad);
            statement.setInt(3, idPedido);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Detalle de pedido agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetallePedido(DetallePedido detallePedido) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE detalle_pedido SET id_producto = ?, cantidad = ?, id_pedido = ? WHERE id_detalle_pedido = ?");
            statement.setInt(1, detallePedido.getIdProducto());
            statement.setInt(2, detallePedido.getCantidad());
            statement.setInt(3, detallePedido.getIdPedido());
            statement.setInt(4, detallePedido.getIdDetalle());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Detalle de pedido actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteDetallePedido(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM detalle_pedido WHERE id_detalle_pedido = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Detalle de pedido eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}