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

public class PantallaCajero extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;


	public PantallaCajero(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 383);
        
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);
		
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("MENÚ PRINCIPAL - CAJERO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        lblNewLabel.setBounds(10, 0, 590, 68);
        contentPane.add(lblNewLabel);
        
        
        JButton btnRegistrarVenta = new JButton("Registrar Venta");
        btnRegistrarVenta.setFont(new Font("Consolas", Font.BOLD, 15));
        btnRegistrarVenta.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnRegistrarVenta.setBounds(219, 94, 156, 33);
        btnRegistrarVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //PantallaRegistroEmpleado pantallaRegistroEmpleado = new PantallaRegistroEmpleado(empleado.getEmail());
                //pantallaRegistroEmpleado.setVisible(true);
                //dispose();
            }
        });
        contentPane.add(btnRegistrarVenta);
        
        JButton btnRegistrarSalidaInventario = new JButton("Registrar Salidas");
        btnRegistrarSalidaInventario.setFont(new Font("Consolas", Font.BOLD, 15));
        btnRegistrarSalidaInventario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnRegistrarSalidaInventario.setBounds(219, 174, 156, 33);
        btnRegistrarSalidaInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	PantallaRegistrarSalidaSucursal pantallaRegistrarSalidaSucursal = new PantallaRegistrarSalidaSucursal(empleado.getEmail());
            	pantallaRegistrarSalidaSucursal.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnRegistrarSalidaInventario);

        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaHome pantallaHome = new PantallaHome(mail);
                pantallaHome.setVisible(true);
                dispose();
            }
        });
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 15));
        btnVolver.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnVolver.setBounds(259, 283, 73, 33);
        contentPane.add(btnVolver);


	}
}