package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import interfaces.DepositoGeneralRepository;
import modelos.DepositoGeneral;
import modelos.DetalleDeposito;

public class DepositoGeneralControlador implements DepositoGeneralRepository {
    private final Connection connection;

    public DepositoGeneralControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<DepositoGeneral> getAllDepositosGenerales() {
        List<DepositoGeneral> depositos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM depositoGeneral");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idDeposito = resultSet.getInt("idDeposito");

                LinkedList<DetalleDeposito> listaDeposito = getDetallesDeposito(idDeposito);

                DepositoGeneral deposito = new DepositoGeneral(idDeposito, listaDeposito);
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
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM depositoGeneral WHERE idDeposito = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                LinkedList<DetalleDeposito> listaDeposito = getDetallesDeposito(id);

                deposito = new DepositoGeneral(resultSet.getInt("idDeposito"), listaDeposito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deposito;
    }

    @Override
    public void addDepositoGeneral(DepositoGeneral deposito) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO depositoGeneral(idDeposito) VALUES (?)");
            statement.setInt(1, deposito.getIdDeposito());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Depósito general insertado exitosamente");
                addDetallesDeposito(deposito.getIdDeposito(), deposito.getListaDeposito());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDepositoGeneral(DepositoGeneral deposito) {
        try {
            deleteDetallesDeposito(deposito.getIdDeposito());
            addDetallesDeposito(deposito.getIdDeposito(), deposito.getListaDeposito());
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE depositoGeneral SET idDeposito = ? WHERE idDeposito = ?");
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
            deleteDetallesDeposito(id);

            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM depositoGeneral WHERE idDeposito = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Depósito general eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private LinkedList<DetalleDeposito> getDetallesDeposito(int idDeposito) {
        LinkedList<DetalleDeposito> detalles = new LinkedList<>();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM detalleDeposito WHERE idDeposito = ?");
            statement.setInt(1, idDeposito);
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

    private void addDetallesDeposito(int idDeposito, LinkedList<DetalleDeposito> detalles) {
        try {
            for (DetalleDeposito detalle : detalles) {
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO detalleDeposito(idProducto, cantidad, idDeposito) VALUES (?, ?, ?)");
                statement.setInt(1, detalle.getIdProducto());
                statement.setInt(2, detalle.getCantidad());
                statement.setInt(3, idDeposito);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteDetallesDeposito(int idDeposito) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM detalleDeposito WHERE idDeposito = ?");
            statement.setInt(1, idDeposito);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
