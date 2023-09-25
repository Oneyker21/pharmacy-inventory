package Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author diedr
 */
public class Class_Compra extends Class_Compra_Producto {

    int Id_Compra;
    LocalDateTime Fecha_Compra;
    int Id_Proveedor;

    public Class_Compra(LocalDateTime Fecha_Compra, int Id_Proveedor, int Id_Producto, int Cantidad) {
        super( Id_Producto,Cantidad);
        this.Fecha_Compra = Fecha_Compra;
        this.Id_Proveedor = Id_Proveedor;
    }

    public LocalDateTime getFecha_Compra() {
        return Fecha_Compra;
    }

    public void setFecha_Compra(LocalDateTime Fecha_Compra) {
        this.Fecha_Compra = Fecha_Compra;
    }

    public int getId_Compra() {
        return Id_Compra;
    }

    public void setId_Compra(int Id_Compra) {
        this.Id_Compra = Id_Compra;
    }

    public int getId_Compra_Producto() {
        return Id_Compra_Producto;
    }

    public void setId_Compra_Producto(int Id_Compra_Producto) {
        this.Id_Compra_Producto = Id_Compra_Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public int getId_Proveedor() {
        return Id_Proveedor;
    }

    public void setId_Proveedor(int Id_Proveedor) {
        this.Id_Proveedor = Id_Proveedor;
    }

}
