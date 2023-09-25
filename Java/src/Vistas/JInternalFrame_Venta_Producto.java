package Vistas;

import Controlador.CRUD_Venta;
import Controlador.CRUD_Venta_Producto;
import Modelo.Clase_Cliente;
import Modelo.Clase_Empleado;
import Vistas.JInternalFrame_Venta;
import java.util.List;

import Modelo.Clase_Producto;
import Modelo.Clase_Producto_Cargardatos;
import Modelo.Clase_Venta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class JInternalFrame_Venta_Producto extends javax.swing.JInternalFrame {

    private Timer timer;
    private BigDecimal totalActual = BigDecimal.ZERO;
    private BigDecimal tasaCambioNicaragua;

    public JInternalFrame_Venta_Producto() {
        initComponents();
        personalizarTabla(jTable_Producto);
        ajustarAlturaFilasTabla(jTable_Producto);
        colorearFilasTabla(jTable_Producto);

        personalizarTabla(jTable_Prsmks);
        ajustarAlturaFilasTabla(jTable_Prsmks);
        colorearFilasTabla(jTable_Prsmks);

        setTitle("Venta");
        setFrameIcon(new ImageIcon("C:\\Users\\Diers\\OneDrive\\Escritorio\\CasoFarmacia\\Repositorio-Farmacia-Rosales.-main\\src\\Vistas_Iconos\\apothecary-IconoPequeño.png"));

       
        jTextField_Pago.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calcularCambio();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calcularCambio();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calcularCambio();
            }
        });

        jTextField_Descuento.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calcularTotalConDescuento();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calcularTotalConDescuento();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calcularTotalConDescuento();
            }
        });

        jTextField_Total.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calcularCambio();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calcularCambio();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calcularCambio();
            }
        });
         jComboBox_Producto.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Clase_Producto_Cargardatos) {
                    Clase_Producto_Cargardatos producto = (Clase_Producto_Cargardatos) value;
                    label.setText("<html><font color='blue'>Nombre:</font> " + producto.getNombre() + ", <font color='blue'>Precio:</font> " + producto.getPrecio_Venta() 
                                  + ", <font color='blue'>Categoría:</font> " + producto.getId_Categoria() 
                                  + ", <font color='blue'>Presentación:</font> " + producto.getId_Presentacion() 
                                  + ", <font color='blue'>Laboratorio:</font> " + producto.getId_Laboratorio() + "</html>");
                }
                return label;
            }
        });

        mostrarFechaHora();
        iniciarTimer();
        jTextField_Fecha_Hora.setEditable(false);

        llenarComboEmpleados(jComboEmpleado);
        jComboBox_Producto.setBackground(new Color(204, 255, 255));

        UIManager.put("Slider.background", Color.WHITE);
    }

    private void calcularTotalConDescuento() {
        try {
            String descuentoStr = jTextField_Descuento.getText().trim();
            BigDecimal descuento;

            // Verificar si la caja de descuento está vacía.
            if (!descuentoStr.isEmpty()) {
                descuento = new BigDecimal(descuentoStr).divide(new BigDecimal("100"));
            } else {
                descuento = BigDecimal.ZERO;
            }

            BigDecimal totalConDescuento = totalActual.subtract(totalActual.multiply(descuento));
            jTextField_Total.setText(totalConDescuento.toString());
        } catch (NumberFormatException e) {
            // Manejar la excepción aquí. Podrías mostrar una ventana de diálogo con un mensaje de error, por ejemplo.
            JOptionPane.showMessageDialog(null, "El valor ingresado en la caja de descuento no es válido. Por favor ingrese un número.");
        }
    }

    private void calcularCambio() {
        try {
            BigDecimal pago = new BigDecimal(jTextField_Pago.getText().trim());
            BigDecimal total = new BigDecimal(jTextField_Total.getText().trim());
            BigDecimal cambio = pago.subtract(total);
            jTextField_Cambio.setText(cambio.toString());
        } catch (NumberFormatException e) {
            // Puedes manejar la excepción aquí. Por ejemplo, podrías limpiar el campo jTextField_Cambio.
            jTextField_Cambio.setText("");
        }
    }

    private void personalizarTabla(JTable table) {
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        table.getTableHeader().setBackground(new Color(0, 255, 255));
        table.getTableHeader().setForeground(Color.BLACK);
        Font headerFont = new Font("Segoe UI", Font.BOLD, 12);
        table.getTableHeader().setFont(headerFont);
        table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void ajustarAlturaFilasTabla(JTable table) {
        table.setRowHeight(35);
    }

    private void colorearFilasTabla(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(new Color(204, 255, 255));
                } else {
                    setBackground(new Color(204, 255, 255));
                }
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });
    }

   public void limpiarCampos() {
    jComboCliente.setSelectedIndex(-1);
    jTextField_BuscarCliente.setText("");
    jTextField_Descuento.setText("");
    jTextField_Cambio.setText("");
    jTextField_Pago.setText("");
    jTextField_Total.setText("");

    // Limpiar jTable_Producto
    DefaultTableModel modeloTablaProducto = (DefaultTableModel) jTable_Producto.getModel();
    modeloTablaProducto.setRowCount(0);

    // Limpiar jTable_Prsmks
    DefaultTableModel modeloTablaPrsmks = (DefaultTableModel) jTable_Prsmks.getModel();
    modeloTablaPrsmks.setRowCount(0);
}


    public void Limpiar() {
        jTextField_Busqueda_Combo.setText("");
        jSpinner_Cantidad_Producto.setValue(0);
        jComboBox_Producto.setSelectedIndex(-1);
        jTextField_Stok.setText("");
    }

    private void iniciarTimer() {
        int intervaloActualizacion = 1000;
        timer = new Timer(intervaloActualizacion, e -> {
            mostrarFechaHora();
        });
        timer.start();
    }

    private void mostrarFechaHora() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDateTime = currentDateTime.format(formatter);
        jTextField_Fecha_Hora.setText(formattedDateTime);

        // Cambiar el color de fondo del JTextField
        jTextField_Fecha_Hora.setBackground(new Color(204, 255, 255));
    }

    private boolean isValidNumber(String text) {
        try {
            int value = Integer.parseInt(text);
            return value >= 0 && value <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void llenarproducto(String busqueda) {
        CRUD_Venta_Producto gr = new CRUD_Venta_Producto();
        ArrayList<Clase_Producto_Cargardatos> listaProductos = gr.buscarProductos(busqueda);
        jComboBox_Producto.removeAllItems();
        for (Clase_Producto_Cargardatos producto : listaProductos) {
            jComboBox_Producto.addItem(producto);

        }
    }

    public void agregarProductoATabla() {
    Clase_Producto_Cargardatos productoSeleccionado = (Clase_Producto_Cargardatos) jComboBox_Producto.getSelectedItem();
    int cantidad = (int) jSpinner_Cantidad_Producto.getValue();
    int cantidadStock = Integer.parseInt(jTextField_Stok.getText());

    // Verificar si el producto ya está agregado en la tabla
    DefaultTableModel modelProducto = (DefaultTableModel) jTable_Producto.getModel();
    for (int i = 0; i < modelProducto.getRowCount(); i++) {
        Integer idProducto = (Integer) modelProducto.getValueAt(i, 0);
        if (idProducto != null && idProducto.intValue() == productoSeleccionado.getId_Producto()) {
            JOptionPane.showMessageDialog(null, "El producto ya ha sido agregado a la tabla.");
            return;
        }
    }

    if (productoSeleccionado == null || cantidad <= 0) {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un producto y especificar una cantidad válida.");
        return;
    }
    
    // Comprobar si la cantidad especificada supera la cantidad en el stock
    if (cantidad > cantidadStock) {
        JOptionPane.showMessageDialog(null, "La cantidad especificada supera la cantidad disponible en el stock.");
        return;
    }

    // Agregamos los detalles del producto a la primera tabla
    BigDecimal precioVenta = BigDecimal.valueOf(productoSeleccionado.getPrecio_Venta());
    BigDecimal total = precioVenta.multiply(BigDecimal.valueOf(cantidad));

    modelProducto.insertRow(0, new Object[]{
        productoSeleccionado.getId_Producto(),
        productoSeleccionado.getNombre(),
        String.valueOf(precioVenta),
        cantidad,
        total
    });

    // Agregamos los detalles de la descripción, categoría, presentación y laboratorio a la segunda tabla
    DefaultTableModel modelDetalles = (DefaultTableModel) jTable_Prsmks.getModel();

    modelDetalles.insertRow(0, new Object[]{
        productoSeleccionado.getDescripcion(), // La descripción va en la primera columna (columna 0)
        productoSeleccionado.getId_Categoria(), // La categoría va en la segunda columna (columna 1)
        productoSeleccionado.getId_Presentacion(), // La presentación va en la tercera columna (columna 2)
        productoSeleccionado.getId_Laboratorio() // El laboratorio va en la cuarta columna (columna 3)
    });

    totalActual = totalActual.add(total);
    jTextField_Total.setText(totalActual.toString());

    jComboBox_Producto.setSelectedIndex(-1);
    jSpinner_Cantidad_Producto.setValue(0);
}


    private void calcularTotalTabla() {
        DefaultTableModel model = (DefaultTableModel) jTable_Producto.getModel();
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < model.getRowCount(); i++) {
            BigDecimal filaTotal = (BigDecimal) model.getValueAt(i, 4);
            if (filaTotal != null) {  // Comprobar si el valor es nulo antes de sumarlo al total
                total = total.add(filaTotal);
            }
        }
        totalActual = total;
        jTextField_Total.setText(totalActual.toString());
    }

    public void llenarComboClientes(JComboBox<Clase_Cliente> jComboCliente, String busqueda) {
        CRUD_Venta_Producto gr = new CRUD_Venta_Producto();
        ArrayList<Clase_Cliente> clientes = gr.obtenerNombresApellidosCliente();
        jComboCliente.removeAllItems(); // Limpiar el JComboBox
        for (Clase_Cliente cliente : clientes) {
            jComboCliente.addItem(cliente);
        }

    }

    public void llenarComboEmpleados(JComboBox<Clase_Empleado> jComboEmpleado) {
        CRUD_Venta_Producto gr = new CRUD_Venta_Producto();
        ArrayList<Clase_Empleado> empleados = gr.obtenerEmpleados();
        jComboEmpleado.removeAllItems(); // Limpiar el JComboBox

        // Configurar el renderizador personalizado
        jComboEmpleado.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Clase_Empleado) {
                    Clase_Empleado empleado = (Clase_Empleado) value;
                    String displayText = empleado.getId_Empleado() + " - " + empleado.getNombre_1() + " " + empleado.getApellido_1();
                    return super.getListCellRendererComponent(list, displayText, index, isSelected, cellHasFocus);
                } else {
                    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            }
        });

        // Agregar los empleados al JComboBox
        for (Clase_Empleado empleado : empleados) {
            jComboEmpleado.addItem(empleado);
        }

        // Establecer el color de fondo
        jComboEmpleado.setBackground(new Color(204, 255, 255));
    }

    public void guardarVentaProducto() {
        CRUD_Venta_Producto ventaProductoDAO = new CRUD_Venta_Producto();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime fechaHora = LocalDateTime.parse(jTextField_Fecha_Hora.getText().trim(), formatter);

        Clase_Cliente clienteSeleccionado = (Clase_Cliente) jComboCliente.getSelectedItem();
        int idCliente = (clienteSeleccionado != null) ? clienteSeleccionado.getId_Cliente() : -1;

        Object itemEmpleado = jComboEmpleado.getSelectedItem();
        Clase_Empleado empleadoSeleccionado = null;
        try {
            empleadoSeleccionado = (Clase_Empleado) itemEmpleado;
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(null, "El item seleccionado no es una instancia de Clase_Empleado");
            e.printStackTrace();
            return;
        }
        int idEmpleado = (empleadoSeleccionado != null) ? empleadoSeleccionado.getId_Empleado() : 0;

        String descuentoStr = jTextField_Descuento.getText().trim();
        descuentoStr = descuentoStr.replace("%", "");
        BigDecimal descuento = null;

        if (!descuentoStr.isEmpty()) {
            try {
                descuento = new BigDecimal(descuentoStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido para el descuento.");
                return;
            }
        }

        // Obtener el modelo de la tabla de productos
        DefaultTableModel modelo = (DefaultTableModel) jTable_Producto.getModel();

        List<Clase_Venta> ventas = new ArrayList<>();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            int idProducto = 0;
            int cantidad = 0;

            Object idProductoObj = modelo.getValueAt(i, 0);
            Object cantidadObj = modelo.getValueAt(i, 3);

            if (idProductoObj != null && cantidadObj != null) {
                idProducto = Integer.parseInt(idProductoObj.toString());
                cantidad = Integer.parseInt(cantidadObj.toString());

                Clase_Venta venta = new Clase_Venta(fechaHora, idCliente, idEmpleado, cantidad, descuento, idProducto);
                ventas.add(venta);
            }
        }

        // Agregar todas las ventas y productos en la base de datos
        ventaProductoDAO.agregarVentasYProductos(ventas);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField_BuscarCliente = new javax.swing.JTextField();
        jTextField_Fecha_Hora = new javax.swing.JTextField();
        jComboEmpleado = new javax.swing.JComboBox<>();
        jComboCliente = new javax.swing.JComboBox<>();
        jButton_guardar_venta = new javax.swing.JButton();
        jButton_ver_Formulario_venta = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextField_Pago = new javax.swing.JTextField();
        jTextField_Cambio = new javax.swing.JTextField();
        jTextField_Total = new javax.swing.JTextField();
        jTextField_Descuento = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTextField_Busqueda_Combo = new javax.swing.JTextField();
        jComboBox_Producto = new javax.swing.JComboBox<>();
        jButton_Agregar = new javax.swing.JButton();
        jButton_Borrar_p = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Prsmks = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Producto = new javax.swing.JTable();
        jSpinner_Cantidad_Producto = new javax.swing.JSpinner();
        jTextField_Stok = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTextField_BuscarCliente.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_BuscarCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_BuscarCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_BuscarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_BuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_BuscarCliente(evt);
            }
        });
        jTextField_BuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_BuscarClienteKeyReleased(evt);
            }
        });

        jTextField_Fecha_Hora.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Fecha_Hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Fecha_Hora.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha y Hora", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Fecha_Hora.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Fecha_Hora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Fecha_Hora(evt);
            }
        });

        jComboEmpleado.setBackground(new java.awt.Color(204, 255, 255));
        jComboEmpleado.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jComboEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jComboEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEmpleadoActionPerformed(evt);
            }
        });

        jComboCliente.setBackground(new java.awt.Color(204, 255, 255));
        jComboCliente.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jComboCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jComboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboClienteActionPerformed(evt);
            }
        });

        jButton_guardar_venta.setBackground(new java.awt.Color(51, 255, 51));
        jButton_guardar_venta.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_guardar_venta.setText("Guardar");
        jButton_guardar_venta.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton_guardar_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_venta(evt);
            }
        });

        jButton_ver_Formulario_venta.setBackground(new java.awt.Color(204, 204, 255));
        jButton_ver_Formulario_venta.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_ver_Formulario_venta.setText("Ver Ventas");
        jButton_ver_Formulario_venta.setBorder(null);
        jButton_ver_Formulario_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ver_Formulario_ventaActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jTextField_Pago.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Pago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Pago.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Pago.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Pago(evt);
            }
        });

        jTextField_Cambio.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Cambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Cambio.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vuelto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Cambio.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Cambio(evt);
            }
        });

        jTextField_Total.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Total.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Total.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Total(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_Pago)
                .addGap(31, 31, 31)
                .addComponent(jTextField_Cambio)
                .addGap(30, 30, 30)
                .addComponent(jTextField_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Pago, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jTextField_Descuento.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Descuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Descuento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descuento %", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Descuento.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Descuento(evt);
            }
        });
        jTextField_Descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_DescuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_DescuentoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jTextField_BuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextField_Descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_Fecha_Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(jButton_guardar_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_ver_Formulario_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 32, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_BuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Fecha_Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jComboEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_guardar_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_ver_Formulario_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 530));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTextField_Busqueda_Combo.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Busqueda_Combo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Busqueda_Combo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Busqueda_Combo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Busqueda_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Busqueda_Combo(evt);
            }
        });
        jTextField_Busqueda_Combo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_Busqueda_ComboKeyReleased(evt);
            }
        });

        jComboBox_Producto.setBackground(new java.awt.Color(204, 255, 255));
        jComboBox_Producto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jComboBox_Producto.setMinimumSize(new java.awt.Dimension(72, 45));
        jComboBox_Producto.setPreferredSize(new java.awt.Dimension(72, 4));
        jComboBox_Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Producto(evt);
            }
        });

        jButton_Agregar.setBackground(new java.awt.Color(51, 255, 51));
        jButton_Agregar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Agregar.setForeground(new java.awt.Color(51, 51, 51));
        jButton_Agregar.setText("Agregar");
        jButton_Agregar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Agregar(evt);
            }
        });

        jButton_Borrar_p.setBackground(new java.awt.Color(255, 102, 102));
        jButton_Borrar_p.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton_Borrar_p.setText("Eliminar");
        jButton_Borrar_p.setBorder(null);
        jButton_Borrar_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Borrar_p(evt);
            }
        });

        jTable_Prsmks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Descripcion", "Categoria", "Presentacion", "Laboratorio"
            }
        ));
        jScrollPane2.setViewportView(jTable_Prsmks);

        jTable_Producto.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jTable_Producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Precio Venta", "Cantidad", "total"
            }
        ));
        jTable_Producto.setGridColor(new java.awt.Color(0, 0, 0));
        jTable_Producto.setShowGrid(true);
        jScrollPane1.setViewportView(jTable_Producto);

        jSpinner_Cantidad_Producto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cantidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jSpinner_Cantidad_Producto.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jSpinner_Cantidad_Producto(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jTextField_Stok.setEditable(false);
        jTextField_Stok.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Stok.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Stok.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Stok", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Stok.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Stok(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox_Producto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField_Busqueda_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton_Borrar_p, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jSpinner_Cantidad_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jTextField_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Busqueda_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner_Cantidad_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Borrar_p, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jComboBox_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 820, 530));

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

    private void jButton_guardar_venta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_venta
      
        guardarVentaProducto();
        limpiarCampos();
         JOptionPane.showMessageDialog(this, "¡La venta fue agregada correctamente!");


    }//GEN-LAST:event_jButton_guardar_venta

    private void jTextField_BuscarCliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_BuscarCliente

    }//GEN-LAST:event_jTextField_BuscarCliente

    private void jTextField_Fecha_Hora(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Fecha_Hora
        mostrarFechaHora();
    }//GEN-LAST:event_jTextField_Fecha_Hora

    private void jComboBox_Producto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Producto
        jComboBox_Producto.setBackground(new Color(204, 255, 255));

    }//GEN-LAST:event_jComboBox_Producto

    private void jTextField_Busqueda_Combo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Busqueda_Combo

    }//GEN-LAST:event_jTextField_Busqueda_Combo

    private void jTextField_Busqueda_ComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Busqueda_ComboKeyReleased
        String busqueda = jTextField_Busqueda_Combo.getText();

        CRUD_Venta_Producto crudVentaProducto = new CRUD_Venta_Producto();

        ArrayList<Clase_Producto_Cargardatos> listaProductos = crudVentaProducto.buscarProductos(busqueda);
        jComboBox_Producto.removeAllItems();
        for (Clase_Producto_Cargardatos producto : listaProductos) {
            jComboBox_Producto.addItem(producto);
        }

        // Obtener la cantidad de stock del producto y mostrarlo en jTextField_Stok
        if (!listaProductos.isEmpty()) {
            Clase_Producto_Cargardatos productoSeleccionado = listaProductos.get(0); // Obtener el primer producto de la lista
            int cantidadStock = crudVentaProducto.obtenerCantidadStockProducto(String.valueOf(productoSeleccionado.getId_Producto()));
            jTextField_Stok.setText(String.valueOf(cantidadStock));
        }
    }//GEN-LAST:event_jTextField_Busqueda_ComboKeyReleased

    private void jButton_Agregar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Agregar
        agregarProductoATabla();
        Limpiar();
    }//GEN-LAST:event_jButton_Agregar

    private void jButton_Borrar_p(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Borrar_p
        int filaSeleccionadaProducto = jTable_Producto.getSelectedRow();
        int filaSeleccionadaDetalles = jTable_Prsmks.getSelectedRow();

        DefaultTableModel modeloProducto = (DefaultTableModel) jTable_Producto.getModel();
        DefaultTableModel modeloDetalles = (DefaultTableModel) jTable_Prsmks.getModel();

        // Si seleccionamos fila en jTable_Producto
        if (filaSeleccionadaProducto != -1 && filaSeleccionadaProducto < modeloDetalles.getRowCount()) {
            modeloProducto.removeRow(filaSeleccionadaProducto);
            modeloDetalles.removeRow(filaSeleccionadaProducto);

            calcularTotalTabla();
            return;  // Regresamos para no ejecutar el siguiente bloque de eliminación
        }

        // Si seleccionamos fila en jTable_Prsmks
        if (filaSeleccionadaDetalles != -1 && filaSeleccionadaDetalles < modeloProducto.getRowCount()) {
            modeloDetalles.removeRow(filaSeleccionadaDetalles);
            modeloProducto.removeRow(filaSeleccionadaDetalles);

            calcularTotalTabla();
            return;  // Regresamos para no mostrar el mensaje de error
        }

        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para borrar.");


    }//GEN-LAST:event_jButton_Borrar_p

    private void jSpinner_Cantidad_Producto(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSpinner_Cantidad_Producto
        jSpinner_Cantidad_Producto.setBackground(new Color(204, 255, 255));

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

    }//GEN-LAST:event_jSpinner_Cantidad_Producto

    private void jTextField_Descuento(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Descuento

    }//GEN-LAST:event_jTextField_Descuento

    private void jTextField_DescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_DescuentoKeyTyped
        char c = evt.getKeyChar();

        // Verificar si el caracter es un dígito o el carácter de retroceso (backspace)
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter no válido
        }

        // Obtener el texto actual en el JTextField
        String currentText = jTextField_Descuento.getText();

        // Obtener el nuevo texto agregando el carácter actual
        String newText = currentText + c;

        // Verificar si el nuevo texto es un número válido en el rango de 0 a 100
        if (!isValidNumber(newText)) {
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter no válido
        }
    }//GEN-LAST:event_jTextField_DescuentoKeyTyped

    private void jTextField_Total(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Total
        calcularTotalTabla();
    }//GEN-LAST:event_jTextField_Total

    private void jComboEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEmpleadoActionPerformed


    }//GEN-LAST:event_jComboEmpleadoActionPerformed

    private void jComboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboClienteActionPerformed

    }//GEN-LAST:event_jComboClienteActionPerformed

    private void jTextField_BuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarClienteKeyReleased
        String busqueda = jTextField_BuscarCliente.getText();

        CRUD_Venta_Producto gr = new CRUD_Venta_Producto();
        ArrayList<Clase_Cliente> listaClientes = gr.buscarClientes(busqueda);
        jComboCliente.removeAllItems();
        for (Clase_Cliente cliente : listaClientes) {
            jComboCliente.addItem(cliente);
        }

    }//GEN-LAST:event_jTextField_BuscarClienteKeyReleased

    private void jButton_ver_Formulario_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ver_Formulario_ventaActionPerformed
        Container container = getParent();
        if (container instanceof JDesktopPane) {
            JDesktopPane desktopPane = (JDesktopPane) container;

            // Buscar el formulario JInternalFrame_Venta en el JDesktopPane
            JInternalFrame_Venta ventaForm = null;
            JInternalFrame[] frames = desktopPane.getAllFrames();
            for (JInternalFrame frame : frames) {
                if (frame instanceof JInternalFrame_Venta) {
                    ventaForm = (JInternalFrame_Venta) frame;
                    break;
                }
            }

            if (ventaForm != null) {
                // Si el formulario ya está abierto, selecciónalo
                try {
                    ventaForm.setSelected(true);
                } catch (java.beans.PropertyVetoException e) {
                    e.printStackTrace();
                }
            } else {
                // Si el formulario no está abierto, crea una nueva instancia y añádela al JDesktopPane
                ventaForm = new JInternalFrame_Venta();
                desktopPane.add(ventaForm);
                ventaForm.setVisible(true);
                try {
                    ventaForm.setSelected(true);
                } catch (java.beans.PropertyVetoException e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton_ver_Formulario_ventaActionPerformed

    private void jTextField_Cambio(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Cambio
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Cambio

    private void jTextField_DescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_DescuentoKeyReleased

    }//GEN-LAST:event_jTextField_DescuentoKeyReleased

    private void jTextField_Pago(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Pago
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Pago

    private void jTextField_Stok(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Stok
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Stok


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_Borrar_p;
    public static javax.swing.JButton jButton_guardar_venta;
    public static javax.swing.JButton jButton_ver_Formulario_venta;
    public static javax.swing.JComboBox<Clase_Producto_Cargardatos> jComboBox_Producto;
    public static javax.swing.JComboBox<Clase_Cliente> jComboCliente;
    public javax.swing.JComboBox<Clase_Empleado> jComboEmpleado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner_Cantidad_Producto;
    private javax.swing.JTable jTable_Producto;
    private javax.swing.JTable jTable_Prsmks;
    private javax.swing.JTextField jTextField_BuscarCliente;
    private javax.swing.JTextField jTextField_Busqueda_Combo;
    private javax.swing.JTextField jTextField_Cambio;
    public static javax.swing.JTextField jTextField_Descuento;
    private javax.swing.JTextField jTextField_Fecha_Hora;
    private javax.swing.JTextField jTextField_Pago;
    private javax.swing.JTextField jTextField_Stok;
    private javax.swing.JTextField jTextField_Total;
    // End of variables declaration//GEN-END:variables
}
