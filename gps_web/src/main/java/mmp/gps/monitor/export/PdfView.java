package mmp.gps.monitor.export;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class PdfView extends AbstractPdfView {

    @Override
    public void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ttfPath = request.getServletContext().getRealPath("/resources/simsun.ttf");
        BaseFont zh = BaseFont.createFont(ttfPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font titleFont = new Font(zh, 10, Font.BOLD);
        Font headerFont = new Font(zh, 8, Font.BOLD);
        Font bodyFont = new Font(zh, 8, Font.NORMAL);

        PdfEvent event = new PdfEvent(writer);// 就是上面那个类
        event.setBf(zh);
        writer.setPageEvent(event);

        String fileName = model.get("fileName").toString();
        // 下面是对中文文件名的处理
        response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".pdf");
        response.setContentType("application/x-download");// 定义输出类型

        // 主题
        String title = model.get("title").toString();
        // 表头
        String[] headers = (String[]) model.get("headers");
        // 列宽
        float[] widths = (float[]) model.get("widths");
        // 表体
        @SuppressWarnings("unchecked") List<List<String>> list = (List<List<String>>) model.get("list");

        if (title != null && !title.isEmpty()) {
            Paragraph p = new Paragraph(title, titleFont);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(p);
            document.add(Chunk.NEWLINE);
        }

        PdfPTable table = new PdfPTable(headers.length);
        if (widths != null)
            table.setWidths(widths);
        table.setWidthPercentage(100);
        // 占页面宽度比例
        table.getDefaultCell().setMinimumHeight(bodyFont.getSize() * 2f);
        for (int i = 0; i < headers.length; i++) { // 设置标题
            String header = headers[i];
            PdfPCell cell = new PdfPCell(new Paragraph(header, headerFont));
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
            table.addCell(cell);
        }

        for (int row = 0; row < list.size(); row++) {
            List<String> data = list.get(row);
            for (int col = 0; col < data.size(); col++) {
                table.addCell(new Paragraph(data.get(col), bodyFont));
            }
        }
        document.add(table);
        document.close();
    }
}
