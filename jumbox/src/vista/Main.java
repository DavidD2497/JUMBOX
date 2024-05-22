package vista;

import javax.swing.JOptionPane;


import modelos.AdminDeposito;
import modelos.AdminSucursal;
import modelos.Cajero;
import modelos.Empleado;

class Main {

	public static void main(String[] args) {
		int opcion;
		boolean login=false;
		int id=0;
		String pass="";
		do {
		String[] menu = { "Administrador de sucursal", "Administrador de depósito", "Cajero", "Salir" };
		opcion = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
				"Selecciona una opción", 0, 0, null, menu, menu[0]);
		String tipoUsuario = menu[opcion];
		
		Empleado empleadoManager = new Empleado("", "");
					if (opcion<3) {
						id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su id de empleado"));
						
						pass = JOptionPane.showInputDialog("Ingrese su contraseña");
					
						//login = empleadoManager.iniciarSesion(id, pass, tipoUsuario);
					}
					if (login == true) {

						switch (opcion) {
						case 0:
							for (AdminSucursal adminSuc : empleadoManager.getListaAdminSucursal()) {
								if (adminSuc.getIdAdminSuc() == id && adminSuc.getContraseña().equals(pass)) {
									empleadoManager = adminSuc;
									break;
								}
							}
							//empleadoManager.bienvenida();
							int opcion2;
							do {
							String[] menu2 = { "Registrar entradas", "Crear descuento", "Generar informe",
									"Solicitar pedido", "Salir" };
							opcion2 = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
									"Selecciona una opción", 0, 0, null, menu2, menu2[0]);
							
								switch (opcion2) {
								case 0:
									JOptionPane.showMessageDialog(null, "El administrador de sucursal registrara las entradas de los productos que llegan del deposito.");
									break;
								case 1:
									JOptionPane.showMessageDialog(null, "El administrador de sucursal podra crear promociones a los productos basandose en temporada, stock o fecha de vencimiento.");
									break;
								case 2:
									JOptionPane.showMessageDialog(null, "El administrador de sucursal podra general informe en el cual saldran las ventas totales del mes y el stock actual de la sucursal");
									break;
								case 3:
									JOptionPane.showMessageDialog(null, "El administrador de sucursal podra solicitar un pedido al administrador de deposito segun lo faltante en su sucursal.");
									break;

								default:
									break;
								}
							} while (opcion2 != 4);
							break;
						case 1:
							for (AdminDeposito adminDep : empleadoManager.getListaAdminDeposito()) {
								if (adminDep.getIdAdminDepo() == id && adminDep.getContraseña().equals(pass)) {
									empleadoManager = adminDep;
									break;
								}
							}
						//	empleadoManager.bienvenida();
							int opcion3;
							do {
							String[] menu3 = { "Registrar salidas", "Armar pedido", "Salir" };
							opcion3 = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
									"Selecciona una opción", 0, 0, null, menu3, menu3[0]);

								switch (opcion3) {
								case 0:
									JOptionPane.showMessageDialog(null, "El administrador de deposito registra las salidas de los productos que salen del deposito");
									break;
								case 1:
									JOptionPane.showMessageDialog(null, "El administrador de deposito podra armar los pedidos recibidos por el administrador de sucursal y le fija una fecha de entrega");
									break;
								default:
									break;
								}
							} while (opcion3 != 2);
							break;
						case 2:
							for (Cajero cajero : empleadoManager.getListaCajero()) {
								if (cajero.getIdCajero() == id && cajero.getContraseña().equals(pass)) {
									empleadoManager = cajero;
									break;
								}
							}
						//	empleadoManager.bienvenida();
							int opcion4;
							do {
							String[] menu4 = { "Registrar venta", "Salir" };
							opcion4 = JOptionPane.showOptionDialog(null, "Seleccione la opción segun corresponda",
									"Selecciona una opción", 0, 0, null, menu4, menu4[0]);
							
								switch (opcion4) {
								case 0:
									JOptionPane.showMessageDialog(null, "El cajero registrara las ventas de la sucursal, en las que registrará la salida de los producto, el tipo de pago y el monto total.");
									break;
								default:
									break;
								}
							} while (opcion4 != 1);
							break;
						default:
							break;
						}
					} else if (login==false && opcion!=3){
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto, intente de nuevo.");
					}

			

		} while (opcion != 3);
	}
}