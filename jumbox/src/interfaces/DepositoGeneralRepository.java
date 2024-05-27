package interfaces;

import java.util.List;
import modelos.DepositoGeneral;

public interface DepositoGeneralRepository {
	List<DepositoGeneral> getAllDepositosGenerales(); // Obtiene todos los depósitos generales

	DepositoGeneral getDepositoGeneralById(int id); // Obtiene un depósito general por su ID

	void addDepositoGeneral(DepositoGeneral deposito); // Agrega un depósito general

	void updateDepositoGeneral(DepositoGeneral deposito); // Actualiza un depósito general

	void deleteDepositoGeneral(int id); // Elimina un depósito general
}
