package vista;

import modelos.Cajero;
import modelos.InventarioSucursal;
import modelos.Producto;
import controladores.InventarioSucursalControlador;
import controladores.ProductoControlador;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import java.util.List;
import java.util.stream.Collectors;

public class PantallaRegistrarSalidaSucursal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpCantidad;
    private JComboBox<String> comboBoxSucursal;
    private JTable table;
    private DefaultTableModel tableModel;
    private int selectedRow = -1;
    private JTextField filtroNombre;
    private JComboBox<String> filtroCategoria;

    public PantallaRegistrarSalidaSucursal(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 812, 476);

        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
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

        JLabel lblFiltroCategoria = new JLabel("Filtrar por Categoría:");
        lblFiltroCategoria.setFont(new Font("Consolas", Font.BOLD, 16));
        lblFiltroCategoria.setBounds(536, 50, 198, 25);
        contentPane.add(lblFiltroCategoria);

        filtroCategoria = new JComboBox<>();
        filtroCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
        filtroCategoria.setBounds(536, 79, 150, 25);
        contentPane.add(filtroCategoria);
        
        cargarCategorias();
        
        filtroCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });

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

        JLabel lblContraseña = new JLabel("SUCURSAL");
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

        comboBoxSucursal = new JComboBox<>();
        comboBoxSucursal.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxSucursal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        comboBoxSucursal.setBounds(433, 310, 301, 31);
        contentPane.add(comboBoxSucursal);
        
        cargarSucursales();

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
        lblSeleccion.setBounds(0, 230, 796, 29);
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
        scrollPane.setBounds(51, 119, 683, 90);
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
                    	String selectedSucursal = (String) comboBoxSucursal.getSelectedItem();                   	                        
                        int idSucursal = Integer.parseInt(selectedSucursal.split(" - ")[0]);
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
    
    private void cargarCategorias() {
        ProductoControlador productoControlador = new ProductoControlador();
        List<String> categorias = productoControlador.getAllCategorias();
        filtroCategoria.addItem("--Todas--");
        for (String categoria : categorias) {
            filtroCategoria.addItem(categoria);
        }
    }

    private void aplicarFiltros() {
        String nombreFiltro = filtroNombre.getText().trim();
        String categoriaFiltro = (String) filtroCategoria.getSelectedItem();

        ProductoControlador productoControlador = new ProductoControlador();
        List<Producto> productos = productoControlador.getAllProductos();

        List<Producto> productosFiltrados = productos.stream()
                .filter(p -> p.getNombreProducto().toLowerCase().contains(nombreFiltro.toLowerCase()))
                .filter(p -> categoriaFiltro.equals("--Todas--") || p.getCategoria().equals(categoriaFiltro))
                .collect(Collectors.toList());

        actualizarTabla(productosFiltrados);
    }


    private void actualizarTabla(List<Producto> productos) {
        tableModel.setRowCount(0);
        for (Producto producto : productos) {
            tableModel.addRow(new Object[]{
                    producto.getIdProducto(),
                    producto.getNombreProducto(),
                    producto.getCategoria()
            });
        }
    }
    
    private void cargarSucursales() {
        comboBoxSucursal.addItem("--Seleccione una sucursal--");
        
        InventarioSucursalControlador inventarioControlador = new InventarioSucursalControlador();
        List<InventarioSucursal> inventarios = inventarioControlador.getAllInventarioSucursal();
        for (InventarioSucursal inventario : inventarios) {
            comboBoxSucursal.addItem(inventario.getIdInventario() + " - " + inventario.getUbicacion());
        }
    }
}


