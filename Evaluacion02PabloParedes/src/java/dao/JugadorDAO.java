/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Equipo;
import modelos.Jugador;
import modelos.Posicion;

/**
 *
 * @author pparedes
 */
public class JugadorDAO extends Conexion{
    
    public int registrarJugador(Jugador j) throws SQLException{
      try{
           String sentencia = "insert into jugador values (?,?,?,?,?,?)";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, j.getId());
           ps.setString(2, j.getNombre());
           ps.setString(3, j.getApellido());
           ps.setString(4, j.getFechanacimiento());
           ps.setInt(4, j.getSueldo());
           ps.setInt(5, j.getEquipo().getCodigo());
           ps.setInt(6, j.getPosicion().getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
     
    public int modificarJugador(Jugador j) throws SQLException{
      try{
           String sentencia = "update jugador set nombre=?, set apellido=?, set fechanacimiento=?, "
                               + "set sueldo=?, set id_equipo=?, set id_posicion=? where id=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, j.getId());
           ps.setString(2, j.getNombre());
           ps.setString(3, j.getApellido());
           ps.setString(4, j.getFechanacimiento());
           ps.setInt(4, j.getSueldo());
           ps.setInt(5, j.getEquipo().getCodigo());
           ps.setInt(6, j.getPosicion().getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public int eliminarJugador(Jugador j) throws SQLException{
        try{
           String sentencia = "delete from jugador where id=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, j.getId());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public ArrayList<Jugador> obtenerJugadores() throws SQLException{
        try{
            String sentencia = "select * from v_jugador";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Jugador> jugadores = new ArrayList();
            while(rs.next()){
                Equipo eq = new Equipo(rs.getInt("e_codigo"),rs.getString("e_nombre"));
                Posicion p = new Posicion(rs.getInt("p_codigo"),rs.getString("p_nombre"));
                jugadores.add(new Jugador(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido"),
                              rs.getString("fechanacimiento"),rs.getInt("sueldo"),eq,p));
            }
            return jugadores;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public Jugador obtenerJugador(int id) throws SQLException{
        try{
            String sentencia = "select * from v_jugador where id = ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Jugador j = null;
            if(rs.next()){
                Equipo eq = new Equipo(rs.getInt("e_codigo"),rs.getString("e_nombre"));
                Posicion p = new Posicion(rs.getInt("p_codigo"),rs.getString("p_nombre"));
                j = new Jugador(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido"),
                              rs.getString("fechanacimiento"),rs.getInt("sueldo"),eq,p);
            }
            return j;
        }catch(Exception e){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public boolean existeEquipo(Equipo equipo) throws SQLException{
        try{
            String sentencia = "select * from v_jugador where e_codigo = ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, equipo.getCodigo());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch(Exception e){
            return false;
        }finally{
            desconectar();
        }
    }
    
    public boolean existePosicion(Posicion posicion) throws SQLException{
        try{
            String sentencia = "select * from v_jugador where p_codigo = ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, posicion.getCodigo());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch(Exception e){
            return false;
        }finally{
            desconectar();
        }
    }
    
}
