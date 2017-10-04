package net.juanxxiii.modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.juanxxiii.persistencia.GestorBBDDVehiculos;

public class GestorVehiculos {

    public GestorVehiculos() {
    }

    GestorBBDDVehiculos gbdv = new GestorBBDDVehiculos("root", "", "127.0.0.1", "bbdd_gestmotor");

    public void addVehiculo(String modelo, String marca, String consumo, String emisiones, String eficiencia) throws SQLException, ClassNotFoundException {
        int idMarca = gbdv.getIDMarca(marca);
        String modeloFinal = marca + " " + modelo;
        String idEficiencia = gbdv.getIDEficiencia(eficiencia);
        float consumoFinal = Float.parseFloat(consumo);
        float emisionesFinal = Float.parseFloat(emisiones);
        Modelo modelo1 = new Modelo(idMarca, idEficiencia, modeloFinal, consumoFinal, emisionesFinal);

        gbdv.insertarModelo(modelo1);
    }

    public ArrayList<Marca> getMarcas() throws SQLException, ClassNotFoundException {
        ArrayList<Marca> marcas = gbdv.getMarcas();
        return marcas;
    }

    public ArrayList<String> getEficiencias() throws SQLException, ClassNotFoundException {
        ArrayList<String> eficiencias = gbdv.getEficiencia();
        return eficiencias;
    }

    public String getMarca(int id) throws SQLException, ClassNotFoundException {
        String marca = gbdv.getMarca(id);
        return marca;
    }

    public float getConsumoMax() throws SQLException, ClassNotFoundException {
        float consumoMax = gbdv.getMaxConsumo();
        return consumoMax;
    }

    public ArrayList<Modelo> getModelos(int marca, float consumo,int numPaginas) throws ClassNotFoundException, SQLException {
        ArrayList<Modelo> alm = gbdv.getModelos(marca, consumo,numPaginas);
        return alm;
    }
    public ArrayList<Modelo> getModelos(int marca,int numPaginas) throws ClassNotFoundException, SQLException {
		ArrayList<Modelo> alm = gbdv.getModelos(marca,numPaginas);
		return alm;
	}
    
    public ArrayList<Modelo> getModelos(float consumo,int numPaginas) throws ClassNotFoundException, SQLException {
		ArrayList<Modelo> alm = gbdv.getModelos(consumo,numPaginas);
		return alm;
	}
    
    public int getIDMarca(String marca) throws SQLException, ClassNotFoundException {
		int idMarca = gbdv.getIDMarca(marca);
		return idMarca;
	}
    
     public void eliminarVehiculo(int id) throws ClassNotFoundException, SQLException{
         gbdv.borrarModelo(id);
    }
     public int getNumModelos(int marca,float consumo) throws SQLException, ClassNotFoundException{
         int numModelos=gbdv.getNumModelos(marca,consumo);
         return numModelos;
     }
      public int getNumModelos(int marca) throws SQLException, ClassNotFoundException{
         int numModelos=gbdv.getNumModelos(marca);
         
         return numModelos;
     }
       public int getNumModelos(float consumo) throws SQLException, ClassNotFoundException{
         int numModelos=gbdv.getNumModelos(consumo);
         
         return numModelos;
     }
/*
    public static void main(String[] args) {
        try {
            GestorVehiculos gv = new GestorVehiculos();
            System.out.println(gv.getConsumoMax());
        } catch (SQLException ex) {
            Logger.getLogger(GestorVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
}
