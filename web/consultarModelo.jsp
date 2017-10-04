<%-- 
    Document   : consultarModelo
    Created on : 03-may-2017, 16:55:58
    Author     : dam
--%>

<%@page import="net.juanxxiii.modelo.Modelo"%>
<%@page import="net.juanxxiii.modelo.GestorVehiculos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.juanxxiii.modelo.Marca"%>
<%@page import="net.juanxxiii.persistencia.GestorBBDDVehiculos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar Modelo</title>
        <style type="text/css">
            form {
                margin: 0 auto;
                width: 600px;
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
                margin-left: -25%;
            }        
            input{
                font: 1em sans-serif;
                width: 300px;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
                border: 1px solid #999;
                margin-left: -25%;
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
            h1{
                margin: 2% 30%;
                text-shadow: white 2px -2px;
            }
            body{
                font-family: sans-serif;
                background-color: firebrick;
            }
            #range{
                margin-left: 3%;
            }
            #textInput{
                width: 15%;
                margin-left: 6%;
            }
            #buscar{
                margin-left: 23%;
                color:white;
                background-color: crimson;
            }
            table{
                color: white;
                margin-left: 10%;
                border-color: black;
                border-width: 10px;
            }
            img{
                width: 100px;
            }
            p{
                text-shadow: white 1px -1px;
                font-weight: bold;
                font-size:  30px;
            }
        </style>
        <script type="text/javascript">
            function updateTextInput(val) {
                document.getElementById('textInput').value = val + " ml/km";
            }
        </script>    
    </head>
    <body>
        <h1>Seleccione los datos por los que quiere buscar</h1>
        </br>
        </br>
        </br>
        <% if (request.getAttribute("mensaje") != null) {%>
        <div><%=request.getAttribute("mensaje")%></div><%}%>
        </br>
        </br>
        </br>
        <form action="./ConsultarServlet" method="post">
            <div>
                <input type="checkbox" name="opcion" value="marca" checked="checked">
                <label>Marca:</label>
                <select name="Marca">                  
                    <option selected="selected" value="Desconocida">Seleccionar Marca</option>                    
                    <%
                        GestorVehiculos gv = new GestorVehiculos();
                        ArrayList<Marca> marcas = new ArrayList();
                        marcas = gv.getMarcas();

                        for (Marca marca : marcas) {
                    %><option><%= marca.getNombre()%></option><%
                        }
                    %>
                </select>             
            </div>
            <div>
                <input type="checkbox" name="opcion" value="consumo" checked="checked">
                <label>Consumo:</label>
                <%float maxConsumo = gv.getConsumoMax();%>
                <input name="Consumo" type="range" id="range" min="0" max="<%=maxConsumo * 100%>" onchange="updateTextInput(this.value);">
                <input type="text" disabled="disabled" id="textInput" value="">
            </div>
            <div>
                <input type="submit" value="Buscar" id="buscar">         
            </div>
        </form>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        <%if (request.getAttribute("seleccion") != null) {%>
        <p>DEBE SELECCIONAR AL MENOS UNA OPCIÓN PARA BUSCAR</p><%}%>
        </br>   
        <%if (request.getAttribute("paginas") != null) {
        %>
        <p>PRIMEROS 100 REGISTROS, AFINA MEJOR LA BÚSQUEDA</p><%}%>
        </br>
        </br>

        <%if (request.getAttribute("modelos") != null) {
                 ArrayList<Modelo> modelos = (ArrayList<Modelo>) request.getAttribute("modelos");
                 if (modelos.size() > 0) { %>
        <table border="all" >     
            <tr>
                <th>MARCA</th>
                <th>MODELO</th>
                <th>CONSUMO</th>
                <th>EMISIONES</th>
                <th>EFICIENCIA</th>
                <th>ELIMINAR</th>
            </tr>
            <% for (Modelo modelo : modelos) {%>
            <tr>
                <td><img src="/VehiculosVersionWeb/imagenes/iconos/<%=modelo.getIdMarca()%>.gif"/></td>
                <td><%=modelo.getNombre()%></td>
                <td><%=modelo.getConsumo()%></td>
                <td><%=modelo.getEmisiones()%></td>
                <td><img src="/VehiculosVersionWeb/imagenes/eficiencias/<%=modelo.getLogoEficiencia()%>"/></td>
                <td><a href="BorrarServlet?id=<%=modelo.getId()%>">Eliminar</a></td>
            </tr>
            <%}
            } else {%> 
            <p>NO EXISTEN MODELOS CON LOS DATOS SELECCIONADOS</P>
                <%}%><%
                    }%>
        </table>
        <a href="index.jsp">Volver al Menú Principal</>
    </body>
</html>
