/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgc.nvrservicesjws.dao;

import com.rgc.nvrservicesjws.utils.Utils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto
 */
public class Database {
    
    private static Connection conn = null;
    private static Database instance;
    private Properties properties = Utils.loadDBProperties();

    private Database() {
        //getConnection();
    }
    
    public static synchronized Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    public Connection getConnection() {
        try {
            if (conn == null) { 
                Class.forName("com.mysql.cj.jdbc.Driver");
                //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database", "username", "password");
                conn = DriverManager.getConnection("jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDatabase() + "" , getUsername(), getPassword());
                System.out.println("Connection OK");
            }
        } catch (ClassNotFoundException | SQLException ex ) {
            System.err.println(ex.getMessage());
            System.out.println("Connection FAIL");
        }
        return conn;
    }
     
    public static void destroyDb() {
        conn = null;
    }
    
    /////////////////////////////////////////////////////////////////////////////
    
    private String getDatabase() {
        return properties.getProperty("DATABASE");
    }
    
    private String getHost() {
        return properties.getProperty("HOST");
    }
    
    private String getPort() {
        return properties.getProperty("PORT");
    }
    
    private String getUsername() {
        return properties.getProperty("USERNAME");
    }
    
    private String getPassword() {
        return properties.getProperty("PASSWORD");
    }
    
    
    
    
}
