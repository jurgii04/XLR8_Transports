package Objects;

public class Van {
    private int Num_empresa;
    private int Num_producto;
    private int codigo;
    private int cantidad;

    public int getNum_empresa() {
        return Num_empresa;
    }

    public void setNum_empresa(int num_empresa) {
        Num_empresa = num_empresa;
    }

    public int getNum_producto() {
        return Num_producto;
    }

    public void setNum_producto(int num_producto) {
        Num_producto = num_producto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
