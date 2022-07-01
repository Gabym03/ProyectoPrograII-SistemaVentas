/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gabri
 */
public class ProductoDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public List listar()  {
        String sql="select * fram producto";
        List<Producto>lista=new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                Producto em=new Producto();
                em.setId(rs.getInt(1));
                em.setNom(rs.getString(2));
                em.setPre(rs.getDouble(3));
                em.setStock(rs.getInt(4));
                em.setEstado(rs.getString(5));
                lista.add(em);
                
            }
        }catch (Exception e){
            
        }
        return lista;
    }
    
    public int agregar(Producto p) {
        String sql="insert into producto (Nombres, Precio, Stock, Estado)values(?,?,?,?)";
        try{
           con=cn.Conexion();
           ps=con.prepareStatement(sql);
           ps.setString(1, p.getNom());
           ps.setDouble(2, p.getPre());
           ps.setInt(3, p.getStock());
           ps.setString(4, p.getEstado());
            int executeUpdate = ps.executeUpdate();
        }catch (Exception e){
            
        }
    return r;    
    }
      

    public Producto listarId(int id) {
        Producto pr=new Producto();
        String sql="select * from producto where IdProducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getNString(2));
                pr.setPre(rs.getDouble(3));
                pr.setStock(rs.getInt(4));
                pr.setEstado(rs.getNString(5));
            } 
        }catch (Exception e) {
            
        }
        return pr;
    }
    
    public int actualizar(Producto em) throws SQLException {
  
        String sql="update producto set Nombres=?, Precio=?, Stock=?, Estado=? where IdProducto=?";
        try {
           con=cn.Conexion();
           ps=con.prepareStatement(sql);
           ps.setString(1, em.getNom());
           ps.setDouble(2, em.getPre());
           ps.setInt(3, em.getStock());
           ps.setString(4, em.getEstado());
           ps.setInt(5, em.getId());
            int executeUpdate = ps.executeUpdate();
            

        }catch (Exception e) {
            
        }
        return r;
    }
    public void delete(int id){
      String sql="delete from producto where IdProducto="+id;
      try {
         con=cn.Conexion();
         ps=con.prepareStatement(sql);
         ps.executeUpdate();
      } catch (Exception e){
      }
   }

    private static class connection {

        public connection() {
        }

        private PreparedStatement prepareStatement(String sql) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class conexion {

        public conexion() {
        }

      
       
    }
}
