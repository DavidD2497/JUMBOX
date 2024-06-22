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

public class PantallaAdminDeposito extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public PantallaAdminDeposito(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 383);
        
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);
        
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Administrador Deposito");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        lblNewLabel.setBounds(10, 0, 590, 68);
        contentPane.add(lblNewLabel);
        
        JLabel lblIdentificacion = new JLabel("");
        lblIdentificacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblIdentificacion.setFont(new Font("Consolas", Font.BOLD, 20));
        lblIdentificacion.setBounds(10, 54, 590, 68);
        if (empleadoControlador.getUserTypeByEmail(mail).equals("Cajero")) {
            lblIdentificacion.setText(empleado.getNombre() + " -  Cajero/a" );
        } else if (empleadoControlador.getUserTypeByEmail(mail).equals("AdminSucursal")) {
            lblIdentificacion.setText(empleado.getNombre() + " -  Administrador/a de Sucursal" );
        } else {
            lblIdentificacion.setText(empleado.getNombre() + " -  Administrador/a de Depósito" );
        } 

        contentPane.add(lblIdentificacion);
        
        JButton btnRegistrarSalida = new JButton("Registrar Salida");
        btnRegistrarSalida.setFont(new Font("Consolas", Font.BOLD, 15));
        btnRegistrarSalida.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnRegistrarSalida.setBounds(74, 133, 156, 33);
        btnRegistrarSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Registro de entrada
                PantallaSalidaDeposito pantallaSalidaDeposito = new PantallaSalidaDeposito(empleado.getEmail());
                pantallaSalidaDeposito.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnRegistrarSalida);
        
        JButton btnPedido = new JButton("Pedido");
        btnPedido.setFont(new Font("Consolas", Font.BOLD, 15));
        btnPedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnPedido.setBounds(352, 133, 173, 33);
        btnPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de Pedido
                PantallaPedidoDeposito pantallaPedidoDeposito = new PantallaPedidoDeposito(empleado.getEmail());
                pantallaPedidoDeposito.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnPedido);
        
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
        
        JButton btnConfirmarSolicitud = new JButton("Confirmar Solicitud de pedido");
        btnConfirmarSolicitud.setFont(new Font("Consolas", Font.BOLD, 15));
        btnConfirmarSolicitud.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnConfirmarSolicitud.setBounds(164, 229, 265, 33);
        contentPane.add(btnConfirmarSolicitud);
    }
}