package mmp.gps.logic.service;

import mmp.gps.common.util.ObjectId;
import mmp.gps.domain.firmware.*;
import mmp.gps.logic.dao.IFirmwareDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class FirmwareService {

    @Autowired
    private IFirmwareDao firmwareDao;


    public QueryFirmwareInfoResponse query(short factoryId) {
        QueryFirmwareInfoResponse info = new QueryFirmwareInfoResponse();
        FirmwareInfoDto dto = this.firmwareDao.query(factoryId);
        info.setSize(dto.fileLength);
        info.setCheckCode(dto.fileXor);
        info.setVersion(dto.version);
        short pages = (short) (dto.fileLength / 1024);
        if (dto.fileLength % 1024 != 0) {
            ++pages;
        }

        info.setPages(pages);
        return info;
    }

    public DownloadPageDataResponse getContent(short factoryId, short index) {
        DownloadPageDataResponse page = new DownloadPageDataResponse();
        FirmwarePageDataDto dto = this.firmwareDao.getContent(factoryId, index);
        byte[] data = dto.content;
        int total = data.length;
        int start = (index - 1) * 1024;
        int left = total - start;
        if (left >= 1024) {
            dto.content = Arrays.copyOfRange(data, start, start + 1024);
        } else {
            dto.content = Arrays.copyOfRange(data, start, total - 1);
        }

        page.setContent(dto.content);
        page.setIndex(index);
        return page;
    }

    public ListFirmwareInfoResponse list(String filter, int pageIndex, int pageSize) {
        ListFirmwareInfoResponse response = new ListFirmwareInfoResponse();
        int total = this.firmwareDao.queryPageCount(filter);
        response.setRows(total);
        if (total > 0) {
            List rows = this.firmwareDao.queryPageDetail(filter, (pageIndex - 1) * pageIndex, pageIndex);
            Iterator var7 = rows.iterator();

            while (var7.hasNext()) {
                FirmwareDto dto = (FirmwareDto) var7.next();
                FirmwareInfo info = new FirmwareInfo();
                info.setDescription(dto.description);
                info.setFactoryId(dto.factoryId);
                info.setId(dto.id);
                info.setName(dto.name);
                info.setUploadTime(dto.uploadTime);
                info.setVersion(dto.version);
                response.getFirmwares().add(info);
            }
        }

        return response;
    }

    @Transactional
    public void create(CreateFirmwareRequest request) {
        if (this.firmwareDao.exist(request.getFactoryId())) {
            throw new RuntimeException("固件信息已存在！");
        } else {
            FirmwareDto dto = new FirmwareDto();
            dto.id = ObjectId.next();
            dto.factoryId = request.getFactoryId();
            dto.description = request.getDescription();
            dto.name = request.getName();
            dto.version = request.getVersion();
            this.firmwareDao.create(dto);
        }
    }

    @Transactional
    public void update(EditFirmwareRequest request) {
        if (this.firmwareDao.existOutId(request.getFactoryId(), request.getId())) {
            throw new RuntimeException("固件信息已存在！");
        } else {
            FirmwareDto dto = new FirmwareDto();
            dto.id = request.getId();
            dto.factoryId = request.getFactoryId();
            dto.description = request.getDescription();
            dto.name = request.getName();
            dto.version = request.getVersion();
            dto.editTime = request.getEditTime();
            int rows = this.firmwareDao.update(dto);
            if (rows != 1) {
                throw new RuntimeException("数据已被其它用户修改！");
            }
        }
    }

    @Transactional
    public void delete(String id) {
        this.firmwareDao.delete(id);
    }

    public FetchFirmwareResponse fetch(String id) {
        FirmwareDto dto = this.firmwareDao.fetch(id);
        FetchFirmwareResponse response = new FetchFirmwareResponse();
        response.setDescription(dto.description);
        response.setEditTime(dto.editTime);
        response.setFactoryId(dto.factoryId);
        response.setId(dto.id);
        response.setName(dto.name);
        response.setVersion(dto.version);
        return response;
    }

    @Transactional
    public void upload(String id, byte[] file) {
        byte checkCode = 0;

        for (int i = 0; i < file.length; ++i) {
            checkCode ^= file[i];
        }

        this.firmwareDao.upload(id, file, checkCode, file.length);
    }

    public boolean exists(short factoryId, String id, boolean checkId) {
        return checkId ? this.firmwareDao.existOutId(factoryId, id) : this.firmwareDao.exist(factoryId);
    }
}
