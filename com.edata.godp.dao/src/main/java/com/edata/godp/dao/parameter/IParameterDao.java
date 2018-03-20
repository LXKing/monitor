package com.edata.godp.dao.parameter;

import java.util.List;

/**
 * 功能参数数据访问接口
 *
 * @author 生
 */
public interface IParameterDao {
    /**
     * 递增功能参数
     */
    void increaseFeatureParms(String featureId);

    /**
     * 递减功能参数
     */
    void decreaseFeatureParms(String featureId);

    /**
     * 创建
     */
    void create(ParameterDto dto);

    /**
     * 更新
     */
    int update(ParameterDto dto);

    /**
     * 读取
     */
    ParameterDto fetch(String id);

    /**
     * 读取父节点ID
     */
    ParentInfoDto parent(String parameterId);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 查询
     */
    List<ParameterDto> list(String featureId);

    /**
     * 是否有子项
     */
    boolean hasSub(String id);

    /**
     * 移动到父节点
     *
     * @
     */
    void move(String pid, String id);

    /**
     * 加载功能参数列表
     */
    List<ParameterDto> load(String pid, String featureId);
}
