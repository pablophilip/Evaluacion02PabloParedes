<%-- 
    Document   : modificaEstadio
    Created on : 12-07-2020, 15:49:56
    Author     : pparedes
--%>

<%@page import="dao.CiudadDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Ciudad"%>
<%@page import="dao.EstadioDAO"%>
<%@page import="modelos.Estadio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica Estadio</title>
    </head>
    <body>
         <center>
        <h1>Confirme datos a modificar</h1>
        <% Estadio es = new Estadio(); 
          if(request.getParameter("codigo")!= null){
              EstadioDAO ed = new EstadioDAO();
             if(ed.obtenerEstadio(Integer.parseInt(request.getParameter("codigo")))!=null){
                 es = ed.obtenerEstadio(Integer.parseInt(request.getParameter("codigo")));
             }
          }
        %>
        <form action="ControladorEstadio" method="post">
            <table>
            <tr>
               <td>Codigo</td>
               <td><input type="text" name="codigo" readonly="true" value="<%= es.getCodigo() %>"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre" value="<%= es.getNombre() %>"/></td>
            </tr>
            <tr>
                <td>Capacidad</td>
                <td><input type="number" name="capacidad" value="<%= es.getCapacidad() %>"/></td>           
            </tr>
            <tr>
                <td>Ciudad</td>
                <td><select name="ciudad">
                            <option value="0">Seleccione</option>
                            <% ArrayList<Ciudad> ciudad = new CiudadDAO().obtenerCiudades();
                            for(Ciudad c:ciudad){%>
                            <option value="<%= c.getCodigo()%>"
                                    <%if(c.getCodigo()== es.getCiudad().getCodigo()){ out.print("selected='selected'");}%>
                                  ><%= c %></option>
                                                               
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
