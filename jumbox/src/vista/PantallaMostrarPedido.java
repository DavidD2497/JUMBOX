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
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PantallaMostrarPedido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel pedidoTableModel;
    private DefaultTableModel detalleTableModel;
    private int selectedRow = -1;
    public static JTable pedidoTable;
    private JTable detalleTable;
    private JScrollPane detalleScrollPane;
    private JScrollPane pedidoScrollPane;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 894, 563);
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);

        // Reemplazar contentPane por una instancia de ImagePanel con la ruta correcta
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Pedidos");
        lblNewLabel_2.setBounds(185, 11, 496, 40);
        lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
        contentPane.add(lblNewLabel_2);

        pedidoTableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Id Pedido", "Fecha de Solicitud" });
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
                    detalleScrollPane.setVisible(true);
                    lblNewLabel_2_1.setVisible(true);
                }
            }
        });

        pedidoScrollPane = new JScrollPane(pedidoTable);
        pedidoScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        pedidoScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        pedidoScrollPane.setBounds(51, 62, 761, 149);
        contentPane.add(pedidoScrollPane);

        detalleTableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Id Producto", "Producto Solicitado", "Cantidad Solicitada" });

        detalleTable = new JTable(detalleTableModel);
        detalleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        detalleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));

        detalleScrollPane = new JScrollPane(detalleTable);
        detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        detalleScrollPane.setBounds(51, 284, 761, 167);
        detalleScrollPane.setVisible(false);// Inicialmente invisible
        contentPane.add(detalleScrollPane);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
        btnVolver.setBounds(0, 0, 99, 31);
        contentPane.add(btnVolver);

        lblNewLabel_2_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Consolas", Font.BOLD, 28));
        lblNewLabel_2_1.setBounds(185, 233, 496, 40);
        lblNewLabel_2_1.setVisible(false);
        contentPane.add(lblNewLabel_2_1);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnEliminar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnEliminar.setBounds(557, 482, 99, 31);
        contentPane.add(btnEliminar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(btnEditar,"Hola, se borro todo");
        		
        	}
        });
        btnEditar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnEditar.setBounds(185, 482, 99, 31);
        contentPane.add(btnEditar);
        
        JButton btnAgregarPedido = new JButton("Agregar Pedido");
        btnAgregarPedido.setFont(new Font("Consolas", Font.BOLD, 13));
        btnAgregarPedido.setBounds(319, 482, 214, 31);
        contentPane.add(btnAgregarPedido);

        btnVolver.addActionListener(e -> {
            PantallaPedido pantallaPedido = new PantallaPedido(mail);
            pantallaPedido.setVisible(true);
            dispose();
        });
    }

    private void cargarPedidos() {
        PedidoControlador pedidoControlador = new PedidoControlador();
        List<Pedido> pedidos = pedidoControlador.getAllPedidos();
        pedidoTableModel.setRowCount(0);
        for (Pedido pedido : pedidos) {
            pedidoTableModel.addRow(new Object[] { pedido.getCodigoPedido(), pedido.getFechaEntrega() });
        }
    }

    private void cargarDetallesPedido(int idPedido) {
        DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();
        List<DetallePedido> detalles = detallePedidoControlador.getAllDetallePedidosByIdPedido(idPedido);
        ProductoControlador productoControlador = new ProductoControlador();
        detalleTableModel.setRowCount(0);
        for (DetallePedido detalle : detalles) {
            detalleTableModel.addRow(new Object[] { detalle.getIdProducto(),
                    productoControlador.getProductoById(detalle.getIdProducto()).getNombreProducto(),
                    detalle.getCantidad()+" unidades" });
        }
        this.ajustarAlturaDetalleTable();
    }

    private void ajustarAlturaPedidoTable() {
        int rowCount = pedidoTableModel.getRowCount();
        int rowHeight = pedidoTable.getRowHeight();
        int tableHeight = rowCount * rowHeight + pedidoTable.getTableHeader().getHeight();
        pedidoScrollPane.setBounds(51, 62, 761, tableHeight + 20); // Ajustar la altura de la tabla de pedidos
    }

    private void ajustarAlturaDetalleTable() {
    	int rowCount = detalleTableModel.getRowCount();
    	int rowHeight = detalleTable.getRowHeight();
    	int tableHeight = rowCount * rowHeight;
    	detalleScrollPane.setBounds(51, 273, 761, tableHeight + 22); // 24 es para el header
    }
}
