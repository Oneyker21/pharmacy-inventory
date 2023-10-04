package Modelo;

import java.math.BigDecimal;

/**
 *
 * @author diedr
 */
public class Class_Venta_Producto {

    int Id_Venta_Producto;
    int Cantidad;
    BigDecimal Descuento;
    int Id_Venta;
    int Id_Producto;

    public Class_Venta_Producto(int Cantidad, BigDecimal Descuento, int Id_Producto) {
        this.Cantidad = Cantidad;
        this.Descuento = Descuento;
        this.Id_Producto = Id_Producto;
    }

    public Class_Venta_Producto() {
    }

    public Class_Venta_Producto(int Cantidad, BigDecimal Descuento, int Id_Venta, int Id_Producto) {
        this.Cantidad = Cantidad;
        this.Descuento = Descuento;
        this.Id_Venta = Id_Venta;
        this.Id_Producto = Id_Producto;
    }

    public Class_Venta_Producto(int Id_Venta_Producto, int Cantidad, BigDecimal Descuento, int Id_Venta, int Id_Producto) {
        this.Id_Venta_Producto = Id_Venta_Producto;
        this.Cantidad = Cantidad;
        this.Descuento = Descuento;
        this.Id_Venta = Id_Venta;
        this.Id_Producto = Id_Producto;
    }

    public int getId_Venta_Producto() {
        return Id_Venta_Producto;
    }

    public void setId_Venta_Producto(int Id_Venta_Producto) {
        this.Id_Venta_Producto = Id_Venta_Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public BigDecimal getDescuento() {
        return Descuento;
    }

    public void setDescuento(BigDecimal Descuento) {
        this.Descuento = Descuento;
    }

    public int getId_Venta() {
        return Id_Venta;
    }

    public void setId_Venta(int Id_Venta) {
        this.Id_Venta = Id_Venta;
    }

    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

}
