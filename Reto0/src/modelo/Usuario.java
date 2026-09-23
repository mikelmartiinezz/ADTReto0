package modelo;

import java.time.LocalDate;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private LocalDate fechaAlta;
    private String ruta; // ruta del avatar; solo tienen valor los usuarios precargados

    public Usuario() {
    }

    public Usuario(int id, String nombre, String email, String telefono,
                   LocalDate fechaAlta, String ruta) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
        this.ruta = ruta;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre=" + nombre + ", email=" + email + "}";
    }
}