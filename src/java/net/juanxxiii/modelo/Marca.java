package net.juanxxiii.modelo;

public class Marca {

    private int id;

    private String nombre;

    public Marca(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
