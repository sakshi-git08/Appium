package MobileTesting.Appium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ECommerce_TC_3 extends BaseTest {

	@Test
	public void verifyPurchasAmount() throws InterruptedException {
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.textToBePresentInElementValue(
				driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "Cart"));
//		wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"),
//				"text", "Cart"));
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
	}

}
