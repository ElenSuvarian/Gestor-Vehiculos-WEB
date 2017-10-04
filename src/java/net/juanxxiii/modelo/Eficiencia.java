package net.juanxxiii.modelo;



public class Eficiencia
{
  private int id;
  

  private String nombre;
  

  private String descripcion;
  
  private String nombre_fichero;
  

  public Eficiencia(String nombre, String descripcion, String nombre_fichero)
  {
    this.nombre = nombre;
    this.nombre_fichero = nombre_fichero;
    this.descripcion = descripcion;
  }
  




  public Eficiencia(String nombre, String descripcion)
  {
    this.nombre = nombre;
    this.descripcion = descripcion;
  }
  
  public int getId() {
    return id;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public String getDescripcion() {
    return descripcion;
  }
  
  public String getNombre_fichero() {
    return nombre_fichero;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public void setNombre_fichero(String nombre_fichero) {
    this.nombre_fichero = nombre_fichero;
  }
}