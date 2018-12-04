package mmp.gps.logic.service;

import mmp.gps.common.util.ObjectId;
import mmp.gps.domain.parameter.*;
import mmp.gps.logic.dao.IParameterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ParameterService {

    @Autowired
    private IParameterDao parameterDao;


    @Transactional
    public void create(CreateParameterRequest request) {
        ParameterDto dto = new ParameterDto();
        dto.id = ObjectId.next();
        dto.columns = request.getColumns();
        dto.defaultValue = request.getDefaultValue();
        dto.description = request.getDescription();
        dto.dictionaryKey = request.getDictionaryKey();
        dto.featureId = request.getFeatureId();
        dto.index = request.getIndex();
        dto.name = request.getName();
        dto.label = request.getLabel();
        dto.pid = request.getPid();
        dto.rows = request.getRows();
        dto.selectValue = request.getSelectValue();
        dto.switchBit = request.getSwitchBit();
        dto.type = request.getType();
        this.parameterDao.create(dto);
        if (dto.pid == null || dto.pid.isEmpty()) {
            this.parameterDao.increaseFeatureParms(dto.featureId);
        }

    }

    @Transactional
    public void update(EditParameterRequest request) {
        ParameterDto dto = new ParameterDto();
        dto.id = request.getId();
        dto.columns = request.getColumns();
        dto.defaultValue = request.getDefaultValue();
        dto.description = request.getDescription();
        dto.dictionaryKey = request.getDictionaryKey();
        dto.featureId = request.getFeatureId();
        dto.index = request.getIndex();
        dto.name = request.getName();
        dto.label = request.getLabel();
        dto.pid = request.getPid();
        dto.rows = request.getRows();
        dto.selectValue = request.getSelectValue();
        dto.switchBit = request.getSwitchBit();
        dto.type = request.getType();
        dto.editTime = request.getEditTime();
        int rows = this.parameterDao.update(dto);
        if (rows != 1) {
            throw new RuntimeException("数据已被其它用户修改！");
        }
    }

    public FetchParameterResponse fetch(String id) {
        FetchParameterResponse response = new FetchParameterResponse();
        ParameterDto dto = this.parameterDao.fetch(id);
        response.setColumns(dto.columns);
        response.setDefaultValue(dto.defaultValue);
        response.setDescription(dto.description);
        response.setDictionaryKey(dto.dictionaryKey);
        response.setEditTime(dto.editTime);
        response.setFeatureId(dto.featureId);
        response.setId(dto.id);
        response.setIndex(dto.index);
        response.setName(dto.name);
        response.setLabel(dto.label);
        response.setPid(dto.pid);
        response.setRows(dto.rows);
        response.setSelectValue(dto.selectValue);
        response.setSwitchBit(dto.switchBit);
        response.setType(dto.type);
        return response;
    }

    @Transactional
    public void delete(String id) {
        if (this.parameterDao.hasSub(id)) {
            throw new RuntimeException("节点不为空！");
        } else {
            ParentInfoDto dto = this.parameterDao.parent(id);
            if (dto.pid == null || dto.pid.isEmpty()) {
                this.parameterDao.decreaseFeatureParms(dto.featureId);
            }

            this.parameterDao.delete(id);
        }
    }

    public ListParameterResponse list(String featureId) {
        ListParameterResponse response = new ListParameterResponse();
        List rows = this.parameterDao.list(featureId);
        response.setTotal(rows.size());
        Iterator var4 = rows.iterator();

        while (var4.hasNext()) {
            ParameterDto dto = (ParameterDto) var4.next();
            ParameterInfo info = new ParameterInfo();
            info.setDescription(dto.description);
            info.setFeatureId(dto.featureId);
            info.setId(dto.id);
            info.setIndex(dto.index);
            info.setName(dto.name);
            info.setLabel(dto.label);
            info.setPid(dto.pid);
            info.setType(dto.type);
            response.getParameters().add(info);
        }

        return response;
    }

    @Transactional
    public void move(String id, String pid) {
        ParameterDto sub = this.parameterDao.fetch(id);
        ParameterDto parent = null;
        if (pid != null && pid.length() > 0) {
            parent = this.parameterDao.fetch(pid);
        }

        if (sub.pid != pid) {
            if (parent == null) {
                this.parameterDao.increaseFeatureParms(sub.featureId);
            }

            if (sub.pid == null || sub.pid.isEmpty()) {
                this.parameterDao.decreaseFeatureParms(sub.featureId);
            }

            this.parameterDao.move(pid, id);
        }
    }

    public LoadParametersResponse load(String pid, String featureId) {
        LoadParametersResponse response = new LoadParametersResponse();
        List parameters = this.parameterDao.load(pid, featureId);
        ArrayList list = new ArrayList();
        Iterator var6 = parameters.iterator();

        while (var6.hasNext()) {
            ParameterDto dto = (ParameterDto) var6.next();
            LoadParameterInfo info = new LoadParameterInfo();
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
            list.add(info);
        }

        response.setParameters(list);
        return response;
    }

    @Transactional
    public void copy(String id) {
        ParameterDto dto = this.parameterDao.fetch(id);
        List parameters = this.parameterDao.load(id, dto.featureId);
        dto.id = ObjectId.next();
        ++dto.index;
        dto.label = "复制" + dto.label;
        this.parameterDao.create(dto);
        if (dto.pid == null || dto.pid.isEmpty()) {
            this.parameterDao.increaseFeatureParms(dto.featureId);
        }

        if (parameters != null) {
            Iterator var4 = parameters.iterator();

            while (var4.hasNext()) {
                ParameterDto parameterDto = (ParameterDto) var4.next();
                parameterDto.pid = dto.id;
                this.parameterDao.create(parameterDto);
            }
        }

    }
}
