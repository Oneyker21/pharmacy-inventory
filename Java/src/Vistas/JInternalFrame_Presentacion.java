package Vistas;

import Controlador.CRUD_Presentacion;
import Modelo.Clase_Presentacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author diedr
 */
public class JInternalFrame_Presentacion extends javax.swing.JInternalFrame {

    public JInternalFrame_Presentacion() {
        initComponents();
        jTextField_id_presentacion.setEditable(false);
       setTitle("Presentacion");
        setFrameIcon(new ImageIcon("C:\\Users\\Diers\\OneDrive\\Escritorio\\CasoFarmacia\\Repositorio-Farmacia-Rosales.-main\\src\\Vistas_Iconos\\apothecary-IconoPequeño.png"));
        personalizarTitulosTabla();
        ajustarAlturaFilasTabla();
        colorearFilasTabla();

    }

    private void personalizarTitulosTabla() {
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) jTable_Presentacion.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Centra los títulos
        jTable_Presentacion.getTableHeader().setDefaultRenderer(headerRenderer);
        jTable_Presentacion.getTableHeader().setBackground(new Color(0, 255, 255)); // Cambia el color de los títulos
        jTable_Presentacion.getTableHeader().setForeground(Color.BLACK); // Cambia el color del texto de los títulos a negro
        Font headerFont = new Font("Segoe UI", Font.BOLD, 12); // Cambia el tamaño de letra de los títulos
        jTable_Presentacion.getTableHeader().setFont(headerFont);
        jTable_Presentacion.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void ajustarAlturaFilasTabla() {
        jTable_Presentacion.setRowHeight(35); // Ajusta aquí la altura deseada en píxeles
    }

