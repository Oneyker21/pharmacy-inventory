package Vistas;

import Controlador.CRUD_Cliente;
import Modelo.Clase_Cliente;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.CallableStatement;
import java.sql.SQLException;
import Controlador_Conexion_DB.Conexion;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data;

/**
 *
 * @author Oreki
 */
public class InternalFrame_Cliente extends javax.swing.JInternalFrame {

    private JPanel panel;
    public final Conexion con = new Conexion();
    public final Connection cn = (Connection) con.conectar();

    public InternalFrame_Cliente() {
        initComponents();
        jTextField_Id_Ciente.setEditable(false);
        setTitle("Cliente");
        setFrameIcon(new ImageIcon("C:\\Users\\Diers\\OneDrive\\Escritorio\\CasoFarmacia\\Repositorio-Farmacia-Rosales.-main\\src\\Vistas_Iconos\\apothecary-IconoPequeño.png"));
        personalizarTitulosTabla();
        ajustarAlturaFilasTabla();
        colorearFilasTabla();

    }

    private void personalizarTitulosTabla() {
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) jTable_Cliente.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Centra los títulos
        jTable_Cliente.getTableHeader().setDefaultRenderer(headerRenderer);
        jTable_Cliente.getTableHeader().setBackground(new Color(0, 255, 255)); // Cambia el color de los títulos
        jTable_Cliente.getTableHeader().setForeground(Color.BLACK); // Cambia el color del texto de los títulos a negro
        Font headerFont = new Font("Segoe UI", Font.BOLD, 12); // Cambia el tamaño de letra de los títulos
        jTable_Cliente.getTableHeader().setFont(headerFont);
        jTable_Cliente.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void ajustarAlturaFilasTabla() {
        jTable_Cliente.setRowHeight(35); // Ajusta aquí la altura deseada en píxeles
    }

    private void colorearFilasTabla() {
        jTable_Cliente.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        jTextField_Id_Ciente.setText("");
        jFormattedTextField_Cedula.setText("");
        JtextFiel_Nombre_1.setText("");
        JtextFiel_Nombre_2.setText("");
        jTextFiel_Apellido_1.setText("");
        jTextField_Apellido_2.setText("");
        jFormattedTextFieldTelefono.setText("");
        JtextFiel_gmail.setText("");
        jTextArea_Dirrecion.setText("");

    }

    public void guardarCliente() {
        CRUD_Cliente cc = new CRUD_Cliente();
        String Cedula = jFormattedTextField_Cedula.getText();
        String Nombre_1 = JtextFiel_Nombre_1.getText();
        String Nombre_2 = JtextFiel_Nombre_2.getText();
        String Apellido_1 = jTextFiel_Apellido_1.getText();
        String Apellido_2 = jTextField_Apellido_2.getText();
        String gmail = JtextFiel_gmail.getText();
        String Telefono = jFormattedTextFieldTelefono.getText();
        String Direccion = jTextArea_Dirrecion.getText();

        Clase_Cliente cl = new Clase_Cliente(Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Telefono, gmail, Direccion);
        cc.Guardar(cl);
    }

