
package com.rgc.nvrservicesjws.controllers;

import com.rgc.nvrservicesjws.models.Employee;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author RGC
 * @date 7-jun-2020
 */
public interface EmployeeController {
    
    public Response getAll(UriInfo uriInfo, String dateTime);
    
    public Response getById(UriInfo uriInfo, Integer id);
    
    public Response delete(UriInfo uriInfo, Integer id);
    
    public Response update(UriInfo uriInfo, Integer id, Employee employee);
    
    public Response insert(UriInfo uriInfo, Employee employee);
    
}
    

