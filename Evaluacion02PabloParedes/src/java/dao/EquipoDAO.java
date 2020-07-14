
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Ciudad;
import modelos.Divicion;
import modelos.Equipo;

public class EquipoDAO extends Conexion{
    
    public int registrarEquipo(Equipo eq) throws SQLException{
      try{
           String sentencia = "insert into equipo values (?,?,?,?,?)";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, eq.getCodigo());
           ps.setString(2, eq.getNombre());
           ps.setInt(3, eq.getJugador());
           ps.setInt(4, eq.getCiudad().getCodigo());
           ps.setInt(5, eq.getDivicion().getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public int modificarEquipo(Equipo eq) throws SQLException{
        try{
           String sentencia = "update equipo set nombre=?, jugador=?, id_ciudad=?, id_divicion=? where codigo=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setString(1, eq.getNombre());
           ps.setInt(2, eq.getJugador());
           ps.setInt(3, eq.getCiudad().getCodigo());
           ps.setInt(4, eq.getDivicion().getCodigo());
           ps.setInt(5, eq.getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public int eliminarEquipo(Equipo eq) throws SQLException{
        try{
           String sentencia = "delete from equipo where codigo=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, eq.getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public ArrayList<Equipo> obtenerEquipos() throws SQLException{
        try{
            String sentencia = "select * from v_equipos";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Equipo> equipos = new ArrayList();
            while(rs.next()){
                Ciudad c = new Ciudad(rs.getInt("c_codigo"),rs.getString("c_nombre"));
                Divicion d = new Divicion(rs.getInt("d_codigo"),rs.getString("d_nombre"));
                equipos.add(new Equipo(rs.getInt("codigo"),rs.getString("nombre"),rs.getInt("jugador"),c,d));
            }
            return equipos;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
     public Equipo obtenerEquipo(int codigo) throws SQLException{
        try{
            String sentencia = "select * from v_equipo where codigo = ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            Equipo eq = null;
            if(rs.next()){
                Ciudad c = new Ciudad(rs.getInt("c_codigo"),rs.getString("c_nombre"));
                Divicion d = new Divicion(rs.getInt("d_codigo"),rs.getString("d_nombre"));
                eq = new Equipo(rs.getInt("codigo"),rs.getString("nombre"),rs.getInt("jugador"),c,d);
            }
            return eq;
        }catch(Exception e){
            return null;
        }finally{
            desconectar();
        }
    }
     
    public Equipo obtenerEquipo(String nombre) throws SQLException{
        try{
            String sentencia = "select * from v_equipo where nombre = ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            Equipo eq = null;
            if(rs.next()){
                Ciudad c = new Ciudad(rs.getInt("c_codigo"),rs.getString("c_nombre"));
                Divicion d = new Divicion(rs.getInt("d_codigo"),rs.getString("d_nombre"));
                eq = new Equipo(rs.getInt("codigo"),rs.getString("nombre"),rs.getInt("jugador"),c,d);
            }
            return eq;
        }catch(Exception e){
            return null;
        }finally{
            desconectar();
        }
    }
     
    public boolean existeCiudad(Ciudad ciudad) throws SQLException{
        try{
            String sentencia = "select * from v_estadios where c_codigo = ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, ciudad.getCodigo());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch(Exception e){
            return false;
        }finally{
            desconectar();
        }
    }
    
    public boolean existeDivicion(Divicion divicion) throws SQLException{
        try{
            String sentencia = "select * from v_estadios where d_codigo = ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, divicion.getCodigo());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch(Exception e){
            return false;
        }finally{
            desconectar();
        }
    }
}
