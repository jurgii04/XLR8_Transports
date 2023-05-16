package Objects;

public class User {
    private String nombre_completo;
    private String EMAIL;
    private String FECHA_NACIMIENTO;
    private String DNI;
    private String GENERO;
    private String CONTRASENA;
    private String DIRECCION;
    private String TELEFONO;
    private String SECTOR;
    private String TIPO_USER;
    private String IMG;
    public User(String nombre_completo,String EMAIL,String FECHA_NACIMIENTO,String DNI,String GENERO,String IMG){
        this.nombre_completo=nombre_completo;
        this.EMAIL=EMAIL;
        this.FECHA_NACIMIENTO=FECHA_NACIMIENTO;
        this.DNI=DNI;
        this.GENERO=GENERO;
        this.IMG=IMG;

    }
    public User(String nombre_completo,String EMAIL,String DIRECCION,String DNI,String TELEFONO,String IMG,String SECTOR){
        this.nombre_completo=nombre_completo;
        this.EMAIL=EMAIL;
        this.DIRECCION=DIRECCION;
        this.DNI=DNI;
        this.TELEFONO=TELEFONO;
        this.SECTOR=SECTOR;
        this.IMG=IMG;

    }
    public User(){
        this.nombre_completo="";
        this.EMAIL="";
        this.FECHA_NACIMIENTO="";
        this.DNI="";
        this.GENERO="";
        this.IMG="";
        this.DIRECCION="";
        this.TELEFONO="";
        this.SECTOR="";

    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getFECHA_NACIMIENTO() {
        return FECHA_NACIMIENTO;
    }

    public void setFECHA_NACIMIENTO(String FECHA_NACIMIENTO) {
        this.FECHA_NACIMIENTO = FECHA_NACIMIENTO;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getGENERO() {
        return GENERO;
    }

    public void setGENERO(String GENERO) {
        this.GENERO = GENERO;
    }

    public String getCONTRASENA() {
        return CONTRASENA;
    }

    public void setCONTRASENA(String CONTRASENA) {
        this.CONTRASENA = CONTRASENA;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getSECTOR() {
        return SECTOR;
    }

    public void setSECTOR(String SECTOR) {
        this.SECTOR = SECTOR;
    }

    public String getTIPO_USER() {
        return TIPO_USER;
    }

    public void setTIPO_USER(String TIPO_USER) {
        this.TIPO_USER = TIPO_USER;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }
}
