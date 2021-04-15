
package com.rgc.nvrservicesjws.exception;

/**
 *
 * @author RGC
 * @date 12-jun-2020  
 */
public class ApiCustomException extends RuntimeException {

    private Integer code;
    private String detail;
    
    public ApiCustomException(String message, Integer code, String detail) {
        super(message);
        this.code = code;
        this.detail = detail;
    }

    public Integer getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }
    
    

}
