<%-- 
    Document   : modificaCiudad
    Created on : 12-07-2020, 12:14:40
    Author     : pparedes
--%>

<%@page import="dao.CiudadDAO"%>
<%@page import="modelos.Ciudad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica Ciudad</title>
    </head>
    <body>
        <center>
        <h1>Confirme datos a modificar</h1>
        <% Ciudad c = new Ciudad(); 
          if(request.getParameter("codigo")!= null){
              CiudadDAO cd = new CiudadDAO();
             if(cd.obtenerCiudad(Integer.parseInt(request.getParameter("codigo")))!=null){
                 c = cd.obtenerCiudad(Integer.parseInt(request.getParameter("codigo")));
             }
          }
        %>
        <form action="ControladorCiudad" method="post">
            <table>
            <tr>
               <td>Codigo</td>
                <td><input type="number" name="codigo" readonly="true" value="<%= c.getCodigo() %>"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre" value="<%= c.getNombre() %>"/></td>
            </tr>
            <tr>
                <td><input type="reset" value="Limpiar"/></td>
                <td><input type="submit" value="Modificar"/></td>
            </tr>
            
        </table>
            <input type="hidden" name="accion" value="2"/>
        </form>
    </center>
    </body>
</html>
