package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.CajeroRepository;
import modelos.Cajero;

public class CajeroControlador implements CajeroRepository {
    private final Connection connection;

    public CajeroControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Cajero> getAllUsers() {
        List<Cajero> cajeros = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cajero");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cajero cajero = new Cajero(
                    resultSet.getString("nombre"),
                    resultSet.getString("email"),
                    resultSet.getString("contraseña"),
                    resultSet.getInt("idCajero")
                );
                cajeros.add(cajero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cajeros;
    }


    public Cajero getUserById(int id) {
        Cajero cajero = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cajero WHERE idCajero = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cajero = new Cajero(
                    resultSet.getString("nombre"),
                    resultSet.getString("email"),
                    resultSet.getString("contraseña"),
                    resultSet.getInt("idCajero")
                );
            } else {
                System.out.println("No se encontró el cajero con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cajero;
    }

    @Override
    public void addUser(Cajero user) {
        try {
            String query = "INSERT INTO cajero (nombre, email, contraseña, idCajero) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setInt(4, user.getIdCajero());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("El cajero fue agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(Cajero user) {
        try {
            String query = "UPDATE cajero SET nombre = ?, email = ?, contraseña = ? WHERE idCajero = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setInt(4, user.getIdCajero());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El cajero fue actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            String query = "DELETE FROM cajero WHERE idCajero = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("El cajero fue eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
