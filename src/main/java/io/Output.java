package io;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import structures.Detail;
import structures.Plate;

import java.io.FileOutputStream;
import java.io.IOException;

public class Output{

    private static String path;

    public static void setPath(String path) {
        Output.path = path;
    }

    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
    public static void createExcel(Plate list, int num) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("output" + num);

        int rownum = 0;
        Cell cell;
        Row row;

        XSSFCellStyle style = createStyleForTitle(workbook);
        row = sheet.createRow(rownum);

        cell = row.createCell(0);
        cell.setCellValue("width");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("height");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("position_x");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("position_y");
        cell.setCellStyle(style);

        // Data
        for (Detail detail : list.getList()) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0);
            cell.setCellValue(detail.getWidth());

            cell = row.createCell(1);
            cell.setCellValue(detail.getHeight());

            cell = row.createCell(2);
            cell.setCellValue(detail.getX());

            cell = row.createCell(3);
            cell.setCellValue(detail.getY());
        }

        FileOutputStream outFile = new FileOutputStream(path);
        workbook.write(outFile);
    }
}
