package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import modelos.DetalleInventario;
import modelos.InventarioSucursal;

public class InventarioSucursalControlador {
    private final Connection connection;

    public InventarioSucursalControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public InventarioSucursal getInventarioSucursalById(int idInventario) {
        InventarioSucursal inventarioSucursal = null;
        LinkedList<DetalleInventario> listaInventario = new LinkedList<>();
        try {
            String query = "SELECT * FROM detalle_inventario WHERE id_inventario = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idInventario);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                int idDescuento = resultSet.getInt("id_descuento");
                int idDetalle = resultSet.getInt("id_detalle");
                int cantidad = resultSet.getInt("cantidad");
                DetalleInventario detalleInventario = new DetalleInventario(idProducto, idDescuento, idDetalle, cantidad);
                listaInventario.add(detalleInventario);
            }

            inventarioSucursal = new InventarioSucursal(idInventario, listaInventario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarioSucursal;
    }

    public void addInventarioSucursal(InventarioSucursal inventarioSucursal) {
        try {
            String query = "INSERT INTO inventario_sucursal (id_inventario) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, inventarioSucursal.getIdInventario());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idInventario = generatedKeys.getInt(1);
                    inventarioSucursal.setIdInventario(idInventario);
                    for (DetalleInventario detalle : inventarioSucursal.getListaInventario()) {
                        addDetalleInventario(idInventario, detalle);
                    }
                    System.out.println("El inventario de la sucursal fue agregado exitosamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addDetalleInventario(int idInventario, DetalleInventario detalleInventario) {
        try {
            String query = "INSERT INTO detalle_inventario (id_inventario, id_producto, id_descuento, id_detalle, cantidad) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idInventario);
            statement.setInt(2, detalleInventario.getIdProducto());
            statement.setInt(3, detalleInventario.getIdDescuento());
            statement.setInt(4, detalleInventario.getIdDetalle());
            statement.setInt(5, detalleInventario.getCantidad());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInventarioSucursal(InventarioSucursal inventarioSucursal) {
        try {
            for (DetalleInventario detalle : inventarioSucursal.getListaInventario()) {
                updateDetalleInventario(inventarioSucursal.getIdInventario(), detalle);
            }
            System.out.println("El inventario de la sucursal fue actualizado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDetalleInventario(int idInventario, DetalleInventario detalleInventario) {
        try {
            String query = "UPDATE detalle_inventario SET cantidad = ?, id_descuento = ?, id_detalle = ? WHERE id_inventario = ? AND id_producto = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, detalleInventario.getCantidad());
            statement.setInt(2, detalleInventario.getIdDescuento());
            statement.setInt(3, detalleInventario.getIdDetalle());
            statement.setInt(4, idInventario);
            statement.setInt(5, detalleInventario.getIdProducto());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInventarioSucursal(int idInventario) {
        try {
            String query = "DELETE FROM detalle_inventario WHERE id_inventario = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idInventario);
            statement.executeUpdate();

            query = "DELETE FROM inventario_sucursal WHERE id_inventario = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idInventario);
            statement.executeUpdate();

            System.out.println("El inventario de la sucursal fue eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDetalleInventario(int idInventario, int idProducto) {
        try {
            String query = "DELETE FROM detalle_inventario WHERE id_inventario = ? AND id_producto = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idInventario);
            statement.setInt(2, idProducto);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}