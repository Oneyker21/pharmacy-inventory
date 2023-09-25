package Vistas;

import Controlador_Conexion_DB.Conexion;
import Modelo.Login;
import Modelo_MDI1.MDIMenu;
import Modelo_MDI1.MDIMenu1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import java.sql.Connection;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 *
 * @author Diers
 */
public class Login_Form extends javax.swing.JFrame {

    private ImageIcon hideIcon;
    private ImageIcon showIcon;
    private Connection cn;

    private String rol;

    public Login_Form() {
        initComponents();
        setTitle("Farmacia Rosales");
        try {
            Image image = ImageIO.read(getClass().getResource("/Vistas_Iconos/apothecary-IconoPequeño.png"));
            setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getRootPane().setDefaultButton(jButton_Iniciar_sesion);
        jTextField_Contraseña.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = jComboBox_Usuario.getSelectedItem().toString();
                String contraseña = jTextField_Contraseña.getText();

                if (cn != null) {
                    if (Login.validarCredenciales(cn, usuario, contraseña)) {
                        rol = Login.obtenerRol(cn, usuario); // Asignar el valor del rol

                        if (rol.equals("Gerente")) {
                            MDIMenu farmacia = new MDIMenu();
                            farmacia.setVisible(true);
                        } else if (rol.equals("Vendedor")) {
                            MDIMenu1 farmacia1 = new MDIMenu1();
                            farmacia1.setVisible(true);
                        }

                        dispose(); // Cerrar el formulario de inicio de sesión
                    } else {
                        JOptionPane.showMessageDialog(Login_Form.this, "Contraseña incorrecta. Por favor, inténtalo de nuevo.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        Conexion conexion = new Conexion();
        cn = conexion.conectar();

        hideIcon = new ImageIcon(getClass().getResource("/Vistas_Iconos/private.png"));
        showIcon = new ImageIcon(getClass().getResource("/Vistas_Iconos/show.png"));

        jTextField_Contraseña.setEchoChar('*');
        jButton_Ver.setIcon(hideIcon);

        jButton_Ver.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (jTextField_Contraseña.getEchoChar() == (char) 0) {
                    jTextField_Contraseña.setEchoChar('*');
                    jButton_Ver.setIcon(hideIcon);
                } else {
                    jTextField_Contraseña.setEchoChar((char) 0);
                    jButton_Ver.setIcon(showIcon);
                }
            }
        });

        jComboBox_Usuario.setBackground(new Color(204, 255, 255));
        jComboBox_Rol.setBackground(new Color(204, 255, 255));
        jTextField_Contraseña.setBackground(new Color(204, 255, 255));
        jButton_Ver.setBackground(new Color(204, 255, 255));
        UIManager.put("Slider.background", new Color(204, 255, 255));

    }

    public String getRol() {
        return rol;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel_Imagen2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jComboBox_Usuario = new javax.swing.JComboBox<>();
        jComboBox_Rol = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton_Iniciar_sesion = new javax.swing.JButton();
        jTextField_Contraseña = new javax.swing.JPasswordField();
        jButton_Ver = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jLabel_Imagen2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Imagenes/medical-g285be860d_1280.png"))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jComboBox_Usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Vendedor" }));
        jComboBox_Usuario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jComboBox_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Usuario(evt);
            }
        });

        jComboBox_Rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Vendedor" }));
        jComboBox_Rol.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rol", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jComboBox_Rol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Rol(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/user.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/settings.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/lock.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas_Iconos/user11.png"))); // NOI18N

        jButton_Iniciar_sesion.setBackground(new java.awt.Color(102, 153, 255));
        jButton_Iniciar_sesion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_Iniciar_sesion.setText("Iniciar Sesion");
        jButton_Iniciar_sesion.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255))));
        jButton_Iniciar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Iniciar_sesion(evt);
            }
        });

        jTextField_Contraseña.setText("Contraseña1");
        jTextField_Contraseña.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contraseña ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jTextField_Contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ContraseñaActionPerformed(evt);
            }
        });

        jButton_Ver.setBorder(null);
        jButton_Ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Iniciar_sesion)
                .addGap(115, 115, 115))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField_Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_Ver, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox_Rol, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Ver, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox_Rol, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButton_Iniciar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        jLabel5.setBackground(new java.awt.Color(0, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Bienvenido a Farmacia Rosales");
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel_Imagen2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 507, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Imagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1410, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_Usuario(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Usuario
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Usuario

    private void jComboBox_Rol(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Rol
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Rol

    private void jButton_Iniciar_sesion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Iniciar_sesion
        String usuario = jComboBox_Usuario.getSelectedItem().toString();
        String contraseña = jTextField_Contraseña.getText();

        if (cn != null) {
            if (Login.validarCredenciales(cn, usuario, contraseña)) {
                rol = Login.obtenerRol(cn, usuario); // Asignar el valor del rol

                if (rol.equals("Gerente")) {
                    MDIMenu farmacia = new MDIMenu();
                    farmacia.setVisible(true);
                } else if (rol.equals("Vendedor")) {
                    MDIMenu1 farmacia1 = new MDIMenu1();
                    farmacia1.setVisible(true);
                }

                dispose(); // Cerrar el formulario de inicio de sesión
            } else {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta. Por favor, inténtalo de nuevo.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton_Iniciar_sesion

    private void jTextField_ContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ContraseñaActionPerformed

    }//GEN-LAST:event_jTextField_ContraseñaActionPerformed

    private void jButton_VerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_VerActionPerformed

    public static void main(String args[]) {
        Login_Form loginForm = new Login_Form();
        loginForm.setVisible(true);

        loginForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Iniciar_sesion;
    private javax.swing.JButton jButton_Ver;
    private javax.swing.JComboBox<String> jComboBox_Rol;
    private javax.swing.JComboBox<String> jComboBox_Usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_Imagen2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jTextField_Contraseña;
    // End of variables declaration//GEN-END:variables
}
