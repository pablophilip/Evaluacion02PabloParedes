<%-- 
    Document   : modificaJugador
    Created on : 14-07-2020, 11:36:09
    Author     : pparedes
--%>

<%@page import="dao.PosicionDAO"%>
<%@page import="modelos.Posicion"%>
<%@page import="dao.EquipoDAO"%>
<%@page import="modelos.Equipo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.JugadorDAO"%>
<%@page import="modelos.Jugador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica Jugador</title>
    </head>
    <body>
        <center>
        <h1>Confirme datos a modificar</h1>
        <% Jugador j = new Jugador(); 
          if(request.getParameter("id")!= null){
              JugadorDAO jd = new JugadorDAO();
             if(jd.obtenerJugador(Integer.parseInt(request.getParameter("id")))!=null){
                 j = jd.obtenerJugador(Integer.parseInt(request.getParameter("id")));
             }
          }
        %>
        <form action="ControladorJugador" method="post">
            <table>
            <tr>
               <td>Id</td>
               <td><input type="text" name="id" readonly="true" value="<%= j.getId() %>"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre" value="<%= j.getNombre() %>"/></td>
            </tr>
            <tr>
                <td>Apellido</td>
                <td><input type="text" name="apellido" value="<%= j.getApellido() %>"/></td>           
            </tr>
            <tr>
                <td>Fecha Nacimiento</td>
                <td><input type="date" name="fechanacimiento" value="<%= j.getFechanacimiento() %>"/></td>           
            </tr>
            <tr>
                <td>Sueldo</td>
                <td><input type="number" name="sueldo" value="<%= j.getSueldo() %>"/></td>           
            </tr>
            <tr>
                <td>Equipo</td>
                <td><select name="equipo">
                            <option value="0">Seleccione</option>
                            <% ArrayList<Equipo> equipo = new EquipoDAO().obtenerEquipos();
                            for(Equipo eq:equipo){%>
                            <option value="<%= eq.getCodigo()%>"
                                    <%if(eq.getCodigo()== j.getEquipo().getCodigo()){ out.print("selected='selected'");}%>
                                  ><%= eq %></option>
                                                               
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
                            <option value="<%= p.getCodigo()%>"
                                    <%if(p.getCodigo()== j.getPosicion().getCodigo()){ out.print("selected='selected'");}%>
                                  ><%= p %></option>
                                                              
                            <% } %>
                    </select>
                </td>           
            </tr>
            <tr>
                <td><input type="submit" value="Modificar"/></td>
                <input type="hidden" name="accion" value="2"/>
                </td>
            </tr>
            
        </table>
        </form>
        </center>
    </body>
</html>
