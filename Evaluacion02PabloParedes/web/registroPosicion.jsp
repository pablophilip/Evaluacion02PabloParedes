<%-- 
    Document   : registroPosicion
    Created on : 13-07-2020, 11:21:23
    Author     : pparedes
--%>

<%@page import="modelos.Posicion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.PosicionDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Posicion</title>
    </head>
    <body>
        <center>
        <h1>Registro de Posiciones</h1>
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
        <form action="ControladorPosicion" method="post">
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
        <h3>Posiciones Registradas</h3>
        <table border="1" style="border-collapse: collapse;">
            <tr>
                <td>Codigo</td>
                <td>Nombre</td>
                <td>Modificar</td>
                <td>Eliminar</td>
            </tr>
            <%  PosicionDAO pd = new PosicionDAO();
                ArrayList<Posicion> posicion = pd.obtenerPosiciones(); 
            for(Posicion p:posicion){
            %>
            <tr>
                <td><%= p.getCodigo() %></td>
                <td><%= p.getNombre() %></td>
                <td><a href="modificaPosicion.jsp?codigo=<%= p.getCodigo() %>">
                        <input type="button" value="Modificar"/>
                    </a>
                </td>
                <td><a href="eliminarPosicion.jsp?codigo=<%= p.getCodigo() %>">
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
