
package com.rgc.nvrservicesjws.services.impl;

import com.rgc.nvrservicesjws.dao.IncidenceDao;
import com.rgc.nvrservicesjws.dao.impl.IncidenceDaoImpl;
import com.rgc.nvrservicesjws.models.Incidence;
import com.rgc.nvrservicesjws.services.IncidenceService;
import java.util.List;

/**
 * @author RGC
 * @date 08-jun-2020  
 */
public class IncidenceServiceImpl implements IncidenceService {
    
    private IncidenceDao incidenceDao = new IncidenceDaoImpl();

    @Override
    public List<Incidence> getAll() {
        return incidenceDao.getAll();
    }

    @Override
    public List<Incidence> insertAll(List<Incidence> incidences) {
        return incidenceDao.insertAll(incidences);
    }

    @Override
    public Incidence getById(Integer id) {
        return incidenceDao.getById(id);
    }

    @Override
    public List<Incidence> getAllByType(String type) {
        return incidenceDao.getAllByType(type);
    }

    @Override
    public List<Incidence> getAllByDate(String dateStart, String dateEnd) {
        return incidenceDao.getAllByDate(dateStart, dateEnd);
    }

    @Override
    public List<Incidence> getAllByTypeDate(String type, String dateStart, String dateEnd) {
        return incidenceDao.getAllByTypeDate(type, dateStart, dateEnd);
    }

}
