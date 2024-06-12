package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM informe");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idInforme = resultSet.getInt("id_informe");
                LocalDate fechaInforme = resultSet.getDate("fecha_informe").toLocalDate();

                Informe informe = new Informe(fechaInforme);
                informe.setIdInforme(idInforme);
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM informe WHERE id_informe = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                LocalDate fechaInforme = resultSet.getDate("fecha_informe").toLocalDate();
                informe = new Informe(fechaInforme);
                informe.setIdInforme(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informe;
    }

    @Override
    public void addInforme(Informe informe) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO informe (id_informe, fecha_informe) VALUES (?, ?)");
            statement.setInt(1, informe.getIdInforme());
            statement.setDate(2, Date.valueOf(LocalDate.now()));
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
        // Implementar lógica de actualización si es necesario
    }
    
    @Override
    public int obtenerUltimoIdInforme() {
        int ultimoIdInforme = -1;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(id_informe) FROM informe");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ultimoIdInforme = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoIdInforme;
    }

    @Override
    public void deleteInforme(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM informe WHERE id_informe = ?");
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
