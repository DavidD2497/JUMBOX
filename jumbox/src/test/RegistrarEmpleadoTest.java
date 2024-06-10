package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modelos.Empleado;
import controladores.EmpleadoControlador;

public class RegistrarEmpleadoTest {

    @Test
    public void RegistroEmpleadoCorrecto() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.registrarEmpleado("David", "david@gmail.com", "1234","Cajero").equals("Usuario registrado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(true, flag);
    }
    
    @Test
    public void RegistroEmpleadoNombreVacio() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.registrarEmpleado("", "david@gmail.com", "1234","Cajero").equals("Usuario registrado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void RegistroEmpleadoEmailVacio() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.registrarEmpleado("David", "", "1234","Cajero").equals("Usuario registrado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void RegistroEmpleadoContraseñaVacia() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.registrarEmpleado("David", "david@gmail.com", "","Cajero").equals("Usuario registrado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void RegistroEmpleadoTipoVacio() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.registrarEmpleado("David", "david@gmail.com", "1234","").equals("Usuario registrado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void RegistroEmpleadoEmailSinArroba() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.registrarEmpleado("David", "davidgmail.com", "1234","Cajero").equals("Usuario registrado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void RegistroEmpleadoEmailYaExistente() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.registrarEmpleado("David", "ana.torres@gmail.com", "1234","Cajero").equals("Usuario registrado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }
    
    @Test
    public void RegistroEmpleadoTipoNoValido() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        boolean flag = false;

        for (Empleado empleado : empleadoControlador.getAllUsers()) {
            if (empleado.registrarEmpleado("David", "david2@gmail.com", "1234", "Admin").equals("Usuario registrado exitosamente.")) {
                flag = true;
                break;
            }
        }

        assertEquals(false, flag);
    }


}
