package controladores;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.DescuentoRepository;
import modelos.Descuento;

public class DescuentoControlador implements DescuentoRepository {
	private final Connection connection;

	public DescuentoControlador() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	@Override
	public List<Descuento> getAllDescuentos() {
		List<Descuento> descuentos = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM descuento ");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
                int porcentajeDesc = resultSet.getInt("porcentaje_descuento");
                int idDescuento = resultSet.getInt("id_descuento");
                int idProducto = resultSet.getInt("id_producto");
				Descuento descuento = new Descuento(porcentajeDesc, idDescuento, idProducto);
				descuentos.add(descuento);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return descuentos;
	}

	@Override
	public Descuento getDescuentoById(int id) {
		Descuento descuento = null;
		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM `descuento` WHERE `idDescuento` = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				descuento = new Descuento(resultSet.getInt("porcentaje_descuento"), resultSet.getInt("id_descuento"), resultSet.getInt("id_producto"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return descuento;
	}

	@Override
	public void addDescuento(Descuento descuento) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO `descuento`(`porcentaje`) VALUES (?)");
			statement.setDouble(1, descuento.getPorcentajeDesc());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Descuento insertado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDescuento(Descuento descuento) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("UPDATE `descuento` SET `porcentaje` = ? WHERE `idDescuento` = ?");
			statement.setDouble(1, descuento.getPorcentajeDesc());
			statement.setInt(2, descuento.getIdDescuento());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Descuento actualizado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDescuento(int id) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM `descuento` WHERE `idDescuento` = ?");
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Descuento eliminado exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
