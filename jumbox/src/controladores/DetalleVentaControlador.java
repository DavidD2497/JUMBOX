package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import interfaces.DetalleVentaRepository;
import modelos.DetalleVenta;

public class DetalleVentaControlador implements DetalleVentaRepository {
    private final Connection connection;

    public DetalleVentaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<DetalleVenta> getAllDetalleVentas() {
        List<DetalleVenta> detalleVentas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_venta");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                int idVenta = resultSet.getInt("id_venta");
                double monto = resultSet.getDouble("monto");
                int cantidad = resultSet.getInt("cantidad");

                DetalleVenta detalleVenta = new DetalleVenta(idProducto, idVenta, monto, cantidad);
                detalleVentas.add(detalleVenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleVentas;
    }

    @Override
    public DetalleVenta getDetalleVentaById(int id) {
        DetalleVenta detalleVenta = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_venta WHERE id_detalle_venta = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                int idVenta = resultSet.getInt("id_venta");
                double monto = resultSet.getDouble("monto");
                int cantidad = resultSet.getInt("cantidad");

                detalleVenta = new DetalleVenta(idProducto, idVenta, monto, cantidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleVenta;
    }

    @Override
    public void addDetalleVenta(DetalleVenta detalleVenta) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO detalle_venta (id_producto, id_venta, monto, cantidad) VALUES (?, ?, ?, ?)");
=======
import modelos.DetalleVenta;

public class DetalleVentaControlador {
    private final Connection connection;

    public DetalleVentaControlador() {
    	this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void addDetalleVenta(DetalleVenta detalleVenta) {
        String query = "INSERT INTO detalle_venta (idProducto, idVenta, monto, cantidad, idDetalle) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
>>>>>>> origin/vicky
            statement.setInt(1, detalleVenta.getIdProducto());
            statement.setInt(2, detalleVenta.getIdVenta());
            statement.setDouble(3, detalleVenta.getMonto());
            statement.setInt(4, detalleVenta.getCantidad());
<<<<<<< HEAD

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Detalle de venta agregado exitosamente.");
            }
=======
            statement.setInt(5, detalleVenta.getIdDetalle());
            statement.executeUpdate();
            System.out.println("Detalle de venta agregado con éxito.");
>>>>>>> origin/vicky
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    @Override
    public void updateDetalleVenta(DetalleVenta detalleVenta) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE detalle_venta SET id_producto = ?, id_venta = ?, monto = ?, cantidad = ? WHERE id_detalle_venta = ?");
            statement.setInt(1, detalleVenta.getIdProducto());
            statement.setInt(2, detalleVenta.getIdVenta());
            statement.setDouble(3, detalleVenta.getMonto());
            statement.setInt(4, detalleVenta.getCantidad());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Detalle de venta actualizado exitosamente.");
            }
=======
    public List<DetalleVenta> getDetallesPorVenta(int idVenta) {
        List<DetalleVenta> detalles = new ArrayList<>();
        String query = "SELECT * FROM detalle_venta WHERE idVenta = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idVenta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DetalleVenta detalle = new DetalleVenta(
                        resultSet.getInt("idProducto"),
                        resultSet.getInt("idVenta"),
                        resultSet.getDouble("monto"),
                        resultSet.getInt("cantidad"),
                        resultSet.getInt("idDetalle")
                );
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    public void updateDetalleVenta(DetalleVenta detalleVenta) {
        String query = "UPDATE detalle_venta SET idProducto = ?, monto = ?, cantidad = ? WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, detalleVenta.getIdProducto());
            statement.setDouble(2, detalleVenta.getMonto());
            statement.setInt(3, detalleVenta.getCantidad());
            statement.setInt(4, detalleVenta.getIdDetalle());
            statement.executeUpdate();
            System.out.println("Detalle de venta actualizado con éxito.");
>>>>>>> origin/vicky
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    @Override
    public void deleteDetalleVenta(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM detalle_venta WHERE id_detalle_venta = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Detalle de venta eliminado exitosamente.");
=======
    public void deleteDetalleVenta(int idDetalle) {
        String query = "DELETE FROM detalle_venta WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDetalle);
            statement.executeUpdate();
            System.out.println("Detalle de venta eliminado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getIdProducto(int idDetalle) {
        String query = "SELECT idProducto FROM detalle_venta WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDetalle);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("idProducto");
>>>>>>> origin/vicky
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
    }
}

=======
        return -1;
    }
    
    public int getCantidad(int idDetalle) {
        String query = "SELECT cantidad FROM detalle_venta WHERE idDetalle = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDetalle);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("cantidad");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
>>>>>>> origin/vicky
