package utils;

import java.math.MathContext;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Command;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AndroidDriverBase extends AndroidDriver {
	private int x;
	private int y;
	private TouchAction ac;
	private AndroidDriverCommand command=new AndroidDriverCommand();

	public AndroidDriverBase(URL remoteAddress, Capabilities desiredCapabilities) {
		super(remoteAddress, desiredCapabilities);
		// TODO Auto-generated constructor stub
		this.x=super.manage().window().getSize().getWidth();
		this.y = super.manage().window().getSize().getHeight();
		ac=new TouchAction(this);
	}

	private void upSwipe(int duration) {
		super.swipe(x / 2, y * 9 / 10, x / 2, y / 10, duration);

	}

	private void downSwipe(int duration) {
		super.swipe(x / 2, y / 10, x / 2, y * 9 / 10, duration);
	}

	private void rightSwipe(int duration) {
		super.swipe(x / 10, y / 2, x * 9 / 10, y / 2, duration);
	}

	private void leftSwipe(int duration) {
		super.swipe(x * 9 / 10, y / 2, x / 10, y / 2, duration);
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
		WebElement start = super.findElement(startelement);
		beginlocation = start.getLocation();
		WebElement end;
		super.manage().timeouts().implicitlyWait(2000, TimeUnit.MICROSECONDS);

		while (true) {
			try {
				end = super.findElement(endelement);
				break;
			} catch (Exception e) {
				switch (direction.toLowerCase()) {
				case "up":
					super.swipe(beginlocation.getX(), beginlocation.getY(), beginlocation.getX(),
							beginlocation.getY() - 500, 2000);
					break;
				case "down":
					super.swipe(beginlocation.getX(), beginlocation.getY(), beginlocation.getX(),
							beginlocation.getY() + 500, 2000);
					break;
				case "left":
					super.swipe(beginlocation.getX(), beginlocation.getY(), beginlocation.getX() - 500,
							beginlocation.getY(), 2000);
					break;
				case "right":
					super.swipe(beginlocation.getX(), beginlocation.getY(), beginlocation.getX() + 500,
							beginlocation.getY(), 2000);
				}
				continue;
			}
		}
	}
	/**
	 * 点击元素方法
	 * @param element 要点击的元素
	 * 
	 * */
	public void click(By element) throws NoSuchElementException {

		
		WebElement ck = super.findElement(element);
		ck.click();
	}
	/**
	 *发送指定字符串
	 *@param element 要发送字符串的元素
	 *@param keyWord 要发送的字符串
	 * */
	public void sendKey(By element ,String keyWord) throws NoSuchElementException{
		super.findElement(element).sendKeys(keyWord);
	}
	/**
	 * 点击方法
	 * @param element 要点击的元素
	 * */
	public void tap(By element) {
		ac.tap(super.findElement(element)).release().perform();
	}
	/**
	 * 点击屏幕内所有List
	 * @param by 元素
	 * */
	public void browseHomeList(By by) {
		List<AndroidElement> list = super.findElements(by);
		for (AndroidElement lo : list) {
			lo.click();
			super.pressKeyCode(4);
		}
	}
	/**
	 * 
	 * 清除文本框的方法
	 * @param text 文本内容
	 * */
	public void clearText(String text){
		super.pressKeyCode(123);
		for(int i=0;i<text.length();i++){
			super.pressKeyCode(67);
		}
	}
	public void getPhoneSize(){
		AndroidDriverCommand command = new AndroidDriverCommand();
		List<String> list = command.comm("adb shell wm size");
		String regEx = "[0-9]\\d*";
		StringBuilder sb=new StringBuilder();
		Pattern pattern = Pattern.compile(regEx);
		for (String s : list) {
			Matcher matcher = pattern.matcher(s);
			while (matcher.find()) {
				
				sb.append(matcher.group());
			}
		}
		
		System.out.println(sb);

	}

}
