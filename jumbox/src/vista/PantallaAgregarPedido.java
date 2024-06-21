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
	private DefaultTableModel inventarioSucursalTableModel;
	private DefaultTableModel productosSolicitudTableModel;
	private int selectedRow = -1;
	public static JTable tablaInventarioSucursal;
	private JComboBox<String> comboBoxSucursal;
	private JTable tablaProductosSolicitud;
	private JScrollPane productosSolicitudScrollPane;
	private JScrollPane inventarioSucursalScrollPane;

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

		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1.setBounds(178, 116, 28, 23);
		contentPane.add(tglbtnNewToggleButton_1);

		JLabel lblNewLabel_2 = new JLabel("Productos Agregados al Pedido");
		lblNewLabel_2.setBounds(479, 73, 496, 40);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 18));
		contentPane.add(lblNewLabel_2);

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
					lblNewLabel_2_1.setVisible(true);
				}
			}
		});

		inventarioSucursalScrollPane = new JScrollPane(tablaInventarioSucursal);
		inventarioSucursalScrollPane.setBounds(37, 114, 522, 328);
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

		comboBoxSucursal = new JComboBox<>();
		comboBoxSucursal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSucursal.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		comboBoxSucursal.setBounds(141, 75, 230, 31);
		contentPane.add(comboBoxSucursal);

		cargarSucursales(comboBoxSucursal);
		comboBoxSucursal.addActionListener(e -> {
			if (comboBoxSucursal.getSelectedIndex() > 0) {
				String selectedItem = (String) comboBoxSucursal.getSelectedItem();
				String[] parts = selectedItem.split(" - ");
				int idSucursal = Integer.parseInt(parts[0]);
				cargarInventarioSucursal(idSucursal);
			}
		});

		btnVolver.addActionListener(e -> {
			PantallaPedido pantallaPedido = new PantallaPedido(mail);
			pantallaPedido.setVisible(true);
			dispose();
		});
	}

	private void cargarSucursales(JComboBox<String> combo) {
		combo.addItem("--Seleccione una sucursal--");

		InventarioSucursalControlador inventarioControlador = new InventarioSucursalControlador();
		List<InventarioSucursal> inventarios = inventarioControlador.getAllInventarioSucursal();
		for (InventarioSucursal inventario : inventarios) {
			combo.addItem(inventario.getIdInventario() + " - " + inventario.getUbicacion());
		}
	}

	private void cargarInventarioSucursal(int idSucursal) {
		DetalleInventarioControlador detalleControlador = new DetalleInventarioControlador();
		ProductoControlador productoControlador = new ProductoControlador();
		List<DetalleInventario> detalles = detalleControlador.getAllDetalleInventariosBySucursalId(idSucursal);
		inventarioSucursalTableModel.setRowCount(0);
		for (DetalleInventario detalle : detalles) {
			inventarioSucursalTableModel.addRow(new Object[] {detalle.getIdProducto(),
					productoControlador.getProductoById(detalle.getIdProducto()).getNombreProducto(), detalle.getCantidad() });
		}
	}

	private void cargarProductos() {
		ProductoControlador productoControlador = new ProductoControlador();
		InventarioSucursalControlador inventarioControlador = new InventarioSucursalControlador();
		DetalleInventarioControlador detalleControlador = new DetalleInventarioControlador();
		List<Producto> productos = productoControlador.getAllProductos();
	}
}
