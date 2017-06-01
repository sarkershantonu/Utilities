package com.att.blueprint.util;

import java.io.File;


public class FileUtil {
    /**
     * This converts java path format to windows
     * @param path_generic = Property has path with current workspace ./
     * @return
     */
    public static String getWindowsFormatPath(String path_generic){
        String path = new File(path_generic).getAbsolutePath();
        return path.replace("/", "\\\\");
    }
}
