package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.DepositoGeneralRepository;
import modelos.DepositoGeneral;

public class DepositoGeneralControlador implements DepositoGeneralRepository {
    private final Connection connection;

    public DepositoGeneralControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<DepositoGeneral> getAllDepositosGenerales() {
        List<DepositoGeneral> depositosGenerales = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM depositos_generales");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idDeposito = resultSet.getInt("idDeposito");
                DepositoGeneral depositoGeneral = new DepositoGeneral(idDeposito);
                depositosGenerales.add(depositoGeneral);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositosGenerales;
    }

    @Override
    public DepositoGeneral getDepositoGeneralById(int id) {
        DepositoGeneral depositoGeneral = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM depositos_generales WHERE idDeposito = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                depositoGeneral = new DepositoGeneral(resultSet.getInt("idDeposito"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositoGeneral;
    }

    @Override
    public void addDepositoGeneral(DepositoGeneral deposito) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `depositos_generales`(`idDeposito`) VALUES (?)");
            statement.setInt(1, deposito.getIdDeposito());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Depósito general agregado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDepositoGeneral(DepositoGeneral deposito) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `depositos_generales` SET `idDeposito` = ? WHERE `idDeposito` = ?");
            statement.setInt(1, deposito.getIdDeposito());
            statement.setInt(2, deposito.getIdDeposito());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Depósito general actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDepositoGeneral(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `depositos_generales` WHERE `idDeposito` = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Depósito general eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

