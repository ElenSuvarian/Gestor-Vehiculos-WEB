package net.juanxxiii.persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.juanxxiii.modelo.Marca;
import net.juanxxiii.modelo.Modelo;

public class GestorBBDDVehiculos
        extends GestorBBDD {

    private static final String TABLA_MODELOS = "modelos";
    private static final String TABLA_MARCAS = "marcas";
    private static final String TABLA_EFICIENCIAS = "eficiencias";

    public GestorBBDDVehiculos(String usuario, String password, String ip, String nombreBBDD) {
        super(usuario, password, ip, nombreBBDD);
    }

    public GestorBBDDVehiculos(String usuario, String password, String ip, String nombreBBDD, int puerto) {
        super(usuario, password, ip, nombreBBDD, puerto);
    }

    public void insertarModelo(Modelo modelo1) throws SQLException, ClassNotFoundException {        
        establecerConexion();
        String sql = "INSERT INTO modelos (ID_MARCA,MODELO,CONSUMO,EMISIONES,C_ENERGETICA) VALUES ('" + modelo1.getIdMarca() + "','" + modelo1.getNombre() + "'," + modelo1.getConsumo() + "," + modelo1.getEmisiones() + ",'" + modelo1.getIdEficiencia() + "')";
        Statement st = conexion.createStatement();
        st.executeUpdate(sql);
        st.close();
        cerrarConexion();
    }

    public void borrarModelo(int id) throws SQLException, ClassNotFoundException {
        establecerConexion();
        String sql = "DELETE FROM modelos WHERE id=" + id;
        Statement st = conexion.createStatement();
        st.executeUpdate(sql);
        st.close();
        cerrarConexion();
    }

    public ArrayList<Marca> getMarcas() throws SQLException, ClassNotFoundException {
        establecerConexion();
        ArrayList<Marca> marcas = new ArrayList();
        String sql = "SELECT MARCA FROM marcas";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next() == true) {
            Marca m = new Marca(rs.getString("MARCA"));
            marcas.add(m);
        }

        rs.close();
        cerrarConexion();
        return marcas;
    }

    public ArrayList<String> getEficiencia() throws SQLException, ClassNotFoundException {
        establecerConexion();
        ArrayList<String> eficiencias = new ArrayList();
        String sql = "SELECT DESCRIPCION FROM eficiencias";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next() == true) {
            String idEfi = rs.getString("DESCRIPCION");
            eficiencias.add(idEfi);
        }
        cerrarConexion();
        return eficiencias;
    }

    public int getIDMarca(String marca) throws SQLException, ClassNotFoundException {
        establecerConexion();
        int idMarca = 0;
        String sql = "SELECT ID FROM marcas where MARCA like ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, "%" + marca + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next() == true) {
            idMarca = rs.getInt("ID");
        }
        cerrarConexion();
        return idMarca;
    }

    public String getIDEficiencia(String eficiencia) throws SQLException, ClassNotFoundException {
        establecerConexion();
        String idEficiencia = null;
        String sql = "SELECT C_ENERGETICA FROM Eficiencias where DESCRIPCION like ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, "%" + eficiencia + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next() == true) {
            idEficiencia = rs.getString("C_ENERGETICA");
        }
        cerrarConexion();
        return idEficiencia;
    }

    public String getMarca(int id) throws SQLException, ClassNotFoundException {
        establecerConexion();
        String marca = null;
        String sql = "SELECT MARCA FROM " + TABLA_MARCAS + " where id=" + id;
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next() == true) {
            marca = rs.getString("MARCA");
        }
        cerrarConexion();
        return marca;
    }

    public float getMaxConsumo() throws SQLException, ClassNotFoundException {
        establecerConexion();
        float maxConsumo = -1;
        String sql = "SELECT max(consumo) FROM " + TABLA_MODELOS;
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next() == true) {
            maxConsumo = rs.getFloat("max(consumo)");
        }
        cerrarConexion();
        return maxConsumo;
    }

    public ArrayList<Modelo> getModelos(int marca, float consumo,int numeroPagina) throws SQLException, ClassNotFoundException {
        int registroInicial = numeroPagina*10-9;
        establecerConexion();
        ArrayList<Modelo> modelos = new ArrayList();
        String sql = "select modelos.id,modelo,consumo,emisiones,marca,icono,id_marca from " + TABLA_MARCAS + "," + TABLA_MODELOS + "," + TABLA_EFICIENCIAS + " where eficiencias.C_ENERGETICA=modelos.C_ENERGETICA and ID_MARCA=marcas.ID and  marcas.id =" + marca + " and consumo<=" + consumo + " order by consumo LIMIT 100";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next() == true) {
            Modelo modelo = new Modelo(rs.getString("marca"), rs.getString("icono"), rs.getString("modelo"), Float.parseFloat(rs.getString("consumo")), Float.parseFloat(rs.getString("emisiones")), rs.getInt("MODELOS.ID_MARCA"),rs.getInt("modelos.ID"));
            modelos.add(modelo);
        }
        rs.close();
        cerrarConexion();
        return modelos;
    }

    public ArrayList<Modelo> getModelos(int marca,int numeroPagina) throws ClassNotFoundException, SQLException {
        int registroInicial = numeroPagina*10-9;
        ArrayList<Modelo> modelos = null;
        establecerConexion();
        modelos = new ArrayList();
        String sql = "select modelos.id,modelo,consumo,emisiones,marca,icono,id_marca from " + TABLA_MARCAS + "," + TABLA_MODELOS + "," + TABLA_EFICIENCIAS + " where eficiencias.C_ENERGETICA=modelos.C_ENERGETICA and ID_MARCA=marcas.ID and  marcas.id =" + marca + " order by consumo LIMIT 100";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next() == true) {
            Modelo modelo = new Modelo(rs.getString("marca"), rs.getString("icono"), rs.getString("modelo"), Float.parseFloat(rs.getString("consumo")), Float.parseFloat(rs.getString("emisiones")), rs.getInt("MODELOS.ID_MARCA"),rs.getInt("modelos.ID"));
            modelos.add(modelo);
        }
        rs.close();
        cerrarConexion();
        return modelos;
    }
    
    public ArrayList<Modelo> getModelos(float consumo,int numeroPagina) throws SQLException, ClassNotFoundException {
        int registroInicial = numeroPagina*10-9;
        establecerConexion();
        ArrayList<Modelo> modelos = new ArrayList();
        String sql = "select modelos.id,modelo,consumo,emisiones,marca,icono,id_marca from "+ TABLA_MARCAS+","+TABLA_MODELOS+","+TABLA_EFICIENCIAS+" where eficiencias.C_ENERGETICA=modelos.C_ENERGETICA and ID_MARCA=marcas.ID and  consumo<="+consumo+" order by consumo LIMIT 100 ";        
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next() == true) {
        	 Modelo modelo = new Modelo(rs.getString("marca"),rs.getString("icono"),rs.getString("modelo"),Float.parseFloat(rs.getString("consumo")),Float.parseFloat(rs.getString("emisiones")),rs.getInt("MODELOS.ID_MARCA"),rs.getInt("modelos.ID"));	
            modelos.add(modelo);            
        }
        rs.close();
        cerrarConexion();
        return modelos;
    }
    
     public int getNumModelos(int marca, float consumo) throws SQLException, ClassNotFoundException {
        establecerConexion();
        int numModelos=-1;
        String sql = "select count(*) from " + TABLA_MARCAS + "," + TABLA_MODELOS + "," + TABLA_EFICIENCIAS + " where eficiencias.C_ENERGETICA=modelos.C_ENERGETICA and ID_MARCA=marcas.ID and  marcas.id =" + marca + " and consumo<=" + consumo;
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next() == true) {
            numModelos=rs.getInt("count(*)");
        }
        rs.close();
        cerrarConexion();
        return numModelos;
    }
     public int getNumModelos(int marca) throws SQLException, ClassNotFoundException {
        establecerConexion();
        int numModelos=-1;
        String sql = "select count(*) from " + TABLA_MARCAS + "," + TABLA_MODELOS + "," + TABLA_EFICIENCIAS + " where eficiencias.C_ENERGETICA=modelos.C_ENERGETICA and ID_MARCA=marcas.ID and  marcas.id =" + marca;
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next() == true) {
            numModelos=rs.getInt("count(*)");
        }
        rs.close();
        cerrarConexion();
        return numModelos;
    }
     public int getNumModelos(float consumo) throws SQLException, ClassNotFoundException {
        establecerConexion();
        int numModelos=-1;
        String sql = "select count(*) from " + TABLA_MARCAS + "," + TABLA_MODELOS + "," + TABLA_EFICIENCIAS + " where eficiencias.C_ENERGETICA=modelos.C_ENERGETICA and consumo<=" + consumo;
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next() == true) {
            numModelos=rs.getInt("count(*)");
        }
        rs.close();
        cerrarConexion();
        return numModelos;
    }
    
    /*
    public static void main(String[] args) {
        try {

            //new Modelo (1,"C","AAAAAA",0,0);
            GestorBBDDVehiculos gbdv = new GestorBBDDVehiculos("root", "", "127.0.0.1", "bbdd_gestmotor");
            ArrayList<Modelo> modelos = gbdv.getModelos(0f,1);
            System.out.println(modelos.get(0).getNombre());
        } catch (SQLException ex) {
            Logger.getLogger(GestorBBDDVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorBBDDVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
     }
