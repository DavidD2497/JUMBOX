package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import controladores.*;
import modelos.*;

public class PantallaPedidoDeposito extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField inpCantidad;
    private int selectedRow = -1;
    private JTextField txtAdministradorDeposito;
    private JTextField txtPedido;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PantallaPedidoDeposito frame = new PantallaPedidoDeposito("user@example.com");
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
    public PantallaPedidoDeposito(String mail) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Consolas", Font.BOLD, 13));
        btnVolver.setBounds(342, 412, 99, 31);
        contentPane.add(btnVolver);
        
        txtAdministradorDeposito = new JTextField();
        txtAdministradorDeposito.setFont(new Font("Tahoma", Font.PLAIN, 26));
        txtAdministradorDeposito.setText("ADMINISTRADOR DEPOSITO");
        txtAdministradorDeposito.setBounds(212, 11, 351, 56);
        contentPane.add(txtAdministradorDeposito);
        txtAdministradorDeposito.setColumns(10);
        
        txtPedido = new JTextField();
        txtPedido.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtPedido.setText("Pedido");
        txtPedido.setColumns(10);
        txtPedido.setBounds(336, 78, 75, 43);
        contentPane.add(txtPedido);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaAdminDeposito admindeposito = new PantallaAdminDeposito(mail);
                admindeposito.setVisible(true);
                dispose();
            }
        });
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/vicky
