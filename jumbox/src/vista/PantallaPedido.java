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

public class PantallaPedido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public PantallaPedido(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 383);
        
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);
        
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Pedido");
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
        
        JButton btnMostrarPedido = new JButton("Mostrar Pedidos");
        btnMostrarPedido.setFont(new Font("Consolas", Font.BOLD, 15));
        btnMostrarPedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnMostrarPedido.setBounds(40, 162, 173, 33);
        btnMostrarPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                PantallaMostrarPedido pantallaMostrarPedido = new PantallaMostrarPedido(empleado.getEmail());
                pantallaMostrarPedido.setVisible(true);
                 dispose();
            }
        });
        contentPane.add(btnMostrarPedido);
        
        JButton btnCrearPedido = new JButton("Crear Pedido");
        btnCrearPedido.setFont(new Font("Consolas", Font.BOLD, 15));
        btnCrearPedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnCrearPedido.setBounds(230, 105, 173, 33);
        btnCrearPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Pedido
              //  PantallaPedido pantallaPedido = new PantallaPedido(empleado.getEmail());
              //  pantallaPedido.setVisible(true);
             //   dispose();
            }
        });
        contentPane.add(btnCrearPedido);
        
        JButton btnEditarPedido = new JButton("Editar Pedido");
        btnEditarPedido.setFont(new Font("Consolas", Font.BOLD, 15));
        btnEditarPedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnEditarPedido.setBounds(230, 218, 173, 33);
        btnEditarPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Informe
               //  PantallaInforme pantallaInforme = new PantallaInforme(empleado.getEmail());
                // pantallaInforme.setVisible(true);
               //  dispose();
            }
        });
        contentPane.add(btnEditarPedido);
        
        JButton btnEliminarPedido = new JButton("Eliminar Pedido");
        btnEliminarPedido.setFont(new Font("Consolas", Font.BOLD, 15));
        btnEliminarPedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnEliminarPedido.setBounds(410, 162, 173, 33);
        btnEliminarPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Descuento
             //   PantallaDescuento pantallaDescuento = new PantallaDescuento(empleado.getEmail());
              //  pantallaDescuento.setVisible(true);
                // dispose();
            }
        });
        contentPane.add(btnEliminarPedido);
        
        JButton btnVolveralHome = new JButton("Volver");
        btnVolveralHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	PantallaAdminSucursal adminsucursal = new PantallaAdminSucursal(empleado.getEmail());
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