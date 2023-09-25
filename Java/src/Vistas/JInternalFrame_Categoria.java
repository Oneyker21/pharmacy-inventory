package Vistas;

import Controlador.CRUD_Categoria;
import Modelo.Clase_Categoria;
import Controlador_Conexion_DB.Conexion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
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
 *
 * @author diedr
 */
public class JInternalFrame_Categoria extends javax.swing.JInternalFrame {

    public final Conexion con = new Conexion();
    public final Connection cn = (Connection) con.conectar();
    private JPanel panel;

    public JInternalFrame_Categoria() {

        initComponents();
        jTextField_Id_Categoria.setEditable(false);
       setTitle("Categoria");
        setFrameIcon(new ImageIcon("C:\\Users\\Diers\\OneDrive\\Escritorio\\CasoFarmacia\\Repositorio-Farmacia-Rosales.-main\\src\\Vistas_Iconos\\apothecary-IconoPequeño.png"));
        personalizarTitulosTabla();
        ajustarAlturaFilasTabla();
        colorearFilasTabla();
        
    }

   private void personalizarTitulosTabla() {
    DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) jTable_Categoria.getTableHeader().getDefaultRenderer();
    headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Centra los títulos
    jTable_Categoria.getTableHeader().setDefaultRenderer(headerRenderer);
    jTable_Categoria.getTableHeader().setBackground(new Color(0, 255, 255)); // Cambia el color de los títulos
    jTable_Categoria.getTableHeader().setForeground(Color.BLACK); // Cambia el color del texto de los títulos a negro
    Font headerFont = new Font("Segoe UI", Font.BOLD, 12); // Cambia el tamaño de letra de los títulos
    jTable_Categoria.getTableHeader().setFont(headerFont);
    jTable_Categoria.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
}


    private void ajustarAlturaFilasTabla() {
        jTable_Categoria.setRowHeight(35); // Ajusta aquí la altura deseada en píxeles
    }

    private void colorearFilasTabla() {
        jTable_Categoria.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(new Color(204,255,255));
                } else {
                    setBackground(new Color(204,255,255));
                }
                setHorizontalAlignment(SwingConstants.CENTER); // Centramos el contenido
                return this;
            }
        });
    }

    public void mostrarCategoria() {
        try {
            DefaultTableModel modelo;
            CRUD_Categoria cli = new CRUD_Categoria();
            modelo = cli.mostrarDatos();
            jTable_Categoria.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    

    public void limpiar() {
        jTextField_Id_Categoria.setText("");
        jTextField_Nombre_Categoria.setText("");
        jTextArea_Descripcion_Categoria.setText("");
    }

    public void GuardarCategoria() {
        CRUD_Categoria cc = new CRUD_Categoria();
        String Nombre_Categoria = jTextField_Nombre_Categoria.getText();
        String Descripcion = jTextArea_Descripcion_Categoria.getText();

        Clase_Categoria c1 = new Clase_Categoria(Nombre_Categoria, Descripcion);
        cc.Guardar(c1);
    }

    public void BuscarCliente() {
        try {
            DefaultTableModel modelo;
            CRUD_Categoria cli = new CRUD_Categoria();
            modelo = cli.buscarDatos(jTextField_Buscar.getText());

            if (modelo != null) {
                jTable_Categoria.setModel(modelo);
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
        jTable_Categoria = new javax.swing.JTable();
        jTextField_Id_Categoria = new javax.swing.JTextField();
        jTextField_Buscar = new javax.swing.JTextField();
        jTextField_Nombre_Categoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Descripcion_Categoria = new javax.swing.JTextArea();
        jButton_Agregar_Categoria = new javax.swing.JButton();
        jButton_Editar_categoria = new javax.swing.JButton();
        jButton_Actualizar_Categoria = new javax.swing.JButton();
        jButton_Eliminar_Categoria = new javax.swing.JButton();

        setClosable(true);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jTable_Categoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id Categoria", "Nombre de categoria", "Descripción"
            }
        ));
        jTable_Categoria.setGridColor(new java.awt.Color(0, 153, 153));
        jTable_Categoria.setShowGrid(true);
        jTable_Categoria.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable_Categoria(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(jTable_Categoria);

        jTextField_Id_Categoria.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Id_Categoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Id_Categoria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Id_Categoria.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Id_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Id_Categoria(evt);
            }
        });

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

        jTextField_Nombre_Categoria.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Nombre_Categoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Nombre_Categoria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre de Categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Nombre_Categoria.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Nombre_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Nombre_Categoria(evt);
            }
        });
        jTextField_Nombre_Categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Nombre_CategoriaKeyTyped(evt);
            }
        });

        jTextArea_Descripcion_Categoria.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea_Descripcion_Categoria.setColumns(20);
        jTextArea_Descripcion_Categoria.setRows(5);
        jTextArea_Descripcion_Categoria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripcion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextArea_Descripcion_Categoria.setMinimumSize(new java.awt.Dimension(11, 45));
        jTextArea_Descripcion_Categoria.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTextArea_Descripcion_Categoria(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTextArea_Descripcion_Categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea_Descripcion_CategoriaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea_Descripcion_Categoria);

        jButton_Agregar_Categoria.setBackground(new java.awt.Color(51, 255, 51));
        jButton_Agregar_Categoria.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Agregar_Categoria.setText("Agregar");
        jButton_Agregar_Categoria.setBorder(null);
        jButton_Agregar_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agregar_Categoria(evt);
            }
        });

        jButton_Editar_categoria.setBackground(new java.awt.Color(255, 255, 51));
        jButton_Editar_categoria.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Editar_categoria.setText("Editar");
        jButton_Editar_categoria.setBorder(null);
        jButton_Editar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Editar_categoria(evt);
            }
        });

        jButton_Actualizar_Categoria.setBackground(new java.awt.Color(51, 204, 255));
        jButton_Actualizar_Categoria.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Actualizar_Categoria.setText("Actualizar");
        jButton_Actualizar_Categoria.setBorder(null);
        jButton_Actualizar_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Actualizar_Categoria(evt);
            }
        });

        jButton_Eliminar_Categoria.setBackground(new java.awt.Color(255, 102, 102));
        jButton_Eliminar_Categoria.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Eliminar_Categoria.setText("Eliminar");
        jButton_Eliminar_Categoria.setBorder(null);
        jButton_Eliminar_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Eliminar_Categoria(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField_Id_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_Nombre_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jButton_Agregar_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Editar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Actualizar_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Eliminar_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_Id_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Nombre_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Eliminar_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Agregar_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Actualizar_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_Editar_categoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_Eliminar_Categoria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Eliminar_Categoria
        int selectedRow = jTable_Categoria.getSelectedRow();
        if (selectedRow != -1) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            JLabel messageLabel = new JLabel("Se eliminará el registro, ¿desea continuar?");
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
                "Eliminar Registro",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new Object[]{"Sí", "No"},
                "No"
            );

            if (option == JOptionPane.YES_OPTION) {
                String idCategoriaString = jTable_Categoria.getValueAt(selectedRow, 0).toString();
                int idCategoria = Integer.parseInt(idCategoriaString);

                CRUD_Categoria cli = new CRUD_Categoria();
                cli.eliminar(idCategoria);
                mostrarCategoria();

                JPanel successPanel = new JPanel();
                successPanel.setLayout(new BorderLayout());

                JLabel successMessageLabel = new JLabel("Categoría eliminada correctamente");
                successMessageLabel.setFont(new Font("Arial", Font.BOLD, 14));
                successMessageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                successPanel.add(successMessageLabel, BorderLayout.CENTER);

                ImageIcon successIcon = new ImageIcon(getClass().getResource("/Vistas_Iconos/eliminar (2).png"));
                JLabel successIconLabel = new JLabel(successIcon);
                successIconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                successPanel.add(successIconLabel, BorderLayout.WEST);

                JOptionPane.showMessageDialog(null, successPanel, "Eliminación Exitosa", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar la categoría");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la fila");
        }

    }//GEN-LAST:event_jButton_Eliminar_Categoria

    private void jButton_Actualizar_Categoria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Actualizar_Categoria
        String idCategoriaText = jTextField_Id_Categoria.getText();
        int idCategoria = Integer.parseInt(idCategoriaText);
        String nombre = jTextField_Nombre_Categoria.getText();
        String descripcion = jTextArea_Descripcion_Categoria.getText();

        Clase_Categoria categoria = new Clase_Categoria(idCategoria, nombre, descripcion);

        int option = JOptionPane.showOptionDialog(
            null,
            "¿Desea actualizar la categoría?",
            "Confirmar Actualización",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            new ImageIcon(getClass().getResource("/Vistas_Iconos/actualizar.png")),
            new Object[]{"Sí", "No"},
            "No"
        );

        if (option == JOptionPane.YES_OPTION) {
            CRUD_Categoria crudCategoria = new CRUD_Categoria();
            crudCategoria.actualizar(categoria);
            mostrarCategoria();

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JLabel messageLabel = new JLabel("Categoría actualizada exitosamente.");
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

    }//GEN-LAST:event_jButton_Actualizar_Categoria

    private void jButton_Editar_categoria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Editar_categoria
        int filaSeleccionada = jTable_Categoria.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla para editar");
        } else {
            String id_Cate = jTable_Categoria.getValueAt(filaSeleccionada, 0) != null ? jTable_Categoria.getValueAt(filaSeleccionada, 0).toString() : "";
            String nombre = jTable_Categoria.getValueAt(filaSeleccionada, 1) != null ? jTable_Categoria.getValueAt(filaSeleccionada, 1).toString() : "";
            String Descrip = jTable_Categoria.getValueAt(filaSeleccionada, 2) != null ? jTable_Categoria.getValueAt(filaSeleccionada, 2).toString() : "";

            jTextField_Id_Categoria.setText(id_Cate);
            jTextField_Nombre_Categoria.setText(nombre);
            jTextArea_Descripcion_Categoria.setText(Descrip);

            jTextField_Id_Categoria.setEditable(false);
        }
    }//GEN-LAST:event_jButton_Editar_categoria

    private void jButton_Agregar_Categoria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agregar_Categoria
        CRUD_Categoria c1 = new CRUD_Categoria();
        try {
            if (jTextField_Nombre_Categoria.getText().isEmpty() || jTextArea_Descripcion_Categoria.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tiene datos vacíos");
            } else {
                int option = JOptionPane.showOptionDialog(
                    null,
                    "¿Desea guardar la Categoría?",
                    "Confirmar Guardado",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(getClass().getResource("/Vistas_Iconos/agregar.png")),
                    new Object[]{"Sí", "No"},
                    "No"
                );

                if (c1.verificarDatos(jTextField_Nombre_Categoria.getText())) {
                    JOptionPane.showMessageDialog(null, "Ya existe esta categoría");
                }

                if (option == JOptionPane.YES_OPTION) {
                    GuardarCategoria();
                    limpiar();

                    JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());

                    JLabel messageLabel = new JLabel("Datos Guardados Correctamente");
                    messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    panel.add(messageLabel, BorderLayout.CENTER);

                    ImageIcon icon = new ImageIcon(getClass().getResource("/Vistas_Iconos/agregar.png"));
                    JLabel iconLabel = new JLabel(icon);
                    iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    panel.add(iconLabel, BorderLayout.WEST);

                    JOptionPane.showMessageDialog(null, panel, "Guardado Exitoso", JOptionPane.PLAIN_MESSAGE);

                    mostrarCategoria();
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

    }//GEN-LAST:event_jButton_Agregar_Categoria

    private void jTextArea_Descripcion_CategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea_Descripcion_CategoriaKeyTyped
        String texto = jTextArea_Descripcion_Categoria.getText();

        if (texto.length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextArea_Descripcion_CategoriaKeyTyped

    private void jTextArea_Descripcion_Categoria(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTextArea_Descripcion_Categoria
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea_Descripcion_Categoria

    private void jTextField_Nombre_CategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Nombre_CategoriaKeyTyped
        char car = evt.getKeyChar();
        String texto = jTextField_Nombre_Categoria.getText(); // Obtener el texto actual en el campo

        if (((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
            && car != 'á' // Minúsculas
            && car != 'é'
            && car != 'í'
            && car != 'ó'
            && car != 'ú'
            && car != 'Á' // Mayúsculas
            && car != 'É'
            && car != 'Í'
            && car != 'Ó'
            && car != 'Ú'
            && car != 'Ü'
            && car != 'ü'
            && car != 'Ñ'
            && car != 'ñ'
            && (car != (char) KeyEvent.VK_SPACE))
        || texto.length() >= 50) {
        evt.consume();
        }
    }//GEN-LAST:event_jTextField_Nombre_CategoriaKeyTyped

    private void jTextField_Nombre_Categoria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Nombre_Categoria
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Nombre_Categoria

    private void jTextField_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarKeyReleased
        BuscarCliente();
    }//GEN-LAST:event_jTextField_BuscarKeyReleased

    private void jTextField_Buscar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Buscar
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Buscar

    private void jTextField_BuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_BuscarMouseClicked
        jTextField_Buscar.setText("");
        jTextField_Buscar.setForeground(Color.black);
    }//GEN-LAST:event_jTextField_BuscarMouseClicked

    private void jTextField_Id_Categoria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Id_Categoria
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Id_Categoria

    private void jTable_Categoria(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable_Categoria
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_Categoria


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar_Categoria;
    private javax.swing.JButton jButton_Agregar_Categoria;
    private javax.swing.JButton jButton_Editar_categoria;
    private javax.swing.JButton jButton_Eliminar_Categoria;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable_Categoria;
    private javax.swing.JTextArea jTextArea_Descripcion_Categoria;
    private javax.swing.JTextField jTextField_Buscar;
    private javax.swing.JTextField jTextField_Id_Categoria;
    private javax.swing.JTextField jTextField_Nombre_Categoria;
    // End of variables declaration//GEN-END:variables
}
