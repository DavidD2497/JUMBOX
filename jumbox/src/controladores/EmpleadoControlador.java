package controladores;

import interfaces.EmpleadoRepository;
import modelos.Empleado;
import modelos.AdminDeposito;
import modelos.AdminSucursal;
import modelos.Cajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoControlador implements EmpleadoRepository {
    private final Connection connection;

    public EmpleadoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Empleado> getAllUsers() {
        List<Empleado> empleados = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM empleado");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                Empleado empleado = null;
                switch (tipo) {
                    case "AdminDeposito":
                        empleado = new AdminDeposito(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña")
                        );
                        break;
                    case "AdminSucursal":
                        empleado = new AdminSucursal(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña")
                        );
                        break;
                    case "Cajero":
                        empleado = new Cajero(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña")
                        );
                        break;
                }
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    @Override
    public void addUser(Empleado user) {
        try {
            String query = "INSERT INTO empleado (nombre, email, contraseña, tipo) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());
            statement.setString(4, user.getClass().getSimpleName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("El empleado fue agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(String email, Empleado updatedEmpleado) {
        try {
            String query = "UPDATE empleado SET nombre = ?, email = ?, contraseña = ?, tipo = ? WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, updatedEmpleado.getNombre());
            statement.setString(2, updatedEmpleado.getEmail());
            statement.setString(3, updatedEmpleado.getContraseña());
            statement.setString(4, updatedEmpleado.getClass().getSimpleName());
            statement.setString(5, email);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El empleado fue actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String email) {
        try {
            String query = "DELETE FROM empleado WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("El empleado fue eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Empleado getUserByEmail(String email) {
        Empleado empleado = null;
        try {
            String query = "SELECT * FROM empleado WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                switch (tipo) {
                    case "AdminDeposito":
                        empleado = new AdminDeposito(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña")
                        );
                        break;
                    case "AdminSucursal":
                        empleado = new AdminSucursal(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña")
                        );
                        break;
                    case "Cajero":
                        empleado = new Cajero(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña")
                        );
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }

    @Override
    public Empleado getUserByEmailAndPassword(String email, String contraseña) {
        Empleado empleado = null;
        try {
            String query = "SELECT * FROM empleado WHERE email = ? AND contraseña = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, contraseña);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                switch (tipo) {
                    case "AdminDeposito":
                        empleado = new AdminDeposito(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña")
                        );
                        break;
                    case "AdminSucursal":
                        empleado = new AdminSucursal(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña")
                        );
                        break;
                    case "Cajero":
                        empleado = new Cajero(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña")
                        );
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }
    
    @Override
    public String getUserTypeByEmail(String email) {
        String tipo = null;
        try {
            String query = "SELECT tipo FROM empleado WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tipo = resultSet.getString("tipo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipo;
    }
}
