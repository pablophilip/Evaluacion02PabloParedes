/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.EquipoDAO;
import dao.JugadorDAO;
import dao.PosicionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Jugador;

/**
 *
 * @author pparedes
 */
@WebServlet(name = "ControladorJugador", urlPatterns = {"/ControladorJugador"})
public class ControladorJugador extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("id").trim());
            String nombre = request.getParameter("nombre").trim();
            String apellido = request.getParameter("apellido").trim();
            String fechanacimiento = request.getParameter("fechanacimiento").trim();
            int sueldo = Integer.parseInt(request.getParameter("sueldo").trim());
            int equipo = Integer.parseInt(request.getParameter("equipo").trim());
            int posicion = Integer.parseInt(request.getParameter("posicion").trim());
            if(id<1||nombre.isEmpty()||apellido.isEmpty()||fechanacimiento.isEmpty()||sueldo<1||equipo<1||posicion>1){
            response.sendRedirect("registroJugador.jsp?msj=valores erroneos");
            }else{
                EquipoDAO ed = new EquipoDAO();
                PosicionDAO pd = new PosicionDAO();
                Jugador nuevoJugador = new Jugador(id,nombre,apellido,fechanacimiento,sueldo,
                                                   ed.obtenerEquipo(equipo),pd.obtenerPosicion(posicion));
                JugadorDAO jd = new JugadorDAO();
                if(jd.obtenerJugador(nuevoJugador.getId())==null){
                    int respuesta = jd.registrarJugador(nuevoJugador);
                    if(respuesta==1){
                        response.sendRedirect("registroJugador.jsp?msj=Jugador ingresado");
                    }else{
                        response.sendRedirect("registroJugador.jsp?msj=Jugador no se puede registar");
                    }
                }else{
                    response.sendRedirect("registroJugador.jsp?msj=Jugador ya existe");
                }
            }
        }catch(Exception e){
            response.sendRedirect("registroJugador.jps="+e.getMessage());
        }
    }
    
    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            int id = Integer.parseInt(request.getParameter("id").trim());
            String nombre = request.getParameter("nombre").trim();
            String apellido = request.getParameter("apellido").trim();
            String fechanacimiento = request.getParameter("fechanacimiento").trim();
            int sueldo = Integer.parseInt(request.getParameter("sueldo").trim());
            int equipo = Integer.parseInt(request.getParameter("equipo").trim());
            int posicion = Integer.parseInt(request.getParameter("posicion").trim());
            if(id<1||nombre.isEmpty()||apellido.isEmpty()||fechanacimiento.isEmpty()||sueldo<1||equipo<1||posicion>1){
            response.sendRedirect("registroJugador.jsp?msj=valores erroneos");
            }else{
                EquipoDAO ed = new EquipoDAO();
                PosicionDAO pd = new PosicionDAO();
                Jugador nuevoJugador = new Jugador(id,nombre,apellido,fechanacimiento,sueldo,
                                                   ed.obtenerEquipo(equipo),pd.obtenerPosicion(posicion));
                JugadorDAO jd = new JugadorDAO();
                if(jd.obtenerJugador(nuevoJugador.getId())==null){
                    response.sendRedirect("registroJugador.jsp?msj=Codigo inexistente");   
                }else{
                    int respuesta = jd.modificarJugador(nuevoJugador);
                    if(respuesta>0){
                        response.sendRedirect("registroJugador.jsp?msj=Jugador modificado"); 
                    }else{
                        response.sendRedirect("registroJugador.jsp?msj=Jugador no se pudo modificar"); 
                    }
                }
            }
        }catch(Exception e){
            response.sendRedirect("registroJugador.jps="+e.getMessage());
        }
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            int id = Integer.parseInt(request.getParameter("id").trim());
            if(id<1){
                response.sendRedirect("registroEquipo.jsp?msj=Campos incompletos");
            }else{
                JugadorDAO jd = new JugadorDAO();
                Jugador nuevoJugador = new Jugador();
                nuevoJugador.setId(id);
                int respuesta = jd.eliminarJugador(nuevoJugador);
                if(respuesta ==1){
                    response.sendRedirect("registroEquipo.jsp?msj=Jugador eliminado");
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
