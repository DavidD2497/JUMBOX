package vista;

import modelos.Empleado;
import modelos.Pedido;
import modelos.DetallePedido;
import controladores.EmpleadoControlador;
import controladores.PedidoControlador;
import controladores.DetallePedidoControlador;
import controladores.ProductoControlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class PantallaMostrarPedido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel pedidoTableModel;
    private DefaultTableModel detalleTableModel;
    private DefaultTableModel editarTableModel;
    private int selectedRow = -1;
    public static JTable pedidoTable;
    private JComboBox<String> comboBoxSucursal;
    private JTable detalleTable;
    private JScrollPane detalleScrollPane;
    private JScrollPane pedidoScrollPane;
    private TableRowSorter<DefaultTableModel> sorterPedido;
    private TableRowSorter<DefaultTableModel> sorterDetalle;

    /**
     * Lanzar la aplicación.
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
     * Crear el frame.
     */
    public PantallaMostrarPedido(String mail) {
        JLabel lblNewLabel_2_1 = new JLabel("Productos Solicitados");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEliminar_1 = new JButton("Eliminar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 894, 563);
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);
        JDialog editarDialog = new JDialog(this, "Editar Pedido", true); // Modal
        editarDialog.setSize(400, 300);
        editarDialog.setLocationRelativeTo(this); // Centrar el JDialog en la ventana principal

        // Reemplazar contentPane por una instancia de ImagePanel con la ruta correcta
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        pedidoTableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Id Pedido", "Fecha de Solicitud", "Estado" });
        cargarPedidos();

        JLabel lblNewLabel_3 = new JLabel("Detalle del Pedido");
        pedidoTable = new JTable(pedidoTableModel);
        pedidoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pedidoTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pedidoTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = pedidoTable.getSelectedRow();
                if (selectedRow != -1) {
                    int idPedido = (int) pedidoTableModel.getValueAt(selectedRow, 0);
                    cargarDetallesPedido(idPedido);
                    btnEliminar.setEnabled(true);
                    lblNewLabel_2_1.setVisible(true);
                }
            }
        });

        pedidoScrollPane = new JScrollPane(pedidoTable);
        pedidoScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        pedidoScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        pedidoScrollPane.setBounds(51, 50, 761, 149);
        contentPane.add(pedidoScrollPane);

        detalleTableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Id Detalle","Id Producto", "Producto Solicitado", "Cantidad Solicitada" });

        detalleTable = new JTable(detalleTableModel);
        detalleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        detalleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));

        detalleScrollPane = new JScrollPane(detalleTable);
        detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        detalleScrollPane.setBounds(51, 297, 761, 132);
        detalleScrollPane.setVisible(true); // Inicialmente visible
        contentPane.add(detalleScrollPane);

        detalleTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = detalleTable.getSelectedRow();
                if (selectedRow != -1) {
                    int idPedido = (int) detalleTableModel.getValueAt(selectedRow, 0);
                    btnEliminar_1.setEnabled(true);
                }
            }
        });
        JScrollPane editarScrollPane = new JScrollPane();
        editarScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        editarScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        JButton btnVolver = new JButton("");
        btnVolver.setBackground(null);

        JButton button = new JButton("Botón Transparente");
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        editarDialog.getContentPane().add(editarScrollPane, BorderLayout.CENTER);
        btnVolver.setIcon(new ImageIcon(PantallaMostrarPedido.class.getResource("/resources/Image0n1.png")));
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
        btnVolver.setBounds(0, 0, 49, 31);
        contentPane.add(btnVolver);

        lblNewLabel_2_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblNewLabel_2_1.setBounds(192, 249, 496, 40);
        lblNewLabel_2_1.setVisible(false);
        contentPane.add(lblNewLabel_2_1);

        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Estás seguro de que deseas eliminar este pedido?",
                        "Confirmación de Eliminación",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmacion == JOptionPane.YES_OPTION) {
                    PedidoControlador pedidoControlador = new PedidoControlador();
                    DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();

                    int selectedRow = pedidoTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int idPedido = (int) pedidoTableModel.getValueAt(selectedRow, 0);

                        // Eliminar detalles del pedido
                        detallePedidoControlador.deleteDetallesByIdPedido(idPedido);
                        // Eliminar el pedido
                        pedidoControlador.deletePedido(idPedido);
                        // Recargar la tabla de pedidos
                        cargarPedidos();
                        // Limpiar la tabla de detalles
                        detalleTableModel.setRowCount(0);

                        btnEliminar.setEnabled(false);
                    }
                }
            }
        });

        btnEliminar_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Estás seguro de que deseas eliminar este producto del pedido?",
                        "Confirmación de Eliminación",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmacion == JOptionPane.YES_OPTION) {
                    PedidoControlador pedidoControlador = new PedidoControlador();
                    DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();

                    int selectedRow = detalleTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int idDetalle = (int) detalleTableModel.getValueAt(selectedRow, 0);

                        // Eliminar detalles del pedido
                        detallePedidoControlador.deleteDetallePedido(idDetalle);

                        // Recargar la tabla de detalles del pedido
                        int idPedido = (int) pedidoTableModel.getValueAt(pedidoTable.getSelectedRow(), 0);
                        cargarDetallesPedido(idPedido);

                        btnEliminar_1.setEnabled(false);
                    }
                }
            }
        });

        btnEliminar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnEliminar.setBounds(390, 207, 150, 31);
        contentPane.add(btnEliminar);

        // Inicializar el TableRowSorter para la tabla de pedidos
        sorterPedido = new TableRowSorter<>(pedidoTableModel);
        pedidoTable.setRowSorter(sorterPedido);

        // Inicializar el TableRowSorter para la tabla de detalles
        sorterDetalle = new TableRowSorter<>(detalleTableModel);
        detalleTable.setRowSorter(sorterDetalle);

        JButton btnAgregaPedido = new JButton("Agregar Pedido");
        btnAgregaPedido.addActionListener(e -> {
            PantallaAgregarPedido pantallaAgregarPedido = new PantallaAgregarPedido(mail);
            pantallaAgregarPedido.setVisible(true);
            dispose();
        });

        btnAgregaPedido.setFont(new Font("Consolas", Font.BOLD, 18));
        btnAgregaPedido.setBounds(339, 482, 270, 31);
        contentPane.add(btnAgregaPedido);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Consolas", Font.BOLD, 18));
        btnEditar.setBounds(329, 440, 127, 31);
        contentPane.add(btnEditar);
     // Dentro del método donde agregas los botones y eventos
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = detalleTable.getSelectedRow();
                if (selectedRow != -1) {
                    int idPedido = (int) detalleTableModel.getValueAt(selectedRow, 0);
     
                    PantallaEditarPedido pantallaEditarPedido = new PantallaEditarPedido(mail, idPedido);
                    pantallaEditarPedido.setVisible(true);
                }
            }
        });

        JLabel lblNewLabel = new JLabel("Pedidos");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel.setBounds(369, 12, 223, 39);
        contentPane.add(lblNewLabel);

        btnEliminar_1.setFont(new Font("Consolas", Font.BOLD, 13));
        btnEliminar_1.setEnabled(false);
        btnEliminar_1.setBounds(474, 440, 150, 31);
        contentPane.add(btnEliminar_1);

        btnVolver.addActionListener(e -> {
            PantallaPedido pantallaPedido = new PantallaPedido(mail);
            pantallaPedido.setVisible(true);
            dispose();
        });

        editarTableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Id Pedido", "Fecha de Solicitud", "Estado" });
    }
    private void cargarDetallesPedidoEnEditarTable(int idPedido) {
        editarTableModel.setRowCount(0);
        DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();
        List<DetallePedido> detalles = detallePedidoControlador.getAllDetallePedidosByIdPedido(idPedido);
        ProductoControlador productoControlador = new ProductoControlador();
        for (DetallePedido detalle : detalles) {
            editarTableModel.addRow(new Object[] { detalle.getIdProducto(),
                productoControlador.getProductoById(detalle.getIdProducto()).getNombreProducto(),
                detalle.getCantidad() });
        }
    }

    private void cargarPedidos() {
        pedidoTableModel.setRowCount(0);
        PedidoControlador pedidoControlador = new PedidoControlador();
        List<Pedido> pedidos = pedidoControlador.getAllPedidos();
        for (Pedido pedido : pedidos) {
            pedidoTableModel
                    .addRow(new Object[] { pedido.getCodigoPedido(), pedido.getFechaEntrega(), pedido.getEstado() });
        }
    }

    private void cargarDetallesPedido(int idPedido) {
        detalleTableModel.setRowCount(0);
        DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();
        List<DetallePedido> detalles = detallePedidoControlador.getAllDetallePedidosByIdPedido(idPedido);
        ProductoControlador productoControlador = new ProductoControlador();
        for (DetallePedido detalle : detalles) {
            detalleTableModel.addRow(new Object[] {detalle.getIdDetalle(), detalle.getIdProducto(),
                    productoControlador.getProductoById(detalle.getIdProducto()).getNombreProducto(),
                    detalle.getCantidad() });
        }
    }
}
