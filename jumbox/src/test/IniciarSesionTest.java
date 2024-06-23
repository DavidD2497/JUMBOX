package test;
<<<<<<< HEAD

=======
>>>>>>> origin/vicky
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modelos.Empleado;
import controladores.EmpleadoControlador;

public class IniciarSesionTest {

<<<<<<< HEAD
    @Test
    public void inicioSesionCorrecto() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (Empleado.iniciarSesion("ana.torres@gmail.com", "1234").equals("Inicio de sesión exitoso.")) {
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
            if (Empleado.iniciarSesion("", "contraseña45").equals("Inicio de sesión exitoso.")) {
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
            if (Empleado.iniciarSesion("ana@example.com", "").equals("Inicio de sesión exitoso.")) {
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
            if (Empleado.iniciarSesion("", "").equals("Inicio de sesión exitoso.")) {
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
            if (Empleado.iniciarSesion("ana.torres@gmail.com", "contraseñaIncorrecta").equals("Inicio de sesión exitoso.")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
}

=======
	
	
}
>>>>>>> origin/vicky
