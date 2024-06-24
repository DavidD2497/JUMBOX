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
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PantallaEliminarEmpleado extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private int selectedRow = -1;
    private JTextField filtroNombre;
    private JComboBox<String> filtroTipo;

    public PantallaEliminarEmpleado(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 812, 476);

        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ELIMINAR EMPLEADO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        lblNewLabel.setBounds(0, 0, 796, 68);
        contentPane.add(lblNewLabel);

        JLabel lblFiltroNombre = new JLabel("Filtrar por Nombre:");
        lblFiltroNombre.setFont(new Font("Consolas", Font.BOLD, 16));
        lblFiltroNombre.setBounds(51, 50, 184, 25);
        contentPane.add(lblFiltroNombre);

        filtroNombre = new JTextField();
        filtroNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
        filtroNombre.setBounds(51, 79, 150, 25);
        contentPane.add(filtroNombre);
        
        filtroNombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                aplicarFiltros();
            }
        });

        JLabel lblFiltroTipo = new JLabel("Filtrar por Tipo:");
        lblFiltroTipo.setFont(new Font("Consolas", Font.BOLD, 16));
        lblFiltroTipo.setBounds(584, 50, 198, 25);
        contentPane.add(lblFiltroTipo);

        filtroTipo = new JComboBox<>();
        filtroTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        filtroTipo.setBounds(584, 79, 150, 25);
        contentPane.add(filtroTipo);
        
        cargarTipos();
        
        filtroTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });

        JLabel lblError = new JLabel("");
        lblError.setFont(new Font("Consolas", Font.BOLD, 16));
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setBounds(0, 352, 796, 14);
        lblError.setVisible(false);
        contentPane.add(lblError);

        JLabel lblAprobado = new JLabel("");
        lblAprobado.setHorizontalAlignment(SwingConstants.CENTER);
        lblAprobado.setForeground(Color.GREEN);
        lblAprobado.setFont(new Font("Consolas", Font.BOLD, 16));
        lblAprobado.setBounds(0, 352, 796, 14);
        lblAprobado.setVisible(false);
        contentPane.add(lblAprobado);

        JLabel lblSeleccionado = new JLabel("Debes seleccionar un usuario para poder eliminarlo.");
        lblSeleccionado.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccionado.setFont(new Font("Consolas", Font.BOLD, 19));
        lblSeleccionado.setBounds(0, 192, 796, 158);
        contentPane.add(lblSeleccionado);

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
        scrollPane.setBounds(51, 119, 683, 113);
        contentPane.add(scrollPane);

        cargarEmpleados();

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedRow = table.getSelectedRow();
                String email = (String) table.getValueAt(selectedRow, 1);
                lblSeleccionado.setText("Empleado seleccionado: " + email);
            } else {
                lblSeleccionado.setText("Debes seleccionar un usuario para poder eliminarlo.");
                selectedRow = -1;
            }
        });

        JButton btnEliminarEmpleado = new JButton("Eliminar");
        btnEliminarEmpleado.setFont(new Font("Consolas", Font.BOLD, 15));
        btnEliminarEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    String emailActual = (String) table.getValueAt(selectedRow, 1);
                    String respuesta = Empleado.borrarEmpleado(emailActual);
                    if (respuesta.equals("Usuario eliminado exitosamente.")) {
                        lblAprobado.setText(respuesta);
                        lblAprobado.setVisible(true);
                        lblError.setVisible(false);
                        selectedRow = -1;
                        cargarEmpleados();
                    } else {
                        lblError.setText(respuesta);
                        lblError.setVisible(true);
                        lblAprobado.setVisible(false);
                    }
                } else {
                    lblError.setText("Por favor, seleccione un empleado.");
                    lblError.setVisible(true);
                    lblAprobado.setVisible(false);
                }
            }
        });
        btnEliminarEmpleado.setBounds(260, 383, 110, 29);
        contentPane.add(btnEliminarEmpleado);

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
    
    private void cargarTipos() {
        filtroTipo.addItem("--Todos--");
        filtroTipo.addItem("AdminSucursal");
        filtroTipo.addItem("Cajero");
        filtroTipo.addItem("AdminDeposito");
    }

    private void aplicarFiltros() {
        String nombreFiltro = filtroNombre.getText().trim();
        String tipoFiltro = (String) filtroTipo.getSelectedItem();

        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        List<Empleado> empleados = empleadoControlador.getAllUsers();

        List<Empleado> empleadosFiltrados = empleados.stream()
                .filter(e -> e.getNombre().toLowerCase().contains(nombreFiltro.toLowerCase()))
                .filter(e -> tipoFiltro.equals("--Todos--") || empleadoControlador.getUserTypeByEmail(e.getEmail()).equals(tipoFiltro))
                .collect(Collectors.toList());

        actualizarTabla(empleadosFiltrados);
    }

    private void actualizarTabla(List<Empleado> empleados) {
        tableModel.setRowCount(0);
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
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

