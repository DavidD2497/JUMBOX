package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import interfaces.DetalleInventarioRepository;
import modelos.DetalleInventario;
import modelos.Producto;

public class DetalleInventarioControlador implements DetalleInventarioRepository {
	private final Connection connection;

	public DetalleInventarioControlador() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	@Override
	public List<DetalleInventario> getAllDetalleInventarios() {
		List<DetalleInventario> detallesInventario = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalles_inventario");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Producto producto = obtenerProductoPorId(resultSet.getInt("idProducto"));
				DetalleInventario detalleInventario = new DetalleInventario(producto, resultSet.getInt("idDescuento"),
						resultSet.getInt("idDetalle"), resultSet.getInt("cantidad"));
				detallesInventario.add(detalleInventario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detallesInventario;
	}

	@Override
	public DetalleInventario getDetalleInventarioById(int id) {
		DetalleInventario detalleInventario = null;
		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM detalles_inventario WHERE idDetalle = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Producto producto = obtenerProductoPorId(resultSet.getInt("idProducto"));
				detalleInventario = new DetalleInventario(producto, resultSet.getInt("idDescuento"),
						resultSet.getInt("idDetalle"), resultSet.getInt("cantidad"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detalleInventario;
	}

	@Override
	public void addDetalleInventario(DetalleInventario detalleInventario) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO `detalles_inventario`(`idProducto`, `idDescuento`, `cantidad`) VALUES (?, ?, ?)");
			statement.setInt(1, detalleInventario.getProducto().getId());
			statement.setInt(2, detalleInventario.getIdDescuento());
			statement.setInt(3, detalleInventario.getCantidad());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Detalle de Inventario insertado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDetalleInventario(DetalleInventario detalleInventario) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE `detalles_inventario` SET `idProducto` = ?, `idDescuento` = ?, `cantidad` = ? WHERE `idDetalle` = ?");
			statement.setInt(1, detalleInventario.getProducto().getId());
			statement.setInt(2, detalleInventario.getIdDescuento());
			statement.setInt(3, detalleInventario.getCantidad());
			statement.setInt(4, detalleInventario.getIdDetalle());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Detalle de Inventario actualizado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDetalleInventario(int id) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM `detalles_inventario` WHERE `idDetalle` = ?");
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Detalle de Inventario eliminado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*private Producto obtenerProductoPorId(int id) throws SQLException {
		Producto producto = null;
		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT idProducto FROM detalles_inventario WHERE idDetalle = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int idProducto = resultSet.getInt("idProducto");
				// Utilizamos un controlador de Producto para obtener el producto por su ID
				ProductoControlador productoControlador = new ProductoControlador();
				producto = productoControlador.getProductoById(idProducto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return producto;
	}*/

}
