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
        List<AdminDeposito> adminsDeposito = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM administrador_deposito");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_admindep");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                String contraseña = resultSet.getString("contraseña");
                String tipo = resultSet.getString("tipo");

                AdminDeposito adminDeposito = new AdminDeposito(nombre, email, contraseña);
                adminDeposito.setTipo(tipo);
                adminsDeposito.add(adminDeposito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsDeposito;
    }

    @Override
    public AdminDeposito getUserById(int id) {
        AdminDeposito adminDeposito = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM administrador_deposito WHERE id_admindep = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                String contraseña = resultSet.getString("contraseña");
                String tipo = resultSet.getString("tipo");

                adminDeposito = new AdminDeposito(nombre, email, contraseña);
                adminDeposito.setTipo(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminDeposito;
    }

    @Override
    public void addUser(AdminDeposito user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO administrador_deposito (nombre, email, contraseña, tipo) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setString(4, user.getTipo());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Admin de depósito agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(AdminDeposito user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE administrador_deposito SET nombre = ?, email = ?, contraseña = ?, tipo = ? WHERE id_admindep = ?");
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setString(4, user.getTipo());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Admin de depósito actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM administrador_deposito WHERE id_admindep = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Admin de depósito eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
<<<<<<< HEAD
}

=======
}
>>>>>>> origin/vicky
