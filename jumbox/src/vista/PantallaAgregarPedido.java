package vista;

import modelos.*;
import controladores.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaAgregarPedido extends JFrame {

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
	public PantallaAgregarPedido(String mail) {
		JLabel lblNewLabel_2_1 = new JLabel("Productos Solicitados");
		lblNewLabel_2_1.setBounds(217, 25, 496, 40);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 563);
		EmpleadoControlador empleadoControlador = new EmpleadoControlador();
		ProductoControlador productoControlador = new ProductoControlador();
		DetalleInventarioControlador detalleControlador= new DetalleInventarioControlador();
		InventarioSucursalControlador inventarioControlador = new InventarioSucursalControlador();
		Empleado empleado = empleadoControlador.getUserByEmail(mail);

		// Reemplazar contentPane por una instancia de ImagePanel con la ruta correcta
		contentPane = new ImagePanel("/resources/supermercado.jpg");
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Productos Agregados al Pedido");
		lblNewLabel_2.setBounds(479, 73, 496, 40);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 18));
		contentPane.add(lblNewLabel_2);

		pedidoTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id Producto", "Producto", "Cantidad Inventario" });
		JLabel lblNewLabel_3 = new JLabel("Detalle del Pedido");
		pedidoTable = new JTable(pedidoTableModel );
		pedidoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pedidoTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pedidoTable.getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {

				int selectedRow = pedidoTable.getSelectedRow();
				if (selectedRow != -1) {
					cargarProductos();

					detalleScrollPane.setVisible(true);
					lblNewLabel_2_1.setVisible(true);
				}
			}
		});

		String[] tiposSucursal = new String[inventarioControlador.getAllInventarioSucursal().size()];
		for (int i = 0; i < inventarioControlador.getAllInventarioSucursal().size(); i++) {
			tiposSucursal[i] = inventarioControlador.getInventarioSucursalById(i).getUbicacion();
		}
		JComboBox<String> comboBoxTipo = new JComboBox<>(tiposSucursal);
		comboBoxTipo.setBounds(145, 76, 229, 29);
		comboBoxTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxTipo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(comboBoxTipo);

		pedidoScrollPane = new JScrollPane(pedidoTable);
		pedidoScrollPane.setBounds(37, 114, 522, 328);
		pedidoScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		pedidoScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		contentPane.add(pedidoScrollPane);
		
		JButton button = new JButton("New button");
		button.setBackground(Color.GREEN);
		pedidoScrollPane.setColumnHeaderView(button);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New xzxzzxzxbutton");
		tglbtnNewToggleButton.setForeground(Color.RED);
		pedidoScrollPane.setColumnHeaderView(tglbtnNewToggleButton);

		detalleTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id Producto", "Producto Solicitado", "Cantidad Solicitada" });

		detalleTable = new JTable(detalleTableModel);
		detalleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		detalleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));

		detalleScrollPane = new JScrollPane(detalleTable);
		detalleScrollPane.setBounds(586, 114, 282, 207);
		detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		detalleScrollPane.setVisible(false);// Inicialmente invisible
		contentPane.add(detalleScrollPane);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(0, 0, 99, 31);
		btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
		contentPane.add(btnVolver);

		lblNewLabel_2_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Consolas", Font.BOLD, 28));
		lblNewLabel_2_1.setVisible(false);
		contentPane.add(lblNewLabel_2_1);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(676, 326, 99, 31);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setFont(new Font("Consolas", Font.BOLD, 13));
		contentPane.add(btnEliminar);

		JButton btnAgregarPedido = new JButton("Registrar Pedido");
		btnAgregarPedido.setBounds(239, 469, 416, 31);
		btnAgregarPedido.setFont(new Font("Consolas", Font.BOLD, 20));
		contentPane.add(btnAgregarPedido);

		JLabel lblNewLabel = new JLabel("Sucursal");
		lblNewLabel.setBounds(38, 67, 197, 54);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 22));
		contentPane.add(lblNewLabel);

		btnVolver.addActionListener(e -> {
			PantallaPedido pantallaPedido = new PantallaPedido(mail);
			pantallaPedido.setVisible(true);
			dispose();
		});
	}

	private void cargarProductos() {
		ProductoControlador productoControlador = new ProductoControlador();
		InventarioSucursalControlador inventarioControlador = new InventarioSucursalControlador();
		DetalleInventarioControlador detalleControlador= new DetalleInventarioControlador();
		List<Producto> productos = productoControlador.getAllProductos();
		pedidoTableModel.setRowCount(0);
		for (Producto producto : productos) {
			pedidoTableModel.addRow(new Object[] { producto.getIdProducto(),producto.getNombreProducto(),detalleControlador.getCantidadDisponible(selectedRow, selectedRow) });
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
					detalle.getCantidad() + " unidades" });
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
