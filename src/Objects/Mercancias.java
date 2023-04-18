package Objects;

public class Mercancias {
    private int num_producto;
    private int num_empresa;
    private String descripcion;
    private float peso;
    private float precio;

    public int getNum_producto() {
        return num_producto;
    }

    public void setNum_producto(int num_producto) {
        this.num_producto = num_producto;
    }

    public int getNum_empresa() {
        return num_empresa;
    }

    public void setNum_empresa(int num_empresa) {
        this.num_empresa = num_empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
