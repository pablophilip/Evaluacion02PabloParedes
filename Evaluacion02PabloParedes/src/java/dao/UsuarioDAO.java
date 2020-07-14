
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Usuario;


public class UsuarioDAO extends Conexion{
    
    public int registrarUsuario(Usuario u) throws SQLException{
      try{
           String sentencia = "insert into usuario values (?,?,?,?)";
           conectar();
           PreparedStatement ps= obtenerPS(sentencia);
           ps.setString(1, u.getId());
           ps.setString(2, u.getNombre());
           ps.setString(3, u.getApellido());
           ps.setString(4, u.getPassword());
           return ps.executeUpdate();
        }catch(Exception e){
          return -1;
        }finally{
          desconectar();
        }
    }
    
    public ArrayList<Usuario> obtenerUsuarios() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from usuario";
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Usuario> lista = new ArrayList();
        while(rs.next()){
            lista.add(new Usuario(rs.getString("id"),rs.getString("nombre"),
                    rs.getString("apellido"),rs.getString("password")));
        }
        desconectar();
        return lista;
    }
    
    public Usuario obtenerUsuario(String id) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from usuario where id = ?";
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Usuario u = null;
        if(rs.next()){
           u = new Usuario(rs.getString("id"),rs.getString("nombre"), rs.getString("apellido"),rs.getString("password"));
        }
        desconectar();
        return u;
    }
}
