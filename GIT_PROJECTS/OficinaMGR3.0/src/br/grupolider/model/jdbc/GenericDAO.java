package br.grupolider.model.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigues raffael
 */
public class GenericDAO {

    protected Statement stmt = null;
    protected Connection connection = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;

    //establish connection DBMS
    protected GenericDAO() {
        try {
            this.connection = DBAccess.getConnection();

        } catch (Exception ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //start connection
    protected Connection getConnection() {
        return connection;
    }

}
