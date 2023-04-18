package Objects;

public class Pedidos {
    private int Codigo;
    private int num_empresa;
    private String Origen;
    private String Destino;
    private String Tipo_envio;
    private String matricula;
    private float peso_total;
    private float Distancia;
    private float Precio;
    private float Gastos;

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public int getNum_empresa() {
        return num_empresa;
    }

    public void setNum_empresa(int num_empresa) {
        this.num_empresa = num_empresa;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String origen) {
        Origen = origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public String getTipo_envio() {
        return Tipo_envio;
    }

    public void setTipo_envio(String tipo_envio) {
        Tipo_envio = tipo_envio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getPeso_total() {
        return peso_total;
    }

    public void setPeso_total(float peso_total) {
        this.peso_total = peso_total;
    }

    public float getDistancia() {
        return Distancia;
    }

    public void setDistancia(float distancia) {
        Distancia = distancia;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }

    public float getGastos() {
        return Gastos;
    }

    public void setGastos(float gastos) {
        Gastos = gastos;
    }
}
