package Objects;

public class Clientes {
    private String DNI;
    private String nombre;
    private int edad;
    private int num_de_asiento;

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNum_de_asiento() {
        return num_de_asiento;
    }

    public void setNum_de_asiento(int num_de_asiento) {
        this.num_de_asiento = num_de_asiento;
    }
}
