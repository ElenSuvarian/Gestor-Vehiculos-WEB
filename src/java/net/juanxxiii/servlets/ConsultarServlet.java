package net.juanxxiii.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.juanxxiii.modelo.GestorVehiculos;
import net.juanxxiii.modelo.Modelo;

public class ConsultarServlet
        extends HttpServlet {

    public ConsultarServlet() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GestorVehiculos gv = new GestorVehiculos();
        ArrayList<Modelo> modelos = new ArrayList();
        int cantidadRegistros = 0;
        int numPaginas = 1;

        request.setAttribute("seleccion", null);
        request.setAttribute("paginas", null);

        try {
            String marca = request.getParameter("Marca");
            int idMarca = gv.getIDMarca(marca);
            float consumo = Float.parseFloat(request.getParameter("Consumo")) / 100;
            String[] opciones = request.getParameterValues("opcion");

            if (opciones != null) {
                if (opciones.length == 2) {
                    modelos = gv.getModelos(idMarca, consumo, numPaginas);
                    cantidadRegistros = gv.getNumModelos(idMarca, consumo);
                } else if (opciones.length < 2) {
                    if (opciones[0].equalsIgnoreCase("marca")) {
                        modelos = gv.getModelos(idMarca, numPaginas);
                        cantidadRegistros = gv.getNumModelos(idMarca);
                    } else {
                        modelos = gv.getModelos(consumo, numPaginas);
                        cantidadRegistros = gv.getNumModelos(consumo);
                    }
                }
            } else {
                String nextJSP = "/consultarModelo.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);

                request.setAttribute("seleccion", "Seleccionar");
                dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            request.setAttribute("mensaje", "SE HA PRODUCIDO UN PROBLEMA AL CARGAR LOS DATOS, INTÉNTELO DE NUEVO");
        } catch (SQLException ex) {
            request.setAttribute("mensaje", ex.getErrorCode());

        }
        request.setAttribute("modelos", modelos);
        if (cantidadRegistros >= 100) {
            request.setAttribute("paginas", cantidadRegistros);
        }
        String nextJSP = "/consultarModelo.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);

        dispatcher.forward(request, response);
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
