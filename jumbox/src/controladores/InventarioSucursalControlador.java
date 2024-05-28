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

public class InventarioSucursalControlador implements InventarioSucursalRepository {
    private final Connection connection;

    public InventarioSucursalControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<InventarioSucursal> getAllInventarioSucursal() {
        List<InventarioSucursal> inventariosSucursal = new LinkedList<>();
        try {
            String query = "SELECT * FROM inventario_sucursal";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idInventario = resultSet.getInt("id_inventario");
                inventariosSucursal.add(getInventarioSucursalById(idInventario));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventariosSucursal;
    }

    @Override
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

    @Override
    public void addInventarioSucursal(InventarioSucursal inventarioSucursal) {
        try {
            String query = "INSERT INTO inventario_sucursal (id_inventario) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, inventarioSucursal.getIdInventario());
            statement.executeUpdate();

            for (DetalleInventario detalle : inventarioSucursal.getListaInventario()) {
                query = "INSERT INTO detalle_inventario (id_inventario, id_producto, id_descuento, id_detalle, cantidad) VALUES (?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, inventarioSucursal.getIdInventario());
                statement.setInt(2, detalle.getIdProducto());
                statement.setInt(3, detalle.getIdDescuento());
                statement.setInt(4, detalle.getIdDetalle());
                statement.setInt(5, detalle.getCantidad());
                statement.executeUpdate();
            }
            System.out.println("Inventario de sucursal agregado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInventarioSucursal(InventarioSucursal inventarioSucursal) {
        try {
            String query = "DELETE FROM detalle_inventario WHERE id_inventario = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, inventarioSucursal.getIdInventario());
            statement.executeUpdate();

            for (DetalleInventario detalle : inventarioSucursal.getListaInventario()) {
                query = "INSERT INTO detalle_inventario (id_inventario, id_producto, id_descuento, id_detalle, cantidad) VALUES (?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, inventarioSucursal.getIdInventario());
                statement.setInt(2, detalle.getIdProducto());
                statement.setInt(3, detalle.getIdDescuento());
                statement.setInt(4, detalle.getIdDetalle());
                statement.setInt(5, detalle.getCantidad());
                statement.executeUpdate();
            }
            System.out.println("Inventario de sucursal actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
    public int getCantidadDisponible(int idProducto) {
        int cantidadDisponible = 0;
        String query = "SELECT SUM(cantidad) AS total FROM detalle_inventario WHERE id_producto = ?";

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

    @Override
    public void actualizarCantidadProducto(int idProducto, int nuevaCantidad) {
        String query = "UPDATE detalle_inventario SET cantidad = ? WHERE id_producto = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, idProducto);
            statement.executeUpdate();
            System.out.println("Inventario actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean existeProducto(int idProducto) {
        boolean existe = false;
        String query = "SELECT * FROM detalle_inventario WHERE id_producto = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idProducto);
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
