package test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import utils.Suit;

public class SouHuNews extends Suit {
  @Test(description="����ģ��")
  public void news() {
	  ac.click(By.id(hpage.newsBtnId));
  }
}
