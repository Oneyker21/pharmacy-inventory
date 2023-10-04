package Modelo;

/**
 *
 * @author Diers
 */
public class Clase_Persona {

    public int Id_Persona;
    public String Cedula;
    public String Nombre_1;
    public String Nombre_2;
    public String Apellido_1;
    public String Apellido_2;
    public String Numero_Celular;
    public String Gmail;
    public String Direccion;

    public Clase_Persona(String Nombre_1, String Apellido_1) {
        this.Nombre_1 = Nombre_1;
        this.Apellido_1 = Apellido_1;
    }

    public Clase_Persona() {
    }

    public Clase_Persona(String Cedula, String Nombre_1, String Nombre_2, String Apellido_1, String Apellido_2, String Numero_Celular, String Gmail, String Direccion) {
        this.Cedula = Cedula;
        this.Nombre_1 = Nombre_1;
        this.Nombre_2 = Nombre_2;
        this.Apellido_1 = Apellido_1;
        this.Apellido_2 = Apellido_2;
        this.Numero_Celular = Numero_Celular;
        this.Gmail = Gmail;
        this.Direccion = Direccion;
    }

    public Clase_Persona(int Id_Persona, String Cedula, String Nombre_1, String Nombre_2, String Apellido_1, String Apellido_2, String Numero_Celular, String Gmail, String Direccion) {
        this.Id_Persona = Id_Persona;
        this.Cedula = Cedula;
        this.Nombre_1 = Nombre_1;
        this.Nombre_2 = Nombre_2;
        this.Apellido_1 = Apellido_1;
        this.Apellido_2 = Apellido_2;
        this.Numero_Celular = Numero_Celular;
        this.Gmail = Gmail;
        this.Direccion = Direccion;

    }

    public int getId_Persona() {
        return Id_Persona;
    }

    public void setId_Persona(int Id_Persona) {
        this.Id_Persona = Id_Persona;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre_1() {
        return Nombre_1;
    }

    public void setNombre_1(String Nombre_1) {
        this.Nombre_1 = Nombre_1;
    }

    public String getNombre_2() {
        return Nombre_2;
    }

    public void setNombre_2(String Nombre_2) {
        this.Nombre_2 = Nombre_2;
    }

    public String getApellido_1() {
        return Apellido_1;
    }

    public void setApellido_1(String Apellido_1) {
        this.Apellido_1 = Apellido_1;
    }

    public String getApellido_2() {
        return Apellido_2;
    }

    public void setApellido_2(String Apellido_2) {
        this.Apellido_2 = Apellido_2;
    }

    public String getNumero_Celular() {
        return Numero_Celular;
    }

    public void setNumero_Celular(String Numero_Celular) {
        this.Numero_Celular = Numero_Celular;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String Gmail) {
        this.Gmail = Gmail;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String toString() {
        return "Clase_Persona{"
                + "Id_Persona=" + Id_Persona
                + ", Cedula='" + Cedula + '\''
                + ", Nombre_1='" + Nombre_1 + '\''
                + ", Nombre_2='" + Nombre_2 + '\''
                + ", Apellido_1='" + Apellido_1 + '\''
                + ", Apellido_2='" + Apellido_2 + '\''
                + ", Numero_Celular='" + Numero_Celular + '\''
                + ", Gmail='" + Gmail + '\''
                + ", Direccion='" + Direccion + '\''
                + '}';
    }
}
