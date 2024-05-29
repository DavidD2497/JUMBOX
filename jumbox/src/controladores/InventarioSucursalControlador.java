package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.InventarioSucursalRepository;
import modelos.InventarioSucursal;

public class InventarioSucursalControlador implements InventarioSucursalRepository {
    private final Connection connection;

    public InventarioSucursalControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<InventarioSucursal> getAllInventarioSucursal() {
        List<InventarioSucursal> inventarios = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventarios_sucursal");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idInventario = resultSet.getInt("idInventario");
                InventarioSucursal inventario = new InventarioSucursal(idInventario);
                inventarios.add(inventario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    @Override
    public InventarioSucursal getInventarioSucursalById(int id) {
        InventarioSucursal inventario = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventarios_sucursal WHERE idInventario = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                inventario = new InventarioSucursal(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventario;
    }

    @Override
    public void addInventarioSucursal(InventarioSucursal inventarioSucursal) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO inventarios_sucursal (idInventario) VALUES (?)");
            statement.setInt(1, inventarioSucursal.getIdInventario());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inventario de sucursal insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInventarioSucursal(InventarioSucursal inventarioSucursal) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE inventarios_sucursal SET idInventario = ? WHERE idInventario = ?");
            statement.setInt(1, inventarioSucursal.getIdInventario());
            statement.setInt(2, inventarioSucursal.getIdInventario());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Inventario de sucursal actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInventarioSucursal(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM inventarios_sucursal WHERE idInventario = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Inventario de sucursal eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


