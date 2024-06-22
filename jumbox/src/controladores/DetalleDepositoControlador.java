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
        List<DetalleDeposito> detalles = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_deposito");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idDetalle = resultSet.getInt("id_detalle_deposito");
                int idProducto = resultSet.getInt("id_producto");
                int idDepositoGeneral = resultSet.getInt("id_deposito");
                int cantidad = resultSet.getInt("cantidad");

                DetalleDeposito detalle = new DetalleDeposito(idProducto, idDepositoGeneral, cantidad);
                detalle.setIdDetalle(idDetalle);
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    @Override
    public DetalleDeposito getDetalleDepositoById(int id) {
        DetalleDeposito detalle = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_deposito WHERE id_detalle_deposito = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                int idDepositoGeneral = resultSet.getInt("id_detalle_deposito");
                int cantidad = resultSet.getInt("cantidad");

                detalle = new DetalleDeposito(idProducto, idDepositoGeneral, cantidad);
                detalle.setIdDetalle(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalle;
    }

    @Override
    public void addDetalleDeposito(DetalleDeposito detalle) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO detalle_deposito (id_producto, id_deposito, cantidad) VALUES (?, ?, ?)");
            statement.setInt(1, detalle.getIdProducto());
            statement.setInt(2, detalle.getIdDepositoGeneral());
            statement.setInt(3, detalle.getCantidad());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Detalle de depósito agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetalleDeposito(DetalleDeposito detalle) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE detalle_deposito SET id_producto = ?, id_deposito = ?, cantidad = ? WHERE id_detalle_deposito = ?");
            statement.setInt(1, detalle.getIdProducto());
            statement.setInt(2, detalle.getIdDepositoGeneral());
            statement.setInt(3, detalle.getCantidad());
            statement.setInt(4, detalle.getIdDetalle());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Detalle de depósito actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDetalleDeposito(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM detalle_deposito WHERE id_detalle_deposito = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Detalle de depósito eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getCantidadDisponible( int idProducto) {
        int cantidadDisponible = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT cantidad FROM detalle_deposito WHERE id_producto = ?");
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cantidadDisponible = resultSet.getInt("cantidad");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cantidadDisponible;
    }


    public void actualizarCantidadProducto( int idProducto, int nuevaCantidad) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE detalle_deposito SET cantidad = ? WHERE id_producto = ?");
            statement.setInt(1, nuevaCantidad);
           
            statement.setInt(2, idProducto);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cantidad del producto actualizada exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeProducto(int idProducto) {
        boolean existe = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_deposito WHERE id_producto = ?");
            
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();

            existe = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
}

