package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modelos.Empleado;
import controladores.EmpleadoControlador;

public class EditarEmpleadoTest {

    @Test
    public void EditarEmpleadoCorrecto() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.editarEmpleado("david@gmail.com", "NuevoNombre", "david@gmail.com", "nuevaContraseña", "AdminDeposito")) {
                flag = true;
                break;
            }
        }

        assertEquals(true, flag);
    }
    
    @Test
    public void EditarEmpleadoEmailVacio() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.editarEmpleado("", "NuevoNombre", "nuevoemail@gmail.com", "nuevaContraseña", "AdminDeposito")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void EditarEmpleadoCamposVacios() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.editarEmpleado("david@gmail.com", "", "", "", "")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void EditarEmpleadoTipoNoValido() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.editarEmpleado("david@gmail.com", "NuevoNombre", "nuevoemail@gmail.com", "nuevaContraseña", "TipoInvalido")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void EditarEmpleadoNuevoEmailInvalido() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.editarEmpleado("david@gmail.com", "NuevoNombre", "nuevoemailgmail.com", "nuevaContraseña", "AdminDeposito")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void EditarEmpleadoEmailIncorrecto() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.editarEmpleado("noexiste@gmail.com", "NuevoNombre", "nuevoemail@gmail.com", "nuevaContraseña", "AdminDeposito")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
}

