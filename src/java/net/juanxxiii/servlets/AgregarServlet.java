package net.juanxxiii.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.juanxxiii.modelo.GestorVehiculos;

public class AgregarServlet
        extends HttpServlet {

    public AgregarServlet() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("confirmacion", null);
        request.setAttribute("idMarca", null);
        try {

            String marca = request.getParameter("Marca");
            String modelo = request.getParameter("Modelo");
            String consumo = request.getParameter("Consumo");
            String emisiones = request.getParameter("Emisiones");
            String eficiencia = request.getParameter("Eficiencia");

            if (marca.equalsIgnoreCase("Desconocida")) {
                String nextJSP = "/agregarModelo.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);

                request.setAttribute("idMarca", "DEBE SELECCIONAR UNA MARCA");
                dispatcher.forward(request, response);
            } else {

                GestorVehiculos gv = new GestorVehiculos();
                gv.addVehiculo(modelo, marca, consumo, emisiones, eficiencia);
                //misession.setAttribute("confirmacion", "Agregado Correctamente");
                request.setAttribute("confirmacion", "Agregado Correctamente");
                String nextJSP = "/agregarModelo.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);

                dispatcher.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgregarServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("confirmacion", "Ha ocurrido un problema al intentar agregar");
        } catch (ClassNotFoundException ex) {
            request.setAttribute("confirmacion", "Ha ocurrido un problema al intentar agregar");
            Logger.getLogger(AgregarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
