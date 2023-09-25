package Controlador;

import Modelo.Class_Venta_Producto;
import Controlador_Conexion_DB.Conexion;
import Modelo.Clase_Cliente;
import Modelo.Clase_Empleado;
import Modelo.Clase_Producto;
import Modelo.Clase_Producto_Cargardatos;
import Modelo.Clase_Venta;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Types;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author diedr
 */
public class CRUD_Venta_Producto {

    public final Conexion con = new Conexion();
    public final Connection cn = (Connection) con.conectar();
    
    public int obtenerCantidadStockProducto(String busqueda) {
    int cantidadStock = 0;
    try {
        String query = "{ CALL MostrarCantidadStok(?) }";
        CallableStatement cstmt = cn.prepareCall(query);
        cstmt.setString(1, busqueda);
        ResultSet rs = cstmt.executeQuery();
        if (rs.next()) {
            cantidadStock = rs.getInt("Cantidad_Producto");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
    return cantidadStock;
}
     public ArrayList<Clase_Producto_Cargardatos> buscarProductos(String busqueda) {
        ArrayList<Clase_Producto_Cargardatos> listaProductos = new ArrayList<>();

        try {
            String query = "{ CALL BuscaProductos(?) }";
            CallableStatement cstmt = cn.prepareCall(query);
            cstmt.setString(1, busqueda);
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("Id_Producto");
                String nombreProducto = rs.getString("Nombre");
                String descripcionProducto = rs.getString("Descripcion");
                float precioVenta = rs.getFloat("Precio_Venta");
                String categoria = rs.getString("Categoria");
                String presentacion = rs.getString("Presentacion");
                String laboratorio = rs.getString("Laboratorio");

                Clase_Producto_Cargardatos producto = new Clase_Producto_Cargardatos(idProducto, nombreProducto, descripcionProducto, precioVenta, categoria, presentacion, laboratorio);
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaProductos;
    }

   public void agregarVentasYProductos(List<Clase_Venta> ventas) {
    String sql = "{CALL AgregarVentaYProducto(?, ?, ?, ?, ?, ?)}";

    try (CallableStatement stmt = cn.prepareCall(sql)) {
        // Iniciar la transacción
        cn.setAutoCommit(false);

        for (Clase_Venta venta : ventas) {
            stmt.setObject(1, venta.getFecha_Hora());

         if (venta.getId_Cliente() == -1) {
        stmt.setNull(2, Types.INTEGER);
    } else {
        stmt.setInt(2, venta.getId_Cliente());
    }

            stmt.setInt(3, venta.getId_Empleado());

            if (venta.getDescuento() == null) {
                stmt.setNull(4, java.sql.Types.DECIMAL);
            } else {
                stmt.setBigDecimal(4, venta.getDescuento());
            }

            stmt.setInt(5, venta.getId_Producto());
            stmt.setInt(6, venta.getCantidad());

            stmt.execute();
        }

        // Confirmar la transacción
        cn.commit();
    } catch (SQLException e) {
        // Rollback en caso de error
        try {
            cn.rollback();
        } catch (SQLException rollbackException) {
            rollbackException.printStackTrace();
        }
        e.printStackTrace();
    } finally {
        // Restaurar el modo de autocommit
        try {
            cn.setAutoCommit(true);
        } catch (SQLException autoCommitException) {
            autoCommitException.printStackTrace();
        }
    }
}



    public DefaultTableModel mostrarDatosVenta() {
        ResultSet rs;
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Producto", "Cantidad", "Descuento", "Cliente", "Empleado", "Fecha y Hora", "Total", "Total General"};
        String[] registro = new String[9];

        modelo = new DefaultTableModel(null, titulos);

        try {
            CallableStatement call = cn.prepareCall("{call MostrarVentaYProducto}");
            rs = call.executeQuery();

            while (rs.next()) {
                registro[0] = rs.getString("Id_Venta");
                registro[1] = rs.getString("Producto");
                registro[2] = rs.getString("Cantidad");
                registro[3] = rs.getString("Descuento");
                registro[4] = rs.getString("Cliente");
                registro[5] = rs.getString("Empleado");
                registro[6] = rs.getString("Fecha_Hora");
                registro[7] = String.valueOf(rs.getDouble("Total"));
                registro[8] = String.valueOf(rs.getDouble("TotalGeneral"));

                modelo.addRow(registro);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public void eliminarVentaYProducto(int idVenta) {
        String sql = "{CALL EliminarVentaYProducto(?)}";

        try (CallableStatement stmt = cn.prepareCall(sql)) {
            stmt.setInt(1, idVenta);
            stmt.execute();

            // Confirmar la transacción
            cn.commit();

            // Cerrar el CallableStatement y la Connection
            stmt.close();
            cn.close();
        } catch (SQLException e) {
            // Imprimir el seguimiento de la pila y el mensaje de la excepción
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public DefaultTableModel buscarVentaYProducto(String textoBusqueda) {
        ResultSet rs;
        DefaultTableModel modelo;
        String[] titulos = {"Id_Venta", "Fecha_Hora", "Id_Cliente"};
        String[] registro = new String[3];

        modelo = new DefaultTableModel(null, titulos);

        try {
            CallableStatement call = cn.prepareCall("{call BuscarVentaYProducto(?)}");
            call.setString(1, textoBusqueda);
            rs = call.executeQuery();

            while (rs.next()) {
                registro[0] = rs.getString("Id_Venta");
                registro[1] = rs.getString("Fecha_Hora");
                registro[2] = rs.getString("Id_Cliente");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public ResultSet obtenerNombreApellidoCliente() throws SQLException {
        String query = "{CALL ObtenerNombreApellidoCliente()}";
        CallableStatement stmt = cn.prepareCall(query);
        return stmt.executeQuery();
    }

    public ResultSet buscarCliente(String criterioBusqueda) throws SQLException {
        String query = "{CALL BuscarCliente(?)}";
        CallableStatement stmt = cn.prepareCall(query);
        stmt.setString(1, criterioBusqueda);
        return stmt.executeQuery();
    }

    public ArrayList<Clase_Empleado> obtenerEmpleados() {
        ArrayList<Clase_Empleado> empleados = new ArrayList<>();
        try {
            String query = "{ call ObtenerEmpleados }";
            CallableStatement stmt = cn.prepareCall(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int idEmpleado = resultSet.getInt("Id_Empleado");
                String nombreCompleto = resultSet.getString("NombreCompleto");
                String[] nombres = nombreCompleto.split(" ");
                String nombre = nombres[0];
                String apellido = nombres[1];
                Clase_Empleado empleado = new Clase_Empleado(idEmpleado, nombre, apellido);
                empleados.add(empleado);
            }

            resultSet.close();
            stmt.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    private void actualizarCantidadProducto(int idProducto, int cantidad) {
        try {
            PreparedStatement stmt = cn.prepareStatement("UPDATE Producto SET Cantidad_Producto = Cantidad_Producto - ? WHERE Id_Producto = ?");
            stmt.setInt(1, cantidad);
            stmt.setInt(2, idProducto);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public DefaultTableModel mostrarDatosVentaProducto() {
        ResultSet rs;
        DefaultTableModel modelo;
        String[] titulos = {"Id_Venta_Producto", "Cantidad", "Descuento", "Id_Venta", "Id_Producto"};
        String[] registro = new String[5];

        modelo = new DefaultTableModel(null, titulos);

        try {
            CallableStatement cbstc = cn.prepareCall("{call ConsultarDatosVentaProducto}");
            rs = cbstc.executeQuery();

            while (rs.next()) {
                registro[0] = rs.getString("Id_Venta_Producto");
                registro[1] = rs.getString("Cantidad");
                registro[2] = rs.getString("Descuento");
                registro[3] = rs.getString("Id_Venta");
                registro[4] = rs.getString("Id_Producto");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean verificarVentaProducto(String dato) {
        ResultSet rs;

        try {
            CallableStatement call = cn.prepareCall("{call BuscarVentaProducto(?)}");
            call.setString(1, dato);
            rs = call.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public void eliminarVentaProducto(int Id_Venta_Producto) {
        try {
            CallableStatement cbst = cn.prepareCall("{call EliminarVentaProducto(?)}");
            cbst.setInt(1, Id_Venta_Producto);
            cbst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public ArrayList<Clase_Cliente> buscarClientes(String busqueda) {
        ArrayList<Clase_Cliente> listaClientes = new ArrayList<>();
        try {
            String query = "{ call BuscarCliente(?) }";
            CallableStatement cstmt = cn.prepareCall(query);
            cstmt.setString(1, busqueda);
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("Id_Cliente");
                String cedula = rs.getString("Cedula");
                String nombre1 = rs.getString("Nombre_1");
                String nombre2 = rs.getString("Nombre_2");
                String apellido1 = rs.getString("Apellido_1");
                String apellido2 = rs.getString("Apellido_2");
                String numeroCelular = rs.getString("Numero_Celular");
                String gmail = rs.getString("Gmail");
                String direccion = rs.getString("Direccion");

                Clase_Cliente cliente = new Clase_Cliente(idCliente, cedula, nombre1, nombre2, apellido1, apellido2, numeroCelular, gmail, direccion);
                listaClientes.add(cliente);
            }

            rs.close();
            cstmt.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

    public ArrayList<Clase_Cliente> obtenerNombresApellidosCliente() {
        ArrayList<Clase_Cliente> clientes = new ArrayList<>();
        try {
            String query = "{ call ObtenerNombreApellidoCliente }";
            CallableStatement cstmt = cn.prepareCall(query);
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("Id_Cliente");
                String nombreCompleto = rs.getString("NombreCompleto");
                String[] nombres = nombreCompleto.split(" ");
                String nombre = nombres[0];
                String apellido = nombres[1];
                Clase_Cliente cliente = new Clase_Cliente(idCliente, nombre, apellido);
                clientes.add(cliente);
            }

            rs.close();
            cstmt.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

}
