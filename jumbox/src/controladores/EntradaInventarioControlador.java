package controladores;

import java.sql.Connection;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.EntradaInventarioRepository;
import modelos.EntradaInventario;

public class EntradaInventarioControlador implements EntradaInventarioRepository {
    private final Connection connection;

    public EntradaInventarioControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<EntradaInventario> getAllEntradasInventario() {
        List<EntradaInventario> entradas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entrada_inventario");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idEntrada = resultSet.getInt("id_entrada");
                int idProducto = resultSet.getInt("id_producto");
                int idInventario = resultSet.getInt("id_inventario");
               LocalDate fechaEntrada = resultSet.getDate("fecha_entrada").toLocalDate();
                int cantidad = resultSet.getInt("cantidad");

                EntradaInventario entrada = new EntradaInventario(idProducto, idInventario, fechaEntrada, cantidad);
                entrada.setIdEntrada(idEntrada);
                entradas.add(entrada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entradas;
    }

    @Override
    public EntradaInventario getEntradaInventarioById(int id) {
        EntradaInventario entrada = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entrada_inventario WHERE id_entrada = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                int idInventario = resultSet.getInt("id_inventario");
                LocalDate fechaEntrada = resultSet.getDate("fecha_entrada").toLocalDate();
                int cantidad = resultSet.getInt("cantidad");
                
                entrada = new EntradaInventario(idProducto, idInventario, fechaEntrada, cantidad);
                entrada.setIdEntrada(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrada;
    }

    @Override
    public void addEntradaInventario(EntradaInventario entrada) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO entrada_inventario (id_producto, id_inventario, fecha_entrada, cantidad) VALUES (?, ?, ?, ?)");
            statement.setInt(1, entrada.getIdProducto());
            statement.setInt(2, entrada.getIdInventario());
            statement.setDate(3, Date.valueOf(entrada.getFechaEntrada()));
            statement.setInt(4, entrada.getCantidad());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Entrada de inventario agregada exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEntradaInventario(EntradaInventario entrada) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE entrada_inventario SET id_producto = ?, id_inventario = ?, fecha_entrada = ?, cantidad = ? WHERE id_entrada = ?");
            statement.setInt(1, entrada.getIdProducto());
            statement.setInt(2, entrada.getIdInventario());
            statement.setDate(3,  Date.valueOf(entrada.getFechaEntrada()));
            statement.setInt(4, entrada.getCantidad());
            statement.setInt(5, entrada.getIdEntrada());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Entrada de inventario actualizada exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> origin/vicky
    public void deleteEntradaInventario(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM entrada_inventario WHERE id_entrada = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Entrada de inventario eliminada exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
<<<<<<< HEAD
    @Override
=======
>>>>>>> origin/vicky
    public int obtenerUltimoIdEntrada() {
        int ultimoIdDetalle = -1;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(id_entrada) AS max_id FROM entrada_inventario");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ultimoIdDetalle = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ultimoIdDetalle;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/vicky
