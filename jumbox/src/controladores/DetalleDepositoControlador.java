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
        List<DetalleDeposito> detalleDepositos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_deposito");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idDetalle = resultSet.getInt("idDetalle");
                int idDepositoGeneral = resultSet.getInt("idDepositoGeneral");
                int idProducto = resultSet.getInt("idProducto");
                int cantidad = resultSet.getInt("cantidad");
                DetalleDeposito detalleDeposito = new DetalleDeposito(idDetalle, idDepositoGeneral, idProducto, cantidad);
                detalleDepositos.add(detalleDeposito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleDepositos;
    }

    @Override
    public DetalleDeposito getDetalleDepositoById(int id) {
        DetalleDeposito detalleDeposito = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_deposito WHERE idDetalle = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idDepositoGeneral = resultSet.getInt("idDepositoGeneral");
                int idProducto = resultSet.getInt("idProducto");
                int cantidad = resultSet.getInt("cantidad");
                detalleDeposito = new DetalleDeposito(id, idDepositoGeneral, idProducto, cantidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleDeposito;
    }

    @Override
    public void addDetalleDeposito(DetalleDeposito detalle) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO detalle_deposito(idDepositoGeneral, idProducto, cantidad) VALUES (?, ?, ?)");
            statement.setInt(1, detalle.getIdDepositoGeneral());
            statement.setInt(2, detalle.getIdProducto());
            statement.setInt(3, detalle.getCantidad());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Detalle de deposito agregado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetalleDeposito(DetalleDeposito detalle) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE detalle_deposito SET idDepositoGeneral = ?, idProducto = ?, cantidad = ? WHERE idDetalle = ?");
            statement.setInt(1, detalle.getIdDepositoGeneral());
            statement.setInt(2, detalle.getIdProducto());
            statement.setInt(3, detalle.getCantidad());
            statement.setInt(4, detalle.getIdDetalle());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Detalle de deposito actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDetalleDeposito(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM detalle_deposito WHERE idDetalle = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Detalle de deposito eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getCantidadDisponible(int idDepositoGeneral, int idProducto) {
        int cantidadDisponible = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT cantidad FROM detalle_deposito WHERE idDepositoGeneral = ? AND idProducto = ?");
            statement.setInt(1, idDepositoGeneral);
            statement.setInt(2, idProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cantidadDisponible = resultSet.getInt("cantidad");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cantidadDisponible;
    }


    public void actualizarCantidadProducto(int idDepositoGeneral, int idProducto, int nuevaCantidad) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE detalle_deposito SET cantidad = ? WHERE idDepositoGeneral = ? AND idProducto = ?");
            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, idDepositoGeneral);
            statement.setInt(3, idProducto);
            statement.executeUpdate();
            System.out.println("Cantidad del producto en el dep贸sito general actualizada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean existeProducto(int idDepositoGeneral, int idProducto) {
        boolean existe = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_deposito WHERE idDepositoGeneral = ? AND idProducto = ?");
            statement.setInt(1, idDepositoGeneral);
            statement.setInt(2, idProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
}