package mmp.gps.logic.dao;

import mmp.gps.domain.firmware.FirmwareDto;
import mmp.gps.domain.firmware.FirmwareInfoDto;
import mmp.gps.domain.firmware.FirmwarePageDataDto;

import java.util.List;

/**
 * 固件信息数据访问接口
 */
public interface IFirmwareDao {
    /**
     * 查询固件信息
     *
     * @param factoryId 车厂id
     * @return 固件信息
     */
    FirmwareInfoDto query(short factoryId);

    /**
     * 获取包内容
     *
     * @param factoryId 车厂id
     * @param index     包序号
     * @return 数据包内容
     */
    FirmwarePageDataDto getContent(short factoryId, short index);

    int queryPageCount(String filter);

    /**
     * 按页查询固件信息
     *
     * @param filter    名称过滤
     * @param pageIndex 页序号
     * @param pageSize  页大小 @
     */
    List<FirmwareDto> queryPageDetail(String filter, int pageIndex, int pageSize);

    /**
     * 固件信息是否已存在
     *
     * @param factoryId 车厂id @
     */
    boolean exist(short factoryId);

    /**
     * 创建新的固件信息记录
     *
     * @
     */
    void create(FirmwareDto dto);

    /**
     * 固件信息是否已存在
     *
     * @param factoryId 车厂id
     * @param id        记录id @
     */
    boolean existOutId(short factoryId, String id);

    /**
     * 更新固件信息
     *
     * @
     */
    int update(FirmwareDto dto);

    /**
     * 删除固件信息
     *
     * @param id 记录id @
     */
    void delete(String id);

    /**
     * 获取固件信息
     *
     * @param id 记录id @
     */
    FirmwareDto fetch(String id);

    /**
     * 上传升级文件
     *
     * @param id        记录id
     * @param file      文件内容
     * @param checkCode 校验码 @
     */
    void upload(String id, byte[] file, byte checkCode, int fileSize);

}
