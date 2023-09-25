package Modelo;

import java.math.BigDecimal;
import java.sql.Time;

/**
 *
 * @author diedr
 */
public class Clase_Empleado extends Clase_Persona {

    public int Id_Empleado;

    public Clase_Empleado(int Id_Empleado, String Nombre_1, String Apellido_1) {
        super(Nombre_1, Apellido_1);
        this.Id_Empleado = Id_Empleado;
    }
    public BigDecimal Salario;
    public Time Hora_Entrada;
    public Time Hora_Salida;

    

    public Clase_Empleado(BigDecimal Salario, Time Hora_Entrada, Time Hora_Salida, String Cedula, String Nombre_1, String Nombre_2, String Apellido_1, String Apellido_2, String Numero_Celular, String Gmail, String Direccion) {
        super(Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion);
        this.Salario = Salario;
        this.Hora_Entrada = Hora_Entrada;
        this.Hora_Salida = Hora_Salida;
    }

    public Clase_Empleado(BigDecimal Salario, Time Hora_Entrada, Time Hora_Salida, int Id_Empleado, String Cedula, String Nombre_1, String Nombre_2, String Apellido_1, String Apellido_2, String Numero_Celular, String Gmail, String Direccion) {
        super(Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion);
        this.Salario = Salario;
        this.Hora_Entrada = Hora_Entrada;
        this.Hora_Salida = Hora_Salida;
        this.Id_Empleado = Id_Empleado;
    }

    public int getId_Empleado() {
        return Id_Empleado;
    }

    public void setId_Empleado(int Id_Empleado) {
        this.Id_Empleado = Id_Empleado;
    }

    public BigDecimal getSalario() {
        return Salario;
    }

    public void setSalario(BigDecimal Salario) {
        this.Salario = Salario;
    }

    public Time getHora_Entrada() {
        return Hora_Entrada;
    }

    public void setHora_Entrada(Time Hora_Entrada) {
        this.Hora_Entrada = Hora_Entrada;
    }

    public Time getHora_Salida() {
        return Hora_Salida;
    }

    public void setHora_Salida(Time Hora_Salida) {
        this.Hora_Salida = Hora_Salida;
    }

 @Override
public String toString() {
    if (Id_Empleado != 0) {
        return "Clase_Empleado{" +
                "Id_Empleado=" + Id_Empleado +
                ", Nombre_1='" + getNombre_1() + '\'' +
                ", Apellido_1='" + getApellido_1() + '\'' +
                ", Salario=" + Salario +
                ", Hora_Entrada=" + Hora_Entrada +
                ", Hora_Salida=" + Hora_Salida +
                '}';
    } else {
        return Id_Empleado + " - " + getNombre_1() + " " + getApellido_1();
    }
}

}
