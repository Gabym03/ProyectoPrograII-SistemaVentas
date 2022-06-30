/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Controlador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RODRIGUEZ
 */
public class ProductoDAO {
    conexion cn=new conexion();
    connection con;
    PreparedStatement ps;
    ResultSet rs; 
    int r;

    public List listar() throws SQLException {
        String sql="select * fram producto";
        List<Producto>lista=new ArrayList<>();
        try {
            con=cn.conexion();
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
        return null;
    }

    public Producto listarId(int id) {
        Producto pr=new Producto();
        String sql="select * from producto where IdProducto="+id;
        try {
            con=cn.conexion();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
