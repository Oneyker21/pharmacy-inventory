package Vistas;

import Controlador.CRUD_Producto;
import Controlador_Conexion_DB.Conexion;
import Modelo.Clase_Producto;
import java.sql.Date;

import Modelo_MDI1.MDIMenu;
import static Modelo_MDI1.MDIMenu.jDesktopPane;
import static Vistas.JInternalFrame_Producto.jComboBox_Categoria;
import static Vistas.JInternalFrame_Producto.jComboBox_Id_Proveedor;
import static Vistas.JInternalFrame_Producto.jComboBox_Laboratorio;
import static Vistas.JInternalFrame_Producto.jComboBox_Presentacion;
import static Vistas.JInternalFrame_Producto.jFormattedTextField_fecha_de_caducidad;
import static Vistas.JInternalFrame_Producto.jLabel_Mostrar_Imagen;
import static Vistas.JInternalFrame_Producto.jSpinner_Cantidad;
import static Vistas.JInternalFrame_Producto.jTextArea_descripcion;
import static Vistas.JInternalFrame_Producto.jTextField_nombre;
import static Vistas.JInternalFrame_Producto.jTextField_precio_venta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import static Vistas.JInternalFrame_Producto.jTextField_precio_compra;
import java.awt.HeadlessException;
import java.text.ParseException;

/**
 *
 * @author Diers
 */
public class JInternalFrame_Gestionar_Producto extends javax.swing.JInternalFrame {

    private Image iconoRedimensionado;
    private ImageIcon imagenIcono;
    private ImageIcon imagenGuardada;
    private int idProductoActual;

    Conexion conexion = new Conexion();

    public JInternalFrame_Gestionar_Producto() {
        // Aquí estamos configurando los colores de los encabezados de la tabla.
        UIManager.put("TableHeader.background", new Color(153, 255, 255));
        UIManager.put("TableHeader.foreground", Color.BLACK);

        initComponents();  // Inicializa los componentes antes de usarlos
        mostrarCantidadStock();
       

        setLayout(new FlowLayout());

        int rowHeight = 64; // Altura deseada de la fila en píxeles
        jTable_Producto1.setRowHeight(rowHeight);

        // Configurar la tabla
        configurarTabla();

        // Mostrar los datos en la tabla
        mostrar();
    }

