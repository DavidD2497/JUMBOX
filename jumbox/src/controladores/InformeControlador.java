package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import interfaces.InformeRepository;
import modelos.Informe;


public class InformeControlador implements InformeRepository {
    private final Connection connection;

    public InformeControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Informe> getAllInformes() {
        List<Informe> informes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM informes");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idInforme = resultSet.getInt("idInforme");

                Informe informe = new Informe(idInforme);
                informes.add(informe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informes;
    }

    @Override
    public Informe getInformeById(int id) {
        Informe informe = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM informes WHERE idInforme = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idInforme = resultSet.getInt("idInforme");

                informe = new Informe(idInforme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informe;
    }
    
    @Override
    public void addInforme(Informe informe) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO informes (idInforme) VALUES (?)");
            statement.setInt(1, informe.getIdInforme());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Informe agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInforme(Informe informe) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE informes SET idInforme = ? WHERE idInforme = ?");
            statement.setInt(1, informe.getIdInforme());
            statement.setInt(2, informe.getIdInforme());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Informe actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInforme(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM informes WHERE idInforme = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Informe eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}