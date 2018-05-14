package com.rayton.gps.export;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

// 你问问spring5.0
public class ExcelView extends AbstractXlsView {


    // public void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
    //                                HttpServletResponse response) throws Exception {
    //     // 主题
    //     String title = model.get("title").toString();
    //     // 表头
    //     String[] headers = (String[]) model.get("headers");
    //     String fileName = model.get("fileName").toString();
    //     // 表体
    //     @SuppressWarnings("unchecked") List<List<String>> list = (List<List<String>>) model.get("list");
    //
    //     OutputStream os = response.getOutputStream();// 取得输出流
    //     // 下面是对中文文件名的处理
    //     response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
    //     response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
    //     response.setContentType("application/msexcel");// 定义输出类型
    //
    //     HSSFCellStyle headerStyle = workbook.createCellStyle(); // 标题样式
    //     headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    //     headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    //     HSSFFont headerFont = workbook.createFont(); // 标题字体
    //     headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    //     headerFont.setFontHeightInPoints((short) 11);
    //     headerStyle.setFont(headerFont);
    //
    //     HSSFSheet sheet = workbook.createSheet(title);
    //     int index = 0;
    //
    //     if (title != null && !title.isEmpty()) {
    //         HSSFCell cell = getCell(sheet, index++, 0);
    //         cell.setCellStyle(headerStyle);
    //         setText(cell, title);
    //     }
    //
    //     for (int i = 0; i < headers.length; i++) { // 设置标题
    //         String header = headers[i];
    //         HSSFCell cell = getCell(sheet, index, i);
    //         cell.setCellStyle(headerStyle);
    //         setText(cell, header);
    //     }
    //
    //     HSSFCellStyle contentStyle = workbook.createCellStyle(); // 内容样式
    //     contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    //
    //     for (int row = 0; row < list.size(); row++) {
    //         index++;
    //         List<String> data = list.get(row);
    //         for (int col = 0; col < data.size(); col++) {
    //             HSSFCell cell = getCell(sheet, index, col);
    //             cell.setCellStyle(contentStyle);
    //             setText(cell, data.get(col));
    //         }
    //     }
    //
    //     workbook.write(os);
    //     os.flush();
    //     os.close();
    // }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {


        String excelName = model.get("fileName").toString() + ".xls";
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(excelName, "utf-8"));
        response.setContentType("application/ms-excel; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        @SuppressWarnings("unchecked")
        // List<ExportMemberVo> list = (List<ExportMemberVo>) model.get("members");
                List<List<String>> list = (List<List<String>>) model.get("list");


        // 主题
        String title = model.get("title").toString();
        // 表头
        String[] headers = (String[]) model.get("headers");

        Sheet sheet = workbook.createSheet(title);
        // sheet.setDefaultColumnWidth(30);

        // CellStyle style = workbook.createCellStyle();
        // Font font = workbook.createFont();
        // font.setFontName("Arial");
        // style.setFillForegroundColor(HSSFColor.BLUE.index);
        // style.setFillPattern((short) 1);
        // font.setBold(true);
        // font.setColor(HSSFColor.WHITE.index);
        // style.setFont(font);

        // HSSFSheet sheet = workbook.createSheet(title);
        int index = 0;

        if (title != null && !title.isEmpty()) {
            Row header = sheet.createRow(index++);
            header.createCell(0).setCellValue(title);

            // HSSFCell cell = getCell(sheet, index++, 0);
            // // cell.setCellStyle(headerStyle);
            // setText(cell, title);
        }

        Row headerRow=  sheet.createRow(index++);
        for (int i = 0; i < headers.length; i++) { // 设置标题
            String header = headers[i];
            // HSSFCell cell = getCell(sheet, index, i);
            // // cell.setCellStyle(headerStyle);
            // setText(cell, header);
            headerRow.createCell(i).setCellValue(header);
        }

        // HSSFCellStyle contentStyle = workbook.createCellStyle(); // 内容样式
        // contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        for (int row = 0; row < list.size(); row++) {
            Row userRow = sheet.createRow(index++);
            List<String> data = list.get(row);
            for (int col = 0; col < data.size(); col++) {

                userRow.createCell(col).setCellValue(data.get(col));
                // HSSFCell cell = getCell(sheet, index, col);
                // // cell.setCellStyle(contentStyle);
                // setText(cell, data.get(col));
            }
        }


    }

}
