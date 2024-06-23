package interfaces;

import java.util.List;

import modelos.EntradaInventario;

public interface EntradaInventarioRepository {

    List<EntradaInventario> getAllEntradasInventario(); // Obtiene todas las entradas de inventario de la base de datos

    EntradaInventario getEntradaInventarioById(int id); // Obtiene una entrada de inventario por su ID

    void addEntradaInventario(EntradaInventario entrada); // Agrega una entrada de inventario a la base de datos

    void updateEntradaInventario(EntradaInventario entrada); // Actualiza una entrada de inventario en la base de datos

    void deleteEntradaInventario(int id); // Elimina una entrada de inventario de la base de datos


	int obtenerUltimoIdEntrada();
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/vicky
