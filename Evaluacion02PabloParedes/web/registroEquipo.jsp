<%-- 
    Document   : registroEquipo
    Created on : 13-07-2020, 13:53:23
    Author     : pparedes
--%>

<%@page import="modelos.Equipo"%>
<%@page import="dao.EquipoDAO"%>
<%@page import="dao.DivicionDAO"%>
<%@page import="modelos.Divicion"%>
<%@page import="dao.CiudadDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Ciudad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resgistro de Equipos</title>
    </head>
    <body>
        <center>
        <h1>Registro de Equipo</h1>
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
        <form action="ControladorEquipo" method="post">
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
                <td>Jugadores</td>
                <td><input type="number" name="jugador"/></td>
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
                <td>Divicion</td>
                <td><select name="divicion">
                            <option value="0">Seleccione</option>
                            <% ArrayList<Divicion> divicion = new DivicionDAO().obtenerDiviones();
                            for(Divicion d:divicion){%>
                            <option value="<%= d.getCodigo() %>"><%= d %></option>
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
                <td>Jugadores</td>
                <td>Ciudad</td>
                <td>Divicion</td>
                <td>Modificar</td>
                <td>Eliminar</td>
            </tr>
            <%  EquipoDAO ed = new EquipoDAO();
                ArrayList<Equipo> equipo = ed.obtenerEquipos(); 
            for(Equipo eq:equipo){
            %>
            <tr>
                <td><%= eq.getCodigo() %></td>
                <td><%= eq.getNombre() %></td>
                <td><%= eq.getJugador() %></td>
                <td><%= eq.getCiudad() %></td>
                <td><%= eq.getDivicion() %></td>
                <td><a href="modificaEquipo.jsp?codigo=<%= eq.getCodigo() %>">
                        <input type="button" value="Modificar"/>
                    </a>
                </td>
                <td><a href="eliminarEquipo.jsp?codigo=<%= eq.getCodigo() %>">
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
