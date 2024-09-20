package MobileTesting.Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
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
		options.setApp("D:\\Sakshi\\MobileTestingApks\\resources\\ApiDemos-debug.apk");

		driver = new AndroidDriver(new URL(" http://127.0.0.1:4723"), options);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
