package utils;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import page.HomePage;

import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;

public class Suit {
	public Logger log =Logger.getLogger(Suit.class);
	public AndroidDriver<AndroidElement> driver = null;
	public Action ac=null;
	public HomePage hpage=null;
  @BeforeSuite
  public void beforeSuite() throws IOException {
	  DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Android");
		caps.setCapability("appPackage", "com.sohu.newsclient");
		caps.setCapability("appActivity", "com.sohu.newsclient.app.SplashActivity");
		//caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.ss.android.article.news.activity.SplashActivity");
		//caps.setCapability("appWaitActivity", "com.tencent.qqlive.ona.activity.MainActivity");
		caps.setCapability("unicodeKeyboard", "true");
		caps.setCapability("resetKeyboard", "true");
		URL u = new URL("http://127.0.0.1:4723/wd/hub/");
		driver = new AndroidDriver<AndroidElement>(u, caps);
		TestNGListener.setDriver(driver);
		ac=new Action(driver);
		hpage=new HomePage();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }

  @AfterSuite
  public void afterSuite() {
	  driver.quit();
  }

}
