package org.automation.excel;



import lombok.Getter;
import lombok.NonNull;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shantonu on 6/8/16.
 *
 */
public class Excel97Utils {
    private @Getter static HSSFWorkbook workbook;
    private @Getter static HSSFSheet sheet;
    private @Getter static String excelFolder = System.getProperty("user.dir");
    private static HSSFCell cell;
    private static HSSFCellStyle cellStyleDefault;
    private static StringBuilder fileName = new StringBuilder();
    private static String currentSheet;
    private static String fontDefault = "Calibri-Regular";
    private static  HSSFCreationHelper createHelper;
    private Excel97Utils(){}

    public static String readCell(@NonNull String excelPath,@NonNull int sheetNo,@NonNull int rowNo, @NonNull int colno) throws IOException {
        InputStream in = new FileInputStream(excelPath);
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(sheetNo);
        HSSFRow aRow = sheet.getRow(rowNo);
        HSSFCell cell =  aRow.getCell(colno);
        return cell.getStringCellValue();
    }
    public static String readCellFrom(String excelFileName,@NonNull int sheetNo,@NonNull int rowNo, @NonNull int colno) throws IOException {
        String excelPath = excelFolder+System.getProperty("file.separator")+excelFileName;
        return readCell(excelPath, sheetNo, rowNo, colno);
    }
    private static HSSFRow readRow(HSSFWorkbook workbook,@NonNull int sheetNo, @NonNull int rowNo) {
        HSSFSheet sheet = workbook.getSheetAt(sheetNo);
        return sheet.getRow(rowNo);
    }

    public static String getFileName(){return fileName.toString();}

    public static void save(@NonNull String fullFileNameAndPath) throws IOException {
            FileOutputStream out = new FileOutputStream(new File(fullFileNameAndPath));
            save(out);
    }
    public static void save(@NonNull FileOutputStream outputStream) throws IOException {
            workbook.write(outputStream);
    }

    public static HSSFSheet createSheet(HSSFWorkbook book, String sheetName){
        return createSheet(book, sheetName, null);
    }
    public static HSSFSheet createSheet(HSSFWorkbook book, String sheetName, String... headers){
        workbook=book;
        // if sheet name is null, use previous, if sheet name is not current, make it current
        if(!sheetName.equals(null)){
            if(!currentSheet.equals(sheetName)){
                currentSheet=sheetName;
            }
        }

        sheet =workbook.getSheet(currentSheet);
        if(sheet==null){
            sheet = workbook.createSheet(currentSheet);
            if (headers!=null)
                createHeader(sheet, headers);
            sheet.createFreezePane(0,1);
        }
        return sheet;
    }

    /**
     * init report excel file
     * @param fileName
     * @param sheetName
     * @param columnHeaders
     * @return
     * @throws IOException
     */
    public static File initReport(String fileName, @NonNull String sheetName, String... columnHeaders) throws IOException {

        if(fileName.equals(null)){
            fileName=getFileName();
        }
        File report = new File(fileName);
        if(report.exists()){
            workbook = new HSSFWorkbook(new FileInputStream(report));
        }
        else{
            report.getParentFile().mkdir();
            report.createNewFile();
            workbook = new HSSFWorkbook();
            workbook.write(new FileOutputStream(report));
        }
        cellStyleDefault = createStyle(workbook);
        sheet = createSheet(workbook, sheetName, columnHeaders);
        return report;
    }
    private static void createHeader(HSSFSheet sheet,  String... headers){
        //begining of a sheet
        if(sheet.getLastRowNum()==0){
            HSSFCellStyle cellStyle = createStyle(workbook);
            cellStyle.setFillPattern((short) 1);
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());//setting header gray background
            createRow(true, headers);
        }
    }

    public static void createRow(boolean isHeader , String... values){
        short ith = (short) (sheet.getLastRowNum()+1);
        HSSFRow row = sheet.createRow(ith);
        createHelper = workbook.getCreationHelper();
        for(int i=0;i<values.length-1; i++){
            Cell cell = row.createCell(i);
            if(isHeader)
                cellStyleDefault=createStyleForHeader(workbook);
            else
                cellStyleDefault =createStyle(workbook);

            cell.setCellStyle(cellStyleDefault);
            cell.setCellValue(createHelper.createRichTextString(values[i]));
        }
    }
    private static HSSFCellStyle getResultStyle(HSSFCellStyle style, String status){
        HSSFCellStyle newStyle = createStyle(workbook);
        newStyle.setFillPattern((short) 1);
        if(status.equals("pass")){
            newStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        }else if(status.equals("failed")){
            newStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        }
        else if(status.equals("skipped")){
            newStyle.setFillForegroundColor(IndexedColors.BROWN.getIndex());
        }
        else if(status.equals("ignored")){
            newStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        }
        else{
            newStyle = style;
        }

        return newStyle;
    }

    private static String generateFileName(){
        return generateFileName(fileName);
        }

    private static String generateFileName(@NonNull StringBuilder fileName){
        return fileName.append(fileName)
                .append(new SimpleDateFormat("yyyy-MM-dd_HHmmss")
                        .format(new Date()))
                .append(".xls")
                .toString();
    }

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


}
