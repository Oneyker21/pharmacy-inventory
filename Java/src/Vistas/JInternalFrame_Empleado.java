package Vistas;

import Modelo.Clase_Empleado;
import Controlador_Conexion_DB.Conexion;
import Controlador.*;
import static Vistas.JInternalFrame_Compra.jTable_Compra;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Time;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;


/**
 *
 * @author diedr
 */
public class JInternalFrame_Empleado extends javax.swing.JInternalFrame {

    private JPanel panel;
    public final Conexion con = new Conexion();
    public final Connection cn = (Connection) con.conectar();

    public JInternalFrame_Empleado() {
        initComponents();
       setTitle("Empleado");
        setFrameIcon(new ImageIcon("C:\\Users\\Diers\\OneDrive\\Escritorio\\CasoFarmacia\\Repositorio-Farmacia-Rosales.-main\\src\\Vistas_Iconos\\apothecary-IconoPequeño.png"));

        colorearFilasTabla();
        personalizarTitulosTabla();
        ajustarAlturaFilasTabla();

    }

    private void personalizarTitulosTabla() {
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) jTable_Empleado.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Centra los títulos
        jTable_Empleado.getTableHeader().setDefaultRenderer(headerRenderer);
        jTable_Empleado.getTableHeader().setBackground(new Color(0, 255, 255)); // Cambia el color de los títulos
        jTable_Empleado.getTableHeader().setForeground(Color.BLACK); // Cambia el color del texto de los títulos a negro
        Font headerFont = new Font("Segoe UI", Font.BOLD, 12); // Cambia el tamaño de letra de los títulos
        jTable_Empleado.getTableHeader().setFont(headerFont);
        jTable_Empleado.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void ajustarAlturaFilasTabla() {
        jTable_Empleado.setRowHeight(35); // Ajusta aquí la altura deseada en píxeles
    }

    private void colorearFilasTabla() {
        jTable_Empleado.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        jTextIdEmpleado2.setText("");
        jFormattedTextField_Cedula.setText("");
        jText_Salario2.setText("");
        jTextNombre1.setText("");
        jTextNombre2.setText("");
        jTextApellido1.setText("");
        jTextApellido2.setText("");
        jFormattedTextField_Telefono2.setText("");
        jTextGmail2.setText("");
        jTextADirecion2.setText("");
        jFormattedTextField_Hora_Entrada2.setText("");
        jFormattedTextField_Hora_Salida2.setText("");
    }

