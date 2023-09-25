package Vistas;

import Controlador.CRUD_Compra_Producto;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author diedr
 */
public class JInternalFrame_Compra extends javax.swing.JInternalFrame {

    public JInternalFrame_Compra() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Compras Realizada");
        setFrameIcon(new ImageIcon("C:\\Users\\Diers\\OneDrive\\Escritorio\\CasoFarmacia\\Repositorio-Farmacia-Rosales.-main\\src\\Vistas_Iconos\\apothecary-IconoPequeño.png"));

        colorearFilasTabla();
        personalizarTitulosTabla();
        ajustarAlturaFilasTabla();

    }

    private void personalizarTitulosTabla() {
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) jTable_Compra.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Centra los títulos
        jTable_Compra.getTableHeader().setDefaultRenderer(headerRenderer);
        jTable_Compra.getTableHeader().setBackground(new Color(0, 255, 255)); // Cambia el color de los títulos
        jTable_Compra.getTableHeader().setForeground(Color.BLACK); // Cambia el color del texto de los títulos a negro
        Font headerFont = new Font("Segoe UI", Font.BOLD, 12); // Cambia el tamaño de letra de los títulos
        jTable_Compra.getTableHeader().setFont(headerFont);
        jTable_Compra.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void ajustarAlturaFilasTabla() {
        jTable_Compra.setRowHeight(35); // Ajusta aquí la altura deseada en píxeles
    }

    private void colorearFilasTabla() {
        jTable_Compra.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(new Color(204, 255, 255));
                } else {
                    setBackground(new Color(204, 255, 255));
                }
                setHorizontalAlignment(SwingConstants.CENTER); // Centramos el contenido
                return this;
            }
        });
    }

    public void mostrar() {
        try {
            DefaultTableModel modelo;
            CRUD_Compra_Producto ventaProductoDAO = new CRUD_Compra_Producto();
            modelo = ventaProductoDAO.mostrarDatosCompra();
            jTable_Compra.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void buscarCompraYProducto() {
        try {
            String busqueda = jTextField_Buscar.getText();

            DefaultTableModel modelo = (DefaultTableModel) jTable_Compra.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
            jTable_Compra.setRowSorter(sorter);

            if (busqueda.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + busqueda);
                sorter.setRowFilter(rf);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Método para aplicar el borde doblado a un botón
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Compra = new javax.swing.JTable();
        jTextField_Buscar = new javax.swing.JTextField();
        jButton_Borrar = new javax.swing.JButton();
        jButton_Agregar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_Compra.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jTable_Compra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Compra", "Id Proveedor", "Fecha", "Total"
            }
        ));
        jTable_Compra.setGridColor(new java.awt.Color(0, 153, 153));
        jTable_Compra.setShowGrid(true);
        jTable_Compra.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable_Compra(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(jTable_Compra);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1120, 330));

        jTextField_Buscar.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Buscar.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField_Buscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_BuscarActionPerformed(evt);
            }
        });
        jTextField_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_BuscarKeyReleased(evt);
            }
        });
        jPanel1.add(jTextField_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 330, 45));

        jButton_Borrar.setBackground(new java.awt.Color(255, 102, 102));
        jButton_Borrar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Borrar.setText("Eliminar");
        jButton_Borrar.setBorder(null);
        jButton_Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BorrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_Borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 80, 30));

        jButton_Agregar.setBackground(new java.awt.Color(51, 255, 51));
        jButton_Agregar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Agregar.setText("Agregar");
        jButton_Agregar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AgregarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1162, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_BuscarActionPerformed

    private void jButton_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AgregarActionPerformed
       Container container = getParent();
    if (container instanceof JDesktopPane) {
        JDesktopPane desktopPane = (JDesktopPane) container;

        // Buscar el formulario JInternalFrame_Compra_Producto en el JDesktopPane
        JInternalFrame_Compra_Producto compraProductoForm = null;
        JInternalFrame[] frames = desktopPane.getAllFrames();
        for (JInternalFrame frame : frames) {
            if (frame instanceof JInternalFrame_Compra_Producto) {
                compraProductoForm = (JInternalFrame_Compra_Producto) frame;
                break;
            }
        }

        if (compraProductoForm != null) {
            // Si el formulario ya está abierto, selecciónalo
            try {
                compraProductoForm.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        } else {
            // Si el formulario no está abierto, crea una nueva instancia y añádela al JDesktopPane
            compraProductoForm = new JInternalFrame_Compra_Producto();
            desktopPane.add(compraProductoForm);

            // Centrar el formulario en el JDesktopPane
            Dimension desktopSize = desktopPane.getSize();
            Dimension frameSize = compraProductoForm.getSize();
            int x = (desktopSize.width - frameSize.width) / 2;
            int y = (desktopSize.height - frameSize.height) / 2;
            compraProductoForm.setLocation(x, y);

            compraProductoForm.setVisible(true);
            try {
                compraProductoForm.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        }
    }
    }//GEN-LAST:event_jButton_AgregarActionPerformed

    private void jTable_Compra(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable_Compra
        mostrar();
    }//GEN-LAST:event_jTable_Compra

    private void jButton_BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BorrarActionPerformed

        int selectedRow = jTable_Compra.getSelectedRow();
        if (selectedRow != -1) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            JLabel messageLabel = new JLabel("Se eliminará la venta, ¿desea continuar?");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
            messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            ImageIcon icon = new ImageIcon(getClass().getResource("/Vistas_Iconos/eliminar (2).png"));
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            panel.add(iconLabel);
            panel.add(messageLabel);

            int option = JOptionPane.showOptionDialog(
                    null,
                    panel,
                    "Eliminar Venta",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    new Object[]{"Sí", "No"},
                    "No"
            );

            if (option == JOptionPane.YES_OPTION) {
                String idVentaString = jTable_Compra.getValueAt(selectedRow, 0).toString();
                int idVenta = Integer.parseInt(idVentaString);

                CRUD_Compra_Producto compraProductoDAO = new CRUD_Compra_Producto();
                compraProductoDAO.eliminarCompraYProducto(idVenta);
                mostrar();

                JPanel successPanel = new JPanel();
                successPanel.setLayout(new BorderLayout());

                JLabel successMessageLabel = new JLabel("Venta eliminada correctamente");
                successMessageLabel.setFont(new Font("Arial", Font.BOLD, 14));
                successMessageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                successPanel.add(successMessageLabel, BorderLayout.CENTER);

                ImageIcon successIcon = new ImageIcon(getClass().getResource("/Vistas_Iconos/eliminar (2).png"));
                JLabel successIconLabel = new JLabel(successIcon);
                successIconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                successPanel.add(successIconLabel, BorderLayout.WEST);

                JOptionPane.showMessageDialog(null, successPanel, "Eliminación Exitosa", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una venta");
        }
    }//GEN-LAST:event_jButton_BorrarActionPerformed

    private void jTextField_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarKeyReleased
        buscarCompraYProducto();
    }//GEN-LAST:event_jTextField_BuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_Borrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_Compra;
    private javax.swing.JTextField jTextField_Buscar;
    // End of variables declaration//GEN-END:variables
}
