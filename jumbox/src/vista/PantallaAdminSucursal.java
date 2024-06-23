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

public class PantallaAdminSucursal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public PantallaAdminSucursal(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 383);
        
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);
        
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Administrador Sucursal");
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
        
        JButton btnRegistroEntrada = new JButton("Registro de entrada");
        btnRegistroEntrada.setFont(new Font("Consolas", Font.BOLD, 15));
        btnRegistroEntrada.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnRegistroEntrada.setBounds(219, 89, 156, 33);
        btnRegistroEntrada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Registro de entrada
                PantallaRegistroEntrada pantallaRegistroEntrada = new PantallaRegistroEntrada(empleado.getEmail());
                pantallaRegistroEntrada.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnRegistroEntrada);
        
        JButton btnPedido = new JButton("Pedido");
        btnPedido.setFont(new Font("Consolas", Font.BOLD, 15));
        btnPedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnPedido.setBounds(21, 176, 173, 33);
        btnPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Pedido
                PantallaPedido pantallaPedido = new PantallaPedido(empleado.getEmail());
                pantallaPedido.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnPedido);
        
        JButton btnInforme = new JButton("Informe");
        btnInforme.setFont(new Font("Consolas", Font.BOLD, 15));
        btnInforme.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnInforme.setBounds(219, 267, 156, 33);
        btnInforme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Informe
                PantallaInforme pantallaInforme = new PantallaInforme(empleado.getEmail());
                pantallaInforme.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnInforme);
        
        JButton btnDescuento = new JButton("Descuento");
        btnDescuento.setFont(new Font("Consolas", Font.BOLD, 15));
        btnDescuento.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnDescuento.setBounds(405, 176, 173, 33);
        btnDescuento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Descuento
<<<<<<< HEAD
                PantallaDescuento pantallaDescuento = new PantallaDescuento(empleado.getEmail());
=======
                PantallaDescuentos pantallaDescuento = new PantallaDescuentos(empleado.getEmail());
>>>>>>> origin/vicky
                pantallaDescuento.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnDescuento);
        
        JButton btnVolveralHome = new JButton("Volver");
        btnVolveralHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaHome pantallahome = new PantallaHome(empleado.getEmail());
                pantallahome.setVisible(true);
                dispose();
            }
        });
        btnVolveralHome.setFont(new Font("Consolas", Font.BOLD, 15));
        btnVolveralHome.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnVolveralHome.setBounds(444, 300, 156, 33);
        contentPane.add(btnVolveralHome);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/vicky
