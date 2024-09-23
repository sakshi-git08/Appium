package MobileTesting.Appium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class DragAndDropDemo extends BaseTest {

	@Test
	public void swipeDemoTest() throws InterruptedException {
//view -> drag and drop 
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		WebElement destination = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));

		// Java
		dragAndDropAction(source, 654, 562);
		String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
		Assert.assertEquals(result, "Dropped!");

		Thread.sleep(2000);
	}
}
