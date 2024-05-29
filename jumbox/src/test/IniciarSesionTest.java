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
            if (empleado.iniciarSesion("ana.torres@gmail.com", "1234")) {
                flag = true;
                break;
            }
        }

        assertEquals(true, flag);
    }

    @Test
    public void inicioSesionMailVacio() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.iniciarSesion("", "contraseña45")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }

    @Test
    public void inicioSesionContraseñaVacia() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.iniciarSesion("ana@example.com", "")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void inicioSesionAmbosCamposVacios() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.iniciarSesion("", "")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }

    @Test
    public void inicioSesionEmailCorrectoContraseñaIncorrecta() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.iniciarSesion("ana.torres@gmail.com", "contraseñaIncorrecta")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    


}
