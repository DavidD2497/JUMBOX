package vista;

import modelos.Cajero;
import modelos.DetalleVenta;
import modelos.Producto;
import controladores.ProductoControlador;
import controladores.VentaControlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    private JLabel lblError;
    private JLabel lblAprobado;
    private Timer mensajeTimer;

    public PantallaRegistrarVenta(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 812, 700);

        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        mensajeTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando el temporizador se activa, ocultar los mensajes
                lblError.setVisible(false);
                lblAprobado.setVisible(false);
                mensajeTimer.stop(); // Detener el temporizador después de ocultar los mensajes
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

        JLabel lblContraseña = new JLabel("ID SUCURSAL");
        lblContraseña.setVerticalAlignment(SwingConstants.BOTTOM);
        lblContraseña.setHorizontalAlignment(SwingConstants.LEFT);
        lblContraseña.setFont(new Font("Consolas", Font.BOLD, 20));
        lblContraseña.setBounds(52, 39, 175, 29);
        contentPane.add(lblContraseña);

        inpCantidad = new JTextField();
        inpCantidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpCantidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpCantidad.setBounds(52, 310, 230, 31);
        contentPane.add(inpCantidad);

        String[] tiposEmpleado = {"", "Efectivo", "Debito", "Credito"};
        JComboBox<String> comboBoxTipo = new JComboBox<>(tiposEmpleado);
        comboBoxTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxTipo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        comboBoxTipo.setBounds(506, 443, 229, 29);
        contentPane.add(comboBoxTipo);

        JButton btnMas = new JButton("Agregar");
        btnMas.setFont(new Font("Consolas", Font.BOLD, 15));
        btnMas.setBounds(326, 309, 98, 39);
        contentPane.add(btnMas);

        inpIDSuc = new JTextField();
        inpIDSuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        inpIDSuc.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        inpIDSuc.setBounds(51, 75, 301, 31);
        contentPane.add(inpIDSuc);

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

                        actualizarTablaDetalles();
                        inpCantidad.setText("");
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

        JButton btnEliminarDetalle = new JButton("Borrar");
        btnEliminarDetalle.setFont(new Font("Consolas", Font.BOLD, 15));
        btnEliminarDetalle.setBounds(431, 511, 86, 39);
        contentPane.add(btnEliminarDetalle);

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

        JButton btnRegistrarSalida = new JButton("Registrar");
        btnRegistrarSalida.setFont(new Font("Consolas", Font.BOLD, 15));
        btnRegistrarSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idSucursal = Integer.parseInt(inpIDSuc.getText());
                    String tipoPago = (String) comboBoxTipo.getSelectedItem();
                    String respuesta = Cajero.registrarVenta(idSucursal, detalles, tipoPago);
                    if (respuesta.equals("Venta registrada con éxito")) {
                        mostrarMensajeExito(respuesta);
                        detalles.clear();
                        detalleTableModel.setRowCount(0);
                        actualizarMontoTotal();
                        cargarProducto();
                        inpIDSuc.setText("");
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

        JLabel lblNombre_1 = new JLabel("CANTIDAD DEL PRODUCTO");
        lblNombre_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNombre_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNombre_1.setFont(new Font("Consolas", Font.BOLD, 20));
        lblNombre_1.setBounds(52, 280, 256, 29);
        contentPane.add(lblNombre_1);

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
}



