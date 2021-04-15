
package com.rgc.nvrservicesjws.models;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author RGC
 * @date 07-jun-2020  
 */
@XmlRootElement(name = "employees")
@XmlType(propOrder = {"id","firstname", "surname", "created_at", "updated_at","active"})
public class Employee implements Serializable {
    
    private Integer id;
    private String firstname;
    private String surname;
    private String created_at;
    private String updated_at;
    private Integer active;
    
    private Navigation navigation;
    
    public Employee(){}

    public Employee(Integer id, String firstname, String surname, String created_at, String updated_at, Integer active) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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