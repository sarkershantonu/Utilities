

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class IoUtils {
    private static final String SRC_MAIN_RESOURCES_PRODUCT_NAME_IDS_TXT = "src/test/resources/product_name_ids.txt";
    private static final Charset CHARSET = Charset.forName("US-ASCII");
    protected static Logger logger = LoggerFactory.getLogger(IoUtils.class);

    public static void write(List<String> items) {
        Path file = Paths.get(SRC_MAIN_RESOURCES_PRODUCT_NAME_IDS_TXT);
        try (BufferedWriter writer = Files.newBufferedWriter(file, CHARSET)) {
            for (String item : items) {
                writer.append(item).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Ids were written to: " + file.toAbsolutePath());
    }

    public static long getProductId() {
        Path file = Paths.get(SRC_MAIN_RESOURCES_PRODUCT_NAME_IDS_TXT);
        List<String> ids = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file, CHARSET)) {
            String line;
            while ((line = reader.readLine()) != null) {
                ids.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Long.valueOf(ids.get(RandomUtils.nextInt(0, ids.size() - 1)));
    }

    private String toString(Path xmlFilePath) throws IOException {
        return new String(Files.readAllBytes(xmlFilePath), Charsets.UTF_8);
    }

    private List<Path> getFilePaths(String xmlFilter, Path pathToDir) throws IOException {
        verifyThatExists(pathToDir);
        verifyThatPathToDir(pathToDir);
        return getPathsToFiles(pathToDir, xmlFilter);
    }

    private void verifyThatExists(Path pathToDir) throws FileNotFoundException {
        if (!Files.exists(pathToDir)) {
            throw new FileNotFoundException(String.format("File with path '%s' wasn't found", pathToDir
                    .toAbsolutePath().toString()));
        }
    }

    private List<Path> getPathsToFiles(Path pathToDir, String filter) throws IOException {
        List<Path> workbooksPath = Lists.newArrayList();
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(pathToDir, filter)) {
            for (Path entry : dirStream) {
                workbooksPath.add(entry);
            }
        }
        if (workbooksPath.size() != 1) {
            throw new IllegalArgumentException(String.format("Input directory contains '%s' %s files, but must " +
                            "contain 1",
                    workbooksPath.size(), filter));
        }
        return workbooksPath;
    }

    private void verifyThatPathToDir(Path pathToDir) {
        if (!Files.isDirectory(pathToDir)) {
            throw new IllegalArgumentException(String.format("%s is not a directory", pathToDir.toAbsolutePath().toString()));
        }
    }

    public String loadXml(Path dirPath, String filter) throws IOException {
        List<Path> paths = getFilePaths(filter, dirPath);
        Preconditions.checkState(paths.size() == 1, "Unsuccessfull attempt to read xml files");
        return toString(paths.get(0));
    }

    public static HSSFWorkbook loadWorkbook(String filePath) {
        HSSFWorkbook expectedWorkbook = null;
        try {
            expectedWorkbook = new HSSFWorkbook(new FileInputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        return expectedWorkbook;
    }
}

