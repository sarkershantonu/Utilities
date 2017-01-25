package org.automation;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CSVHelper {

   public static List<Summary_output_do> readSummaryFile(String filePath) throws IOException {
        List<Summary_output_do> output = new ArrayList<>();

        CSVFormat format = CSVFormat.TDF.withHeader(Summary_output_do.FILE_HEADERS);
        Reader in = new FileReader(filePath);
        CSVParser parser = new CSVParser(in, format);
        List<CSVRecord> recs = parser.getRecords();

        for (int i = 1; i < recs.size(); i++) {
            CSVRecord record = recs.get(i);
            Summary_output_do rec = new Summary_output_do(
                    Long.getLong(record.get(Summary_output_do.column_1)),
                    Long.getLong(record.get(Summary_output_do.column_2)),
                    Double.valueOf(record.get(Summary_output_do.column_3)),
                    Double.valueOf(record.get(Summary_output_do.column_4)));
            output.add(rec);
        }
        return output;
    }
   
       public static List<Detail_output_do> readDetailsFile(String filePath, String dateFormat) throws IOException {
        List<Detail_output_do> allRecordsOfDetail = new ArrayList<>();
        CSVFormat format = CSVFormat.TDF.withHeader(Detail_output_do.FILE_HEADERS);
        Reader in = new FileReader(filePath);
        CSVParser parser = new CSVParser(in, format);
        List<CSVRecord> recs = parser.getRecords();
        for (int i = 1; i < recs.size(); i++) {
            CSVRecord record = recs.get(i);
            Detail_output_do rec = new Detail_output_do(
                    record.get(Detail_output_do.column_1),
                    record.get(Detail_output_do.column_2),

                    WeekUtils.convertWithFprmat((record.get(Detail_output_do.column_3)), dateFormat),
                    record.get(Detail_output_do.column_4),
                    Long.getLong(record.get(Detail_output_do.column_5)),
                    Double.valueOf(record.get(Detail_output_do.column_6)),
                    Double.valueOf(record.get(Detail_output_do.column_7)),
                    Long.getLong(record.get(Detail_output_do.column_8)),
                    Long.getLong(record.get(Detail_output_do.column_9)),
                    Double.valueOf(record.get(Detail_output_do.column_10)),
                    Double.valueOf(record.get(Detail_output_do.column_11)));
            allRecordsOfDetail.add(rec);
        }
        return allRecordsOfDetail;
    }
public static List<Detail_output_do> readDetailsFile(String filePath) throws IOException {
        return readDetailsFile(filePath, "MM/dd/yyyy");
    }
}
