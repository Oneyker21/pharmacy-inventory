package Modelo;

/**
 *
 * @author diedr
 */
public class Clase_Proveedor extends Clase_Persona {

    public int Id_Proveedor;

    public Clase_Proveedor(int Id_Proveedor) {
        this.Id_Proveedor = Id_Proveedor;
    }

    public Clase_Proveedor() {
    }

    public Clase_Proveedor(int Id_Proveedor, String Nombre_1, String Apellido_1) {
        super(Nombre_1, Apellido_1);
        this.Id_Proveedor = Id_Proveedor;
    }

    public Clase_Proveedor(String Cedula, String Nombre_1, String Nombre_2, String Apellido_1, String Apellido_2, String Numero_Celular, String Gmail, String Direccion) {
        super(Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion);
    }

    public Clase_Proveedor(int Id_Proveedor, String Cedula, String Nombre_1, String Nombre_2, String Apellido_1, String Apellido_2, String Numero_Celular, String Gmail, String Direccion) {
        super(Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion);
        this.Id_Proveedor = Id_Proveedor;
    }

    public int getId_Proveedor() {
        return Id_Proveedor;
    }

    public void setId_Proveedor(int Id_Proveedor) {
        this.Id_Proveedor = Id_Proveedor;
    }

    @Override
    public String toString() {
        return "ID: " + Id_Proveedor + " " + Nombre_1 + " " + Apellido_1;
    }

    public String AtoString() {
        return getId_Proveedor() + " - " + getNombre_1() + " " + getApellido_1();
    }
}
