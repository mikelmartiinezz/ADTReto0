/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Ike.Bosquez
 */
public class Desarrollador {
    private Integer id;
    private String nombre;
    private String pais;
    private Integer añoFundacion;
public Desarrollador() {
    }

    public Desarrollador(Integer id, String nombre, String pais, Integer añoFundacion) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.añoFundacion = añoFundacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAnioFundacion() {
        return añoFundacion;
    }

    public void setAnioFundacion(Integer anioFundacion) {
        this.añoFundacion = anioFundacion;
    }
}
