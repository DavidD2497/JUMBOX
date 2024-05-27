package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import interfaces.InventarioSucursalRepository;
import modelos.DetalleInventario;
import modelos.InventarioSucursal;
import modelos.Producto;

public class InventarioSucursalControlador implements InventarioSucursalRepository {
    private final Connection connection;

    public InventarioSucursalControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<InventarioSucursal> getAllInventarioSucursal() {
        List<InventarioSucursal> inventariosSucursal = new LinkedList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventario_sucursal");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                InventarioSucursal inventarioSucursal = mapResultSetToInventarioSucursal(resultSet);
                inventariosSucursal.add(inventarioSucursal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventariosSucursal;
    }

    @Override
    public InventarioSucursal getInventarioSucursalById(int id) {
        InventarioSucursal inventarioSucursal = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventario_sucursal WHERE idInventario = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                inventarioSucursal = mapResultSetToInventarioSucursal(resultSet);
            } else {
                System.out.println("No se encontró el inventario de la sucursal con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarioSucursal;
    }

    @Override
    public void addInventarioSucursal(InventarioSucursal inventarioSucursal) {
        try {
            String query = "INSERT INTO inventario_sucursal (idInventario) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, inventarioSucursal.getIdInventario());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("El inventario de la sucursal fue agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInventarioSucursal(InventarioSucursal inventarioSucursal) {
        try {
            String query = "UPDATE inventario_sucursal SET idInventario = ? WHERE idInventario = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, inventarioSucursal.getIdInventario());
            statement.setInt(2, inventarioSucursal.getIdInventario());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El inventario de la sucursal fue actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInventarioSucursal(int id) {
        try {
            String query = "DELETE FROM inventario_sucursal WHERE idInventario = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("El inventario de la sucursal fue eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private InventarioSucursal mapResultSetToInventarioSucursal(ResultSet resultSet) throws SQLException {
        int idInventario = resultSet.getInt("idInventario");

        // Obtenemos la lista de DetalleInventario asociada a este inventario
        LinkedList<DetalleInventario> listaInventario = getDetallesInventarioByInventarioId(idInventario);

        return new InventarioSucursal(idInventario, listaInventario);
    }

    private LinkedList<DetalleInventario> getDetallesInventarioByInventarioId(int idInventario) throws SQLException {
        LinkedList<DetalleInventario> listaInventario = new LinkedList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalles_inventario WHERE idInventario = ?");
            statement.setInt(1, idInventario);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idDetalle = resultSet.getInt("idDetalle");
                int idProducto = resultSet.getInt("idProducto");
                int idDescuento = resultSet.getInt("idDescuento");
                int cantidad = resultSet.getInt("cantidad");

                // Se asume que hay un método para obtener Producto por idProducto
                Producto producto = obtenerProductoPorId(idProducto);

                DetalleInventario detalleInventario = new DetalleInventario(producto, idDescuento, idDetalle, cantidad);
                listaInventario.add(detalleInventario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return listaInventario;
    }

    private Producto obtenerProductoPorId(int idProducto) throws SQLException {
        Producto producto = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM productos WHERE idProducto = ?");
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                producto = new Producto(
                    resultSet.getInt("idProducto"),
                    resultSet.getString("nombreProducto"),
                    resultSet.getString("categoria"),
                    resultSet.getDouble("precio"),
                    resultSet.getDate("fechaVencimiento").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return producto;
    }
    
    public int getCantidadDisponible(int idProducto) {
        int cantidadDisponible = 0;
        String query = "SELECT SUM(cantidad) AS total FROM detalles_inventario WHERE idProducto = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cantidadDisponible = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cantidadDisponible;
    }
    
    public void actualizarCantidadProducto(int idProducto, int nuevaCantidad) {
        String query = "UPDATE detalles_inventario SET cantidad = ? WHERE idProducto = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, idProducto);
            statement.executeUpdate();
            System.out.println("Inventario actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}