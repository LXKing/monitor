package mmp.gps.monitor.export;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {


    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String excelName = model.get("fileName").toString() + ".xls";
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(excelName, "utf-8"));
        response.setContentType("application/ms-excel; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        @SuppressWarnings("unchecked")
        List<List<String>> list = (List<List<String>>) model.get("list");

        // 主题
        String title = model.get("title").toString();
        // 表头
        String[] headers = (String[]) model.get("headers");

        Sheet sheet = workbook.createSheet(title);

        int index = 0;

        if (title != null && !title.isEmpty()) {
            Row header = sheet.createRow(index++);
            header.createCell(0).setCellValue(title);
        }

        Row headerRow = sheet.createRow(index++);

        for (int i = 0; i < headers.length; i++) { // 设置标题
            String header = headers[i];
            headerRow.createCell(i).setCellValue(header);
        }

        for (int row = 0; row < list.size(); row++) {
            Row userRow = sheet.createRow(index++);
            List<String> data = list.get(row);
            for (int col = 0; col < data.size(); col++) {
                userRow.createCell(col).setCellValue(data.get(col));
            }
        }

    }

}
