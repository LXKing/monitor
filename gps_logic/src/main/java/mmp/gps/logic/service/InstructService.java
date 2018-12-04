package mmp.gps.logic.service;

import mmp.gps.domain.device.Device;
import mmp.gps.domain.instruct.*;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.dao.IInstructDao;
import mmp.gps.logic.net.InstructSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class InstructService {

    @Autowired
    private IInstructDao instructDao;


    @Transactional
    public void send(SendInstructRequest request) throws Exception {
        Device device = Devices.getCurrent().get(request.getDeviceNumber());
        if (device != null && device.getHost() != null && device.getHost().length() > 0) {
            InstructDto dto = new InstructDto();
            dto.command = request.getCommand();
            dto.number = request.getDeviceNumber();
            dto.name = request.getName();
            dto.parameter = request.getParams();
            dto.id = request.getSerialNumber();
            dto.userId = request.getUserId();
            dto.user = request.getUser();
            this.instructDao.create(dto);
            StringBuilder s = new StringBuilder();
            s.append(dto.number).append("\t").append(dto.id).append("\t").append(dto.command).append("\t").append(dto.parameter);
            System.out.println("s.tostring" + s.toString());
            InstructSender.send(device, s.toString());
        } else {
            throw new Exception("设备未上线！");
        }
    }

    public QueryInstructResponse query(QueryInstructRequest request) throws Exception {
        String number = request.getNumber();
        boolean total = false;
        int total1;
        if (number != null && !number.isEmpty()) {
            total1 = this.instructDao.queryByNumberPageCount(request.getNumber(), request.getUserId(), request.getStart(), request.getEnd());
        } else {
            total1 = this.instructDao.queryByUserPageCount(request.getUserId(), request.getStart(), request.getEnd());
        }

        QueryInstructResponse response = new QueryInstructResponse();
        response.setTotal(total1);
        if (total1 > 0) {
            List rows = null;
            if (number != null && !number.isEmpty()) {
                rows = this.instructDao.queryByNumberPageDetail(request.getNumber(), request.getUserId(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            } else {
                rows = this.instructDao.queryByUserPageDetail(request.getUserId(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            }

            Iterator var6 = rows.iterator();

            while (var6.hasNext()) {
                InstructDto dto = (InstructDto) var6.next();
                InstructInfo info = new InstructInfo();
                info.setCommand(dto.command);
                info.setId(dto.id);
                info.setName(dto.name);
                info.setNumber(dto.number);
                info.setParameter(dto.parameter);
                info.setReplyTime(dto.replyTime);
                info.setResult(dto.result);
                info.setSendTime(dto.sendTime);
                info.setStatus(dto.status);
                response.getInstructs().add(info);
            }
        }

        return response;
    }

    public FetchInstructResponse fetch(FetchInstructRequest request) throws Exception {
        InstructDto dto = this.instructDao.fetch(request.getId());
        FetchInstructResponse response = new FetchInstructResponse();
        response.setCommand(dto.command);
        response.setId(dto.id);
        response.setName(dto.name);
        response.setNumber(dto.number);
        response.setParameter(dto.parameter);
        response.setReplyTime(dto.replyTime);
        response.setResult(dto.result);
        response.setSendTime(dto.sendTime);
        response.setStatus(dto.status);
        return response;
    }
}
