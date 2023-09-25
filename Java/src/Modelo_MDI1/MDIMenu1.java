package Modelo_MDI1;

import Vistas.Login_Form;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Vistas.InternalFrame_Cliente;
import Vistas.JFrame_Espera;

import Vistas.JInternalFrame_Categoria;
import Vistas.JInternalFrame_Compra_Producto;
import Vistas.JInternalFrame_Empleado;
import Vistas.JInternalFrame_Laboratorio;
import Vistas.JInternalFrame_Presentacion;
import Vistas.JInternalFrame_Producto;
import Vistas.JInternalFrame_Proveedor;
import Vistas.JInternalFrame_Venta;
import Vistas.JInternalFrame_Venta_Producto;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.Timer;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author Usuario
 */
public class MDIMenu1 extends javax.swing.JFrame {

    private JDesktopPane desktopPane;

    public MDIMenu1() {
        initComponents();
        setTitle("Farmacia Rosales");
        try {
            Image image = ImageIO.read(getClass().getResource("/Vistas_Iconos/apothecary-IconoPequeño.png"));
            setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fecha actual y formatearla
                LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formateadorFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fechaFormateada = fechaActual.format(formateadorFecha);

                // Actualizar jLabelFecha
                jLabelFecha.setText(fechaFormateada);

                // Obtener la hora actual y formatearla
                LocalTime horaActual = LocalTime.now();
                DateTimeFormatter formateadorHora = new DateTimeFormatterBuilder()
                        .appendPattern("hh:mm:ss")
                        .appendLiteral("  ")
                        .appendPattern("a")
                        .toFormatter(new Locale("en", "US"));
                String horaFormateada = horaActual.format(formateadorHora).toUpperCase();

                // Actualizar jLabel_Hora
                jLabel_Hora.setText(horaFormateada);

                // Obtener la fecha actual y formatearla
                DateTimeFormatter formateadorFechaLetra = DateTimeFormatter.ofPattern("EEEE dd 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
                String fechaFormateadaLetra = fechaActual.format(formateadorFechaLetra);

                // Convertir la primera letra en mayúsculas
                fechaFormateadaLetra = fechaFormateadaLetra.substring(0, 1).toUpperCase() + fechaFormateadaLetra.substring(1);

                // Actualizar jLabel_Mostrar_Fecha_Letra
                jLabel_Mostrar_Fecha_Letra.setText(fechaFormateadaLetra);
            }
        });
        timer.start();
    }

    public JDesktopPane getDesktopPane() {
        return jDesktopPane;
    }

    public void mostrarFormularioVenta() {
        JInternalFrame_Venta ventaForm = new JInternalFrame_Venta();
        jDesktopPane.add(ventaForm);
        ventaForm.setVisible(true);
        try {
            ventaForm.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton_Inicio = new javax.swing.JButton();
        jButton_Venta_Producto = new javax.swing.JButton();
        jButton_Compra_producto = new javax.swing.JButton();
        jButton_Producto = new javax.swing.JButton();
        jButton_Cliente = new javax.swing.JButton();
        jButton_Proveedor = new javax.swing.JButton();
        jLabelFecha = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_Hora = new javax.swing.JLabel();
        jLabel_Mostrar_Fecha_Letra = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane.setBackground(new java.awt.Color(204, 255, 255));
        jDesktopPane.setForeground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1360, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktopPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1360, 610));

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jButton4.setBackground(new java.awt.Color(0, 255, 255));
        jButton4.setText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton_Inicio.setBackground(new java.awt.Color(0, 255, 255));
        jButton_Inicio.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton_Inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/Boton_Inicio.png"))); // NOI18N
        jButton_Inicio.setText("Inicio");
        jButton_Inicio.setToolTipText("Nuevo Cliente");
        jButton_Inicio.setBorder(null);
        jButton_Inicio.setFocusable(false);
        jButton_Inicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Inicio.setPreferredSize(new java.awt.Dimension(90, 92));
        jButton_Inicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Inicio(evt);
            }
        });

        jButton_Venta_Producto.setBackground(new java.awt.Color(0, 255, 255));
        jButton_Venta_Producto.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton_Venta_Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/Boton_Venta_Producto.png"))); // NOI18N
        jButton_Venta_Producto.setText("Venta de producto");
        jButton_Venta_Producto.setToolTipText("Nuevo Cliente");
        jButton_Venta_Producto.setBorder(null);
        jButton_Venta_Producto.setFocusable(false);
        jButton_Venta_Producto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Venta_Producto.setPreferredSize(new java.awt.Dimension(90, 92));
        jButton_Venta_Producto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_Venta_Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Venta_Producto(evt);
            }
        });

        jButton_Compra_producto.setBackground(new java.awt.Color(0, 255, 255));
        jButton_Compra_producto.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton_Compra_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/Boton_Compra_Producto.png"))); // NOI18N
        jButton_Compra_producto.setText("Compra de producto");
        jButton_Compra_producto.setToolTipText("Nuevo Cliente");
        jButton_Compra_producto.setBorder(null);
        jButton_Compra_producto.setFocusable(false);
        jButton_Compra_producto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Compra_producto.setPreferredSize(new java.awt.Dimension(90, 92));
        jButton_Compra_producto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_Compra_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Compra_producto(evt);
            }
        });

        jButton_Producto.setBackground(new java.awt.Color(0, 255, 255));
        jButton_Producto.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton_Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/Boton_medicamento.png"))); // NOI18N
        jButton_Producto.setText("Producto");
        jButton_Producto.setToolTipText("Nuevo Cliente");
        jButton_Producto.setBorder(null);
        jButton_Producto.setFocusable(false);
        jButton_Producto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Producto.setPreferredSize(new java.awt.Dimension(90, 92));
        jButton_Producto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Producto(evt);
            }
        });

        jButton_Cliente.setBackground(new java.awt.Color(0, 255, 255));
        jButton_Cliente.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton_Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/Boton_Cliente.png"))); // NOI18N
        jButton_Cliente.setText("Cliente");
        jButton_Cliente.setToolTipText("Nuevo Cliente");
        jButton_Cliente.setBorder(null);
        jButton_Cliente.setFocusable(false);
        jButton_Cliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Cliente.setPreferredSize(new java.awt.Dimension(90, 92));
        jButton_Cliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Cliente(evt);
            }
        });

        jButton_Proveedor.setBackground(new java.awt.Color(0, 255, 255));
        jButton_Proveedor.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton_Proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/Boton_Proveedor.png"))); // NOI18N
        jButton_Proveedor.setText("Proveedor");
        jButton_Proveedor.setToolTipText("Nuevo Cliente");
        jButton_Proveedor.setBorder(null);
        jButton_Proveedor.setFocusable(false);
        jButton_Proveedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Proveedor.setPreferredSize(new java.awt.Dimension(90, 92));
        jButton_Proveedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Proveedor(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Venta_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Compra_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Producto, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(jButton4)
                        .addGap(138, 138, 138))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(565, 565, 565)
                        .addComponent(jLabelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_Producto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Inicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Venta_Producto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Compra_producto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton_Cliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Proveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, 80));

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 2, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/apothecary-g023478692_1280.png"))); // NOI18N
        jLabel1.setText("Farmacia Rosales");

        jLabel_Hora.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jLabel_Mostrar_Fecha_Letra.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_Mostrar_Fecha_Letra, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 663, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_Hora, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jLabel_Mostrar_Fecha_Letra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_Inicio(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Inicio

    }//GEN-LAST:event_jButton_Inicio

    private void jButton_Venta_Producto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Venta_Producto
        JInternalFrame_Venta_Producto ventaProductoForm = new JInternalFrame_Venta_Producto();
        int x = (jDesktopPane.getWidth() / 2) - ventaProductoForm.getWidth() / 2;
        int y = (jDesktopPane.getHeight() / 2) - ventaProductoForm.getHeight() / 2;
        ventaProductoForm.setLocation(x, y);
        jDesktopPane.add(ventaProductoForm);
        ventaProductoForm.setVisible(true);
        ventaProductoForm.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE); // Configurar el cierre del formulario
        ventaProductoForm.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                mostrarFormularioVenta(); // Mostrar el formulario JInternalFrame_Venta al cerrar JInternalFrame_Venta_Producto
            }
        });
    }//GEN-LAST:event_jButton_Venta_Producto

    private void jButton_Cliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Cliente
        InternalFrame_Cliente est = new InternalFrame_Cliente();
        int x = (jDesktopPane.getWidth() - est.getWidth()) / 2;
        int y = (jDesktopPane.getHeight() - est.getHeight()) / 2;
        est.setLocation(x, y);
        est.mostrar();
        jDesktopPane.add(est);
        est.setVisible(true);
        try {
            est.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton_Cliente

    private void jButton_Compra_producto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Compra_producto
        JInternalFrame_Compra_Producto compra = new JInternalFrame_Compra_Producto();
        compra.setSize(1082, 536);

        int desktopPaneWidth = jDesktopPane.getWidth();

        int x = (desktopPaneWidth - compra.getWidth()) / 2;
        int y = 0;

        compra.setLocation(x, y);
        compra.setVisible(true);

        jDesktopPane.removeAll();
        jDesktopPane.add(compra);
        try {
            compra.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton_Compra_producto

    private void jButton_Producto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Producto

        JInternalFrame_Producto vistaProducto = new JInternalFrame_Producto();
        vistaProducto.setSize(1065, 562);
        vistaProducto.setLocation(0, 0);
        vistaProducto.setVisible(true);

        jDesktopPane.removeAll();
        jDesktopPane.add(vistaProducto);
        try {
            vistaProducto.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton_Producto

    private void jButton_Proveedor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Proveedor
        JInternalFrame_Proveedor est = new JInternalFrame_Proveedor();
        int x = (jDesktopPane.getWidth() / 2) - est.getWidth() / 2;
        int y = (jDesktopPane.getHeight() / 2) - est.getHeight() / 2;
        est.setLocation(x, y);
        est.mostrar();
        jDesktopPane.add(est);
        est.setVisible(true);
    }//GEN-LAST:event_jButton_Proveedor

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame_Espera frameEspera = new JFrame_Espera();
                frameEspera.setLocationRelativeTo(null);
                frameEspera.setVisible(true);

                // Simulación de carga del proyecto (sustituir con la lógica real de carga)
                try {
                    Thread.sleep(3000); // Espera de 3 segundos
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                frameEspera.dispose();

                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        Login_Form loginForm = new Login_Form();
                        loginForm.setLocationRelativeTo(null);
                        loginForm.setVisible(true);

                        loginForm.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                String rol = loginForm.getRol();

                                if (rol != null) {
                                    if (rol.equals("Gerente")) {
                                        mostrarMDIMenu();
                                    } else if (rol.equals("Vendedor")) {
                                        mostrarMDIMenu1();
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private static void mostrarMDIMenu() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MDIMenu1 frameRosales = new MDIMenu1();
                frameRosales.setLocationRelativeTo(null);
                frameRosales.setVisible(true);
            }
        });
    }

    private static void mostrarMDIMenu1() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MDIMenu1 frameRosales1 = new MDIMenu1();
                frameRosales1.setLocationRelativeTo(null);
                frameRosales1.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton_Cliente;
    private javax.swing.JButton jButton_Compra_producto;
    private javax.swing.JButton jButton_Inicio;
    private javax.swing.JButton jButton_Producto;
    private javax.swing.JButton jButton_Proveedor;
    private javax.swing.JButton jButton_Venta_Producto;
    public static javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabel_Hora;
    private javax.swing.JLabel jLabel_Mostrar_Fecha_Letra;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
