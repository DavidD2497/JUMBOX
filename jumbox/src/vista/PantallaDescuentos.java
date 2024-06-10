package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class PantallaDescuentos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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

	public PantallaDescuentos() {
		contentPane = new ImagePanel("/resources/supermercado.jpg");
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 383);

		JLabel Descuentos = new JLabel("Descuentos");
		Descuentos.setFont(new Font("Consolas", Font.PLAIN, 35));
		Descuentos.setBounds(219, 24, 190, 41);
		contentPane.add(Descuentos);

		JButton btnCrearDescuento = new JButton("Crear Descuento");
		btnCrearDescuento.setFont(new Font("Consolas", Font.BOLD, 15));
		btnCrearDescuento.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCrearDescuento.setBounds(103, 143, 156, 33);
		contentPane.add(btnCrearDescuento);
		

		JButton btnEditarDescuento = new JButton("Editar Descuento");
		btnEditarDescuento.setFont(new Font("Consolas", Font.BOLD, 15));
		btnEditarDescuento.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEditarDescuento.setBounds(365, 143, 156, 33);
		contentPane.add(btnEditarDescuento);
		
		JButton btnMostrarDescuento = new JButton("Mostrar Descuentos");
		btnMostrarDescuento.setFont(new Font("Consolas", Font.BOLD, 15));
		btnMostrarDescuento.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnMostrarDescuento.setBounds(103, 217, 156, 33);
		contentPane.add(btnMostrarDescuento);

		JButton btnEliminarDescuento = new JButton("Eliminar Descuento");
		btnEliminarDescuento.setFont(new Font("Consolas", Font.BOLD, 15));
		btnEliminarDescuento.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEliminarDescuento.setBounds(365, 217, 156, 33);
		contentPane.add(btnEliminarDescuento);
	}
}
