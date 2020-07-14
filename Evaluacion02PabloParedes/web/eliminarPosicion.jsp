

<%@page import="dao.PosicionDAO"%>
<%@page import="modelos.Posicion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Posicion</title>
    </head>
    <body>
        <center>
        <h1>Confirme los datos a eliminar</h1>
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
                <td><input type="text" name="nombre" readonly="true" value="<%= p.getNombre() %>"/></td>
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
