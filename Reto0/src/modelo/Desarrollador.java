package modelo;


public class Desarrollador {

    private int id;
    private String nombre;
    private String pais;
    private int añoFundacion;

    public Desarrollador() {
    }

    public Desarrollador(int id, String nombre, String pais, int añoFundacion) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.añoFundacion = añoFundacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getAñoFundacion() {
        return añoFundacion;
    }

    public void setAñoFundacion(int añoFundacion) {
        this.añoFundacion = añoFundacion;
    }

    @Override
    public String toString() {
        return "Desarrollador{id=" + id + ", nombre=" + nombre + ", pais=" + pais + "}";
    }
}
