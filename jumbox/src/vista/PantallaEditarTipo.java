package vista;

import modelos.Empleado;
import modelos.DetalleInforme;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controladores.EmpleadoControlador;
import controladores.DetalleInformeControlador;

public class PantallaEditarTipo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel tableModel;
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

        // Replace contentPane with an instance of ImagePanel with the correct path
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
                new String[] { "Id Detalle", "Tipo de registro", "Id Tipo" });

        this.cargarDetallesInforme();

        detalleTable = new JTable(detalleTableModel);
        detalleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        detalleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));

        detalleScrollPane = new JScrollPane(detalleTable);
        detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        detalleScrollPane.setBounds(65, 102, 761, 167);
        contentPane.add(detalleScrollPane);

        String[] Tipo = {"Entrada","Pedido","Venta"};
        JComboBox<String> comboBoxIdinventario = new JComboBox<>(Tipo);
        comboBoxIdinventario.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxIdinventario.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        comboBoxIdinventario.setBounds(360, 371, 163, 29);
        contentPane.add(comboBoxIdinventario);

        JLabel lblCANTIDAD = new JLabel("Tipo de Registro:");
        lblCANTIDAD.setBounds(339, 341, 200, 40);
        lblCANTIDAD.setFont(new Font("Consolas", Font.BOLD, 20));
        lblCANTIDAD.setHorizontalAlignment(SwingConstants.CENTER);
        lblCANTIDAD.setHorizontalTextPosition(SwingConstants.CENTER);
        contentPane.add(lblCANTIDAD);

        JLabel lblMensaje = new JLabel("Tipo de Registro actualizado exitosamente.");
        lblMensaje.setFont(new Font("Consolas", Font.BOLD, 15));
        lblMensaje.setForeground(new Color(0, 128, 64));
        lblMensaje.setBounds(238, 411, 415, 19);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setVisible(false);
        contentPane.add(lblMensaje);

        JLabel lblMensajeFalse = new JLabel("No se puede editar al mismo tipo.");
        lblMensajeFalse.setFont(new Font("Consolas", Font.BOLD, 15));
        lblMensajeFalse.setForeground(new Color(255, 0, 0));
        lblMensajeFalse.setBounds(212, 411, 415, 19);
        lblMensajeFalse.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensajeFalse.setVisible(false);
        contentPane.add(lblMensajeFalse);
        
        JLabel lblMensajeFalse1 = new JLabel("Debe seleccionar una fila para editar.");
        lblMensajeFalse1.setFont(new Font("Consolas", Font.BOLD, 15));
        lblMensajeFalse1.setForeground(new Color(255, 0, 0));
        lblMensajeFalse1.setBounds(238, 411, 415, 19);
        lblMensajeFalse1.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensajeFalse1.setVisible(false);
        contentPane.add(lblMensajeFalse1);
        
        JButton btnRegistrar = new JButton("EDITAR");
        btnRegistrar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnRegistrar.setBounds(386, 462, 99, 33);
        contentPane.add(btnRegistrar);

        JButton btnVolveralHome = new JButton("Volver");
        btnVolveralHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaInforme pantallaInforme = new PantallaInforme(empleado.getEmail());
                pantallaInforme.setVisible(true);
                dispose();
            }
        });
        btnVolveralHome.setFont(new Font("Consolas", Font.BOLD, 15));
        btnVolveralHome.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        btnVolveralHome.setBounds(0, 0, 99, 33);
        contentPane.add(btnVolveralHome);

        
        detalleTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = detalleTable.getSelectedRow();
                lblMensaje.setVisible(false);  
            }
        });

        
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow >= 0) {
                    int idDetalle = (int) detalleTableModel.getValueAt(selectedRow, 0);
                    String nuevoTipo = (String) comboBoxIdinventario.getSelectedItem();
                    String tipoActual = (String) detalleTableModel.getValueAt(selectedRow, 1);

                    if (nuevoTipo.equals(tipoActual)) {
                    	lblMensajeFalse.setVisible(true); 
                    	lblMensaje.setVisible(false);  
                    	lblMensajeFalse1.setVisible(false);
                    } else {
                        DetalleInformeControlador detalleInformeControlador = new DetalleInformeControlador();
                        DetalleInforme detalleInforme = detalleInformeControlador.getDetalleInformeById(idDetalle);
                        if (detalleInforme != null) {
                            detalleInforme.setTipo(nuevoTipo);
                            detalleInformeControlador.updateDetalleInforme(detalleInforme);
                           
                            cargarDetallesInforme();
                            lblMensaje.setVisible(true);  
                            lblMensajeFalse.setVisible(false); 
                            lblMensajeFalse1.setVisible(false);
                        }
                    }
                } else {
                	lblMensajeFalse1.setVisible(true);  
                	lblMensajeFalse.setVisible(false);
                	lblMensaje.setVisible(false);
                }
            }
        });
    }

    private void cargarDetallesInforme() {
        DetalleInformeControlador detalleInformeControlador = new DetalleInformeControlador();
        List<DetalleInforme> detalles = detalleInformeControlador.getAllDetalleInformes();
        detalleTableModel.setRowCount(0);
        for (DetalleInforme detalle : detalles) {
            detalleTableModel.addRow(new Object[] { detalle.getIdDetalle(), detalle.getTipo(), detalle.getIdTipo() });
        }
    }
}