    private void colorearFilasTabla() {
        jTable_Presentacion.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

    public void limpiar() {
        jTextField_id_presentacion.setText("");
        jTextField_Nombre_Presentacion.setText("");
        jTextArea_Detalle.setText("");
    }

    public void GuardarPresentacion() {
        CRUD_Presentacion cc = new CRUD_Presentacion();
        String Nombre_Presentacion = jTextField_Nombre_Presentacion.getText();
        String Detalle = jTextArea_Detalle.getText();

        Clase_Presentacion c1 = new Clase_Presentacion(Nombre_Presentacion, Detalle);
        cc.Guardar(c1);
    }

    public void mostrar() {
        try {
            DefaultTableModel modelo;
            CRUD_Presentacion cli = new CRUD_Presentacion();
            modelo = cli.mostrarDatos();
            jTable_Presentacion.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void BuscarPresentacion() {
        try {
            DefaultTableModel modelo;
            CRUD_Presentacion cli = new CRUD_Presentacion();
            modelo = cli.buscarDatos(jTextField_Buscar.getText());

            if (modelo != null) {
                jTable_Presentacion.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Presentacion = new javax.swing.JTable();
        jTextField_Buscar = new javax.swing.JTextField();
        jTextField_Nombre_Presentacion = new javax.swing.JTextField();
        jTextField_id_presentacion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Detalle = new javax.swing.JTextArea();
        jButton_Agregar = new javax.swing.JButton();
        jButton_Editar = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jTable_Presentacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id Presentacion", "Nombre de presentación", "Detalle"
            }
        ));
        jTable_Presentacion.setGridColor(new java.awt.Color(0, 153, 153));
        jTable_Presentacion.setShowGrid(true);
        jTable_Presentacion.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable_Presentacion(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(jTable_Presentacion);

        jTextField_Buscar.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Buscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTextField_Buscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_BuscarMouseClicked(evt);
            }
        });
        jTextField_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Buscar(evt);
            }
        });
        jTextField_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_BuscarKeyReleased(evt);
            }
        });

        jTextField_Nombre_Presentacion.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Nombre_Presentacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Nombre_Presentacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Nombre_Presentacion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Nombre_Presentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Nombre_Presentacion(evt);
            }
        });

        jTextField_id_presentacion.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_id_presentacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_id_presentacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_id_presentacion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_id_presentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_id_presentacionActionPerformed(evt);
            }
        });

        jTextArea_Detalle.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea_Detalle.setColumns(20);
        jTextArea_Detalle.setRows(5);
        jTextArea_Detalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextArea_Detalle.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTextArea_Detalle(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(jTextArea_Detalle);

        jButton_Agregar.setBackground(new java.awt.Color(51, 255, 51));
        jButton_Agregar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Agregar.setText("Agregar");
        jButton_Agregar.setBorder(null);
        jButton_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agregar(evt);
            }
        });

        jButton_Editar.setBackground(new java.awt.Color(255, 255, 51));
        jButton_Editar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Editar.setText("Editar");
        jButton_Editar.setBorder(null);
        jButton_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Editar(evt);
            }
        });

        jButton_Actualizar.setBackground(new java.awt.Color(102, 204, 255));
        jButton_Actualizar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Actualizar.setText("Actualizar");
        jButton_Actualizar.setBorder(null);
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Actualizar(evt);
            }
        });

        jButton_Eliminar.setBackground(new java.awt.Color(255, 102, 102));
        jButton_Eliminar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Eliminar.setText("Eliminar");
        jButton_Eliminar.setBorder(null);
        jButton_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Eliminar(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField_id_presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jTextField_Nombre_Presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_id_presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Nombre_Presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_Presentacion(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable_Presentacion
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_Presentacion

    private void jTextField_BuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_BuscarMouseClicked
        jTextField_Buscar.setText("");
        jTextField_Buscar.setForeground(Color.black);
    }//GEN-LAST:event_jTextField_BuscarMouseClicked

    private void jTextField_Buscar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Buscar

    }//GEN-LAST:event_jTextField_Buscar

    private void jTextField_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarKeyReleased
        BuscarPresentacion();
    }//GEN-LAST:event_jTextField_BuscarKeyReleased

    private void jTextField_id_presentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_id_presentacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_id_presentacionActionPerformed

    private void jButton_Agregar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agregar
        CRUD_Presentacion c1 = new CRUD_Presentacion();
        try {
            if (jTextField_Nombre_Presentacion.getText().isEmpty() || jTextArea_Detalle.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tiene datos vacíos");
            } else {
                if (c1.verificarDatos(jTextField_Nombre_Presentacion.getText())) {
                    JOptionPane.showMessageDialog(null, "Ya existe esta presentación");
                } else {
                    int option = JOptionPane.showOptionDialog(
                            null,
                            "Desea agregar la presentacion?",
                            "Agregar Presentacion",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            new ImageIcon(getClass().getResource("/Vistas_Iconos/agregar.png")),
                            new Object[]{"Sí", "No"},
                            "No"
                    );

                    if (option == JOptionPane.YES_OPTION) {
                        GuardarPresentacion();
                        limpiar();
                        mostrar();

                        JPanel panel = new JPanel();
                        panel.setLayout(new BorderLayout());

                        JLabel messageLabel = new JLabel("Presentacion agregada correctamente");
                        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
                        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        panel.add(messageLabel, BorderLayout.CENTER);

                        ImageIcon icon = new ImageIcon(getClass().getResource("/Vistas_Iconos/agregar.png"));
                        JLabel iconLabel = new JLabel(icon);
                        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        panel.add(iconLabel, BorderLayout.WEST);

                        JOptionPane.showMessageDialog(null, panel, "Agregado Exitoso", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }


    }//GEN-LAST:event_jButton_Agregar

    private void jButton_Editar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Editar
        int filaSeleccionada = jTable_Presentacion.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla para editar");
        } else {
            String id_Prese = jTable_Presentacion.getValueAt(filaSeleccionada, 0) != null ? jTable_Presentacion.getValueAt(filaSeleccionada, 0).toString() : "";
            String nombre = jTable_Presentacion.getValueAt(filaSeleccionada, 1) != null ? jTable_Presentacion.getValueAt(filaSeleccionada, 1).toString() : "";
            String Detalle = jTable_Presentacion.getValueAt(filaSeleccionada, 2) != null ? jTable_Presentacion.getValueAt(filaSeleccionada, 2).toString() : "";

            // Asignar los valores a los campos de texto y área de texto
            jTextField_id_presentacion.setText(id_Prese);
            jTextField_Nombre_Presentacion.setText(nombre);
            jTextArea_Detalle.setText(Detalle);

            // Desactivar la edición del campo de texto para el ID de la categoría
            jTextField_id_presentacion.setEditable(false);
        }
    }//GEN-LAST:event_jButton_Editar

    private void jButton_Actualizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Actualizar
        String idpresenText = jTextField_id_presentacion.getText();
        int idCliente = Integer.parseInt(idpresenText);
        String nombre = jTextField_Nombre_Presentacion.getText();
        String Detalle = jTextArea_Detalle.getText();

        Clase_Presentacion Presentacion = new Clase_Presentacion(idCliente, nombre, Detalle);

        int option = JOptionPane.showOptionDialog(
                null,
                "¿Desea actualizar la presentación?",
                "Confirmar Actualización",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(getClass().getResource("/Vistas_Iconos/actualizar.png")),
                new Object[]{"Sí", "No"},
                "No"
        );

        if (option == JOptionPane.YES_OPTION) {
            CRUD_Presentacion Crud_presentacion = new CRUD_Presentacion();
            Crud_presentacion.actualizar(Presentacion);
            mostrar();

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JLabel messageLabel = new JLabel("Presentación actualizada exitosamente.");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
            messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(messageLabel, BorderLayout.CENTER);

            ImageIcon icon = new ImageIcon(getClass().getResource("/Vistas_Iconos/actualizar.png"));
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(iconLabel, BorderLayout.WEST);

            JOptionPane.showMessageDialog(null, panel, "Actualización Exitosa", JOptionPane.PLAIN_MESSAGE);
            limpiar();
        }

    }//GEN-LAST:event_jButton_Actualizar

    private void jButton_Eliminar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Eliminar
        int selectedRow = jTable_Presentacion.getSelectedRow();
        if (selectedRow != -1) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/Vistas_Iconos/eliminar (2).png"));
            int option = JOptionPane.showOptionDialog(
                    null,
                    "Se eliminará el registro, ¿desea continuar?",
                    "Eliminar Registro",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    icon,
                    new Object[]{"Sí", "No"},
                    "No"
            );

            if (option == JOptionPane.YES_OPTION) {
                String idCategoriaString = jTable_Presentacion.getValueAt(selectedRow, 0).toString();
                int idCategoria = Integer.parseInt(idCategoriaString);

                CRUD_Presentacion cli = new CRUD_Presentacion();
                cli.eliminar(idCategoria);
                mostrar();

                JPanel resultPanel = new JPanel();
                resultPanel.setLayout(new BorderLayout());

                JLabel resultLabel = new JLabel("Presentación eliminada correctamente");
                resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
                resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                resultPanel.add(resultLabel, BorderLayout.CENTER);

                JLabel resultIconLabel = new JLabel(icon);
                resultIconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                resultPanel.add(resultIconLabel, BorderLayout.WEST);

                JOptionPane.showMessageDialog(null, resultPanel, "Eliminación Exitosa", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar la Presentacion");
            }

        }


    }//GEN-LAST:event_jButton_Eliminar

    private void jTextField_Nombre_Presentacion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Nombre_Presentacion
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Nombre_Presentacion

    private void jTextArea_Detalle(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTextArea_Detalle

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea_Detalle


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_Editar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Presentacion;
    private javax.swing.JTextArea jTextArea_Detalle;
    private javax.swing.JTextField jTextField_Buscar;
    private javax.swing.JTextField jTextField_Nombre_Presentacion;
    private javax.swing.JTextField jTextField_id_presentacion;
    // End of variables declaration//GEN-END:variables
}
