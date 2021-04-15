
package com.rgc.nvrservicesjws.controllers.impl;

import com.rgc.nvrservicesjws.controllers.EmployeeController;
import com.rgc.nvrservicesjws.exception.ApiCustomException;
import com.rgc.nvrservicesjws.models.Employee;
import com.rgc.nvrservicesjws.models.Navigation;
import com.rgc.nvrservicesjws.services.impl.EmployeeServiceImpl;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.rgc.nvrservicesjws.services.EmployeeService;
import java.net.URI;
import java.time.LocalDateTime;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 * @author RGC
 * @date 7-jun-2020
 * 
 * GET
 * http://server/nvrservicesjws/employees --> All Employees
 * http://server/nvrservicesjws/employees/id --> Employee by id
 * http://server/nvrservicesjws/employees/id/update --> Update Employee by id
 * http://server/nvrservicesjws/employees/id/delete --> Delte Employee by id
 * 
 * POST
 * http://server/nvrservicesjws/employees --> Insert Employee
 * 
 */
@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeControllerImpl implements EmployeeController {
    
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    @GET
    @Override
    public Response getAll(@Context UriInfo uriInfo, @QueryParam("dateupd") String dateTime) {
        List<Employee> employees;
        if (dateTime != null && dateTime.length() > 0) {
            LocalDateTime dt = LocalDateTime.parse(dateTime);
            employees = employeeService.getAllByDate(dt.toString());
        } else {
            employees = employeeService.getAll();
        }
        if (employees.isEmpty()) {
            throw new ApiCustomException("Not found Resources.",
                    404, 
                    "Not exists this resource in the server. Contact the Administrator.");
        }
        for(Employee e : employees) {
            Navigation nav = new Navigation("Resource Employee #" + e.getId(), 
                                            uriInfo.getAbsolutePath().toString() + "/" + e.getId(), 
                                            "Location Resource Employee #" + e.getId());
            e.setNavigation(nav);
        }
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok(employees)
                .location(uri)
                .build();
    }
        
    @Path("/{id}")
    @GET
    @Override
    public Response getById(@Context UriInfo uriInfo, @PathParam("id") Integer id) {
        Employee employee = employeeService.getById(id);
        if (employee == null) {
            throw new ApiCustomException("Not found Resource.", 
                    404,
                    "Not exists this resource in the server. Contact the Administrator.");
        }
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        Navigation nav = new Navigation("Resource Employee #" + employee.getId(), 
                                        uriInfo.getAbsolutePath().toString(), 
                                        "Location Resource Employee #" + employee.getId());
        employee.setNavigation(nav);
        return Response.ok(employee)
                .location(uri)
                .build();
    }

    @Path("/{id}/delete")
    @DELETE
    @Override
    public Response delete(@Context UriInfo uriInfo, @PathParam("id") Integer id) {
        // This return int (1) rows affected (2) 0 return nothing
        Integer res = employeeService.delete(id);
        URI uri = uri = uriInfo.getAbsolutePathBuilder().build();
        if (res == 1) { 
            return Response.status(Response.Status.OK)
                    .location(uri)
                    .build();          
        } else {
            throw new ApiCustomException("Error process delete.", 
                    500, 
                    "A ocurred an possible error in the server. Contact the Administrator.");
        }
    }

    @Path("/{id}/update")
    @PUT
    @Override
    public Response update(@Context UriInfo uriInfo, @PathParam("id") Integer id, Employee employee) {
        // This return int (1) rows affected (2) 0 return nothing
        Integer res = employeeService.update(employee);
        Employee e = null;
        URI uri = uri = uriInfo.getAbsolutePathBuilder().build();
        if (res == 1) {
            e = employeeService.getById(id);
            if (e == null) {
                throw new ApiCustomException("Not found Resource.", 
                        404,
                        "Not exists this resource in the server. Contact the Administrator.");
            }
        } else {
            throw new ApiCustomException("Error process update.",
                    500, 
                    "A ocurred an possible error in the server. Contact the Administrator.");
        }
        return Response.status(Response.Status.OK)
                        .entity(e)
                        .location(uri)
                        .build();
    }
    
    @POST
    @Override
    public Response insert(@Context UriInfo uriInfo, Employee employee) {
        Employee e = employeeService.insert(employee);
        if (e == null) {
            throw new ApiCustomException("Not found Resource.",
                    404, 
                    "Not exists this resource in the server. Contact the Administrator.");
        }
        URI uri = uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.status(Response.Status.OK)
                        .entity(e)
                        .location(uri)
                        .build();
    }

    
}
   
