package vista;

import modelos.Empleado;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PantallaRegistroEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpMail;
    private JTextField inpNombre;
    private JTextField inpContraseña;


	public PantallaRegistroEmpleado(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 383);
        
		
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("REGISTRO DE EMPLEADO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        lblNewLabel.setBounds(0, 0, 610, 68);
        contentPane.add(lblNewLabel);
        
        JLabel lblNombre = new JLabel("NOMBRE");
        lblNombre.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setFont(new Font("Consolas", Font.BOLD, 20));
        lblNombre.setBounds(78, 87, 73, 29);
        contentPane.add(lblNombre);
        
        inpMail = new JTextField();
        inpMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpMail.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpMail.setBounds(151, 127, 301, 31);
        contentPane.add(inpMail);
        
        JLabel lblContraseña = new JLabel("CONTRASEÑA");
        lblContraseña.setVerticalAlignment(SwingConstants.BOTTOM);
        lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
        lblContraseña.setFont(new Font("Consolas", Font.BOLD, 20));
        lblContraseña.setBounds(27, 170, 124, 29);
        contentPane.add(lblContraseña);
        
        inpNombre = new JTextField();
        inpNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpNombre.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpNombre.setBounds(151, 86, 301, 31);
        contentPane.add(inpNombre);
        
        JLabel lblMail = new JLabel("MAIL");
        lblMail.setVerticalAlignment(SwingConstants.BOTTOM);
        lblMail.setHorizontalAlignment(SwingConstants.CENTER);
        lblMail.setFont(new Font("Consolas", Font.BOLD, 20));
        lblMail.setBounds(98, 127, 54, 29);
        contentPane.add(lblMail);
        
        inpContraseña = new JTextField();
        inpContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpContraseña.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpContraseña.setBounds(151, 169, 301, 31);
        contentPane.add(inpContraseña);
        
        JLabel lblTipo = new JLabel("TIPO");
        lblTipo.setVerticalAlignment(SwingConstants.BOTTOM);
        lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipo.setFont(new Font("Consolas", Font.BOLD, 20));
        lblTipo.setBounds(97, 210, 54, 29);
        contentPane.add(lblTipo);
        
        String[] tiposEmpleado = { "","AdminSucursal", "Cajero", "AdminDeposito" };
        JComboBox<String> comboBoxTipo = new JComboBox<>(tiposEmpleado);
        comboBoxTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxTipo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        comboBoxTipo.setBounds(151, 211, 301, 29);
        contentPane.add(comboBoxTipo);
        
        
        JLabel lblError = new JLabel("");
        lblError.setFont(new Font("Consolas", Font.BOLD, 16));
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setBounds(0, 262, 610, 14);
        lblError.setVisible(false);
        contentPane.add(lblError);
        
        JLabel lblAprobado = new JLabel("");
        lblAprobado.setHorizontalAlignment(SwingConstants.CENTER);
        lblAprobado.setForeground(Color.GREEN);
        lblAprobado.setFont(new Font("Consolas", Font.BOLD, 16));
        lblAprobado.setBounds(0, 268, 610, 14);
        lblAprobado.setVisible(false);
        contentPane.add(lblAprobado);
        
        JButton btnRegistrarEmpleado = new JButton("Registrar");
        btnRegistrarEmpleado.setFont(new Font("Consolas", Font.BOLD, 15));
        btnRegistrarEmpleado.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nombre = inpNombre.getText();
        		String email = inpMail.getText();
        		String contraseña = inpContraseña.getText();
        		String tipo = (String) comboBoxTipo.getSelectedItem();
        		String respuesta = Empleado.registrarEmpleado(nombre, email, contraseña, tipo);
                if (respuesta.equals("Usuario registrado exitosamente.")) {
                    inpNombre.setText("");
                    inpMail.setText("");
                    inpContraseña.setText("");
                    comboBoxTipo.setSelectedIndex(0);
                    lblAprobado.setText(respuesta);
                    lblAprobado.setVisible(true);
                    lblError.setVisible(false);
                } else {
                    lblError.setText(respuesta);
                    lblError.setVisible(true);
                    lblAprobado.setVisible(false);
                }
        	}
        });
        btnRegistrarEmpleado.setBounds(177, 293, 110, 29);
        contentPane.add(btnRegistrarEmpleado);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 15));
        btnVolver.setBounds(306, 293, 110, 29);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	PantallaHome pantallaHome = new PantallaHome(mail); 
                pantallaHome.setVisible(true);
                dispose();
                
            }
        });
        contentPane.add(btnVolver);

	}
}
