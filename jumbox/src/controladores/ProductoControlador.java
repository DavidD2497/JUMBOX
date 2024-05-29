package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import interfaces.ProductoRepository;
import modelos.Producto;

public class ProductoControlador implements ProductoRepository {
    private final Connection connection;

    public ProductoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM producto");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                String nombreProducto = resultSet.getString("nombre_producto");
                String categoria = resultSet.getString("categoria");
                double precio = resultSet.getDouble("precio");
                LocalDate fechaVencimiento = resultSet.getDate("fecha_vencimiento").toLocalDate();

                Producto producto = new Producto(idProducto, nombreProducto, categoria, precio, fechaVencimiento);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Producto getProductoById(int id) {
        Producto producto = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM producto WHERE id_producto = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombreProducto = resultSet.getString("nombre_producto");
                String categoria = resultSet.getString("categoria");
                double precio = resultSet.getDouble("precio");
                LocalDate fechaVencimiento = resultSet.getDate("fecha_vencimiento").toLocalDate();

                producto = new Producto(id, nombreProducto, categoria, precio, fechaVencimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void addProducto(Producto producto) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO producto (nombre_producto, categoria, precio, fecha_vencimiento) VALUES (?, ?, ?, ?)");
            statement.setString(1, producto.getNombreProducto());
            statement.setString(2, producto.getCategoria());
            statement.setDouble(3, producto.getPrecio());
            statement.setDate(4, Date.valueOf(producto.getFechaVencimiento()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Producto insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProducto(Producto producto) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE producto SET nombre_producto = ?, categoria = ?, precio = ?, fecha_vencimiento = ? WHERE id_producto = ?");
            statement.setString(1, producto.getNombreProducto());
            statement.setString(2, producto.getCategoria());
            statement.setDouble(3, producto.getPrecio());
            statement.setDate(4, Date.valueOf(producto.getFechaVencimiento()));
            statement.setInt(5, producto.getIdProducto());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Producto actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProducto(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM producto WHERE id_producto = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Producto eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getPrecioProductoById(int id) {
        double precio = 0.0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT precio FROM producto WHERE id_producto = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                precio = resultSet.getDouble("precio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precio;
    }
}
