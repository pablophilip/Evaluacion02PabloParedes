<%-- 
    Document   : registroJugador
    Created on : 14-07-2020, 11:00:06
    Author     : pparedes
--%>

<%@page import="modelos.Jugador"%>
<%@page import="dao.JugadorDAO"%>
<%@page import="dao.PosicionDAO"%>
<%@page import="modelos.Posicion"%>
<%@page import="dao.EquipoDAO"%>
<%@page import="modelos.Equipo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Jugadores</title>
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
        <form action="ControladorJugador" method="post">
            <table>
            <tr>
               <td>Id</td>
                <td><input type="number" name="id"/></td>
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
                <td>Fecha nacimiento</td>
                <td><input type="date" name="fechanacimiento"/></td>
            </tr>
            <tr>
                <td>Sueldo</td>
                <td><input type="number" name="sueldo"/></td>
            </tr>
            <tr>
                <td>Equipo</td>
                <td><select name="equipo">
                            <option value="0">Seleccione</option>
                            <% ArrayList<Equipo> equipo = new EquipoDAO().obtenerEquipos();
                            for(Equipo eq:equipo){%>
                            <option value="<%= eq.getCodigo() %>"><%= eq %></option>
                            <% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Posicion</td>
                <td><select name="posicion">
                            <option value="0">Seleccione</option>
                            <% ArrayList<Posicion> posicion = new PosicionDAO().obtenerPosiciones();
                            for(Posicion p:posicion){%>
                            <option value="<%= p.getCodigo() %>"><%= p %></option>
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
        <h3>Jugadores Registrados</h3>
        <table border="1" style="border-collapse: collapse;">
            <tr>
                <td>Id</td>
                <td>Nombre</td>
                <td>Fecha Nacimiento</td>
                <td>Sueldo</td>
                <td>Equipo</td>
                <td>Posicion</td>
                <td>Modificar</td>
                <td>Eliminar</td>
            </tr>
            <%  JugadorDAO jd = new JugadorDAO();
                ArrayList<Jugador> jugador = jd.obtenerJugadores(); 
            for(Jugador j:jugador){
            %>
            <tr>
                <td><%= j.getId() %></td>
                <td><%= j.getNombre() %></td>
                <td><%= j.getApellido() %></td>
                <td><%= j.getFechanacimiento() %></td>
                <td><%= j.getSueldo() %></td>
                <td><%= j.getEquipo() %></td>
                <td><%= j.getPosicion() %></td>
                <td><a href="modificaJugador.jsp?id=<%= j.getId() %>">
                        <input type="button" value="Modificar"/>
                    </a>
                </td>
                <td><a href="eliminarJugador.jsp?id=<%= j.getId() %>">
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
