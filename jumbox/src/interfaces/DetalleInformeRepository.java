package interfaces;

import java.util.List;
import modelos.DetalleInforme;

public interface DetalleInformeRepository {
    List<DetalleInforme> getAllDetalleInformes();
    List<DetalleInforme> getAllDetalleInformesByInformeId(int informeId);
    DetalleInforme getDetalleInformeById(int id);
    void addDetalleInforme(DetalleInforme detalleInforme);
    void updateDetalleInforme(DetalleInforme detalleInforme);
    void deleteDetalleInforme(int id);
    
    // Declaración del método obtenerUltimoIdDetalle
    int obtenerUltimoIdDetalle();
}
