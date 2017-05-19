package utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Action {
	Logger log = Logger.getLogger(Action.class);
	private AndroidDriver<AndroidElement> driver;
	private int x;
	private int y;
	TouchAction ac;

	public Action(AndroidDriver<AndroidElement> driver) {

		this.driver = driver;
		this.x = driver.manage().window().getSize().getWidth();
		this.y = driver.manage().window().getSize().getHeight();
		ac = new TouchAction(driver);

	}

	private void upSwipe(int duration) {
		driver.swipe(x / 2, y * 9 / 10, x / 2, y / 10, duration);

	}

	private void downSwipe(int duration) {
		driver.swipe(x / 2, y / 10, x / 2, y * 9 / 10, duration);
	}

	private void rightSwipe(int duration) {
		driver.swipe(x / 10, y / 2, x * 9 / 10, y / 2, duration);
	}

	private void leftSwipe(int duration) {
		driver.swipe(x * 9 / 10, y / 2, x / 10, y / 2, duration);
	}

	public void Swipe(String direction, int duration) {
		switch (direction.toLowerCase()) {
		case "up":
			this.upSwipe(duration);
			break;
		case "down":
			this.downSwipe(duration);
			break;
		case "right":
			this.rightSwipe(duration);
			break;
		case "left":
			this.leftSwipe(duration);
			break;

		default:
			System.out.println("please enter up/down/right/left");
			break;
		}

	}

	/**
	 * @param startelement 开始位置
	 * 
	 * @param endelement 目标元素
	 * 
	 * @param direction “up down left right”
	 */
	public void SwipeTo(By startelement, By endelement, String direction) {
		Point beginlocation;
		WebElement start = driver.findElement(startelement);
		beginlocation = start.getLocation();
		WebElement end;
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MICROSECONDS);

		while (true) {
			try {
				end = driver.findElement(endelement);
				break;
			} catch (Exception e) {
				switch (direction.toLowerCase()) {
				case "up":
					driver.swipe(beginlocation.getX(), beginlocation.getY(), beginlocation.getX(),
							beginlocation.getY() - 500, 2000);
					break;
				case "down":
					driver.swipe(beginlocation.getX(), beginlocation.getY(), beginlocation.getX(),
							beginlocation.getY() + 500, 2000);
					break;
				case "left":
					driver.swipe(beginlocation.getX(), beginlocation.getY(), beginlocation.getX() - 500,
							beginlocation.getY(), 2000);
					break;
				case "right":
					driver.swipe(beginlocation.getX(), beginlocation.getY(), beginlocation.getX() + 500,
							beginlocation.getY(), 2000);
				}
				continue;
			}
		}
	}

	public void click(By element) throws NoSuchElementException {

		
			WebElement ck = driver.findElement(element);
			ck.click();
	}
	public void sendKey(By element ,String keyWord) throws NoSuchElementException{
		driver.findElement(element).sendKeys(keyWord);
	}
	

	public void tap(By element) {
		ac.tap(driver.findElement(element)).release().perform();
	}
	/**
	 * 点击屏幕内所有List
	 * @param by 元素
	 * */
	public void browseHomeList(By by) {
		List<AndroidElement> list = driver.findElements(by);
		for (AndroidElement lo : list) {
			lo.click();
			driver.pressKeyCode(4);
		}
	}
	/**
	 * 
	 * 清除文本框的方法
	 * @param text 文本内容
	 * */
	public void clearText(String text){
		driver.pressKeyCode(123);
		for(int i=0;i<text.length();i++){
			driver.pressKeyCode(67);
		}
	}

}
