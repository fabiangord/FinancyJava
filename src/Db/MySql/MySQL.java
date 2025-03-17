package Db.MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    static String url = "jdbc:mysql://localhost:3306/JavaFinancy";
    static String name = "root";
    static String password = "";

    public static Connection connect(){
        Connection con = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, name, password);
            System.out.println("Coneccion exitosa");
        }catch(SQLException e){
            System.out.println("Error de coneccion" + e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("ERROR: MySql Driver not fount");
            e.printStackTrace();
        }

        return con;
    }


    public static void Desconection(Connection connect){
        if(connect != null){
            try {
                connect.close();
                System.out.println("Coneccion close");
            } catch (Exception e) {
                System.out.println("ERROR Closing Connection" + e.getMessage()); 
            }
        }

    }
}
