package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.AdminSucursalRepository;
import modelos.AdminSucursal;

public class AdminSucursalControlador implements AdminSucursalRepository {
    private final Connection connection;

<<<<<<< HEAD
    public AdminSucursalControlador(Connection connection) {
        this.connection = connection;
=======
    public AdminSucursalControlador() {
    	this.connection = DatabaseConnection.getInstance().getConnection();
>>>>>>> david
    }

    @Override
    public List<AdminSucursal> getAllUsers() {
        List<AdminSucursal> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin_sucursal");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                AdminSucursal user = new AdminSucursal(
                        resultSet.getString("nombre"),
                        resultSet.getString("email"),
                        resultSet.getString("contraseña"),
                        resultSet.getInt("idAdminSuc")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public AdminSucursal getUserById(int id) {
        AdminSucursal user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin_sucursal WHERE idAdminSuc = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new AdminSucursal(
                        resultSet.getString("nombre"),
                        resultSet.getString("email"),
                        resultSet.getString("contraseña"),
                        resultSet.getInt("idAdminSuc")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(AdminSucursal user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO admin_sucursal(nombre, email, contraseña, idAdminSuc) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setInt(4, user.getIdAdminSuc());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(AdminSucursal user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE admin_sucursal SET nombre = ?, email = ?, contraseña = ? WHERE idAdminSuc = ?");
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setInt(4, user.getIdAdminSuc());

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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM admin_sucursal WHERE idAdminSuc = ?");
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
