package io;

import algorithms.*;
import org.apache.commons.codec.binary.BaseNCodecOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import structures.Detail;
import structures.Plate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static io.Output.createSheet;
import static io.Output.createStyleForTitle;
import static java.lang.Math.abs;

public class Statistics {
    private static String path;
    private static int numberOfTests;
    private static int plate_width;

    public static void setNumberOfTests(int numberOfTests) {
        Statistics.numberOfTests = numberOfTests;
    }

    public static void setPath(String path) {
        Statistics.path = path;
    }

    public static void setPlate_width(int plate_width) {
        Statistics.plate_width = plate_width;
    }

    public static void generateTests() throws IOException {
        for (int i=0; i<numberOfTests; i++) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("input");

            int rownum = 0;
            Cell cell;
            Row row;

            XSSFCellStyle style = createStyleForTitle(workbook);
            row = sheet.createRow(rownum);

            cell = row.createCell(0);
            cell.setCellValue("detail_id");
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue("width");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("height");
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue("number");
            cell.setCellStyle(style);

            Random random = new Random();
            // Data
            for (int j = 0; j < numberOfTests / (abs(random.nextInt(10)) + 1); j++) {
                rownum++;
                row = sheet.createRow(rownum);

                cell = row.createCell(0);
                cell.setCellValue(j + 1);

                cell = row.createCell(1);
                cell.setCellValue(abs(random.nextInt(plate_width)));

                cell = row.createCell(2);
                cell.setCellValue(abs(random.nextInt(plate_width)));

                cell = row.createCell(3);
                cell.setCellValue(abs(random.nextInt(10)) + 1);
            }

            FileOutputStream outFile = new FileOutputStream(path + "\\test" + i + ".xlsx");
            workbook.write(outFile);
        }
    }

    public static void executeTests(String where) throws IOException {
        XSSFWorkbook statistics = new XSSFWorkbook();
        XSSFSheet heights = statistics.createSheet("heights");
        styleStatistics(statistics, heights);
        XSSFSheet emptySpaces = statistics.createSheet("empty_spaces");
        styleStatistics(statistics, emptySpaces);
        emptySpaces.getRow(0).getCell(7).setCellValue((String) null);

        XSSFCellStyle style = createStyleForTitle(statistics);
        Row rowHeights;
        Row rowSpaces;
        Cell cellHeights;
        Cell cellSpaces;

        //statistics.write(new FileOutputStream(where + "\\statistics.xlsx"));
        for (int i=0; i<numberOfTests; i++) {
            Input.setPath(path + "\\test" + i + ".xlsx");
            ArrayList<Detail> detailSet = Input.makeList("input",1);

            PackageAlgorithm algorithms[] = new PackageAlgorithm[6];
            algorithms[0] = new NFDH();
            algorithms[1] = new FFDH();
            algorithms[2] = new BFDH();
            algorithms[3] = new SF();
            algorithms[4] = new Join();
            algorithms[5] = new FCNR();
            PackageAlgorithmSwitcher switcher;

            XSSFWorkbook workbook = new XSSFWorkbook();

            rowHeights=heights.createRow(i+1);
            rowSpaces=emptySpaces.createRow(i+1);
            cellHeights = rowHeights.createCell(0);
            cellSpaces = rowSpaces.createCell(0);
            cellHeights.setCellValue(i+1);
            cellHeights.setCellStyle(style);
            cellSpaces.setCellValue(i+1);
            cellSpaces.setCellStyle(style);

            for(int k = 0; k < 6; k++) {
                switcher = new PackageAlgorithmSwitcher(algorithms[k]);
                Plate plate = switcher.execute(detailSet, plate_width);
                XSSFSheet sheet = workbook.createSheet(switcher.getName());
                createSheet(workbook, sheet, plate);

                cellHeights = rowHeights.createCell(k+1);
                cellSpaces = rowSpaces.createCell(k+1);
                cellHeights.setCellValue(plate.getHeight());
                cellSpaces.setCellValue(plate.emptySpaces());
            }
            cellHeights = rowHeights.createCell(7);
            int h=0;
            for(int l=1; l<7; l++) {
                h+=(int) rowHeights.getCell(l).getNumericCellValue();
            }
            for (int l=1; l<7; l++) {
                rowHeights.getCell(l).setCellValue(100*rowHeights.getCell(l).getNumericCellValue()/h);
            }
            cellHeights.setCellValue(100);

            FileOutputStream outFile = new FileOutputStream(where + "\\test" + i + ".xlsx");
            workbook.write(outFile);

        }
        rowHeights = heights.createRow(101);
        rowSpaces = emptySpaces.createRow(101);

        rowHeights.createCell(0).setCellValue("average");
        rowSpaces.createCell(0).setCellValue("average");
        rowHeights.getCell(0).setCellStyle(style);
        rowSpaces.getCell(0).setCellStyle(style);
        for (int k=1; k<7; k++) {
            double tempH = 0, tempS = 0;
            for (int i=1; i<numberOfTests+1; i++)
            {
                tempH+=heights.getRow(i).getCell(k).getNumericCellValue();
                tempS+=emptySpaces.getRow(i).getCell(k).getNumericCellValue();
            }
            rowHeights.createCell(k).setCellValue(tempH/numberOfTests);
            rowSpaces.createCell(k).setCellValue(tempS/numberOfTests);
            rowHeights.getCell(k).setCellStyle(style);
            rowSpaces.getCell(k).setCellStyle(style);
        }
        statistics.write(new FileOutputStream(where + "\\statistics.xlsx"));
    }

    static void styleStatistics(XSSFWorkbook statistics, XSSFSheet sheet)
    {
        Cell cell;
        Row row;

        XSSFCellStyle style = createStyleForTitle(statistics);
        row = sheet.createRow(0);

        cell = row.createCell(0);
        cell.setCellValue("test");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("NFDH");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("FFDH");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("BFDH");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("SF");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("Join");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("FCNR");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("sum");
        cell.setCellStyle(style);
    }
}
