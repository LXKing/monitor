package com.rayton.gps.service.lisence;
import java.util.List;
import com.rayton.gps.dao.lisence.DrivervrDao;
import com.rayton.gps.dao.lisence.Drivervr;
import com.rayton.gps.util.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DrivervrServiceImpl implements DrivervrService{
    @Autowired
    private DrivervrDao drivervrDao;
    @Override
    public long getDrivervrRowCount(Assist assist){
        return drivervrDao.getDrivervrRowCount(assist);
    }
    @Override
    public List<Drivervr> selectDrivervr(Assist assist){
        return drivervrDao.selectDrivervr(assist);
    }
    @Override
    public Drivervr selectDrivervrByObj(Drivervr obj){
        return drivervrDao.selectDrivervrByObj(obj);
    }
    @Override
    public Drivervr selectDrivervrById(String id){
        return drivervrDao.selectDrivervrById(id);
    }
    @Override
    public int insertDrivervr(Drivervr value){
        return drivervrDao.insertDrivervr(value);
    }
    @Override
    public int insertNonEmptyDrivervr(Drivervr value){
        return drivervrDao.insertNonEmptyDrivervr(value);
    }
    @Override
    public int insertDrivervrByBatch(List<Drivervr> value){
        return drivervrDao.insertDrivervrByBatch(value);
    }
    @Override
    public int deleteDrivervrById(String id){
        return drivervrDao.deleteDrivervrById(id);
    }
    @Override
    public int deleteDrivervr(Assist assist){
        return drivervrDao.deleteDrivervr(assist);
    }
    @Override
    public int updateDrivervrById(Drivervr enti){
        return drivervrDao.updateDrivervrById(enti);
    }
    @Override
    public int updateDrivervr(Drivervr value, Assist assist){
        return drivervrDao.updateDrivervr(value,assist);
    }
    @Override
    public int updateNonEmptyDrivervrById(Drivervr enti){
        return drivervrDao.updateNonEmptyDrivervrById(enti);
    }
    @Override
    public int updateNonEmptyDrivervr(Drivervr value, Assist assist){
        return drivervrDao.updateNonEmptyDrivervr(value,assist);
    }

    public DrivervrDao getDrivervrDao() {
        return this.drivervrDao;
    }

    public void setDrivervrDao(DrivervrDao drivervrDao) {
        this.drivervrDao = drivervrDao;
    }

}