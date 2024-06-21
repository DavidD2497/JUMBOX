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
        return getDetalleInformes("SELECT * FROM detalle_informe", -1);
    }

    @Override
    public List<DetalleInforme> getAllDetalleInformesByInformeId(int informeId) {
        return getDetalleInformes("SELECT * FROM detalle_informe WHERE id_informe = ?", informeId);
    }

    private List<DetalleInforme> getDetalleInformes(String query, int informeId) {
        List<DetalleInforme> detallesInformes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            if (informeId != -1) {
                statement.setInt(1, informeId);
            }
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idDetalle = resultSet.getInt("id_detalle_informe");
                int idInforme = resultSet.getInt("id_informe");
                String tipo = resultSet.getString("tipo");
                int idTipo = resultSet.getInt("id_tipo");

                DetalleInforme detalleInforme = new DetalleInforme(idInforme, tipo, idTipo);
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
                int idInforme = resultSet.getInt("id_informe");
                String tipo = resultSet.getString("tipo");
                int idTipo = resultSet.getInt("id_tipo");
                detalleInforme = new DetalleInforme(idInforme, tipo, idTipo);
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO detalle_informe (id_informe, tipo, id_tipo) VALUES (?, ?, ?)");
            statement.setInt(1, detalleInforme.getIdInforme());
            statement.setString(2, detalleInforme.getTipo());
            statement.setInt(3, detalleInforme.getIdTipo());

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
            PreparedStatement statement = connection.prepareStatement("UPDATE detalle_informe SET id_informe = ?, tipo = ?, id_tipo = ? WHERE id_detalle_informe = ?");
            statement.setInt(1, detalleInforme.getIdInforme());
            statement.setString(2, detalleInforme.getTipo());
            statement.setInt(3, detalleInforme.getIdTipo());
            statement.setInt(4, detalleInforme.getIdDetalle());

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

    public int obtenerUltimoIdDetalle() {
        int ultimoIdDetalle = -1;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(id_detalle_informe) AS max_id FROM detalle_informe");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ultimoIdDetalle = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ultimoIdDetalle;
    }

}

