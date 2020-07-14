

<%@page import="dao.DivicionDAO"%>
<%@page import="modelos.Divicion"%>
<%@page import="dao.CiudadDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Ciudad"%>
<%@page import="dao.EquipoDAO"%>
<%@page import="modelos.Equipo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica Equipo</title>
    </head>
    <body>
        <center>
        <h1>Confirme datos a modificar</h1>
        <% Equipo eq = new Equipo(); 
          if(request.getParameter("codigo")!= null){
              EquipoDAO ed = new EquipoDAO();
             if(ed.obtenerEquipo(Integer.parseInt(request.getParameter("codigo")))!=null){
                 eq = ed.obtenerEquipo(Integer.parseInt(request.getParameter("codigo")));
             }
          }
        %>
        <form action="ControladorEquipo" method="post">
            <table>
            <tr>
               <td>Codigo</td>
               <td><input type="text" name="codigo" readonly="true" value="<%= eq.getCodigo() %>"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre" value="<%= eq.getNombre() %>"/></td>
            </tr>
            <tr>
                <td>Jugadores</td>
                <td><input type="number" name="jugador" value="<%= eq.getJugador() %>"/></td>           
            </tr>
            <tr>
                <td>Ciudad</td>
                <td><select name="cuidad">
                            <option value="0">Seleccione</option>
                            <% ArrayList<Ciudad> ciudad = new CiudadDAO().obtenerCiudades();
                            for(Ciudad c:ciudad){%>
                            <option value="<%= c.getCodigo()%>"
                                    <%if(c.getCodigo()== eq.getCiudad().getCodigo()){ out.print("selected='selected'");}%>
                                  ><%= c %></option>
                                                               
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
                            <option value="<%= d.getCodigo()%>"
                                    <%if(d.getCodigo()== eq.getDivicion().getCodigo()){ out.print("selected='selected'");}%>
                                  ><%= d %></option>
                                                              
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
