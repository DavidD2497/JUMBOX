package vista;

import modelos.Empleado;
import modelos.AdminSucursal;
import modelos.DetalleInforme;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controladores.EmpleadoControlador;
import controladores.AdminSucursalControlador;
import controladores.DetalleInformeControlador;
import controladores.ProductoControlador;
import modelos.Producto;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class PantallaEditarTipo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpCANTIDAD;
    private DefaultTableModel tableModel;
    private JTextField intIDsuc;
    private int selectedRow = -1;
    private DefaultTableModel detalleTableModel;
    private JTable detalleTable;
    private JScrollPane detalleScrollPane;

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
    public PantallaEditarTipo(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 894, 563);
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);

        // Reemplazar contentPane por una instancia de ImagePanel con la ruta correcta
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Editar Informe");
        lblNewLabel_2.setBounds(185, 43, 496, 40);
        lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
        contentPane.add(lblNewLabel_2);

        detalleTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Id Detalle", "Tipo de registro", "Id Tipo" }
                cargarDetallesInforme();
            );

            detalleTable = new JTable(detalleTableModel);
            detalleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            detalleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));

            

           
            


        detalleTable = new JTable(detalleTableModel);
        detalleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        detalleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));

        detalleScrollPane = new JScrollPane(detalleTable);
        detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        detalleScrollPane.setBounds(65, 102, 761, 167);
        detalleScrollPane.setVisible(false); // Inicialmente invisible
        contentPane.add(detalleScrollPane);

        JLabel lblCANTIDAD = new JLabel("Tipo:");
        lblCANTIDAD.setBounds(339, 341, 200, 40);
        lblCANTIDAD.setFont(new Font("Consolas", Font.BOLD, 20));
        lblCANTIDAD.setHorizontalAlignment(SwingConstants.CENTER);
        lblCANTIDAD.setHorizontalTextPosition(SwingConstants.CENTER);
        contentPane.add(lblCANTIDAD);

        inpCANTIDAD = new JTextField();
        inpCANTIDAD.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpCANTIDAD.setBounds(306, 376, 261, 31);
        inpCANTIDAD.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        contentPane.add(inpCANTIDAD);

        JButton btnRegistrar = new JButton("EDITAR");
        btnRegistrar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnRegistrar.setBounds(386, 462, 99, 33);
        contentPane.add(btnRegistrar);

        JButton btnVolveralHome = new JButton("Volver");
        btnVolveralHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaAdminSucursal adminsucursal = new PantallaAdminSucursal(empleado.getEmail());
                adminsucursal.setVisible(true);
                dispose();
            }
        });
        btnVolveralHome.setFont(new Font("Consolas", Font.BOLD, 15));
        btnVolveralHome.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        btnVolveralHome.setBounds(0, 0, 99, 33);
        contentPane.add(btnVolveralHome);
    }

    private void cargarDetallesInforme(int idInforme) {
        DetalleInformeControlador detalleInformeControlador = new DetalleInformeControlador();
        List<DetalleInforme> detalles = detalleInformeControlador.getAllDetalleInformesByInformeId(idInforme);
        detalleTableModel.setRowCount(0);
        for (DetalleInforme detalle : detalles) {
            detalleTableModel.addRow(new Object[] { detalle.getIdDetalle(), detalle.getTipo(), detalle.getIdTipo() });
        }
        
    }

    
    
}
