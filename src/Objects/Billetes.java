package Objects;

import java.util.Date;

public class Billetes {
    private int Num_billete;
    private Date fecha;
    private float precio;
    private String tipo_pago;
    private String destino;
    private String origen;
    private String DNI;
    private int Num_viaje;
    public Billetes(){

    }

    public int getNum_billete() {
        return Num_billete;
    }

    public void setNum_billete(int num_billete) {
        Num_billete = num_billete;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getNum_viaje() {
        return Num_viaje;
    }

    public void setNum_viaje(int num_viaje) {
        Num_viaje = num_viaje;
    }
}
