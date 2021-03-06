package org.automation.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//this is regular expression based utilities on file names
public class FileNameUtils{
    private String filePath;
    private FileNameUtils(){}
    public FileNameUtils(String path){
        this.filePath =path;
        
    }
    public String getExtention(){
        String filename= new File(this.filePath).getName();
        String searchPattern = "\\.(\\w+)";
        Matcher m = Pattern.compile(searchPattern).matcher(filename);
        return m.find()?m.group():"";
    }
}
