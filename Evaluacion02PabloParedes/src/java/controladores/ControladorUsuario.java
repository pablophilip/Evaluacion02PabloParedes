/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuario;

/**
 *
 * @author pparedes
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("accion")!=null){
        String accion = request.getParameter("accion");
        switch(accion){
            case "1": iniciarSesion(request,response);
                break;
            case "2": registrar(request,response);
                break;
           }
        }
    }
    
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        Usuario usuarioIniciando = new Usuario(id,password);
        UsuarioDAO ud = new UsuarioDAO();
        Usuario temporal= ud.obtenerUsuario(usuarioIniciando.getId());
        if(temporal!=null){
            if(temporal.getPassword().equals(usuarioIniciando.getPassword())){
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", temporal);
            response.sendRedirect("intranet.jsp");
            }else{
                response.sendRedirect("inicio.jsp?msj=Password incorrecto");
            }
        }else{
            response.sendRedirect("inicio.jsp?msj=id inexistente");
        }
        }catch(Exception e){
            response.sendRedirect("inicio.jsp?msj="+e.getMessage());
        }
    }
     private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
       try{
            String id = request.getParameter("id").trim();
            String nombre = request.getParameter("nombre").trim();
            String apellido = request.getParameter("apellido").trim();
            String password = request.getParameter("password").trim();
            if(id.isEmpty()||nombre.isEmpty()||apellido.isEmpty()||password.isEmpty()){
                response.sendRedirect("registroUsuario.jsp?msj=Campos incompletos");
            }else{
                Usuario usuarioNuevo = new Usuario(id,nombre,apellido,password);
                UsuarioDAO ud = new UsuarioDAO();
                HttpSession sesion = request.getSession();
                if(ud.obtenerUsuario(usuarioNuevo.getId())==null){
                    int respuesta = ud.registrarUsuario(usuarioNuevo);
                    if(respuesta==1){
                    response.sendRedirect((sesion.getAttribute("usuario")!=null)?"intranet.jsp?msj=Usuario registrado"
                            :"inicio.jsp?msj=Usuario registrado, inicie sesion");
                    }else{
                    response.sendRedirect((sesion.getAttribute("usuario")!=null)?"intranet.jsp?msj=Usuario no se puede registar"
                            :"inicio.jsp?msj=Usuario no se puede registrar");
                    }

                }else{
                    response.sendRedirect((sesion.getAttribute("usuario")!=null)?"intranet.jsp?msj=Usuario ya existe":
                            "inicio.jsp?msj=Usuario ya existe");
                }
            }
           }catch(Exception e){
               response.sendRedirect("inicio.jsp?msj="+e.getMessage());
           }
    }
     

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
