package com.rayton.gps.service.lisence;

import com.rayton.gps.dao.lisence.Drivervl;
import com.rayton.gps.dao.lisence.DrivervlDao;
import com.rayton.gps.util.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrivervlServiceImpl implements DrivervlService {
    @Autowired
    private DrivervlDao drivervlDao;

    @Override
    public long getDrivervlRowCount(Assist assist) {
        return drivervlDao.getDrivervlRowCount(assist);
    }

    @Override
    public List<Drivervl> selectDrivervl(Assist assist) {
        return drivervlDao.selectDrivervl(assist);
    }

    @Override
    public Drivervl selectDrivervlByObj(Drivervl obj) {
        return drivervlDao.selectDrivervlByObj(obj);
    }

    @Override
    public Drivervl selectDrivervlById(String id) {
        return drivervlDao.selectDrivervlById(id);
    }

    @Override
    public int insertDrivervl(Drivervl value) {
        return drivervlDao.insertDrivervl(value);
    }

    @Override
    public int insertNonEmptyDrivervl(Drivervl value) {
        return drivervlDao.insertNonEmptyDrivervl(value);
    }

    @Override
    public int insertDrivervlByBatch(List<Drivervl> value) {
        return drivervlDao.insertDrivervlByBatch(value);
    }

    @Override
    public int deleteDrivervlById(String id) {
        return drivervlDao.deleteDrivervlById(id);
    }

    @Override
    public int deleteDrivervl(Assist assist) {
        return drivervlDao.deleteDrivervl(assist);
    }

    @Override
    public int updateDrivervlById(Drivervl enti) {
        return drivervlDao.updateDrivervlById(enti);
    }

    @Override
    public int updateDrivervl(Drivervl value, Assist assist) {
        return drivervlDao.updateDrivervl(value, assist);
    }

    @Override
    public int updateNonEmptyDrivervlById(Drivervl enti) {
        return drivervlDao.updateNonEmptyDrivervlById(enti);
    }

    @Override
    public int updateNonEmptyDrivervl(Drivervl value, Assist assist) {
        return drivervlDao.updateNonEmptyDrivervl(value, assist);
    }

    public DrivervlDao getDrivervlDao() {
        return this.drivervlDao;
    }

    public void setDrivervlDao(DrivervlDao drivervlDao) {
        this.drivervlDao = drivervlDao;
    }

}