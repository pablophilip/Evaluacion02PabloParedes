/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.CiudadDAO;
import dao.EstadioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Estadio;

/**
 *
 * @author pparedes
 */
@WebServlet(name = "ControladorEstadio", urlPatterns = {"/ControladorEstadio"})
public class ControladorEstadio extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("accion")!=null){
        String accion = request.getParameter("accion");
            switch(accion){
                case "1": registrar(request,response);
                    break;
                case "2": modificar(request,response);
                    break;
                case "3": eliminar(request,response);
                   break;
            }
        }else{
            response.sendRedirect("inicio.jsp?=No tienes permitido hacer eso");
        }
    }
    
    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();
            int capacidad = Integer.parseInt(request.getParameter("capacidad").trim());
            int ciudad = Integer.parseInt(request.getParameter("ciudad").trim());
            if(codigo<1||nombre.isEmpty()||capacidad<1||ciudad<1){
            response.sendRedirect("registroEstadio.jsp?msj=valores erroneos");
            }else{
                CiudadDAO cd = new CiudadDAO();
                Estadio nuevoEstadio = new Estadio(codigo,nombre,capacidad,cd.obtenerCiudad(ciudad));
                EstadioDAO ed = new EstadioDAO();
                if(ed.obtenerEstadio(nuevoEstadio.getCodigo())==null){
                    int respuesta = ed.registrarEstadio(nuevoEstadio);
                    if(respuesta==1){
                        response.sendRedirect("registroEstadio.jsp?msj=Estadio ingresado");
                    }else{
                        response.sendRedirect("registroEstadio.jsp?msj=Estadio no se puede registar");
                    }
                }else{
                    response.sendRedirect("registroEstadio.jsp?msj=Estadio ya existe");
                }
            }
        }catch(Exception e){
            response.sendRedirect("registroEstadio.jps="+e.getMessage());
        }
    }
    
    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();
            int capacidad = Integer.parseInt(request.getParameter("capacidad").trim());
            int ciudad = Integer.parseInt(request.getParameter("ciudad").trim());
            if(codigo<1||nombre.isEmpty()||capacidad<1||ciudad<1){
            response.sendRedirect("registroEstadio.jsp?msj=valores erroneos");
            }else{
                CiudadDAO cd = new CiudadDAO();
                Estadio nuevoEstadio = new Estadio(codigo,nombre,capacidad,cd.obtenerCiudad(ciudad));
                EstadioDAO ed = new EstadioDAO();
                if(ed.obtenerEstadio(nuevoEstadio.getCodigo())==null){
                    response.sendRedirect("registroEstadio.jsp?msj=Codigo inexistente");   
                }else{
                    int respuesta = ed.modificarEstadio(nuevoEstadio);
                    if(respuesta==1){
                        response.sendRedirect("registroEstadio.jsp?msj=Estadio modificado"); 
                    }else{
                        response.sendRedirect("registroEstadio.jsp?msj=Estadio no se pudo modificar"); 
                    }
                }
            }
        }catch(Exception e){
            response.sendRedirect("registroEstadio.jps="+e.getMessage());
        }
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            if(codigo<1){
                response.sendRedirect("registroEstadio.jsp?msj=Campos incompletos");
            }else{
                EstadioDAO ed = new EstadioDAO();
                Estadio nuevoEstadio = new Estadio();
                nuevoEstadio.setCodigo(codigo);
                int respuesta = ed.eliminarEstadio(nuevoEstadio);
                if(respuesta ==1){
                    response.sendRedirect("registroEstadio.jsp?msj=Estadio eliminado");
                }
            }
        }catch(Exception e){
            response.sendRedirect("registroEstadio.jps="+e.getMessage());
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
