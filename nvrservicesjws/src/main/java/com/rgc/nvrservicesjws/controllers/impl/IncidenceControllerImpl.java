
package com.rgc.nvrservicesjws.controllers.impl;

import com.rgc.nvrservicesjws.controllers.IncidenceController;
import com.rgc.nvrservicesjws.exception.ApiCustomException;
import com.rgc.nvrservicesjws.models.Incidence;
import com.rgc.nvrservicesjws.models.Navigation;
import com.rgc.nvrservicesjws.services.IncidenceService;
import com.rgc.nvrservicesjws.services.impl.IncidenceServiceImpl;
import static com.rgc.nvrservicesjws.utils.Utils.convertFormatDateToDateTime;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author RGC
 * @date 08-jun-2020
 * 
 * GET
 * http://server/nvrservicesjws/incidences --> All incidences
 * http://server/nvrservicesjws/incidences?type="" --> All incidences by type params
 * http://server/nvrservicesjws/incidences?type=graffitis&dateStart=20200115065416&dateEnd=20200120065416 --> All incidences by type | dateStart | dateEnd params
 * http://server/nvrservicesjws/incidences?dateStart=20200115065416&dateEnd=20200120065416 --> All incidences by dateStart | dateEnd params
 * http://server/nvrservicesjws/incidences/id --> Incidence by id
 * 
 * POST
 * http://server/nvrservicesjws/incidences --> Insert Incidence
 * 
 * 
 */
@Path("/incidences")
public class IncidenceControllerImpl implements IncidenceController {

    private IncidenceService incidenceService = new IncidenceServiceImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAll(@Context UriInfo uriInfo, @QueryParam("type") String type, @QueryParam("dateStart") String dateStart, @QueryParam("dateEnd") String dateEnd) {
        
        List<Incidence> incidences;
        if (type != null && type.length() > 0) {
            if (dateStart != null && dateStart.length() > 0 ) { 
                if (dateEnd != null && dateEnd.length() > 0 ) {
                    incidences = incidenceService.getAllByTypeDate(type, dateStart, dateEnd);
                } else {
                    throw new ApiCustomException("Bad Request.",
                    400,
                    "A date start and end is required.");
                } 
            } else {
                incidences = incidenceService.getAllByType(type);
            }
        } else if (dateStart != null && dateStart.length() > 0) {
            if (dateEnd != null && dateEnd.length() > 0 ) {
                incidences = incidenceService.getAllByDate(dateStart, dateEnd);
            } else {
                throw new ApiCustomException("Bad Request.",
                    400,
                    "A date start and end is required.");
            }
        } else {
            incidences = incidenceService.getAll();
        }
      
        if (incidences.isEmpty()) {
            throw new ApiCustomException("Not found Resources.",
                    404,
                    "Not exists this resource in the server. Contact the Administrator.");
        }
        for(Incidence i : incidences) {
            Navigation nav = new Navigation("Resource Incidence #" + i.getId(), 
                                            uriInfo.getAbsolutePath().toString() + "/" + i.getId(), 
                                            "Location Resource Incidence #" + i.getId());
            i.setNavigation(nav);
        }
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok()
                .location(uri)
                .entity(incidences)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response insertAll(List<Incidence> incidences, @Context UriInfo uriInfo) {
        List<Incidence> insertIncidencesId = incidenceService.insertAll(incidences);
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.status(Response.Status.CREATED)
                .location(uri)
                .entity(insertIncidencesId)
                .build();
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getById(@Context UriInfo uriInfo, @PathParam("id") Integer id) {
        Incidence incidence = incidenceService.getById(id);
        if (incidence == null) {
            throw new ApiCustomException("Not found Resource.",
                    404,
                    "Not exists this resource in the server. Contact the Administrator.");
        }
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        Navigation nav = new Navigation("Resource Incidence #" + incidence.getId(), 
                                        uriInfo.getAbsolutePath().toString(), 
                                        "Location Resource Incidence #" + incidence.getId());
        incidence.setNavigation(nav);
        return Response.ok(incidence)
                .location(uri)
                .build();
    }

}
