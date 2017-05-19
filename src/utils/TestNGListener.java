package utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestNGListener extends TestListenerAdapter {
    private static AndroidDriver<AndroidElement> driver;

    //LogUtil log = new LogUtil(this.getClass());

    public static void setDriver(AndroidDriver<AndroidElement> driver) {
        TestNGListener.driver = driver;
    }
	@Override
	public void onTestFailure(ITestResult tr) {
       // log.error("Test Failure");
        super.onTestFailure(tr);

        ScreenShotOnFailure screenShot = new ScreenShotOnFailure(driver);
        screenShot.getScreenShot();
    }
	

}
