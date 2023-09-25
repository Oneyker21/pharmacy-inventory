package Controlador;

import Modelo.Class_Producto_Proveedor;
import Controlador_Conexion_DB.Conexion;
import Modelo.Clase_Producto;
import Modelo.Clase_Proveedor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/* @author diedr
 */
public class CRUD_Producto_Proveedor {

    public final Conexion con = new Conexion();
    public final Connection cn = (Connection) con.conectar();

    public void CrearProducto_Proveedor(Class_Producto_Proveedor cg) {
        try {
            CallableStatement call = cn.prepareCall("{call InsertarProductoProveedor(?, ?)}");
            call.setInt(1, cg.getId_Proveedor());
            call.setInt(2, cg.getId_Producto());
            call.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }

}
