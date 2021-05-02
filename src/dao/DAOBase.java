/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed Sayed
 */
public class DAOBase implements DAO {

    private ConnectionSettings connSettings;

    public ConnectionSettings getConnSettings() {
        return connSettings;
    }

    public void setConnSettings(ConnectionSettings connSettings) {
        this.connSettings = connSettings;
    }

    @Override
    public Connection getConnection() throws DAOException {
        try {
            System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            return DriverManager.getConnection(getConnSettings().getUrl(), getConnSettings().getUser(), getConnSettings().getPassword());
        } catch (SQLException ex) {
            StackTraceElement[] trace = ex.getStackTrace();
            DAOException daoEx = new DAOException();
            daoEx.setStackTrace(trace);
            throw daoEx;
        }
    }

}
