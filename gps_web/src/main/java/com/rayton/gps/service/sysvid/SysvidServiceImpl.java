package com.rayton.gps.service.sysvid;

import com.rayton.gps.dao.baseinfo.sysvid.Sysvid;
import com.rayton.gps.dao.baseinfo.sysvid.SysvidDao;
import com.rayton.gps.util.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysvidServiceImpl implements SysvidService {
    @Autowired
    private SysvidDao sysvidDao;

    @Override
    public long getSysvidRowCount(Assist assist) {
        return sysvidDao.getSysvidRowCount(assist);
    }

    @Override
    public List<Sysvid> selectSysvid(Assist assist) {
        return sysvidDao.selectSysvid(assist);
    }

    @Override
    public Sysvid selectSysvidByObj(Sysvid obj) {
        return sysvidDao.selectSysvidByObj(obj);
    }

    @Override
    public Sysvid selectSysvidById(Integer id) {
        return sysvidDao.selectSysvidById(id);
    }

    @Override
    public int insertSysvid(Sysvid value) {
        return sysvidDao.insertSysvid(value);
    }

    @Override
    public int insertNonEmptySysvid(Sysvid value) {
        return sysvidDao.insertNonEmptySysvid(value);
    }

    @Override
    public int insertSysvidByBatch(List<Sysvid> value) {
        return sysvidDao.insertSysvidByBatch(value);
    }

    @Override
    public int deleteSysvidById(Integer id) {
        return sysvidDao.deleteSysvidById(id);
    }

    @Override
    public int deleteSysvid(Assist assist) {
        return sysvidDao.deleteSysvid(assist);
    }

    @Override
    public int updateSysvidById(Sysvid enti) {
        return sysvidDao.updateSysvidById(enti);
    }

    @Override
    public int updateSysvid(Sysvid value, Assist assist) {
        return sysvidDao.updateSysvid(value, assist);
    }

    @Override
    public int updateNonEmptySysvidById(Sysvid enti) {
        return sysvidDao.updateNonEmptySysvidById(enti);
    }

    @Override
    public int updateNonEmptySysvid(Sysvid value, Assist assist) {
        return sysvidDao.updateNonEmptySysvid(value, assist);
    }

    public SysvidDao getSysvidDao() {
        return this.sysvidDao;
    }

    public void setSysvidDao(SysvidDao sysvidDao) {
        this.sysvidDao = sysvidDao;
    }

}