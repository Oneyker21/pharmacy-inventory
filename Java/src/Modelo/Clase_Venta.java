package Modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author diedr
 */
public class Clase_Venta extends Class_Venta_Producto {

    int Id_Venta;
    LocalDateTime Fecha_Hora;
    int Id_Cliente;
    int Id_Empleado;

    public Clase_Venta(int Id_Venta) {
        this.Id_Venta = Id_Venta;
    }

    public Clase_Venta(int Id_Venta, LocalDateTime Fecha_Hora, int Id_Cliente) {
        this.Id_Venta = Id_Venta;
        this.Fecha_Hora = Fecha_Hora;
        this.Id_Cliente = Id_Cliente;
    }

    public Clase_Venta(LocalDateTime Fecha_Hora, int Id_Cliente, int Id_Empleado, int Cantidad, BigDecimal Descuento, int Id_Producto) {
        super(Cantidad, Descuento, Id_Producto);
        this.Fecha_Hora = Fecha_Hora;
        this.Id_Cliente = Id_Cliente;
        this.Id_Empleado = Id_Empleado;
    }

    public Clase_Venta(LocalDateTime Fecha_Hora, int Id_Cliente, int Id_Empleado, int Cantidad, BigDecimal Descuento, int Id_Venta, int Id_Producto) {
        super(Cantidad, Descuento, Id_Venta, Id_Producto);
        this.Fecha_Hora = Fecha_Hora;
        this.Id_Cliente = Id_Cliente;
        this.Id_Empleado = Id_Empleado;
    }

    public int getId_Venta() {
        return Id_Venta;
    }

    public void setId_Venta(int Id_Venta) {
        this.Id_Venta = Id_Venta;
    }

    public LocalDateTime getFecha_Hora() {
        return Fecha_Hora;
    }

    public void setFecha_Hora(LocalDateTime Fecha_Hora) {
        this.Fecha_Hora = Fecha_Hora;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int Id_Cliente) {
        this.Id_Cliente = Id_Cliente;
    }

    public int getId_Empleado() {
        return Id_Empleado;
    }

    public void setId_Empleado(int Id_Empleado) {
        this.Id_Empleado = Id_Empleado;
    }

    @Override
    public String toString() {
        return "Cliente: " + this.Id_Cliente + ", Empleado: " + this.Id_Empleado + ", Producto: " + this.getId_Producto() + ", Cantidad: " + this.getCantidad();
    }

}
