package MobileTesting.Appium;

import org.openqa.selenium.By;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumBasics extends BaseTest {
	@Test
	public void wifiSettingsName() throws MalformedURLException, InterruptedException {
//		driver.findElement(By.); This is used both for web & mobile this is coming from selenium jar
		// click on Preferences -> Preference dependencies
//		driver.rotate(ScreenOrientation.PORTRAIT);
//		Thread.sleep(2000);
		/*
		 * App Package & App Activity adb shell dumpsys window | find “mCurrentFocus”
		 */
		String packageName = "io.appium.android.apis";
		String activityName = "io.appium.android.apis.preference.PreferenceDependencies";
		Activity activity = new Activity(packageName, activityName);
//		driver.startActivity(activity);
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
//		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		// set wifi name
//		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		// Rotating the device by 90 degrees
//		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
//		driver.rotate(landscape);
//		driver.rotate(ScreenOrientation.LANDSCAPE);
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		// checking whether wifi settings popup is opened or not before entering wifi
		// name
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");
		// copy & paste
		// copy to clipboard(text gets copied on to the virtual clipboard)
		driver.setClipboardText("Sakshi_Wifi");
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		driver.pressKey(new KeyEvent(AndroidKey.ENTER)); // these are to return back and forward to click some key on
															// mobile keyboard
		driver.findElements(AppiumBy.className("(android.widget.Button)")).get(1).click();
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));

	}

}
