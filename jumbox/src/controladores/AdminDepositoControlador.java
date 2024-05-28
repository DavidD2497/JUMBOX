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
}
