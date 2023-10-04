package Modelo;

public class Clase_Producto_Cargardatos {

    public int Id_Producto;
    public String Nombre;
    public String Descripcion;
    public float Precio_Venta;
    public String Id_Categoria;
    public String Id_Presentacion;
    public String Id_Laboratorio;

    public Clase_Producto_Cargardatos(int Id_Producto, String Nombre, String Descripcion, float Precio_Venta, String Id_Categoria, String Id_Presentacion, String Id_Laboratorio) {
        this.Id_Producto = Id_Producto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio_Venta = Precio_Venta;
        this.Id_Categoria = Id_Categoria;
        this.Id_Presentacion = Id_Presentacion;
        this.Id_Laboratorio = Id_Laboratorio;
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

    public float getPrecio_Venta() {
        return Precio_Venta;
    }

    public void setPrecio_Venta(float Precio_Venta) {
        this.Precio_Venta = Precio_Venta;
    }

    public String getId_Categoria() {
        return Id_Categoria;
    }

    public void setId_Categoria(String Id_Categoria) {
        this.Id_Categoria = Id_Categoria;
    }

    public String getId_Presentacion() {
        return Id_Presentacion;
    }

    public void setId_Presentacion(String Id_Presentacion) {
        this.Id_Presentacion = Id_Presentacion;
    }

    public String getId_Laboratorio() {
        return Id_Laboratorio;
    }

    public void setId_Laboratorio(String Id_Laboratorio) {
        this.Id_Laboratorio = Id_Laboratorio;
    }

    @Override
    public String toString() {
        return "Nombre: " + Nombre + ", Precio: " + Precio_Venta + ", Categoría: " + Id_Categoria
                + ", Presentación: " + Id_Presentacion + ", Laboratorio: " + Id_Laboratorio;
    }

}
