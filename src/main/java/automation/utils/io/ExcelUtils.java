package automation.utils.io;

import lombok.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shantonu on 6/8/16.
 * todo, planned to complete ASAP
 */
public class ExcelUtils {
    private @Getter static HSSFWorkbook workbook;
    private @Getter static HSSFSheet sheet;
    private static HSSFCell cell;
    private static HSSFCellStyle cellStyleDefault;
    private static StringBuilder fileName = new StringBuilder();
    private static String currentSheet;
    private static String fontDefault = "Calibri-Regular";
    private static  HSSFCreationHelper createHelper;

    static {
        if(System.getProperty("ScreenPath") != null) {
            fileName.append("ScreenPath").append("\\");
        }
        fileName.append(System.getProperty("user.dir"));
    }
    public static String getFileName(){return fileName.toString();}
    private ExcelUtils(){}

    public static void save(@NonNull String fullFileNameAndPath){
        try {
            FileOutputStream out = new FileOutputStream(new File(fullFileNameAndPath));
            save(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void save(@NonNull FileOutputStream outputStream){
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static File createXLfile(String filename){
        return null;
    }
    public static void createSheet(HSSFWorkbook book, String sheetName){

    }
    public static void createSheet(HSSFWorkbook book, String sheetName, String... headers){}
    public static void createRow(boolean isHeader , String... values){
        short ith = (short) (sheet.getLastRowNum()+1);
        HSSFRow row = sheet.createRow(ith);
        createHelper = workbook.getCreationHelper();
        for(int i=0;i<values.length-1; i++){
            Cell cell = row.createCell(i);

            if(isHeader){
                cellStyleDefault=createStyleForHeader(workbook);
            }
            else {
                cellStyleDefault =createStyle(workbook);
            }
            cell.setCellStyle(cellStyleDefault);
        }
    }
    public static void createCol(boolean isHeader , String... values){}

    private static String generateFileName(){return null;}

    private static HSSFCellStyle createStyleForHeader(HSSFWorkbook book){
        HSSFCellStyle style = book.createCellStyle();
        style.setFillPattern((short) 1);

        HSSFFont font = book.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight((short) 600);
        font.setColor((short) 21);
        style.setFont(font);
      return style;
    }
    private static HSSFCellStyle createStyle(HSSFWorkbook book){
        HSSFCellStyle style = book.createCellStyle();
        HSSFFont font = book.createFont();
        font.setFontName(fontDefault);
        style.setFont(font);
        style.setWrapText(true);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);
        style.setBorderTop((short) 1);
        style.setBorderBottom((short) 1);
        return style;
    }
    private static String generateName(StringBuilder fileName){
        return fileName.append(fileName)
                .append(new SimpleDateFormat("yyyy-MM-dd_HHmmss")
                        .format(new Date()))
                .append(".xls")
                .toString();
    }
}
