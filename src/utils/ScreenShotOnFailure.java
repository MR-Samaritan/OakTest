package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ScreenShotOnFailure {
    private AndroidDriver<AndroidElement> driver=null;
    // ����ʧ�ܽ��������·��
    private String path;
    

    public ScreenShotOnFailure (AndroidDriver<AndroidElement> driver){
        this.driver=driver;
        path=System.getProperty("user.dir")+ "//snapshot//"+ this.getClass().getSimpleName()+"_"+getCurrentTime() + ".jpg";
    }

    public void getScreenShot() {

        File screen = driver.getScreenshotAs(OutputType.FILE);
        File screenFile = new File(path);
        try {
            FileUtils.copyFile(screen, screenFile);
            //log.info("��ͼ�����·��:" + path);
            System.out.println("��ͼ�����·��:"+path);
        } catch (Exception e) {
            //log.error("��ͼʧ��");
        	System.out.println("��ͼʧ��");
            e.printStackTrace();
        }
    }


    /**
     * ��ȡ��ǰʱ��
     */
    public String getCurrentTime(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String currentTime=sdf.format(date);
        return currentTime; 
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
	
	

}
