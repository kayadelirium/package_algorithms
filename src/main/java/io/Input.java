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
    private static ArrayList<Detail> list;

    public static void setPath(String path) {
        Input.path = path;
    }

    public static ArrayList<Detail> makeList(String sheetFrom, int firstRow) throws IOException {
        list = new ArrayList<Detail>();
        XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(path));
        XSSFSheet sheet = book.getSheet(sheetFrom);
        for (int i = firstRow; i <= sheet.getLastRowNum(); i++) {
            XSSFRow current = sheet.getRow(i);
            int numberOfDetails = (int) current.getCell(3).getNumericCellValue();
            for(int num=0; num<numberOfDetails; num++)
                list.add(new Detail( (int)current.getCell(0).getNumericCellValue (), (int)current.getCell(1).getNumericCellValue(), (int) current.getCell(2).getNumericCellValue()));
        }
        return list;
    }
    public ArrayList<Detail> getList() {
        return list;
    }
}

