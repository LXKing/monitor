package mmp.gps.logic.service;


import mmp.gps.common.util.DaoFactory;

import mmp.gps.common.util.Page;
import mmp.gps.domain.exist.ExistResponse;
import mmp.gps.domain.faultcode.*;
import mmp.gps.logic.dao.IFaultCodeDao;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class FaultCodeService {

    public static FetchFaultCodeResponse create(CreateFaultCodeRequest request) throws Exception {
        IFaultCodeDao dao = (IFaultCodeDao) DaoFactory.getAdo(IFaultCodeDao.class);
        if (dao.exists(request.getModeId(), request.getCode1(), request.getCode2(), request.getCode3())) {
            throw new Exception("故障码已存在");
        } else {
            FaultCodeDto dto = fillFaultCodeDto(request);
            dao.create(dto);
            return fillFetchFaultCodeResponse(dto);
        }
    }

    public static FetchFaultCodeResponse update(EditFaultCodeRequest request) throws Exception {
        IFaultCodeDao dao = (IFaultCodeDao) DaoFactory.getAdo(IFaultCodeDao.class);
        if (dao.exists(request.getModeId(), request.getCode1(), request.getCode2(), request.getCode3(), request.getId())) {
            throw new Exception("故障码已存在");
        } else {
            FaultCodeDto dto = fillFaultCodeDto(request);
            dto.id = request.getId();
            dto.editTime = request.getEditTime();
            dao.update(dto);
            return fillFetchFaultCodeResponse(dto);
        }
    }

    public static FetchFaultCodeResponse fetch(String id) throws Exception {
        IFaultCodeDao dao = (IFaultCodeDao) DaoFactory.getAdo(IFaultCodeDao.class);
        FaultCodeDto dto = dao.fetch(id);
        return fillFetchFaultCodeResponse(dto);
    }

    public static void delete(String id) throws Exception {
        IFaultCodeDao dao = (IFaultCodeDao) DaoFactory.getAdo(IFaultCodeDao.class);
        dao.delete(id);
    }

    public static QueryFaultCodeResponse query(QueryFaultCodeRequest request) throws Exception {
        QueryFaultCodeResponse response = new QueryFaultCodeResponse();
        IFaultCodeDao ado = (IFaultCodeDao) DaoFactory.getAdo(IFaultCodeDao.class);
        Page page = ado.query(request.getModeId(), request.getCode1(), request.getCode2(), request.getCode3(), request.getPage(), request.getPageSize());
        response.setTotal(page.total);
        ArrayList faultcodes = new ArrayList();
        Iterator var5 = page.data.iterator();

        while (var5.hasNext()) {
            FaultCodeInfoDto dto = (FaultCodeInfoDto) var5.next();
            FaultCodeInfo info = new FaultCodeInfo();
            info.setCode1(dto.code1);
            info.setCode2(dto.code2);
            info.setCode3(dto.code3);
            info.setId(dto.id);
            info.setLevel(dto.level);
            info.setModeId(dto.modeId);
            info.setPcode(dto.pcode);
            info.setContentC(dto.contentC);
            faultcodes.add(info);
        }

        response.setCodes(faultcodes);
        return response;
    }

    public static ExistResponse exists(ExistFaultCodeRequest request) throws Exception {
        IFaultCodeDao dao = (IFaultCodeDao) DaoFactory.getAdo(IFaultCodeDao.class);
        ExistResponse response = new ExistResponse();
        boolean existent = false;
        if (request.isCheckId()) {
            existent = dao.exists(request.getModeId(), request.getCode1(), request.getCode2(), request.getCode3(), request.getId());
        } else {
            existent = dao.exists(request.getModeId(), request.getCode1(), request.getCode2(), request.getCode3());
        }

        response.setExistent(existent);
        return response;
    }

    private static FaultCodeDto fillFaultCodeDto(CreateFaultCodeRequest request) {
        FaultCodeDto dto = new FaultCodeDto();
        dto.brand = request.getBrand();
        dto.clear = request.getClear();
        dto.code1 = request.getCode1();
        dto.code2 = request.getCode2();
        dto.code3 = request.getCode3();
        dto.contentC = request.getContentC();
        dto.contentE = request.getContentE();
        dto.level = request.getLevel();
        dto.modeId = request.getModeId();
        dto.notifyAdvisor = request.isNotifyAdvisor();
        dto.notifyOwner = request.isNotifyOwner();
        dto.pcode = request.getPcode();
        dto.solution = request.getSolution();
        dto.sensors = request.getSensors();
        return dto;
    }

    private static FetchFaultCodeResponse fillFetchFaultCodeResponse(FaultCodeDto dto) {
        FetchFaultCodeResponse r = new FetchFaultCodeResponse();
        r.setBrand(dto.brand);
        r.setClear(dto.clear);
        r.setCode1(dto.code1);
        r.setCode2(dto.code2);
        r.setCode3(dto.code3);
        r.setContentC(dto.contentC);
        r.setContentE(dto.contentE);
        r.setSensors(dto.sensors);
        r.setLevel(dto.level);
        r.setModeId(dto.modeId);
        r.setNotifyAdvisor(dto.notifyAdvisor);
        r.setNotifyOwner(dto.notifyOwner);
        r.setPcode(dto.pcode);
        r.setSolution(dto.solution);
        r.setId(dto.id);
        r.setEditTime(dto.editTime);
        return r;
    }

    public static ParseFaultCodeResponse parse(ParseFaultCodeRequest request) throws Exception {
        IFaultCodeDao dao = (IFaultCodeDao) DaoFactory.getAdo(IFaultCodeDao.class);
        FaultCodeDto dto = dao.parse(request.getModeId(), request.getCode1(), request.getCode2(), request.getCode3());
        ParseFaultCodeResponse response = new ParseFaultCodeResponse();
        response.setBrand(dto.brand);
        response.setClear(dto.clear);
        response.setContentC(dto.contentC);
        response.setContentE(dto.contentE);
        response.setLevel(dto.level);
        response.setNotifyAdvisor(dto.notifyAdvisor);
        response.setNotifyOwner(dto.notifyOwner);
        response.setPcode(dto.pcode);
        response.setSolution(dto.solution);
        response.setSensors(dto.sensors);
        return response;
    }

    public static void importRow(ImportFaultCodeRequest request) throws Exception {
        IFaultCodeDao dao = (IFaultCodeDao) DaoFactory.getAdo(IFaultCodeDao.class);
        FaultCodeDto dto = new FaultCodeDto();
        dto.brand = request.getBrand();
        dto.clear = request.getClear();
        dto.code1 = request.getCode1();
        dto.code2 = request.getCode2();
        dto.code3 = request.getCode3();
        dto.contentC = request.getContentC();
        dto.contentE = request.getContentE();
        dto.level = request.getLevel();
        dto.modeId = request.getModeId();
        dto.notifyAdvisor = request.isNotifyAdvisor();
        dto.notifyOwner = request.isNotifyOwner();
        dto.pcode = request.getPcode();
        dto.sensors = request.getSensors();
        dto.solution = request.getSolution();
        dao.importRow(dto);
    }

    public static int importExcel2003File(InputStream file) throws Exception {
        int total = 0;
        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(file);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        int rowstart = hssfSheet.getFirstRowNum();
        int rowEnd = hssfSheet.getLastRowNum();

        for (int i = rowstart + 1; i <= rowEnd; ++i) {
            HSSFRow row = hssfSheet.getRow(i);
            if (null != row) {
                short cellStart = row.getFirstCellNum();
                short cellEnd = row.getLastCellNum();
                ImportFaultCodeRequest request = new ImportFaultCodeRequest();

                for (int k = cellStart; k <= cellEnd; ++k) {
                    HSSFCell cell = row.getCell(k);
                    if (null != cell) {
                        try {
                            boolean ex;
                            double var17;
                            String var18;
                            switch (k) {
                                case 0:
                                    var18 = cell.getStringCellValue();
                                    request.setBrand(var18);
                                    break;
                                case 1:
                                    var18 = cell.getStringCellValue().trim().toLowerCase().replace("\"", "");
                                    request.setModeId(Short.parseShort(var18.replace("0x", ""), 16));
                                    break;
                                case 2:
                                    var18 = cell.getStringCellValue().trim().toLowerCase().replace("\"", "");
                                    request.setCode1(Short.parseShort(var18.replace("0x", ""), 16));
                                    break;
                                case 3:
                                    var18 = cell.getStringCellValue().trim().toLowerCase().replace("\"", "");
                                    request.setCode2(Short.parseShort(var18.replace("0x", ""), 16));
                                    break;
                                case 4:
                                    var18 = cell.getStringCellValue().trim().toLowerCase().replace("\"", "");
                                    request.setCode3(Short.parseShort(var18.replace("0x", ""), 16));
                                    break;
                                case 5:
                                    var18 = cell.getStringCellValue().trim().replace("\"", "");
                                    request.setPcode(var18);
                                    break;
                                case 6:
                                    var18 = cell.getStringCellValue().trim().replace("\"", "");
                                    request.setContentC(var18);
                                    break;
                                case 7:
                                    var18 = cell.getStringCellValue().trim().replace("\"", "");
                                    request.setContentE(var18);
                                    break;
                                case 8:
                                    var18 = cell.getStringCellValue().trim().replace("\"", "");
                                    request.setSensors(var18);
                                    break;
                                case 9:
                                    var18 = cell.getStringCellValue().trim().replace("\"", "");
                                    request.setSolution(var18);
                                    break;
                                case 10:
                                    var17 = cell.getNumericCellValue();
                                    request.setLevel((short) ((int) var17));
                                    break;
                                case 11:
                                    var17 = cell.getNumericCellValue();
                                    request.setClear((byte) ((int) var17));
                                    break;
                                case 12:
                                    ex = cell.getBooleanCellValue();
                                    request.setNotifyOwner(ex);
                                    break;
                                case 13:
                                    ex = cell.getBooleanCellValue();
                                    request.setNotifyAdvisor(ex);
                            }
                        } catch (Exception var16) {
                            throw new Exception("单位格（" + i + "行," + k + "列）数据错误：" + var16.getMessage());
                        }
                    }
                }

                importRow(request);
                ++total;
            }
        }

        return total;
    }
}
