<%-- 
    Document   : registroUsuario
    Created on : 11-07-2020, 20:10:56
    Author     : pparedes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Usuarios</title>
    </head>
    <body>
        <center>
        <h1>Registro de Usuarios</h1>
        <menu >
            <a href="registroUsuario.jsp">
            <menuitem >Usuarios</menuitem>
            </a> |
            <a href="registroDivicion.jsp">
            <menuitem >Divicion</menuitem>
            </a> | 
            <a href="registroCiudad.jsp">
            <menuitem >Cuidad</menuitem>
            </a> |
            <a href="registroEstadio.jsp">
            <menuitem >Estadio</menuitem>
            </a> |
            <a href="registroPosicion.jsp">
            <menuitem >Posicion</menuitem>
            </a>
        </menu>
        <form action="ControladorUsuario" method="post">
            <table>
            <tr>
               <td>ID</td>
                <td><input type="text" name="id"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre"/></td>
            </tr>
            <tr>
                <td>Apellido</td>
                <td><input type="text" name="apellido"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Registrar"/></td>
                <td><a href="inicio.jsp">
                    <input type="button" value="Volver"/>
                    </a>
                </td>
            </tr>
            
        </table>
            <input type="hidden" name="accion" value="2"/>
        </form>
        <% if(request.getParameter("msj")!= null){%>
        <h3><%= request.getParameter("msj") %></h3>
        <%}%>
    </center>
    </body>
</html>
