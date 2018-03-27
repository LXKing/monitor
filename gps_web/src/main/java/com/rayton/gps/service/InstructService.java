package com.rayton.gps.service;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.cache.AssociationCache;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.device.IDeviceDao;
import com.rayton.gps.dao.cache.UserMonitorTarget;
import com.rayton.gps.dao.cache.association.MonitorTarget;
import com.rayton.gps.dao.instruct.*;
import com.rayton.gps.dao.security.ISecurityDao;
import com.rayton.gps.godp.IGodpDao;
import com.rayton.gps.util.JsonMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class InstructService {
    @Autowired
    private IGodpDao godpDao;
    @Autowired
    private ISecurityDao securityDao;
    @Autowired
    private IDeviceDao deviceDao;

    public List<FeatureInfo> features(String number) throws Exception {
        List<FeatureDto> list = godpDao.loadFeatures(number);

        List<FeatureInfo> result = new ArrayList<FeatureInfo>();
        for (FeatureDto dto : list) {
            FeatureInfo info = new FeatureInfo();
            info.setCommand(dto.command);
            info.setDescription(dto.description);
            info.setId(dto.id);
            info.setIndex(dto.index);
            info.setName(dto.name);
            info.setParams(dto.params);

            result.add(info);
        }

        return result;
    }

    public List<ParameterInfo> parameters(String pid, String featureId) throws Exception {
        List<ParameterDto> list = godpDao.loadParameter(pid, featureId);
        List<ParameterInfo> result = new ArrayList<ParameterInfo>();
        for (ParameterDto dto : list) {
            ParameterInfo info = new ParameterInfo();
            info.setId(dto.id);
            info.setPid(dto.pid);
            info.setFeatureId(dto.featureId);
            info.setIndex(dto.index);
            info.setName(dto.name);
            info.setLabel(dto.label);
            info.setType(dto.type);
            info.setSelectValue(dto.selectValue);
            info.setDictionaryKey(dto.dictionaryKey);
            info.setSwitchBit(dto.switchBit);
            info.setRows(dto.rows);
            info.setColumns(dto.columns);
            info.setDefaultValue(dto.defaultValue);
            info.setDescription(dto.description);

            result.add(info);
        }

        return result;
    }


    @RequiresPermissions("center.locate.instruct")
    @ServiceMethod(id = "center.locate.instruct", pid = "center.locate", name = "指令下发")
    public InstructInfo send(String userId, String serialNumber, String deviceNumber, String unid, String user,
                             String command, String name, String params, String confirm) throws Exception {
        if (confirm != null && confirm.length() > 0) {
            String pwd = securityDao.getPasswordByUnid(unid);
            if (!pwd.equals(confirm))
                throw new Exception("密码错误！");
        }

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Map<String, MonitorTarget> devices = target.getDevices();
        MonitorTarget device = devices.get(deviceNumber);

        InstructInfo info = new InstructInfo();
        info.setSendTime(new Date());
        info.setCommand(command);
        info.setDeviceNumber(deviceNumber);
        info.setId(serialNumber);
        info.setName(name);
        info.setParameter(params);
        info.setPlateNumber(device == null ? deviceNumber : device.getName());
        info.setResult("发送中......");

        CommandDto dto = new CommandDto();
        dto.serialNumber = serialNumber;
        dto.deviceNumber = deviceNumber;
        dto.unid = unid;
        dto.user = user;
        dto.command = command;
        dto.name = name;
        dto.params = params;

        godpDao.sendInstruct(dto);

        if (dto.command.equals("8301")) {
            updateDeviceEventSetting(dto);
        }

        return info;
    }

    public void send(String serialNumber, String deviceNumber, String unid, String user, String command, String name,
                     String params) throws Exception {
        InstructInfo info = new InstructInfo();
        info.setSendTime(new Date());
        info.setCommand(command);
        info.setDeviceNumber(deviceNumber);
        info.setId(serialNumber);
        info.setName(name);
        info.setParameter(params);
        info.setResult("发送中......");

        CommandDto dto = new CommandDto();
        dto.serialNumber = serialNumber;
        dto.deviceNumber = deviceNumber;
        dto.unid = unid;
        dto.user = user;
        dto.command = command;
        dto.name = name;
        dto.params = params;

        godpDao.sendInstruct(dto);
    }

    /**
     * 更新设置事件设备，与终端同步，当终端发生事件报告时可以解析事件内容
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void updateDeviceEventSetting(CommandDto dto) throws Exception {
        String params = dto.params;
        Map<String, Object> map = JsonMapper.toObject(params, Map.class);
        // 类型
        String p = map.get("type").toString();
        byte type = Byte.parseByte(p);
        // 0:删除终端现有所有事件,该命令后不带后继字节;
        if (type == 0) {
            deviceDao.clearEventMenu(dto.deviceNumber);
            return;
        }
        // 1:更新事件;
        // 2:追加事件;
        // 3:修改事件;
        // 4:删除特定几项事件,之后事件项中无需带事件内容
        List<Map<String, String>> items = (List<Map<String, String>>) map.get("events");
        for (Map<String, String> item : items) {
            p = item.get("id");
            short eventId = Short.parseShort(p);

            p = item.get("content");
            String content = p;

            switch (type) {
                case 1:
                case 2:
                case 3:
                    deviceDao.resetEventNumber(dto.deviceNumber, eventId, content);
                    break;
                case 4:
                    deviceDao.removeEventNumber(dto.deviceNumber, eventId);
                    break;
            }
        }
    }

    public Page<InstructInfo> query(String deviceNumber, String unid, Date start, Date end, int pageIndex, int
            pageSize) throws Exception {

        Page<InstructDto> query = godpDao.queryInstruct(deviceNumber, unid, start, end, pageIndex, pageSize);

        Page<InstructInfo> page = new Page<InstructInfo>();
        page.total = query.total;

        if (query.rows != null)
            for (InstructDto dto : query.rows) {
                InstructInfo info = new InstructInfo();

                info.setPlateNumber(deviceDao.getPlateNumber(dto.number));
                info.setCommand(dto.command);
                info.setId(dto.id);
                info.setName(dto.name);
                info.setDeviceNumber(dto.number);
                info.setParameter(dto.parameter);
                info.setReplyTime(dto.replyTime);
                info.setResult(dto.result);
                info.setSendTime(dto.sendTime);
                info.setStatus(dto.status);

                page.rows.add(info);
            }

        return page;
    }

    public InstructInfo fetch(String id) throws Exception {
        InstructDto dto = godpDao.fetchInstruct(id);
        InstructInfo info = new InstructInfo();
        info.setCommand(dto.command);
        info.setId(dto.id);
        info.setName(dto.name);
        info.setDeviceNumber(dto.number);
        info.setParameter(dto.parameter);
        info.setReplyTime(dto.replyTime);
        info.setResult(dto.result);
        info.setSendTime(dto.sendTime);
        info.setStatus(dto.status);

        return info;
    }
}