    public void mostrar() {
        try {
            DefaultTableModel modelo;
            CRUD_Cliente cli = new CRUD_Cliente();
            modelo = cli.mostrarDatos();
            jTable_Cliente.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void editarCliente() {

        CRUD_Cliente cc = new CRUD_Cliente();

        Clase_Cliente cl = new Clase_Cliente(jFormattedTextField_Cedula.getText(),
                JtextFiel_Nombre_1.getText(),
                JtextFiel_Nombre_2.getText(),
                jTextFiel_Apellido_1.getText(),
                jTextField_Apellido_2.getText(),
                jFormattedTextFieldTelefono.getText(),
                JtextFiel_gmail.getText(),
                jTextArea_Dirrecion.getText());
        cc.actualizar(cl);

    }

    public void BuscarCliente() {
        try {
            DefaultTableModel modelo;
            CRUD_Cliente cli = new CRUD_Cliente();
            modelo = cli.BuscarCliente(jTextBuscar.getText());

            if (modelo != null) {
                jTable_Cliente.setModel(modelo);
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

        jPanel1_Cliente = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Dirrecion = new javax.swing.JTextArea();
        JtextFiel_gmail = new javax.swing.JTextField();
        jFormattedTextFieldTelefono = new javax.swing.JFormattedTextField();
        jFormattedTextField_Cedula = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Cliente = new javax.swing.JTable();
        jTextBuscar = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton_Editar = new javax.swing.JButton();
        jButton_Borrar = new javax.swing.JButton();
        jButton_Actualizar1 = new javax.swing.JButton();
        jTextField_Id_Ciente = new javax.swing.JTextField();
        JtextFiel_Nombre_1 = new javax.swing.JTextField();
        JtextFiel_Nombre_2 = new javax.swing.JTextField();
        jTextFiel_Apellido_1 = new javax.swing.JTextField();
        jTextField_Apellido_2 = new javax.swing.JTextField();

        setClosable(true);

        jPanel1_Cliente.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1_Cliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel5.setOpaque(false);

        jTextArea_Dirrecion.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea_Dirrecion.setColumns(20);
        jTextArea_Dirrecion.setRows(5);
        jTextArea_Dirrecion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dirrecion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextArea_Dirrecion.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTextArea_Dirrecion(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTextArea_Dirrecion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea_DirrecionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea_Dirrecion);

        JtextFiel_gmail.setBackground(new java.awt.Color(204, 255, 255));
        JtextFiel_gmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JtextFiel_gmail.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gmail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        JtextFiel_gmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtextFiel_gmail(evt);
            }
        });
        JtextFiel_gmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JtextFiel_gmailKeyTyped(evt);
            }
        });

        jFormattedTextFieldTelefono.setBackground(new java.awt.Color(204, 255, 255));
        jFormattedTextFieldTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Numero Celular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        try {
            jFormattedTextFieldTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldTelefono(evt);
            }
        });

        jFormattedTextField_Cedula.setBackground(new java.awt.Color(204, 255, 255));
        jFormattedTextField_Cedula.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cedula", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        try {
            jFormattedTextField_Cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-######-####U")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Cedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jFormattedTextField_Cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(JtextFiel_gmail, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jFormattedTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JtextFiel_gmail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField_Cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1_Cliente.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 980, -1));

        jTable_Cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Cedula", "Primer N", "Segundo N", "Primer A", "Segundo A", "Telefono", "Gmail", "Dirección"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Cliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable_Cliente.setGridColor(new java.awt.Color(0, 153, 153));
        jTable_Cliente.setShowGrid(false);
        jTable_Cliente.setShowHorizontalLines(true);
        jTable_Cliente.setShowVerticalLines(true);
        jTable_Cliente.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable_Cliente(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTable_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable_ClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTable_ClienteMouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Cliente);

        jPanel1_Cliente.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 980, 250));

        jTextBuscar.setBackground(new java.awt.Color(204, 255, 255));
        jTextBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTextBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextBuscarMouseClicked(evt);
            }
        });
        jTextBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextBuscar(evt);
            }
        });
        jTextBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextBuscarKeyReleased(evt);
            }
        });
        jPanel1_Cliente.add(jTextBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 270, 40));

        jButton6.setBackground(new java.awt.Color(51, 255, 51));
        jButton6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton6.setText("Agregar");
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar_Cliente(evt);
            }
        });
        jPanel1_Cliente.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 80, 30));

        jButton_Editar.setBackground(new java.awt.Color(255, 255, 102));
        jButton_Editar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton_Editar.setText("Editar");
        jButton_Editar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jButton_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Editar(evt);
            }
        });
        jPanel1_Cliente.add(jButton_Editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 80, 30));

        jButton_Borrar.setBackground(new java.awt.Color(255, 102, 102));
        jButton_Borrar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton_Borrar.setText("Eliminar");
        jButton_Borrar.setBorder(null);
        jButton_Borrar.setMaximumSize(new java.awt.Dimension(80, 30));
        jButton_Borrar.setMinimumSize(new java.awt.Dimension(80, 30));
        jButton_Borrar.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton_Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Borrar(evt);
            }
        });
        jPanel1_Cliente.add(jButton_Borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, 80, 30));

        jButton_Actualizar1.setBackground(new java.awt.Color(102, 204, 255));
        jButton_Actualizar1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton_Actualizar1.setText("Actualizar");
        jButton_Actualizar1.setBorder(null);
        jButton_Actualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Actualizar1(evt);
            }
        });
        jPanel1_Cliente.add(jButton_Actualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 80, 30));

        jTextField_Id_Ciente.setEditable(false);
        jTextField_Id_Ciente.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Id_Ciente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField_Id_Ciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Id_Ciente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Id_Ciente.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_Id_Ciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Id_Ciente(evt);
            }
        });
        jPanel1_Cliente.add(jTextField_Id_Ciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 45));

        JtextFiel_Nombre_1.setBackground(new java.awt.Color(204, 255, 255));
        JtextFiel_Nombre_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JtextFiel_Nombre_1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Primer Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        JtextFiel_Nombre_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtextFiel_nombre1(evt);
            }
        });
        JtextFiel_Nombre_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JtextFiel_Nombre_1KeyTyped(evt);
            }
        });
        jPanel1_Cliente.add(JtextFiel_Nombre_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 160, 45));

        JtextFiel_Nombre_2.setBackground(new java.awt.Color(204, 255, 255));
        JtextFiel_Nombre_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JtextFiel_Nombre_2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Segundo Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        JtextFiel_Nombre_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtextFiel_Nombre_2(evt);
            }
        });
        JtextFiel_Nombre_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JtextFiel_Nombre_2KeyTyped(evt);
            }
        });
        jPanel1_Cliente.add(JtextFiel_Nombre_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 160, 45));

        jTextFiel_Apellido_1.setBackground(new java.awt.Color(204, 255, 255));
        jTextFiel_Apellido_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFiel_Apellido_1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Primer Apellido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextFiel_Apellido_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFiel_Apellido_1(evt);
            }
        });
        jTextFiel_Apellido_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFiel_Apellido_1KeyTyped(evt);
            }
        });
        jPanel1_Cliente.add(jTextFiel_Apellido_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 160, 45));

        jTextField_Apellido_2.setBackground(new java.awt.Color(204, 255, 255));
        jTextField_Apellido_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Apellido_2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Segundo Apellido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jTextField_Apellido_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Apellido_2(evt);
            }
        });
        jTextField_Apellido_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Apellido_2KeyTyped(evt);
            }
        });
        jPanel1_Cliente.add(jTextField_Apellido_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 160, 45));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1_Cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1_Cliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_Cliente(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable_Cliente
      
    }//GEN-LAST:event_jTable_Cliente

    private void jTable_ClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ClienteMouseEntered

    }//GEN-LAST:event_jTable_ClienteMouseEntered

    private void jTable_ClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ClienteMouseExited

    }//GEN-LAST:event_jTable_ClienteMouseExited

    private void jTextArea_Dirrecion(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTextArea_Dirrecion

    }//GEN-LAST:event_jTextArea_Dirrecion

    private void Guardar_Cliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar_Cliente
        CRUD_Cliente cl = new CRUD_Cliente();
        try {
            if (JtextFiel_Nombre_1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tiene datos vacíos");
            } else {
                int option = JOptionPane.showOptionDialog(
                        null,
                        "¿Desea guardar el cliente?",
                        "Confirmar Guardado",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon(getClass().getResource("/Vistas_Iconos/agregar.png")),
                        new Object[]{"Sí", "No"},
                        "No"
                );

                if (option == JOptionPane.YES_OPTION) {
                    guardarCliente();
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

    }//GEN-LAST:event_Guardar_Cliente

    private void jButton_Editar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Editar
        int filaSeleccionada = jTable_Cliente.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla para editar");
        } else {
            String id = jTable_Cliente.getValueAt(filaSeleccionada, 0) != null ? jTable_Cliente.getValueAt(filaSeleccionada, 0).toString() : "";
            String Cedula = jTable_Cliente.getValueAt(filaSeleccionada, 1) != null ? jTable_Cliente.getValueAt(filaSeleccionada, 1).toString() : "";
            String nombre1 = jTable_Cliente.getValueAt(filaSeleccionada, 2) != null ? jTable_Cliente.getValueAt(filaSeleccionada, 2).toString() : "";
            String nombre2 = jTable_Cliente.getValueAt(filaSeleccionada, 3) != null ? jTable_Cliente.getValueAt(filaSeleccionada, 3).toString() : "";
            String apellido1 = jTable_Cliente.getValueAt(filaSeleccionada, 4) != null ? jTable_Cliente.getValueAt(filaSeleccionada, 4).toString() : "";
            String apellido2 = jTable_Cliente.getValueAt(filaSeleccionada, 5) != null ? jTable_Cliente.getValueAt(filaSeleccionada, 5).toString() : "";
            String telefono = jTable_Cliente.getValueAt(filaSeleccionada, 6) != null ? jTable_Cliente.getValueAt(filaSeleccionada, 6).toString() : "";
            String Gmail = jTable_Cliente.getValueAt(filaSeleccionada, 7) != null ? jTable_Cliente.getValueAt(filaSeleccionada, 7).toString() : "";
            String direccion = jTable_Cliente.getValueAt(filaSeleccionada, 8) != null ? jTable_Cliente.getValueAt(filaSeleccionada, 8).toString() : "";

            jTextField_Id_Ciente.setText(id);
            jFormattedTextField_Cedula.setText(Cedula);
            JtextFiel_Nombre_1.setText(nombre1);
            JtextFiel_Nombre_2.setText(nombre2);
            jTextFiel_Apellido_1.setText(apellido1);
            jTextField_Apellido_2.setText(apellido2);
            jFormattedTextFieldTelefono.setText(telefono);
            JtextFiel_gmail.setText(Gmail);
            jTextArea_Dirrecion.setText(direccion);

            // Desactivar la edición del campo de texto para el ID del cliente
            jTextField_Id_Ciente.setEditable(false);
        }


    }//GEN-LAST:event_jButton_Editar

    private void jTextBuscar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextBuscar
        jTextBuscar.setText("");
        jTextBuscar.setForeground(Color.black);
        BuscarCliente();
    }//GEN-LAST:event_jTextBuscar

    private void JtextFiel_nombre1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtextFiel_nombre1

    }//GEN-LAST:event_JtextFiel_nombre1

    private void JtextFiel_Nombre_2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtextFiel_Nombre_2
        // TODO add your handling code here:
    }//GEN-LAST:event_JtextFiel_Nombre_2

    private void jTextFiel_Apellido_1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFiel_Apellido_1
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFiel_Apellido_1

    private void jTextField_Apellido_2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Apellido_2
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Apellido_2

    private void jButton_Borrar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Borrar
        int selectedRow = jTable_Cliente.getSelectedRow();
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
                String idClienteString = jTable_Cliente.getValueAt(selectedRow, 0).toString();
                int idCliente = Integer.parseInt(idClienteString);

                CRUD_Cliente cli = new CRUD_Cliente();
                cli.eliminar(idCliente);

                mostrar();

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JLabel messageLabel = new JLabel("Cliente eliminado correctamente");
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

            JLabel messageLabel = new JLabel("Debe seleccionar un cliente");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
            messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(messageLabel, BorderLayout.CENTER);

            ImageIcon icon = new ImageIcon(getClass().getResource("/Vistas_Iconos/abvertencia.png"));
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(iconLabel, BorderLayout.WEST);

            JOptionPane.showMessageDialog(null, panel, "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton_Borrar

    private void jTextBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextBuscarMouseClicked
        jTextBuscar.setText("");
        jTextBuscar.setForeground(Color.black);
    }//GEN-LAST:event_jTextBuscarMouseClicked

    private void jButton_Actualizar1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Actualizar1
        String idClienteText = jTextField_Id_Ciente.getText();
        int idCliente = Integer.parseInt(idClienteText);
        String cedula = jFormattedTextField_Cedula.getText();
        String nombre1 = JtextFiel_Nombre_1.getText();
        String nombre2 = JtextFiel_Nombre_2.getText();
        String apellido1 = jTextFiel_Apellido_1.getText();
        String apellido2 = jTextField_Apellido_2.getText();
        String celular = jFormattedTextFieldTelefono.getText();
        String Gmail = JtextFiel_gmail.getText();
        String direccion = jTextArea_Dirrecion.getText();

        if (jTextField_Id_Ciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tiene datos vacíos");
        } else {
            int option = JOptionPane.showOptionDialog(
                    null,
                    "¿Desea actualizar el cliente?",
                    "Confirmar Actualización",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(getClass().getResource("/Vistas_Iconos/actualizar.png")),
                    new Object[]{"Sí", "No"},
                    "No"
            );

            if (option == JOptionPane.YES_OPTION) {
                // Crear objeto Clase_Cliente con los datos obtenidos
                Clase_Cliente cliente = new Clase_Cliente(idCliente, cedula, nombre1, nombre2, apellido1, apellido2, celular, Gmail, direccion);

                // Llamar al método "actualizar" de CRUD_Cliente
                CRUD_Cliente clienteCRUD = new CRUD_Cliente();
                clienteCRUD.actualizar(cliente);

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JLabel messageLabel = new JLabel("Cliente actualizado exitosamente.");
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
        CRUD_Cliente clienteCRUD = new CRUD_Cliente();
        clienteCRUD.mostrarDatos();
        limpiar();
        mostrar();


    }//GEN-LAST:event_jButton_Actualizar1

    private void jTextBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBuscarKeyReleased
        BuscarCliente();
    }//GEN-LAST:event_jTextBuscarKeyReleased

    private void jFormattedTextFieldTelefono(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTelefono
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldTelefono

    private void JtextFiel_Nombre_1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtextFiel_Nombre_1KeyTyped
        char car = evt.getKeyChar();
        String texto = JtextFiel_Nombre_1.getText(); // Obtener el texto actual en el campo

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
    }//GEN-LAST:event_JtextFiel_Nombre_1KeyTyped

    private void JtextFiel_Nombre_2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtextFiel_Nombre_2KeyTyped
        char car = evt.getKeyChar();
        String texto = JtextFiel_Nombre_2.getText(); // Obtener el texto actual en el campo

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
    }//GEN-LAST:event_JtextFiel_Nombre_2KeyTyped

    private void jTextFiel_Apellido_1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFiel_Apellido_1KeyTyped
        char car = evt.getKeyChar();
        String texto = jTextFiel_Apellido_1.getText(); // Obtener el texto actual en el campo

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
    }//GEN-LAST:event_jTextFiel_Apellido_1KeyTyped

    private void jTextField_Apellido_2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Apellido_2KeyTyped
        char car = evt.getKeyChar();
        String texto = jTextField_Apellido_2.getText(); // Obtener el texto actual en el campo

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
    }//GEN-LAST:event_jTextField_Apellido_2KeyTyped

    private void jTextArea_DirrecionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea_DirrecionKeyTyped
        String texto = jTextArea_Dirrecion.getText();

        if (texto.length() >= 200) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextArea_DirrecionKeyTyped

    private void JtextFiel_gmail(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtextFiel_gmail
        // TODO add your handling code here:
    }//GEN-LAST:event_JtextFiel_gmail

    private void JtextFiel_gmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtextFiel_gmailKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_JtextFiel_gmailKeyTyped

    private void jTextField_Id_Ciente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Id_Ciente
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Id_Ciente


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JtextFiel_Nombre_1;
    private javax.swing.JTextField JtextFiel_Nombre_2;
    private javax.swing.JTextField JtextFiel_gmail;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton_Actualizar1;
    private javax.swing.JButton jButton_Borrar;
    private javax.swing.JButton jButton_Editar;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefono;
    private javax.swing.JFormattedTextField jFormattedTextField_Cedula;
    public javax.swing.JPanel jPanel1_Cliente;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Cliente;
    private javax.swing.JTextArea jTextArea_Dirrecion;
    private javax.swing.JTextField jTextBuscar;
    private javax.swing.JTextField jTextFiel_Apellido_1;
    private javax.swing.JTextField jTextField_Apellido_2;
    private javax.swing.JTextField jTextField_Id_Ciente;
    // End of variables declaration//GEN-END:variables
}
