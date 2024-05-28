package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import interfaces.DetalleInventarioRepository;
import modelos.DetalleInventario;

public class DetalleInventarioControlador implements DetalleInventarioRepository {
    private final Connection connection;

    public DetalleInventarioControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<DetalleInventario> getAllDetalleInventarios() {
        List<DetalleInventario> detallesInventario = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalles_inventario");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleInventario detalleInventario = new DetalleInventario(
                        resultSet.getInt("idProducto"),
                        resultSet.getInt("idInventarioSucursal"),
                        resultSet.getInt("idDescuento"),
                        resultSet.getInt("idDetalle"),
                        resultSet.getInt("cantidad")
                );
                detallesInventario.add(detalleInventario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallesInventario;
    }

    @Override
    public DetalleInventario getDetalleInventarioById(int id) {
        DetalleInventario detalleInventario = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalles_inventario WHERE idDetalle = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                detalleInventario = new DetalleInventario(
                        resultSet.getInt("idProducto"),
                        resultSet.getInt("idInventarioSucursal"),
                        resultSet.getInt("idDescuento"),
                        resultSet.getInt("idDetalle"),
                        resultSet.getInt("cantidad")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleInventario;
    }

    @Override
    public void addDetalleInventario(DetalleInventario detalleInventario) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `detalles_inventario`(`idProducto`, `idInventarioSucursal`, `idDescuento`, `idDetalle`, `cantidad`) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, detalleInventario.getIdProducto());
            statement.setInt(2, detalleInventario.getIdInventarioSucursal());
            statement.setInt(3, detalleInventario.getIdDescuento());
            statement.setInt(4, detalleInventario.getIdDetalle());
            statement.setInt(5, detalleInventario.getCantidad());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Detalle de Inventario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetalleInventario(DetalleInventario detalleInventario) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `detalles_inventario` SET `idProducto` = ?, `idInventarioSucursal` = ?, `idDescuento` = ?, `cantidad` = ? WHERE `idDetalle` = ?");
            statement.setInt(1, detalleInventario.getIdProducto());
            statement.setInt(2, detalleInventario.getIdInventarioSucursal());
            statement.setInt(3, detalleInventario.getIdDescuento());
            statement.setInt(4, detalleInventario.getCantidad());
            statement.setInt(5, detalleInventario.getIdDetalle());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Detalle de Inventario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDetalleInventario(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `detalles_inventario` WHERE `idDetalle` = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Detalle de Inventario eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int getCantidadDisponible(int idProducto) {
        int cantidadDisponible = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT cantidad FROM `detalles_inventario` WHERE `idProducto` = ?");
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cantidadDisponible = resultSet.getInt("cantidad");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cantidadDisponible;
    }

    @Override
    public void actualizarCantidadProducto( int idProducto, int nuevaCantidad) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `detalles_inventario` SET `cantidad` = ? WHERE ? AND `idProducto` = ?");
            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, idProducto);
            statement.executeUpdate();
            System.out.println("Cantidad del producto actualizada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existeProducto( int idProducto) {
        boolean existe = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `detalles_inventario` WHERE `idInventarioSucursal` = ? AND `idProducto` = ?");
            
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
    
    @Override
    public boolean getNombreProducto( int idProducto) {
        boolean existe = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT nombre FROM `producto` WHERE `idProducto` = ?");
            
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }

}
