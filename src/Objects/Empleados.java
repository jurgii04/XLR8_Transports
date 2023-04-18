package Objects;

import java.util.Date;

public class Empleados {
    private int numemp;
    private String DNI;
    private Date fecha_contrato;
    private String nombre_apellido;
    private int jefe;

    public int getNumemp() {
        return numemp;
    }

    public void setNumemp(int numemp) {
        this.numemp = numemp;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Date getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(Date fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }

    public String getNombre_apellido() {
        return nombre_apellido;
    }

    public void setNombre_apellido(String nombre_apellido) {
        this.nombre_apellido = nombre_apellido;
    }

    public int getJefe() {
        return jefe;
    }

    public void setJefe(int jefe) {
        this.jefe = jefe;
    }
}
