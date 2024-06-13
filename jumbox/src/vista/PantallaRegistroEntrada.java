package vista;

import modelos.Empleado;
import modelos.AdminSucursal;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controladores.EmpleadoControlador;
import controladores.AdminSucursalControlador;
import controladores.ProductoControlador;
import modelos.Producto;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;



import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.ListSelectionModel;

public class PantallaRegistroEntrada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpCANTIDAD;
	private DefaultTableModel tableModel;
	private JTextField intIDsuc;
	private int selectedRow = -1;
	private JTable table;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public PantallaRegistroEntrada(String mail) {
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

		JLabel lblNewLabel_2 = new JLabel("Registrar entrada de Productos");
		lblNewLabel_2.setBounds(220, 11, 496, 40);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
		contentPane.add(lblNewLabel_2);

		
		JLabel lblSeleccionado = new JLabel("");
		lblSeleccionado.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSeleccionado.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionado.setFont(new Font("Consolas", Font.BOLD, 18));
		lblSeleccionado.setBounds(149, 288, 530, 29);
		contentPane.add(lblSeleccionado);
		
		
		JLabel lblError = new JLabel("");
		lblError.setBackground(new Color(255, 0, 0));
		lblError.setVerticalAlignment(SwingConstants.BOTTOM);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Consolas", Font.BOLD, 18));
		lblError.setBounds(52, 436, 573, 29);
		contentPane.add(lblError);
		
		JLabel lblAprobado = new JLabel("");
		lblAprobado.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAprobado.setHorizontalAlignment(SwingConstants.CENTER);
		lblAprobado.setFont(new Font("Consolas", Font.BOLD, 18));
		lblAprobado.setBackground(new Color(0, 128, 0));
		lblAprobado.setBounds(10, 411, 858, 29);
		contentPane.add(lblAprobado);
		
		tableModel = new DefaultTableModel(
				new Object[][]{},
				new String[]{"Id Producto", "Nombre" , "Categoria"}
		);

		JTable table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBounds(51, 79, 683, 112);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		scrollPane.setBounds(52, 119, 800, 144);
		contentPane.add(scrollPane);

		cargarProductos();

		table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedRow = table.getSelectedRow();
                String producto = (String) table.getValueAt(selectedRow, 1);
                lblSeleccionado.setText("Producto seleccionado: " + producto);
            } else {
                lblSeleccionado.setText("Debes seleccionar un producto para poder registrarlo.");
                selectedRow = -1;
            }
        });

		JLabel lblCANTIDAD = new JLabel("Cantidad:");
		lblCANTIDAD.setBounds(493, 360, 200, 40);
		lblCANTIDAD.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCANTIDAD.setHorizontalAlignment(SwingConstants.CENTER);
		lblCANTIDAD.setHorizontalTextPosition(SwingConstants.CENTER);
		contentPane.add(lblCANTIDAD);

		inpCANTIDAD = new JTextField();
		inpCANTIDAD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inpCANTIDAD.setBounds(460, 394, 261, 31);
		inpCANTIDAD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(inpCANTIDAD);
		
		JLabel lblIdsucursal = new JLabel("Id Inventario Sucursal:");
		lblIdsucursal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblIdsucursal.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdsucursal.setFont(new Font("Consolas", Font.BOLD, 20));
		lblIdsucursal.setBounds(111, 371, 288, 29);
        contentPane.add(lblIdsucursal);

        String[] Idinventarios = {"1","2","3","4","5","6","7","8","9","10"};
        JComboBox<String> comboBoxIdinventario = new JComboBox<>(Idinventarios);
        comboBoxIdinventario.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxIdinventario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        comboBoxIdinventario.setBounds(111, 395, 301, 29);
        contentPane.add(comboBoxIdinventario);

		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.setFont(new Font("Consolas", Font.BOLD, 13));
		btnRegistrar.setBounds(330, 462, 99, 31);

		btnRegistrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (selectedRow != -1) {
		            try {
		            	int Idinventarios = Integer.parseInt((String) comboBoxIdinventario.getSelectedItem()) ;
		                int cantidad = Integer.parseInt(inpCANTIDAD.getText());
		                Object valueAt = table.getValueAt(selectedRow, 0);

		                int idProducto;
		                if (valueAt instanceof Integer) {
		                    idProducto = (Integer) valueAt;
		                } else if (valueAt instanceof String) {
		                    idProducto = Integer.parseInt((String) valueAt);
		                } else {
		                    throw new NumberFormatException("Tipo de datos inesperado en la celda seleccionada.");
		                }

		                String respuesta = AdminSucursal.registroEntradaProducto(Idinventarios, idProducto, cantidad);
		                if (respuesta.equals("Registro con éxito")) {
		                    lblAprobado.setText(respuesta);
		                    lblAprobado.setVisible(true);
		                    lblError.setVisible(false);
		                    selectedRow = -1;
		                    cargarProductos();
		                } else {
		                    lblError.setText(respuesta);
		                    lblError.setVisible(true);
		                    lblAprobado.setVisible(false);
		                }
		            } catch (NumberFormatException ex) {
		                lblError.setText("Por favor, ingrese valores numeros válidos.");
		                lblError.setVisible(true);
		                lblAprobado.setVisible(false);
		            }
		        } else {
		            lblError.setText("Por favor, seleccione un producto.");
		            lblError.setVisible(true);
		            lblAprobado.setVisible(false);
		        }
		    }
		});
		contentPane.add(btnRegistrar);
		
		JButton btnVolver_1 = new JButton("Volver");
		btnVolver_1.setFont(new Font("Consolas", Font.BOLD, 13));
		btnVolver_1.setBounds(460, 462, 99, 31);
		contentPane.add(btnVolver_1);

		
		
		
		
	
	}

	private void cargarProductos() {
		ProductoControlador productoControlador = new ProductoControlador();
		List<Producto> productos = productoControlador.getAllProductos();
		tableModel.setRowCount(0);
		for (Producto producto : productos) {
			tableModel.addRow(new Object[]{
					producto.getIdProducto(),
					producto.getNombreProducto(),
					producto.getCategoria(),
					
			});
		 }
	}
}
