package framework;

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

import framework.pageObjects.android.CartPage;
import framework.pageObjects.android.FormPage;
import framework.pageObjects.android.ProductCatalogue;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class EndToEndGeneralStoreTest extends TestBase {
	@Test
	public void hybridAppTest() throws InterruptedException {

		formPage.setNameField("Sakshi Aggarwal");
		formPage.setGender("female");
		formPage.setCountrySelection("Aruba");
		ProductCatalogue productCatalogue = formPage.submitForm();
		Thread.sleep(3000);
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCart();

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

}
