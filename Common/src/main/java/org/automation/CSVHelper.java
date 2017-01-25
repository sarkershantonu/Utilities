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
}
