package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelos.Cajero;
import interfaces.CajeroRepository;

public class CajeroControlador implements CajeroRepository {
    private final Connection connection;

    public CajeroControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void addUser(Cajero cajero) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO cajeros (nombre, email, contraseña, tipo) VALUES (?, ?, ?, ?)");
            statement.setString(1, cajero.getNombre());
            statement.setString(2, cajero.getEmail());
            statement.setString(3, cajero.getContraseña());
            statement.setString(4, cajero.getTipo());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cajero agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cajero> getAllUsers() {
        List<Cajero> cajeros = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cajero");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                String contraseña = resultSet.getString("contraseña");

                Cajero cajero = new Cajero(nombre, email, contraseña);
                cajeros.add(cajero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cajeros;
    }


    @Override
    public void updateUser(Cajero user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE cajero SET nombre = ?, email = ?, contraseña = ?, tipo = ? WHERE id_cajero = ?");
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setString(4, user.getTipo());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cajero actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM cajero WHERE id_cajero = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cajero eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Cajero getUserById(int id) {
        Cajero cajero = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cajero WHERE id_cajero = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                String contraseña = resultSet.getString("contraseña");
                String tipo = resultSet.getString("tipo");

                cajero = new Cajero(nombre, email, contraseña);
                cajero.setTipo(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cajero;
    }
}