    private ImageIcon redimensionarImagen(ImageIcon imagenIcono, int ancho, int alto) {
        if (imagenIcono != null && imagenIcono.getImage() != null) {
            Image imagen = imagenIcono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagen);
        } else {
            // Manejo de la situación cuando la imagenIcono o la imagen son nulas
            return null;
        }
    }

    public void configurarTabla() {
        // ...

        int rowHeight = 84; // Altura deseada de la fila en píxeles
        jTable_Producto1.setRowHeight(rowHeight);

        // Establecer el ancho preferido de cada columna
        jTable_Producto1.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable_Producto1.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable_Producto1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable_Producto1.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable_Producto1.getColumnModel().getColumn(4).setPreferredWidth(120);
        jTable_Producto1.getColumnModel().getColumn(5).setPreferredWidth(120);
        jTable_Producto1.getColumnModel().getColumn(6).setPreferredWidth(100);
        jTable_Producto1.getColumnModel().getColumn(7).setPreferredWidth(120);
        jTable_Producto1.getColumnModel().getColumn(8).setPreferredWidth(110);
        jTable_Producto1.getColumnModel().getColumn(9).setPreferredWidth(120);
        jTable_Producto1.getColumnModel().getColumn(10).setPreferredWidth(110);
        jTable_Producto1.getColumnModel().getColumn(11).setPreferredWidth(125);

        // Centrar los datos en las celdas de texto
        DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer();
        textRenderer.setHorizontalAlignment(JLabel.CENTER); // Centra el texto
        jTable_Producto1.setDefaultRenderer(Object.class, textRenderer);

        // Establecer el tamaño de la columna de imagen
        TableColumnModel columnModel = jTable_Producto1.getColumnModel();
        TableColumn imageColumn = columnModel.getColumn(6); // Columna de la imagen
        imageColumn.setPreferredWidth(100); // Ajusta el tamaño según tus necesidades

        // Obtener el renderizador actual para la columna de imagen
        TableCellRenderer defaultRenderer = jTable_Producto1.getDefaultRenderer(ImageIcon.class);

        // Crear un nuevo renderizador para la columna de imagen
        TableCellRenderer imageRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    ImageIcon originalIcon = (ImageIcon) value;
                    Image image = originalIcon.getImage();
                    if (image != null) {
                        Image resizedImage = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);

                        JLabel label = new JLabel(resizedIcon);
                        label.setHorizontalAlignment(JLabel.CENTER); // Centra la imagen
                        return label;
                    }
                }
                return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        // Establecer el nuevo renderizador para la columna de imagen
        jTable_Producto1.getColumnModel().getColumn(6).setCellRenderer(imageRenderer);
    }

    private byte[] obtenerBytesDesdeImagen(ImageIcon imagenIcono, String formato) {
        BufferedImage bufferedImage = new BufferedImage(imagenIcono.getIconWidth(), imagenIcono.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.createGraphics();
        imagenIcono.paintIcon(null, g, 0, 0);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, formato, baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    public void mostrarCantidadStock() {
        CRUD_Producto crudProducto = new CRUD_Producto();

        try {
            int totalCantidad = crudProducto.obtenerTotalCantidadProducto();
            jTextField_Stok.setText(String.valueOf(totalCantidad));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
  



    public void mostrar() {
        try {
            DefaultTableModel modelo;
            CRUD_Producto crudProducto = new CRUD_Producto();
            crudProducto.mostrarProductoConProveedor();

            // Obtener los datos del modelo de la tabla
            String[] columnas = {"ID", "Nombre", "Descripción", "Cantidad", "Precio de Compra", "Precio de Venta", "Imagen", "Fecha de Caducidad", "Categoría", "Presentación", "Laboratorio", "Proveedor"};
            Object[][] datos = obtenerDatosTabla(jTable_Producto1);

            if (datos != null && datos.length > 0) {
                modelo = new DefaultTableModel(datos, columnas) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Desactivar la edición de celdas
                    }
                };
            } else {
                modelo = new DefaultTableModel(columnas, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Desactivar la edición de celdas
                    }
                };
            }

            // Agregar un CellRenderer personalizado para la columna de la imagen
            jTable_Producto1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (value instanceof ImageIcon && column == 6) {
                        JLabel label = new JLabel();
                        ImageIcon imagenIcono = (ImageIcon) value;
                        ImageIcon imagenRedimensionada = redimensionarImagen(imagenIcono, 64, 64);
                        label.setIcon(imagenRedimensionada);
                        return label;
                    } else {
                        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    }
                }
            });

            jTable_Producto1.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e.getMessage());
        }
    }

    private Object[][] obtenerDatosTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int filas = modelo.getRowCount();
        int columnas = modelo.getColumnCount();
        Object[][] datos = new Object[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = modelo.getValueAt(i, j);
            }
        }

        return datos;
    }

    public JInternalFrame_Producto obtenerFormularioProducto() {
        Component[] components = getParent().getComponents();

        // Buscar si el formulario JInternalFrame_Producto ya está abierto
        for (Component component : components) {
            if (component instanceof JInternalFrame_Producto) {
                return (JInternalFrame_Producto) component;
            }
        }

        // Si el formulario no está abierto, crear una nueva instancia
        JInternalFrame_Producto formularioProducto = new JInternalFrame_Producto();

        // Agregar el formulario al contenedor MDIParent (jdpane)
        getParent().add(formularioProducto);

        // Ubicar el formulario en el centro del contenedor
        int x = (getParent().getWidth() - formularioProducto.getWidth()) / 2;
        int y = (getParent().getHeight() - formularioProducto.getHeight()) / 2;
        formularioProducto.setLocation(x, y);

        return formularioProducto;
    }

    public void BuscarProducto() {
        try {
            DefaultTableModel modelo;
            CRUD_Producto cli = new CRUD_Producto();
            modelo = cli.BuscarProducto(jTextField_Buscar.getText());

            if (modelo != null) {
                jTable_Producto1.setModel(modelo);
                configurarTabla(); // llama a configurarTabla() después de establecer el nuevo modelo
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_Producto1 = new javax.swing.JTable();
        jButton_Editar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();
        jTextField_Buscar = new javax.swing.JTextField();
        jButton_Agregar = new javax.swing.JButton();
        jTextField_Stok = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(1200, 579));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jTable_Producto1.setBackground(new java.awt.Color(204, 255, 255));
        jTable_Producto1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id_Producto", "Nombre", "Descripción", "Cantidad de producto", "Precio compra", "Precio_Venta", "Imagen", "Fecha_Caducidad", "Id_Categoria", "Id_Presentacion", "Id_Laboratorio", "Proveedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_Producto1.setGridColor(new java.awt.Color(0, 153, 153));
        jTable_Producto1.setShowGrid(true);
        jTable_Producto1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable_Producto1(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane6.setViewportView(jTable_Producto1);

        jButton_Editar.setBackground(new java.awt.Color(255, 255, 51));
        jButton_Editar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Editar.setText("Editar");
        jButton_Editar.setBorder(null);
        jButton_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Editar(evt);
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

        jTextField_Buscar.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Buscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTextField_Buscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
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

        jButton_Agregar.setBackground(new java.awt.Color(51, 255, 51));
        jButton_Agregar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Agregar.setText("Agregar");
        jButton_Agregar.setBorder(null);
        jButton_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agregar(evt);
            }
        });

        jTextField_Stok.setEditable(false);
        jTextField_Stok.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Stok.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Stok.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos en Stok", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_StokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1324, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_Producto1(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable_Producto1
        configurarTabla();
    }//GEN-LAST:event_jTable_Producto1

    private void jButton_Editar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Editar
        int selectedRow = jTable_Producto1.getSelectedRow();
        if (selectedRow != -1) {
            // Obtener los datos de la fila seleccionada
            String idProducto = jTable_Producto1.getValueAt(selectedRow, 0).toString();
            String nombre = jTable_Producto1.getValueAt(selectedRow, 1).toString();
            String descripcion = jTable_Producto1.getValueAt(selectedRow, 2).toString();
            int cantidad = (int) jTable_Producto1.getValueAt(selectedRow, 3);
            float precioCompra = (float) jTable_Producto1.getValueAt(selectedRow, 4);
            float precioVenta = (float) jTable_Producto1.getValueAt(selectedRow, 5);
            ImageIcon imagenIcono = (ImageIcon) jTable_Producto1.getValueAt(selectedRow, 6);
            String fechaCaducidad = jTable_Producto1.getValueAt(selectedRow, 7).toString();
            String idCategoria = jTable_Producto1.getValueAt(selectedRow, 8).toString();
            String idPresentacion = jTable_Producto1.getValueAt(selectedRow, 9).toString();
            String idLaboratorio = jTable_Producto1.getValueAt(selectedRow, 10).toString();
            String idProveedor = jTable_Producto1.getValueAt(selectedRow, 11).toString();

            // Crear una instancia del formulario JInternalFrame_Producto
            JInternalFrame_Producto formularioProducto = new JInternalFrame_Producto();

            // Establecer los valores en los campos del formulario JInternalFrame_Producto
            formularioProducto.setDatos(Integer.parseInt(idProducto), nombre, descripcion, cantidad, precioCompra, precioVenta, imagenIcono, fechaCaducidad, idCategoria, idPresentacion, idLaboratorio, idProveedor);

            // Agregar el formulario al contenedor MDIParent (jdpane)
            getParent().add(formularioProducto);

            // Ubicar el formulario en el centro del contenedor
            int x = (getParent().getWidth() - formularioProducto.getWidth()) / 2;
            int y = (getParent().getHeight() - formularioProducto.getHeight()) / 2;
            formularioProducto.setLocation(x, y);

            // Mostrar el formulario JInternalFrame_Producto
            formularioProducto.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_Editar

    private void jButton_Eliminar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Eliminar
        int selectedRow = jTable_Producto1.getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showOptionDialog(
                    rootPane,
                    "Se eliminará el registro, ¿desea continuar?",
                    "Eliminar Registro",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    new ImageIcon(getClass().getResource("/Vistas_Iconos/eliminar (2).png")),
                    new Object[]{"Sí", "No"},
                    "No"
            );

            if (option == JOptionPane.YES_OPTION) {
                String idProductoString = jTable_Producto1.getValueAt(selectedRow, 0).toString();
                int idProducto = Integer.parseInt(idProductoString);

                CRUD_Producto prod = new CRUD_Producto();
                prod.borrarProductoYProveedor(idProducto);

                // Aquí deberías llamar al método que actualiza tu tabla de productos en la interfaz.
                // Estoy suponiendo que tienes un método 'mostrar()' similar al del ejemplo que diste.
                mostrar();
                configurarTabla();

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JLabel messageLabel = new JLabel("Producto y proveedor eliminados correctamente");
                messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
                messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panel.add(messageLabel, BorderLayout.CENTER);

                ImageIcon icon = new ImageIcon(getClass().getResource("/Vistas_Iconos/eliminar (2).png"));
                JLabel iconLabel = new JLabel(icon);
                iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panel.add(iconLabel, BorderLayout.WEST);

                JOptionPane.showMessageDialog(null, panel, "Eliminación Exitosa", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JLabel messageLabel = new JLabel("Debe seleccionar un Producto");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
            messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(messageLabel, BorderLayout.CENTER);

            ImageIcon icon = new ImageIcon(getClass().getResource("/Vistas_Iconos/abvertencia.png"));
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(iconLabel, BorderLayout.WEST);

            JOptionPane.showMessageDialog(null, panel, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_Eliminar

    private void jTextField_Buscar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Buscar
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Buscar

    private void jButton_Agregar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agregar
        // Cierra este JInternalFrame_Gestionar_Producto
        this.dispose();

        // Abre el JInternalFrame_Producto
        JInternalFrame_Producto producto = new JInternalFrame_Producto();
        producto.setSize(1200, 666);
        producto.setLocation(0, 0);
        producto.setVisible(true);

        // Aquí necesitarías agregar el nuevo JInternalFrame a tu JDesktopPane
        jDesktopPane.removeAll();
        jDesktopPane.add(producto);
        try {
            producto.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton_Agregar

    private void jTextField_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarKeyReleased
        BuscarProducto();
    }//GEN-LAST:event_jTextField_BuscarKeyReleased

    private void jTextField_StokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_StokActionPerformed
        mostrarCantidadStock();
    }//GEN-LAST:event_jTextField_StokActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_Editar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JTable jTable_Producto1;
    private javax.swing.JTextField jTextField_Buscar;
    private javax.swing.JTextField jTextField_Stok;
    // End of variables declaration//GEN-END:variables
}
