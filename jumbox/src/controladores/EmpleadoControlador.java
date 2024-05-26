package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelos.Empleado;
import modelos.AdminDeposito;
import modelos.AdminSucursal;
import modelos.Cajero;

public class EmpleadoControlador {
    private final Connection connection;

    public EmpleadoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

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
                            resultSet.getString("contraseña"),
                            resultSet.getInt("idAdminDepo")
                        );
                        break;
                    case "AdminSucursal":
                        empleado = new AdminSucursal(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña"),
                            resultSet.getInt("idAdminSuc")
                        );
                        break;
                    case "Cajero":
                        empleado = new Cajero(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña"),
                            resultSet.getInt("idCajero")
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

    public Empleado getUserById(int id) {
        Empleado empleado = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM empleado WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                switch (tipo) {
                    case "AdminDeposito":
                        empleado = new AdminDeposito(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña"),
                            resultSet.getInt("idAdminDepo")
                        );
                        break;
                    case "AdminSucursal":
                        empleado = new AdminSucursal(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña"),
                            resultSet.getInt("idAdminSuc")
                        );
                        break;
                    case "Cajero":
                        empleado = new Cajero(
                            resultSet.getString("nombre"),
                            resultSet.getString("email"),
                            resultSet.getString("contraseña"),
                            resultSet.getInt("idCajero")
                        );
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }

    public void addUser(Empleado user) {
        try {
            String query = "INSERT INTO empleado (nombre, email, contraseña, tipo, idAdminDepo, idAdminSuc, idCajero) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());

            if (user instanceof AdminDeposito) {
                AdminDeposito adminDepo = (AdminDeposito) user;
                statement.setString(4, "AdminDeposito");
                statement.setInt(5, adminDepo.getIdAdminDepo());
                statement.setNull(6, java.sql.Types.INTEGER);
                statement.setNull(7, java.sql.Types.INTEGER);
            } else if (user instanceof AdminSucursal) {
                AdminSucursal adminSuc = (AdminSucursal) user;
                statement.setString(4, "AdminSucursal");
                statement.setNull(5, java.sql.Types.INTEGER);
                statement.setInt(6, adminSuc.getIdAdminSuc());
                statement.setNull(7, java.sql.Types.INTEGER);
            } else if (user instanceof Cajero) {
                Cajero cajero = (Cajero) user;
                statement.setString(4, "Cajero");
                statement.setNull(5, java.sql.Types.INTEGER);
                statement.setNull(6, java.sql.Types.INTEGER);
                statement.setInt(7, cajero.getIdCajero());
            }

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("El empleado fue agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Empleado user) {
        try {
            String query = "UPDATE empleado SET nombre = ?, email = ?, contraseña = ?, tipo = ?, idAdminDepo = ?, idAdminSuc = ?, idCajero = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getContraseña());

            if (user instanceof AdminDeposito) {
                AdminDeposito adminDepo = (AdminDeposito) user;
                statement.setString(4, "AdminDeposito");
                statement.setInt(5, adminDepo.getIdAdminDepo());
                statement.setNull(6, java.sql.Types.INTEGER);
                statement.setNull(7, java.sql.Types.INTEGER);
            } else if (user instanceof AdminSucursal) {
                AdminSucursal adminSuc = (AdminSucursal) user;
                statement.setString(4, "AdminSucursal");
                statement.setNull(5, java.sql.Types.INTEGER);
                statement.setInt(6, adminSuc.getIdAdminSuc());
                statement.setNull(7, java.sql.Types.INTEGER);
            } else if (user instanceof Cajero) {
                Cajero cajero = (Cajero) user;
                statement.setString(4, "Cajero");
                statement.setNull(5, java.sql.Types.INTEGER);
                statement.setNull(6, java.sql.Types.INTEGER);
                statement.setInt(7, cajero.getIdCajero());
            }

            statement.setInt(8, user.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El empleado fue actualizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try {
            String query = "DELETE FROM empleado WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("El empleado fue eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
