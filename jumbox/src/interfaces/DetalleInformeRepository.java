package interfaces;

import java.util.List;
import modelos.DetalleInforme;

public interface DetalleInformeRepository {

	List<DetalleInforme> getAllDetalleInformes(); // Obtiene todos los detalles de informes de la base de datos

	DetalleInforme getDetalleInformeById(int id); // Obtiene un detalle de informe por su ID

	void addDetalleInforme(DetalleInforme detalleInforme); // Agrega un detalle de informe a la base de datos

	void updateDetalleInforme(DetalleInforme detalleInforme); // Actualiza un detalle de informe en la base de datos

	void deleteDetalleInforme(int id); // Elimina un detalle de informe de la base de datos
}
