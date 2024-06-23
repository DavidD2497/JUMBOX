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

    public AdminSucursalControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<AdminSucursal> getAllUsers() {
        List<AdminSucursal> adminsSucursal = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM administrador_sucursal");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_adminsuc");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                String contraseña = resultSet.getString("contraseña");
                String tipo = resultSet.getString("tipo");

                AdminSucursal adminSucursal = new AdminSucursal(nombre, email, contraseña);
                adminSucursal.setTipo(tipo);
                adminsSucursal.add(adminSucursal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsSucursal;
    }

    @Override
    public AdminSucursal getUserById(int id) {
        AdminSucursal adminSucursal = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM administrador_sucursal WHERE id_adminsuc = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                String contraseña = resultSet.getString("contraseña");
                String tipo = resultSet.getString("tipo");

                adminSucursal = new AdminSucursal(nombre, email, contraseña);
                adminSucursal.setTipo(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminSucursal;
    }

    @Override
    public void addUser(AdminSucursal user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO administrador_sucursal (nombre, email, contraseña, tipo) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setString(4, user.getTipo());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Admin de sucursal agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(AdminSucursal user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE administrador_sucursal SET nombre = ?, email = ?, contraseña = ?, tipo = ? WHERE id_adminsuc = ?");
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setString(4, user.getTipo());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Admin de sucursal actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM administrador_sucursal WHERE id_adminsuc = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Admin de sucursal eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}