package interfaces;

import java.util.List;
import modelos.Informe;

public interface InformeRepository {

    List<Informe> getAllInformes(); // Obtiene todos los informes de la base de datos

    Informe getInformeById(int id); // Obtiene un informe por su ID

    void addInforme(Informe informe); // Agrega un informe a la base de datos

    void updateInforme(Informe informe); // Actualiza un informe en la base de datos

    void deleteInforme(int id); // Elimina un informe de la base de datos
}
