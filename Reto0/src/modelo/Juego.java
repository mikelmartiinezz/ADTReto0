package modelo;

import java.io.Serializable;


public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private double precio;
    private int stock;
    private Genero genero;
    private int idDesarrollador;

    public Juego() {
    }

    public Juego(int id, String titulo, double precio, int stock, Genero genero, int idDesarrollador) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.stock = stock;
        this.genero = genero;
        this.idDesarrollador = idDesarrollador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(int idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    @Override
    public String toString() {
        return "Juego{id=" + id + ", titulo=" + titulo + ", precio=" + precio
                + ", stock=" + stock + ", genero=" + genero
                + ", idDesarrollador=" + idDesarrollador + "}";
    }
}
