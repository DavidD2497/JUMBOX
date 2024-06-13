package vista;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import modelos.Empleado;
import modelos.Producto;
import modelos.AdminSucursal;
import modelos.Descuento;
import controladores.AdminSucursalControlador;
import controladores.DescuentoControlador;
import controladores.ProductoControlador;

public class PantallaCrearDescuento extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpProd;
	private JTextField inpDto;
	private JLabel lblAviso;

	/**
	 * Launch the application.
	 */
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

	public PantallaCrearDescuento() {
		contentPane = new ImagePanel("/resources/supermercado.jpg");
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 384);

		JLabel crearDescuento = new JLabel("Crear descuento");
		crearDescuento.setFont(new Font("Consolas", Font.PLAIN, 35));
		crearDescuento.setBounds(171, 21, 285, 41);
		contentPane.add(crearDescuento);

		JLabel lblId = new JLabel("Seleccione el ID del producto");
		lblId.setHorizontalTextPosition(SwingConstants.CENTER);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Consolas", Font.BOLD, 20));
		lblId.setBounds(0, 73, 611, 55);
		contentPane.add(lblId);

		inpProd = new JTextField();
		inpProd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inpProd.setBounds(155, 130, 301, 31);
		inpProd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(inpProd);

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

		JButton btnCrear = new JButton("CREAR");
		btnCrear.setFont(new Font("Consolas", Font.BOLD, 13));
		btnCrear.setBounds(193, 302, 99, 31);
		contentPane.add(btnCrear);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
		btnVolver.setBounds(317, 302, 99, 31);
		contentPane.add(btnVolver);

		JLabel lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setFont(new Font("Consolas", Font.BOLD, 16));
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setBounds(5, 270, 601, 21);
		lblAviso.setVisible(false);
		contentPane.add(lblAviso);

		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id = inpProd.getText();
				String Dto = inpDto.getText();

				int cantDescuento = Integer.parseInt(Dto);

				if (cantDescuento > 95 || cantDescuento < 5) {
					lblAviso.setText("El porcentaje de descuento debe estar entre 5% y 95%");
					lblAviso.setVisible(true);
				} else {
					AdminSucursal nuevo = new AdminSucursal();
					ProductoControlador productoControlador = new ProductoControlador();
					Producto producto = productoControlador.getProductoById(Integer.parseInt(Id));

					String respuesta = nuevo.crearDescuentoVencimiento(producto, cantDescuento);
					
					if (respuesta.equals("Se aplicó el descuento")) {
						PantallaDescuentos frame = new PantallaDescuentos();
						frame.setVisible(true);
						dispose();
					} else {
						lblAviso.setText(respuesta);
						lblAviso.setVisible(true);
					}
				}

				
			}
		});
	}
}