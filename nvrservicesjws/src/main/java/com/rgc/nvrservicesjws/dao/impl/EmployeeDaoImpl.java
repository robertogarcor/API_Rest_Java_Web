
package com.rgc.nvrservicesjws.dao.impl;

import com.rgc.nvrservicesjws.dao.Database;
import com.rgc.nvrservicesjws.dao.EmployeeDao;
import com.rgc.nvrservicesjws.models.Employee;
import com.rgc.nvrservicesjws.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author RGC
 * @date 07-jun-2020
 */
public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public List<Employee> getAll() { 
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM employees WHERE active = ?";
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1, 1);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"), 
                        rs.getString("firstname"), 
                        rs.getString("surname"),
                        //Utils.convertFormatStringDateTimeToDate(rs.getString("created_at")), // if data type in model is Date
                        rs.getString("created_at"), // if data type in model is String
                        //Utils.convertFormatStringDateTimeToDate(rs.getString("updated_at")), // if data type in model is Date
                        rs.getString("updated_at"), // if data type in model is String
                        rs.getInt("active")));   
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return employees;
    }

    @Override
    public Employee getById(Integer id) {
        Employee employee = null;
        try {
            String sql = "SELECT * FROM employees WHERE id = ?";
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                employee = new Employee(
                        rs.getInt("id"), 
                        rs.getString("firstname"), 
                        rs.getString("surname"),
                        //Utils.convertFormatStringDateTimeToDate(rs.getString("created_at")), // if data type in model is Date
                        rs.getString("created_at"), // if data type in model is String
                        //Utils.convertFormatStringDateTimeToDate(rs.getString("updated_at")), // if data type in model is Date
                        rs.getString("updated_at"), // if data type in model is String
                        rs.getInt("active"));   
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return employee; 
    }

    @Override
    public Integer delete(Integer id) {
        Integer res = 0;
        Connection connection = null;
        try {
            String sql = "UPDATE employees SET active = 0 WHERE id = ?";
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
    public Integer update(Employee employee) {
        Integer res = 0;
        Connection connection = null;
        try {
            String sql = "UPDATE employees SET firstname = ?, surname = ? WHERE id = ?";
            connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            // Begin transaction
            connection.setAutoCommit(false);
            prst.setString(1, employee.getFirstname());
            prst.setString(2, employee.getSurname());
            prst.setInt(3, employee.getId());
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
    public Employee insert(Employee employee) {
        Connection connection = null;
        try {
            String sql = "INSERT INTO employees (firstname, surname) VALUES (?,?)";
            connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            prst.setString(1, employee.getFirstname());
            prst.setString(2, employee.getSurname());
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
        return employee;
    }

    @Override
    public List<Employee> getAllByDate(String dateTime) {
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM employees WHERE updated_at > ? AND active = 1";
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1, dateTime);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"), 
                        rs.getString("firstname"), 
                        rs.getString("surname"),
                        //Utils.convertFormatStringDateTimeToDate(rs.getString("created_at")), 
                        rs.getString("created_at"), // if data type in model is String
                        //Utils.convertFormatStringDateTimeToDate(rs.getString("updated_at")), // if data type in model is Date
                        rs.getString("updated_at"), // if data type in model is String
                        rs.getInt("active")));   
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            Database.destroyDb();
        }
        return employees;
    }
    
    
    
}
