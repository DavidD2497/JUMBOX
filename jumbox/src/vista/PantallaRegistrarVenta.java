package vista;

import modelos.Cajero;
import modelos.DetalleVenta;
import modelos.InventarioSucursal;
import modelos.Producto;
import controladores.InventarioSucursalControlador;
import controladores.ProductoControlador;
import controladores.VentaControlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PantallaRegistrarVenta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpCantidad;
    private JTable table;
    private DefaultTableModel tableModel;
    private DefaultTableModel detalleTableModel;
    private int selectedRow = -1;
    private List<DetalleVenta> detalles = new ArrayList<>();
    private JLabel lblMontoTotal;
    private JLabel lblError;
    private JLabel lblAprobado;
    private Timer mensajeTimer;
    private JComboBox<String> comboBoxSucursal;
    private JTextField filtroNombre;
    private JComboBox<String> filtroCategoria;
    private JButton btnMas;
    private JButton btnEliminarDetalle;

    public PantallaRegistrarVenta(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 812, 700);

        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblFiltroNombre = new JLabel("Filtrar por Nombre:");
        lblFiltroNombre.setFont(new Font("Consolas", Font.BOLD, 16));
        lblFiltroNombre.setBounds(51, 74, 184, 25);
        contentPane.add(lblFiltroNombre);

        filtroNombre = new JTextField();
        filtroNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
        filtroNombre.setBounds(51, 99, 150, 25);
        contentPane.add(filtroNombre);
        
        filtroNombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                aplicarFiltros();
            }
        });

        JLabel lblFiltroCategoria = new JLabel("Filtrar por Categoría:");
        lblFiltroCategoria.setFont(new Font("Consolas", Font.BOLD, 16));
        lblFiltroCategoria.setBounds(536, 74, 198, 25);
        contentPane.add(lblFiltroCategoria);

        filtroCategoria = new JComboBox<>();
        filtroCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
        filtroCategoria.setBounds(536, 99, 150, 25);
        contentPane.add(filtroCategoria);
        
        cargarCategorias();
        
        filtroCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });
        
        
        mensajeTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblError.setVisible(false);
                lblAprobado.setVisible(false);
                mensajeTimer.stop(); 
            }
        });
        mensajeTimer.setRepeats(false);
        
 

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

        JLabel lblContraseña = new JLabel("SUCURSAL");
        lblContraseña.setVerticalAlignment(SwingConstants.BOTTOM);
        lblContraseña.setHorizontalAlignment(SwingConstants.LEFT);
        lblContraseña.setFont(new Font("Consolas", Font.BOLD, 20));
        lblContraseña.setBounds(505, 319, 175, 29);
        contentPane.add(lblContraseña);
        
        btnMas = new JButton("Agregar");
        btnMas.setFont(new Font("Consolas", Font.BOLD, 15));
        btnMas.setBounds(326, 309, 98, 39);
        btnMas.setEnabled(false);
        contentPane.add(btnMas);

        inpCantidad = new JTextField();
        inpCantidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpCantidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpCantidad.setBounds(51, 310, 174, 31);
        inpCantidad.setText("0");
        contentPane.add(inpCantidad);

        String[] tiposEmpleado = {"--Seleccione un tipo de pago--", "Efectivo", "Debito", "Credito"};
        JComboBox<String> comboBoxTipo = new JComboBox<>(tiposEmpleado);
        comboBoxTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxTipo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        comboBoxTipo.setBounds(506, 443, 229, 29);
        contentPane.add(comboBoxTipo);

        comboBoxSucursal = new JComboBox<>();
        comboBoxSucursal.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxSucursal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        comboBoxSucursal.setBounds(505, 363, 230, 31);
        contentPane.add(comboBoxSucursal);
        
        cargarSucursales();

        lblError = new JLabel("");
        lblError.setFont(new Font("Consolas", Font.BOLD, 16));
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setBounds(0, 596, 796, 14);
        lblError.setVisible(false);
        contentPane.add(lblError);

        lblAprobado = new JLabel("");
        lblAprobado.setHorizontalAlignment(SwingConstants.CENTER);
        lblAprobado.setForeground(Color.GREEN);
        lblAprobado.setFont(new Font("Consolas", Font.BOLD, 16));
        lblAprobado.setBounds(0, 596, 796, 14);
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
        scrollPane.setBounds(51, 145, 683, 95);
        contentPane.add(scrollPane);

        cargarProducto();

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedRow = table.getSelectedRow();
            } else {
                selectedRow = -1;
            }
        });

        detalleTableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Producto", "Nombre", "Cantidad", "Monto"}
        );

        JTable detalleTable = new JTable(detalleTableModel);
        JScrollPane detalleScrollPane = new JScrollPane(detalleTable);
        detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        detalleScrollPane.setBounds(52, 399, 372, 150);
        contentPane.add(detalleScrollPane);

        lblMontoTotal = new JLabel("Monto Total: $0.00");
        lblMontoTotal.setFont(new Font("Consolas", Font.BOLD, 20));
        lblMontoTotal.setBounds(52, 560, 372, 29);
        contentPane.add(lblMontoTotal);

        btnMas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    try {
                        VentaControlador ventaControlador = new VentaControlador();
                        int cantidad = Integer.parseInt(inpCantidad.getText());
                        int idVenta = ventaControlador.obtenerUltimoIdVenta() + 1;
                        Object valueAt = table.getValueAt(selectedRow, 0);
                        int idProducto;
                        if (valueAt instanceof Integer) {
                            idProducto = (Integer) valueAt;
                        } else if (valueAt instanceof String) {
                            idProducto = Integer.parseInt((String) valueAt);
                        } else {
                            throw new NumberFormatException("Tipo de datos inesperado en la celda seleccionada.");
                        }

                        ProductoControlador productoControlador = new ProductoControlador();
                        double precioProducto = productoControlador.getPrecioProductoById(idProducto);

                        DetalleVenta detalleExistente = detalles.stream()
                                .filter(detalle -> detalle.getIdProducto() == idProducto)
                                .findFirst()
                                .orElse(null);

                        if (detalleExistente != null) {
                            detalleExistente.setCantidad(detalleExistente.getCantidad() + cantidad);
                        } else {
                            DetalleVenta nuevoDetalle = new DetalleVenta(idProducto, idVenta, precioProducto, cantidad);
                            detalles.add(nuevoDetalle);
                        }
                        inpCantidad.setText("0");
                        btnMas.setEnabled(false);
                        btnMas.setBackground(UIManager.getColor("Button.background"));
                        actualizarTablaDetalles();
                        actualizarMontoTotal();
                        mostrarMensajeExito("Producto agregado correctamente.");
                    } catch (NumberFormatException ex) {
                        mostrarMensajeError("Por favor, ingrese valores numéricos válidos.");
                    }
                } else {
                    mostrarMensajeError("Por favor, seleccione un producto.");
                }
            }
        });
        
        inpCantidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                checkInputs(); 
            }
        });

        btnEliminarDetalle = new JButton("Borrar");
        btnEliminarDetalle.setFont(new Font("Consolas", Font.BOLD, 15));
        btnEliminarDetalle.setBounds(431, 511, 86, 39);
        contentPane.add(btnEliminarDetalle);
        btnEliminarDetalle.setEnabled(false);

        btnEliminarDetalle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int selectedDetalleIndex = detalleTable.getSelectedRow();
                if (selectedDetalleIndex != -1) {
                    detalles.remove(selectedDetalleIndex);
                    detalleTableModel.removeRow(selectedDetalleIndex);
                    actualizarMontoTotal();
                    mostrarMensajeExito("Detalle de venta eliminado correctamente.");
                } else {
                    mostrarMensajeError("Por favor, seleccione un detalle de venta.");
                }
            }
        });
        
        detalleTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && detalleTable.getSelectedRow() != -1) {
                btnEliminarDetalle.setEnabled(true);
                btnEliminarDetalle.setBackground(Color.RED);
            } else {
                btnEliminarDetalle.setEnabled(false);
                btnEliminarDetalle.setBackground(UIManager.getColor("Button.background"));
            }
        });

        JButton btnRegistrarSalida = new JButton("Registrar");
        btnRegistrarSalida.setFont(new Font("Consolas", Font.BOLD, 15));
        btnRegistrarSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	String selectedSucursal = (String) comboBoxSucursal.getSelectedItem();
                	
                   
                    int idSucursal = Integer.parseInt(selectedSucursal.split(" - ")[0]);
                    String tipoPago = (String) comboBoxTipo.getSelectedItem();
                    String respuesta = Cajero.registrarVenta(idSucursal, detalles, tipoPago);
                    if (respuesta.equals("Venta registrada con éxito")) {
                        mostrarMensajeExito(respuesta);
                        detalles.clear();
                        detalleTableModel.setRowCount(0);
                        actualizarMontoTotal();
                        cargarProducto();
                        comboBoxSucursal.setSelectedIndex(0);
                        comboBoxTipo.setSelectedIndex(0);
                    } else {
                        mostrarMensajeError(respuesta);
                    }
                } catch (NumberFormatException ex) {
                    mostrarMensajeError("Por favor, ingrese valores numéricos válidos para el ID de sucursal.");
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

        JLabel lblNombre_1 = new JLabel("Ingresar Cantidad");
        lblNombre_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNombre_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNombre_1.setFont(new Font("Consolas", Font.BOLD, 20));
        lblNombre_1.setBounds(52, 280, 256, 29);
        contentPane.add(lblNombre_1);
        
        JButton btnSumar = new JButton("+");
        btnSumar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnSumar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int valorActual = Integer.parseInt(inpCantidad.getText());
                    int nuevoValor = valorActual + 1;                  
                    inpCantidad.setText(String.valueOf(nuevoValor));
              
                    checkInputs();
                    
                } catch (NumberFormatException ex) {
                	mostrarMensajeError("Solo se pueden sumar enteros");
                    inpCantidad.setText("0");
                }
            }
        });
        btnSumar.setBounds(51, 343, 86, 14);
        contentPane.add(btnSumar);
        
        JButton btnRestar = new JButton("-");
        btnRestar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnRestar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int valorActual = Integer.parseInt(inpCantidad.getText());
                    int nuevoValor = valorActual - 1;                  
                    inpCantidad.setText(String.valueOf(nuevoValor));
              
                    checkInputs();
                    
                } catch (NumberFormatException ex) {
                	mostrarMensajeError("Solo se pueden restar enteros");
                    inpCantidad.setText("0");
                }
            }
        });
        btnRestar.setBounds(139, 343, 86, 14);
        contentPane.add(btnRestar);

        actualizarMontoTotal();
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

    private void actualizarTablaDetalles() {
        detalleTableModel.setRowCount(0);
        for (DetalleVenta detalle : detalles) {
            detalleTableModel.addRow(new Object[]{
                    detalle.getIdProducto(),
                    detalle.getIdVenta(),
                    detalle.getCantidad(),
                    detalle.getMonto() * detalle.getCantidad()
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

    private void mostrarMensajeError(String mensaje) {
        lblError.setText(mensaje);
        lblError.setVisible(true);
        lblAprobado.setVisible(false);
        mensajeTimer.restart();
    }

    private void mostrarMensajeExito(String mensaje) {
        lblAprobado.setText(mensaje);
        lblAprobado.setVisible(true);
        lblError.setVisible(false);
        mensajeTimer.restart();
    }
    
    private void cargarSucursales() {
        comboBoxSucursal.addItem("--Seleccione una sucursal--");
        
        InventarioSucursalControlador inventarioControlador = new InventarioSucursalControlador();
        List<InventarioSucursal> inventarios = inventarioControlador.getAllInventarioSucursal();
        for (InventarioSucursal inventario : inventarios) {
            comboBoxSucursal.addItem(inventario.getIdInventario() + " - " + inventario.getUbicacion());
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
    
    private void checkInputs() {
    	
    	
    	
        if (selectedRow != -1 && esNumeroValido(inpCantidad.getText().trim())) {
            btnMas.setEnabled(true);
            btnMas.setBackground(Color.GREEN);
        } else {
            btnMas.setEnabled(false);
            btnMas.setBackground(UIManager.getColor("Button.background"));
        }
    }
    
    private boolean esNumeroValido(String str) {
        try {
            int num = Integer.parseInt(str);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
