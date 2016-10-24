package org.automation.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;


/**
 * Created by shantonu on 7/10/16.
 * todo =. completing using this pdfbox.apache.org/index.html
 */
public class PdfUtils {


    public static void main(String[] args) {
        selectFiles();
    }

    public static void read(String pathOfPdf) {
        PDDocument document = null;
        try {
            document = PDDocument.load(pathOfPdf.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(byte[] data) {

    }

    public static void selectFiles() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Word 2007+", "docx");
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(true);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File[] Files = chooser.getSelectedFiles();
            System.out.println("wait...");

            for (int i = 0; i < Files.length; i++) {
                String wordfile = Files[i].toString();
                System.out.println("FILE NAME = " + wordfile);// to know selected file
                word2pdf(wordfile, wordfile.substring(0, wordfile.indexOf('.')) + ".pdf");
            }
            System.out.println("complete");
        }
    }

    public static void word2pdf(String wordFile, String pdf) {
        try {
            FileInputStream fs = new FileInputStream(wordFile);
            XWPFDocument doc = new XWPFDocument(fs);
            Document pdfdoc = new Document(PageSize.A4, 72, 72, 72, 72);
            PdfWriter pwriter = PdfWriter.getInstance(pdfdoc, new FileOutputStream(pdf));
            pwriter.setInitialLeading(20);
            List<XWPFParagraph> plist = doc.getParagraphs();

            pdfdoc.open();
            for (int i = 0; i < plist.size(); i++) {
                XWPFParagraph pa = plist.get(i);
                List<XWPFRun> runs = pa.getRuns();
                for (int j = 0; j < runs.size(); j++) {
                    XWPFRun run = runs.get(j);
                    List<XWPFPicture> piclist = run.getEmbeddedPictures();
                    Iterator iterator = piclist.iterator();

                    while (iterator.hasNext()) {
                        XWPFPicture pic = (XWPFPicture) iterator.next();
                        XWPFPictureData picdata = pic.getPictureData();
                        byte[] bytepic = picdata.getData();
                        Image imag = Image.getInstance(bytepic);
                        pdfdoc.add(imag);

                    }
                    int color = getCode(run.getColor());
                    Font f = null;
                    if (run.isBold() && run.isItalic())
                        f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.BOLDITALIC, new BaseColor(color));
                    else if (run.isBold())
                        f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.BOLD, new BaseColor(color));
                    else if (run.isItalic())
                        f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.ITALIC, new BaseColor(color));
                    else if (run.isStrike())
                        f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.STRIKETHRU, new BaseColor(color));
                    else
                        f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.NORMAL, new BaseColor(color));
                    String text = run.getText(-1);
                    byte[] bs;
                    if (text != null) {
                        bs = text.getBytes();
                        String str = new String(bs, "UTF-8");
                        Chunk chObj1 = new Chunk(str, f);
                        pdfdoc.add(chObj1);
                    }

                }
                pdfdoc.add(new Chunk(Chunk.NEWLINE));
            }
            pdfdoc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getCode(String code) {
        int colorCode;
        if (code != null)
            colorCode = Long.decode("0x" + code).intValue();
        else
            colorCode = Long.decode("0x000000").intValue();
        return colorCode;
    }
}
