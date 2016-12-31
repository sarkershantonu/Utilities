package org.automation.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by shantonu on 9/4/16.
 */
public class RuntimeUtil {

    private RuntimeUtil(){}
    public static void loadNativeLibraryBy(String fileNameNotNull){
        if(null==fileNameNotNull||fileNameNotNull==""){
        throw new NullPointerException("null path");
        }else {
        Runtime.getRuntime().load(fileNameNotNull);
        }
    }
    public static void loadNativeLibraryByName(String libName){
        if(null==libName||libName==""){
            throw new NullPointerException("null path");
        }else {
            Runtime.getRuntime().loadLibrary(libName);
        }
    }
    public static void tracing(boolean isEnable){
        Runtime.getRuntime().traceInstructions(isEnable);
    }
    public static void tracingMethod(boolean isEnable){
        Runtime.getRuntime().traceMethodCalls(isEnable);
    }
    public static Long getFreeMemory(){

        return Runtime.getRuntime().freeMemory();
    }
    public static Long getTotalMemory(){

        return Runtime.getRuntime().totalMemory();
    }
    public static Long getMaxMemory(){

        return Runtime.getRuntime().maxMemory();
    }
    public static void shutdownJVM(){
        Runtime.getRuntime().exit(0);
    }
    public static void shutdownJVMForcely(){
        Runtime.getRuntime().halt(0);
    }
    public static void initiateShutdown(Thread hook){
        Runtime.getRuntime().addShutdownHook(hook);
    }
    public static void removedHookedThreadFromShutdown(Thread hook){
        Runtime.getRuntime().removeShutdownHook(hook);
    }
    public static int processorInJVM(){
        return Runtime.getRuntime().availableProcessors();
    }
    public static void collect(){
        Runtime.getRuntime().gc();
    }

    public static Process run(String[] cmdarray, String[] envParam,File dir ) throws IOException {
        return Runtime.getRuntime().exec(cmdarray,envParam,dir);
    }
}
