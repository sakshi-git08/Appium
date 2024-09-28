package framework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TestBase {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void configureAppium() throws MalformedURLException {
		// code to start server
		// AndroidDriver, IOSDriver
		// Appium code -> Appium Server(has all capabilities and reponsibilities) ->
		// Mobile Device
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\DELL\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		/*
		 * So, URL is deprecated after JDK 20 so we can use it in a below manner as
		 * Appium accepts Driver so we can create an object of URI and convert it into
		 * URL AndroidDriver driver = new AndroidDriver(new
		 * URI(" http://127.0.0.1:4723").toURL(), options);
		 * 
		 */

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Sakshiemulator");
		options.setChromedriverExecutable(
				"C:\\Users\\DELL\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		// options.setApp("D:\\Sakshi\\MobileTestingApks\\resources\\ApiDemos-debug.apk");
		options.setApp("D:\\Sakshi\\MobileTestingApks\\resources\\General-Store.apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		if (driver == null) {
			throw new RuntimeException("Failed to initialize the Appium driver");
		}
	}

	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
	}

	public void scrollToEndAction() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScrollMore); // it will keep scrolling until it gets true that it needs to scroll more
		// once it is false you can't scroll more.
	}

	public void swipeAction(WebElement ele, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) ele).getId(), "direction", direction, "percent", 0.25));
	}

	public void dragAndDropAction(WebElement ele, int cordX, int cordY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "endX", cordX, "endY", cordY));
	}

	public Double getFormattedString(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
