package vista;

import modelos.Empleado;
import modelos.Informe;
import modelos.DetalleInforme;
import controladores.EmpleadoControlador;
import controladores.InformeControlador;
import controladores.DetalleInformeControlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PantallaEliminarInforme extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel pedidoTableModel;
    private DefaultTableModel detalleTableModel;
    public static JTable pedidoTable;
    private JTable detalleTable;
    private JScrollPane detalleScrollPane;
    private JScrollPane pedidoScrollPane;
    private JLabel lblMensajeEliminarDetalles;
    private JLabel lblMensajeEliminarInforme;
    InformeControlador informeControlador = new InformeControlador();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PantallaLogueo frame = new PantallaLogueo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PantallaEliminarInforme(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 894, 563);
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        Empleado empleado = empleadoControlador.getUserByEmail(mail);

        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Tabla Informe");
        lblNewLabel_2.setBounds(185, 11, 496, 40);
        lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
        contentPane.add(lblNewLabel_2);

        pedidoTableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Id Informe", "Fecha de Informe"});
        cargarInformes();

        JLabel lblNewLabel_2_1 = new JLabel("Detalle del Informe");
        lblNewLabel_2_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Consolas", Font.BOLD, 28));
        lblNewLabel_2_1.setBounds(217, 233, 496, 40);
        lblNewLabel_2_1.setVisible(false);
        contentPane.add(lblNewLabel_2_1);

        pedidoTable = new JTable(pedidoTableModel);
        pedidoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pedidoTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pedidoTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = pedidoTable.getSelectedRow();
                if (selectedRow != -1) {
                    int idInforme = (int) pedidoTableModel.getValueAt(selectedRow, 0);
                    cargarDetallesInforme(idInforme);
                    detalleScrollPane.setVisible(true);
                    lblNewLabel_2_1.setVisible(true);
                }
            }
        });

        pedidoScrollPane = new JScrollPane(pedidoTable);
        pedidoScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        pedidoScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        pedidoScrollPane.setBounds(51, 62, 761, 149);
        contentPane.add(pedidoScrollPane);

        detalleTableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Id Detalle", "Tipo de registro", "Id Tipo"});
        detalleTable = new JTable(detalleTableModel);
        detalleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        detalleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));

        detalleScrollPane = new JScrollPane(detalleTable);
        detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        detalleScrollPane.setBounds(51, 284, 761, 167);
        detalleScrollPane.setVisible(false);
        contentPane.add(detalleScrollPane);

        lblMensajeEliminarDetalles = new JLabel("");
        lblMensajeEliminarDetalles.setForeground(new Color(0, 128, 0));
        lblMensajeEliminarDetalles.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMensajeEliminarDetalles.setBounds(244, 462, 400, 20);
        contentPane.add(lblMensajeEliminarDetalles);

        lblMensajeEliminarInforme = new JLabel("");
        lblMensajeEliminarInforme.setForeground(new Color(255, 0, 0));
        lblMensajeEliminarInforme.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMensajeEliminarInforme.setBounds(244, 462, 400, 20);
        contentPane.add(lblMensajeEliminarInforme);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
        btnVolver.setBounds(0, 0, 99, 31);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(e -> {
            PantallaInforme pantallaInforme = new PantallaInforme(mail);
            pantallaInforme.setVisible(true);
            dispose();
        });

        JButton btnEliminarDetalles = new JButton("Eliminar Detalles");
        btnEliminarDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = pedidoTable.getSelectedRow();
                if (selectedRow != -1) {
                    int idInforme = (int) pedidoTableModel.getValueAt(selectedRow, 0);
                    eliminarDetallesInforme(idInforme);
                }
            }
        });

        btnEliminarDetalles.setFont(new Font("Consolas", Font.BOLD, 13));
        btnEliminarDetalles.setBounds(244, 482, 177, 31);
        contentPane.add(btnEliminarDetalles);

        JButton btnEliminarInforme = new JButton("Eliminar Informe");
        btnEliminarInforme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = pedidoTable.getSelectedRow();
                if (selectedRow != -1) {
                    int idInforme = (int) pedidoTableModel.getValueAt(selectedRow, 0);
                    eliminarInforme(idInforme);
                }
            }
        });

        btnEliminarInforme.setFont(new Font("Consolas", Font.BOLD, 13));
        btnEliminarInforme.setBounds(454, 482, 184, 31);
        contentPane.add(btnEliminarInforme);

    }

    private void cargarInformes() {
        List<Informe> informes = informeControlador.getAllInformes();
        pedidoTableModel.setRowCount(0);
        for (Informe informe : informes) {
            pedidoTableModel.addRow(new Object[]{informe.getIdInforme(), informe.getFechaInforme()});
        }
    }

    private void cargarDetallesInforme(int idInforme) {
        DetalleInformeControlador detalleInformeControlador = new DetalleInformeControlador();
        List<DetalleInforme> detalles = detalleInformeControlador.getAllDetalleInformesByInformeId(idInforme);
        detalleTableModel.setRowCount(0);
        for (DetalleInforme detalle : detalles) {
            detalleTableModel.addRow(new Object[]{detalle.getIdDetalle(), detalle.getTipo(), detalle.getIdTipo()});
        }
        ajustarAlturaDetalleTable();
    }

    private void eliminarDetallesInforme(int idInforme) {
        DetalleInformeControlador detalleInformeControlador = new DetalleInformeControlador();
        List<DetalleInforme> detalles = detalleInformeControlador.getAllDetalleInformesByInformeId(idInforme);

        for (DetalleInforme detalle : detalles) {
            detalleInformeControlador.deleteDetalleInforme(detalle.getIdDetalle());
        }

        lblMensajeEliminarDetalles.setText("Los detalles del informe fueron eliminados correctamente.");
        detalleTableModel.setRowCount(0); // Limpiar la tabla de detalles
        detalleScrollPane.setVisible(false); // Ocultar el JScrollPane si es necesario
    }

    private void eliminarInforme(int idInforme) {
        DetalleInformeControlador detalleInformeControlador = new DetalleInformeControlador();
        List<DetalleInforme> detalles = detalleInformeControlador.getAllDetalleInformesByInformeId(idInforme);

        if (!detalles.isEmpty()) {
            lblMensajeEliminarInforme.setText("Debes eliminar los detalles antes de eliminar el informe.");
        } else {
            informeControlador.deleteInforme(idInforme);
            cargarInformes(); // Recargar la tabla de informes después de la eliminación
            lblMensajeEliminarInforme.setText("El informe fue eliminado correctamente.");
        }
    }

    private void ajustarAlturaPedidoTable() {
        int rowCount = pedidoTableModel.getRowCount();
        int rowHeight = pedidoTable.getRowHeight();
        int tableHeight = rowCount * rowHeight + pedidoTable.getTableHeader().getHeight();
        pedidoScrollPane.setBounds(51, 62, 761, tableHeight + 20);
    }

    private void ajustarAlturaDetalleTable() {
        int rowCount = detalleTableModel.getRowCount();
        int rowHeight = detalleTable.getRowHeight();
        int tableHeight = rowCount * rowHeight;
        detalleScrollPane.setBounds(51, 273, 761, tableHeight + 22);
    }
}
