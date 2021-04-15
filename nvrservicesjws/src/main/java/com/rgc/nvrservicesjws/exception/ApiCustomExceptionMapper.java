
package com.rgc.nvrservicesjws.exception;

import com.rgc.nvrservicesjws.models.MessageException;
import javafx.scene.media.Media;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Wapper to mapper ApiCustomException
 * Not necessary use try/cast to capture exception
 * @author RGC
 * @date 12-jun-2020  
 */
@Provider
public class ApiCustomExceptionMapper implements ExceptionMapper<ApiCustomException>{

    @Override
    public Response toResponse(ApiCustomException exception) {
        Status status = Response.Status.NOT_FOUND;
        if (exception.getCode() == 500) {
            status = Response.Status.INTERNAL_SERVER_ERROR; 
        }
        MessageException message = new MessageException(exception.getMessage(),
                                                        exception.getCode(),
                                                        exception.getDetail());
        return Response.status(status)
                .entity(message)
                .build();
    }

}
