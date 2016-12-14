package Util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Baltzar on 2016-12-14.
 */
public class CreateExcelFile {

    //HSSFWorkbook workbook = new HSSFWorkbook();
    XSSFWorkbook workbook = new XSSFWorkbook();
    int sheetCounter = 1;
    XSSFSheet sheet = workbook.createSheet("Sheet " + sheetCounter);

    int rowCounter = 0;
    Row row = sheet.createRow(rowCounter++);
    Cell cell = row.createCell(0);
    cell.setCellValue("Date");

    cell = row.createCell(1);
             cell.setCellValue("Libraray");

    cell = row.createCell(2);
             cell.setCellValue("Message");

    Logg readLogg = null;
    for (int i = 0; i < readLoggs.size(); i++) {
        readLogg = readLoggs.get(i);
        row = sheet.createRow(rowCounter++);
        cell = row.createCell(0);
        cell.setCellValue(readLogg.getDateAsString());
        cell = row.createCell(1);
        cell.setCellValue(readLogg.getLibrary());
        cell = row.createCell(2);
        cell.setCellValue(readLogg.getMessage());
    }
    try {
        FileOutputStream out = new FileOutputStream(new File(CreateExcelFile.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "excelLogg.xlsx"));
        workbook.write(out);
        out.close();

    } catch (IOException e) {
        e.printStackTrace();
    }


}
