<%-- 
    Document   : index
    Created on : 29-abr-2017, 12:09:44
    Author     : Informal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestor Vehículos</title>
    <style type="text/css">
        form {
            margin: 100px auto auto;
            width: 300px;
            padding: 1em;
            border: 45px ridge #CCC;
            border-radius: 1em;
        }
        form div + div {
            margin-top: 1em;
        }
        label {
            display: inline-block;
            width: 90px;
            text-align: right;
            margin-right: 55%;
            color: darkslategrey;
            font-weight: bold;
            line-height: 150%;
            text-shadow: white 1px -1px;
            font-size: 20px;
        }        
        .boton {
            padding-left: 90px;
        }
        .boton input{
            background-color:darkslategrey;
            color: white;
        }
        h1{
            margin: 4% 28%;
            font-size: 50px; 
            color:darkslategrey;
            text-decoration: underline;
        }
        body{
            background-color: cadetblue;
            font-family: sans-serif;
        }
    </style>
</head>
<body>
    <h1>Bienvenido al Gestor de Vehículos</h1>
    <form action="./IndexServlet" method="post">
        <div>
            <label>Consultar/Eliminar Modelo:</label>
            <input type="radio" id="consulta" name="opcion" value="consultar"/>
        </div>
        <div>
            <label>Agregar Modelo:</label>
            <input type="radio" id="agrega" name="opcion" value="eliminar" />
        </div>
        <div class="boton">
            <input type="submit" value="ENVIAR">
        </div>
    </form>
</body>
</html>

<!-- ------------------------------------------

<a href="index" >Menu Principal</a>
request.setAtributte("Poblaciones",poblaciones);


En el JSP:
ArrayList<Pobacions> poblaciones = (ArrayList)request.getAttribute("Poblaciones");

----->