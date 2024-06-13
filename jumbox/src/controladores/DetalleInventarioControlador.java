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

    private Connection connection;

    public DetalleInventarioControlador() {
    	this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<DetalleInventario> getAllDetalleInventarios() {
        List<DetalleInventario> detalles = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_inventario");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DetalleInventario detalle = new DetalleInventario(
                    result.getInt("id_producto"),
                    result.getInt("id_inventario_sucursal"),
                    result.getInt("id_descuento"),
                    result.getInt("cantidad")
                );
                detalle.setIdDetalle(result.getInt("id_detalle"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    @Override
    public DetalleInventario getDetalleInventarioById(int id) {
        DetalleInventario detalle = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_inventario WHERE id_detalle = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                detalle = new DetalleInventario(
                    result.getInt("id_producto"),
                    result.getInt("id_inventario_sucursal"),
                    result.getInt("id_descuento"),
                    result.getInt("cantidad")
                );
                detalle.setIdDetalle(result.getInt("id_detalle"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalle;
    }

    @Override
    public void addDetalleInventario(DetalleInventario detalleInventario) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO detalle_inventario (id_producto, id_inventario_sucursal, id_descuento, cantidad) VALUES (?, ?, ?, ?)");
            statement.setInt(1, detalleInventario.getIdProducto());
            statement.setInt(2, detalleInventario.getIdInventarioSucursal());
            statement.setInt(3, detalleInventario.getIdDescuento());
            statement.setInt(4, detalleInventario.getCantidad());
            statement.executeUpdate();
            System.out.println("Detalle de inventario agregado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetalleInventario(DetalleInventario detalleInventario) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE detalle_inventario SET id_producto = ?, id_inventario_sucursal = ?, id_descuento = ?, cantidad = ? WHERE id_detalle = ?");
            statement.setInt(1, detalleInventario.getIdProducto());
            statement.setInt(2, detalleInventario.getIdInventarioSucursal());
            statement.setInt(3, detalleInventario.getIdDescuento());
            statement.setInt(4, detalleInventario.getCantidad());
            statement.setInt(5, detalleInventario.getIdDetalle());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Detalle de inventario actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún detalle de inventario con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDetalleInventario(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM detalle_inventario WHERE id_detalle = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Detalle de inventario eliminado exitosamente.");
            } else {
                System.out.println("No se encontró ningún detalle de inventario con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int getCantidadDisponible(int idInventarioSucursal, int idProducto) {
        int cantidadDisponible = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT cantidad FROM `detalle_inventario` WHERE `id_inventario` = ? AND `id_producto` = ?");
            statement.setInt(1, idInventarioSucursal);
            statement.setInt(2, idProducto);
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
    public void actualizarCantidadProducto(int idInventarioSucursal, int idProducto, int nuevaCantidad) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `detalle_inventario` SET `cantidad` = ? WHERE `id_inventario` = ? AND `id_producto` = ?");
            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, idInventarioSucursal);
            statement.setInt(3, idProducto);
            statement.executeUpdate();
            System.out.println("Cantidad del producto actualizada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean existeProducto(int idInventarioSucursal, int idProducto) {
        boolean existe = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `detalle_inventario` WHERE `id_inventario` = ? AND `id_producto` = ?");
            
            statement.setInt(1, idInventarioSucursal); 
            statement.setInt(2, idProducto); 
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
	public boolean existeProducto(int idInventarioSucursal) {
		// TODO Auto-generated method stub
		return false;
	}


}