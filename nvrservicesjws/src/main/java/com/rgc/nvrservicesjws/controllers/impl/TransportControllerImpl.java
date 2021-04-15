
package com.rgc.nvrservicesjws.controllers.impl;

import com.rgc.nvrservicesjws.controllers.TransportController;
import com.rgc.nvrservicesjws.exception.ApiCustomException;
import com.rgc.nvrservicesjws.models.Navigation;
import com.rgc.nvrservicesjws.models.Transport;
import com.rgc.nvrservicesjws.services.TransportService;
import com.rgc.nvrservicesjws.services.impl.TransportServiceImpl;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
 * http://server/nvrservicesjws/transports --> All Transports
 * http://server/nvrservicesjws/transports/id --> Transport by id
 * http://server/nvrservicesjws/transports/id/update --> Update Transport by id
 * http://server/nvrservicesjws/transports/id/delete --> Delte Transport by id
 * 
 * POST
 * http://server/nvrservicesjws/transports --> Insert Transport
 * 
 * 
 */
@Path("/transports")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransportControllerImpl implements TransportController {

    private TransportService transportService = new TransportServiceImpl();
    
    @GET
    @Override
    public Response getAll(@Context UriInfo uriInfo, @QueryParam("dateupd") String dateTime) {
        List<Transport> transports;
        if (dateTime != null && dateTime.length() > 0) {
            LocalDateTime dt = LocalDateTime.parse(dateTime);
            transports = transportService.getAllByDate(dt.toString());
        } else {
            transports = transportService.getAll();
        }
        if (transports.isEmpty()) {
            throw new ApiCustomException("Not found Resources.",
                    404, 
                    "Not exists this resource in the server. Contact the Administrator.");
        }
        for(Transport t : transports) {
            Navigation nav = new Navigation("Resource Transport #" + t.getId(), 
                                            uriInfo.getAbsolutePath().toString() + "/" + t.getId(), 
                                            "Location Resource Transport #" + t.getId());
            t.setNavigation(nav);
        }
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok(transports)
                .location(uri)
                .build();  
    }

    @Path("/{id}")
    @GET
    @Override
    public Response getById(@Context UriInfo uriInfo, @PathParam("id") Integer id) {
        Transport transport = transportService.getById(id);
        if (transport == null) {
            throw new ApiCustomException("Not found Resource.",
                    404, 
                    "Not exists this resource in the server. Contact the Administrator.");
        }
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        Navigation nav = new Navigation("Resource Transport #" + transport.getId(), 
                                        uriInfo.getAbsolutePath().toString(), 
                                        "Location Resource Transport #" + transport.getId());
        transport.setNavigation(nav);
        return Response.ok(transport)
                .location(uri)
                .build();
    }

    @Path("/{id}/delete")
    @DELETE
    @Override
    public Response delete(@Context UriInfo uriInfo, @PathParam("id") Integer id) {
        // This return int (1) rows affected (2) 0 return nothing
        Integer res = transportService.delete(id);
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
    public Response update(@Context UriInfo uriInfo, @PathParam("id") Integer id, Transport transport) {
        // This return int (1) rows affected (2) 0 return nothing
        Integer res = transportService.update(transport);
        Transport t = null;
        URI uri = uri = uriInfo.getAbsolutePathBuilder().build();
        if (res == 1) {
            t = transportService.getById(id);
            if (t == null) {
                throw new ApiCustomException("Not found Resources.",
                        404, 
                        "Not exists this resource in the server. Contact the Administrator.");
            }
        } else {
            throw new ApiCustomException("Error process update.",
                    500,
                    "A ocurred an possible error in the server. Contact the Administrator.");
        }
        return Response.status(Response.Status.OK)
                        .entity(t)
                        .location(uri)
                        .build();
    }

    @POST
    @Override
    public Response insert(@Context UriInfo uriInfo, Transport transport) {
        Transport t = transportService.insert(transport);
        if (t == null) {
            throw new ApiCustomException("Not found Resource.",
                    404, 
                    "Not exists this resource in the server. Contact the Administrator.");
        }
        URI uri = uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.status(Response.Status.OK)
                        .entity(t)
                        .location(uri)
                        .build();
    }
    

}
