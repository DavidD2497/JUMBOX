package vista;

import modelos.Cajero;
import modelos.DetalleVenta;
import modelos.Producto;
import controladores.ProductoControlador;
import controladores.DetalleVentaControlador;
import controladores.DetalleInventarioControlador;
import controladores.VentaControlador;

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
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PantallaRegistrarVenta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpCantidad;
    private JTextField inpIDSuc;
    private JTable table;
    private DefaultTableModel tableModel;
    private DefaultTableModel detalleTableModel;
    private int selectedRow = -1;
    private List<DetalleVenta> detalles = new ArrayList<>();
    private JLabel lblMontoTotal;

    public PantallaRegistrarVenta(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 812, 700); // Ajustado el tamaño para acomodar más componentes

        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("REGISTRAR VENTAS");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        lblNewLabel.setBounds(0, 0, 796, 68);
        contentPane.add(lblNewLabel);

        JLabel lblTipo = new JLabel("TIPO DE PAGO");
        lblTipo.setVerticalAlignment(SwingConstants.BOTTOM);
        lblTipo.setHorizontalAlignment(SwingConstants.LEFT);
        lblTipo.setFont(new Font("Consolas", Font.BOLD, 20));
        lblTipo.setBounds(505, 403, 230, 29);
        contentPane.add(lblTipo);

        JLabel lblContraseña = new JLabel("ID SUCURSAL");
        lblContraseña.setVerticalAlignment(SwingConstants.BOTTOM);
        lblContraseña.setHorizontalAlignment(SwingConstants.LEFT);
        lblContraseña.setFont(new Font("Consolas", Font.BOLD, 20));
        lblContraseña.setBounds(52, 39, 175, 29);
        contentPane.add(lblContraseña);

        inpCantidad = new JTextField();
        inpCantidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpCantidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpCantidad.setBounds(52, 310, 230, 31); // Ajustado para dejar espacio para el botón "+"
        contentPane.add(inpCantidad);

        String[] tiposEmpleado = {"", "Efectivo", "Debito", "Credito"};
        JComboBox<String> comboBoxTipo = new JComboBox<>(tiposEmpleado);
        comboBoxTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxTipo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        comboBoxTipo.setBounds(506, 443, 229, 29);
        contentPane.add(comboBoxTipo);

        JButton btnMas = new JButton("+");
        btnMas.setFont(new Font("Consolas", Font.BOLD, 15));
        btnMas.setBounds(346, 302, 56, 39); // Tamaño y ubicación del botón "+"
        contentPane.add(btnMas);

        inpIDSuc = new JTextField();
        inpIDSuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpIDSuc.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpIDSuc.setBounds(51, 75, 301, 31);
        contentPane.add(inpIDSuc);

        JLabel lblError = new JLabel("");
        lblError.setFont(new Font("Consolas", Font.BOLD, 16));
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setBounds(0, 582, 796, 14);
        lblError.setVisible(false);
        contentPane.add(lblError);

        JLabel lblAprobado = new JLabel("");
        lblAprobado.setHorizontalAlignment(SwingConstants.CENTER);
        lblAprobado.setForeground(Color.GREEN);
        lblAprobado.setFont(new Font("Consolas", Font.BOLD, 16));
        lblAprobado.setBounds(0, 582, 796, 14);
        lblAprobado.setVisible(false);
        contentPane.add(lblAprobado);

        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID Producto", "Nombre", "Categoría"}
        );

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setBounds(51, 79, 683, 112);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        scrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        scrollPane.setBounds(52, 117, 683, 142);
        contentPane.add(scrollPane);

        cargarProducto();

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedRow = table.getSelectedRow();
            } else {
                selectedRow = -1;
            }
        });

        // Tabla para mostrar los detalles
        detalleTableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID Producto", "Nombre", "Cantidad", "Monto"}
        );

        JTable detalleTable = new JTable(detalleTableModel);
        JScrollPane detalleScrollPane = new JScrollPane(detalleTable);
        detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        detalleScrollPane.setBounds(52, 399, 372, 150); // Posición de la tabla de detalles
        contentPane.add(detalleScrollPane);

        // Label para mostrar el monto total
        lblMontoTotal = new JLabel("Monto Total: $0.00");
        lblMontoTotal.setFont(new Font("Consolas", Font.BOLD, 20));
        lblMontoTotal.setBounds(52, 560, 372, 29); // Posición del label
        contentPane.add(lblMontoTotal);

        // Acción del botón "+"
        btnMas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    try {
                        int cantidad = Integer.parseInt(inpCantidad.getText());
                        Object valueAt = table.getValueAt(selectedRow, 0);
                        String nombreProducto = (String) table.getValueAt(selectedRow, 1);
                        double precioProducto = 10.0; // Supongamos un precio fijo por simplicidad

                        int idProducto;
                        if (valueAt instanceof Integer) {
                            idProducto = (Integer) valueAt;
                        } else if (valueAt instanceof String) {
                            idProducto = Integer.parseInt((String) valueAt);
                        } else {
                            throw new NumberFormatException("Tipo de datos inesperado en la celda seleccionada.");
                        }

                        // Añadir el detalle a la lista y actualizar la tabla de detalles
                        DetalleVenta detalle = new DetalleVenta(idProducto, nombreProducto, cantidad, precioProducto);
                        detalles.add(detalle);
                        detalleTableModel.addRow(new Object[]{idProducto, nombreProducto, cantidad, cantidad * precioProducto});
                        inpCantidad.setText(""); // Limpiar el campo de cantidad
                        actualizarMontoTotal();
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

        JButton btnRegistrarSalida = new JButton("Registrar");
        btnRegistrarSalida.setFont(new Font("Consolas", Font.BOLD, 15));
        btnRegistrarSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idSucursal = Integer.parseInt(inpIDSuc.getText());
                    String tipoPago = (String) comboBoxTipo.getSelectedItem();
                    String respuesta = Cajero.registrarVenta(idSucursal, detalles, tipoPago);
                    if (respuesta.equals("Venta registrada con éxito")) {
                        lblAprobado.setText(respuesta);
                        lblAprobado.setVisible(true);
                        lblError.setVisible(false);
                        detalles.clear(); // Limpiar la lista de detalles
                        detalleTableModel.setRowCount(0); // Limpiar la tabla de detalles
                        actualizarMontoTotal(); // Reiniciar el monto total
                        cargarProducto();
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
            }
        });
        btnRegistrarSalida.setBounds(264, 621, 110, 29);
        contentPane.add(btnRegistrarSalida);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 15));
        btnVolver.setBounds(395, 621, 110, 29);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaCajero pantallaCajero = new PantallaCajero(mail);
                pantallaCajero.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnVolver);

        JLabel lblInformativo = new JLabel("PRODUCTOS AGREGADOS A LA VENTA");
        lblInformativo.setVerticalAlignment(SwingConstants.BOTTOM);
        lblInformativo.setHorizontalAlignment(SwingConstants.LEFT);
        lblInformativo.setFont(new Font("Consolas", Font.BOLD, 17));
        lblInformativo.setBounds(52, 363, 350, 29);
        contentPane.add(lblInformativo);

        JLabel lblNombre_1 = new JLabel("CANTIDAD DEL PRODUCTO");
        lblNombre_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNombre_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNombre_1.setFont(new Font("Consolas", Font.BOLD, 20));
        lblNombre_1.setBounds(52, 280, 256, 29);
        contentPane.add(lblNombre_1);
    }

    private void cargarProducto() {
        ProductoControlador productoControlador = new ProductoControlador();
        List<Producto> productos = productoControlador.getAllProductos();
        tableModel.setRowCount(0);
        for (Producto producto : productos) {
            tableModel.addRow(new Object[]{
                producto.getIdProducto(),
                producto.getNombreProducto(),
                producto.getCategoria()
            });
        }
    }

    private void actualizarMontoTotal() {
        double montoTotal = 0.0;
        for (DetalleVenta detalle : detalles) {
            montoTotal += detalle.getMonto() * detalle.getCantidad();
        }
        lblMontoTotal.setText("Monto Total: $" + String.format("%.2f", montoTotal));
    }
}


