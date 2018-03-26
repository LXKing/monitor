package com.rayton.gps.service;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.dao.baseinfo.dictionary.DictionaryItem;
import com.rayton.gps.dao.baseinfo.dictionary.DictionaryItemInfo;
import com.rayton.gps.dao.baseinfo.dictionary.IDictionaryDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典服务类
 *
 * @author yangzs
 */
@Service
public class DictionaryService {
    @Autowired
    private IDictionaryDao dictionaryDao;

    /**
     * 读取指定类型字典
     *
     * @param kind 字典类型
     */
    public List<DictionaryItemInfo> list(int kind) {
        List<DictionaryItem> list = dictionaryDao.list(kind);
        List<DictionaryItemInfo> dictionaries = new ArrayList<DictionaryItemInfo>(list.size());
        list.forEach(dictionaryItemDto -> {
            DictionaryItemInfo info = new DictionaryItemInfo();
            BeanUtils.copyProperties(dictionaryItemDto, info);
            dictionaries.add(info);
        });
        // for (DictionaryItemDto dto : list) {
        //     DictionaryItemInfo info = new DictionaryItemInfo();
        //     info.setCode(dto.code);
        //     info.setId(dto.id);
        //     info.setIndex(dto.index);
        //     info.setName(dto.name);
        //     info.setPid(dto.pid);
        //     dictionarys.add(info);
        // }
        return dictionaries;
    }

    /**
     * 创建新的字典项
     */
    @ServiceMethod(id = "baseinfo.dictionary.create", pid = "baseinfo.dictionary", name = "创建新的字典项")
    @Transactional
    public void create(DictionaryItem item) {

        dictionaryDao.create(item);
    }

    /**
     * 读取字典项资料
     *
     * @param id 字典项唯一编号
     */
    public DictionaryItem fetch(long id) {
        DictionaryItem item = dictionaryDao.fetch(id);


        return item;
    }

    /**
     * 更新字典项资料
     */
    @ServiceMethod(id = "baseinfo.dictionary.update", pid = "baseinfo.dictionary", name = "修改字典项")
    @Transactional
    public void update(DictionaryItem item) {


        int rows = dictionaryDao.update(item);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }

    /**
     * 删除字典项
     *
     * @param id 字典项唯一编号
     */
    @ServiceMethod(id = "baseinfo.dictionary.delete", pid = "baseinfo.dictionary", name = "删除字典项")
    @Transactional
    public void delete(long id) {
        dictionaryDao.delete(id);
    }

    /**
     * 是否已存在字典项
     *
     * @param name 字典项名称
     * @param kind 字典类型
     */
    public boolean exist(String name, int kind) {
        return dictionaryDao.exist(name, kind);
    }

    /**
     * 是否已存在字典项
     *
     * @param name 字典项名称
     * @param kind 字典类型
     * @param id   字典项唯一编号
     */
    public boolean exist(String name, int kind, long id) {
        return dictionaryDao.existOutId(name, kind, id);
    }

    /**
     * 移动字典项
     *
     * @param id  字典项唯一编号
     * @param pid 父字典项唯一编号
     */
    @Transactional
    public void move(long id, Long pid) {
        dictionaryDao.move(id, pid);
    }
}
