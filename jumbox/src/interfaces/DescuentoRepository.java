package interfaces;

import java.util.List;

import modelos.Descuento;
import modelos.DetalleDeposito;

public interface DescuentoRepository {

	List<Descuento> getAllDescuentos(); // Obtiene todos los descuentos de la base de datos

    Descuento getDescuentoById(int id); // Obtiene un descuento por su ID

    void addDescuento(Descuento descuento); // Agrega un descuento a la base de datos

    void updateDescuento(Descuento descuento); // Actualiza un descuento en la base de datos

    void deleteDescuento(int id); // Elimina un descuento de la base de datos

}
