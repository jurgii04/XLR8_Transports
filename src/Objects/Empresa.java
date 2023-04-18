package Objects;

public class Empresa {
    private int Num_empresa;
    private String localizacion;
    private String nombre_empresa;

    public int getNum_empresa() {
        return Num_empresa;
    }

    public void setNum_empresa(int num_empresa) {
        Num_empresa = num_empresa;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }
}
