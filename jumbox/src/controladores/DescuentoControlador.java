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
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM descuentos ");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Descuento descuento = new Descuento(resultSet.getInt("id"), resultSet.getDouble("porcentaje"));
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
					.prepareStatement("SELECT * FROM `descuentos` WHERE `idDescuento` = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				descuento = new Descuento(resultSet.getInt("idDescuento"), resultSet.getDouble("porcentajeDesc"));
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
					.prepareStatement("INSERT INTO `descuentos`(`porcentaje`) VALUES (?)");
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
					.prepareStatement("UPDATE `descuentos` SET `porcentaje` = ? WHERE `idDescuento` = ?");
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
					.prepareStatement("DELETE FROM `descuentos` WHERE `idDescuento` = ?");
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
