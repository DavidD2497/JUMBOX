package interfaces;

import java.util.List;

import modelos.DepositoGeneral;

public interface DepositoGeneralRepository {
	List<DepositoGeneral> getAllUsers();

	DepositoGeneral getUserById(int id);

	void addUser(DepositoGeneral user);

	void updateUser(DepositoGeneral user);

	void deleteUser(int id);
}
