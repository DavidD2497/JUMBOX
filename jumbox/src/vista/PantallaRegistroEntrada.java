package vista;

import modelos.Empleado;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import controladores.EmpleadoControlador;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaRegistroEntrada extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private JTextField inpIDPRODUCTO;
    private JTextField inpCANTIDAD;
    private JTextField inpIDINVENTARIOSUCURSAL;
    
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
    public PantallaRegistroEntrada(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 383);
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);
        // Reemplazar contentPane por una instancia de ImagePanel con la ruta correcta
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Jumbox - Supermercado");
        lblNewLabel_1.setBounds(5, 7, 601, 55);
        lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 35));
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Registro Entrada");
        lblNewLabel_2.setBounds(173, 54, 255, 40);
        lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
        contentPane.add(lblNewLabel_2);

        JLabel lblIDPRODUCTO = new JLabel("ID Producto:");
        lblIDPRODUCTO.setHorizontalTextPosition(SwingConstants.CENTER);
        lblIDPRODUCTO.setHorizontalAlignment(SwingConstants.CENTER);
        lblIDPRODUCTO.setFont(new Font("Consolas", Font.BOLD, 20));
        lblIDPRODUCTO.setBounds(10, 124, 200, 40);
        contentPane.add(lblIDPRODUCTO);
        
        inpIDPRODUCTO = new JTextField();
        inpIDPRODUCTO.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpIDPRODUCTO.setBounds(193, 126, 301, 31);
        inpIDPRODUCTO.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        contentPane.add(inpIDPRODUCTO);
        
        JLabel lblCANTIDAD = new JLabel("Cantidad:");
        lblCANTIDAD.setBounds(10, 226, 200, 40);
        lblCANTIDAD.setFont(new Font("Consolas", Font.BOLD, 20));
        lblCANTIDAD.setHorizontalAlignment(SwingConstants.CENTER);
        lblCANTIDAD.setHorizontalTextPosition(SwingConstants.CENTER);
        contentPane.add(lblCANTIDAD);
        
        inpCANTIDAD = new JTextField();
        inpCANTIDAD.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpCANTIDAD.setBounds(173, 228, 301, 31);
        inpCANTIDAD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        contentPane.add(inpCANTIDAD);
        
        JLabel lblIDINVENTARIOSUCURSAL = new JLabel("ID Inventario Sucursal:");
        lblIDINVENTARIOSUCURSAL.setBounds(-19, 175, 301, 40);
        lblIDINVENTARIOSUCURSAL.setFont(new Font("Consolas", Font.BOLD, 20));
        lblIDINVENTARIOSUCURSAL.setHorizontalAlignment(SwingConstants.CENTER);
        lblIDINVENTARIOSUCURSAL.setHorizontalTextPosition(SwingConstants.CENTER);
        contentPane.add(lblIDINVENTARIOSUCURSAL);
        
        inpIDINVENTARIOSUCURSAL = new JTextField();
        inpIDINVENTARIOSUCURSAL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpIDINVENTARIOSUCURSAL.setBounds(269, 177, 301, 31);
        inpIDINVENTARIOSUCURSAL.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        contentPane.add(inpIDINVENTARIOSUCURSAL);
        
        JButton btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnRegistrar.setBounds(193, 302, 99, 31);
        contentPane.add(btnRegistrar);

        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
        btnVolver.setBounds(329, 302, 99, 31);
        contentPane.add(btnVolver);
      
          

        // Lógica del botón REGISTRAR
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idProducto = inpIDPRODUCTO.getText();
                String cantidad = inpCANTIDAD.getText();
                String idInventarioSucursal = inpIDINVENTARIOSUCURSAL.getText();
                

                // Implementar lógica de registro aquí, por ejemplo, validar y registrar en la base de datos

                // Mostrar un mensaje de confirmación o error
                lblAviso.setText("Registro exitoso");
                lblAviso.setVisible(true);
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AdminSucursal adminsucursal = new AdminSucursal(empleado.getEmail());
                adminsucursal.setVisible(true);
                dispose();
            }
        });
    }
}
