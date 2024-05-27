package test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modelos.Empleado;
import controladores.EmpleadoControlador;

public class IniciarSesionTest {

    @Test
    public void inicioSesionCorrecto() {
 
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();

        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {

            if (empleado.iniciarSesion("david@gmail.com", "1234")) {
                flag = true;
                break;
            }
        }

        assertEquals(true, flag);
    }
	
}
