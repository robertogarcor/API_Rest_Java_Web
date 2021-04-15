
package com.rgc.nvrservicesjws.controllers;

import com.rgc.nvrservicesjws.models.Transport;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author RGC
 * @date 08-jun-2020  
 */
public interface TransportController {

    public Response getAll(UriInfo uriInfo, String dateTime);
    
    public Response getById(UriInfo uriInfo, Integer id);
    
    public Response delete(UriInfo uriInfo, Integer id);
    
    public Response update(UriInfo uriInfo, Integer id, Transport transport);
    
    public Response insert(UriInfo uriInfo, Transport transport);
}
