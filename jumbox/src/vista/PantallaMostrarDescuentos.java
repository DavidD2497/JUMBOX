package vista;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.util.List;
import controladores.DescuentoControlador;
import modelos.Descuento;
import java.util.regex.PatternSyntaxException;

public class PantallaMostrarDescuentos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private int selectedRow = -1;
	private JTable table;
	private JScrollPane scrollPane;
	private TableRowSorter<DefaultTableModel> sorter;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(619, 416); 
        setLocationRelativeTo(null); 
		contentPane = new ImagePanel("/resources/supermercado.jpg");
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMostrarDescuento = new JLabel("Mostrar Descuentos");
		lblMostrarDescuento.setBounds(141, 11, 304, 40);
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
		sorter = new TableRowSorter<>(tableModel);
		 table.setRowSorter(sorter);
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
		scrollPane.setBounds(42, 104, 504, 220);
		contentPane.add(scrollPane);

		// Campo de búsqueda
        JTextField textFieldBuscar = new JTextField();
        textFieldBuscar.setBounds(42, 68, 200, 25);
        contentPane.add(textFieldBuscar);

        // Botón de búsqueda
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(263, 68, 80, 25);
        btnBuscar.addActionListener(e -> {
            String textoFiltro = textFieldBuscar.getText();
            RowFilter<DefaultTableModel, Object> rf = null;
            try {
                rf = RowFilter.regexFilter("(?i)" + textoFiltro, 0, 1, 2); // Filtra insensitivo a mayúsculas/minúsculas en todas las columnas
            } catch (PatternSyntaxException ex) {
                return;
            }
            sorter.setRowFilter(rf);
        });
        contentPane.add(btnBuscar);

        // ComboBox de ordenamiento
        JComboBox<String> comboBoxOrden = new JComboBox<>();
        comboBoxOrden.addItem("ID Dto.");
        comboBoxOrden.addItem("Porcentaje Dto.");
        comboBoxOrden.addItem("ID producto");
        comboBoxOrden.setBounds(396, 68, 150, 25);
        comboBoxOrden.addActionListener(e -> {
            String seleccion = (String) comboBoxOrden.getSelectedItem();
            switch (seleccion) {
                case "ID Dto.":
                    sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
                    break;
                case "Porcentaje Dto.":
                    sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
                    break;
                case "ID producto":
                    sorter.setSortKeys(List.of(new RowSorter.SortKey(2, SortOrder.ASCENDING)));
                    break;
                default:
                    break;
            }
        });
        contentPane.add(comboBoxOrden);

		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
		btnVolver.setBounds(494, 335, 99, 31);
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
	        tableModel.addRow(new Object[] {
	            descuento.getIdDescuento(),       
	            descuento.getPorcentajeDesc(),    
	            descuento.getIdProducto()         
	        });
	    }
	}


	
}
