package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import interfaces.VentaRepository;
import modelos.Venta;

public class VentaControlador implements VentaRepository {
    private final Connection connection;

    public VentaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Venta> getAllVentas() {
        List<Venta> ventas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM venta");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idVenta = resultSet.getInt("id_venta");
                double montoTotal = resultSet.getDouble("monto_total");
                String tipoPago = resultSet.getString("tipo_pago");
                LocalDate fechaVenta = resultSet.getDate("fecha_venta").toLocalDate();

                Venta venta = new Venta(montoTotal, tipoPago, fechaVenta);
                venta.setIdVenta(idVenta);
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    @Override
    public Venta getVentaById(int id) {
        Venta venta = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM venta WHERE id_venta = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idVenta = resultSet.getInt("id_venta");
                double montoTotal = resultSet.getDouble("monto_total");
                String tipoPago = resultSet.getString("tipo_pago");
                LocalDate fechaVenta = resultSet.getDate("fecha_venta").toLocalDate();

                venta = new Venta(montoTotal, tipoPago, fechaVenta);
                venta.setIdVenta(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venta;
    }

    @Override
    public void addVenta(Venta venta) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO venta (monto_total, tipo_pago, fecha_venta) VALUES (?, ?, ?)");
            statement.setDouble(1, venta.getMontoTotal());
            statement.setString(2, venta.getTipoPago());
            statement.setDate(3, Date.valueOf(venta.getFechaVenta()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Venta agregada exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVenta(Venta venta) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE venta SET monto_total = ?, tipo_pago = ?, fecha_venta = ? WHERE id_venta = ?");
            statement.setDouble(1, venta.getMontoTotal());
            statement.setString(2, venta.getTipoPago());
            statement.setDate(3, Date.valueOf(venta.getFechaVenta()));
            statement.setInt(4, venta.getIdVenta());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Venta actualizada exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVenta(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM venta WHERE id_venta = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Venta eliminada exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int obtenerUltimoIdVenta() {
        int ultimoIdVenta = -1;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(id_venta) FROM venta");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ultimoIdVenta = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoIdVenta;
    }
}
