

import au.com.bytecode.opencsv.CSVReader;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.Assert.fail;

public class TemplateOverrideExportUtils {
    private static List<String[]> parsedData;

    public static List<String[]> exportTemplateOverride(long code) {
        String exportUrl = Config.BASE_URL + String.format("template-override/exportCsv?searchCriteria.templateId=%s", code);


        String downloaded = null;
        try {
            HttpManager httpManager = HttpManager.getInstance();

            HttpResponse export = httpManager.doGet(exportUrl);
            downloaded = EntityUtils.toString(export.getEntity());

            InputStream is = new ByteArrayInputStream(downloaded.getBytes(Charset.forName("UTF-8")));

            CSVReader reader = new CSVReader(new InputStreamReader(is));
            parsedData = reader.readAll();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unsuccessful attempt to get Template Override Export file");
        }
        return parsedData;
    }
}
