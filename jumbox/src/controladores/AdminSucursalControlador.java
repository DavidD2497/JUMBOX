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

}
