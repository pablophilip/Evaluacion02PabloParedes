<%-- 
    Document   : registroEstadio
    Created on : 12-07-2020, 14:21:41
    Author     : pparedes
--%>

<%@page import="modelos.Estadio"%>
<%@page import="dao.EstadioDAO"%>
<%@page import="dao.CiudadDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Ciudad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Estadios</title>
    </head>
    <body>
        <center>
        <h1>Registro de Estadio</h1>
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
        <form action="ControladorEstadio" method="post">
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
                <td>Capacidad</td>
                <td><input type="number" name="capacidad"/></td>
            </tr>
            <tr>
                <td>Ciudad</td>
                <td><select name="ciudad">
                            <option value="0">Seleccione</option>
                            <% ArrayList<Ciudad> ciudad = new CiudadDAO().obtenerCiudades();
                            for(Ciudad c:ciudad){%>
                            <option value="<%= c.getCodigo() %>"><%= c %></option>
                            <% } %>
                    </select>
                </td>
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
        <h3>Estadios Registrados</h3>
        <table border="1" style="border-collapse: collapse;">
            <tr>
                <td>Codigo</td>
                <td>Nombre</td>
                <td>Capacidad</td>
                <td>Ciudad</td>
                <td>Modificar</td>
                <td>Eliminar</td>
            </tr>
            <%  EstadioDAO ed = new EstadioDAO();
                ArrayList<Estadio> estadio = ed.obtenerEstadios(); 
            for(Estadio es:estadio){
            %>
            <tr>
                <td><%= es.getCodigo() %></td>
                <td><%= es.getNombre() %></td>
                <td><%= es.getCapacidad() %></td>
                <td><%= es.getCiudad() %></td>
                <td><a href="modificaEstadio.jsp?codigo=<%= es.getCodigo() %>">
                        <input type="button" value="Modificar"/>
                    </a>
                </td>
                <td><a href="eliminarEstadio.jsp?codigo=<%= es.getCodigo() %>">
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
