package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.DetalleInforme;
import modelos.Informe;

public class InformeControlador {
    private final Connection connection;

<<<<<<< HEAD
    public InformeControlador(Connection connection) {
        this.connection = connection;
=======
    public InformeControlador() {
    	this.connection = DatabaseConnection.getInstance().getConnection();
>>>>>>> david
    }

    public void addInforme(Informe informe) {
        String query = "INSERT INTO informe (idInforme) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, informe.getIdInforme());
            statement.executeUpdate();
            System.out.println("Informe agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Informe> getAllInformes() {
        List<Informe> informes = new ArrayList<>();
        String query = "SELECT * FROM informe";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idInforme = resultSet.getInt("idInforme");
                List<DetalleInforme> listaDetalles = getDetallesInforme(idInforme);
                Informe informe = new Informe(idInforme, listaDetalles);
                informes.add(informe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informes;
    }

    public Informe getInformeById(int id) {
        Informe informe = null;
        String query = "SELECT * FROM informe WHERE idInforme = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                List<DetalleInforme> listaDetalles = getDetallesInforme(id);
                informe = new Informe(id, listaDetalles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informe;
    }

    private List<DetalleInforme> getDetallesInforme(int idInforme) {
        List<DetalleInforme> detalles = new ArrayList<>();
        String query = "SELECT * FROM detalle_informe WHERE idInforme = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idInforme);
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

    public void deleteInforme(int id) {
        String query = "DELETE FROM informe WHERE idInforme = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Informe eliminado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
