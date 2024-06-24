package vista;

import modelos.*;
import controladores.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class PantallaAgregarPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel inventarioSucursalTableModel;
	private DefaultTableModel productosSolicitudTableModel;
	private int selectedRow = -1;
	public static JTable tablaInventarioSucursal;
	private JTextField filtroNombre;
	private JComboBox<String> filtroCategoria;
	private JComboBox<String> comboBoxSucursal;
	private JTable tablaProductosSolicitud;
	private JScrollPane productosSolicitudScrollPane;
	private JScrollPane inventarioSucursalScrollPane;
	private TableRowSorter<DefaultTableModel> sorterInventario;
	private TableRowSorter<DefaultTableModel> sorterProductos;
	private int selectedItem1; // Mover selectedItem1 aquí

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

		LinkedList<DetallePedido> listaDetalles = new LinkedList<DetallePedido>();
		JComboBox<Integer> comboBoxSucursal_1 = new JComboBox<Integer>();
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
		lblNewLabel_2.setBounds(466, 76, 496, 40);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 18));
		contentPane.add(lblNewLabel_2);
		JButton btnAgregarProducto = new JButton("");
		// btnAgregarProducto.setBackground(new Color(0, 128, 0));
		JButton btnEliminar = new JButton("");
		btnEliminar.setEnabled(false);
		inventarioSucursalTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id Producto", "Producto", "Cantidad Inventario" });
		JLabel lblNewLabel_3 = new JLabel("Detalle del Pedido");
		tablaInventarioSucursal = new JTable(inventarioSucursalTableModel);
		tablaInventarioSucursal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaInventarioSucursal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaInventarioSucursal.getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				if (tablaInventarioSucursal.getSelectedRow() != -1) {
					int j = (int) comboBoxSucursal_1.getSelectedItem();
					if (j > 0) {
						btnAgregarProducto.setEnabled(true);
						btnAgregarProducto.setBackground(new Color(0, 128, 0));
					}

				}

			}
		});

		inventarioSucursalScrollPane = new JScrollPane(tablaInventarioSucursal);
		inventarioSucursalScrollPane.setBounds(10, 114, 522, 243);
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
		productosSolicitudScrollPane.setBounds(542, 114, 326, 243);
		productosSolicitudScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		productosSolicitudScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		productosSolicitudScrollPane.setVisible(true); // Inicialmente invisible
		contentPane.add(productosSolicitudScrollPane);
		tablaProductosSolicitud.getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				btnEliminar.setEnabled(true);
				btnEliminar.setBackground(new Color(210, 0, 0));

			}
		});
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(0, 0, 99, 31);
		btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
		contentPane.add(btnVolver);

		lblNewLabel_2_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Consolas", Font.BOLD, 28));
		lblNewLabel_2_1.setVisible(true);
		contentPane.add(lblNewLabel_2_1);

		btnEliminar.setIcon(new ImageIcon(PantallaAgregarPedido.class.getResource("/resources/Imagen1.png")));
		btnEliminar.setBackground(new Color(128, 0, 0));
		btnEliminar.setBounds(542, 358, 326, 26);
		btnEliminar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				listaDetalles.remove(tablaProductosSolicitud.getSelectedRow());
				productosSolicitudTableModel.removeRow(tablaProductosSolicitud.getSelectedRow());

				btnEliminar.setEnabled(false);
				btnEliminar.setBackground(new Color(128, 0, 0));
			}
		});
		btnEliminar.setFont(new Font("Consolas", Font.BOLD, 13));
		contentPane.add(btnEliminar);

		JButton btnAgregarPedido = new JButton("Registrar Pedido");
		btnAgregarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSucursal.solicitarPedido(listaDetalles);

			}
		});
		btnAgregarPedido.setBounds(227, 454, 416, 40);
		btnAgregarPedido.setFont(new Font("Consolas", Font.BOLD, 20));
		contentPane.add(btnAgregarPedido);

		comboBoxSucursal = new JComboBox<>();
		comboBoxSucursal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSucursal.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		comboBoxSucursal.setBounds(202, 78, 330, 31);
		contentPane.add(comboBoxSucursal);

		cargarSucursales(comboBoxSucursal);

		btnAgregarProducto.setIcon(new ImageIcon(PantallaAgregarPedido.class.getResource("/resources/Imagen2.png")));
		btnAgregarProducto.setBackground(new Color(67, 100, 66));
		btnAgregarProducto.setEnabled(false);
		btnAgregarProducto.setFont(new Font("Consolas", Font.BOLD, 5));
		btnAgregarProducto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregarProducto.setBounds(251, 358, 281, 26);
		contentPane.add(btnAgregarProducto);

		comboBoxSucursal_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSucursal_1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		comboBoxSucursal_1.setBounds(106, 358, 143, 26);
		contentPane.add(comboBoxSucursal_1);
		comboBoxSucursal.addActionListener(e -> {
			inventarioSucursalTableModel.setRowCount(0);
			String nombre = (String) comboBoxSucursal.getSelectedItem();
			if (comboBoxSucursal.getSelectedIndex() > 0) {
				String selectedItem = (String) comboBoxSucursal.getSelectedItem();
				String[] parts = selectedItem.split(" - ");
				aplicarFiltros(parts[0]);

			} else {
				inventarioSucursalTableModel.setRowCount(0);
			}
		});
		for (int i = 0; i < 101; i++) {

			// comboBoxSucursal.addItem(i);
			comboBoxSucursal_1.addItem(i);

		}

		comboBoxSucursal_1.addActionListener(e -> {
			if (comboBoxSucursal_1.getSelectedIndex() > 0) {
				selectedItem1 = (int) comboBoxSucursal_1.getSelectedItem();
				if (tablaInventarioSucursal.getSelectedRow() != -1) {
					btnAgregarProducto.setEnabled(true);
					btnAgregarProducto.setBackground(new Color(0, 128, 0));
				}
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
				int j = (int) tablaInventarioSucursal.getValueAt(selectedRow, 0);
				String nombre = (String) tablaInventarioSucursal.getValueAt(selectedRow, 1);

				DetallePedido ped = new DetallePedido(j, selectedItem1, 0);
				listaDetalles.add(ped);

				productosSolicitudTableModel.addRow(new Object[] { j, nombre, selectedItem1 });
				comboBoxSucursal_1.setSelectedItem(0);
				selectedItem1 = 0;
				btnAgregarProducto.setEnabled(false);
				btnAgregarProducto.setBackground(new Color(67, 100, 66));
				tablaInventarioSucursal.clearSelection();
			}

			;
		});

		// Inicializar el TableRowSorter para la tabla de inventario de sucursal
		sorterInventario = new TableRowSorter<>(inventarioSucursalTableModel);
		tablaInventarioSucursal.setRowSorter(sorterInventario);

		// Configurar el comparador para la columna "Id Producto" como numérico
		sorterInventario.setComparator(0, (a, b) ->

		{
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

		JTextPane txtpnCantidad = new JTextPane();
		txtpnCantidad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnCantidad.setText(" Cantidad");
		txtpnCantidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtpnCantidad.setBounds(10, 358, 99, 26);
		contentPane.add(txtpnCantidad);

		filtroNombre = new JTextField();
		filtroNombre.setText("Buscar Por Nombre");
		filtroNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		filtroNombre.setBounds(10, 79, 182, 30);
		contentPane.add(filtroNombre);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hola=(int)comboBoxSucursal_1.getSelectedItem()+1;
				comboBoxSucursal_1.setSelectedItem(hola);
			}
		});
		btnNewButton.setIcon(new ImageIcon(PantallaAgregarPedido.class.getResource("/resources/Imagen2.png")));
		btnNewButton.setBounds(208, 358, 41, 26);
		contentPane.add(btnNewButton);
		filtroNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (filtroNombre.getText().equals("Buscar Por Nombre")) {
					filtroNombre.setText("");
					filtroNombre.setForeground(Color.GRAY); // Cambia el color del texto al obtener el foco
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (filtroNombre.getText().isEmpty()) {
					filtroNombre.setText("Buscar Por Nombre");
					filtroNombre.setForeground(Color.GRAY); // Cambia el color del texto al perder el foco
				}
			}
		});
		JDialog dialogo = new JDialog(this, "Ventana de Diálogo", true);
		dialogo.setSize(200, 150);
		dialogo.setLocationRelativeTo(this); // Centrar el diálogo respecto a la ventana principal

		JLabel etiqueta = new JLabel("Este es un JDialog de ejemplo");
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		dialogo.getContentPane().add(etiqueta);

		dialogo.setVisible(true); // Mostrar el diálogo

		filtroNombre.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (comboBoxSucursal.getSelectedIndex() > 0) {
					String selectedItem = (String) comboBoxSucursal.getSelectedItem();
					String[] parts = selectedItem.split(" - ");

					aplicarFiltros(parts[0]);
				}
			}
		});
		// ...
	}

	private void mostrarDialogo() {
		// Crear un JDialog con el JFrame como padre
		JDialog dialogo = new JDialog(this, "Ventana de Diálogo", true);
		dialogo.setSize(200, 150);
		dialogo.setLocationRelativeTo(this); // Centrar el diálogo respecto a la ventana principal

		JLabel etiqueta = new JLabel("Este es un JDialog de ejemplo");
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		dialogo.getContentPane().add(etiqueta);

		dialogo.setVisible(true); // Mostrar el diálogo
	}

	private void aplicarFiltros(String sucursal) {
		int idSucursal = Integer.parseInt(sucursal);
		if (filtroNombre.getText().equals("Buscar Por Nombre")) {
			cargarInventarioSucursal(sucursal);
			return;
		} else {
			DetalleInventarioControlador detalleControlador = new DetalleInventarioControlador();
			String nombreFiltro = filtroNombre.getText().trim();
			idSucursal = Integer.parseInt(sucursal);
			List<DetalleInventario> detalles = detalleControlador.getAllDetalleInventariosBySucursalId(idSucursal);
			ProductoControlador productoControlador = new ProductoControlador();
			List<Producto> productos = productoControlador.getAllProductos();

			List<Producto> productosFiltrados = productos.stream()
					.filter(p -> p.getNombreProducto().toLowerCase().contains(nombreFiltro.toLowerCase()))
					.collect(Collectors.toList());

			actualizarTabla(productosFiltrados, idSucursal);
		}

	}

	private void actualizarTabla(List<Producto> productos, int sucursal) {
		inventarioSucursalTableModel.setRowCount(0);
		DetalleInventarioControlador detalleControlador = new DetalleInventarioControlador();
		List<DetalleInventario> detalles = detalleControlador.getAllDetalleInventariosBySucursalId(sucursal);
		int cantidad;
		for (Producto producto : productos) {
			cantidad = 0;
			for (DetalleInventario detalleInventario : detalles) {
				if (detalleInventario.getIdProducto() == producto.getIdProducto()) {
					cantidad = detalleInventario.getCantidad();
				}
			}

			inventarioSucursalTableModel
					.addRow(new Object[] { producto.getIdProducto(), producto.getNombreProducto(), cantidad });
			;
		}
	}

	private void cargarInventarioSucursal(String sucursal) {
		DetalleInventarioControlador detalleControlador = new DetalleInventarioControlador();
		ProductoControlador productoControlador = new ProductoControlador();

		int idSucursal = Integer.parseInt(sucursal);
		List<DetalleInventario> detalles = detalleControlador.getAllDetalleInventariosBySucursalId(idSucursal);

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

	private void limpiarTabla(JTable table) {
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID Producto", "Nombre", "Categoría" }));
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
