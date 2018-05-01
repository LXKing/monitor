package com.rayton.gps.service.lisence;
import java.util.List;
import com.rayton.gps.dao.lisence.DriverdlDao;
import com.rayton.gps.dao.lisence.Driverdl;
import com.rayton.gps.util.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DriverdlServiceImpl implements DriverdlService{
    @Autowired
    private DriverdlDao driverdlDao;
    @Override
    public long getDriverdlRowCount(Assist assist){
        return driverdlDao.getDriverdlRowCount(assist);
    }
    @Override
    public List<Driverdl> selectDriverdl(Assist assist){
        return driverdlDao.selectDriverdl(assist);
    }
    @Override
    public Driverdl selectDriverdlByObj(Driverdl obj){
        return driverdlDao.selectDriverdlByObj(obj);
    }
    @Override
    public Driverdl selectDriverdlById(Integer id){
        return driverdlDao.selectDriverdlById(id);
    }
    @Override
    public int insertDriverdl(Driverdl value){
        return driverdlDao.insertDriverdl(value);
    }
    @Override
    public int insertNonEmptyDriverdl(Driverdl value){
        return driverdlDao.insertNonEmptyDriverdl(value);
    }
    @Override
    public int insertDriverdlByBatch(List<Driverdl> value){
        return driverdlDao.insertDriverdlByBatch(value);
    }
    @Override
    public int deleteDriverdlById(Integer id){
        return driverdlDao.deleteDriverdlById(id);
    }
    @Override
    public int deleteDriverdl(Assist assist){
        return driverdlDao.deleteDriverdl(assist);
    }
    @Override
    public int updateDriverdlById(Driverdl enti){
        return driverdlDao.updateDriverdlById(enti);
    }
    @Override
    public int updateDriverdl(Driverdl value, Assist assist){
        return driverdlDao.updateDriverdl(value,assist);
    }
    @Override
    public int updateNonEmptyDriverdlById(Driverdl enti){
        return driverdlDao.updateNonEmptyDriverdlById(enti);
    }
    @Override
    public int updateNonEmptyDriverdl(Driverdl value, Assist assist){
        return driverdlDao.updateNonEmptyDriverdl(value,assist);
    }

    public DriverdlDao getDriverdlDao() {
        return this.driverdlDao;
    }

    public void setDriverdlDao(DriverdlDao driverdlDao) {
        this.driverdlDao = driverdlDao;
    }

}