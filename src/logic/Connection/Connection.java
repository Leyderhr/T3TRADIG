package logic.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    java.sql.Connection connection = null;
    String bd = "DB.db";
    String cadena = "jdbc:sqlite:src/Util/DB/" + bd;


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
