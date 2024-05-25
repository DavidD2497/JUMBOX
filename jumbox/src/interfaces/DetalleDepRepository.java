package interfaces;

import java.util.List;
import modelos.DetalleDeposito;

public interface DetalleDepRepository {

    List<DetalleDeposito> getAllDetalleDepositos(); // Obtiene todos los detalles de depósito de la base de datos

    DetalleDeposito getDetalleDepositoById(int id); // Obtiene un detalle de depósito por su ID

    void addDetalleDeposito(DetalleDeposito detalle); // Agrega un detalle de depósito a la base de datos

    void updateDetalleDeposito(DetalleDeposito detalle); // Actualiza un detalle de depósito en la base de datos

    void deleteDetalleDeposito(int id); // Elimina un detalle de depósito de la base de datos
}
