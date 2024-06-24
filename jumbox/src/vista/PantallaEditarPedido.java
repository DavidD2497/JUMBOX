package vista;

import modelos.Empleado;
import modelos.DetallePedido;
import controladores.EmpleadoControlador;
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

public class PantallaEditarPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel detalleTableModel;
	private JTable detalleTable;
	private JScrollPane detalleScrollPane;
	private int idPedido;

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
	public PantallaEditarPedido(String mail, int idDetallePedido) {

		this.idPedido = idDetallePedido;
		JOptionPane.showMessageDialog(contentPane, idDetallePedido);
		JLabel lblNewLabel_2_1 = new JLabel("Editar Producto");
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

		JLabel lblNewLabel_3 = new JLabel("Detalle del Pedido");

		detalleTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id Producto", "Producto Solicitado", "Cantidad Solicitada" });

		detalleTable = new JTable(detalleTableModel);
		detalleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		detalleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));

		detalleScrollPane = new JScrollPane(detalleTable);
		detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		detalleScrollPane.setBounds(46, 198, 761, 48);
		contentPane.add(detalleScrollPane);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
		btnVolver.setBounds(0, 0, 99, 31);
		contentPane.add(btnVolver);
		JButton btnEditar = new JButton("Editar");
		JComboBox<Integer> comboBox_1 = new JComboBox<>();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditar.setEnabled(true);
			}
		});
		lblNewLabel_2_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Consolas", Font.BOLD, 28));
		lblNewLabel_2_1.setBounds(177, 147, 496, 40);
		contentPane.add(lblNewLabel_2_1);

		DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = (int) comboBox_1.getSelectedItem();
				if (selectedRow > 0) {
					int nuevaCantidad = (int) comboBox_1.getSelectedItem();
					detallePedidoControlador.updateCantidadDetallePedido(idDetallePedido, nuevaCantidad);
					JOptionPane.showMessageDialog(comboBox_1, "Producto Actualizado");
					PantallaMostrarPedido pantallaMostrarPedido = new PantallaMostrarPedido(mail);
					pantallaMostrarPedido.setVisible(true);
					dispose();
				}
			}
		});
		btnEditar.setFont(new Font("Consolas", Font.BOLD, 18));
		btnEditar.setBounds(309, 418, 229, 48);
		contentPane.add(btnEditar);
		btnEditar.setEnabled(false);
		comboBox_1.setBounds(322, 376, 203, 31);
		contentPane.add(comboBox_1);
		for (int i = 0; i < 101; i++) {
			comboBox_1.addItem(i);
		}

		JLabel lblIngresarNuevaCantidad = new JLabel("Ingresar nueva cantidad");
		lblIngresarNuevaCantidad.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblIngresarNuevaCantidad.setBounds(311, 302, 404, 63);
		contentPane.add(lblIngresarNuevaCantidad);

		btnVolver.addActionListener(e -> {
			PantallaMostrarPedido pantallaMostrarPedido = new PantallaMostrarPedido(mail);
			pantallaMostrarPedido.setVisible(true);
			dispose();
		});

		cargarDetallePedido(idDetallePedido);
	}

	private void cargarDetallePedido(int idDetallePedido) {
		JOptionPane.showMessageDialog(detalleScrollPane, idDetallePedido);
		DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();
		DetallePedido detalle = detallePedidoControlador.getDetallePedidoById(idDetallePedido);
		ProductoControlador productoControlador = new ProductoControlador();
		detalleTableModel.setRowCount(0);
		detalleTableModel
				.addRow(new Object[] { detallePedidoControlador.getDetallePedidoById(idDetallePedido).getIdProducto(),
						productoControlador.getProductoById(detalle.getIdProducto()).getNombreProducto(),
						detallePedidoControlador.getDetallePedidoById(idDetallePedido).getCantidad() + " unidades" });

	}
}