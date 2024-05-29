package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import modelos.Pedido;
import modelos.DetallePedido;
import interfaces.PedidoRepository;

public class PedidoControlador implements PedidoRepository {
    private final Connection connection;

    public PedidoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Pedido> getAllPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedidos");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pedido pedido = mapResultSetToPedido(resultSet);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public Pedido getPedidoById(int id) {
        Pedido pedido = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedidos WHERE codigoPedido = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                pedido = mapResultSetToPedido(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }

    @Override
    public void addPedido(Pedido pedido) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO pedidos (fechaEntrega) VALUES (?)");
            statement.setDate(1, Date.valueOf(pedido.getFechaEntrega()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Pedido insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePedido(Pedido pedido) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE pedidos SET fechaEntrega = ? WHERE codigoPedido = ?");
            statement.setDate(1, Date.valueOf(pedido.getFechaEntrega()));
            statement.setInt(2, pedido.getCodigoPedido());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Pedido actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePedido(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM pedidos WHERE codigoPedido = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Pedido eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Pedido mapResultSetToPedido(ResultSet resultSet) throws SQLException {
        int codigoPedido = resultSet.getInt("codigoPedido");
        Date fechaEntrega = resultSet.getDate("fechaEntrega");
        LinkedList<DetallePedido> listaPedidos = getDetallesForPedido(codigoPedido);

        return new Pedido(codigoPedido, fechaEntrega.toLocalDate(), listaPedidos);
    }

    private LinkedList<DetallePedido> getDetallesForPedido(int codigoPedido) throws SQLException {
        LinkedList<DetallePedido> listaPedidos = new LinkedList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_pedidos WHERE codigoPedido = ?");
        statement.setInt(1, codigoPedido);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            DetallePedido detalle = new DetallePedido(
                resultSet.getInt("idDetalle"),
                resultSet.getInt("idProducto"),
                resultSet.getInt("cantidad")
            );
            listaPedidos.add(detalle);
        }

        return listaPedidos;
    }
}