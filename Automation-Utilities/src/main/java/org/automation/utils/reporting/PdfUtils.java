package org.automation.utils.reporting;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;

/**
 * Created by shantonu on 7/10/16.
 * todo =. completing using this https://pdfbox.apache.org/index.html
 */
public class PdfUtils {

    public static void readPdf(String pathOfPdf){
        PDDocument document = null ;
        try {
            document = PDDocument.load(pathOfPdf.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
