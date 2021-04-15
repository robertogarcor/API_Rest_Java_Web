
package com.rgc.nvrservicesjws.services.impl;

import com.rgc.nvrservicesjws.dao.TransportDao;
import com.rgc.nvrservicesjws.dao.impl.TransportDaoImpl;
import com.rgc.nvrservicesjws.models.Transport;
import com.rgc.nvrservicesjws.services.TransportService;
import java.util.List;

/**
 * @author RGC
 * @date 08-jun-2020  
 */
public class TransportServiceImpl implements TransportService {

    private TransportDao transportDao = new TransportDaoImpl();
        
    @Override
    public List<Transport> getAll() {
        return transportDao.getAll();
    }

    @Override
    public Transport getById(Integer id) {
        return transportDao.getById(id);
    }

    @Override
    public Integer delete(Integer id) {
        return transportDao.delete(id);
    }

    @Override
    public Integer update(Transport transport) {
       return transportDao.update(transport);
    }

    @Override
    public Transport insert(Transport transport) {
        return transportDao.insert(transport);
    }

    @Override
    public List<Transport> getAllByDate(String dateTime) {
        return transportDao.getAllByDate(dateTime);
    }

}
