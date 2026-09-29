package modelo;


public class Desarrollador {

    private int id;
    private String nombre;
    private String pais;
    private int anioFundacion;

    public Desarrollador() {
    }

    public Desarrollador(int id, String nombre, String pais, int anioFundacion) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.anioFundacion = anioFundacion;
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

    public int getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    @Override
    public String toString() {
        return "Desarrollador{id=" + id + ", nombre=" + nombre + ", pais=" + pais + "}";
    }
}
