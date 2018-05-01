package com.rayton.gps.service;
import java.util.List;
import com.rayton.gps.dao.ViewportDao;
import com.rayton.gps.dao.Viewport;
import com.rayton.gps.util.Assist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ViewportServiceImpl implements ViewportService{
    @Autowired
    private ViewportDao viewportDao;
    @Override
    public long getViewportRowCount(Assist assist){
        return viewportDao.getViewportRowCount(assist);
    }
    @Override
    public List<Viewport> selectViewport(Assist assist){
        return viewportDao.selectViewport(assist);
    }
    @Override
    public Viewport selectViewportByObj(Viewport obj){
        return viewportDao.selectViewportByObj(obj);
    }
    @Override
    public Viewport selectViewportById(Integer id){
        return viewportDao.selectViewportById(id);
    }
    @Override
    public int insertViewport(Viewport value){
        return viewportDao.insertViewport(value);
    }
    @Override
    public int insertNonEmptyViewport(Viewport value){
        return viewportDao.insertNonEmptyViewport(value);
    }
    @Override
    public int insertViewportByBatch(List<Viewport> value){
        return viewportDao.insertViewportByBatch(value);
    }
    @Override
    public int deleteViewportById(Integer id){
        return viewportDao.deleteViewportById(id);
    }
    @Override
    public int deleteViewport(Assist assist){
        return viewportDao.deleteViewport(assist);
    }
    @Override
    public int updateViewportById(Viewport enti){
        return viewportDao.updateViewportById(enti);
    }
    @Override
    public int updateViewport(Viewport value, Assist assist){
        return viewportDao.updateViewport(value,assist);
    }
    @Override
    public int updateNonEmptyViewportById(Viewport enti){
        return viewportDao.updateNonEmptyViewportById(enti);
    }
    @Override
    public int updateNonEmptyViewport(Viewport value, Assist assist){
        return viewportDao.updateNonEmptyViewport(value,assist);
    }

    public ViewportDao getViewportDao() {
        return this.viewportDao;
    }

    public void setViewportDao(ViewportDao viewportDao) {
        this.viewportDao = viewportDao;
    }

}