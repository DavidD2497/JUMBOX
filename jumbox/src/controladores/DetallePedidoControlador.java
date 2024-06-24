package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelos.DetallePedido;

public class DetallePedidoControlador {
	private final Connection connection;

	public DetallePedidoControlador() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	public List<DetallePedido> getAllDetallesPedidos() {
		List<DetallePedido> detallesPedidos = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_pedido");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				DetallePedido detallePedido = new DetallePedido(resultSet.getInt("id_detalle_pedido"),
						resultSet.getInt("id_producto"), resultSet.getInt("cantidad"));
				detallesPedidos.add(detallePedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detallesPedidos;
	}

	public DetallePedido getDetallePedidoById(int id) {
		DetallePedido detallePedido = null;
		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM detalle_pedido WHERE id_detalle_pedido = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				detallePedido = new DetallePedido(resultSet.getInt("id_detalle_pedido"), resultSet.getInt("id_producto"),
						resultSet.getInt("cantidad"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detallePedido;
	}

	public List<DetallePedido> getDetallePedidoByIdPedido(int idPedido) {
		List<DetallePedido> detallesPedido = new ArrayList<>();
		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM detalle_pedido WHERE id_pedido = ?");
			statement.setInt(1, idPedido);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				DetallePedido detallePedido = new DetallePedido(resultSet.getInt("id_detalle_pedido"),
						resultSet.getInt("id_producto"), resultSet.getInt("cantidad"));
				detallesPedido.add(detallePedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detallesPedido;
	}
	
	

	public void addDetallePedido(DetallePedido detallePedido) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO `detalle_pedido`(`idProducto`, `cantidad`) VALUES (?, ?)");
			statement.setInt(1, detallePedido.getIdProducto());
			statement.setInt(2, detallePedido.getCantidad());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Detalle de Pedido insertado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDetallePedido(DetallePedido detallePedido) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE `detalle_pedido` SET `idProducto` = ?, `cantidad` = ? WHERE `idDetalle` = ?");
			statement.setInt(1, detallePedido.getIdProducto());
			statement.setInt(2, detallePedido.getCantidad());
			statement.setInt(3, detallePedido.getIdDetalle());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Detalle de Pedido actualizado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteDetallePedido(int id) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM `detalle_pedido` WHERE `idDetalle` = ?");
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Detalle de Pedido eliminado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteDetallesByIdPedido(int idPedido) {
	    try {
	        PreparedStatement statement = connection.prepareStatement("DELETE FROM detalle_pedido WHERE idPedido = ?");
	        statement.setInt(1, idPedido);

	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Detalles de Pedido eliminados exitosamente");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
