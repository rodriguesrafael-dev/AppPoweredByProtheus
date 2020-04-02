package br.grupolider.model.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigues rafael
 */
public class DBAccess {

    private static final String HOST = "jdbc:oracle:thin:@10.15.2.61:1521:PROTHEUS";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String USER = "power_bi";
    private static final String PASS = "RR!1BR34kk4bl3!!";
 
    //establish connection DBMS
    public static Connection getConnection() {

          try {  
              try {
                  Class.forName(DRIVER);
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
              }
            return DriverManager.getConnection(HOST, USER, PASS);
                   
        } catch (SQLException ex) {
            throw new RuntimeException("Connection failed: ", ex);
        }    
        
    }

}
