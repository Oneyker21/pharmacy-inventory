package Modelo;

/**
 *
 * @author diedr
 */
public class Clase_Cliente extends Clase_Persona {

    int Id_Cliente;

    public Clase_Cliente(int Id_Cliente, String Nombre_1, String Apellido_1) {
        super(Nombre_1, Apellido_1);
        this.Id_Cliente = Id_Cliente;
    }

    public Clase_Cliente(int Id_Cliente, String Cedula, String Nombre_1, String Nombre_2, String Apellido_1, String Apellido_2, String Numero_Celular, String Gmail, String Direccion) {
        super(Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion);
        this.Id_Cliente = Id_Cliente;
    }

    public Clase_Cliente(String Cedula, String Nombre_1, String Nombre_2, String Apellido_1, String Apellido_2, String Numero_Celular, String Gmail, String Direccion) {
        super(Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion);

    }

    public Clase_Cliente(int Id_Cliente, int Id_Persona, String Cedula, String Nombre_1, String Nombre_2, String Apellido_1, String Apellido_2, String Numero_Celular, String Gmail, String Direccion) {
        super(Id_Persona, Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion);
        this.Id_Cliente = Id_Cliente;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int Id_Cliente) {
        this.Id_Cliente = Id_Cliente;
    }

    public String getNombreCompleto() {
        return getNombre_1() + " " + getApellido_1();
    }

    public String getRepresentacion1() {
        return "Clase_Cliente{"
                + "Id_Cliente=" + Id_Cliente
                + "} " + super.toString();
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }

}
