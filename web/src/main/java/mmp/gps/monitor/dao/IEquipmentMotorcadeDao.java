package mmp.gps.monitor.dao;


import mmp.gps.domain.equipmentMotorcade.EquipmentMotorcade;
import org.springframework.stereotype.Repository;

/**
 * 设备车队服务接口
 */

@Repository
public interface IEquipmentMotorcadeDao {

    int insertequMotorcade(EquipmentMotorcade dto);
}
