package Modelo;

import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Cliente buscar(String dni) {
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM cliente WHERE Dni=" + dni;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                cliente.setIdCliente(rs.getInt(1));
                cliente.setDni(rs.getString(2));
                cliente.setNombres(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return cliente;
    }

    public List listar() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList<>();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt(1));
                cliente.setDni(rs.getString(2));
                cliente.setNombres(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setEstado(rs.getString(5));

                lista.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public int agregar(Cliente cliente) {
        String sql = "INSERT INTO cliente(Dni, Nombres, Direccion, Estado) VALUES (?,?,?,?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getEstado());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET Dni=?, Nombres=?, Direccion=?, Estado=? WHERE idCliente=?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getEstado());
            ps.setInt(5, cliente.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public Cliente listarId(int id) {
        String sql = "SELECT * FROM cliente WHERE idCliente=" + id;
        Cliente cliente = null;

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt(1));
                cliente.setDni(rs.getString(2));
                cliente.setNombres(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setEstado(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public int delete(int id) {
        String sql = "DELETE FROM cliente WHERE idCliente=" + id;

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
