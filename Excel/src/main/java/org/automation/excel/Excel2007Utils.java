package org.automation.excel;



import lombok.Getter;
import lombok.NonNull;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}