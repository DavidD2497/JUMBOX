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

    private Connection connection;

    public InventarioSucursalControlador(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<InventarioSucursal> getAllInventarioSucursal() {
        List<InventarioSucursal> inventarios = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventario_sucursal");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                InventarioSucursal inventario = new InventarioSucursal();
                inventario.setIdInventario(result.getInt("id_inventario_sucursal"));
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventario_sucursal WHERE id_inventario_sucursal = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                inventario = new InventarioSucursal();
                inventario.setIdInventario(result.getInt("id_inventario_sucursal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventario;
    }

    @Override
    public void addInventarioSucursal(InventarioSucursal inventarioSucursal) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO inventario_sucursal VALUES (NULL)");
            statement.executeUpdate();
            System.out.println("Inventario de sucursal agregado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInventarioSucursal(InventarioSucursal inventarioSucursal) {
    }

    @Override
    public void deleteInventarioSucursal(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM inventario_sucursal WHERE id_inventario_sucursal = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Inventario de sucursal eliminado exitosamente.");
            } else {
                System.out.println("No se encontró ningún inventario de sucursal con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



