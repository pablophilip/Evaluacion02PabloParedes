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

/**
 *
 * @author pparedes
 */
public class CiudadDAO extends Conexion{
    
    public int registrarCuidad(Ciudad c) throws SQLException{
      try{
           String sentencia = "insert into ciudad values (?,?)";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, c.getCodigo());
           ps.setString(2, c.getNombre());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public int modificarCiudad(Ciudad c) throws SQLException{
      try{
           String sentencia = "update ciudad set nombre=? where codigo=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setString(1, c.getNombre());
           ps.setInt(2, c.getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public int eliminarCiudad(Ciudad c) throws SQLException{
      try{
           String sentencia = "delete from ciudad where codigo=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, c.getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public ArrayList<Ciudad> obtenerCiudades() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from ciudad";
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Ciudad> lista = new ArrayList();
        while(rs.next()){
            lista.add(new Ciudad(rs.getInt("codigo"),rs.getString("nombre")));
        }
        desconectar();
        return lista;
    }
    
    public Ciudad obtenerCiudad(int codigo) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from ciudad where codigo = ?";
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        Ciudad c = null;
        if(rs.next()){
           c = new Ciudad(rs.getInt("codigo"),rs.getString("nombre"));
        }
        desconectar();
        return c;
    }
    
    public Ciudad obtenerCiudad(String nombre) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from ciudad where nombre = ?";
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        Ciudad c = null;
        if(rs.next()){
           c = new Ciudad(rs.getInt("codigo"),rs.getString("nombre"));
        }
        desconectar();
        return c;
    }
}
