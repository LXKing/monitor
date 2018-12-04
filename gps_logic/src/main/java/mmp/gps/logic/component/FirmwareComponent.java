package mmp.gps.logic.component;


import mmp.gps.domain.exist.ExistRequest;
import mmp.gps.domain.exist.ExistResponse;
import mmp.gps.domain.firmware.*;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.FirmwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class FirmwareComponent {

    @Autowired
    private FirmwareService firmwareService;


    public FirmwareComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "firmware.query",
            allowAnoumous = true,
            description = "查询固件升级文件信息"
    )
    public QueryFirmwareInfoResponse query(QueryFirmwareInfoRequest request) throws Exception {
        return this.firmwareService.query(request.getFactoryId());
    }

    @ServiceMethod(
            value = "firmware.download",
            allowAnoumous = true,
            description = "下载固件升级文件包数据"
    )
    public DownloadPageDataResponse download(DownloadPageDataRequest request) throws Exception {
        return this.firmwareService.getContent(request.getFactoryId(), request.getPageIndex());
    }

    @ServiceMethod(
            value = "firmware.list",
            description = "查询固件升级文件信息"
    )
    public ListFirmwareInfoResponse list(ListFirmwareInfoRequest request) throws Exception {
        return this.firmwareService.list(request.getFilter(), request.getPage(), request.getPageSize());
    }

    @ServiceMethod(
            value = "firmware.create",
            description = "创建新的固件升级文件信息"
    )
    public void create(CreateFirmwareRequest request) throws Exception {
        this.firmwareService.create(request);
    }

    @ServiceMethod(
            value = "firmware.update",
            description = "更新固件升级文件信息"
    )
    public void update(EditFirmwareRequest request) throws Exception {
        this.firmwareService.update(request);
    }

    @ServiceMethod(
            value = "firmware.delete",
            description = "删除固件升级文件信息"
    )
    public boolean delete(DeleteFirmwareRequest request) throws Exception {
        this.firmwareService.delete(request.getId());
        return true;
    }

    @ServiceMethod(
            value = "firmware.fetch",
            description = "读取固件升级文件信息"
    )
    public FetchFirmwareResponse fetch(FetchFirmwareRequest request) throws Exception {
        return this.firmwareService.fetch(request.getId());
    }

    @ServiceMethod(
            value = "firmware.upload",
            description = "上传固件升级文件内容"
    )
    public boolean upload(UploadFirmwareRequest request) throws Exception {
        this.firmwareService.upload(request.getId(), request.getFile());
        return true;
    }

    @ServiceMethod(
            value = "firmware.exists",
            allowAnoumous = true,
            description = "检查是否已存在固件升级文件信息"
    )
    public ExistResponse exists(ExistRequest request) throws NumberFormatException, Exception {
        ExistResponse response = new ExistResponse();
        response.setExistent(this.firmwareService.exists(Short.parseShort(request.getKey()), request.getId(), request.isCheckId()));
        return response;
    }
}
