
package com.rgc.nvrservicesjws.dao;

import com.rgc.nvrservicesjws.models.Employee;
import java.util.List;

/**
 * @author RGC
 * @date 07-jun-2020
 */
public interface EmployeeDao {
    
    public List<Employee> getAll();
    
    public List<Employee> getAllByDate(String dateTime);
    
    public Employee getById(Integer id);
    
    public Integer delete(Integer id);
    
    public Integer update(Employee employee);
    
    public Employee insert(Employee employee);
   
}
