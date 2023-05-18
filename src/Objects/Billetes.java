package Objects;

public class Billetes {
    private int Num_billete;
    private String fecha;
    private float precio;
    private String tipo_pago;
    private String destino;
    private String origen;
    private String DNI;
    private int Num_viaje;
    public Billetes(int Num_billete,String fecha,float precio,String tipo_pago,String destino,String origen,String DNI){
        this.Num_billete=Num_billete;
        this.fecha=fecha;
        this.precio=precio;
        this.tipo_pago=tipo_pago;
        this.destino=destino;
        this.origen=origen;
        this.DNI=DNI;


    }

    public int getNum_billete() {
        return Num_billete;
    }

    public void setNum_billete(int num_billete) {
        Num_billete = num_billete;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPrecio() {
        return (int) precio;
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
