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
import modelos.Divicion;

/**
 *
 * @author pparedes
 */
public class DivicionDAO extends Conexion{
    
    public int registrarDivicion(Divicion d) throws SQLException{
      try{
           String sentencia = "insert into divicion values (?,?)";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, d.getCodigo());
           ps.setString(2, d.getNombre());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public int modificarDivicion(Divicion d) throws SQLException{
      try{
           String sentencia = "update divicion set nombre=? where codigo=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setString(1, d.getNombre());
           ps.setInt(2, d.getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }   
    
    public int eliminarDivicion(Divicion d) throws SQLException{
      try{
           String sentencia = "delete from divicion where codigo=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, d.getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public ArrayList<Divicion> obtenerDiviones() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from divicion";
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Divicion> lista = new ArrayList();
        while(rs.next()){
            lista.add(new Divicion(rs.getInt("codigo"),rs.getString("nombre")));
        }
        desconectar();
        return lista;
    }
    
    public Divicion obtenerDivicion(int codigo) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from divicion where codigo = ?";
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        Divicion d = null;
        if(rs.next()){
           d = new Divicion(rs.getInt("codigo"),rs.getString("nombre"));
        }
        desconectar();
        return d;
    }
    
    public Divicion obtenerDivicion(String nombre) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from divicion where nombre = ?";
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        Divicion d = null;
        if(rs.next()){
           d = new Divicion(rs.getInt("codigo"),rs.getString("nombre"));
        }
        desconectar();
        return d;
    }
}
