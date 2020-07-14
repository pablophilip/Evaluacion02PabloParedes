<%-- 
    Document   : eliminarDivicion
    Created on : 12-07-2020, 11:19:01
    Author     : pparedes
--%>

<%@page import="dao.DivicionDAO"%>
<%@page import="modelos.Divicion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Divicion</title>
    </head>
    <body>
    <center>
        <h1>Confirme los datos a eliminar</h1>
        <% Divicion d = new Divicion(); 
          if(request.getParameter("codigo")!= null){
              DivicionDAO dd = new DivicionDAO();
             if(dd.obtenerDivicion(Integer.parseInt(request.getParameter("codigo")))!=null){
                 d = dd.obtenerDivicion(Integer.parseInt(request.getParameter("codigo")));
             }
          }
        %>
        <form action="ControladorDivicion" method="post">
            <table>
            <tr>
               <td>Codigo</td>
                <td><input type="number" name="codigo" readonly="true" value="<%= d.getCodigo() %>"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre" readonly="true" value="<%= d.getNombre() %>"/></td>
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
