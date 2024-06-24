package vista;

import modelos.Empleado;
import modelos.Informe;
import modelos.AdminSucursal;
import modelos.DetalleInforme;
import controladores.EmpleadoControlador;
import controladores.InformeControlador;
import controladores.DetalleInformeControlador;
import controladores.ProductoControlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PantallaInforme extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel pedidoTableModel;
    private DefaultTableModel detalleTableModel;
    public static JTable pedidoTable;
    private JTable detalleTable;
    private JScrollPane detalleScrollPane;
    InformeControlador informeControlador = new InformeControlador();
    private JScrollPane pedidoScrollPane;

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
    public PantallaInforme(String mail) {
        JLabel lblNewLabel_2_1 = new JLabel("Tabla Informe");
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

        JLabel lblNewLabel_2 = new JLabel("Tabla Informe");
        lblNewLabel_2.setBounds(185, 11, 496, 40);
        lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 28));
        contentPane.add(lblNewLabel_2);

        pedidoTableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Id Informe", "Fecha de Informe"});
        cargarInformes();
        JLabel lblNewLabel_3 = new JLabel("Detalle del Pedido");
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
                    devolverIdInforme(idInforme);
                }
            }
        });

        pedidoScrollPane = new JScrollPane(pedidoTable);
        pedidoScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        pedidoScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        pedidoScrollPane.setBounds(51, 62, 761, 149);
        contentPane.add(pedidoScrollPane);

        detalleTableModel = new DefaultTableModel(new Object[][]{},
                new String[]{"Id Detalle", "Tipo de registro", "Id Tipo"});

        detalleTable = new JTable(detalleTableModel);
        detalleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        detalleTable.setFont(new Font("Tahoma", Font.PLAIN, 14));

        detalleScrollPane = new JScrollPane(detalleTable);
        detalleScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        detalleScrollPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        detalleScrollPane.setBounds(51, 270, 761, 167);
        detalleScrollPane.setVisible(false); // Inicialmente invisible
        contentPane.add(detalleScrollPane);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
        btnVolver.setBounds(0, 0, 99, 31);
        contentPane.add(btnVolver);

        lblNewLabel_2_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Consolas", Font.BOLD, 28));
        lblNewLabel_2_1.setBounds(217, 233, 496, 40);
        lblNewLabel_2_1.setVisible(false);
        contentPane.add(lblNewLabel_2_1);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaEliminarInforme pantallaEliminar = new PantallaEliminarInforme(mail);
                pantallaEliminar.setVisible(true);
                dispose();
            }
        });

        btnEliminar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnEliminar.setBounds(542, 482, 99, 31);
        contentPane.add(btnEliminar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaEditarTipo pantallaEditarTipo = new PantallaEditarTipo(mail);
                pantallaEditarTipo.setVisible(true);
                dispose();
            }
        });

        btnEditar.setFont(new Font("Consolas", Font.BOLD, 13));
        btnEditar.setBounds(406, 482, 99, 31);
        contentPane.add(btnEditar);

        JButton btnCrear = new JButton("Crear");
        btnCrear.setFont(new Font("Consolas", Font.BOLD, 13));
        btnCrear.setBounds(275, 482, 99, 31);
        contentPane.add(btnCrear);

        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminSucursal.crearInforme();
                System.out.println("Se agregó exitosamente");

                int idInforme = informeControlador.obtenerUltimoIdInforme();
                pedidoTableModel.addRow(new Object[]{informeControlador.getInformeById(idInforme).getIdInforme(), informeControlador.getInformeById(idInforme).getFechaInforme()});
            }
        });

        btnVolver.addActionListener(e -> {
            PantallaAdminSucursal pantallaAdminSucursal = new PantallaAdminSucursal(mail);
            pantallaAdminSucursal.setVisible(true);
            dispose();
        });

        // Botones para filtrar tabla de detalles
        JButton btnFiltrarDetalleAbc = new JButton("Filtrar Detalle (ABC)");
        btnFiltrarDetalleAbc.setFont(new Font("Consolas", Font.BOLD, 13));
        btnFiltrarDetalleAbc.setBounds(51, 448, 194, 31);
        contentPane.add(btnFiltrarDetalleAbc);

        JButton btnFiltrarDetalleNum = new JButton("Filtrar Detalle (Num)");
        btnFiltrarDetalleNum.setFont(new Font("Consolas", Font.BOLD, 13));
        btnFiltrarDetalleNum.setBounds(51, 482, 194, 31);
        contentPane.add(btnFiltrarDetalleNum);
        btnFiltrarDetalleAbc.addActionListener(e -> filtrarDetalleTablaABC());
        btnFiltrarDetalleNum.addActionListener(e -> filtrarDetalleTablaNum());
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
        ProductoControlador productoControlador = new ProductoControlador();
        detalleTableModel.setRowCount(0);
        for (DetalleInforme detalle : detalles) {
            detalleTableModel.addRow(new Object[]{detalle.getIdDetalle(), detalle.getTipo(), detalle.getIdTipo()});
        }
        this.ajustarAlturaDetalleTable();
    }

    private void ajustarAlturaPedidoTable() {
        int rowCount = pedidoTableModel.getRowCount();
        int rowHeight = pedidoTable.getRowHeight();
        int tableHeight = rowCount * rowHeight + pedidoTable.getTableHeader().getHeight();
        pedidoScrollPane.setBounds(51, 62, 761, tableHeight + 20); // Ajustar la altura de la tabla de pedidos
    }

    int devolverIdInforme(int id) {
        return id;
    }

    private void ajustarAlturaDetalleTable() {
        int rowCount = detalleTableModel.getRowCount();
        int rowHeight = detalleTable.getRowHeight();
        int tableHeight = rowCount * rowHeight;
        detalleScrollPane.setBounds(51, 273, 761, tableHeight + 22); // 22 es para el header
    }

    private void filtrarPedidoTablaABC() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(pedidoTableModel);
        pedidoTable.setRowSorter(sorter);
        sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
    }

    private void filtrarPedidoTablaNum() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(pedidoTableModel);
        pedidoTable.setRowSorter(sorter);
        sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
    }

    private void filtrarDetalleTablaABC() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(detalleTableModel);
        detalleTable.setRowSorter(sorter);
        sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
    }

    private void filtrarDetalleTablaNum() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(detalleTableModel);
        detalleTable.setRowSorter(sorter);
        sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
    }
}
