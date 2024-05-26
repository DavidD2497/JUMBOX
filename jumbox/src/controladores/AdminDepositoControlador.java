package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.AdminDepositoRepository;
import modelos.AdminDeposito;

public class AdminDepositoControlador implements AdminDepositoRepository {
    private final Connection connection;

    public AdminDepositoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<AdminDeposito> getAllUsers() {
        List<AdminDeposito> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM adminDeposito");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                AdminDeposito user = new AdminDeposito(
                    resultSet.getString("nombre"),
                    resultSet.getString("email"),
                    resultSet.getString("contraseña"),
                    resultSet.getInt("idAdminDepo")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public AdminDeposito getUserById(int id) {
        AdminDeposito user = null;
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM adminDeposito WHERE idAdminDepo = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new AdminDeposito(
                    resultSet.getString("nombre"),
                    resultSet.getString("email"),
                    resultSet.getString("contraseña"),
                    resultSet.getInt("idAdminDepo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(AdminDeposito user) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO adminDeposito(nombre, email, contraseña, idAdminDepo) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setInt(4, user.getIdAdminDepo());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(AdminDeposito user) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE adminDeposito SET nombre = ?, email = ?, contraseña = ? WHERE idAdminDepo = ?");
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setInt(4, user.getIdAdminDepo());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM adminDeposito WHERE idAdminDepo = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Usuario eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
