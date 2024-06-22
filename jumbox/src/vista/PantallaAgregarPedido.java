package vista;

import modelos.*;
import controladores.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaAgregarPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel inventarioSucursalTableModel;
	private DefaultTableModel productosSolicitudTableModel;
	private int selectedRow = -1;
	public static JTable tablaInventarioSucursal;
	private JComboBox<String> comboBoxSucursal;
	private JTable tablaProductosSolicitud;
	private JScrollPane productosSolicitudScrollPane;
	private JScrollPane inventarioSucursalScrollPane;
	private TableRowSorter<DefaultTableModel> sorterInventario;
	private TableRowSorter<DefaultTableModel> sorterProductos;

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
		DetalleInventarioControlador detalleControlador = new DetalleInventarioControlador();
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
		JButton btnEliminar = new JButton("");
		inventarioSucursalTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id Producto", "Producto", "Cantidad Inventario" });
		JLabel lblNewLabel_3 = new JLabel("Detalle del Pedido");
		tablaInventarioSucursal = new JTable(inventarioSucursalTableModel);
		tablaInventarioSucursal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaInventarioSucursal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaInventarioSucursal.getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				int selectedRow = tablaInventarioSucursal.getSelectedRow();
				if (selectedRow != -1) {
					cargarProductos();
					productosSolicitudScrollPane.setVisible(true);
					btnEliminar.setVisible(true);
					lblNewLabel_2_1.setVisible(true);
				}
			}
		});

		inventarioSucursalScrollPane = new JScrollPane(tablaInventarioSucursal);
		inventarioSucursalScrollPane.setBounds(37, 114, 522, 243);
		inventarioSucursalScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		inventarioSucursalScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		contentPane.add(inventarioSucursalScrollPane);

		JButton button = new JButton("New button");
		button.setBackground(Color.GREEN);
		inventarioSucursalScrollPane.setColumnHeaderView(button);

		JToggleButton tglbtnNewToggleButton = new JToggleButton("New xzxzzxzxbutton");
		tglbtnNewToggleButton.setForeground(Color.RED);
		inventarioSucursalScrollPane.setColumnHeaderView(tglbtnNewToggleButton);

		JToggleButton tglbtnNewToggleButton_1_1 = new JToggleButton("New toggle button");
		inventarioSucursalScrollPane.setColumnHeaderView(tglbtnNewToggleButton_1_1);

		productosSolicitudTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id Producto", "Producto Solicitado", "Cantidad Solicitada" });

		tablaProductosSolicitud = new JTable(productosSolicitudTableModel);
		tablaProductosSolicitud.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaProductosSolicitud.setFont(new Font("Tahoma", Font.PLAIN, 14));

		productosSolicitudScrollPane = new JScrollPane(tablaProductosSolicitud);
		productosSolicitudScrollPane.setBounds(586, 114, 282, 207);
		productosSolicitudScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		productosSolicitudScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		productosSolicitudScrollPane.setVisible(false); // Inicialmente invisible
		contentPane.add(productosSolicitudScrollPane);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(0, 0, 99, 31);
		btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
		contentPane.add(btnVolver);

		lblNewLabel_2_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Consolas", Font.BOLD, 28));
		lblNewLabel_2_1.setVisible(false);
		contentPane.add(lblNewLabel_2_1);

		btnEliminar.setIcon(new ImageIcon(PantallaAgregarPedido.class.getResource("/resources/Imagen1.png")));
		btnEliminar.setBackground(new Color(226, 46, 14));
		btnEliminar.setBounds(586, 318, 282, 31);
		btnEliminar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setFont(new Font("Consolas", Font.BOLD, 13));
		contentPane.add(btnEliminar);

		JButton btnAgregarPedido = new JButton("Registrar Pedido");
		btnAgregarPedido.setBounds(391, 424, 416, 40);
		btnAgregarPedido.setFont(new Font("Consolas", Font.BOLD, 20));
		contentPane.add(btnAgregarPedido);
		btnEliminar.setVisible(false);
		JLabel lblNewLabel = new JLabel("Sucursal");
		lblNewLabel.setBounds(26, 68, 183, 48);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 22));
		contentPane.add(lblNewLabel);

		comboBoxSucursal = new JComboBox<>();
		comboBoxSucursal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSucursal.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		comboBoxSucursal.setBounds(124, 75, 230, 31);
		contentPane.add(comboBoxSucursal);

		cargarSucursales(comboBoxSucursal);

		JButton btnAgregarProducto = new JButton("");
		btnAgregarProducto.setIcon(new ImageIcon(PantallaAgregarPedido.class.getResource("/resources/Imagen2.png")));
		btnAgregarProducto.setBackground(new Color(0, 128, 0));
		btnAgregarProducto.setFont(new Font("Consolas", Font.BOLD, 5));
		btnAgregarProducto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregarProducto.setBounds(37, 353, 522, 40);
		contentPane.add(btnAgregarProducto);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Consolas", Font.BOLD, 18));
		lblCantidad.setBounds(37, 417, 106, 31);
		contentPane.add(lblCantidad);

		JComboBox<String> comboBoxSucursal_1 = new JComboBox<String>();
		comboBoxSucursal_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSucursal_1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		comboBoxSucursal_1.setBounds(125, 414, 57, 31);
		contentPane.add(comboBoxSucursal_1);
		comboBoxSucursal.addActionListener(e -> {
			if (comboBoxSucursal.getSelectedIndex() > 0) {
				String selectedItem = (String) comboBoxSucursal.getSelectedItem();
				String[] parts = selectedItem.split(" - ");
			
				cargarInventarioSucursal(parts[0]);
			}
		});
		btnVolver.addActionListener(e -> {
			PantallaPedido pantallaPedido = new PantallaPedido(mail);
			pantallaPedido.setVisible(true);
			dispose();
		});

		btnAgregarProducto.addActionListener(e -> {
			int selectedRow = tablaInventarioSucursal.getSelectedRow();
			if (selectedRow != -1) {
				String idProducto = (String) tablaInventarioSucursal.getValueAt(selectedRow, 0);
				String producto = (String) tablaInventarioSucursal.getValueAt(selectedRow, 1);
				String cantidadInventario = (String) tablaInventarioSucursal.getValueAt(selectedRow, 2);
				String cantidadSolicitada = JOptionPane.showInputDialog(null, "Ingrese la cantidad solicitada:",
						"Cantidad Solicitada", JOptionPane.PLAIN_MESSAGE);

				if (cantidadSolicitada != null && !cantidadSolicitada.isEmpty()) {
					int cantidadSolicitadaInt = Integer.parseInt(cantidadSolicitada);
					int cantidadInventarioInt = Integer.parseInt(cantidadInventario);

					if (cantidadSolicitadaInt <= cantidadInventarioInt) {
						productosSolicitudTableModel.addRow(new Object[] { idProducto, producto, cantidadSolicitada });
					} else {
						JOptionPane.showMessageDialog(null,
								"La cantidad solicitada no puede ser mayor a la cantidad en inventario.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla de inventario.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		// Inicializar el TableRowSorter para la tabla de inventario de sucursal
		sorterInventario = new TableRowSorter<>(inventarioSucursalTableModel);
		tablaInventarioSucursal.setRowSorter(sorterInventario);

		// Configurar el comparador para la columna "Id Producto" como numérico
		sorterInventario.setComparator(0, (a, b) -> {
			try {
				Integer int1 = Integer.parseInt(a.toString());
				Integer int2 = Integer.parseInt(b.toString());
				return int1.compareTo(int2);
			} catch (NumberFormatException e) {
				return a.toString().compareTo(b.toString());
			}
		});

		// Inicializar el TableRowSorter para la tabla de productos solicitados
		sorterProductos = new TableRowSorter<>(productosSolicitudTableModel);
		tablaProductosSolicitud.setRowSorter(sorterProductos);

		// Configurar el comparador para la columna "Id Producto" si es necesario
		// sorterProductos.setComparator(0, (a, b) -> {
		// try {
		// Integer int1 = Integer.parseInt(a.toString());
		// Integer int2 = Integer.parseInt(b.toString());
		// return int1.compareTo(int2);
		// } catch (NumberFormatException e) {
		// return a.toString().compareTo(b.toString());
		// }
		// });

		JButton btnEliminarProducto = new JButton("Eliminar Producto");
		btnEliminarProducto.setFont(new Font("Consolas", Font.BOLD, 13));
		btnEliminarProducto.setBounds(586, 359, 282, 31);
		contentPane.add(btnEliminarProducto);
		btnEliminarProducto.addActionListener(e -> {
			int selectedRowProducto = tablaProductosSolicitud.getSelectedRow();
			if (selectedRowProducto != -1) {
				productosSolicitudTableModel.removeRow(selectedRowProducto);
			} else {
				JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla de productos solicitados.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private void cargarInventarioSucursal(String sucursal) {
		DetalleInventarioControlador detalleControlador = new DetalleInventarioControlador();
		ProductoControlador productoControlador = new ProductoControlador();
		JOptionPane.showMessageDialog(comboBoxSucursal, sucursal);
		int idSucursal = Integer.parseInt(sucursal);
		List<DetalleInventario> detalles = detalleControlador.getAllDetalleInventariosBySucursalId(idSucursal);
		inventarioSucursalTableModel.setRowCount(0);
		for (DetalleInventario detalle : detalles) {
			inventarioSucursalTableModel.addRow(new Object[] { detalle.getIdProducto(),
					productoControlador.getProductoById(detalle.getIdProducto()).getNombreProducto(),
					detalle.getCantidad() });
		}
	}

	private void cargarProductos() {
		productosSolicitudTableModel.setRowCount(0);
		// Agrega lógica para cargar productos solicitados si es necesario
	}

	private void cargarSucursales(JComboBox<String> comboBoxSucursal) {
		comboBoxSucursal.addItem("Seleccione una sucursal");
		InventarioSucursalControlador inventarioControlador = new InventarioSucursalControlador();
		List<InventarioSucursal> inventarios = inventarioControlador.getAllInventarioSucursal();
		for (InventarioSucursal inventario : inventarios) {
			comboBoxSucursal.addItem(inventario.getIdInventario() + " - " + inventario.getUbicacion());

		}
	}
}
