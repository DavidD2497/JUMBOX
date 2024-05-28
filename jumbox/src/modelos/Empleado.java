package modelos;

import controladores.EmpleadoControlador;
import javax.swing.JOptionPane;

public abstract class Empleado {
	private String nombre;
    private String email;
    private String contraseña;
	
	public Empleado(String nombre, String email, String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
    public boolean iniciarSesion(String email, String contraseña) {
        if (email.isEmpty() || contraseña.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Email y/o contraseña no pueden estar vacíos.");
            return false;
        }
    	
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmailAndPassword(email, contraseña);

        if (empleado != null) {
            //JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
            return true;
        } else {
            //JOptionPane.showMessageDialog(null, "Error de inicio de sesión. Verifique sus credenciales.");
            return false;
        }
    }
    
    public boolean registrarEmpleado(String nombre, String email, String contraseña, String tipo) {

        if (nombre.isEmpty() || email.isEmpty() || contraseña.isEmpty() || tipo.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false;
        }


        if (!email.contains("@")) {
            //JOptionPane.showMessageDialog(null, "Email no válido. Debe contener un '@'.");
            return false;
        }

        EmpleadoControlador empleadoControlador = new EmpleadoControlador();

        if (empleadoControlador.getUserByEmail(email) != null) {
            //JOptionPane.showMessageDialog(null, "Ya existe un usuario con este email.");
            return false;
        }

        Empleado nuevoEmpleado;
        switch (tipo) {
            case "AdminDeposito":
                nuevoEmpleado = new AdminDeposito(nombre, email, contraseña);
                break;
            case "AdminSucursal":
                nuevoEmpleado = new AdminSucursal(nombre, email, contraseña);
                break;
            case "Cajero":
                nuevoEmpleado = new Cajero(nombre, email, contraseña);
                break;
            default:
                //JOptionPane.showMessageDialog(null, "Tipo de usuario no válido.");
                return false;
        }

        empleadoControlador.addUser(nuevoEmpleado);
        //JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
        return true;
    }
    
    public boolean editarEmpleado(String email, String nuevoNombre, String nuevoEmail, String nuevaContraseña, String nuevoTipo) {
        if (email.isEmpty() || nuevoNombre.isEmpty() || nuevoEmail.isEmpty() || nuevaContraseña.isEmpty() || nuevoTipo.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false;
        }

        if (!nuevoEmail.contains("@")) {
            //JOptionPane.showMessageDialog(null, "Nuevo email no válido. Debe contener un '@'.");
            return false;
        }

        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(email);

        if (empleado != null) {
            empleado.setNombre(nuevoNombre);
            empleado.setEmail(nuevoEmail);
            empleado.setContraseña(nuevaContraseña);

            Empleado nuevoEmpleado = null;
            switch (nuevoTipo) {
                case "AdminDeposito":
                    nuevoEmpleado = new AdminDeposito(nuevoNombre, nuevoEmail, nuevaContraseña);
                    break;
                case "AdminSucursal":
                    nuevoEmpleado = new AdminSucursal(nuevoNombre, nuevoEmail, nuevaContraseña);
                    break;
                case "Cajero":
                    nuevoEmpleado = new Cajero(nuevoNombre, nuevoEmail, nuevaContraseña);
                    break;
                default:
                    //JOptionPane.showMessageDialog(null, "Tipo de usuario no válido.");
                    return false;
            }

            // Actualizar los datos del empleado
            empleadoControlador.updateUser(email, nuevoEmpleado);
            //JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
            return true;
        } else {
            //JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el email proporcionado.");
            return false;
        }
    }
    
    public boolean borrarEmpleado(String email) {
        if (email.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "El email no puede estar vacío.");
            return false;
        }

        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(email);

        if (empleado != null) {
            empleadoControlador.deleteUser(email);
            //JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
            return true;
        } else {
            //JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el email proporcionado.");
            return false;
        }
    }
    


}	
