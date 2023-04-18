package Objects;

public class Viajes {
    private String tipo_viaje;
    private String destino;
    private String matricula;
    private String origen;
    private int Num_viaje;
    private int distancia;
    private float gastos;
    private float precio;

    public String getTipo_viaje() {
        return tipo_viaje;
    }

    public void setTipo_viaje(String tipo_viaje) {
        this.tipo_viaje = tipo_viaje;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getNum_viaje() {
        return Num_viaje;
    }

    public void setNum_viaje(int num_viaje) {
        Num_viaje = num_viaje;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
