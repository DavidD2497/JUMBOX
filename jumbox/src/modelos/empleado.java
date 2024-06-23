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
	
	
    public static String iniciarSesion(String email, String contraseña) {
        if (email.isEmpty() || contraseña.isEmpty()) {
<<<<<<< HEAD
=======
            //JOptionPane.showMessageDialog(null, "Email y/o contraseña no pueden estar vacíos.");
>>>>>>> origin/vicky
            return "Email y/o contraseña no pueden estar vacíos.";
        }
    	
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmailAndPassword(email, contraseña);

        if (empleado != null) {
<<<<<<< HEAD
            return "Inicio de sesión exitoso.";
        } else {
=======
            //JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
            return "Inicio de sesión exitoso.";
        } else {
            //JOptionPane.showMessageDialog(null, "Error de inicio de sesión. Verifique sus credenciales.");
>>>>>>> origin/vicky
            return "Error de inicio de sesión. Verifique sus credenciales.";
        }
    }
    
<<<<<<< HEAD
    public static String registrarEmpleado(String nombre, String email, String contraseña, String tipo) {

        if (nombre.isEmpty() || email.isEmpty() || contraseña.isEmpty() || tipo.isEmpty()) {
            return "Todos los campos son obligatorios.";

=======
    public boolean registrarEmpleado(String nombre, String email, String contraseña, String tipo) {

        if (nombre.isEmpty() || email.isEmpty() || contraseña.isEmpty() || tipo.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false;
>>>>>>> origin/vicky
        }


        if (!email.contains("@")) {
<<<<<<< HEAD
            return "Email no válido. Debe contener un '@'.";

=======
            //JOptionPane.showMessageDialog(null, "Email no válido. Debe contener un '@'.");
            return false;
>>>>>>> origin/vicky
        }

        EmpleadoControlador empleadoControlador = new EmpleadoControlador();

        if (empleadoControlador.getUserByEmail(email) != null) {
<<<<<<< HEAD
            return "Ya existe un usuario con este email.";
=======
            //JOptionPane.showMessageDialog(null, "Ya existe un usuario con este email.");
            return false;
>>>>>>> origin/vicky
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
<<<<<<< HEAD
                return "Tipo de usuario no válido.";

        }

        empleadoControlador.addUser(nuevoEmpleado);
        return "Usuario registrado exitosamente.";
=======
                //JOptionPane.showMessageDialog(null, "Tipo de usuario no válido.");
                return false;
        }

        empleadoControlador.addUser(nuevoEmpleado);
        //JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
        return true;
>>>>>>> origin/vicky
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
    


<<<<<<< HEAD
}	
=======
}	
>>>>>>> origin/vicky
