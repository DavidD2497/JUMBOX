package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modelos.Empleado;
import controladores.EmpleadoControlador;

public class BorrarEmpleadoTest {

    @Test
    public void EmpleadoBorradoCorrecto() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.borrarEmpleado("david@gmail.com").equals("Usuario eliminado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(true, flag);
    }
    
    @Test
    public void EmpleadoBorradoMailVacio() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.borrarEmpleado("").equals("Usuario eliminado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void EmpleadoBorradoMailIncorrecto() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.borrarEmpleado("fede@noexiste.com").equals("Usuario eliminado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }

}
