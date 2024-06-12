package vista;

import modelos.Empleado;
import modelos.Pedido;
import controladores.EmpleadoControlador;
import controladores.PedidoControlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PantallaMostrarPedido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel tableModel;
    private int selectedRow = -1;
    private JTable table;

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
    public PantallaMostrarPedido(String mail) {
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

        JLabel lblNewLabel_2 = new JLabel("Mostrar Pedidos");
        lblNewLabel_2.setBounds(185, 11, 496, 40);
        lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
        contentPane.add(lblNewLabel_2);

       
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id Pedido", "Fecha de Entrega"}
        );
        cargarPedidos();

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        scrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        scrollPane.setBounds(51, 80, 761, 177);
        contentPane.add(scrollPane);

        

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
        btnVolver.setBounds(413, 462, 99, 31);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(e -> {
            PantallaAdminSucursal adminsucursal = new PantallaAdminSucursal(mail);
            adminsucursal.setVisible(true);
            dispose();
        });
    }

    private void cargarPedidos() {
        PedidoControlador pedidoControlador = new PedidoControlador();
        List<Pedido> pedidos = pedidoControlador.getAllPedidos();
        tableModel.setRowCount(0);
        for (Pedido pedido : pedidos) {
            tableModel.addRow(new Object[]{
                    pedido.getCodigoPedido(),
                    pedido.getFechaEntrega()
            });
        }
    }
}