    public void guardarEmpleado() {
        CRUD_Empleado cl = new CRUD_Empleado();
        String Cedula = jFormattedTextField_Cedula.getText();
        String Nombre1 = jTextNombre1.getText();
        String Nombre2 = jTextNombre2.getText();
        String Apellido1 = jTextApellido1.getText();
        String Apellido2 = jTextApellido2.getText();
        String salarioStr = jText_Salario2.getText();
        String NumeroCelular = jFormattedTextField_Telefono2.getText();
        String gmail = jTextGmail2.getText();
        String direccion = jTextADirecion2.getText();
        String horaEntradaStr = jFormattedTextField_Hora_Entrada2.getText();
        String horaSalidaStr = jFormattedTextField_Hora_Salida2.getText();

        // Verificar que los campos no estén vacíos
        if (Cedula.isEmpty() || Nombre1.isEmpty() || salarioStr.isEmpty() || horaEntradaStr.isEmpty() || horaSalidaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tiene datos vacíos");
            return;
        }

        try {
            // Convertir las variables String a BigDecimal y Time
            BigDecimal salarioValue = new BigDecimal(salarioStr);
            Time horaEntrada = Time.valueOf(horaEntradaStr);
            Time horaSalida = Time.valueOf(horaSalidaStr);

            // Crear objeto Clase_Empleado con los datos obtenidos
            Clase_Empleado empleado = new Clase_Empleado(salarioValue, horaEntrada, horaSalida, Cedula, Nombre1, Nombre2, Apellido1, Apellido2, NumeroCelular, gmail, direccion);

            cl.Guardar(empleado);

            mostrar();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Formato de número incorrecto");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: Formato de hora incorrecto");
        }
    }

    public void mostrar() {
        try {
            DefaultTableModel modelo;
            CRUD_Empleado Empleado = new CRUD_Empleado();
            modelo = Empleado.mostrarDatos();
            jTable_Empleado.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void editarCliente() {
        CRUD_Empleado cc = new CRUD_Empleado();

        String salarioStr = jText_Salario2.getText().replace(',', '.'); // Reemplazar coma por punto para formato decimal

        if (salarioStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de salario está vacío.");
            return; // Salir del método si el campo está vacío
        }

        BigDecimal salario;

        try {
            salario = new BigDecimal(salarioStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo de salario contiene un valor inválido.");
            return; // Salir del método si el valor no es numérico
        }

        String horaEntradaStr = jFormattedTextField_Hora_Entrada2.getText();
        String horaSalidaStr = jFormattedTextField_Hora_Salida2.getText();

        // Verificar que los campos de hora no estén vacíos
        if (horaEntradaStr.isEmpty() || horaSalidaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos de hora están vacíos.");
            return; // Salir del método si alguno de los campos de hora está vacío
        }

        Time horaEntrada;
        Time horaSalida;

        try {
            horaEntrada = Time.valueOf(horaEntradaStr);
            horaSalida = Time.valueOf(horaSalidaStr);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Formato de hora incorrecto.");
            return; // Salir del método si el formato de hora es incorrecto
        }

        Clase_Empleado cl = new Clase_Empleado(
                salario,
                horaEntrada,
                horaSalida,
                jFormattedTextField_Cedula.getText(),
                jTextNombre1.getText(),
                jTextNombre2.getText(),
                jTextApellido1.getText(),
                jTextApellido2.getText(),
                jFormattedTextField_Telefono2.getText(),
                jTextGmail2.getText(),
                jTextADirecion2.getText()
        );

        cc.actualizar(cl);
    }

    public void BuscarEmpleado() {
        try {
            DefaultTableModel modelo;
            CRUD_Empleado cli = new CRUD_Empleado();
            modelo = cli.buscarDatos(jTextBuscar2.getText());

            if (modelo != null) {
                jTable_Empleado.setModel(modelo);
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
        jTextIdEmpleado2 = new javax.swing.JTextField();
        jTextBuscar2 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jFormattedTextField_Telefono2 = new javax.swing.JFormattedTextField();
        jTextGmail2 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextADirecion2 = new javax.swing.JTextArea();
        jText_Salario2 = new javax.swing.JTextField();
        jFormattedTextField_Hora_Entrada2 = new javax.swing.JFormattedTextField();
        jFormattedTextField_Hora_Salida2 = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Empleado = new javax.swing.JTable();
        guardar_empleado = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        Actualizar = new javax.swing.JButton();
        Borrar = new javax.swing.JButton();
        jFormattedTextField_Cedula = new javax.swing.JFormattedTextField();
        jTextNombre1 = new javax.swing.JTextField();
        jTextNombre2 = new javax.swing.JTextField();
        jTextApellido1 = new javax.swing.JTextField();
        jTextApellido2 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);
        setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextIdEmpleado2.setEditable(false);
        jTextIdEmpleado2.setBackground(new java.awt.Color(204, 255, 255));
        jTextIdEmpleado2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextIdEmpleado2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextIdEmpleado2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextIdEmpleado2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_id_empleado(evt);
            }
        });
        jPanel3.add(jTextIdEmpleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 100, 45));

        jTextBuscar2.setBackground(new java.awt.Color(204, 255, 255));
        jTextBuscar2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTextBuscar2.setForeground(new java.awt.Color(153, 153, 153));
        jTextBuscar2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextBuscar2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextBuscar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextBuscarMouseClicked(evt);
            }
        });
        jTextBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextBuscarKeyReleased(evt);
            }
        });
        jPanel3.add(jTextBuscar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 340, 45));

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel8.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel5(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jFormattedTextField_Telefono2.setBackground(new java.awt.Color(204, 255, 255));
        jFormattedTextField_Telefono2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Telefono", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        try {
            jFormattedTextField_Telefono2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Telefono2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField_Telefono2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField_Telefono(evt);
            }
        });

        jTextGmail2.setBackground(new java.awt.Color(204, 255, 255));
        jTextGmail2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextGmail2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gmail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextGmail2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextGmailnombre_2TextField(evt);
            }
        });
        jTextGmail2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextGmailKeyTyped(evt);
            }
        });

        jTextADirecion2.setBackground(new java.awt.Color(204, 255, 255));
        jTextADirecion2.setColumns(20);
        jTextADirecion2.setRows(5);
        jTextADirecion2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dirrecion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextADirecion2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                direccion_TextField(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTextADirecion2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextADirecionKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(jTextADirecion2);

        jText_Salario2.setBackground(new java.awt.Color(204, 255, 255));
        jText_Salario2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jText_Salario2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jText_Salario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_Salarionombre_1TextField(evt);
            }
        });
        jText_Salario2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_SalarioKeyTyped(evt);
            }
        });

        jFormattedTextField_Hora_Entrada2.setBackground(new java.awt.Color(204, 255, 255));
        jFormattedTextField_Hora_Entrada2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hora de Entrada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        try {
            jFormattedTextField_Hora_Entrada2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Hora_Entrada2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jFormattedTextField_Hora_Salida2.setBackground(new java.awt.Color(204, 255, 255));
        jFormattedTextField_Hora_Salida2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hora de Entrada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        try {
            jFormattedTextField_Hora_Salida2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Hora_Salida2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextField_Telefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextGmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jText_Salario2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jFormattedTextField_Hora_Entrada2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jFormattedTextField_Hora_Salida2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jText_Salario2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField_Hora_Entrada2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField_Hora_Salida2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField_Telefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextGmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1300, 70));

        jTable_Empleado.setBackground(new java.awt.Color(204, 255, 255));
        jTable_Empleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Empleado", "Nombre 1", "Nombre 2", "Apellido 1", "Apellido 2", "Numero Celular", "Gmail", "Direccion", "Hora Entrada", "Hora Salida"
            }
        ));
        jTable_Empleado.setGridColor(new java.awt.Color(0, 153, 153));
        jTable_Empleado.setShowGrid(true);
        jTable_Empleado.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable_Empleado(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(jTable_Empleado);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 1330, 290));

        guardar_empleado.setBackground(new java.awt.Color(51, 255, 51));
        guardar_empleado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        guardar_empleado.setText("Agregar");
        guardar_empleado.setBorder(null);
        guardar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_empleado(evt);
            }
        });
        jPanel3.add(guardar_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 80, 30));

        Editar.setBackground(new java.awt.Color(255, 255, 51));
        Editar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Editar.setText("Editar");
        Editar.setBorder(null);
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        jPanel3.add(Editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 80, 30));

        Actualizar.setBackground(new java.awt.Color(102, 204, 255));
        Actualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Actualizar.setText("Actualizar");
        Actualizar.setBorder(null);
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        jPanel3.add(Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, 80, 30));

        Borrar.setBackground(new java.awt.Color(255, 102, 102));
        Borrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Borrar.setText("Eliminar");
        Borrar.setBorder(null);
        Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarActionPerformed(evt);
            }
        });
        jPanel3.add(Borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 200, 80, 30));

        jFormattedTextField_Cedula.setBackground(new java.awt.Color(204, 255, 255));
        jFormattedTextField_Cedula.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cedula", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        try {
            jFormattedTextField_Cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-######-####U")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(jFormattedTextField_Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 220, 45));

        jTextNombre1.setBackground(new java.awt.Color(204, 255, 255));
        jTextNombre1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextNombre1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Primer Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_1TextField(evt);
            }
        });
        jTextNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNombre1KeyTyped(evt);
            }
        });
        jPanel3.add(jTextNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 210, 45));

        jTextNombre2.setBackground(new java.awt.Color(204, 255, 255));
        jTextNombre2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextNombre2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Segundo Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextNombre2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_2TextField(evt);
            }
        });
        jTextNombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNombre2KeyTyped(evt);
            }
        });
        jPanel3.add(jTextNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 210, 45));

        jTextApellido1.setBackground(new java.awt.Color(204, 255, 255));
        jTextApellido1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextApellido1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Primer Apellido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextApellido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellido_1TextField(evt);
            }
        });
        jTextApellido1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextApellido1KeyTyped(evt);
            }
        });
        jPanel3.add(jTextApellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 40, 210, 45));

        jTextApellido2.setBackground(new java.awt.Color(204, 255, 255));
        jTextApellido2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextApellido2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Segundo Apellido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextApellido2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellido_2TextField(evt);
            }
        });
        jTextApellido2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextApellido2KeyTyped(evt);
            }
        });
        jPanel3.add(jTextApellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 40, 190, 45));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1351, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextApellido2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextApellido2KeyTyped
        char car = evt.getKeyChar();
        String texto = jTextApellido2.getText(); // Obtener el texto actual en el campo

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
                || texto.length() >= 32) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextApellido2KeyTyped

    private void apellido_2TextField(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellido_2TextField
        // TODO add your handling code here:
    }//GEN-LAST:event_apellido_2TextField

    private void jTextApellido1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextApellido1KeyTyped
        char car = evt.getKeyChar();
        String texto = jTextApellido1.getText(); // Obtener el texto actual en el campo

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
                || texto.length() >= 32) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextApellido1KeyTyped

    private void apellido_1TextField(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellido_1TextField
        // TODO add your handling code here:
    }//GEN-LAST:event_apellido_1TextField

    private void jTextNombre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNombre2KeyTyped
        char car = evt.getKeyChar();
        String texto = jTextNombre2.getText(); // Obtener el texto actual en el campo

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
                || texto.length() >= 32) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextNombre2KeyTyped

    private void nombre_2TextField(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_2TextField
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_2TextField

    private void jTextNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNombre1KeyTyped
        char car = evt.getKeyChar();
        String texto = jTextNombre1.getText(); // Obtener el texto actual en el campo

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
                || texto.length() >= 32) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextNombre1KeyTyped

    private void nombre_1TextField(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_1TextField
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_1TextField

    private void BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarActionPerformed
        int selectedRow = jTable_Empleado.getSelectedRow();
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
                String idempleadoString = jTable_Empleado.getValueAt(selectedRow, 0).toString();
                int idempleado = Integer.parseInt(idempleadoString);

                CRUD_Empleado cli = new CRUD_Empleado();
                cli.eliminar(idempleado);

                mostrar();

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JLabel messageLabel = new JLabel("Empleado eliminado correctamente");
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

            JLabel messageLabel = new JLabel("Debe seleccionar un Empleado");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
            messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(messageLabel, BorderLayout.CENTER);

            ImageIcon icon = new ImageIcon(getClass().getResource("/Vistas_Iconos/abvertencia.png"));
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(iconLabel, BorderLayout.WEST);

            JOptionPane.showMessageDialog(null, panel, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BorrarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        String idempleadoText = jTextIdEmpleado2.getText();
        int idempleado = Integer.parseInt(idempleadoText);
        String Cedula = jFormattedTextField_Cedula.getText();
        String Nombre1 = jTextNombre1.getText();
        String Nombre2 = jTextNombre2.getText();
        String Apellido1 = jTextApellido1.getText();
        String Apellido2 = jTextApellido2.getText();
        String salario = jText_Salario2.getText();
        String NumeroCelular = jFormattedTextField_Telefono2.getText();
        String gmail = jTextGmail2.getText();
        String direccion = jTextADirecion2.getText();
        String horaEntradaStr = jFormattedTextField_Hora_Entrada2.getText();
        String horaSalidaStr = jFormattedTextField_Hora_Salida2.getText();

        if (jTextIdEmpleado2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tiene datos vacíos");
        } else {
            int option = JOptionPane.showOptionDialog(
                    null,
                    "¿Desea actualizar el Empleado?",
                    "Confirmar Actualización",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(getClass().getResource("/Vistas_Iconos/actualizar.png")),
                    new Object[]{"Sí", "No"},
                    "No"
            );

            if (option == JOptionPane.YES_OPTION) {
                // Crear objeto Clase_Empleado con los datos obtenidos
                BigDecimal salarioValue;
                try {
                    salarioValue = new BigDecimal(salario);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El campo de salario contiene un valor inválido.");
                    return;
                }

                Time horaEntrada;
                Time horaSalida;
                try {
                    horaEntrada = Time.valueOf(horaEntradaStr);
                    horaSalida = Time.valueOf(horaSalidaStr);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Formato de hora incorrecto.");
                    return;
                }

                Clase_Empleado empleado = new Clase_Empleado(salarioValue, horaEntrada, horaSalida, idempleado,
                        Cedula, Nombre1, Nombre2, Apellido1, Apellido2, NumeroCelular, gmail, direccion);

                // Llamar al método "actualizar" de CRUD_Empleado
                CRUD_Empleado EmpleadoCRUD = new CRUD_Empleado();
                EmpleadoCRUD.actualizar(empleado);

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JLabel messageLabel = new JLabel("Empleado actualizado exitosamente.");
                messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
                messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panel.add(messageLabel, BorderLayout.CENTER);

                ImageIcon icon = new ImageIcon(getClass().getResource("/Vistas_Iconos/actualizar.png"));
                JLabel iconLabel = new JLabel(icon);
                iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panel.add(iconLabel, BorderLayout.WEST);

                JOptionPane.showMessageDialog(null, panel, "Actualización Exitosa", JOptionPane.PLAIN_MESSAGE);
            }
        }

        CRUD_Empleado empleadoCRUD = new CRUD_Empleado();
        empleadoCRUD.mostrarDatos();
        limpiar();
        mostrar();
    }//GEN-LAST:event_ActualizarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int filaSeleccionada = jTable_Empleado.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla para editar");
        } else {
            String Id_Empleado = jTable_Empleado.getValueAt(filaSeleccionada, 0) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 0).toString() : "";
            String Cedula = jTable_Empleado.getValueAt(filaSeleccionada, 1) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 1).toString() : "";

            String Nombre_1 = jTable_Empleado.getValueAt(filaSeleccionada, 2) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 2).toString() : "";
            String Nombre_2 = jTable_Empleado.getValueAt(filaSeleccionada, 3) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 3).toString() : "";
            String Apellido_1 = jTable_Empleado.getValueAt(filaSeleccionada, 4) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 4).toString() : "";
            String Apellido_2 = jTable_Empleado.getValueAt(filaSeleccionada, 5) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 5).toString() : "";
            String salario = jTable_Empleado.getValueAt(filaSeleccionada, 6) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 6).toString() : "";
            String horaEntradaStr = jTable_Empleado.getValueAt(filaSeleccionada, 7) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 7).toString() : "";
            String horaSalidaStr = jTable_Empleado.getValueAt(filaSeleccionada, 8) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 8).toString() : "";

            String Numero_Celular = jTable_Empleado.getValueAt(filaSeleccionada, 9) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 9).toString() : "";
            String Gmail = jTable_Empleado.getValueAt(filaSeleccionada, 10) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 10).toString() : "";
            String Direccion = jTable_Empleado.getValueAt(filaSeleccionada, 11) != null ? jTable_Empleado.getValueAt(filaSeleccionada, 11).toString() : "";

            jTextIdEmpleado2.setText(Id_Empleado);
            jFormattedTextField_Cedula.setText(Cedula);
            jTextNombre1.setText(Nombre_1);
            jTextNombre2.setText(Nombre_2);
            jTextApellido1.setText(Apellido_1);
            jTextApellido2.setText(Apellido_2);
            jText_Salario2.setText(salario);
            jFormattedTextField_Telefono2.setText(Numero_Celular);
            jTextGmail2.setText(Gmail);
            jTextADirecion2.setText(Direccion);
            jFormattedTextField_Hora_Entrada2.setText(horaEntradaStr);
            jFormattedTextField_Hora_Salida2.setText(horaSalidaStr);

            jTextIdEmpleado2.setEditable(false); // Deshabilitar la edición del ID
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void guardar_empleado(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_empleado
        try {
            if (jTextNombre1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tiene datos vacíos");
            } else {
                int option = JOptionPane.showOptionDialog(
                        null,
                        "¿Desea guardar el Empleado?",
                        "Confirmar Guardado",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon(getClass().getResource("/Vistas_Iconos/agregar.png")),
                        new Object[]{"Sí", "No"},
                        "No"
                );

                if (option == JOptionPane.YES_OPTION) {
                    guardarEmpleado();
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

                    mostrar();
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }//GEN-LAST:event_guardar_empleado

    private void jTable_Empleado(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable_Empleado
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_Empleado

    private void jPanel5(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel5
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Crear un JPanel
        JPanel panel = new JPanel();

        // Aplicar el borde al JPanel
        panel.setBorder(borde);
    }//GEN-LAST:event_jPanel5

    private void jText_SalarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_SalarioKeyTyped
        char c = evt.getKeyChar();
        String salarioText = jText_Salario2.getText();

        // Validar si el carácter ingresado es un dígito numérico
        if (Character.isDigit(c)) {
            // Validar la longitud máxima del campo de texto
            if (salarioText.length() >= 10) {
                evt.consume(); // Ignorar el carácter ingresado si se alcanza la longitud máxima
            }
        } else {
            evt.consume(); // Ignorar el carácter ingresado si no es un dígito numérico
        }
    }//GEN-LAST:event_jText_SalarioKeyTyped

    private void jText_Salarionombre_1TextField(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_Salarionombre_1TextField
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_Salarionombre_1TextField

    private void jTextADirecionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextADirecionKeyTyped
        String texto = jTextADirecion2.getText();

        if (texto.length() >= 200) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextADirecionKeyTyped

    private void direccion_TextField(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_direccion_TextField
        // TODO add your handling code here:
    }//GEN-LAST:event_direccion_TextField

    private void jTextGmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextGmailKeyTyped
        String texto = jTextGmail2.getText();

        if (texto.length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextGmailKeyTyped

    private void jTextGmailnombre_2TextField(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextGmailnombre_2TextField
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextGmailnombre_2TextField

    private void jFormattedTextField_Telefono(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField_Telefono
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField_Telefono

    private void jTextBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBuscarKeyReleased
        BuscarEmpleado();
    }//GEN-LAST:event_jTextBuscarKeyReleased

    private void jTextBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextBuscarMouseClicked
        jTextBuscar2.setText("");
        jTextBuscar2.setForeground(Color.black);
    }//GEN-LAST:event_jTextBuscarMouseClicked

    private void jTextField_id_empleado(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_id_empleado
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_id_empleado


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Borrar;
    private javax.swing.JButton Editar;
    private javax.swing.JButton guardar_empleado;
    private javax.swing.JFormattedTextField jFormattedTextField_Cedula;
    private javax.swing.JFormattedTextField jFormattedTextField_Hora_Entrada2;
    private javax.swing.JFormattedTextField jFormattedTextField_Hora_Salida2;
    private javax.swing.JFormattedTextField jFormattedTextField_Telefono2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable_Empleado;
    private javax.swing.JTextArea jTextADirecion2;
    private javax.swing.JTextField jTextApellido1;
    private javax.swing.JTextField jTextApellido2;
    private javax.swing.JTextField jTextBuscar2;
    private javax.swing.JTextField jTextGmail2;
    private javax.swing.JTextField jTextIdEmpleado2;
    private javax.swing.JTextField jTextNombre1;
    private javax.swing.JTextField jTextNombre2;
    private javax.swing.JTextField jText_Salario2;
    // End of variables declaration//GEN-END:variables
}
