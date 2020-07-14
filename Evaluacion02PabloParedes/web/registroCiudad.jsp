<%-- 
    Document   : registroCiudad
    Created on : 12-07-2020, 12:01:03
    Author     : pparedes
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Ciudad"%>
<%@page import="dao.CiudadDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Ciudades</title>
    </head>
    <body>
        <center>
        <h1>Registro de Ciudad</h1>
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
        <form action="ControladorCiudad" method="post">
            <table>
            <tr>
               <td>Codigo</td>
                <td><input type="number" name="codigo"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre"/></td>
            </tr>
            <tr>
                <td><input type="reset" value="Limpiar"/></td>
                <td><input type="submit" value="Guardar"/></td>
            </tr>
            
        </table>
            <input type="hidden" name="accion" value="1"/>
        </form>
        <% if(request.getParameter("msj")!= null){%>
        <h3><%= request.getParameter("msj") %></h3>
        <%}%>
        <h3>Ciudades Registradas</h3>
        <table border="1" style="border-collapse: collapse;">
            <tr>
                <td>Codigo</td>
                <td>Nombre</td>
                <td>Modificar</td>
                <td>Eliminar</td>
            </tr>
            <%  CiudadDAO cd = new CiudadDAO();
                ArrayList<Ciudad> ciudad = cd.obtenerCiudades(); 
            for(Ciudad c:ciudad){
            %>
            <tr>
                <td><%= c.getCodigo() %></td>
                <td><%= c.getNombre() %></td>
                <td><a href="modificaCiudad.jsp?codigo=<%= c.getCodigo() %>">
                        <input type="button" value="Modificar"/>
                    </a>
                </td>
                <td><a href="eliminarCiudad.jsp?codigo=<%= c.getCodigo() %>">
                        <input type="button" value="Eliminar"/>
                    </a></td>
            </tr>
            <% } %>
        </table>
        <% if(request.getParameter("msj")!= null){%>
        <h4><%= request.getParameter("msj") %></h4>
        <%}%>
    </center>
    </body>
</html>
