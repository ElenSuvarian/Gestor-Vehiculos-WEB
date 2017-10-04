package net.juanxxiii.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

















public class GestorBBDD
{
  private static final int DEFAULT_PORT = 3306;
  private String usuario;
  private String password;
  private String ip;
  private String nombreBBDD;
  private int puerto;
  protected Connection conexion;
  
  public GestorBBDD(String usuario, String password, String ip, String nombreBBDD)
  {
    this.usuario = usuario;
    this.password = password;
    this.ip = ip;
    this.nombreBBDD = nombreBBDD;
    puerto = 3306;
  }
  







  public GestorBBDD(String usuario, String password, String ip, String nombreBBDD, int puerto)
  {
    this.usuario = usuario;
    this.password = password;
    this.ip = ip;
    this.nombreBBDD = nombreBBDD;
    this.puerto = puerto;
  }
  



  public void establecerConexion()
    throws ClassNotFoundException, SQLException
  {
    String driver = "com.mysql.jdbc.Driver";
    Class.forName(driver);
    String servidor_bbdd = "jdbc:mysql://" + ip + "/" + nombreBBDD;
    conexion = DriverManager.getConnection(servidor_bbdd, usuario, password);
  }
  


  public void cerrarConexion()
    throws SQLException
  {
    conexion.close();
  }
}