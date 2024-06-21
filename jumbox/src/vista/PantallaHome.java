package vista;

import modelos.Empleado;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import controladores.EmpleadoControlador;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaHome extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;


	public PantallaHome(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 383);
        
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);
		
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("MENÚ PRINCIPAL");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        lblNewLabel.setBounds(10, 0, 590, 68);
        contentPane.add(lblNewLabel);
        
        JLabel lblIdentificacion = new JLabel("");
        lblIdentificacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblIdentificacion.setFont(new Font("Consolas", Font.BOLD, 20));
        lblIdentificacion.setBounds(10, 31, 590, 68);
        if (empleadoControlador.getUserTypeByEmail(mail).equals("Cajero")) {
            lblIdentificacion.setText(empleado.getNombre() + " -  Cajero/a" );
		} else if (empleadoControlador.getUserTypeByEmail(mail).equals("AdminSucursal")) {
			lblIdentificacion.setText(empleado.getNombre() + " -  Administrador/a de Sucursal" );
		} else {
			lblIdentificacion.setText(empleado.getNombre() + " -  Administrador/a de Depósito" );
		} 

        contentPane.add(lblIdentificacion);
        
        JButton btnCrearUsuario = new JButton("Crear Usuario");
        btnCrearUsuario.setFont(new Font("Consolas", Font.BOLD, 15));
        btnCrearUsuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnCrearUsuario.setBounds(10, 189, 156, 33);
        btnCrearUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaRegistroEmpleado pantallaRegistroEmpleado = new PantallaRegistroEmpleado(empleado.getEmail());
                pantallaRegistroEmpleado.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnCrearUsuario);
        
        
        JButton btnEditarUsuario = new JButton("Editar Usuario");
        btnEditarUsuario.setFont(new Font("Consolas", Font.BOLD, 15));
        btnEditarUsuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnEditarUsuario.setBounds(433, 189, 156, 33);
        btnEditarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaEditarEmpleado pantallaEditarEmpleado = new PantallaEditarEmpleado(empleado.getEmail());
                pantallaEditarEmpleado.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnEditarUsuario);
        
        JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
        btnEliminarUsuario.setFont(new Font("Consolas", Font.BOLD, 15));
        btnEliminarUsuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnEliminarUsuario.setBounds(219, 189, 156, 33);
        btnEliminarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaEliminarEmpleado pantallaEliminarEmpleado = new PantallaEliminarEmpleado(empleado.getEmail());
                pantallaEliminarEmpleado.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnEliminarUsuario);
        
        JButton btnFuncionPrincipal = new JButton("");
        btnFuncionPrincipal.setFont(new Font("Consolas", Font.BOLD, 15));
        btnFuncionPrincipal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnFuncionPrincipal.setBounds(96, 110, 392, 33);
        if (empleadoControlador.getUserTypeByEmail(mail).equals("Cajero")) {
        	btnFuncionPrincipal.setText("Funciones Cajero/a" );
            btnFuncionPrincipal.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {

                      PantallaCajero pantallaCajero = new PantallaCajero(empleado.getEmail());
                      pantallaCajero.setVisible(true);
                      dispose();
                  }
              });
		} else if (empleadoControlador.getUserTypeByEmail(mail).equals("AdminSucursal")) {
			btnFuncionPrincipal.setText("Funciones Administrador/a de Sucursal" );
			  btnFuncionPrincipal.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                
		                PantallaAdminSucursal adminSucursal = new PantallaAdminSucursal(empleado.getEmail());
		                adminSucursal.setVisible(true);
		                dispose();
		            }
		        });
		} else {
			btnFuncionPrincipal.setText("Funciones Administrador/a de Depósito" );
		}
        contentPane.add(btnFuncionPrincipal);
        
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaLogueo pantallaLogueo = new PantallaLogueo();
                pantallaLogueo.setVisible(true);
                dispose();
            }
        });
        btnCerrarSesion.setFont(new Font("Consolas", Font.BOLD, 15));
        btnCerrarSesion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnCerrarSesion.setBounds(219, 271, 156, 33);
        contentPane.add(btnCerrarSesion);


	}
}
