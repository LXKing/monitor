package mmp.gps.monitor.dao;

import mmp.gps.domain.dictionary.DictionaryItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典数据访问接口
 */

@Repository
public interface IDictionaryDao {
    /**
     * 列出指定类型所有数据字典项
     *
     * @param kind 字典类型
     */
    List<DictionaryItem> list(int kind);

    /**
     * 创建数据字典项
     */
    void create(DictionaryItem dto);

    /**
     * 获取数据字典项
     */
    DictionaryItem fetch(long id);

    /**
     * 更新数据字典项
     */
    int update(DictionaryItem dto);

    /**
     * 删除数据字典项
     */
    void delete(long id);

    /**
     * 字典项是否已存在
     */
    boolean exist(String name, int kind);

    /**
     * 字典项是否已存在
     */
    boolean existOutId(String name, int kind, long id);

    /**
     * 移动字典项
     *
     * @param id  字典项唯一编号
     * @param pid 父字典项唯一编号 @
     */
    void move(long id, Long pid);


    int createDic(DictionaryItem dto);


    int getKindNum();
}
