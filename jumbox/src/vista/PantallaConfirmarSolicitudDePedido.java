package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controladores.PedidoControlador;
import controladores.DetallePedidoControlador;
import modelos.Pedido;
import modelos.DetallePedido;

public class PantallaConfirmarSolicitudDePedido extends JFrame {
	private JPanel contentPane;

	public PantallaConfirmarSolicitudDePedido(String mail, int idPedido) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 393);
		contentPane = new ImagePanel("/resources/supermercado.jpg");
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConfirmarSolicitud = new JLabel("Confirmar solicitud de pedido");
		lblConfirmarSolicitud.setBounds(94, 11, 496, 40);
		lblConfirmarSolicitud.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConfirmarSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmarSolicitud.setFont(new Font("Consolas", Font.BOLD, 28));
		contentPane.add(lblConfirmarSolicitud);

		JTextArea textAreaPedido = new JTextArea();
		textAreaPedido.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textAreaPedido);
		scrollPane.setBounds(50, 70, 600, 200);
		contentPane.add(scrollPane);

		PedidoControlador pedidoControlador = new PedidoControlador();
		Pedido pedido = pedidoControlador.getPedidoById(idPedido);
		DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();
		java.util.List<DetallePedido> detallesPedido = detallePedidoControlador.getDetallePedidoByIdPedido(idPedido);

		StringBuilder mensaje = new StringBuilder("Información del Pedido:\n");
		for (DetallePedido detalle : detallesPedido) {
			mensaje.append("ID Producto: ").append(detalle.getIdProducto()).append("\n");
			mensaje.append("Cantidad: ").append(detalle.getCantidad()).append("\n");
			mensaje.append("ID Pedido: ").append(pedido.getCodigoPedido()).append("\n\n");
		}
		textAreaPedido.setText(mensaje.toString());

		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnConfirmar.setBounds(250, 313, 200, 30);
		contentPane.add(btnConfirmar);

		JButton btnRechazar = new JButton("RECHAZAR Y ELIMINAR");
		btnRechazar.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnRechazar.setBounds(25, 313, 215, 30);
		contentPane.add(btnRechazar);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnVolver.setBounds(460, 313, 200, 30);
		contentPane.add(btnVolver);

		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar este pedido?", "Confirmar pedido",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sí", "No" },
						null);
				if (respuesta == JOptionPane.YES_OPTION) {
					boolean confirmacion = SolicitudDePedido(idPedido);
					if (confirmacion) {
						JOptionPane.showMessageDialog(null, "El pedido ha sido aceptado");
					} else {
						JOptionPane.showMessageDialog(null, "El pedido no pudo ser aceptado");
					}
					dispose(); // Cierra la ventana actual después de confirmar
				} else {
					JOptionPane.showMessageDialog(null, "El pedido ha sido rechazado");
					dispose(); // Cierra la ventana actual después de rechazar
				}
			}
		});
		
		btnRechazar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int respuesta = JOptionPane.showOptionDialog(null, "¿Está seguro que desea rechazar y eliminar este pedido?", "Rechazar y Eliminar pedido",
		                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] { "Sí", "No" },
		                null);
		        if (respuesta == JOptionPane.YES_OPTION) {
		            PedidoControlador pedidoControlador = new PedidoControlador();
		            boolean eliminacionExitosa = pedidoControlador.deletePedido(idPedido);
		            if (eliminacionExitosa) {
		                JOptionPane.showMessageDialog(null, "El pedido ha sido rechazado y eliminado");
		            } else {
		                JOptionPane.showMessageDialog(null, "No se pudo eliminar el pedido");
		            }
		            dispose(); // Cierra la ventana actual después de eliminar
		        }
		    }
		});

		
		btnConfirmar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar este pedido?", "Confirmar pedido",
		                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sí", "No" },
		                null);
		        if (respuesta == JOptionPane.YES_OPTION) {
		            boolean confirmacion = SolicitudDePedido(idPedido);
		            if (confirmacion) {
		                JOptionPane.showMessageDialog(null, "El pedido ha sido aceptado");
		            } else {
		                JOptionPane.showMessageDialog(null, "El pedido no pudo ser aceptado");
		            }
		            dispose(); 
		        } else {
		            JOptionPane.showMessageDialog(null, "El pedido ha sido rechazado");
		            dispose(); 
		        }
		    }
		});

	}
	

	public static boolean SolicitudDePedido(int idPedido) {
		PedidoControlador pedidoControlador = new PedidoControlador();
		Pedido pedido = pedidoControlador.getPedidoById(idPedido);
		DetallePedidoControlador detallePedidoControlador = new DetallePedidoControlador();
		java.util.List<DetallePedido> detallesPedido = detallePedidoControlador.getDetallePedidoByIdPedido(idPedido);

		int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar este pedido?", "Confirmar pedido",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sí", "No" }, null);

		if (respuesta == JOptionPane.YES_OPTION) {
			if (respuesta == JOptionPane.YES_OPTION) {
				pedido.setEstado("Confirmado");
				pedidoControlador.updatePedido(pedido);

				return true;
			} else {
				pedidoControlador.deletePedido(idPedido);
				detallePedidoControlador.deleteDetallesByIdPedido(idPedido);
				return false;
			}
		}
		return false;
	}
	
	
}
