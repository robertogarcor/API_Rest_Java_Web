
package com.rgc.nvrservicesjws.models;

/**
 * @author RGC
 * @date 14-jun-2020  
 */
public class Navigation {

    private String atitle;
    private String link;
    private String zdescription;

    public Navigation() {
    }

    public Navigation(String atitle, String link, String zdescription) {
        this.atitle = atitle;
        this.link = link;
        this.zdescription = zdescription;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getZdescription() {
        return zdescription;
    }

    public void setZdescription(String zdescription) {
        this.zdescription = zdescription;
    }
    
    
    
    
    
}
