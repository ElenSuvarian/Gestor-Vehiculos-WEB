


<%@page import="net.juanxxiii.modelo.GestorVehiculos"%>
<%@page import="net.juanxxiii.persistencia.GestorBBDDVehiculos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.juanxxiii.modelo.Marca"%>
<%@page import="net.juanxxiii.modelo.Marca"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Modelo</title>
        <style type="text/css">
            form {
                margin: 0 auto;
                width: 450px;
                padding: 1em;
                border: 1px solid #CCC;
                border-radius: 1em;
                background-color: black;
            }
            form div + div {
                margin-top: 1em;
            }
            label {
                display: inline-block;
                width: 90px;
                text-align: right;
                color: white;
                font-weight: bolder;
            }        
            input{
                font: 1em sans-serif;
                width: 300px;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
                border: 1px solid #999;
            }     
            input:focus{
                border-color: #000;
            }      
            .boton{
                padding-left: 90px;
            }
            .boton input{
                color:white;
                background-color: crimson;
            }
            input,select {
                margin-left: .5em;
            }
            h1{
                margin: 2% 30%;
                text-shadow: white 2px -2px;
            }
            body{
                font-family: sans-serif;
                background-color: firebrick;
            }
        </style>
    </head>
    <body>
        <h1>Introduzca los datos del modelo del Vehículo</h1>
        <br>
        <br>
        <br>
        <%
            if (request.getAttribute("idMarca") != null) {
        %>
        <p>DEBE SELECCIONAR UNA MARCA</p><%
            }%>
        <form action="./AgregarServlet" method="post">
            <div>
                <label>Modelo:</label>
                <input type="text" id="modelo" name="Modelo" required="required"/>
            </div>
            <div>
                <label>Marca:</label>
                <select name="Marca">
                    <option selected="selected" value="Desconocida" >Seleccionar Marca</option>
                    <%  GestorVehiculos gv = new GestorVehiculos();
                        ArrayList<Marca> marcas = new ArrayList();
                        marcas = gv.getMarcas();

                        for (Marca marca : marcas) {
                    %><option><%= marca.getNombre()%></option><%
                        }
                    %>
                </select>
            </div>
            <div>
                <label>Consumo:</label>
                <input name="Consumo" type="number" step="any" id="Consumo" required="required">
            </div>
            <div>
                <label>Emisiones:</label>
                <input name="Emisiones" type="number" step="any" id="Emisiones" required="required">
            </div>
            <div>
                <label>Eficiencia:</label>
                <select name="Eficiencia">
                    <option selected="selected" value="CLASIFICACION ENERGETICA NA" >Seleccionar Eficiencia</option>
                    <%  ArrayList<String> eficiencias = new ArrayList();
                        eficiencias = gv.getEficiencias();

                        for (String eficiencia : eficiencias) {
                    %><option><%= eficiencia%></option><%
                        }
                    %>
                </select>
            </div>

            <div class="boton">
                <input type="submit" value="AGREGAR"/>
            </div>
        </form>
        <% if (request.getAttribute(
                    "confirmacion") != null) {
        %><p><%=request.getAttribute("confirmacion")%></p>
        <%}%>

        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        <a href="index.jsp">Volver al Menú Principal</>
    </body>
</html>
