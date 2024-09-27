package MobileTesting.Appium;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ECommerce_TC_4 extends BaseTest {
	@Test
	public void hybridAppTest() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sakshi Aggarwal");
		driver.hideKeyboard(); // to hide keyboard since you added text in the field so keyboard would have
								// been open.
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		/*
		 * Here, once we clicked on first item the item will display -> Added To Cart
		 * text under the product name. So, eventually there would be only one product
		 * with Add to cart which would be 0th index
		 */
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.attributeContains(
//				driver.findElement(By.xpath("//android.widget.TextView[@text='Cart']")));
		wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"),
				"text", "Cart"));
		List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		int count = productPrices.size();
		double totalSum = 0;
		for (int i = 0; i < count; i++) {
			String amount = productPrices.get(i).getText();
			// $160.97 -> removing $
			Double price = getFormattedString(amount);
			totalSum = totalSum + price;
		}
		String totalPurchaseAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
				.getText();
		Double displayFormattedSum = getFormattedString(totalPurchaseAmount);
		Assert.assertEquals(totalSum, displayFormattedSum);
		// long press on terms and conditions
		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longPressAction(ele);
		// close popup
		driver.findElement(By.id("android:id/button1")).click();
		// tick checkbox
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		// click on visit to website button
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(20000);
		// Hybrid - Google page -> Here, we are trying to switch the context from
		// AndroidDriver to the one which
		// can work in web view also.
		Set<String> contexts = driver.getContextHandles(); // Get the names of available contexts.
		for (String contextName : contexts) {
			System.out.println(contextName);
		}
		/*
		 * NATIVE_APP WEBVIEW_com.androidsample.generalstore
		 */
		driver.context("WEBVIEW_com.androidsample.generalstore"); // chromedriver
		driver.findElement(By.name("q")).sendKeys("BK Shivani");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}
//124.0.6367
}
