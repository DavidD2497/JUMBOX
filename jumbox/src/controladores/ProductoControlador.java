package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import interfaces.ProductoRepository;
import modelos.Producto;

public class ProductoControlador implements ProductoRepository {

    private Connection connection;

    
    public ProductoControlador() {
    	this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM producto");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Producto producto = new Producto(
                    result.getString("nombre_producto"),
                    result.getString("categoria"),
                    result.getDouble("precio"),
                    result.getDate("fecha_vencimiento").toLocalDate()
                );
                producto.setIdProducto(result.getInt("id_producto"));
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
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                producto = new Producto(
                    result.getString("nombre_producto"),
                    result.getString("categoria"),
                    result.getDouble("precio"),
                    result.getDate("fecha_vencimiento").toLocalDate()
                );
                producto.setIdProducto(result.getInt("id_producto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void addProducto(Producto producto) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO producto (nombre_producto, fecha_vencimiento, categoria, precio) VALUES (?, ?, ?, ?)");
            statement.setString(1, producto.getNombreProducto());
            statement.setDate(2, java.sql.Date.valueOf(producto.getFechaVencimiento()));
            statement.setString(3, producto.getCategoria());
            statement.setDouble(4, producto.getPrecio());
            statement.executeUpdate();
            System.out.println("Producto agregado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProducto(Producto producto) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE producto SET nombre_producto = ?, fecha_vencimiento = ?, categoria = ?, precio = ? WHERE id_producto = ?");
            statement.setString(1, producto.getNombreProducto());
            statement.setDate(2, java.sql.Date.valueOf(producto.getFechaVencimiento()));
            statement.setString(3, producto.getCategoria());
            statement.setDouble(4, producto.getPrecio());
            statement.setInt(5, producto.getIdProducto());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún producto con el ID proporcionado.");
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
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("No se encontró ningún producto con el ID proporcionado.");
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
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                precio = result.getDouble("precio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precio;
    }
    @Override
    public boolean existeProducto( int idProducto) {
        boolean existe = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `producto` WHERE `id_producto` = ?");
            
           
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

