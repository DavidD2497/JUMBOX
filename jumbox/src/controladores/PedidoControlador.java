package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import modelos.DetallePedido;
import modelos.Pedido;

import interfaces.PedidoRepository;

public class PedidoControlador implements PedidoRepository {
	private final Connection connection;
	LinkedList<DetallePedido> estado;
	String idInventario;
	int codigoPedido;
	public PedidoControlador() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	@Override
	public List<Pedido> getAllPedidos() {
		List<Pedido> pedidos = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int codigoPedido = resultSet.getInt("codigo_pedido");
				LocalDate fechaEntrega = resultSet.getDate("fecha_entrega").toLocalDate();
				Pedido pedido = new Pedido(fechaEntrega, codigoPedido, idInventario, estado);
				pedido.setCodigoPedido(codigoPedido);
				pedidos.add(pedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedidos;
	}

	@Override
	public Pedido getPedidoById(int id) {
		Pedido pedido = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido WHERE codigo_pedido = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				LocalDate fechaEntrega = resultSet.getDate("fecha_entrega").toLocalDate();
				
				pedido = new Pedido(fechaEntrega, codigoPedido, idInventario, estado);
				pedido.setCodigoPedido(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedido;
	}

	@Override
	public void addPedido(Pedido pedido) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO pedido (fecha_entrega) VALUES (?)");
			statement.setDate(1, java.sql.Date.valueOf(pedido.getFechaEntrega()));

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Pedido agregado exitosamente.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePedido(Pedido pedido) {
		// No se actualizan los pedidos en esta implementación
	}

	@Override
	public boolean deletePedido(int idPedido) {
	    boolean eliminacionExitosa = false;
	    try {
	        Connection connection = DatabaseConnection.getInstance().getConnection();
	        String query = "DELETE FROM pedido WHERE codigo_pedido = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, idPedido);

	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            eliminacionExitosa = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return eliminacionExitosa;
	}



	@Override
	public int obtenerUltimoIdPedido() {
		int ultimoIdPedido = -1;

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT MAX(codigo_pedido) FROM pedido");
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				ultimoIdPedido = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ultimoIdPedido;
	}

	@Override
	public void actualizarEstadoPedido(int codigoPedido, String estado) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("UPDATE pedidos SET estado = ? WHERE codigo_pedido = ?");
			statement.setString(1, estado);
			statement.setInt(2, codigoPedido);

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Estado del pedido actualizado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarFechaEntrega(int codigoPedido, LocalDate nuevaFechaEntrega) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("UPDATE pedido SET fecha_entrega = ? WHERE codigo_pedido = ?");
			statement.setDate(1, java.sql.Date.valueOf(nuevaFechaEntrega));
			statement.setInt(2, codigoPedido);

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Fecha de entrega del pedido actualizada exitosamente.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}