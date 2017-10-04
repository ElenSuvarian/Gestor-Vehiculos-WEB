package net.juanxxiii.modelo;

public class Modelo {

    private int id;

    private int idMarca;

    private String idEficiencia;

    private String nombre;

    private float consumo;

    private float emisiones;
     private String marca;
    private String logoEficiencia;

    public Modelo(int idMarca, String idEficiencia, String nombre, float consumo, float emisiones) {
        this.idMarca = idMarca;
        this.idEficiencia = idEficiencia;
        this.nombre = nombre;
        this.consumo = consumo;
        this.emisiones = emisiones;
    }
    public Modelo(String marca, String logoEficiencia, String nombre, float consumo, float emisiones,int idMarca,int id) {
        this.marca = marca;
        this.logoEficiencia = logoEficiencia;
        this.nombre = nombre;
        this.consumo = consumo;
        this.emisiones = emisiones;
        this.idMarca=idMarca;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getConsumo() {
        return consumo;
    }

    public float getEmisiones() {
        return emisiones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setConsumo(float consumo) {
        this.consumo = consumo;
    }

    public void setEmisiones(float emisiones) {
        this.emisiones = emisiones;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public String getMarca() {
        return marca;
    }

    public String getLogoEficiencia() {
        return logoEficiencia;
    }

    public String getIdEficiencia() {
        return idEficiencia;
    }
}
