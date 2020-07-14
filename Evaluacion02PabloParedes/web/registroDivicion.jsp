<%-- 
    Document   : registroDivicion
    Created on : 11-07-2020, 21:41:12
    Author     : pparedes
--%>

<%@page import="modelos.Divicion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DivicionDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Diviciones</title>
    </head>
    <body>
        <center>
        <h1>Registro de Diviciones</h1>
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
        <form action="ControladorDivicion" method="post">
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
        <h3>Diviciones Registradas</h3>
        <table border="1" style="border-collapse: collapse;">
            <tr>
                <td>Codigo</td>
                <td>Nombre</td>
                <td>Modificar</td>
                <td>Eliminar</td>
            </tr>
            <%  DivicionDAO dd = new DivicionDAO();
                ArrayList<Divicion> divicion = dd.obtenerDiviones(); 
            for(Divicion d:divicion){
            %>
            <tr>
                <td><%= d.getCodigo() %></td>
                <td><%= d.getNombre() %></td>
                <td><a href="modificaDivicion.jsp?codigo=<%= d.getCodigo() %>">
                        <input type="button" value="Modificar"/>
                    </a>
                </td>
                <td><a href="eliminarDivicion.jsp?codigo=<%= d.getCodigo() %>">
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
