package MobileTesting.Appium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ECommerce_TC_2 extends BaseTest {

	@Test
	public void searchProduct() throws InterruptedException {
		// Searching for Jordan 6 Rings -> Adding it to cart -> click on cart -> Verify
		// the product added to cart is visible
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
		// android.widget.Toast
		// scroll till Jordan 6 Rings
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Jordan 6 Rings']")).click();
		int productAddToCartsList = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		for (int i = 0; i < productAddToCartsList; i++) {
			String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i)
					.getText();
			if (productName.equalsIgnoreCase("Jordan 6 Rings")) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
//		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"),
				"text", "Cart"));
		String lastPageProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(lastPageProduct, "Jordan 6 Rings");
	}

}
