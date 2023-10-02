package Controlador;

import Modelo.Clase_Producto;
import Controlador.CRUD_Producto;
import Controlador_Conexion_DB.Conexion;
import Modelo.Clase_Categoria;
import Modelo.Clase_Laboratorio;
import Modelo.Clase_Presentacion;
import Modelo.Clase_Proveedor;
import Modelo.Class_Producto_Proveedor;
import static Vistas.JInternalFrame_Gestionar_Producto.jTable_Producto1;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.lang.model.util.Types;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

/**
 *
 * @author diedr
 */
public class CRUD_Producto {

    public final Conexion con = new Conexion();
    public final Connection cn = (Connection) con.conectar();

    public int obtenerTotalCantidadProducto() {
        try {
            CallableStatement statement = cn.prepareCall("{CALL CalcularTotalCantidadProducto}");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("TotalCantidad");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void borrarProductoYProveedor(int IdProducto) {
        String sql = "{call BorrarProductoYProveedor(?)}";
        try (CallableStatement stmt = cn.prepareCall(sql)) {
            stmt.setInt(1, IdProducto);

            boolean isResultSet = stmt.execute();
            if (isResultSet) {
                throw new SQLException("Error al borrar el producto y proveedor.");
            }

            System.out.println("Producto y proveedor borrados correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarProductoConProveedor(Clase_Producto producto) {
        String sql = "{call InsertarProductoConProveedor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = cn.prepareCall(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getCantidad_Producto());
            stmt.setFloat(4, producto.getPrecio_Compra());
            stmt.setFloat(5, producto.getPrecio_Venta());
            stmt.setBytes(6, producto.getImagen_Producto());
            stmt.setDate(7, producto.getFecha_Caducidad());
            stmt.setInt(8, producto.getId_Categoria());
            stmt.setInt(9, producto.getId_Presentacion());
            stmt.setInt(10, producto.getId_Laboratorio());
            stmt.setInt(11, producto.getId_Proveedor());

            boolean isResultSet = stmt.execute();
            if (isResultSet) {
                throw new SQLException("Error al insertar el producto con proveedor.");
            }

            System.out.println("Producto insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean actualizarProductoConProveedor(Clase_Producto producto) {
        String sql = "{call ActualizarProductoConProveedor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = cn.prepareCall(sql)) {
            stmt.setInt(1, producto.getId_Producto());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setInt(4, producto.getCantidad_Producto());
            stmt.setFloat(5, producto.getPrecio_Compra());
            stmt.setFloat(6, producto.getPrecio_Venta());
            stmt.setBytes(7, producto.getImagen_Producto());
            stmt.setDate(8, producto.getFecha_Caducidad());
            stmt.setInt(9, producto.getId_Categoria());
            stmt.setInt(10, producto.getId_Presentacion());
            stmt.setInt(11, producto.getId_Laboratorio());
            stmt.setInt(12, producto.getId_Proveedor());

            stmt.executeUpdate();

            System.out.println("Producto actualizado correctamente.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public DefaultTableModel BuscarProducto(String textoBusqueda) {
        ResultSet rs;
        DefaultTableModel modelo;
        String[] titulos = {"Id_Producto", "Nombre", "Descripcion", "Cantidad_Producto", "Precio_Compra", "Precio_Venta", "Imagen_Producto", "Fecha_Caducidad", "Nombre_Categoria", "Nombre_Presentacion", "Nombre_Laboratorio", "Proveedor"};
        Object[] registro = new Object[12];

        modelo = new DefaultTableModel(null, titulos);

        try (CallableStatement call = cn.prepareCall("{call BuscarProductoconProveedor(?)}")) {
            call.setString(1, textoBusqueda);
            rs = call.executeQuery();

            while (rs.next()) {
                registro[0] = rs.getString("Id_Producto");
                registro[1] = rs.getString("Nombre");
                registro[2] = rs.getString("Descripcion");
                registro[3] = rs.getString("Cantidad_Producto");
                registro[4] = rs.getString("Precio_Compra");
                registro[5] = rs.getString("Precio_Venta");
                // Obtener la imagen como un ImageIcon
                byte[] imagenBytes = rs.getBytes("Imagen_Producto");
                ImageIcon imagenIcono;
                if (imagenBytes != null && imagenBytes.length > 0) {
                    imagenIcono = new ImageIcon(imagenBytes);
                } else {
                    imagenIcono = new ImageIcon(); // O cualquier otra imagen por defecto
                }
                registro[6] = imagenIcono;
                registro[7] = rs.getString("Fecha_Caducidad");
                registro[8] = rs.getString("Nombre_Categoria");
                registro[9] = rs.getString("Nombre_Presentacion");
                registro[10] = rs.getString("Nombre_Laboratorio");
                registro[11] = rs.getString("Proveedor");

                modelo.addRow(registro);
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error SQL: " + e.getMessage());
            return null;
        }

        return modelo;
    }

    public void mostrarProductoConProveedor() {
        DefaultTableModel model = (DefaultTableModel) jTable_Producto1.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de mostrar los nuevos datos

        String sql = "{call MostrarProductoConProveedor(?)}";
        try (CallableStatement stmt = cn.prepareCall(sql)) {
            stmt.setInt(1, -1); // Utilizamos un valor inválido para el IdProveedor para obtener todos los registros
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idProducto = rs.getInt("ID");
                    String nombre = rs.getString("Nombre");
                    String descripcion = rs.getString("Descripcion");
                    int cantidad = rs.getInt("Cantidad");
                    float precioCompra = rs.getFloat("Precio_Compra");
                    float precioVenta = rs.getFloat("Precio_Venta");

                    // Obtener la imagen del campo Imagen
                    byte[] imagenBytes = rs.getBytes("Imagen");
                    ImageIcon imagenIcono;
                    if (imagenBytes != null && imagenBytes.length > 0) {
                        imagenIcono = new ImageIcon(imagenBytes);
                    } else {
                        // Si el campo de la imagen es nulo, asignar una imagen por defecto o dejar en blanco
                        imagenIcono = new ImageIcon(); // O cualquier otra imagen por defecto
                    }

                    Date fechaCaducidad = rs.getDate("Fecha_Caducidad");
                    String categoria = rs.getString("Categoria");
                    String presentacion = rs.getString("Presentacion");
                    String laboratorio = rs.getString("Laboratorio");
                    String proveedor = rs.getString("Proveedor");

                    // Agregar los datos a la tabla
                    Object[] row = new Object[]{
                        idProducto,
                        nombre,
                        descripcion,
                        cantidad,
                        precioCompra,
                        precioVenta,
                        imagenIcono,
                        fechaCaducidad,
                        categoria,
                        presentacion,
                        laboratorio,
                        proveedor
                    };

                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarDatos(String dato) {
        try {
            CallableStatement cbstc = cn.prepareCall("{call VerificarDatosProducto(?)}");
            cbstc.setString(1, dato);
            ResultSet rs = cbstc.executeQuery();
            return rs.next(); // Devuelve true si existe al menos un registro que cumple la condición
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public void eliminarProductoConProveedor(int idProducto) {
        String sql = "{call EliminarProductoConProveedor(?)}";
        try (CallableStatement stmt = cn.prepareCall(sql)) {
            stmt.setInt(1, idProducto);
            stmt.executeUpdate();
            System.out.println("Producto eliminado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Clase_Categoria> mostrarDatosCombo(String Busqueda) {
        ArrayList<Clase_Categoria> listaProductos = new ArrayList<>();
        try {
            String query = "{ CALL BuscarCategoria_Caja(?) }";
            CallableStatement cstmt = cn.prepareCall(query);
            cstmt.setString(1, Busqueda);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                int idProducto = rs.getInt("Id_Categoria");
                String nombreProducto = rs.getString("Nombre_Categoria");

                Clase_Categoria producto = new Clase_Categoria(idProducto, nombreProducto);
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaProductos;
    }

    public ArrayList<Clase_Presentacion> buscarPresentaciones(String busqueda) {
        ArrayList<Clase_Presentacion> listaPresentaciones = new ArrayList<>();
        try {
            String query = "{ CALL BuscarPresentacion_Caja(?) }";
            CallableStatement cstmt = cn.prepareCall(query);
            cstmt.setString(1, busqueda);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                int idPresentacion = rs.getInt("Id_Presentacion");
                String nombrePresentacion = rs.getString("Nombre_Presentacion");

                Clase_Presentacion presentacion = new Clase_Presentacion(idPresentacion, nombrePresentacion);
                listaPresentaciones.add(presentacion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaPresentaciones;
    }

    public ArrayList<Clase_Laboratorio> buscarLaboratorios(String busqueda) {
        ArrayList<Clase_Laboratorio> listaLaboratorios = new ArrayList<>();
        try {
            String query = "{ CALL BuscarLaboratorio_caja(?) }";
            CallableStatement cstmt = cn.prepareCall(query);
            cstmt.setString(1, busqueda);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                int idLaboratorio = rs.getInt("Id_Laboratorio");
                String nombreLaboratorio = rs.getString("Nombre");

                Clase_Laboratorio laboratorio = new Clase_Laboratorio(idLaboratorio, nombreLaboratorio);
                listaLaboratorios.add(laboratorio);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaLaboratorios;
    }

    public ArrayList<Clase_Proveedor> buscarProveedor(String busqueda) {
        ArrayList<Clase_Proveedor> listaProveedor = new ArrayList<>();
        try {
            String query = "{ CALL BuscarProveedor_Caja(?) }";
            CallableStatement cstmt = cn.prepareCall(query);
            cstmt.setString(1, busqueda);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                int idProveedor = rs.getInt("Id_Proveedor");
                String nombre1 = rs.getString("Nombre_1");
                String apellido1 = rs.getString("Apellido_1");

                Clase_Proveedor proveedor = new Clase_Proveedor(idProveedor, nombre1, apellido1);
                listaProveedor.add(proveedor);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaProveedor;
    }
}
