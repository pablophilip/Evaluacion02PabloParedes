<%-- 
    Document   : intranet
    Created on : 11-07-2020, 20:48:34
    Author     : pparedes
--%>

<%@page import="modelos.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Intranet</title>
    </head>
    <body>
        <center>
        <h1>Bienvenido
            <% if(session.getAttribute("usuario")!= null){
             Usuario u = (Usuario) session.getAttribute("usuario");
            %>
        <h3><%= u.getNombre()+" "+u.getApellido() %></h3>
        <%}else{response.sendRedirect("index.jsp?msj=acceso denegado");}%>
        </h1>
        <a href="Salir"><input type="button" value="Cerrar Sesion"/></a>
        <h1>Bienvenido que desea registar</h1>
        <menu >
            <a href="registroUsuario.jsp">
            <menuitem >Usuarios</menuitem>
            </a> |
            <a href="registroDivicion.jsp">
            <menuitem >Diviciones</menuitem>
            </a> | 
            <a href="registroCiudad.jsp">
            <menuitem >Cuidades</menuitem>
            </a> |
            <a href="registroEstadio.jsp">
            <menuitem >Estadios</menuitem>
            </a> |
            <a href="registroPosicion.jsp">
            <menuitem >Posiciones</menuitem>
            </a> |
            <a href="registroEquipo.jsp">
            <menuitem >Equipos</menuitem>
            </a> |
            <a href="registroJugador.jsp">
            <menuitem >Jugadores</menuitem>
            </a>
        </menu>
        
      </center>
    </body>
</html>
