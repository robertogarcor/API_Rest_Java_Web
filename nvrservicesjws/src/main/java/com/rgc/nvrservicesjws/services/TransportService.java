
package com.rgc.nvrservicesjws.services;

import com.rgc.nvrservicesjws.models.Transport;
import java.util.List;

/**
 *
 * @author RGC
 * @date 08-jun-2020  
 */
public interface TransportService {
    
    public List<Transport> getAll();
    
    public Transport getById(Integer id);
    
    public List<Transport> getAllByDate(String dateTime);
    
    public Integer delete(Integer id);
    
    public Integer update(Transport transport);
    
    public Transport insert(Transport transport);

}
