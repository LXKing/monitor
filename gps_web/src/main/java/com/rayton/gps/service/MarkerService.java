package com.rayton.gps.service;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.baseinfo.MarkerFileInfo;
import com.rayton.gps.util.IconUtil;
import com.rayton.gps.util.enums.MarkerType;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class MarkerService {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private IconService iconService;

    public List<MarkerFileInfo> getMarkerFiles(String path) {
        // File file = new File(path);

        // List<File> list = Arrays.asList(new File(path).listFiles());
        // File[] list = file.listFiles();

        return iconService.listByType(MarkerType.CAR.getType());

        // return Arrays.asList(new File(path).listFiles()).stream().filter(File::isFile).map(file -> {
        //     MarkerFileInfo info = new MarkerFileInfo();
        //     info.setName(file.getName());
        //     // Long time = file.lastModified();
        //     info.setTime(new Date(file.lastModified()));
        //     return info;
        // }).collect(Collectors.toList());

        // List<MarkerFileInfo> icons = new ArrayList<>();
        // for (File icon : list) {
        //     if (icon.isFile()) {
        //         MarkerFileInfo info = new MarkerFileInfo();
        //         info.setName(icon.getName());
        //         Long time = icon.lastModified();
        //         info.setTime(new Date(time));
        //         icons.add(info);
        //     }
        // }
        // for (int i = 0; i < list.length; i++) {
        //     File icon = list[i];
        //     if (icon.isFile()) {
        //         MarkerFileInfo info = new MarkerFileInfo();
        //         info.setName(icon.getName());
        //         Long time = icon.lastModified();
        //         info.setTime(new Date(time));
        //         icons.add(info);
        //     }
        // }

        // return icons;
    }


    @RequiresPermissions("baseinfo.marker.remove")
    @Log(name = "删除车辆图标")
    public boolean deleteMarkerFile(String path, String name) throws Exception {
        boolean ok = new File(path, name).delete();
        iconService.delete(name);
        return ok;
        // File dest = new File(path, name);
        // if (dest.delete()) {
        //     vehicleService.resetMarker(name, "00.png");
        //     return true;
        // } else
        //     return false;
    }

    @RequiresPermissions("baseinfo.marker.save")
    @Log(name = "上传车辆图标")
    public void saveMarkerFile(MultipartFile file, String path) throws Exception {
        // String fileName = file.getOriginalFilename().toLowerCase();

        String fileName = IconUtil.uploadPic(path, file);

        MarkerFileInfo markerFileInfo = new MarkerFileInfo();
        markerFileInfo.setName(fileName);
        markerFileInfo.setType(MarkerType.CAR.getType());
        // markerFileInfo.setTime(new Date());
        iconService.insert(markerFileInfo);

        // // 检查是否为jpg、png、gif文件
        // if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png") && !fileName.endsWith(".gif")) {
        //     throw new Exception("文件必须为jpg、png、gif类型图像！");
        // }
        //
        // File dest = new File(path, fileName);
        // file.transferTo(dest);
    }
}
