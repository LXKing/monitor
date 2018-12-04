package mmp.gps.logic.codec.jtt808;

import mmp.gps.common.util.DateFormats;
import mmp.gps.common.util.Tuple;
import mmp.gps.domain.drivingRecorder.*;
import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.InstructResultDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.push.InstructResultMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.logic.service.DrivingRecorderService;
import mmp.gps.protocol.gbt19056.Gbt19056Packet;
import mmp.gps.protocol.gbt19056.body.*;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.DrivingRecordInquiryReplyBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class DrivingRecordInquiryReplyHandler implements ICommandHandler {

    @Autowired
    private DrivingRecorderService drivingRecorderService;
    private Object[] keys = new Object[]{Integer.valueOf(1792)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        DrivingRecordInquiryReplyBody body = new DrivingRecordInquiryReplyBody();
        JTT808Packet packet = new JTT808Packet(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        InstructResultDto result = new InstructResultDto();
        result.number = raw.getDn();
        result.id = raw.getMsn();
        result.result = "设备：成功";
        bulk.add(result);
        short command = body.getCommand();
        Gbt19056Packet gbt;
        ArrayList list;
        Iterator sb;
        Date start;
        Date end;
        Tuple info1;
        String list1;
        StringBuilder list2;
        String sb3;
        long end5;
        switch (command) {
            case 0:
                ReadVersionReplyBody message15 = new ReadVersionReplyBody();
                gbt = new Gbt19056Packet(message15);
                gbt.from(body.getData());
                list1 = Integer.toHexString(message15.getYear());
                sb3 = Integer.toHexString(message15.getEdit());
                String info8 = list1 + "." + sb3;
                result.result = "执行标准：" + info8;
                this.drivingRecorderService.updateVersion(raw.getDn(), info8);
                break;
            case 1:
                ReadCurrentDriverReplyBody message14 = new ReadCurrentDriverReplyBody();
                gbt = new Gbt19056Packet(message14);
                gbt.from(body.getData());
                list1 = message14.getLicense();
                result.result = "机动车驾驶证号码：" + list1;
                this.drivingRecorderService.updateLicense(raw.getDn(), list1);
                break;
            case 2:
                DrivingTime message13 = new DrivingTime();
                gbt = new Gbt19056Packet(message13);
                gbt.from(body.getData());
                result.result = "时间：" + message13.toString();
                break;
            case 3:
                ReadMileageReplyBody message12 = new ReadMileageReplyBody();
                gbt = new Gbt19056Packet(message12);
                gbt.from(body.getData());
                DecimalFormat list3 = new DecimalFormat("#0.0");
                sb3 = message12.getCurrentTime().toString();
                long info7 = Long.parseLong(Long.toHexString(message12.getInitialMileage()));
                end5 = Long.parseLong(Long.toHexString(message12.getTotalMileage()));
                StringBuilder sb4 = new StringBuilder();
                sb4.append("时间:").append(sb3.toString()).append("\r\n");
                sb4.append("初始里程:").append(list3.format((double) info7 * 0.1D)).append("\r\n");
                sb4.append("累计行驶里程:").append(list3.format((double) end5 * 0.1D));
                result.result = sb4.toString();
                this.drivingRecorderService.updateMeleage(raw.getDn(), (double) info7 * 0.1D, (double) end5 * 0.1D);
                break;
            case 4:
                PulseBody message11 = new PulseBody();
                gbt = new Gbt19056Packet(message11);
                gbt.from(body.getData());
                list1 = message11.getTime().toString();
                result.result = "时间：" + list1.toString() + "\r\n仪脉冲系数:" + message11.getCoefficient();
                this.drivingRecorderService.updatePulse(raw.getDn(), message11.getCoefficient());
                break;
            case 5:
                VehicleInfoBody message10 = new VehicleInfoBody();
                gbt = new Gbt19056Packet(message10);
                gbt.from(body.getData());
                list2 = new StringBuilder();
                list2.append("车辆识别代号:").append(message10.getVehicleIdcode()).append("\r\n");
                list2.append("机动车号牌号码:").append(message10.getPlateNumber()).append("\r\n");
                list2.append("机动车号牌分类:").append(message10.getCategory());
                result.result = list2.toString();
                this.drivingRecorderService.updateVehicleInfo(raw.getDn(), message10.getVehicleIdcode(), message10.getPlateNumber(), message10.getCategory());
                break;
            case 6:
                ReadStatusSettingReplyBody message9 = new ReadStatusSettingReplyBody();
                gbt = new Gbt19056Packet(message9);
                gbt.from(body.getData());
                list2 = new StringBuilder();
                list2.append("D0的状态信号名称:").append(message9.getName0()).append("\r\n");
                list2.append("D1的状态信号名称:").append(message9.getName1()).append("\r\n");
                list2.append("D2的状态信号名称:").append(message9.getName2()).append("\r\n");
                list2.append("D3的状态信号名称:").append(message9.getName3()).append("\r\n");
                list2.append("D4的状态信号名称:").append(message9.getName4()).append("\r\n");
                list2.append("D5的状态信号名称:").append(message9.getName5()).append("\r\n");
                list2.append("D6的状态信号名称:").append(message9.getName6()).append("\r\n");
                list2.append("D7的状态信号名称:").append(message9.getName7()).append("\r\n");
                result.result = list2.toString();
                this.drivingRecorderService.updateStatusSetting(raw.getDn(), message9.getName0(), message9.getName1(), message9.getName2(), message9.getName3(), message9.getName4(), message9.getName5(), message9.getName6(), message9.getName7());
                break;
            case 7:
                ReadIdReplyBody message8 = new ReadIdReplyBody();
                gbt = new Gbt19056Packet(message8);
                gbt.from(body.getData());
                list1 = Integer.toHexString(message8.getYear()) + "年" + Integer.toHexString(message8.getMonth()) + "月" + Integer.toHexString(message8.getDay()) + "日";
                StringBuilder sb2 = new StringBuilder();
                sb2.append("CCC认证代码:").append(message8.getCcc()).append("\r\n");
                sb2.append("产品型号:").append(message8.getModel()).append("\r\n");
                sb2.append("生产日期:").append(list1).append("\r\n");
                sb2.append("生产流水号:").append(message8.getSn());
                result.result = sb2.toString();
                this.drivingRecorderService.updateID(raw.getDn(), message8.getCcc(), message8.getModel(), list1, message8.getSn());
                break;
            case 8:
                ReadSpeedRecordReplyBody message7 = new ReadSpeedRecordReplyBody();
                gbt = new Gbt19056Packet(message7);
                gbt.from(body.getData());
                list = new ArrayList();
                sb = message7.getBlocks().iterator();

                while (sb.hasNext()) {
                    SpeedRecordInfo info6 = (SpeedRecordInfo) sb.next();
                    if (info6.getContent()[0] != 255) {
                        try {
                            start = DateFormats.DateTimeFormat.parse(info6.getTime().toString());
                        } catch (ParseException var19) {
                            continue;
                        }

                        SpeedLog end6 = new SpeedLog();
                        end6.setNumber(raw.getDn());
                        end6.setTime(start);
                        end6.setContent(info6.getContent());
                        list.add(end6);
                    }
                }

                if (list.size() > 0) {
                    this.drivingRecorderService.saveSpeedLog(list);
                }
                break;
            case 9:
                ReadLocateRecordReplyBody message6 = new ReadLocateRecordReplyBody();
                gbt = new Gbt19056Packet(message6);
                gbt.from(body.getData());
                list = new ArrayList();
                sb = message6.getLocates().iterator();

                while (sb.hasNext()) {
                    LocateInfo info5 = (LocateInfo) sb.next();

                    try {
                        start = DateFormats.DateTimeFormat.parse(info5.getTime().toString());
                    } catch (ParseException var20) {
                        continue;
                    }

                    end5 = start.getTime();
                    Iterator sb1 = info5.getBlocks().iterator();

                    while (sb1.hasNext()) {
                        Tuple tuple = (Tuple) sb1.next();
                        end5 += 60000L;
                        if (((Short) tuple.t).shortValue() != 255) {
                            LocateLog log1 = new LocateLog();
                            log1.setNumber(raw.getDn());
                            log1.setTime(new Date(end5));
                            log1.setLat((double) ((Locate) tuple.e).getLat() * 1.0E-4D / 60.0D);
                            log1.setLng((double) ((Locate) tuple.e).getLng() * 1.0E-4D / 60.0D);
                            log1.setAlt(((Locate) tuple.e).getAlt());
                            log1.setSpeed(((Short) tuple.t).shortValue());
                            list.add(log1);
                        }
                    }
                }

                if (list.size() > 0) {
                    this.drivingRecorderService.saveLocateLog(list);
                }
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            default:
                break;
            case 16:
                ReadAccidentDoubtRecordReplyBody message5 = new ReadAccidentDoubtRecordReplyBody();
                gbt = new Gbt19056Packet(message5);
                gbt.from(body.getData());
                list = new ArrayList();
                sb = message5.getAccidentDoubts().iterator();

                while (sb.hasNext()) {
                    AccidentDoubtInfo info4 = (AccidentDoubtInfo) sb.next();

                    try {
                        start = DateFormats.DateTimeFormat.parse(info4.getEnd().toString());
                    } catch (ParseException var21) {
                        continue;
                    }

                    AccidentDoubt end4 = new AccidentDoubt();
                    end4.setNumber(raw.getDn());
                    end4.setTime(start);
                    end4.setLicense(info4.getLicense());
                    end4.setLng((double) info4.getLocate().getLng() * 1.0E-4D / 60.0D);
                    end4.setLat((double) info4.getLocate().getLat() * 1.0E-4D / 60.0D);
                    end4.setAlt(info4.getLocate().getAlt());
                    end4.setContent(info4.getContent());
                    list.add(end4);
                }

                if (list.size() > 0) {
                    this.drivingRecorderService.saveAccidentDoubt(list);
                }
                break;
            case 17:
                ReadTimeoutDrivingRecordReplyBody message4 = new ReadTimeoutDrivingRecordReplyBody();
                gbt = new Gbt19056Packet(message4);
                gbt.from(body.getData());
                list = new ArrayList();
                sb = message4.getTimeouts().iterator();

                while (sb.hasNext()) {
                    TimeoutDrivingInfo info3 = (TimeoutDrivingInfo) sb.next();

                    try {
                        start = DateFormats.DateTimeFormat.parse(info3.getStartTime().toString());
                        end = DateFormats.DateTimeFormat.parse(info3.getEndTime().toString());
                    } catch (ParseException var22) {
                        continue;
                    }

                    TimeoutDriving log2 = new TimeoutDriving();
                    log2.setNumber(raw.getDn());
                    log2.setLicense(info3.getLicense());
                    log2.setStartTime(start);
                    log2.setEndTime(end);
                    log2.setStartLat((double) info3.getStartLocate().getLat() * 1.0E-4D / 60.0D);
                    log2.setStartLng((double) info3.getStartLocate().getLng() * 1.0E-4D / 60.0D);
                    log2.setStartAlt(info3.getStartLocate().getAlt());
                    log2.setEndLat((double) info3.getEndLocate().getLat() * 1.0E-4D / 60.0D);
                    log2.setEndLng((double) info3.getEndLocate().getLng() * 1.0E-4D / 60.0D);
                    log2.setEndAlt(info3.getEndLocate().getAlt());
                    list.add(log2);
                }

                if (list.size() > 0) {
                    this.drivingRecorderService.saveTimeoutDriving(list);
                }
                break;
            case 18:
                ReadLoginLogoutRecordReplyBody message3 = new ReadLoginLogoutRecordReplyBody();
                gbt = new Gbt19056Packet(message3);
                gbt.from(body.getData());
                list = new ArrayList();
                sb = message3.getLogouts().iterator();

                while (sb.hasNext()) {
                    LogOutInfo info2 = (LogOutInfo) sb.next();

                    try {
                        start = DateFormats.DateTimeFormat.parse(info2.getTime().toString());
                    } catch (ParseException var23) {
                        continue;
                    }

                    LoginLogoutLog end3 = new LoginLogoutLog();
                    end3.setNumber(raw.getDn());
                    end3.setLicense(info2.getLicense());
                    end3.setTime(start);
                    end3.setEvent(info2.getAction());
                    list.add(end3);
                }

                if (list.size() > 0) {
                    this.drivingRecorderService.saveLoginLogoutLog(list);
                }
                break;
            case 19:
                ReadExternalPowerSupplyRecordReplyBody message2 = new ReadExternalPowerSupplyRecordReplyBody();
                gbt = new Gbt19056Packet(message2);
                gbt.from(body.getData());
                list = new ArrayList();
                sb = message2.getLogs().iterator();

                while (sb.hasNext()) {
                    info1 = (Tuple) sb.next();

                    try {
                        start = DateFormats.DateTimeFormat.parse(((DrivingTime) info1.e).toString());
                    } catch (ParseException var24) {
                        continue;
                    }

                    PowerSupplyLog end2 = new PowerSupplyLog();
                    end2.setNumber(raw.getDn());
                    end2.setTime(start);
                    end2.setEvent(((Byte) info1.t).byteValue());
                    list.add(end2);
                }

                if (list.size() > 0) {
                    this.drivingRecorderService.savePowerSupplyLog(list);
                }
                break;
            case 20:
                ReadParameterChangeRecordReplyBody message1 = new ReadParameterChangeRecordReplyBody();
                gbt = new Gbt19056Packet(message1);
                gbt.from(body.getData());
                list = new ArrayList();
                sb = message1.getLogs().iterator();

                while (sb.hasNext()) {
                    info1 = (Tuple) sb.next();

                    try {
                        start = DateFormats.DateTimeFormat.parse(((DrivingTime) info1.e).toString());
                    } catch (ParseException var25) {
                        continue;
                    }

                    ParameterChangeLog end1 = new ParameterChangeLog();
                    end1.setNumber(raw.getDn());
                    end1.setTime(start);
                    end1.setEvent(((Short) info1.t).shortValue());
                    list.add(end1);
                }

                if (list.size() > 0) {
                    this.drivingRecorderService.saveParameterChangeLog(list);
                }
                break;
            case 21:
                ReadSpeedStatusLogReplyBody message = new ReadSpeedStatusLogReplyBody();
                gbt = new Gbt19056Packet(message);
                gbt.from(body.getData());
                list = new ArrayList();
                sb = message.getLogs().iterator();

                while (sb.hasNext()) {
                    SpeedStatusInfo info = (SpeedStatusInfo) sb.next();

                    try {
                        start = DateFormats.DateTimeFormat.parse(info.getStart().toString());
                        end = DateFormats.DateTimeFormat.parse(info.getEnd().toString());
                    } catch (ParseException var26) {
                        continue;
                    }

                    SpeedStatusLog log = new SpeedStatusLog();
                    log.setNumber(raw.getDn());
                    log.setStart(start);
                    log.setEnd(end);
                    log.setStatus(info.getFlag());
                    log.setContent(info.getContent());
                    list.add(log);
                }

                if (list.size() > 0) {
                    this.drivingRecorderService.saveSpeedStatusLog(list);
                }
        }

        InstructResultMessage message16 = new InstructResultMessage();
        message16.number = raw.getDn();
        message16.id = result.id;
        message16.result = result.result;
        Pushers.getCurrent().push(packet.getNumber(), "device.instruct.result", message16);
    }
}
