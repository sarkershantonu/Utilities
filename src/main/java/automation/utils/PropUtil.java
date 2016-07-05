public class PropUtil {

    private static Properties prop;
    private static String propertyRoot = "/src/main/resources/";

    public static String getSysProperty(@NonNull String sysProperty) {
        return System.getProperty(sysProperty);
    }

    public static void setSysProerty(@NonNull String name, @NonNull String value) {
        System.setProperty(name, value);
    }

    public static String getSysProperty(@NonNull String propertyFileName, @NonNull String sysProperty) {
        return System.getProperty(sysProperty);
    }

    public static void setProperty(@NonNull String propertyFileName, @NonNull String name, @NonNull String value) {

    }

    public static String read(String propertyFileName, String name) {
        try {
            prop = read(propertyFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(name);
    }

    public static Properties read(String propertyFileName) throws IOException {
        prop = new Properties();
        File currentDirFile = new File(getSysProperty("user.dir"));
        String rootFolder = currentDirFile.getAbsolutePath();
        InputStream input = new FileInputStream(rootFolder + propertyRoot + propertyFileName);
        prop.load(input);
        input.close();
        return prop;
    }

}
