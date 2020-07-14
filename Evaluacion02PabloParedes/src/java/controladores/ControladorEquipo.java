/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.CiudadDAO;
import dao.DivicionDAO;
import dao.EquipoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Equipo;

/**
 *
 * @author pparedes
 */
@WebServlet(name = "ControladorEquipo", urlPatterns = {"/ControladorEquipo"})
public class ControladorEquipo extends HttpServlet {

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
            int jugador = Integer.parseInt(request.getParameter("jugador").trim());
            int ciudad = Integer.parseInt(request.getParameter("ciudad").trim());
            int divicion = Integer.parseInt(request.getParameter("divicion").trim());
            if(codigo<1||nombre.isEmpty()||jugador<1||ciudad<1||divicion>1){
            response.sendRedirect("registroEquipo.jsp?msj=valores erroneos");
            }else{
                CiudadDAO cd = new CiudadDAO();
                DivicionDAO dd = new DivicionDAO();
                Equipo nuevoEquipo = new Equipo(codigo,nombre,jugador,cd.obtenerCiudad(ciudad),
                                                dd.obtenerDivicion(divicion));
                EquipoDAO ed = new EquipoDAO();
                if(ed.obtenerEquipo(nuevoEquipo.getCodigo())==null){
                    int respuesta = ed.registrarEquipo(nuevoEquipo);
                    if(respuesta==1){
                        response.sendRedirect("registroEquipo.jsp?msj=Equipo ingresado");
                    }else{
                        response.sendRedirect("registroEquipo.jsp?msj=Equipo no se puede registar");
                    }
                }else{
                    response.sendRedirect("registroEquipo.jsp?msj=Equipo ya existe");
                }
            }
        }catch(Exception e){
            response.sendRedirect("registroEquipo.jps="+e.getMessage());
        }
    }
    
    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();
            int jugador = Integer.parseInt(request.getParameter("jugador").trim());
            int ciudad = Integer.parseInt(request.getParameter("ciudad").trim());
            int divicion = Integer.parseInt(request.getParameter("divicion").trim());
            if(codigo<1||nombre.isEmpty()||jugador<1||ciudad<1||divicion>1){
            response.sendRedirect("registroEquipo.jsp?msj=valores erroneos");
            }else{
                CiudadDAO cd = new CiudadDAO();
                DivicionDAO dd = new DivicionDAO();
                Equipo nuevoEquipo = new Equipo(codigo,nombre,jugador,cd.obtenerCiudad(ciudad),
                                                dd.obtenerDivicion(divicion));
                EquipoDAO ed = new EquipoDAO();
                if(ed.obtenerEquipo(nuevoEquipo.getCodigo())==null){
                    response.sendRedirect("registroEquipo.jsp?msj=Codigo inexistente");   
                }else{
                    int respuesta = ed.modificarEquipo(nuevoEquipo);
                    if(respuesta>0){
                        response.sendRedirect("registroEquipo.jsp?msj=Equipo modificado"); 
                    }else{
                        response.sendRedirect("registroEquipo.jsp?msj=Equipo no se pudo modificar"); 
                    }
                }
            }
        }catch(Exception e){
            response.sendRedirect("registroEquipo.jps="+e.getMessage());
        }
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            if(codigo<1){
                response.sendRedirect("registroEquipo.jsp?msj=Campos incompletos");
            }else{
                EquipoDAO ed = new EquipoDAO();
                Equipo nuevoEquipo = new Equipo();
                nuevoEquipo.setCodigo(codigo);
                int respuesta = ed.eliminarEquipo(nuevoEquipo);
                if(respuesta ==1){
                    response.sendRedirect("registroEquipo.jsp?msj=Equipo eliminado");
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
