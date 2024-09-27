package MobileTesting.Appium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowserTest extends BrowserBaseTest {
	@Test
	public void browserTest() throws InterruptedException {
//		driver.get("https://google.com");
//		System.out.println(driver.getTitle());
//		driver.findElement(By.name("q")).sendKeys("BK Shivani");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//		Thread.sleep(2000);
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
		driver.findElement(By.xpath("//li[@class='nav-item active']//a[@class='nav-link']")).click();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000)", ""); // Scroll
		String text = driver.findElement(By.xpath("//a[normalize-space()='Devops']")).getText();
		Assert.assertEquals(text, "Devops");
		Thread.sleep(2000);
	}

}
