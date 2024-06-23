package vista;

import modelos.Empleado;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaLogueo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpMail;
    private JPasswordField inpContraseña;
    private JLabel lblAviso;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PantallaLogueo frame = new PantallaLogueo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PantallaLogueo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 383);
        
        // Reemplazar contentPane por una instancia de ImagePanel con la ruta correcta
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("BIENVENIDO");
        lblNewLabel_1.setBounds(5, 7, 601, 55);
        lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 35));
        contentPane.add(lblNewLabel_1);


        JLabel lblNewLabel_2 = new JLabel("JUMBOX - SUPERMERCADO");
        lblNewLabel_2.setBounds(5, 40, 601, 55);
        lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
        contentPane.add(lblNewLabel_2);

        JLabel lblMail = new JLabel("MAIL");
        lblMail.setHorizontalTextPosition(SwingConstants.CENTER);
        lblMail.setHorizontalAlignment(SwingConstants.CENTER);
        lblMail.setFont(new Font("Consolas", Font.BOLD, 20));
        lblMail.setBounds(0, 96, 611, 55);
        contentPane.add(lblMail);
        
        inpMail = new JTextField();
        inpMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpMail.setBounds(149, 140, 301, 31);
        inpMail.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        contentPane.add(inpMail);
        
        JLabel lblContraseña = new JLabel("CONTRASEÑA");
        lblContraseña.setBounds(0, 182, 611, 55);
        lblContraseña.setFont(new Font("Consolas", Font.BOLD, 20));
        lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
        lblContraseña.setHorizontalTextPosition(SwingConstants.CENTER);
        contentPane.add(lblContraseña);

        inpContraseña = new JPasswordField();
        inpContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpContraseña.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpContraseña.setToolTipText("Ingrese su contraseña");
        inpContraseña.setBounds(149, 224, 301, 31);
        contentPane.add(inpContraseña);

        JButton btnIngresar = new JButton("INGRESAR");
        btnIngresar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnIngresar.setBounds(193, 302, 99, 31);
        contentPane.add(btnIngresar);

        JButton btnSalir = new JButton("SALIR");
        btnSalir.setBackground(new Color(255, 255, 255));
        btnSalir.setFont(new Font("Consolas", Font.BOLD, 13));
        btnSalir.setBounds(317, 302, 99, 31);
        contentPane.add(btnSalir);
        
        JLabel lblAviso = new JLabel("");
        lblAviso.setForeground(Color.RED);
        lblAviso.setFont(new Font("Consolas", Font.BOLD, 16));
        lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
        lblAviso.setBounds(5, 270, 601, 21);
        lblAviso.setVisible(false);
        contentPane.add(lblAviso);

        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mail = inpMail.getText();
                String password = new String(inpContraseña.getPassword());
                String respuesta = Empleado.iniciarSesion(mail, password);
                if (respuesta.equals("Inicio de sesión exitoso.")) {
                    PantallaHome pantallaHome = new PantallaHome(mail); 
                    pantallaHome.setVisible(true);
                    dispose();
                } else {
                	lblAviso.setText(respuesta);
                	lblAviso.setVisible(true);
                }
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}