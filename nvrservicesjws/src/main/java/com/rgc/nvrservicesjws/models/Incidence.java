
package com.rgc.nvrservicesjws.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author RGC
 * @date 08-jun-2020  
 */
@XmlRootElement(name = "incidences")
public class Incidence {
    
    private Integer id;
    private String type;
    private Employee employee;
    private Transport transport;
    private Double latitude;
    private Double longitude;
    private String address;
    private String address_number;
    private String address_locality;
    private String address_codepostal;
    private String address_city;
    private String image_before;
    private String date_img_before;
    private String image_after;
    private String date_img_after;
    private String created_at;
    private String created_at_db;
    private String updated_at_db;
    private Integer syncronized;
    
    private Navigation navigation;
    
    public Incidence(){}

    public Incidence(Integer id, String type, Employee employee, Transport transport, Double latitude, Double longitude, String address, String address_number, String address_locality, String address_codepostal, String address_city, String image_before, String date_img_before, String image_after, String date_img_after, String created_at, String created_at_db, String updated_at_db, Integer syncronized) {
        this.id = id;
        this.type = type;
        this.employee = employee;
        this.transport = transport;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.address_number = address_number;
        this.address_locality = address_locality;
        this.address_codepostal = address_codepostal;
        this.address_city = address_city;
        this.image_before = image_before;
        this.date_img_before = date_img_before;
        this.image_after = image_after;
        this.date_img_after = date_img_after;
        this.created_at = created_at;
        this.created_at_db = created_at_db;
        this.updated_at_db = updated_at_db;
        this.syncronized = syncronized;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_number() {
        return address_number;
    }

    public void setAddress_number(String address_number) {
        this.address_number = address_number;
    }

    public String getAddress_locality() {
        return address_locality;
    }

    public void setAddress_locality(String address_locality) {
        this.address_locality = address_locality;
    }

    public String getAddress_codepostal() {
        return address_codepostal;
    }

    public void setAddress_codepostal(String address_codepostal) {
        this.address_codepostal = address_codepostal;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getImage_before() {
        return image_before;
    }

    public void setImage_before(String image_before) {
        this.image_before = image_before;
    }

    public String getDate_img_before() {
        return date_img_before;
    }

    public void setDate_img_before(String date_img_before) {
        this.date_img_before = date_img_before;
    }

    public String getImage_after() {
        return image_after;
    }

    public void setImage_after(String image_after) {
        this.image_after = image_after;
    }

    public String getDate_img_after() {
        return date_img_after;
    }

    public void setDate_img_after(String date_img_after) {
        this.date_img_after = date_img_after;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at_db() {
        return created_at_db;
    }

    public void setCreated_at_db(String created_at_db) {
        this.created_at_db = created_at_db;
    }

    public String getUpdated_at_db() {
        return updated_at_db;
    }

    public void setUpdated_at_db(String updated_at_db) {
        this.updated_at_db = updated_at_db;
    }

    public Integer getSyncronized() {
        return syncronized;
    }

    public void setSyncronized(Integer syncronized) {
        this.syncronized = syncronized;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }
    
    

    
    
    
    
    

}
