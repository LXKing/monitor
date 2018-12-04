package mmp.gps.logic.service;

import mmp.gps.common.util.ObjectId;
import mmp.gps.domain.device.Device;
import mmp.gps.domain.feature.*;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.dao.IFeatureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class FeatureService {

    @Autowired
    private IFeatureDao dao;


    @Transactional
    public void create(CreateFeatureRequest request) throws Exception {
        FeatureDto dto = new FeatureDto();
        dto.id = ObjectId.next();
        dto.command = request.getCommand();
        dto.description = request.getDescription();
        dto.index = request.getIndex();
        dto.kind = request.getKind();
        dto.name = request.getName();
        dto.enabled = request.isEnabled();
        dto.twiceConfirm = request.isTwiceConfirm();
        dto.passwordConfirm = request.isPasswordConfirm();
        this.dao.create(dto);
    }

    @Transactional
    public void update(EditFeatureRequest request) throws Exception {
        FeatureDto dto = new FeatureDto();
        dto.id = request.getId();
        dto.command = request.getCommand();
        dto.description = request.getDescription();
        dto.index = request.getIndex();
        dto.kind = request.getKind();
        dto.name = request.getName();
        dto.enabled = request.isEnabled();
        dto.twiceConfirm = request.isTwiceConfirm();
        dto.passwordConfirm = request.isPasswordConfirm();
        dto.editTime = request.getEditTime();
        int rows = this.dao.update(dto);
        if (rows != 1) {
            throw new RuntimeException("数据已被其它用户修改！");
        }
    }

    public FetchFeatureResponse fetch(String id) throws Exception {
        FeatureDto dto = this.dao.fetch(id);
        FetchFeatureResponse response = new FetchFeatureResponse();
        response.setCommand(dto.command);
        response.setDescription(dto.description);
        response.setEditTime(dto.editTime);
        response.setEnabled(dto.enabled);
        response.setId(dto.id);
        response.setIndex(dto.index);
        response.setKind(dto.kind);
        response.setName(dto.name);
        response.setPasswordConfirm(dto.passwordConfirm);
        response.setTwiceConfirm(dto.twiceConfirm);
        response.setParams(dto.params);
        return response;
    }

    @Transactional
    public void delete(String id) throws Exception {
        this.dao.delteParameters(id);
        this.dao.delete(id);
    }

    public ListFeatureResponse list(int kind) throws Exception {
        ListFeatureResponse response = new ListFeatureResponse();
        List rows = this.dao.list(kind);
        response.setRows(rows.size());
        Iterator var4 = rows.iterator();

        while (var4.hasNext()) {
            FeatureDto dto = (FeatureDto) var4.next();
            FeatureInfo info = new FeatureInfo();
            info.setId(dto.id);
            info.setCommand(dto.command);
            info.setDescription(dto.description);
            info.setEnabled(dto.enabled);
            info.setIndex(dto.index);
            info.setName(dto.name);
            info.setParams(dto.params);
            response.getFeatures().add(info);
        }

        return response;
    }

    public MatchFeatureResponse match(String number, int protocol) throws Exception {
        Device device = Devices.getCurrent().get(number);
        if (device == null) {
            throw new Exception("设备不存在!");
        } else {
            MatchFeatureResponse response = new MatchFeatureResponse();
            System.out.println("protocol" + device.getProtocolKind());
            List list = this.dao.match(protocol);
            Iterator var6 = list.iterator();

            while (var6.hasNext()) {
                FeatureDto dto = (FeatureDto) var6.next();
                FeatureInfo info = new FeatureInfo();
                info.setId(dto.id);
                info.setCommand(dto.command);
                info.setDescription(dto.description);
                info.setEnabled(dto.enabled);
                info.setIndex(dto.index);
                info.setName(dto.name);
                info.setParams(dto.params);
                response.getFeatures().add(info);
            }

            return response;
        }
    }
}
