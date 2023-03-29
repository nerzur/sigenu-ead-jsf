/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asiel
 */
public class connection {

    private static Connection con;

    public static Connection getConnectionInstance(String driver, String url_database, String user, String password) {
        return (con == null) ? databaseConnection(driver, url_database, user, password) : con;
    }

    /**
     * Estos son los datos que yo introduje en mi caso, ten en cuenta que el
     * driver es el mismo para tí:
     *
     * @param driver : "org.postgresql.Driver"
     * @param url_database : "jdbc:postgresql://localhost:5432/Proyecto1" (Esta url
     * está en services, clic derecho sobre la conexión creada, propiedades y
     * copias lo que está en database url)
     * @param user : "postgres"
     * @param password : "start"
     */
    private static Connection databaseConnection(String driver, String url_database, String user, String password) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url_database, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
