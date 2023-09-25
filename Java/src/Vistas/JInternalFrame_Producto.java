package Vistas;

import Controlador.CRUD_Producto;
import Controlador_Conexion_DB.Conexion;
import Modelo.Clase_Categoria;
import Modelo.Clase_Laboratorio;
import Modelo.Clase_Presentacion;
import Modelo.Clase_Producto;
import Modelo.Clase_Proveedor;
import static Modelo_MDI1.MDIMenu.jDesktopPane;
import static Vistas.JInternalFrame_Gestionar_Producto.jTable_Producto1;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;


/**
 * @author diedr
 */
public class JInternalFrame_Producto extends javax.swing.JInternalFrame {

    private Image iconoRedimensionado;
    private ImageIcon imagenIcono;
    private ImageIcon imagenGuardada;
    private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    private int idProductoActual;
    private JInternalFrame_Gestionar_Producto parent;

    Conexion conexion = new Conexion();

    public JInternalFrame_Producto() {
        setLayout(new FlowLayout());
        initComponents();
         setTitle("Producto");
        setFrameIcon(new ImageIcon("C:\\Users\\Diers\\OneDrive\\Escritorio\\CasoFarmacia\\Repositorio-Farmacia-Rosales.-main\\src\\Vistas_Iconos\\apothecary-IconoPequeño.png"));
      
        int idProducto = -1;
        idProductoActual = idProducto;
        int rowHeight = 64; // Altura deseada de la fila en píxeles
        jTable_Producto1 = new javax.swing.JTable();
        Connection cn = (Connection) conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Id_Producto", "Nombre", "Descripcion", "Cantidad_Producto", "Precio_Compra", "Precio_Venta", "Imagen_Producto", "Fecha_Caducidad", "Id_Categoria", "Id_Presentacion", "Id_Laboratorio"});

        jTable_Producto1.setModel(model);

        jTable_Producto1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof JLabel && column == 6) {
                    JLabel label = (JLabel) value;
                    return label;
                } else {
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            }
        });
        

        jTextField_Buscar_Categoria.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                llenarCategoria(jTextField_Buscar_Categoria.getText());
            }
        });
        jTextField_Buscar_Presentacion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                llenarComboBoxPresentacion(jTextField_Buscar_Presentacion.getText());
            }

        });
        jTextField_Buscar_Laboratorio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                llenarComboBoxLaboratorio(jTextField_Buscar_Laboratorio.getText());
            }
        });
        jTextField_Buscar_Id_Proveedor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                llenarComboBoxProveedor(jTextField_Buscar_Id_Proveedor.getText());
            }
        });

       
       
        jSpinner_Cantidad.setBackground(new Color(204, 255, 255));

        UIManager.put("Slider.background", Color.WHITE);
        UIManager.put("Slider.foreground", Color.BLUE);
        UIManager.put("Slider.track", Color.LIGHT_GRAY);
        UIManager.put("Slider.thumb", Color.DARK_GRAY);

    }
    // En JInternalFrame_Producto.java

    public void setImagenLabel(ImageIcon imagen) {
        jLabel_Mostrar_Imagen.setIcon(imagen);
    }

    public void setParent(JInternalFrame_Gestionar_Producto parent) {
        this.parent = parent;
    }

    public ImageIcon redimensionarImagen(ImageIcon imagen, int ancho, int alto) {
        Image img = imagen.getImage();
        Image imgRedimensionada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgRedimensionada);
    }

    public void llenarComboBoxProveedor(String busqueda) {
        CRUD_Producto gr = new CRUD_Producto();
        ArrayList<Clase_Proveedor> listaProveedores = gr.buscarProveedor(busqueda);
        jComboBox_Id_Proveedor.removeAllItems();
        for (Clase_Proveedor proveedor : listaProveedores) {
            jComboBox_Id_Proveedor.addItem(proveedor);
        }
    }

   

    public void llenarComboBoxLaboratorio(String busqueda) {
        CRUD_Producto gr = new CRUD_Producto();
        ArrayList<Clase_Laboratorio> listaLaboratorio = gr.buscarLaboratorios(busqueda);
        jComboBox_Laboratorio.removeAllItems();
        for (Clase_Laboratorio laboratorio : listaLaboratorio) {
            jComboBox_Laboratorio.addItem(laboratorio);
        }
    }

    public void llenarCategoria(String busqueda) {
        CRUD_Producto gr = new CRUD_Producto();
        ArrayList<Clase_Categoria> listaCategorias = gr.mostrarDatosCombo(busqueda);
        jComboBox_Categoria.removeAllItems();
        for (Clase_Categoria categoria : listaCategorias) {
            jComboBox_Categoria.addItem(categoria);
        }
    }

    public void llenarComboBoxPresentacion(String busqueda) {
        CRUD_Producto gr = new CRUD_Producto();
        ArrayList<Clase_Presentacion> listaPresentaciones = gr.buscarPresentaciones(busqueda);
        jComboBox_Presentacion.removeAllItems();
        for (Clase_Presentacion presentacion : listaPresentaciones) {
            jComboBox_Presentacion.addItem(presentacion);
        }
    }

    public void agregarProductoATabla() {
        Clase_Categoria productoSeleccionado = (Clase_Categoria) jComboBox_Categoria.getSelectedItem();
        if (productoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTable_Producto1.getModel();

        model.addRow(new Object[]{
            productoSeleccionado.getId_Categoria(),
            productoSeleccionado.getNombre_Categoria()
        });

        // Limpia el JComboBox y el JTextField después de agregar una categoría a la tabla
        jComboBox_Categoria.setSelectedIndex(-1);
        jTextField_Buscar_Categoria.setText("");
    }

    public void agregarCategoriaAlComboBox() {
        String busqueda = jTextField_Buscar_Categoria.getText();
        if (busqueda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un texto de búsqueda válido.");
            return;
        }

        CRUD_Producto gr = new CRUD_Producto();
        ArrayList<Clase_Categoria> listaCategorias = gr.mostrarDatosCombo(busqueda);
        jComboBox_Categoria.removeAllItems();
        for (Clase_Categoria categoria : listaCategorias) {
            jComboBox_Categoria.addItem(categoria);
        }
    }

    public byte[] obtenerBytesDesdeImagen(ImageIcon imagenIcono, String formato) {
        Image imagen = imagenIcono.getImage();

        BufferedImage bufferedImage = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(imagen, 0, 0, null);
        g2.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, formato, baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    

  

    public java.util.Date obtenerFechaCaducidad() {
        String fechaCaducidadString = jFormattedTextField_fecha_de_caducidad.getText();

        try {
            java.util.Date fechaCaducidadUtil = formato.parse(fechaCaducidadString);

            // Crear un objeto Calendar para manipular la fecha
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaCaducidadUtil);

            // Obtener la fecha resultante
            java.util.Date fechaCaducidadResultante = calendar.getTime();

            return fechaCaducidadResultante;
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Retornar null si ocurre un error al parsear la fecha
        }
    }

   

    public void limpiarFormulario() {
        // Limpiar campos de texto
        jTextField_nombre.setText("");
        jTextField_precio_compra.setText("");
        jTextArea_descripcion.setText("");

        jTextField_precio_venta.setText("");
        jFormattedTextField_fecha_de_caducidad.setText("");

        // Limpiar campos de selección
        jComboBox_Categoria.setSelectedIndex(-1);
        jComboBox_Laboratorio.setSelectedIndex(-1);
        jComboBox_Presentacion.setSelectedIndex(-1);
        jComboBox_Id_Proveedor.setSelectedIndex(-1);

        // Limpiar otros componentes
        jLabel_Mostrar_Imagen.setIcon(null);
        jSpinner_Cantidad.setValue(0);
        jTextField_Buscar_Categoria.setText("");
        jTextField_Buscar_Laboratorio.setText("");
        jTextField_Buscar_Presentacion.setText("");
        jTextField_Buscar_Id_Proveedor.setText("");

    }

    

    public void setDatos(int idProducto, String nombre, String descripcion, int cantidad, float precioCompra, float precioVenta, ImageIcon imagenIcono, String fechaCaducidad, String idCategoria, String idPresentacion, String idLaboratorio, String idProveedor) {
        idProductoActual = idProducto;
        jTextField_nombre.setText(nombre);
        jTextArea_descripcion.setText(descripcion);
        jSpinner_Cantidad.setValue(cantidad);
        jTextField_precio_compra.setText(Float.toString(precioCompra));
        jTextField_precio_venta.setText(Float.toString(precioVenta));

        // Redimensionar la imagen al tamaño deseado
        ImageIcon imagenRedimensionada = redimensionarImagen(imagenIcono, 200, 200);
        if (imagenRedimensionada != null) {
            jLabel_Mostrar_Imagen.setIcon(imagenRedimensionada);
        }

        // Convertir la cadena de fecha a un objeto Date
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaCaducidadDate;
        try {
            fechaCaducidadDate = formato.parse(fechaCaducidad);
        } catch (ParseException e) {
            fechaCaducidadDate = null;
            e.printStackTrace();
        }

        if (fechaCaducidadDate != null) {
            // Establecer la fecha de caducidad en el jFormattedTextField_fecha_de_caducidad
            String dateString = formato.format(fechaCaducidadDate);
            jFormattedTextField_fecha_de_caducidad.setText(dateString);
        }

        // Llenar los combo boxes con los valores correspondientes
        llenarCategoria(idCategoria);
        llenarComboBoxPresentacion(idPresentacion);
        llenarComboBoxLaboratorio(idLaboratorio);
        llenarComboBoxProveedor(idProveedor);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_descripcion = new javax.swing.JTextArea();
        jTextField_nombre = new javax.swing.JTextField();
        jSpinner_Cantidad = new javax.swing.JSpinner();
        jTextField_precio_venta = new javax.swing.JTextField();
        jFormattedTextField_fecha_de_caducidad = new javax.swing.JFormattedTextField();
        jTextField_precio_compra = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jComboBox_Categoria = new javax.swing.JComboBox<>();
        jTextField_Buscar_Categoria = new javax.swing.JTextField();
        jButton_Agregar_Categoria = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jComboBox_Presentacion = new javax.swing.JComboBox<>();
        jTextField_Buscar_Presentacion = new javax.swing.JTextField();
        jButton_Agregar_Presentacion = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jComboBox_Laboratorio = new javax.swing.JComboBox<>();
        jTextField_Buscar_Laboratorio = new javax.swing.JTextField();
        jButton_Agregar_Laboratorio = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jComboBox_Id_Proveedor = new javax.swing.JComboBox<>();
        jTextField_Buscar_Id_Proveedor = new javax.swing.JTextField();
        jButton_Agregar_Proveedor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton_buscar_imagen = new javax.swing.JButton();
        jButton_Eliminar_imagen = new javax.swing.JButton();
        jLabel_Mostrar_Imagen = new javax.swing.JLabel();
        jButton_Guardar = new javax.swing.JButton();
        jButton_Ver_Producto = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jScrollPane2.setViewportView(jTextArea2);

        jPasswordField1.setText("jPasswordField1");

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setName("Producto"); // NOI18N
        setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setOpaque(false);

        jTextArea_descripcion.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea_descripcion.setColumns(20);
        jTextArea_descripcion.setRows(5);
        jTextArea_descripcion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextArea_descripcion.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTextArea_descripcion(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane3.setViewportView(jTextArea_descripcion);

        jTextField_nombre.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_nombre.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_nombre.setPreferredSize(new java.awt.Dimension(64, 45));
        jTextField_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombre(evt);
            }
        });

        jSpinner_Cantidad.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cantidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jSpinner_Cantidad.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jSpinner_Cantidad(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jSpinner_Cantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSpinner_CantidadFocusLost(evt);
            }
        });
        jSpinner_Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jSpinner_CantidadKeyTyped(evt);
            }
        });

        jTextField_precio_venta.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_precio_venta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_precio_venta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Precio Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_precio_venta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_precio_ventaFocusLost(evt);
            }
        });
        jTextField_precio_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_precio_venta(evt);
            }
        });
        jTextField_precio_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_precio_ventaKeyTyped(evt);
            }
        });

        jFormattedTextField_fecha_de_caducidad.setBackground(new java.awt.Color(204, 255, 255));
        jFormattedTextField_fecha_de_caducidad.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha Caducidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        try {
            jFormattedTextField_fecha_de_caducidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_fecha_de_caducidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField_fecha_de_caducidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField_fecha_de_caducidad(evt);
            }
        });

        jTextField_precio_compra.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_precio_compra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_precio_compra.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Precio Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_precio_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_precio_compra(evt);
            }
        });
        jTextField_precio_compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_precio_compraKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jTextField_precio_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jTextField_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_precio_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jSpinner_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jFormattedTextField_fecha_de_caducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_precio_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField_fecha_de_caducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_precio_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel7.setOpaque(false);

        jComboBox_Categoria.setBackground(new java.awt.Color(204, 255, 255));
        jComboBox_Categoria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jComboBox_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Categoria(evt);
            }
        });
        jComboBox_Categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox_CategoriaKeyReleased(evt);
            }
        });

        jTextField_Buscar_Categoria.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Buscar_Categoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Buscar_Categoria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Buscar_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Buscar_Categoria(evt);
            }
        });
        jTextField_Buscar_Categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_Buscar_CategoriaKeyPressed(evt);
            }
        });

        jButton_Agregar_Categoria.setBackground(new java.awt.Color(51, 255, 51));
        jButton_Agregar_Categoria.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Agregar_Categoria.setText("Agregar");
        jButton_Agregar_Categoria.setBorder(null);
        jButton_Agregar_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agregar_Categoria(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Buscar_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jButton_Agregar_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_Buscar_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Agregar_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Presentacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel9.setOpaque(false);

        jComboBox_Presentacion.setBackground(new java.awt.Color(204, 255, 255));
        jComboBox_Presentacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Presentacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jComboBox_Presentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Presentacion(evt);
            }
        });

        jTextField_Buscar_Presentacion.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Buscar_Presentacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Buscar_Presentacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Buscar_Presentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Buscar_Presentacion(evt);
            }
        });

        jButton_Agregar_Presentacion.setBackground(new java.awt.Color(51, 255, 51));
        jButton_Agregar_Presentacion.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Agregar_Presentacion.setText("Agregar");
        jButton_Agregar_Presentacion.setBorder(null);
        jButton_Agregar_Presentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agregar_Presentacion(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jComboBox_Presentacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextField_Buscar_Presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)))
                .addComponent(jButton_Agregar_Presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField_Buscar_Presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_Presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jButton_Agregar_Presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Laboratorio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(345, 126));

        jComboBox_Laboratorio.setBackground(new java.awt.Color(204, 255, 255));
        jComboBox_Laboratorio.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Laboratorio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jComboBox_Laboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Laboratorio(evt);
            }
        });

        jTextField_Buscar_Laboratorio.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Buscar_Laboratorio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Buscar_Laboratorio.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Buscar_Laboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Buscar_Laboratorio(evt);
            }
        });

        jButton_Agregar_Laboratorio.setBackground(new java.awt.Color(51, 255, 51));
        jButton_Agregar_Laboratorio.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Agregar_Laboratorio.setText("Agregar");
        jButton_Agregar_Laboratorio.setBorder(null);
        jButton_Agregar_Laboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agregar_Laboratorio(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Buscar_Laboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Laboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Agregar_Laboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField_Buscar_Laboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Laboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButton_Agregar_Laboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel11.setForeground(new java.awt.Color(0, 153, 153));
        jPanel11.setOpaque(false);

        jComboBox_Id_Proveedor.setBackground(new java.awt.Color(204, 255, 255));
        jComboBox_Id_Proveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jComboBox_Id_Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Id_Proveedor(evt);
            }
        });

        jTextField_Buscar_Id_Proveedor.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Buscar_Id_Proveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Buscar_Id_Proveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Buscar_Id_Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Buscar_Id_Proveedor(evt);
            }
        });

        jButton_Agregar_Proveedor.setBackground(new java.awt.Color(51, 255, 51));
        jButton_Agregar_Proveedor.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Agregar_Proveedor.setText("Agregar");
        jButton_Agregar_Proveedor.setBorder(null);
        jButton_Agregar_Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agregar_Proveedor(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Buscar_Id_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Id_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jButton_Agregar_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_Buscar_Id_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox_Id_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Agregar_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton_buscar_imagen.setBackground(new java.awt.Color(204, 255, 255));
        jButton_buscar_imagen.setText("Agregar");
        jButton_buscar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscar_imagen(evt);
            }
        });

        jButton_Eliminar_imagen.setBackground(new java.awt.Color(255, 204, 204));
        jButton_Eliminar_imagen.setText("Eliminar");
        jButton_Eliminar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Eliminar_imagen(evt);
            }
        });

        jLabel_Mostrar_Imagen.setForeground(new java.awt.Color(0, 153, 153));
        jLabel_Mostrar_Imagen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Foto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jLabel_Mostrar_Imagen.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel_Mostrar_Imagen(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_buscar_imagen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Eliminar_imagen))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_Mostrar_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel_Mostrar_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_buscar_imagen)
                    .addComponent(jButton_Eliminar_imagen))
                .addContainerGap())
        );

        jButton_Guardar.setBackground(new java.awt.Color(51, 255, 51));
        jButton_Guardar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Guardar.setText("Agregar");
        jButton_Guardar.setBorder(null);
        jButton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Guardar(evt);
            }
        });

        jButton_Ver_Producto.setBackground(new java.awt.Color(204, 204, 255));
        jButton_Ver_Producto.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Ver_Producto.setText("Ver Productos ");
        jButton_Ver_Producto.setBorder(null);
        jButton_Ver_Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Ver_Producto(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButton_Ver_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addComponent(jButton_Ver_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_buscar_imagen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buscar_imagen
        JFileChooser j = new JFileChooser();
        FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        j.setFileFilter(fil);

        int s = j.showOpenDialog(this);
        if (s == JFileChooser.APPROVE_OPTION) {
            String ruta = j.getSelectedFile().getAbsolutePath();
            System.out.println("Ruta de la imagen: " + ruta);

            try {
                ImageIcon imagenIcono = new ImageIcon(ruta);

                ImageIcon imagenRedimensionada = redimensionarImagen(imagenIcono, 200, 200);
                jLabel_Mostrar_Imagen.setIcon(imagenRedimensionada);

                imagenGuardada = redimensionarImagen(imagenIcono, 64, 64);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_jButton_buscar_imagen

    private void jTextField_nombre(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombre
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nombre

    private void jTextArea_descripcion(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTextArea_descripcion
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea_descripcion

    private void jTextField_precio_venta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_precio_venta

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_precio_venta

    private void jButton_Guardar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Guardar
        // Obtener la imagen del JLabel
        ImageIcon imagenIcono = (ImageIcon) jLabel_Mostrar_Imagen.getIcon();

// Verificar que se haya seleccionado una imagen
        if (imagenIcono == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

// Obtener los valores seleccionados de los combo boxes
        Clase_Categoria categoriaSeleccionada = (Clase_Categoria) jComboBox_Categoria.getSelectedItem();
        Clase_Laboratorio laboratorioSeleccionado = (Clase_Laboratorio) jComboBox_Laboratorio.getSelectedItem();
        Clase_Presentacion presentacionSeleccionada = (Clase_Presentacion) jComboBox_Presentacion.getSelectedItem();
        Clase_Proveedor proveedorSeleccionado = (Clase_Proveedor) jComboBox_Id_Proveedor.getSelectedItem();

// Verificar que se hayan seleccionado los valores necesarios
        if (categoriaSeleccionada == null || laboratorioSeleccionado == null || presentacionSeleccionada == null || proveedorSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría, laboratorio, presentación y proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

// Crear el objeto Clase_Producto con los datos del formulario y las selecciones de los combo boxes
        Clase_Producto producto = new Clase_Producto();
        producto.setNombre(jTextField_nombre.getText());
        producto.setDescripcion(jTextArea_descripcion.getText());
        producto.setCantidad_Producto((int) jSpinner_Cantidad.getValue());
        producto.setPrecio_Compra(Float.parseFloat(jTextField_precio_compra.getText()));
        producto.setPrecio_Venta(Float.parseFloat(jTextField_precio_venta.getText()));
        producto.setImagen_Producto(obtenerBytesDesdeImagen(imagenIcono, "png")); // Obtener bytes de la imagen
        producto.setFecha_Caducidad(new java.sql.Date(obtenerFechaCaducidad().getTime())); // Obtener fecha de caducidad
        producto.setId_Categoria(categoriaSeleccionada.getId_Categoria());
        producto.setId_Presentacion(presentacionSeleccionada.getId_Presentacion());
        producto.setId_Laboratorio(laboratorioSeleccionado.getId_Laboratorio());
        producto.setId_Proveedor(proveedorSeleccionado.getId_Proveedor());

// Insertar el producto en la base de datos
        CRUD_Producto crudProducto = new CRUD_Producto();
        crudProducto.insertarProductoConProveedor(producto);

// Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Producto insertado correctamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        limpiarFormulario();

    }//GEN-LAST:event_jButton_Guardar

    private void jButton_Actualizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Actualizar
        try {
            if (jTextField_nombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Existen campos vacíos. Por favor, complete todos los campos.");
            } else {
                CRUD_Producto crudProducto = new CRUD_Producto();
                Clase_Producto producto = new Clase_Producto();

                // Establecer los datos del producto
                producto.setId_Producto(idProductoActual);
                producto.setNombre(jTextField_nombre.getText());
                producto.setDescripcion(jTextArea_descripcion.getText());
                producto.setCantidad_Producto((int) jSpinner_Cantidad.getValue());
                producto.setPrecio_Compra(Float.parseFloat(jTextField_precio_compra.getText()));
                producto.setPrecio_Venta(Float.parseFloat(jTextField_precio_venta.getText()));
                producto.setImagen_Producto(obtenerBytesDesdeImagen((ImageIcon) jLabel_Mostrar_Imagen.getIcon(), "png"));
                producto.setFecha_Caducidad(new java.sql.Date(obtenerFechaCaducidad().getTime()));
                producto.setId_Categoria(((Clase_Categoria) jComboBox_Categoria.getSelectedItem()).getId_Categoria());
                producto.setId_Presentacion(((Clase_Presentacion) jComboBox_Presentacion.getSelectedItem()).getId_Presentacion());
                producto.setId_Laboratorio(((Clase_Laboratorio) jComboBox_Laboratorio.getSelectedItem()).getId_Laboratorio());
                producto.setId_Proveedor(((Clase_Proveedor) jComboBox_Id_Proveedor.getSelectedItem()).getId_Proveedor());

                // Actualizar el producto en la base de datos
                boolean isUpdated = crudProducto.actualizarProductoConProveedor(producto);
                if (isUpdated) {
                    JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // Abrir JInternalFrame_Gestionar_Producto
                    JInternalFrame_Gestionar_Producto formularioGestionarProducto = new JInternalFrame_Gestionar_Producto();
                    getParent().add(formularioGestionarProducto);
                    formularioGestionarProducto.setVisible(true);

                    // Actualizar los registros en JInternalFrame_Gestionar_Producto
                    if (getParent() instanceof JDesktopPane) {
                        JDesktopPane desktopPane = (JDesktopPane) getParent();
                        for (JInternalFrame frame : desktopPane.getAllFrames()) {
                            if (frame instanceof JInternalFrame_Gestionar_Producto) {
                                JInternalFrame_Gestionar_Producto gestionarProducto = (JInternalFrame_Gestionar_Producto) frame;
                                gestionarProducto.mostrar();
                                break;
                            }
                        }
                    }

                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton_Actualizar

    private void jLabel_Mostrar_Imagen(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel_Mostrar_Imagen
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel_Mostrar_Imagen

    private void jComboBox_Presentacion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Presentacion

    }//GEN-LAST:event_jComboBox_Presentacion

    private void jComboBox_Laboratorio(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Laboratorio

    }//GEN-LAST:event_jComboBox_Laboratorio

    private void jSpinner_Cantidad(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSpinner_Cantidad

        JSpinner spinner = (JSpinner) evt.getSource();
        JComponent editor = spinner.getEditor();
        JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });


    }//GEN-LAST:event_jSpinner_Cantidad

    private void jTextField_Buscar_Presentacion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Buscar_Presentacion
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Buscar_Presentacion

    private void jTextField_Buscar_Categoria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Buscar_Categoria


    }//GEN-LAST:event_jTextField_Buscar_Categoria

    private void jTextField_Buscar_Laboratorio(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Buscar_Laboratorio
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Buscar_Laboratorio

    private void jButton_Eliminar_imagen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Eliminar_imagen

        jLabel_Mostrar_Imagen.setIcon(null);
        imagenIcono = null;
        iconoRedimensionado = null;
    }//GEN-LAST:event_jButton_Eliminar_imagen

    private void jComboBox_Id_Proveedor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Id_Proveedor
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Id_Proveedor

    private void jTextField_Buscar_Id_Proveedor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Buscar_Id_Proveedor

    }//GEN-LAST:event_jTextField_Buscar_Id_Proveedor

    private void jComboBox_Categoria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Categoria

    }//GEN-LAST:event_jComboBox_Categoria

    private void jTextField_Buscar_CategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Buscar_CategoriaKeyPressed

    }//GEN-LAST:event_jTextField_Buscar_CategoriaKeyPressed

    private void jComboBox_CategoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_CategoriaKeyReleased
        String busqueda = jTextField_Buscar_Categoria.getText();
        CRUD_Producto gr = new CRUD_Producto();
        ArrayList<Clase_Categoria> listaProductos = gr.mostrarDatosCombo(busqueda);
        jComboBox_Categoria.removeAllItems();
        for (Clase_Categoria producto : listaProductos) {
            jComboBox_Categoria.addItem(producto);
        }
    }//GEN-LAST:event_jComboBox_CategoriaKeyReleased

    private void jButton_Ver_Producto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Ver_Producto
       // Cierra este JInternalFrame_Producto
    this.dispose();

    // Abre el JInternalFrame_Gestionar_Producto
    JInternalFrame_Gestionar_Producto gestionarProducto = new JInternalFrame_Gestionar_Producto();
    gestionarProducto.setSize(jDesktopPane.getSize()); // Establecer el tamaño del formulario igual al tamaño del JDesktopPane
    gestionarProducto.setLocation(0, 0);
    gestionarProducto.setVisible(true);

    // Aquí necesitarías agregar el nuevo JInternalFrame a tu JDesktopPane
    jDesktopPane.removeAll();
    jDesktopPane.add(gestionarProducto);
    try {
        gestionarProducto.setSelected(true);
    } catch (java.beans.PropertyVetoException e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jButton_Ver_Producto

    private void jFormattedTextField_fecha_de_caducidad(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField_fecha_de_caducidad

        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField_fecha_de_caducidad

    private void jButton_Agregar_Laboratorio(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agregar_Laboratorio
        // Crear una nueva instancia de JInternalFrame_Laboratorio
        JInternalFrame_Laboratorio internalFrame_Laboratorio = new JInternalFrame_Laboratorio();

        // Calcular las coordenadas para centrar el JInternalFrame
        int x = (jDesktopPane.getWidth() - internalFrame_Laboratorio.getWidth()) / 2;
        int y = (jDesktopPane.getHeight() - internalFrame_Laboratorio.getHeight()) / 2;

        // Configurar la posición del JInternalFrame
        internalFrame_Laboratorio.setLocation(x, y);

        // Agregar la instancia al jDesktopPane
        jDesktopPane.add(internalFrame_Laboratorio);

        // Hacer la instancia visible
        internalFrame_Laboratorio.setVisible(true);
        
         internalFrame_Laboratorio.mostrar();

        try {
            // Traer al frente en el jDesktopPane
            internalFrame_Laboratorio.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton_Agregar_Laboratorio

    private void jButton_Agregar_Categoria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agregar_Categoria
        JInternalFrame_Categoria internalFrame_categoria = new JInternalFrame_Categoria();

    // Calcular las coordenadas para centrar el JInternalFrame
    int x = (jDesktopPane.getWidth() - internalFrame_categoria.getWidth()) / 2;
    int y = (jDesktopPane.getHeight() - internalFrame_categoria.getHeight()) / 2;

    // Configurar la posición del JInternalFrame
    internalFrame_categoria.setLocation(x, y);

    // Agregar la instancia al jDesktopPane
    jDesktopPane.add(internalFrame_categoria);

    // Hacer la instancia visible
    internalFrame_categoria.setVisible(true);

    // Mostrar los datos en la tabla
    internalFrame_categoria.mostrarCategoria();

    try {
        // Traer al frente en el jDesktopPane
        internalFrame_categoria.setSelected(true);
    } catch (java.beans.PropertyVetoException e) {
        e.printStackTrace();
    }
       

    }//GEN-LAST:event_jButton_Agregar_Categoria

    private void jButton_Agregar_Presentacion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agregar_Presentacion
        JInternalFrame_Presentacion internalFrame_presentacion = new JInternalFrame_Presentacion();

        // Calcular las coordenadas para centrar el JInternalFrame
        int x = (jDesktopPane.getWidth() - internalFrame_presentacion.getWidth()) / 2;
        int y = (jDesktopPane.getHeight() - internalFrame_presentacion.getHeight()) / 2;

        // Configurar la posición del JInternalFrame
        internalFrame_presentacion.setLocation(x, y);

        // Agregar la instancia al jDesktopPane
        jDesktopPane.add(internalFrame_presentacion);

        // Hacer la instancia visible
        internalFrame_presentacion.setVisible(true);
        
        
        internalFrame_presentacion.mostrar();

        try {
            // Traer al frente en el jDesktopPane
            internalFrame_presentacion.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton_Agregar_Presentacion

    private void jTextField_precio_compra(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_precio_compra
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_precio_compra

    private void jSpinner_CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSpinner_CantidadKeyTyped


    }//GEN-LAST:event_jSpinner_CantidadKeyTyped

    private void jTextField_precio_compraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_precio_compraKeyTyped
    char car = evt.getKeyChar();
    String currentText = jTextField_precio_compra.getText();

    // Verificar si el carácter es un número, un punto o una coma
    boolean isDigitOrDecimalSeparator = Character.isDigit(car) || car == '.' || car == ',';

    // Verificar si el texto ya contiene un punto o una coma
    boolean containsDecimalSeparator = currentText.contains(".") || currentText.contains(",");

    // Verificar si el texto supera los 8 caracteres o si ya hay dos dígitos decimales después del punto
    boolean exceedsLengthLimit = currentText.replace(".", "").replace(",", "").length() >= 10;
    boolean exceedsDecimalDigitsLimit = currentText.contains(".") && currentText.substring(currentText.indexOf(".") + 1).length() >= 2;

    // Verificar si el carácter es válido
    if (!isDigitOrDecimalSeparator || (containsDecimalSeparator && car == '.') || exceedsLengthLimit || exceedsDecimalDigitsLimit) {
        evt.consume();  // Consumir el evento para evitar que se ingrese el carácter
    }
    }//GEN-LAST:event_jTextField_precio_compraKeyTyped

    private void jTextField_precio_ventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_precio_ventaKeyTyped
   char car = evt.getKeyChar();
    String currentText = jTextField_precio_venta.getText();

    // Verificar si el carácter es un número, un punto o una coma
    boolean isDigitOrDecimalSeparator = Character.isDigit(car) || car == '.' || car == ',';

    // Verificar si el texto ya contiene un punto o una coma
    boolean containsDecimalSeparator = currentText.contains(".") || currentText.contains(",");

    // Verificar si el texto supera los 8 caracteres o si ya hay dos dígitos decimales después del punto
    boolean exceedsLengthLimit = currentText.replace(".", "").replace(",", "").length() >= 10;
    boolean exceedsDecimalDigitsLimit = currentText.contains(".") && currentText.substring(currentText.indexOf(".") + 1).length() >= 2;

    // Verificar si el carácter es válido
    if (!isDigitOrDecimalSeparator || (containsDecimalSeparator && car == '.') || exceedsLengthLimit || exceedsDecimalDigitsLimit) {
        evt.consume();  // Consumir el evento para evitar que se ingrese el carácter
    }

    }//GEN-LAST:event_jTextField_precio_ventaKeyTyped

    private void jSpinner_CantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSpinner_CantidadFocusLost

    }//GEN-LAST:event_jSpinner_CantidadFocusLost

    private void jTextField_precio_ventaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_precio_ventaFocusLost
        // Obtener los valores ingresados en los campos de precio compra y precio venta
        String precioCompraText = jTextField_precio_compra.getText();
        String precioVentaText = jTextField_precio_venta.getText();

        // Validar si ambos campos tienen valores
        if (!precioCompraText.isEmpty() && !precioVentaText.isEmpty()) {
            double precioCompra = Double.parseDouble(precioCompraText);
            double precioVenta = Double.parseDouble(precioVentaText);

            if (precioVenta <= precioCompra) {
                JOptionPane.showMessageDialog(null, "El precio de venta debe ser mayor al precio de compra.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jTextField_precio_ventaFocusLost

    private void jButton_Agregar_Proveedor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agregar_Proveedor
       JInternalFrame_Proveedor internalFrame_Proveedor = new JInternalFrame_Proveedor();

    // Calcular las coordenadas para centrar el JInternalFrame
    int x = (jDesktopPane.getWidth() - internalFrame_Proveedor.getWidth()) / 2;
    int y = (jDesktopPane.getHeight() - internalFrame_Proveedor.getHeight()) / 2;

    // Configurar la posición del JInternalFrame
    internalFrame_Proveedor.setLocation(x, y);

    // Agregar la instancia al jDesktopPane
    jDesktopPane.add(internalFrame_Proveedor);

    // Hacer la instancia visible
    internalFrame_Proveedor.setVisible(true);

    // Mostrar los datos en la tabla
    internalFrame_Proveedor.mostrar();

    try {
        // Traer al frente en el jDesktopPane
        internalFrame_Proveedor.setSelected(true);
    } catch (java.beans.PropertyVetoException e) {
        e.printStackTrace();
    }
       
    }//GEN-LAST:event_jButton_Agregar_Proveedor


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Agregar_Categoria;
    private javax.swing.JButton jButton_Agregar_Laboratorio;
    private javax.swing.JButton jButton_Agregar_Presentacion;
    private javax.swing.JButton jButton_Agregar_Proveedor;
    private javax.swing.JButton jButton_Eliminar_imagen;
    public static javax.swing.JButton jButton_Guardar;
    public static javax.swing.JButton jButton_Ver_Producto;
    private javax.swing.JButton jButton_buscar_imagen;
    public static javax.swing.JComboBox<Clase_Categoria> jComboBox_Categoria;
    public static javax.swing.JComboBox<Clase_Proveedor> jComboBox_Id_Proveedor;
    public static javax.swing.JComboBox<Clase_Laboratorio> jComboBox_Laboratorio;
    public static javax.swing.JComboBox<Clase_Presentacion> jComboBox_Presentacion;
    public static javax.swing.JFormattedTextField jFormattedTextField_fecha_de_caducidad;
    public static javax.swing.JLabel jLabel_Mostrar_Imagen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JSpinner jSpinner_Cantidad;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    public static javax.swing.JTextArea jTextArea_descripcion;
    public static javax.swing.JTextField jTextField_Buscar_Categoria;
    public static javax.swing.JTextField jTextField_Buscar_Id_Proveedor;
    public static javax.swing.JTextField jTextField_Buscar_Laboratorio;
    private javax.swing.JTextField jTextField_Buscar_Presentacion;
    public static javax.swing.JTextField jTextField_nombre;
    public static javax.swing.JTextField jTextField_precio_compra;
    public static javax.swing.JTextField jTextField_precio_venta;
    // End of variables declaration//GEN-END:variables

}
