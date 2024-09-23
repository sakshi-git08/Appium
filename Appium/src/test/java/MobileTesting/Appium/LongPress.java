package MobileTesting.Appium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class LongPress extends BaseTest {

	@Test
	public void LongPressGesture() throws InterruptedException {
		// views -> Expandable List -> Custom Adapter
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement peopleName = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		longPressAction(peopleName);
//		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
//				ImmutableMap.of("elementId", ((RemoteWebElement) peopleName).getId(), "duration", 2000));

		WebElement menu = driver.findElement(By.id("android:id/title"));
		String menuText = menu.getText();
		Assert.assertEquals(menuText, "Sample menu");
		Assert.assertTrue(menu.isDisplayed(), "The element is displayed...");
//		Assert.assertTrue(menu, menuText))
	}

}
