package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.DetalleVenta;

public class DetalleVentaControlador {
    private final Connection connection;

<<<<<<< HEAD
    public DetalleVentaControlador(Connection connection) {
        this.connection = connection;
=======
    public DetalleVentaControlador() {
    	this.connection = DatabaseConnection.getInstance().getConnection();
>>>>>>> david
    }

    public void addDetalleVenta(DetalleVenta detalleVenta) {
        String query = "INSERT INTO detalle_venta (idProducto, idVenta, monto, cantidad, idDetalle) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, detalleVenta.getIdProducto());
            statement.setInt(2, detalleVenta.getIdVenta());
            statement.setDouble(3, detalleVenta.getMonto());
            statement.setInt(4, detalleVenta.getCantidad());
            statement.setInt(5, detalleVenta.getIdDetalle());
            statement.executeUpdate();
            System.out.println("Detalle de venta agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DetalleVenta> getDetallesPorVenta(int idVenta) {
        List<DetalleVenta> detalles = new ArrayList<>();
        String query = "SELECT * FROM detalle_venta WHERE idVenta = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idVenta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DetalleVenta detalle = new DetalleVenta(
                        resultSet.getInt("idProducto"),
                        resultSet.getInt("idVenta"),
                        resultSet.getDouble("monto"),
                        resultSet.getInt("cantidad"),
                        resultSet.getInt("idDetalle")
                );
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    public void updateDetalleVenta(DetalleVenta detalleVenta) {
        String query = "UPDATE detalle_venta SET idProducto = ?, monto = ?, cantidad = ? WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, detalleVenta.getIdProducto());
            statement.setDouble(2, detalleVenta.getMonto());
            statement.setInt(3, detalleVenta.getCantidad());
            statement.setInt(4, detalleVenta.getIdDetalle());
            statement.executeUpdate();
            System.out.println("Detalle de venta actualizado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDetalleVenta(int idDetalle) {
        String query = "DELETE FROM detalle_venta WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDetalle);
            statement.executeUpdate();
            System.out.println("Detalle de venta eliminado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getIdProducto(int idDetalle) {
        String query = "SELECT idProducto FROM detalle_venta WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDetalle);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("idProducto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int getCantidad(int idDetalle) {
        String query = "SELECT cantidad FROM detalle_venta WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDetalle);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("cantidad");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
