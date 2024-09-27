package MobileTesting.Appium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ECommerce_TC_1 extends BaseTest {
	@Test
	public void fillForm() throws InterruptedException {
//		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sakshi Aggarwal");
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
		String toastMsg = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMsg, "Please enter your name");
	}

}
