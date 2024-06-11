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
import vista.PantallaRegistroEntrada;

import controladores.EmpleadoControlador;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaInforme extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public PantallaInforme(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 383);
        
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);
        
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Informe");
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
        
        JButton btnMostrarInforme = new JButton("Mostrar Informe");
        btnMostrarInforme.setFont(new Font("Consolas", Font.BOLD, 15));
        btnMostrarInforme.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnMostrarInforme.setBounds(40, 162, 173, 33);
        btnMostrarInforme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Registro de entrada
                // PantallaRegistroEntrada pantallaRegistroEntrada = new PantallaRegistroEntrada(empleado.getEmail());
              //  pantallaRegistroEntrada.setVisible(true);
                // dispose();
            }
        });
        contentPane.add(btnMostrarInforme);
        
        JButton btnCrearInforme = new JButton("Crear Informe");
        btnCrearInforme.setFont(new Font("Consolas", Font.BOLD, 15));
        btnCrearInforme.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnCrearInforme.setBounds(230, 105, 173, 33);
        btnCrearInforme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Pedido
              //  PantallaPedido pantallaPedido = new PantallaPedido(empleado.getEmail());
              //  pantallaPedido.setVisible(true);
             //   dispose();
            }
        });
        contentPane.add(btnCrearInforme);
        
        JButton btnEditarInforme = new JButton("Editar Informe");
        btnEditarInforme.setFont(new Font("Consolas", Font.BOLD, 15));
        btnEditarInforme.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnEditarInforme.setBounds(230, 218, 173, 33);
        btnEditarInforme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Informe
               //  PantallaInforme pantallaInforme = new PantallaInforme(empleado.getEmail());
                // pantallaInforme.setVisible(true);
               //  dispose();
            }
        });
        contentPane.add(btnEditarInforme);
        
        JButton btnEliminarInforme = new JButton("Eliminar Informe");
        btnEliminarInforme.setFont(new Font("Consolas", Font.BOLD, 15));
        btnEliminarInforme.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnEliminarInforme.setBounds(410, 162, 173, 33);
        btnEliminarInforme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Descuento
             //   PantallaDescuento pantallaDescuento = new PantallaDescuento(empleado.getEmail());
              //  pantallaDescuento.setVisible(true);
                // dispose();
            }
        });
        contentPane.add(btnEliminarInforme);
        
        JButton btnVolveralHome = new JButton("Volver");
        btnVolveralHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AdminSucursal adminsucursal = new AdminSucursal(empleado.getEmail());
                adminsucursal.setVisible(true);
                dispose();
            }
        });
        btnVolveralHome.setFont(new Font("Consolas", Font.BOLD, 15));
        btnVolveralHome.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnVolveralHome.setBounds(444, 300, 156, 33);
        contentPane.add(btnVolveralHome);
    }
}