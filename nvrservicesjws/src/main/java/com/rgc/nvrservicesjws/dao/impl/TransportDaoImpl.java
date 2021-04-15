
package com.rgc.nvrservicesjws.dao.impl;

import com.rgc.nvrservicesjws.dao.Database;
import com.rgc.nvrservicesjws.dao.TransportDao;
import com.rgc.nvrservicesjws.models.Transport;
import com.rgc.nvrservicesjws.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RGC
 * @date 08-jun-2020  
 */
public class TransportDaoImpl implements TransportDao {

    @Override
    public List<Transport> getAll() {
        List<Transport> transports = new ArrayList<Transport>();
        try {
            String sql = "SELECT * FROM transports WHERE active = ?";
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1, 1);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                transports.add(new Transport(
                        rs.getInt("id"), 
                        rs.getInt("number"), 
                        rs.getString("created_at"),
                        rs.getString("updated_at"),
                        rs.getInt("active")));   
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return transports;
    }

    @Override
    public Transport getById(Integer id) {
        Transport transport = null;
        try {
            String sql = "SELECT * FROM transports WHERE id = ?";
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                transport = new Transport(
                        rs.getInt("id"), 
                        rs.getInt("number"), 
                        rs.getString("created_at"),
                        rs.getString("updated_at"),
                        rs.getInt("active"));   
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return transport;
    }

    @Override
    public Integer delete(Integer id) {
        Integer res = 0;
        Connection connection = null;
        try {
            String sql = "UPDATE transports SET active = 0 WHERE id = ?";
            connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            // Begin transaction
            connection.setAutoCommit(false);
            prst.setInt(1, id);
            // This return int (1) rows affected (2) 0 return nothing 
            res = prst.executeUpdate();
            // End transaction
            connection.commit();
        } catch (Exception ex) {
            try {
                System.err.println(ex.getMessage());
                connection.rollback();
            } catch (SQLException e) {
                System.err.println(e.getMessage()); 
            }
        } finally {
            Database.destroyDb();
        }
        return res;
    }
    
    @Override
    public Integer update(Transport transport) {
        Integer res = 0;
        Connection connection = null;
        try {
            String sql = "UPDATE transports SET number = ? WHERE id = ?";
            connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            // Begin transaction
            connection.setAutoCommit(false);
            prst.setInt(1, transport.getNumber());
            prst.setInt(2, transport.getId());
            res = prst.executeUpdate();
            // End transaction
            connection.commit();
        } catch (Exception e) {
            try {
                System.err.println(e.getMessage());
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage()); 
            }
        } finally {
            Database.destroyDb();
        }
        return res;
    }

    @Override
    public Transport insert(Transport transport) {
        Connection connection = null;
        try {
            String sql = "INSERT INTO transports (number) VALUES (?)";
            connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            prst.setInt(1, transport.getNumber());
            prst.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                System.err.println(e.getMessage());
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage()); 
            }
        } finally {
            Database.destroyDb();
        }
        return transport;
    }

    @Override
    public List<Transport> getAllByDate(String dateTime) {
        List<Transport> transports = new ArrayList<>();
        try {
            String sql = "SELECT * FROM transports WHERE updated_at > ? AND active = 1";
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1, dateTime);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                transports.add(new Transport(
                        rs.getInt("id"), 
                        rs.getInt("number"), 
                        rs.getString("created_at"),
                        rs.getString("updated_at"),
                        rs.getInt("active")));  
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return transports;
    }
    
}
