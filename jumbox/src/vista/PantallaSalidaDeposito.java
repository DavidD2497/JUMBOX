package vista;

import modelos.*;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import controladores.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;

public class PantallaSalidaDeposito extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpCANTIDAD;
    private DefaultTableModel tableModel;
    private int selectedRow = -1;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

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
    public PantallaSalidaDeposito(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 894, 563);
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);

        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Registrar Salida de Productos");
        lblNewLabel_2.setBounds(220, 11, 496, 40);
        lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
        contentPane.add(lblNewLabel_2);

        JLabel lblSeleccionado = new JLabel("");
        lblSeleccionado.setVerticalAlignment(SwingConstants.BOTTOM);
        lblSeleccionado.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccionado.setFont(new Font("Consolas", Font.BOLD, 18));
        lblSeleccionado.setBounds(254, 274, 385, 29);
        contentPane.add(lblSeleccionado);

        JLabel lblError = new JLabel("");
        lblError.setBackground(new Color(255, 0, 0));
        lblError.setVerticalAlignment(SwingConstants.BOTTOM);
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setFont(new Font("Consolas", Font.BOLD, 18));
        lblError.setBounds(52, 436, 573, 29);
        contentPane.add(lblError);

        JLabel lblAprobado = new JLabel("");
        lblAprobado.setVerticalAlignment(SwingConstants.BOTTOM);
        lblAprobado.setHorizontalAlignment(SwingConstants.CENTER);
        lblAprobado.setFont(new Font("Consolas", Font.BOLD, 18));
        lblAprobado.setBackground(new Color(0, 128, 0));
        lblAprobado.setBounds(52, 411, 573, 29);
        contentPane.add(lblAprobado);

        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id Producto", "Nombre", "Cantidad"}
        );

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));

        // TableRowSorter
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        scrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        scrollPane.setBounds(52, 119, 800, 144);
        contentPane.add(scrollPane);

        cargarProductos();

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedRow = table.getSelectedRow();
                String producto = (String) table.getValueAt(selectedRow, 1);
                lblSeleccionado.setText("Producto seleccionado: " + producto);
            } else {
                lblSeleccionado.setText("Debes seleccionar un producto para poder registrarlo.");
                selectedRow = -1;
            }
        });

        JLabel lblCANTIDAD = new JLabel("Cantidad:");
        lblCANTIDAD.setBounds(336, 343, 200, 40);
        lblCANTIDAD.setFont(new Font("Consolas", Font.BOLD, 20));
        lblCANTIDAD.setHorizontalAlignment(SwingConstants.CENTER);
        lblCANTIDAD.setHorizontalTextPosition(SwingConstants.CENTER);
        contentPane.add(lblCANTIDAD);

        inpCANTIDAD = new JTextField();
        inpCANTIDAD.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpCANTIDAD.setBounds(312, 369, 261, 31);
        inpCANTIDAD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        contentPane.add(inpCANTIDAD);

        JButton btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnRegistrar.setBounds(330, 462, 99, 31);

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    try {
                        int cantidadSalida = Integer.parseInt(inpCANTIDAD.getText());
                        Object valueAt = table.getValueAt(selectedRow, 0);

                        int idProducto;
                        if (valueAt instanceof Integer) {
                            idProducto = (Integer) valueAt;
                        } else if (valueAt instanceof String) {
                            idProducto = Integer.parseInt((String) valueAt);
                        } else {
                            throw new NumberFormatException("Tipo de datos inesperado en la celda seleccionada.");
                        }

                        AdminDeposito adminDeposito = new AdminDeposito(mail, mail, mail);
                        String respuesta = adminDeposito.registrarSalidaDepositoGeneral(idProducto, cantidadSalida);
                        if (respuesta.equals("registrada con éxito.")) {
                            lblAprobado.setText(respuesta);
                            lblAprobado.setVisible(true);
                            lblError.setVisible(false);
                            selectedRow = -1;
                            cargarProductos(); // Actualiza la tabla después del registro exitoso
                        } else {
                            lblError.setText(respuesta);
                            lblError.setVisible(true);
                            lblAprobado.setVisible(false);
                        }
                    } catch (NumberFormatException ex) {
                        lblError.setText("Por favor, ingrese valores numéricos válidos.");
                        lblError.setVisible(true);
                        lblAprobado.setVisible(false);
                    }
                } else {
                    lblError.setText("Por favor, seleccione un producto.");
                    lblError.setVisible(true);
                    lblAprobado.setVisible(false);
                }
            }
        });
        contentPane.add(btnRegistrar);

        // Campo de búsqueda y botón de filtrado
        JTextField textFieldBuscar = new JTextField();
        textFieldBuscar.setBounds(52, 60, 200, 25);
        contentPane.add(textFieldBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(262, 60, 80, 25);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textoFiltro = textFieldBuscar.getText();
                RowFilter<DefaultTableModel, Object> rf = null;
                try {
                    rf = RowFilter.regexFilter("(?i)" + textoFiltro, 0, 1, 2); // Filtra insensitivo a mayúsculas/minúsculas en Id Producto, Nombre, Cantidad
                } catch (java.util.regex.PatternSyntaxException ex) {
                    return;
                }
                sorter.setRowFilter(rf);
            }
        });
        contentPane.add(btnBuscar);

        // Filtro de ordenamiento
        JComboBox<String> comboBoxOrden = new JComboBox<>();
        comboBoxOrden.addItem("Id Producto");
        comboBoxOrden.addItem("Nombre");
        comboBoxOrden.addItem("Cantidad");
        comboBoxOrden.setBounds(400, 60, 150, 25);
        comboBoxOrden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBoxOrden.getSelectedItem();
                switch (seleccion) {
                    case "Id Producto":
                        sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
                        break;
                    case "Nombre":
                        sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
                        break;
                    case "Cantidad":
                        sorter.setSortKeys(List.of(new RowSorter.SortKey(2, SortOrder.ASCENDING)));
                        break;
                    default:
                        break;
                }
            }
        });
        contentPane.add(comboBoxOrden);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
        btnVolver.setBounds(570, 60, 99, 25);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaAdminDeposito admindeposito = new PantallaAdminDeposito(mail);
                admindeposito.setVisible(true);
                dispose();
            }
        });
    }

    private void cargarProductos() {
        ProductoControlador productoControlador = new ProductoControlador();
        DetalleDepositoControlador detalleDepositoControlador = new DetalleDepositoControlador();
        List<Producto> productos = productoControlador.getAllProductos();
        tableModel.setRowCount(0);
        for (Producto producto : productos) {
            int idProducto = producto.getIdProducto();
            int cantidadActual = detalleDepositoControlador.getDetalleDepositoById(idProducto).getCantidad();
            tableModel.addRow(new Object[]{
                    idProducto,
                    producto.getNombreProducto(),
                    cantidadActual
            });
        }
    }
}
