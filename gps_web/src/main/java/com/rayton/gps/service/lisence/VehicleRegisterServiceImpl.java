package com.rayton.gps.service.lisence;
import java.util.List;
import com.rayton.gps.dao.lisence.VehicleRegisterDao;
import com.rayton.gps.dao.lisence.VehicleRegister;
import com.rayton.gps.util.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class VehicleRegisterServiceImpl implements VehicleRegisterService{
    @Autowired
    private VehicleRegisterDao vehicleRegisterDao;
    @Override
    public long getVehicleRegisterRowCount(Assist assist){
        return vehicleRegisterDao.getVehicleRegisterRowCount(assist);
    }
    @Override
    public List<VehicleRegister> selectVehicleRegister(Assist assist){
        return vehicleRegisterDao.selectVehicleRegister(assist);
    }
    @Override
    public VehicleRegister selectVehicleRegisterByObj(VehicleRegister obj){
        return vehicleRegisterDao.selectVehicleRegisterByObj(obj);
    }
    @Override
    public VehicleRegister selectVehicleRegisterById(Integer id){
        return vehicleRegisterDao.selectVehicleRegisterById(id);
    }
    @Override
    public int insertVehicleRegister(VehicleRegister value){
        return vehicleRegisterDao.insertVehicleRegister(value);
    }
    @Override
    public int insertNonEmptyVehicleRegister(VehicleRegister value){
        return vehicleRegisterDao.insertNonEmptyVehicleRegister(value);
    }
    @Override
    public int insertVehicleRegisterByBatch(List<VehicleRegister> value){
        return vehicleRegisterDao.insertVehicleRegisterByBatch(value);
    }
    @Override
    public int deleteVehicleRegisterById(Integer id){
        return vehicleRegisterDao.deleteVehicleRegisterById(id);
    }
    @Override
    public int deleteVehicleRegister(Assist assist){
        return vehicleRegisterDao.deleteVehicleRegister(assist);
    }
    @Override
    public int updateVehicleRegisterById(VehicleRegister enti){
        return vehicleRegisterDao.updateVehicleRegisterById(enti);
    }
    @Override
    public int updateVehicleRegister(VehicleRegister value, Assist assist){
        return vehicleRegisterDao.updateVehicleRegister(value,assist);
    }
    @Override
    public int updateNonEmptyVehicleRegisterById(VehicleRegister enti){
        return vehicleRegisterDao.updateNonEmptyVehicleRegisterById(enti);
    }
    @Override
    public int updateNonEmptyVehicleRegister(VehicleRegister value, Assist assist){
        return vehicleRegisterDao.updateNonEmptyVehicleRegister(value,assist);
    }

    public VehicleRegisterDao getVehicleRegisterDao() {
        return this.vehicleRegisterDao;
    }

    public void setVehicleRegisterDao(VehicleRegisterDao vehicleRegisterDao) {
        this.vehicleRegisterDao = vehicleRegisterDao;
    }

}