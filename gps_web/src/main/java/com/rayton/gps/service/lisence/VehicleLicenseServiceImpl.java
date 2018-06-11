package com.rayton.gps.service.lisence;

import com.rayton.gps.dao.lisence.VehicleLicense;
import com.rayton.gps.dao.lisence.VehicleLicenseDao;
import com.rayton.gps.util.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleLicenseServiceImpl implements VehicleLicenseService {
    @Autowired
    private VehicleLicenseDao vehicleLicenseDao;

    @Override
    public long getVehicleLicenseRowCount(Assist assist) {
        return vehicleLicenseDao.getVehicleLicenseRowCount(assist);
    }

    @Override
    public List<VehicleLicense> selectVehicleLicense(Assist assist) {
        return vehicleLicenseDao.selectVehicleLicense(assist);
    }

    @Override
    public VehicleLicense selectVehicleLicenseByObj(VehicleLicense obj) {
        return vehicleLicenseDao.selectVehicleLicenseByObj(obj);
    }

    @Override
    public VehicleLicense selectVehicleLicenseById(Integer id) {
        return vehicleLicenseDao.selectVehicleLicenseById(id);
    }

    @Override
    public int insertVehicleLicense(VehicleLicense value) {
        return vehicleLicenseDao.insertVehicleLicense(value);
    }

    @Override
    public int insertNonEmptyVehicleLicense(VehicleLicense value) {
        return vehicleLicenseDao.insertNonEmptyVehicleLicense(value);
    }

    @Override
    public int insertVehicleLicenseByBatch(List<VehicleLicense> value) {
        return vehicleLicenseDao.insertVehicleLicenseByBatch(value);
    }

    @Override
    public int deleteVehicleLicenseById(Integer id) {
        return vehicleLicenseDao.deleteVehicleLicenseById(id);
    }

    @Override
    public int deleteVehicleLicense(Assist assist) {
        return vehicleLicenseDao.deleteVehicleLicense(assist);
    }

    @Override
    public int updateVehicleLicenseById(VehicleLicense enti) {
        return vehicleLicenseDao.updateVehicleLicenseById(enti);
    }

    @Override
    public int updateVehicleLicense(VehicleLicense value, Assist assist) {
        return vehicleLicenseDao.updateVehicleLicense(value, assist);
    }

    @Override
    public int updateNonEmptyVehicleLicenseById(VehicleLicense enti) {
        return vehicleLicenseDao.updateNonEmptyVehicleLicenseById(enti);
    }

    @Override
    public int updateNonEmptyVehicleLicense(VehicleLicense value, Assist assist) {
        return vehicleLicenseDao.updateNonEmptyVehicleLicense(value, assist);
    }

    public VehicleLicenseDao getVehicleLicenseDao() {
        return this.vehicleLicenseDao;
    }

    public void setVehicleLicenseDao(VehicleLicenseDao vehicleLicenseDao) {
        this.vehicleLicenseDao = vehicleLicenseDao;
    }

}