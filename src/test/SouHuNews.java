package test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import utils.Suit;

public class SouHuNews extends Suit {
  @Test(description="пбнедё©И")
  public void news() {
	  ac.click(By.id(hpage.newsBtnId));
  }
}
