package MobileTesting.Appium;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class ScrollDemo extends BaseTest {

	@Test
	public void scrollDownTest() throws InterruptedException {
		// view -> scroll until you get Text called webView
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		// Need to create an object of UiScrollable which accepts object of UiSelector
		// using scrollIntoView
		// which is coming from UiScrollable. Everytime you just need to change text you
		// want to scroll to that's it. This is used where to scroll is know prior
//		driver.findElement(
//				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));

		// where to scroll is not known prior.
		// Java
		scrollToEndAction();
	}

}
