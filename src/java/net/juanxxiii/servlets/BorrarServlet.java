package net.juanxxiii.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.juanxxiii.modelo.GestorVehiculos;


public class BorrarServlet
        extends HttpServlet {

    public BorrarServlet() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        request.setAttribute("mensaje",null);
        try {
            GestorVehiculos gv = new GestorVehiculos();
            int id=Integer.parseInt(request.getParameter("id"));
            gv.eliminarVehiculo(id);
            
            request.setAttribute("mensaje", "MODELO ELIMINADO CON ÉXITO");
            String nextJSP = "/consultarModelo.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
            
        } catch (ClassNotFoundException ex) {
            request.setAttribute("mensaje", "NO SE HA PODIDO ELIMINAR EL MODELO INTÉNTELO DE NUEVO");
        } catch (SQLException ex) {
            request.setAttribute("mensaje", "NO SE HA PODIDO ELIMINAR EL MODELO INTÉNTELO DE NUEVO");
        }
                
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
