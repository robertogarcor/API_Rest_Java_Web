
package com.rgc.nvrservicesjws.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author RGC
 * @date 12-jun-2020  
 */
@XmlRootElement(name = "exception")
@XmlType(propOrder = {"code","message", "detail"})
public class MessageException {
    
    private String message;
    private Integer code;
    private String detail;

    public MessageException() {
    }

    public MessageException(String message, Integer code, String detail) {
        this.message = message;
        this.code = code;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    
    
}
