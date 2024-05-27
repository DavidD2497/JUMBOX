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

<<<<<<< HEAD
    public DetalleInformeControlador(Connection connection) {
        this.connection = connection;
=======
    public DetalleInformeControlador() {
    	this.connection = DatabaseConnection.getInstance().getConnection();
>>>>>>> david
    }

    @Override
    public List<DetalleInforme> getAllDetalleInformes() {
        List<DetalleInforme> detalles = new ArrayList<>();
        String query = "SELECT * FROM detalle_informe";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DetalleInforme detalle = new DetalleInforme(
                        resultSet.getInt("idDetalle"),
                        resultSet.getInt("idVenta"),
                        resultSet.getInt("idInventario")
                );
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    @Override
    public DetalleInforme getDetalleInformeById(int id) {
        DetalleInforme detalle = null;
        String query = "SELECT * FROM detalle_informe WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                detalle = new DetalleInforme(
                        resultSet.getInt("idDetalle"),
                        resultSet.getInt("idVenta"),
                        resultSet.getInt("idInventario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalle;
    }

    @Override
    public void addDetalleInforme(DetalleInforme detalleInforme) {
        String query = "INSERT INTO detalle_informe (idDetalle, idVenta, idInventario) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, detalleInforme.getIdDetalle());
            statement.setInt(2, detalleInforme.getIdVenta());
            statement.setInt(3, detalleInforme.getIdInventario());
            statement.executeUpdate();
            System.out.println("Detalle de informe agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetalleInforme(DetalleInforme detalleInforme) {
        String query = "UPDATE detalle_informe SET idVenta = ?, idInventario = ? WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, detalleInforme.getIdVenta());
            statement.setInt(2, detalleInforme.getIdInventario());
            statement.setInt(3, detalleInforme.getIdDetalle());
            statement.executeUpdate();
            System.out.println("Detalle de informe actualizado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDetalleInforme(int id) {
        String query = "DELETE FROM detalle_informe WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Detalle de informe eliminado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
