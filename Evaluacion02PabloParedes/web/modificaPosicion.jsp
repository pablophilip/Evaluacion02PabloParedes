<%-- 
    Document   : modificaPosicion
    Created on : 13-07-2020, 11:49:27
    Author     : pparedes
--%>

<%@page import="dao.PosicionDAO"%>
<%@page import="modelos.Posicion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica Posicion</title>
    </head>
    <body>
        <center>
        <h1>Confirme datos a modificar</h1>
        <% Posicion p = new Posicion(); 
          if(request.getParameter("codigo")!= null){
              PosicionDAO pd = new PosicionDAO();
             if(pd.obtenerPosicion(Integer.parseInt(request.getParameter("codigo")))!=null){
                 p = pd.obtenerPosicion(Integer.parseInt(request.getParameter("codigo")));
             }
          }
        %>
        <form action="ControladorPosicion" method="post">
            <table>
            <tr>
               <td>Codigo</td>
                <td><input type="number" name="codigo" readonly="true" value="<%= p.getCodigo() %>"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre" value="<%= p.getNombre() %>"/></td>
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
