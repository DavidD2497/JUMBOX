package vista;

import modelos.Empleado;
import controladores.EmpleadoControlador;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.util.List;

public class PantallaEditarEmpleado extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpMail;
    private JTextField inpNombre;
    private JTextField inpContraseña;
    private JTable table;
    private DefaultTableModel tableModel;

    public PantallaEditarEmpleado(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 812, 476);

        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("EDITAR DATOS EMPLEADO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        lblNewLabel.setBounds(0, 0, 796, 68);
        contentPane.add(lblNewLabel);

        JLabel lblNombre = new JLabel("NOMBRE");
        lblNombre.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setFont(new Font("Consolas", Font.BOLD, 20));
        lblNombre.setBounds(69, 206, 116, 29);
        contentPane.add(lblNombre);

        inpMail = new JTextField();
        inpMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpMail.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpMail.setBounds(411, 232, 301, 31);
        contentPane.add(inpMail);

        JLabel lblContraseña = new JLabel("CONTRASEÑA");
        lblContraseña.setVerticalAlignment(SwingConstants.BOTTOM);
        lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
        lblContraseña.setFont(new Font("Consolas", Font.BOLD, 20));
        lblContraseña.setBounds(69, 282, 116, 29);
        contentPane.add(lblContraseña);

        inpNombre = new JTextField();
        inpNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpNombre.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpNombre.setBounds(69, 232, 301, 31);
        contentPane.add(inpNombre);

        JLabel lblMail = new JLabel("MAIL");
        lblMail.setVerticalAlignment(SwingConstants.BOTTOM);
        lblMail.setHorizontalAlignment(SwingConstants.CENTER);
        lblMail.setFont(new Font("Consolas", Font.BOLD, 20));
        lblMail.setBounds(411, 206, 54, 29);
        contentPane.add(lblMail);

        inpContraseña = new JTextField();
        inpContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpContraseña.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpContraseña.setBounds(69, 310, 301, 31);
        contentPane.add(inpContraseña);

        JLabel lblTipo = new JLabel("TIPO");
        lblTipo.setVerticalAlignment(SwingConstants.BOTTOM);
        lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipo.setFont(new Font("Consolas", Font.BOLD, 20));
        lblTipo.setBounds(411, 282, 54, 29);
        contentPane.add(lblTipo);

        String[] tiposEmpleado = {"", "AdminSucursal", "Cajero", "AdminDeposito"};
        JComboBox<String> comboBoxTipo = new JComboBox<>(tiposEmpleado);
        comboBoxTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxTipo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        comboBoxTipo.setBounds(411, 311, 301, 29);
        contentPane.add(comboBoxTipo);

        JButton btnEditarEmpleado = new JButton("Editar");
        btnEditarEmpleado.setFont(new Font("Consolas", Font.BOLD, 15));
        btnEditarEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = inpNombre.getText();
                String email = inpMail.getText();
                String contraseña = inpContraseña.getText();
                String tipo = (String) comboBoxTipo.getSelectedItem();
            }
        });
        btnEditarEmpleado.setBounds(260, 383, 110, 29);
        contentPane.add(btnEditarEmpleado);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 15));
        btnVolver.setBounds(411, 383, 110, 29);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaHome pantallaHome = new PantallaHome(mail);
                pantallaHome.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnVolver);


        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Nombre", "Email", "Tipo", "Contraseña"}
        );

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setBounds(51, 79, 683, 112);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        scrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        scrollPane.setBounds(51, 79, 683, 97);
        contentPane.add(scrollPane);

        cargarEmpleados();


        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                inpNombre.setText((String) table.getValueAt(selectedRow, 0));
                inpMail.setText((String) table.getValueAt(selectedRow, 1));
                inpContraseña.setText((String) table.getValueAt(selectedRow, 3));
                comboBoxTipo.setSelectedItem((String) table.getValueAt(selectedRow, 2));
                
                JLabel lblSeleccion = new JLabel("Seleccione el Empleado");
                lblSeleccion.setVerticalAlignment(SwingConstants.BOTTOM);
                lblSeleccion.setHorizontalAlignment(SwingConstants.LEFT);
                lblSeleccion.setFont(new Font("Consolas", Font.BOLD, 18));
                lblSeleccion.setBounds(52, 49, 385, 29);
                contentPane.add(lblSeleccion);
            }
        });
    }

    private void cargarEmpleados() {
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        List<Empleado> empleados = empleadoControlador.getAllUsers();
        tableModel.setRowCount(0);
        for (Empleado empleado : empleados) {
            tableModel.addRow(new Object[]{
                empleado.getNombre(),
                empleado.getEmail(),
                empleadoControlador.getUserTypeByEmail(empleado.getEmail()),
                empleado.getContraseña()
            });
        }
    }
}

