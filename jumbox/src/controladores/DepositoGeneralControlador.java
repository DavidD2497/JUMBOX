package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.DepositoGeneral;

import interfaces.DepositoGeneralRepository;

public class DepositoGeneralControlador implements DepositoGeneralRepository {
    private final Connection connection;

    public DepositoGeneralControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<DepositoGeneral> getAllDepositosGenerales() {
        List<DepositoGeneral> depositos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM deposito_general");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idDeposito = resultSet.getInt("id_deposito");
                DepositoGeneral deposito = new DepositoGeneral();
                deposito.setIdDeposito(idDeposito);
                depositos.add(deposito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositos;
    }

    @Override
    public DepositoGeneral getDepositoGeneralById(int id) {
        DepositoGeneral deposito = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM deposito_general WHERE id_deposito = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                deposito = new DepositoGeneral();
                deposito.setIdDeposito(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deposito;
    }

    @Override
    public void addDepositoGeneral(DepositoGeneral deposito) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO deposito_general () VALUES ()");

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Depósito general agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDepositoGeneral(DepositoGeneral deposito) {
        // No se actualizan los depósitos generales en esta implementación
    }

    @Override
    public void deleteDepositoGeneral(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM deposito_general WHERE id_deposito = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Depósito general eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

