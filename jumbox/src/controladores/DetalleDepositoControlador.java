package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.DetalleDepRepository;
import modelos.DetalleDeposito;

public class DetalleDepositoControlador implements DetalleDepRepository {
    private final Connection connection;

    public DetalleDepositoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<DetalleDeposito> getAllDetalleDepositos() {
        List<DetalleDeposito> detalles = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalleDeposito");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleDeposito detalle = new DetalleDeposito(
                    resultSet.getInt("idDetalle"),
                    resultSet.getInt("idProducto"),
                    resultSet.getInt("cantidad")
                );
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    
    
    @Override
    public DetalleDeposito getDetalleDepositoById(int id) {
        DetalleDeposito detalle = null;
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM detalleDeposito WHERE idDetalle = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                detalle = new DetalleDeposito(
                    resultSet.getInt("idDetalle"),
                    resultSet.getInt("idProducto"),
                    resultSet.getInt("cantidad")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalle;
    }

    @Override
    public void addDetalleDeposito(DetalleDeposito detalle) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO detalleDeposito(idDetalle, idProducto, cantidad) VALUES (?, ?, ?)");
            statement.setInt(1, detalle.getIdDetalle());
            statement.setInt(2, detalle.getIdProducto());
            statement.setInt(3, detalle.getCantidad());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Detalle de depósito insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetalleDeposito(DetalleDeposito detalle) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE detalleDeposito SET idProducto = ?, cantidad = ? WHERE idDetalle = ?");
            statement.setInt(1, detalle.getIdProducto());
            statement.setInt(2, detalle.getCantidad());
            statement.setInt(3, detalle.getIdDetalle());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Detalle de depósito actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDetalleDeposito(int id) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM detalleDeposito WHERE idDetalle = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Detalle de depósito eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
