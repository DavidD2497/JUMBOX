package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import modelos.DetalleVenta;
import modelos.Venta;

public class VentaControlador {
    private final Connection connection;

<<<<<<< HEAD
    public VentaControlador(Connection connection) {
        this.connection = connection;
=======
    public VentaControlador() {
    	this.connection = DatabaseConnection.getInstance().getConnection();
>>>>>>> david
    }

    public List<Venta> getAllVentas() {
        List<Venta> ventas = new ArrayList<>();
        String query = "SELECT * FROM venta";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idVenta = resultSet.getInt("idVenta");
                double montoTotal = resultSet.getDouble("montoTotal");
                String tipoPago = resultSet.getString("tipoPago");

                List<DetalleVenta> detallesVenta = getDetallesVenta(idVenta);
                Venta venta = new Venta(idVenta, montoTotal, tipoPago, detallesVenta);
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ventas;
    }

    private List<DetalleVenta> getDetallesVenta(int idVenta) {
        List<DetalleVenta> detallesVenta = new LinkedList<>();
        String query = "SELECT * FROM detalle_venta WHERE idVenta = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idVenta);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idDetalle = resultSet.getInt("idDetalle");
                    int idProducto = resultSet.getInt("idProducto");
                    double monto = resultSet.getDouble("monto");
                    int cantidad = resultSet.getInt("cantidad");

                    DetalleVenta detalle = new DetalleVenta(idProducto, idVenta, monto, cantidad, idDetalle);
                    detallesVenta.add(detalle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detallesVenta;
    }

    public void addVenta(Venta venta) {
        String queryVenta = "INSERT INTO venta (idVenta, montoTotal, tipoPago) VALUES (?, ?, ?)";
        String queryDetalle = "INSERT INTO detalle_venta (idDetalle, idVenta, idProducto, monto, cantidad) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statementVenta = connection.prepareStatement(queryVenta);
             PreparedStatement statementDetalle = connection.prepareStatement(queryDetalle)) {

            statementVenta.setInt(1, venta.getIdVenta());
            statementVenta.setDouble(2, venta.getMontoTotal());
            statementVenta.setString(3, venta.getTipoPago());
            statementVenta.executeUpdate();


            for (DetalleVenta detalle : venta.getListaVenta()) {
                statementDetalle.setInt(1, detalle.getIdDetalle());
                statementDetalle.setInt(2, venta.getIdVenta());
                statementDetalle.setInt(3, detalle.getIdProducto());
                statementDetalle.setDouble(4, detalle.getMonto());
                statementDetalle.setInt(5, detalle.getCantidad());
                statementDetalle.executeUpdate();
            }

            System.out.println("Venta agregada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateVenta(Venta venta) {
        String queryVenta = "UPDATE venta SET montoTotal = ?, tipoPago = ? WHERE idVenta = ?";
        String queryDeleteDetalle = "DELETE FROM detalle_venta WHERE idVenta = ?";
        String queryInsertDetalle = "INSERT INTO detalle_venta (idDetalle, idVenta, idProducto, monto, cantidad) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statementVenta = connection.prepareStatement(queryVenta);
             PreparedStatement statementDeleteDetalle = connection.prepareStatement(queryDeleteDetalle);
             PreparedStatement statementInsertDetalle = connection.prepareStatement(queryInsertDetalle)) {
            connection.setAutoCommit(false);


            statementVenta.setDouble(1, venta.getMontoTotal());
            statementVenta.setString(2, venta.getTipoPago());
            statementVenta.setInt(3, venta.getIdVenta());
            statementVenta.executeUpdate();


            statementDeleteDetalle.setInt(1, venta.getIdVenta());
            statementDeleteDetalle.executeUpdate();


            for (DetalleVenta detalle : venta.getListaVenta()) {
                statementInsertDetalle.setInt(1, detalle.getIdDetalle());
                statementInsertDetalle.setInt(2, venta.getIdVenta());
                statementInsertDetalle.setInt(3, detalle.getIdProducto());
                statementInsertDetalle.setDouble(4, detalle.getMonto());
                statementInsertDetalle.setInt(5, detalle.getCantidad());
                statementInsertDetalle.executeUpdate();
            }

            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("Venta actualizada exitosamente.");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void deleteVenta(int idVenta) {
        String queryDeleteVenta = "DELETE FROM venta WHERE idVenta = ?";
        String queryDeleteDetalle = "DELETE FROM detalle_venta WHERE idVenta = ?";

        try (PreparedStatement statementDeleteVenta = connection.prepareStatement(queryDeleteVenta);
             PreparedStatement statementDeleteDetalle = connection.prepareStatement(queryDeleteDetalle)) {
            connection.setAutoCommit(false);


            statementDeleteVenta.setInt(1, idVenta);
            statementDeleteVenta.executeUpdate();


            statementDeleteDetalle.setInt(1, idVenta);
            statementDeleteDetalle.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("Venta eliminada exitosamente.");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

}
