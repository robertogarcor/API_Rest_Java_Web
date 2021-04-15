
package com.rgc.nvrservicesjws.services;

import com.rgc.nvrservicesjws.models.Incidence;
import java.util.List;

/**
 * @author RGC
 * @date 08-jun-2020  
 */
public interface IncidenceService {
    
    public List<Incidence> getAll();
    
    public List<Incidence> insertAll(List<Incidence> incidences);
    
    public Incidence getById(Integer id);
    
    public List<Incidence> getAllByType(String type);
    
    public List<Incidence> getAllByDate(String dateStart, String dateEnd);
    
    public List<Incidence> getAllByTypeDate(String type, String dateStart, String dateEnd);
    
}
