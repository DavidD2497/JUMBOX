package vista;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import controladores.DescuentoControlador;
import modelos.Descuento;

public class PantallaEliminarDescuento extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private int selectedRow = -1;
	private JTable table;
	private JScrollPane scrollPane; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaDescuentos frame = new PantallaDescuentos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PantallaEliminarDescuento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		contentPane = new ImagePanel("/resources/supermercado.jpg");
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMostrarDescuento = new JLabel("Eliminar Descuentos");
		lblMostrarDescuento.setBounds(143, 11, 304, 40);
		lblMostrarDescuento.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMostrarDescuento.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarDescuento.setFont(new Font("Consolas", Font.BOLD, 28));
		contentPane.add(lblMostrarDescuento);

		 tableModel = new DefaultTableModel(new Object[][] {},
	                new String[] { "ID Dto.", "Porcentaje Dto.", "ID producto" });
		cargarDescuentos();

		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		 table.getSelectionModel().addListSelectionListener(event -> {
	            if (!event.getValueIsAdjusting()) {
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow != -1) {
	                    int idDescuento = (int) table.getValueAt(selectedRow, 0);
	                }
	            }
	        });
		 
		 

		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		scrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		scrollPane.setBounds(42, 62, 504, 220);
		contentPane.add(scrollPane);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
		btnVolver.setBounds(447, 301, 99, 31);
		contentPane.add(btnVolver);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBackground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("Consolas", Font.BOLD, 13));
		btnEliminar.setBounds(42, 301, 99, 31);
		contentPane.add(btnEliminar);

		btnVolver.addActionListener(e -> {
			PantallaDescuentos pantallaDescuentos = new PantallaDescuentos();
			pantallaDescuentos.setVisible(true);
			dispose();
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione un descuento para eliminar");
					return;
				}

				// Obtener los valores de la fila seleccionada
				int idDescuento = (int) table.getValueAt(selectedRow, 0); // Suponiendo que el ID del descuento está en
																			// la primera columna
				DescuentoControlador descuentoControlador = new DescuentoControlador();
				descuentoControlador.deleteDescuento(idDescuento); // Llamar al método para eliminar en el controlador

				// Actualizar la tabla después de eliminar
				cargarDescuentos();
				JOptionPane.showMessageDialog(null, "El descuento fue eliminado correctamente");
			}
		});
	}

	private void cargarDescuentos() {
		DescuentoControlador descuentoControlador = new DescuentoControlador();
		List<Descuento> descuentos = descuentoControlador.getAllDescuentos();
		tableModel.setRowCount(0);
		for (Descuento descuento : descuentos) {
			tableModel.addRow(new Object[] { descuento.getIdDescuento(), descuento.getPorcentajeDesc(),
					descuento.getIdProducto() });
		}
	}

}
