package Modelo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author diedr
 */
public class Clase_Producto extends Clase_Proveedor {

    public int Id_Producto;
    public String Nombre;
    public String Descripcion;
    public int Cantidad_Producto;
    public float Precio_Compra;
    public float Precio_Venta;
    public byte[] Imagen_Producto;
    public java.sql.Date Fecha_Caducidad;
    public int Id_Categoria;
    public int Id_Presentacion;
    public int Id_Laboratorio;

    public Clase_Producto(int Id_Producto, int Cantidad_Producto, float Precio_Compra, float Precio_Venta, Date Fecha_Caducidad) {
        this.Id_Producto = Id_Producto;
        this.Cantidad_Producto = Cantidad_Producto;
        this.Precio_Compra = Precio_Compra;
        this.Precio_Venta = Precio_Venta;
        this.Fecha_Caducidad = Fecha_Caducidad;
    }

    public Clase_Producto(int Id_Producto, String Nombre, String Descripcion, float Precio_Venta, int Id_Categoria, int Id_Presentacion, int Id_Laboratorio) {
        this.Id_Producto = Id_Producto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio_Venta = Precio_Venta;
        this.Id_Categoria = Id_Categoria;
        this.Id_Presentacion = Id_Presentacion;
        this.Id_Laboratorio = Id_Laboratorio;
    }

    public Clase_Producto(int Id_Producto, String Nombre, String Descripcion, int Cantidad_Producto, float Precio_Compra, float Precio_Venta, byte[] Imagen_Producto, java.util.Date Fecha_Caducidad, int Id_Categoria, int Id_Presentacion, int Id_Laboratorio, int Id_Proveedor) {
        super(Id_Proveedor);
        this.Id_Producto = Id_Producto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Cantidad_Producto = Cantidad_Producto;
        this.Precio_Compra = Precio_Compra;
        this.Precio_Venta = Precio_Venta;
        this.Imagen_Producto = Imagen_Producto;
        this.Fecha_Caducidad = new java.sql.Date(Fecha_Caducidad.getTime());
        this.Id_Categoria = Id_Categoria;
        this.Id_Presentacion = Id_Presentacion;
        this.Id_Laboratorio = Id_Laboratorio;
    }

    public ImageIcon obtenerImagenComoIcono() {
        if (this.Imagen_Producto == null) {
            return null;
        }

        ImageIcon icono = null;

        try {
            icono = new ImageIcon(ImageIO.read(new ByteArrayInputStream(this.Imagen_Producto)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return icono;
    }

    public Clase_Producto(int Id_Producto, String Nombre, String Descripcion, int Cantidad_Producto, float Precio_Compra, float Precio_Venta, byte[] Imagen_Producto, Date Fecha_Caducidad, int Id_Categoria, int Id_Presentacion, int Id_Laboratorio) {
        this.Id_Producto = Id_Producto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Cantidad_Producto = Cantidad_Producto;
        this.Precio_Compra = Precio_Compra;
        this.Precio_Venta = Precio_Venta;
        this.Imagen_Producto = Imagen_Producto;
        this.Fecha_Caducidad = Fecha_Caducidad;
        this.Id_Categoria = Id_Categoria;
        this.Id_Presentacion = Id_Presentacion;
        this.Id_Laboratorio = Id_Laboratorio;
    }

    public Clase_Producto() {
    }

    public Clase_Producto(String Nombre, String Descripcion, int Cantidad_Producto, float Precio_Compra, float Precio_Venta, byte[] Imagen_Producto, Date Fecha_Caducidad, int Id_Categoria, int Id_Presentacion, int Id_Laboratorio, int Id_Proveedor) {
        super(Id_Proveedor);
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Cantidad_Producto = Cantidad_Producto;
        this.Precio_Compra = Precio_Compra;
        this.Precio_Venta = Precio_Venta;
        this.Imagen_Producto = Imagen_Producto;
        this.Fecha_Caducidad = Fecha_Caducidad;
        this.Id_Categoria = Id_Categoria;
        this.Id_Presentacion = Id_Presentacion;
        this.Id_Laboratorio = Id_Laboratorio;
    }

    public Clase_Producto(int Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public Clase_Producto(int Id_Producto, String Nombre, float Precio_Venta) {
        this.Id_Producto = Id_Producto;
        this.Nombre = Nombre;
        this.Precio_Venta = Precio_Venta;
    }

    @Override
    public String toString() {
        return "Nombre: " + Nombre + ", Precio: " + String.valueOf(Precio_Venta);
    }

    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getCantidad_Producto() {
        return Cantidad_Producto;
    }

    public void setCantidad_Producto(int Cantidad_Producto) {
        this.Cantidad_Producto = Cantidad_Producto;
    }

    public float getPrecio_Compra() {
        return Precio_Compra;
    }

    public void setPrecio_Compra(float Precio_Compra) {
        this.Precio_Compra = Precio_Compra;
    }

    public float getPrecio_Venta() {
        return Precio_Venta;
    }

    public void setPrecio_Venta(float Precio_Venta) {
        this.Precio_Venta = Precio_Venta;
    }

    public byte[] getImagen_Producto() {
        return Imagen_Producto;
    }

    public void setImagen_Producto(byte[] Imagen_Producto) {
        this.Imagen_Producto = Imagen_Producto;
    }

    public Date getFecha_Caducidad() {
        return Fecha_Caducidad;
    }

    public void setFecha_Caducidad(Date Fecha_Caducidad) {
        this.Fecha_Caducidad = Fecha_Caducidad;
    }

    public int getId_Categoria() {
        return Id_Categoria;
    }

    public void setId_Categoria(int Id_Categoria) {
        this.Id_Categoria = Id_Categoria;
    }

    public int getId_Presentacion() {
        return Id_Presentacion;
    }

    public void setId_Presentacion(int Id_Presentacion) {
        this.Id_Presentacion = Id_Presentacion;
    }

    public int getId_Laboratorio() {
        return Id_Laboratorio;
    }

    public void setId_Laboratorio(int Id_Laboratorio) {
        this.Id_Laboratorio = Id_Laboratorio;
    }

    public String toDetailedString() {
        return "Clase_Producto { "
                + "Id_Producto=" + Id_Producto
                + ", Nombre='" + Nombre + '\''
                + ", Descripcion='" + Descripcion + '\''
                + ", Cantidad_Producto=" + Cantidad_Producto
                + ", Precio_Compra=" + Precio_Compra
                + ", Precio_Venta=" + Precio_Venta
                + // No se incluye Imagen_Producto porque es un array de bytes
                ", Fecha_Caducidad=" + Fecha_Caducidad
                + ", Id_Categoria=" + Id_Categoria
                + ", Id_Presentacion=" + Id_Presentacion
                + ", Id_Laboratorio=" + Id_Laboratorio
                + " }";
    }

}
