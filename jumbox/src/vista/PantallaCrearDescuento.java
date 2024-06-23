package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import modelos.AdminSucursal;
import modelos.Producto;
import controladores.ProductoControlador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PantallaCrearDescuento extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpProd;
	private JTextField inpDto;
	private JLabel lblAviso;
	private AdminSucursal adminSucursal;
	private JComboBox<Producto> comboBoxProductos;
	private JButton btnCrear;
	private JButton btnVolver;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaCrearDescuento frame = new PantallaCrearDescuento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PantallaCrearDescuento() {
		contentPane = new ImagePanel("/resources/supermercado.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 384);

		JLabel crearDescuento = new JLabel("Crear descuento");
		crearDescuento.setFont(new Font("Consolas", Font.PLAIN, 35));
		crearDescuento.setBounds(171, 21, 285, 41);
		contentPane.add(crearDescuento);

		JLabel lblId = new JLabel("Seleccione el nombre del producto");
		lblId.setHorizontalTextPosition(SwingConstants.CENTER);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Consolas", Font.BOLD, 20));
		lblId.setBounds(10, 64, 611, 55);
		contentPane.add(lblId);

		comboBoxProductos = new JComboBox<>();
		comboBoxProductos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxProductos.setBounds(155, 130, 301, 31);
		comboBoxProductos.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		contentPane.add(comboBoxProductos);

		llenarComboBoxProductos();

		setVisible(true);

		JLabel lblPorc = new JLabel("Ingrese porcentaje de descuento");
		lblPorc.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPorc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorc.setFont(new Font("Consolas", Font.BOLD, 20));
		lblPorc.setBounds(10, 184, 611, 55);
		contentPane.add(lblPorc);

		inpDto = new JTextField();
		inpDto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inpDto.setBounds(155, 234, 301, 31);
		inpDto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(inpDto);

		btnCrear = new JButton("CREAR");
		btnCrear.setFont(new Font("Consolas", Font.BOLD, 13));
		btnCrear.setBounds(193, 302, 99, 31);
		contentPane.add(btnCrear);

		btnVolver = new JButton("VOLVER");
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
		btnVolver.setBounds(317, 302, 99, 31);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(e -> {
			PantallaDescuentos pantallaDescuentos = new PantallaDescuentos();
			pantallaDescuentos.setVisible(true);
			dispose();
		});

		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setFont(new Font("Consolas", Font.BOLD, 16));
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setBounds(5, 270, 601, 21);
		lblAviso.setVisible(false);
		contentPane.add(lblAviso);

		btnVolver.addActionListener(e -> {
			PantallaDescuentos pantallaDescuentos = new PantallaDescuentos();
			pantallaDescuentos.setVisible(true);
			dispose();
		});
		
		btnCrear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        btnCrear.setEnabled(false); 

		        Producto productoSeleccionado = (Producto) comboBoxProductos.getSelectedItem();

		        if (productoSeleccionado != null) {
		            if (Producto.tieneDescuentoActivo(productoSeleccionado)==true) {
		                lblAviso.setText("Ya existe un descuento activo para este producto.");
		                lblAviso.setVisible(true);
		                btnCrear.setEnabled(true); 
		                return; 
		            }

		            String Dto = inpDto.getText();

		            try {
		                int cantDescuento = Integer.parseInt(Dto);

		                if (cantDescuento > 95 || cantDescuento < 5) {
		                    lblAviso.setText("El porcentaje de descuento debe estar entre 5% y 95%");
		                    lblAviso.setVisible(true);
		                } else {
		                    AdminSucursal adminSucursal = new AdminSucursal();
		                    String respuesta = adminSucursal.crearDescuentoVencimiento(productoSeleccionado, cantDescuento);

		                    if (respuesta.equals("Se aplicó el descuento")) {
		                        JOptionPane.showMessageDialog(null, "Descuento aplicado con éxito");
		                        dispose();
		                        
		                
		                        PantallaDescuentos frame = new PantallaDescuentos();
		                        frame.setVisible(true); 
		                    } else {
		                        lblAviso.setText(respuesta);
		                        lblAviso.setVisible(true);
		                    }
		                }
		            } catch (NumberFormatException ex) {
		                lblAviso.setText("El porcentaje de descuento debe ser un número entero");
		                lblAviso.setVisible(true);
		            }
		        } else {
		            lblAviso.setText("Seleccione un producto válido");
		            lblAviso.setVisible(true);
		        }

		        btnCrear.setEnabled(true); 
		    }
		});
	}

	private void llenarComboBoxProductos() {
	    ProductoControlador productoControlador = new ProductoControlador();
	    List<Producto> productos = productoControlador.getAllProductos();

	    comboBoxProductos.removeAllItems();

	    for (Producto producto : productos) {
	        comboBoxProductos.addItem(producto);
	    }

	    comboBoxProductos.setRenderer(new DefaultListCellRenderer() {
	        @Override
	        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	            if (value instanceof Producto) {
	                Producto producto = (Producto) value;
	                setText(producto.getNombreProducto());
	            }
	            return this;
	        }
	    });
	}
}
