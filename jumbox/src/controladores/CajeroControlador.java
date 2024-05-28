package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.CajeroRepository;
import modelos.Cajero;

public class CajeroControlador implements CajeroRepository {
    private final Connection connection;

    public CajeroControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

}
