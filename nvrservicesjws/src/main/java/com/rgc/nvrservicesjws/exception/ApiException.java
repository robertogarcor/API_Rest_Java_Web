
package com.rgc.nvrservicesjws.exception;

import com.rgc.nvrservicesjws.models.MessageException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author RGC
 * @date 12-jun-2020  
 */
@Provider
public class ApiException /*implements ExceptionMapper<Throwable>*/ {
    
//    @Override
//    public Response toResponse(Throwable exception) {
//        MessageException message = new MessageException(exception.getMessage(),
//                                                        500,
//                                                        "An error has occurred. Contact the Administrator.");
//        return Response.status(Response.Status.NOT_FOUND)
//                .entity(message)
//                .build();
//    }

}
