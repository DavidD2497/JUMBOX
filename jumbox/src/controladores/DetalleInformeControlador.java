package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.DetalleInformeRepository;
import modelos.DetalleInforme;

public class DetalleInformeControlador implements DetalleInformeRepository {
    private final Connection connection;

    public DetalleInformeControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<DetalleInforme> getAllDetalleInformes() {
        List<DetalleInforme> detallesInformes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_informe");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idDetalle = resultSet.getInt("id_detalle_informe");
                int idVenta = resultSet.getInt("id_venta");
                int idInventario = resultSet.getInt("id_inventario_sucursal");
                int idInforme = resultSet.getInt("id_informe");
                int idPedido = resultSet.getInt("id_pedido");

                DetalleInforme detalleInforme = new DetalleInforme(idVenta, idInventario, idInforme,idPedido);
                detalleInforme.setIdDetalle(idDetalle);

                detallesInformes.add(detalleInforme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallesInformes;
    }

    @Override
    public DetalleInforme getDetalleInformeById(int id) {
        DetalleInforme detalleInforme = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_informe WHERE id_detalle_informe = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idDetalle = resultSet.getInt("id_detalle_informe");
                int idVenta = resultSet.getInt("id_venta");
                int idInventario = resultSet.getInt("id_inventario_sucursal");
                int idInforme = resultSet.getInt("id_informe");
                int idPedido = resultSet.getInt("id_pedido");
                detalleInforme = new DetalleInforme(idVenta, idInventario, idInforme,idPedido);
                detalleInforme.setIdDetalle(idDetalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleInforme;
    }

    @Override
    public void addDetalleInforme(DetalleInforme detalleInforme) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO detalle_informe (id_venta, id_inventario_sucursal, id_informe, id_pedido) VALUES (?, ?, ?, ?)");
            statement.setInt(1, detalleInforme.getIdVenta());
            statement.setInt(2, detalleInforme.getIdInventario());
            statement.setInt(3, detalleInforme.getIdInforme());
            statement.setInt(4, detalleInforme.getIdPedido());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Detalle de informe agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetalleInforme(DetalleInforme detalleInforme) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE detalle_informe SET id_venta = ?, id_inventario_sucursal = ?, id_informe = ?, id_pedido = ? WHERE id_detalle_informe = ?");
            statement.setInt(1, detalleInforme.getIdVenta());
            statement.setInt(2, detalleInforme.getIdInventario());
            statement.setInt(3, detalleInforme.getIdInforme());
            statement.setInt(5, detalleInforme.getIdDetalle());
            statement.setInt(4, detalleInforme.getIdPedido());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Detalle de informe actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteDetalleInforme(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM detalle_informe WHERE id_detalle_informe = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Detalle de informe eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
