package org.automation.selenium.tracking;


import org.apache.commons.io.FileUtils;
import org.automation.utils.common.PropertyUtil;
import org.automation.selenium.SeleniumUtilBase;

import org.automation.selenium.browser.Browser;
import org.automation.selenium.javascripts.JavaScriptSeleniumUtil;
import org.automation.selenium.page.PageUtil;
import org.automation.utils.config.ConfigHelper;
import org.automation.utils.io.FileUtilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import com.google.common.io.Files;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shantonu on 4/19/16.
 */
public class ScreenShotUtil extends SeleniumUtilBase {

    public static String ScreenPath = PropertyUtil.getUserDir()+ConfigHelper.screenshotImagerFolder;//PropertyUtil.getSysProperty("screenshot.folder");

    public ScreenShotUtil(WebDriver aDriver) {
        super(aDriver);
    }

    /**
     * This is default screenshot using webdriver
     * @param name
     * @param isError
     * @return
     */
    public void takeScreenShot(String name, boolean isError){
        StringBuilder fileNama = new StringBuilder(getFileName(name,isError));
        File screenShot = new File(fileNama.toString());
        screenShot.getParentFile().mkdir();
        new PageUtil(this.driver).waitForPageLoad();
        if(!new PageUtil(this.driver).isPageLoaded()){
            File srcFile = ((TakesScreenshot) Browser.getInstance()).getScreenshotAs(OutputType.FILE);
            try{
                FileUtils.copyFile(srcFile,screenShot);
            } catch (IOException e) {
                //todo default exception managemebt
            }
        }else
        {
            screenShotByJS(screenShot);
        }
    }


    /**
     * This is fully based on JS library used by project.. 100% project specific. use different library if you need to
     * @param screenShot
     * Todo => hafl done, need to complete
     * main source => https://github.com/niklasvh/html2canvas
     */

    private void screenShotByJS(File screenShot){
        String screenshotjs;
        String base64js_getImage;
        String base64_imageContant;

        try{
        screenshotjs = new JavaScriptSeleniumUtil(this.driver).readJsLibrary("html2canvas.min.js");
        /**The main JS => Change this if it does not works
         * function injectHtml2Canvas()
         {
         {
         %s
         }
         }
         var script = document.createElement('script');
         script.appendChild(document.createTextNode('('+ injectHtml2Canvas +')();'));
         (document.body || document.head || document.documentElement).appendChild(script);
         */
        base64js_getImage = String.format("function injectHtml2Canvas()\n                "
                +"{{\n%s\n}}\n \n                "
                +"var script = document.createElement(\'script\');\n                "
                +"script.appendChild(document.createTextNode(\'(\'+ injectHtml2Canvas +\')();\'));\n                "
                +"(document.body || document.head || document.documentElement).appendChild(script);", new Object[]{screenshotjs});
        base64_imageContant =  "return window.html2canvas != undefined;";

        boolean i = ((Boolean) new JavaScriptSeleniumUtil(driver).getJsExecutor().executeScript(base64_imageContant,new Object[0])).booleanValue();
        if(!i){
            new JavaScriptSeleniumUtil(driver).getJsExecutor().executeScript(base64js_getImage, new Object[0]);
        }
        }catch (Exception e){

        }

    }
    private String getFileName(String testname, boolean isError){
        SimpleDateFormat date = ConfigHelper.dateFormat;
        SimpleDateFormat time  = ConfigHelper.timeFormat;
        StringBuilder file = new StringBuilder();
        Date screenDate = new Date();
        
        if(ScreenPath!=null){
            file.append(ScreenPath).append(PropertyUtil.getSeperator());
        }
        file.append(PropertyUtil.getUserDir());
        file.append(PropertyUtil.getSeperator())
                .append("target")
                .append(PropertyUtil.getSeperator())
                .append(date.format(screenDate));
        if(isError){
            file.append(PropertyUtil.getSeperator()).append("error_");
        }else
        {
            file.append(PropertyUtil.getSeperator()).append("screenshot_");
        }
        file.append(testname+"_").append(time.format(screenDate));
        file = FileUtilities.trimLimit(file);
        file.append(ConfigHelper.screenshotType);
        return file.toString();
    }


    private void saveImage(String name, BufferedImage image){
        File output = new File(ScreenPath+name+"."+ConfigHelper.screenshotType);
        try {
            ImageIO.write(image,ConfigHelper.screenshotType,output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//************** Bottom items are using Ashot********************//
    /**
     * Take screenshot with Ashot
     * @param element
     * @param aDriver
     * @param name
     */
    public void takeWith(WebElement element, WebDriver aDriver,String name){
        AShot aShot = new AShot();
        Screenshot screenshot = aShot.takeScreenshot(aDriver,element);
        saveImage(name,screenshot.getImage());
    }


    public void takeFullScreen(String name, WebDriver aDriver){
        takeFullScreen(name, aDriver, 500);
    }
    /***
     * This will waiti 500ms(default) to scroll and take full screenshot
     * This view point, you need to change based on application behavior
     * @param name
     * @param aDriver
     */

    public void takeFullScreen(String name, WebDriver aDriver, int scrollTimeout){
        saveImage(name, new AShot().shootingStrategy(ShootingStrategies.viewportPasting(scrollTimeout)).takeScreenshot(aDriver).getImage());
    }
}
