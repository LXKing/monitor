package mmp.gps.monitor.service;

import mmp.gps.monitor.aop.Log;
import mmp.gps.domain.dictionary.DictionaryItem;
import mmp.gps.domain.dictionary.DictionaryItemInfo;
import mmp.gps.monitor.dao.IDictionaryDao;
import mmp.gps.common.util.Errors;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典服务类
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


        return dictionaries;
    }

    /**
     * 创建新的字典项
     */
    @RequiresPermissions("baseinfo.dictionary.create")
    @Log(name = "创建新的字典项")
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
    @RequiresPermissions("baseinfo.dictionary.update")
    @Log(name = "修改字典项")
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
    @RequiresPermissions("baseinfo.dictionary.delete")
    @Log(name = "删除字典项")
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

    @Transactional
    public int createDic(DictionaryItem dto) {
        int kind = dictionaryDao.getKindNum();
        dto.setKind(++kind);
        return dictionaryDao.createDic(dto);
    }
}
