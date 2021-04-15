
package com.rgc.nvrservicesjws.services;

import com.rgc.nvrservicesjws.models.Employee;
import java.util.List;

/**
 *
 * @author RGC
 * @date 7-jun-2020
 */
public interface EmployeeService {
    
    public List<Employee> getAll();
    
    public List<Employee> getAllByDate(String dateTime);
    
    public Employee getById(Integer id);
    
    public Integer delete(Integer id);
    
    public Integer update(Employee employee);
    
    public Employee insert(Employee employee);
    
    
}
