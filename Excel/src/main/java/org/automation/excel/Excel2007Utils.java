package org.automation.excel;



import lombok.Getter;
import lombok.NonNull;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shantonu on 6/8/16.
 * todo, planned to complete ASAP
 */
public class Excel2007Utils {
    private
    @Getter
    static XSSFWorkbook workbook;
    private
    @Getter
    static XSSFSheet sheet;
    private
    @Getter
    static String excelFolder = System.getProperty("user.dir");
    private static HSSFCell cell;
    private static HSSFCellStyle cellStyleDefault;
    private static StringBuilder fileName = new StringBuilder();
    private static String currentSheet;
    private static String fontDefault = "Calibri-Regular";
    private static HSSFCreationHelper createHelper;

    private Excel2007Utils() {

    }

    public List<ExcelBook> readBooksFromExcelFile(String excelFilePath) throws IOException {
        List<ExcelBook> listBooks = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            ExcelBook aBook = new ExcelBook();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 1:
                        aBook.setTitle((String) getCellValue(nextCell));
                        break;
                    case 2:
                        aBook.setAuthor((String) getCellValue(nextCell));
                        break;
                    case 3:
                        aBook.setPrice((double) getCellValue(nextCell));
                        break;
                }


            }
            listBooks.add(aBook);
        }

        workbook.close();
        inputStream.close();

        return listBooks;
    }
    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }
}