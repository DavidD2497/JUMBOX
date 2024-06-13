package vista;

import modelos.Cajero;
import modelos.Producto;
import controladores.ProductoControlador;

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
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.util.List;

public class PantallaRegistrarSalidaSucursal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpCantidad;
    private JTextField inpIDSuc;
    private JTable table;
    private DefaultTableModel tableModel;
    private int selectedRow = -1;

    public PantallaRegistrarSalidaSucursal(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 812, 476);

        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("REGISTRAR SALIDAS - INVENTARIO SUCURSAL");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        lblNewLabel.setBounds(0, 0, 796, 68);
        contentPane.add(lblNewLabel);

        JLabel lblNombre = new JLabel("CANTIDAD DEL PRODUCTO");
        lblNombre.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
        lblNombre.setFont(new Font("Consolas", Font.BOLD, 20));
        lblNombre.setBounds(69, 270, 256, 29);
        contentPane.add(lblNombre);

        JLabel lblContraseña = new JLabel("ID SUCURSAL");
        lblContraseña.setVerticalAlignment(SwingConstants.BOTTOM);
        lblContraseña.setHorizontalAlignment(SwingConstants.LEFT);
        lblContraseña.setFont(new Font("Consolas", Font.BOLD, 20));
        lblContraseña.setBounds(433, 270, 175, 29);
        contentPane.add(lblContraseña);

        inpCantidad = new JTextField();
        inpCantidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpCantidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpCantidad.setBounds(69, 310, 301, 31);
        contentPane.add(inpCantidad);

        inpIDSuc = new JTextField();
        inpIDSuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpIDSuc.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpIDSuc.setBounds(433, 310, 301, 31);
        contentPane.add(inpIDSuc);

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

        JLabel lblSeleccion = new JLabel("Debes seleccionar un producto para poder registrarlo.");
        lblSeleccion.setVerticalAlignment(SwingConstants.BOTTOM);
        lblSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccion.setFont(new Font("Consolas", Font.BOLD, 16));
        lblSeleccion.setBounds(0, 210, 796, 29);
        contentPane.add(lblSeleccion);

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
        scrollPane.setBounds(51, 91, 683, 90);
        contentPane.add(scrollPane);

        cargarProducto();

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedRow = table.getSelectedRow();
                String producto = (String) table.getValueAt(selectedRow, 1);
                lblSeleccion.setText("Producto seleccionado: " + producto);
            } else {
                lblSeleccion.setText("Debes seleccionar un producto para poder registrarlo.");
                selectedRow = -1;
            }
        });

        JButton btnRegistrarSalida = new JButton("Registrar");
        btnRegistrarSalida.setFont(new Font("Consolas", Font.BOLD, 15));
        btnRegistrarSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    try {
                        int idSucursal = Integer.parseInt(inpIDSuc.getText());
                        int cantidad = Integer.parseInt(inpCantidad.getText());
                        Object valueAt = table.getValueAt(selectedRow, 0);

                        int idProducto;
                        if (valueAt instanceof Integer) {
                            idProducto = (Integer) valueAt;
                        } else if (valueAt instanceof String) {
                            idProducto = Integer.parseInt((String) valueAt);
                        } else {
                            throw new NumberFormatException("Tipo de datos inesperado en la celda seleccionada.");
                        }

                        String respuesta = Cajero.registrarSalidaProductoInventarioSuc(idSucursal, idProducto, cantidad);
                        if (respuesta.equals("Salida registrada correctamente")) {
                            lblAprobado.setText(respuesta);
                            lblAprobado.setVisible(true);
                            lblError.setVisible(false);
                            selectedRow = -1;
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
                } else {
                    lblError.setText("Por favor, seleccione un producto.");
                    lblError.setVisible(true);
                    lblAprobado.setVisible(false);
                }
            }
        });
        btnRegistrarSalida.setBounds(260, 383, 110, 29);
        contentPane.add(btnRegistrarSalida);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 15));
        btnVolver.setBounds(411, 383, 110, 29);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaCajero pantallaCajero = new PantallaCajero(mail);
                pantallaCajero.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnVolver);
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
}


