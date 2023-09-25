package Controlador;

import Modelo.Clase_Empleado;
import Controlador_Conexion_DB.Conexion;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diedr
 */
public class CRUD_Empleado {

    public final Conexion con = new Conexion();
    public final Connection cn = (Connection) con.conectar();

    public void eliminar(int Id_Empleado) {
        try {
            CallableStatement cbst = cn.prepareCall("{call EliminarEmpleado(?)}");
            cbst.setInt(1, Id_Empleado);
            cbst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void actualizar(Clase_Empleado empleado) {
        try {
            CallableStatement cbst = cn.prepareCall("{call ActualizarEmpleado(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cbst.setInt(1, empleado.getId_Empleado());
            cbst.setBigDecimal(2, empleado.getSalario());
            cbst.setTime(3, empleado.getHora_Entrada());
            cbst.setTime(4, empleado.getHora_Salida());
            cbst.setString(5, empleado.getCedula());
            cbst.setString(6, empleado.getNombre_1());
            cbst.setString(7, empleado.getNombre_2());
            cbst.setString(8, empleado.getApellido_1());
            cbst.setString(9, empleado.getApellido_2());
            cbst.setString(10, empleado.getNumero_Celular());
            cbst.setString(11, empleado.getGmail());
            cbst.setString(12, empleado.getDireccion());
            cbst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public DefaultTableModel buscarDatos(String dato) {
        ResultSet rs;
        DefaultTableModel modelo;

        String[] titulos = {"Id_Empleado", "Cedula", "Nombre_1", "Nombre_2", "Apellido_1", "Apellido_2", "Salario", "Hora_Entrada", "Hora_Salida", "Numero_Celular", "Gmail", "Direccion"};
        String[] registro = new String[12];

        modelo = new DefaultTableModel(null, titulos);

        try {
            CallableStatement call = cn.prepareCall("{call BuscarEmpleado(?)}");
            call.setString(1, dato);
            rs = call.executeQuery();

            while (rs.next()) {
                registro[0] = rs.getString("Id_Empleado");
                registro[1] = rs.getString("Cedula");
                registro[2] = rs.getString("Nombre_1");
                registro[3] = rs.getString("Nombre_2");
                registro[4] = rs.getString("Apellido_1");
                registro[5] = rs.getString("Apellido_2");
                registro[6] = rs.getString("Salario");
                registro[7] = rs.getTime("Hora_Entrada").toString();
                registro[8] = rs.getTime("Hora_Salida").toString();
                registro[9] = rs.getString("Numero_Celular");
                registro[10] = rs.getString("Gmail");
                registro[11] = rs.getString("Direccion");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean verificarDatos(String dato) {
        ResultSet rs;

        try {
            CallableStatement call = cn.prepareCall("{call BuscarEmpleado(?)}");
            call.setString(1, dato);
            rs = call.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public DefaultTableModel mostrarDatos() {
        ResultSet rs;
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Cedula", "Primer N", "Segundo N", "Primer A", "Segundo A", "Salario", "Hora de Entrada", "Hora de Salida", "Telefono", "Gmail", "Direccion"};
        String[] registro = new String[12];

        modelo = new DefaultTableModel(null, titulos);

        try {
            CallableStatement cbstc = cn.prepareCall("{call ConsultarDatosEmpleado}");
            rs = cbstc.executeQuery();

            while (rs.next()) {
                registro[0] = rs.getString("Id_Empleado");
                registro[1] = rs.getString("Cedula");
                registro[2] = rs.getString("Nombre_1");
                registro[3] = rs.getString("Nombre_2");
                registro[4] = rs.getString("Apellido_1");
                registro[5] = rs.getString("Apellido_2");
                registro[6] = rs.getString("Salario");
                registro[7] = rs.getTime("Hora_Entrada").toString();
                registro[8] = rs.getTime("Hora_Salida").toString();
                registro[9] = rs.getString("Numero_Celular");
                registro[10] = rs.getString("Gmail");
                registro[11] = rs.getString("Direccion");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public void Guardar(Clase_Empleado empleado) {
        try {
            CallableStatement cbst = cn.prepareCall("{call InsertarEmpleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cbst.setBigDecimal(1, empleado.getSalario());
            cbst.setTime(2, empleado.getHora_Entrada());
            cbst.setTime(3, empleado.getHora_Salida());
            cbst.setString(4, empleado.getCedula());
            cbst.setString(5, empleado.getNombre_1());
            cbst.setString(6, empleado.getNombre_2());
            cbst.setString(7, empleado.getApellido_1());
            cbst.setString(8, empleado.getApellido_2());
            cbst.setString(9, empleado.getNumero_Celular());
            cbst.setString(10, empleado.getGmail());
            cbst.setString(11, empleado.getDireccion());
            cbst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
