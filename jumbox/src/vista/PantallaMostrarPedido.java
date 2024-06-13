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

public class PantallaMostrarPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel pedidoTableModel;
	private DefaultTableModel detalleTableModel;
	private int selectedRow = -1;
	private JTable pedidoTable;
	private JTable detalleTable;
	private JScrollPane detalleScrollPane;

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

		JLabel lblNewLabel_2 = new JLabel("Mostrar Pedidos");
		lblNewLabel_2.setBounds(185, 11, 496, 40);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
		contentPane.add(lblNewLabel_2);

		pedidoTableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Id Pedido", "Fecha de Entrega" });
		cargarPedidos();

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
				}
			}
		});

		JScrollPane pedidoScrollPane = new JScrollPane(pedidoTable);
		pedidoScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		pedidoScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		pedidoScrollPane.setBounds(51, 80, 761, 167);
		contentPane.add(pedidoScrollPane);

		detalleTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id Producto", "Producto", "Cantidad" });

		detalleTable = new JTable(detalleTableModel);
		detalleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		detalleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));

		detalleScrollPane = new JScrollPane(detalleTable);
		detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		detalleScrollPane.setBounds(51, 273, 761, 167);
		detalleScrollPane.setVisible(false); // Inicialmente invisible
		contentPane.add(detalleScrollPane);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
		btnVolver.setBounds(413, 462, 99, 31);
		contentPane.add(btnVolver);

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
					detalle.getCantidad() });
		}
	}
}
