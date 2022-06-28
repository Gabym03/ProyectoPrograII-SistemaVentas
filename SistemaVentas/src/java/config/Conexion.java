package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    Connection con;
    String url="jdbc:mysql://127.0.0.1:3306/bd_ventas";
    String user="root";
    String pass="1234";
    public Connection Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            
        }
        return con;
    }
}

