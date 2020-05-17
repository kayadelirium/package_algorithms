package io;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import structures.Detail;


public class Input {
    private static String path;
    static ArrayList<Detail> list;

    public static void setPath(String path) {
        Input.path = path;
    }

    public static ArrayList<Detail> makeList(String sheetFrom, int firstRow, int col1, int col2) throws IOException {
        list = new ArrayList<Detail>();
        XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(path));
        XSSFSheet sheet = book.getSheet(sheetFrom);
        for (int i = firstRow; i <= sheet.getLastRowNum(); i++) {
            XSSFRow current = sheet.getRow(i);
            list.add(new Detail((int) current.getCell(col1).getNumericCellValue(), (int) current.getCell(col2).getNumericCellValue()));
        }
        return list;
    }
    public ArrayList<Detail> getList() {
        return list;
    }
}

