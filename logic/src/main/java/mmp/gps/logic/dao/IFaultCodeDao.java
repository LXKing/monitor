package mmp.gps.logic.dao;


import mmp.gps.common.util.Page;
import mmp.gps.domain.faultcode.FaultCodeDto;
import mmp.gps.domain.faultcode.FaultCodeInfoDto;

/**
 * 故障码数据库访问接口
 */
public interface IFaultCodeDao {
    /**
     * 检查是否已存在故障码
     *
     * @param modeId 故障码模式
     * @param code1  故障码字节1
     * @param code2  故障码字节2
     * @param code3  故障码字节3
     * @param id     记录Id
     */
    boolean exists(short modeId, short code1, short code2, short code3, String id) throws Exception;

    /**
     * 检查是否已存在故障码
     *
     * @param modeId 故障码模式
     * @param code1  故障码字节1
     * @param code2  故障码字节2
     * @param code3  故障码字节3
     */
    boolean exists(short modeId, short code1, short code2, short code3) throws Exception;

    /**
     * 按页查询故障码信息
     *
     * @param modeId   故障码模式
     * @param code1    故障码字节1
     * @param code2    故障码字节2
     * @param code3    故障码字节3
     * @param page     页序号
     * @param pageSize 页大小
     */
    Page<FaultCodeInfoDto> query(Short modeId, Short code1, Short code2, Short code3, int page, int pageSize) throws
            Exception;

    /**
     * 创建新的故障码信息
     */
    void create(FaultCodeDto dto) throws Exception;

    /**
     * 更新故障码信息
     */
    void update(FaultCodeDto dto) throws Exception;

    /**
     * 读取故障码信息
     *
     * @param id 记录id
     */
    FaultCodeDto fetch(String id) throws Exception;

    /**
     * 删除故障码信息
     *
     * @param id 记录id
     */
    void delete(String id) throws Exception;

    /**
     * 解析故障信息
     *
     * @param modeId 故障码模式
     * @param code1  故障码字节1
     * @param code2  故障码字节2
     * @param code3  故障码字节3
     * @throws Exception
     */
    FaultCodeDto parse(short modeId, short code1, short code2, short code3) throws Exception;

    /**
     * 导入行数据
     *
     * @throws Exception
     */
    void importRow(FaultCodeDto dto) throws Exception;

}
