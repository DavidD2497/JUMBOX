package vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class PantallaLogueo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField pwdIngreseSuContrasea;

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
        setBounds(100, 100, 627, 383);
        
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

        JLabel lblNewLabel = new JLabel("CONTRASEÑA");
        lblNewLabel.setBounds(0, 182, 611, 55);
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("JUMBOX - SUPERMERCADO");
        lblNewLabel_2.setBounds(5, 40, 601, 55);
        lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
        contentPane.add(lblNewLabel_2);

        JTextArea txtrIngreseSuMail = new JTextArea();
        txtrIngreseSuMail.setBounds(149, 140, 301, 31);
        txtrIngreseSuMail.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        contentPane.add(txtrIngreseSuMail);

        JLabel lblMail = new JLabel("MAIL");
        lblMail.setHorizontalTextPosition(SwingConstants.CENTER);
        lblMail.setHorizontalAlignment(SwingConstants.CENTER);
        lblMail.setFont(new Font("Consolas", Font.BOLD, 20));
        lblMail.setBounds(0, 96, 611, 55);
        contentPane.add(lblMail);

        pwdIngreseSuContrasea = new JPasswordField();
        pwdIngreseSuContrasea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        pwdIngreseSuContrasea.setToolTipText("Ingrese su contraseña");
        pwdIngreseSuContrasea.setBounds(149, 224, 301, 31);
        contentPane.add(pwdIngreseSuContrasea);

        JButton btnNewButton = new JButton("INGRESAR");
        btnNewButton.setFont(new Font("Consolas", Font.BOLD, 13));
        btnNewButton.setBounds(190, 289, 99, 31);
        contentPane.add(btnNewButton);

        JButton btnSalir = new JButton("SALIR");
        btnSalir.setBackground(new Color(255, 255, 255));
        btnSalir.setFont(new Font("Consolas", Font.BOLD, 13));
        btnSalir.setBounds(317, 289, 99, 31);
        contentPane.add(btnSalir);
    }
}
