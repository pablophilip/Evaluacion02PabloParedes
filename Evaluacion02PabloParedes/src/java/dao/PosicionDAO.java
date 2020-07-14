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
import modelos.Posicion;

/**
 *
 * @author pparedes
 */
public class PosicionDAO extends Conexion{
    
    public int registrarPosicion(Posicion p) throws SQLException{
      try{
           String sentencia = "insert into posicion values (?,?)";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, p.getCodigo());
           ps.setString(2, p.getNombre());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public int modificarPosicion(Posicion p) throws SQLException{
      try{
           String sentencia = "update posicion set nombre=? where codigo=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setString(1, p.getNombre());
           ps.setInt(2, p.getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public int eliminarPosicion(Posicion p) throws SQLException{
      try{
           String sentencia = "delete from posicion where codigo=?";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setInt(1, p.getCodigo());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public ArrayList<Posicion> obtenerPosiciones() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from posicion";
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Posicion> lista = new ArrayList();
        while(rs.next()){
            lista.add(new Posicion(rs.getInt("codigo"),rs.getString("nombre")));
        }
        desconectar();
        return lista;
    }
    
    public Posicion obtenerPosicion(int codigo) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from posicion where codigo = ?";
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        Posicion p = null;
        if(rs.next()){
           p = new Posicion(rs.getInt("codigo"),rs.getString("nombre"));
        }
        desconectar();
        return p;
    }
}
