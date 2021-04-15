
package com.rgc.nvrservicesjws.services.impl;

import com.rgc.nvrservicesjws.dao.EmployeeDao;
import com.rgc.nvrservicesjws.dao.impl.EmployeeDaoImpl;
import com.rgc.nvrservicesjws.models.Employee;
import java.util.List;
import com.rgc.nvrservicesjws.services.EmployeeService;

/**
 * @author RGC
 * @date 07-jun-2020  
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    
    @Override
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Override
    public Employee getById(Integer id) {
       return employeeDao.getById(id);
    }

    @Override
    public Integer delete(Integer id) {
        return employeeDao.delete(id);
    }

    @Override
    public Integer update(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public Employee insert(Employee employee) {
        return employeeDao.insert(employee);
    }

    @Override
    public List<Employee> getAllByDate(String dateTime) {
        return employeeDao.getAllByDate(dateTime);
    }
    
}
