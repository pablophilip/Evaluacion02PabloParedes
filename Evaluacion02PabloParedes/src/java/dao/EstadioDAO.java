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
import modelos.Ciudad;
import modelos.Estadio;

/**
 *
 * @author pparedes
 */
public class EstadioDAO extends Conexion{
    
    public int registrarEstadio(Estadio es) throws SQLException{
      try{
           String sentencia = "insert into estadio values (?,?,?,?)";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, es.getCodigo());
           ps.setString(2, es.getNombre());
           ps.setInt(3, es.getCapacidad());
           ps.setInt(4, es.getCiudad().getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public int modificarEstadio(Estadio es) throws SQLException{
        try{
           String sentencia = "update estadio set nombre=?, capacidad=?, id_ciudad=? where codigo=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setString(1, es.getNombre());
           ps.setInt(2, es.getCapacidad());
           ps.setInt(3, es.getCiudad().getCodigo());
           ps.setInt(4, es.getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public int eliminarEstadio(Estadio es) throws SQLException{
        try{
           String sentencia = "delete from estadio where codigo=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setLong(1, es.getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public ArrayList<Estadio> obtenerEstadios() throws SQLException{
        try{
            String sentencia = "select * from v_estadios";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Estadio> estadios = new ArrayList();
            while(rs.next()){
                Ciudad c = new Ciudad(rs.getInt("c_codigo"),rs.getString("c_nombre"));
                estadios.add(new Estadio(rs.getInt("codigo"),rs.getString("nombre"), rs.getInt("capacidad"), c));
            }
            return estadios;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public Estadio obtenerEstadio(int codigo) throws SQLException{
        try{
            String sentencia = "select * from v_estadios where codigo = ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            Estadio es = null;
            if(rs.next()){
                Ciudad c = new Ciudad(rs.getInt("c_codigo"),rs.getString("c_nombre"));
                es = new Estadio(rs.getInt("codigo"),rs.getString("nombre"), rs.getInt("capacidad"), c);
            }
            return es;
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
    
}
