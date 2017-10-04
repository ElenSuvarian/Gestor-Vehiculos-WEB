package net.juanxxiii.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet
  extends HttpServlet
{
  public IndexServlet() {}
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    if(request.getParameter("opcion").equalsIgnoreCase("consultar")){
        String nextJSP = "/consultarModelo.jsp";
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    }else{
      String nextJSP = "/agregarModelo.jsp";
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    }

  }
  

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }
  


  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }
  





  public String getServletInfo()
  {
    return "Short description";
  }
}