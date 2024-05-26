package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.AdminSucursal;

public class AdminSucursalControlador {
    
    // Método para obtener la conexión a la base de datos (debes implementar este método según tu configuración)
    private Connection obtenerConexion() {
        // Implementa la lógica para obtener y retornar la conexión a la base de datos
        // Esto puede variar dependiendo del sistema de gestión de bases de datos que estés utilizando (MySQL, PostgreSQL, etc.)
        return null; // Solo es un ejemplo, debes implementarlo según tu configuración
    }
    
    // Método para crear un nuevo administrador de sucursal y guardarlo en la base de datos
    public void crearAdminSucursal(String nombre, String email, String contraseña, int idAdminSuc) {
        AdminSucursal adminSucursal = new AdminSucursal(nombre, email, contraseña, idAdminSuc);
        Connection connection = obtenerConexion();
        String query = "INSERT INTO admin_sucursal (nombre, email, contraseña, id_admin_sucursal) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, adminSucursal.getNombre());
            statement.setString(2, adminSucursal.getEmail());
            statement.setString(3, adminSucursal.getContraseña());
            statement.setInt(4, adminSucursal.getIdAdminSuc());
            statement.executeUpdate();
            statement.close();
            connection.close();
            System.out.println("El administrador de sucursal fue creado exitosamente en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Método para eliminar un administrador de sucursal de la base de datos por su ID
    public void eliminarAdminSucursal(int idAdminSuc) {
        Connection connection = obtenerConexion();
        String query = "DELETE FROM admin_sucursal WHERE id_admin_sucursal = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idAdminSuc);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("El administrador de sucursal fue eliminado exitosamente de la base de datos.");
            } else {
                System.out.println("No se encontró ningún administrador de sucursal con el ID proporcionado.");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Método para obtener todos los administradores de sucursal de la base de datos
    public List<AdminSucursal> obtenerTodosAdminSucursal() {
        List<AdminSucursal> adminsSucursal = new ArrayList<>();
        Connection connection = obtenerConexion();
        String query = "SELECT * FROM admin_sucursal";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                AdminSucursal adminSucursal = new AdminSucursal(
                    resultSet.getString("nombre"),
                    resultSet.getString("email"),
                    resultSet.getString("contraseña"),
                    resultSet.getInt("id_admin_sucursal")
                );
                adminsSucursal.add(adminSucursal);
            }
            
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return adminsSucursal;
    }
    
    // Método para obtener un administrador de sucursal por su ID
    public AdminSucursal obtenerAdminSucursalPorId(int idAdminSuc) {
        Connection connection = obtenerConexion();
        String query = "SELECT * FROM admin_sucursal WHERE id_admin_sucursal = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idAdminSuc);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                AdminSucursal adminSucursal = new AdminSucursal(
                    resultSet.getString("nombre"),
                    resultSet.getString("email"),
                    resultSet.getString("contraseña"),
                    resultSet.getInt("id_admin_sucursal")
                );
                statement.close();
                connection.close();
                return adminSucursal;
            } else {
                System.out.println("No se encontró ningún administrador de sucursal con el ID proporcionado.");
            }
            
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Método para actualizar los datos de un administrador de sucursal en la base de datos
    public void actualizarAdminSucursal(AdminSucursal adminSucursal) {
        Connection connection = obtenerConexion();
        String query = "UPDATE admin_sucursal SET nombre = ?, email = ?, contraseña = ? WHERE id_admin_sucursal = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, adminSucursal.getNombre());
            statement.setString(2, adminSucursal.getEmail());
            statement.setString(3, adminSucursal.getContraseña());
            statement.setInt(4, adminSucursal.getIdAdminSuc());
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
                System.out.println("Los datos del administrador de sucursal fueron actualizados exitosamente.");
            } else {
                System.out.println("No se encontró ningún administrador de sucursal con el ID proporcionado para actualizar.");
            }
            
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Otros métodos para la gestión de administradores de sucursal podrían agregarse aquí
    
}
