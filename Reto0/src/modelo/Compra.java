package modelo;

import java.time.LocalDate;

public class Compra {

    private int id;
    private LocalDate fecha;
    private int cantidad;
    private int usuarioId;
    private int juegoId;

    public Compra() {
    }

    public Compra(int id, LocalDate fecha, int cantidad, int usuarioId, int juegoId) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.usuarioId = usuarioId;
        this.juegoId = juegoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(int juegoId) {
        this.juegoId = juegoId;
    }

    @Override
    public String toString() {
        return "Compra{id=" + id + ", fecha=" + fecha + ", cantidad=" + cantidad
                + ", usuarioId=" + usuarioId + ", juegoId=" + juegoId + "}";
    }
}