/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DivicionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Divicion;

/**
 *
 * @author pparedes
 */
@WebServlet(name = "ControladorDivicion", urlPatterns = {"/ControladorDivicion"})
public class ControladorDivicion extends HttpServlet {

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
            if(codigo<1||nombre.isEmpty()){
            response.sendRedirect("registroDivicion.jsp?msj=valores erroneos");
            }else{
                DivicionDAO dd = new DivicionDAO();
                Divicion nuevaDivicion = new Divicion(codigo,nombre);
                if(dd.obtenerDivicion(nuevaDivicion.getCodigo())==null){
                int respuesta = dd.registrarDivicion(nuevaDivicion);
                if(respuesta==1){
                    response.sendRedirect("registroDivicion.jsp?msj=Divicion ingresada");
                        
                }else{
                    response.sendRedirect("registroDivicion.jsp?msj=Divicion no se puede registar");
                }
            }else{
                response.sendRedirect("registroDivicion.jsp?msj=Divicion ya existe");
            }
            }
        }catch(Exception e){
            response.sendRedirect("registroDivicion.jps="+e.getMessage());
        }
    }
    
    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();
            if(codigo<1||nombre.isEmpty()){
            response.sendRedirect("registroDivicion.jsp?msj=valores erroneos");
            }else{
                DivicionDAO dd = new DivicionDAO();
                Divicion nuevaDivicion = new Divicion(codigo,nombre);
                if(dd.obtenerDivicion(nuevaDivicion.getCodigo())==null){
               response.sendRedirect("registroDivicion.jsp?msj=Codigo inexistente");                  
            }else{
              int respuesta = dd.modificarDivicion(nuevaDivicion);
              if(respuesta>0){
                response.sendRedirect("registroDivicion.jsp?msj=Divicion modificada"); 
              }else{
                  response.sendRedirect("registroDivicion.jsp?msj=Divicion no se pudo modificar");
              }
            }
            }
        }catch(Exception e){
           response.sendRedirect("registroDivicion.jps="+e.getMessage());
       }
    }
     
     private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            if(codigo<1){
                response.sendRedirect("registroDivicion.jsp?msj=Campos incompletos");
            }else{
                DivicionDAO dd = new DivicionDAO();
                Divicion nuevaDivicion = new Divicion();
                nuevaDivicion.setCodigo(codigo);
                int respuesta = dd.eliminarDivicion(nuevaDivicion);
                if(respuesta ==1){
                    response.sendRedirect("registroDivicion.jsp?msj=Divicion eliminada");
                }
            }
        }catch(Exception e){
            response.sendRedirect("registroDivicion.jps="+e.getMessage());
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
