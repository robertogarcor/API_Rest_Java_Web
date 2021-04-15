
package com.rgc.nvrservicesjws.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author RGC
 * @date 08-jun-2020  
 */
@XmlRootElement(name = "transports")
@XmlType(propOrder = {"id","number", "created_at", "updated_at","active"})
public class Transport {
    
    private Integer id;
    private Integer number;
    private String created_at;
    private String updated_at;
    private Integer active;
    
    private Navigation navigation;
    
    public Transport(){}

    public Transport(Integer id, Integer number, String created_at, String updated_at, Integer active) {
        this.id = id;
        this.number = number;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    
    
    
    
    
}
