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
import modelos.AdminSucursal;
import modelos.Descuento;

public class PantallaEditarDescuentos extends JFrame {
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
                    PantallaEditarDescuentos frame = new PantallaEditarDescuentos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PantallaEditarDescuentos() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        contentPane = new ImagePanel("/resources/supermercado.jpg");
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEditarDescuento = new JLabel("Editar Descuentos");
        lblEditarDescuento.setBounds(143, 11, 304, 40);
        lblEditarDescuento.setHorizontalTextPosition(SwingConstants.CENTER);
        lblEditarDescuento.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditarDescuento.setFont(new Font("Consolas", Font.BOLD, 28));
        contentPane.add(lblEditarDescuento);

        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "ID Dto.", "Porcentaje Dto.", "ID producto" });
        cargarDescuentos();

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                selectedRow = table.getSelectedRow();
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

        JButton btnEditar = new JButton("EDITAR");
        btnEditar.setBackground(new Color(255, 255, 255));
        btnEditar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnEditar.setBounds(42, 301, 99, 31);
        contentPane.add(btnEditar);

        btnVolver.addActionListener(e -> {
            PantallaDescuentos pantallaDescuentos = new PantallaDescuentos();
            pantallaDescuentos.setVisible(true);
            dispose();
        });

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione un descuento para editar");
                    return;
                }

                // Obtener los valores de la fila seleccionada
                int idDescuento = (int) table.getValueAt(selectedRow, 0);
                String nuevoPorcentajeStr = JOptionPane.showInputDialog("Ingrese el nuevo porcentaje de descuento:");
                int nuevoPorcentaje = Integer.parseInt(nuevoPorcentajeStr);
                if (nuevoPorcentajeStr == null || nuevoPorcentajeStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El porcentaje no puede estar vacío");
                    return;
                }
                if (nuevoPorcentaje >= 101 || nuevoPorcentaje <= 0) {
                    JOptionPane.showMessageDialog(null, "El porcentaje no puede ser mayor a 100 o menor o igual a 0");
                    return;
                }
                
                try {
                    int idProducto=(int)table.getValueAt(selectedRow,2);
                    DescuentoControlador descuentoControlador = new DescuentoControlador();
                    Descuento descuento=new Descuento(idDescuento,nuevoPorcentaje,idProducto);
                    descuentoControlador.updateDescuento(descuento);
                    cargarDescuentos();
                    JOptionPane.showMessageDialog(null, "El descuento fue actualizado correctamente");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un porcentaje válido");
                }
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
