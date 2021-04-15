
package com.rgc.nvrservicesjws.controllers;

import com.rgc.nvrservicesjws.models.Incidence;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author RGC
 * @date 08-jun-2020  
 */
public interface IncidenceController {
    
    public Response getAll(UriInfo uriInfo, String type, String dateStart, String dateEnd);
    
    public Response insertAll(List<Incidence> incidences, UriInfo uriInfo);
    
    public Response getById(UriInfo uriInfo, Integer id);
    
}
