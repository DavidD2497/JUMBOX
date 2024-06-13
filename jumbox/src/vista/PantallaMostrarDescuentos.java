package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import controladores.DescuentoControlador;
import modelos.Descuento;

public class PantallaMostrarDescuentos extends JFrame {
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

	public PantallaMostrarDescuentos() {
		contentPane = new ImagePanel("/resources/supermercado.jpg");
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMostrarDescuento = new JLabel("Mostrar Descuentos");
		lblMostrarDescuento.setBounds(172, 11, 304, 40);
		lblMostrarDescuento.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMostrarDescuento.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarDescuento.setFont(new Font("Consolas", Font.BOLD, 28));
		contentPane.add(lblMostrarDescuento);

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "ID Dto.","Porcentaje Dto.", "ID producto" });
		cargarDescuentos();

		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		table.getSelectionModel().addListSelectionListener(event -> {
		    if (!event.getValueIsAdjusting()) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            int idDescuento = (int) table.getValueAt(selectedRow, 0);
		            cargarDescuentos();
		            scrollPane.setVisible(true);
		        }
		    }
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		scrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		scrollPane.setBounds(51, 80, 761, 177);
		contentPane.add(scrollPane);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
		btnVolver.setBounds(508, 305, 99, 31);
		contentPane.add(btnVolver);

		btnVolver.addActionListener(e -> {
			PantallaDescuentos pantallaDescuentos = new PantallaDescuentos();
			pantallaDescuentos.setVisible(true);
			dispose();
		});
	}

	private void cargarDescuentos() {
		DescuentoControlador descuentoControlador = new DescuentoControlador();
		List<Descuento> descuentos = descuentoControlador.getAllDescuentos();
		tableModel.setRowCount(0);
		for (Descuento descuento : descuentos) {
			tableModel.addRow(new Object[] { descuento.getPorcentajeDesc(), descuento.getIdDescuento(),
					descuento.getIdProducto() });
		}
		//this.ajustarAlturaDescuentoTable();
	}

	//private void ajustarAlturaDescuentoTable() {
	    //int rowCount = tableModel.getRowCount();
	   // int rowHeight = table.getRowHeight();
	   // int tableHeight = rowCount * rowHeight;
	   // table.setPreferredScrollableViewportSize(new Dimension(761, tableHeight));
	   // table.revalidate();
	//}


}
