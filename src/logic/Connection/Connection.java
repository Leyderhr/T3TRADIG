package logic.Connection;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    java.sql.Connection connection = null;
    String bd = "DB.db";
    String a = System.getProperty("user.dir");
    String cadena = "jdbc:sqlite:"+ a +"/DB.db";
    String dbPath = "jdbc:sqlite:" + new File("DB.db").getAbsolutePath();


    public java.sql.Connection connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(cadena);
            System.out.println("Conectado...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public void desconect() {


        try {
            if (connection != null) {
                connection.close();
                System.out.println("Se desconecto...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
