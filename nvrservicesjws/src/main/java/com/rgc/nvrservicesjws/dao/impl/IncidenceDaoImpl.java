
package com.rgc.nvrservicesjws.dao.impl;

import com.rgc.nvrservicesjws.dao.Database;
import com.rgc.nvrservicesjws.dao.IncidenceDao;
import com.rgc.nvrservicesjws.models.Employee;
import com.rgc.nvrservicesjws.models.Incidence;
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
public class IncidenceDaoImpl implements IncidenceDao {
    
    List<Incidence> incidences = new ArrayList<>();

    @Override
    public List<Incidence> getAll() {
        //List<Incidence> incidences = new ArrayList<Incidence>();
        try {
            String sql = "SELECT * FROM Incidences";
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                Transport transport = new Transport();
                transport.setId(rs.getInt("transport_id"));
                incidences.add(new Incidence(
                        rs.getInt("id"),
                        rs.getString("type"),
                        employee,
                        transport,
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("address"),
                        rs.getString("address_number"),
                        rs.getString("address_locality"),
                        rs.getString("address_codepostal"),
                        rs.getString("address_city"),
                        rs.getString("image_before"),
                        rs.getString("date_img_before"),
                        rs.getString("image_after"),        
                        rs.getString("date_img_after"),
                        rs.getString("created_at"),
                        rs.getString("created_at_db"),
                        rs.getString("updated_at_db"),
                        rs.getInt("syncronized")
                ));
            }   
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return incidences;
    }

    @Override
    public List<Incidence> insertAll(List<Incidence> incidences) {
        List<Incidence> insertIncidences = new ArrayList<Incidence>();
        Connection connection = null;
        try {
            String sql = "INSERT INTO incidences ("
                    + "type, "
                    + "employee_id, "
                    + "transport_id, "
                    + "latitude, "
                    + "longitude, "
                    + "address, "
                    + "address_number, "
                    + "address_locality, "
                    + "address_codepostal, "
                    + "address_city, "
                    + "image_before, "
                    + "date_img_before, "
                    + "image_after, "
                    + "date_img_after, "
                    + "created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            
            // Begin transaction 
            connection.setAutoCommit(false);
            for (Incidence i : incidences) {
                Incidence ii = new Incidence();
                ii.setId(i.getId());
                //prst.setInt(1, i.getId());
                prst.setString(1, i.getType());
                prst.setInt(2, i.getEmployee().getId());
                prst.setInt(3, i.getTransport().getId());
                prst.setDouble(4, i.getLatitude());
                prst.setDouble(5, i.getLongitude());
                prst.setString(6, i.getAddress());
                prst.setString(7, i.getAddress_number());
                prst.setString(8, i.getAddress_locality());
                prst.setString(9, i.getAddress_codepostal());
                prst.setString(10, i.getAddress_city());
                prst.setString(11, i.getImage_before());
                prst.setString(12, i.getDate_img_before());
                prst.setString(13, i.getImage_after());
                prst.setString(14, i.getDate_img_after());
                prst.setString(15, i.getCreated_at());
                prst.execute();
                insertIncidences.add(ii);    
            }
            // End transaction
            connection.commit();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
               System.err.println(ex.getMessage()); 
            }
        } finally {
            Database.destroyDb();
        }
        return insertIncidences;
           
    }

    @Override
    public Incidence getById(Integer id) {
        Incidence incidence = null;
        try {
            String sql = "SELECT * FROM incidences WHERE id = ?";
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                Transport transport = new Transport();
                transport.setId(rs.getInt("transport_id"));
                incidence = new Incidence(
                        rs.getInt("id"),
                        rs.getString("type"),
                        employee,
                        transport,
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("address"),
                        rs.getString("address_number"),
                        rs.getString("address_locality"),
                        rs.getString("address_codepostal"),
                        rs.getString("address_city"),
                        rs.getString("image_before"),
                        rs.getString("date_img_before"),
                        rs.getString("image_after"),        
                        rs.getString("date_img_after"),
                        rs.getString("created_at"),
                        rs.getString("created_at_db"),
                        rs.getString("updated_at_db"),
                        rs.getInt("syncronized")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return incidence; 
    }

    @Override
    public List<Incidence> getAllByType(String type) {
        //List<Incidence> incidences = new ArrayList<Incidence>();
        String sql = "SELECT * FROM incidences WHERE type = ?";
        try {
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1, type);
            ResultSet rs = prst.executeQuery();
            incidences = getIncidences(incidences, rs);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return incidences;
    }
    
    @Override
    public List<Incidence> getAllByDate(String dateStart, String dateEnd) {
        //List<Incidence> incidences = new ArrayList<Incidence>();
        String sql = "SELECT * FROM incidences WHERE created_at BETWEEN CAST(? AS DATETIME) AND CAST(? AS DATETIME)";
        try {
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1, dateStart);
            prst.setString(2, dateEnd);
            ResultSet rs = prst.executeQuery();
            incidences = getIncidences(incidences, rs);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return incidences;
    }
    
    @Override
    public List<Incidence> getAllByTypeDate(String type, String dateStart, String dateEnd) {
        //List<Incidence> incidences = new ArrayList<Incidence>();
        String sql = "SELECT * FROM incidences WHERE type = ? AND created_at BETWEEN ? AND ?";
        try {
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1, type);
            prst.setString(2, dateStart);
            prst.setString(3, dateEnd);
            ResultSet rs = prst.executeQuery();
            incidences = getIncidences(incidences, rs);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return incidences;
    }


    private List<Incidence> getIncidences(List<Incidence> incidences, ResultSet rs) {
        //List<Incidence> incidences = new ArrayList<Incidence>();
        try {
            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                Transport transport = new Transport();
                transport.setId(rs.getInt("transport_id"));
                incidences.add(new Incidence(
                        rs.getInt("id"),
                        rs.getString("type"),
                        employee,
                        transport,
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("address"),
                        rs.getString("address_number"),
                        rs.getString("address_locality"),
                        rs.getString("address_codepostal"),
                        rs.getString("address_city"),
                        rs.getString("image_before"),
                        rs.getString("date_img_before"),
                        rs.getString("image_after"),
                        rs.getString("date_img_after"),
                        rs.getString("created_at"),
                        rs.getString("created_at_db"),
                        rs.getString("updated_at_db"),
                        rs.getInt("syncronized")
                ));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return incidences;
    }

    
    


}
