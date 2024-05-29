package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_ventas");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("idProducto");
                int idVenta = resultSet.getInt("idVenta");
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_ventas WHERE idDetalle = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idProducto = resultSet.getInt("idProducto");
                int idVenta = resultSet.getInt("idVenta");
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO detalle_ventas (idProducto, idVenta, monto, cantidad) VALUES (?, ?, ?, ?)");
            statement.setInt(1, detalleVenta.getIdProducto());
            statement.setInt(2, detalleVenta.getIdVenta());
            statement.setDouble(3, detalleVenta.getMonto());
            statement.setInt(4, detalleVenta.getCantidad());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Detalle de venta agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetalleVenta(DetalleVenta detalleVenta) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE detalle_ventas SET idProducto = ?, idVenta = ?, monto = ?, cantidad = ? WHERE idDetalle = ?");
            statement.setInt(1, detalleVenta.getIdProducto());
            statement.setInt(2, detalleVenta.getIdVenta());
            statement.setDouble(3, detalleVenta.getMonto());
            statement.setInt(4, detalleVenta.getCantidad());
            statement.setInt(5, detalleVenta.getIdDetalle());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Detalle de venta actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDetalleVenta(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM detalle_ventas WHERE idDetalle = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Detalle de venta eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

