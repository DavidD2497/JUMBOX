package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminSucursal extends JFrame {

    public AdminSucursal() {
        // Configuración básica de la ventana
        setTitle("Administración de Sucursal");
        setSize(709, 446);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1)); // 4 filas, 1 columna
        getContentPane().add(panel);
        
        // Botones de operaciones
        JButton btnCrearPedido = new JButton("Crear Pedido");
        JButton btnVerInventario = new JButton("Ver Inventario");
        JButton btnCrearDescuento = new JButton("Crear Descuento");
        panel.add(btnCrearDescuento);
        
        btnCrearDescuento.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
                // Acción para "Crear Descuento"
                JOptionPane.showMessageDialog(AdminSucursal.this, "Función: Crear Descuento");
            }
        });
        
        // Agregar los botones al panel
        panel.add(btnCrearPedido);
        JButton btnSalir = new JButton("Salir");
        panel.add(btnSalir);
        
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para "Salir"
                System.exit(0); // Cierra la aplicación
            }
        });
        panel.add(btnVerInventario);
        
        // Acciones de los botones
        btnCrearPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para "Crear Pedido"
                JOptionPane.showMessageDialog(AdminSucursal.this, "Función: Crear Pedido");
            }
        });
        
        btnVerInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para "Ver Inventario"
                JOptionPane.showMessageDialog(AdminSucursal.this, "Función: Ver Inventario");
            }
        });
    }

    public static void main(String[] args) {
        // Ejemplo de creación y visualización de la ventana
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AdminSucursal ventana = new AdminSucursal();
                ventana.setVisible(true);
            }
        });
    }
}
