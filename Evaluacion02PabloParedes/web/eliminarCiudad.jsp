<%-- 
    Document   : eliminarCiudad
    Created on : 12-07-2020, 12:18:36
    Author     : pparedes
--%>

<%@page import="dao.CiudadDAO"%>
<%@page import="modelos.Ciudad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Ciudad</title>
    </head>
    <body>
        <center>
        <h1>Confirme los datos a eliminar</h1>
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
                <td><input type="text" name="nombre" readonly="true" value="<%= c.getNombre() %>"/></td>
            </tr>
            <tr>
                <td><input type="reset" value="Limpiar"/></td>
                <td><input type="submit" value="Eliminar"/></td>
            </tr>
            
        </table>
            <input type="hidden" name="accion" value="3"/>
        </form>
    </center>
    </body>
</html>
