package modelos;

import java.util.LinkedList;
import controladores.EmpleadoControlador;
import javax.swing.JOptionPane;

public abstract class Empleado {
	private String nombre;
    private String email;
    private String contraseña;
	private LinkedList<AdminSucursal> listaAdminSucursal = new LinkedList<>();
	private LinkedList<AdminDeposito> listaAdminDeposito = new LinkedList<>();
	private LinkedList<Cajero> listaCajero = new LinkedList<>();
	
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

	public LinkedList<AdminSucursal> getListaAdminSucursal() {
		return listaAdminSucursal;
	}

	public void setListaAdminSucursal(LinkedList<AdminSucursal> listaAdminSucursal) {
		this.listaAdminSucursal = listaAdminSucursal;
	}

	public LinkedList<AdminDeposito> getListaAdminDeposito() {
		return listaAdminDeposito;
	}

	public void setListaAdminDeposito(LinkedList<AdminDeposito> listaAdminDeposito) {
		this.listaAdminDeposito = listaAdminDeposito;
	}

	public LinkedList<Cajero> getListaCajero() {
		return listaCajero;
	}

	public void setListaCajero(LinkedList<Cajero> listaCajero) {
		this.listaCajero = listaCajero;
	}
	
	public abstract int getId();
	
	
    public boolean iniciarSesion(String email, String contraseña) {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmailAndPassword(email, contraseña);

        if (empleado != null) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error de inicio de sesión. Verifique sus credenciales.");
            return false;
        }
    }
    
    public void registrarEmpleado(String nombre, String email, String contraseña, String tipo) {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();

        if (empleadoControlador.getUserByEmail(email) != null) {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con este email.");
            return;
        }

        Empleado nuevoEmpleado;
        switch (tipo) {
            case "AdminDeposito":
                nuevoEmpleado = new AdminDeposito(nombre, email, contraseña, 0);
                break;
            case "AdminSucursal":
                nuevoEmpleado = new AdminSucursal(nombre, email, contraseña, 0);
                break;
            case "Cajero":
                nuevoEmpleado = new Cajero(nombre, email, contraseña, 0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tipo de usuario no válido.");
                return;
        }

        empleadoControlador.addUser(nuevoEmpleado);
        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
    }
    
    public void borrarEmpleado(String email) {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(email);

        if (empleado != null) {
            empleadoControlador.deleteUser(empleado.getId());
            JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el email proporcionado.");
        }
    }
    
    public void editarUsuario(String email, String nuevoNombre, String nuevoEmail, String nuevaContraseña, String nuevoTipo) {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(email);

        if (empleado != null) {
            empleado.setNombre(nuevoNombre);
            empleado.setEmail(nuevoEmail);
            empleado.setContraseña(nuevaContraseña);

            switch (nuevoTipo) {
                case "AdminDeposito":
                    if (!(empleado instanceof AdminDeposito)) {
                        empleado = new AdminDeposito(nuevoNombre, nuevoEmail, nuevaContraseña, empleado.getId());
                    }
                    break;
                case "AdminSucursal":
                    if (!(empleado instanceof AdminSucursal)) {
                        empleado = new AdminSucursal(nuevoNombre, nuevoEmail, nuevaContraseña, empleado.getId());
                    }
                    break;
                case "Cajero":
                    if (!(empleado instanceof Cajero)) {
                        empleado = new Cajero(nuevoNombre, nuevoEmail, nuevaContraseña, empleado.getId());
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Tipo de usuario no válido.");
                    return;
            }

            empleadoControlador.updateUser(empleado);
            JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el email proporcionado.");
        }
    }
    
}	
