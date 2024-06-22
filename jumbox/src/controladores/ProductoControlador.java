package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				Producto producto = mapResultSetToProducto(resultSet);
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
				producto = mapResultSetToProducto(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}

	@Override
	public void addProducto(Producto producto) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO producto (nombre_producto, categoria, precio, fecha_vencimiento) VALUES (?, ?, ?, ?)");
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
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE productos SET nombre_producto = ?, categoria = ?, precio = ?, fecha_vencimiento = ? WHERE id_producto = ?");
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
			PreparedStatement statement = connection.prepareStatement("DELETE FROM productos WHERE id_producto = ?");
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Producto eliminado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Producto mapResultSetToProducto(ResultSet resultSet) throws SQLException {
		int idProducto = resultSet.getInt("id_producto");
		String nombreProducto = resultSet.getString("nombre_producto");
		String categoria = resultSet.getString("categoria");
		double precio = resultSet.getDouble("precio");
		Date fechaVencimiento = resultSet.getDate("fecha_vencimiento");

		return new Producto(idProducto, nombreProducto, categoria, precio, fechaVencimiento.toLocalDate());
	}

	public void updateProducto(int idProducto, double precioDescuentoVencimiento) {
		try {
			String sql = "UPDATE producto SET precio = ? WHERE id_producto = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setDouble(1, precioDescuentoVencimiento);
			statement.setInt(2, idProducto);

			int filasActualizadas = statement.executeUpdate();

			if (filasActualizadas > 0) {
				System.out.println("Precio del producto actualizado correctamente.");
			} else {
				System.out.println("No se pudo actualizar el precio del producto.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}