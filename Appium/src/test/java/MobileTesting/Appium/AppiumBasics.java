package MobileTesting.Appium;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumBasics {
	@Test
	public void AppiumTest() throws MalformedURLException{
		//code to start server
		//AndroidDriver, IOSDriver
		//Appium code -> Appium Server(has all capabilities and reponsibilities) -> Mobile Device
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Sakshiemulator");
		options.setApp("C:\\Users\\DELL\\Sakshi\\MobileTesting\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		
		AndroidDriver driver = new AndroidDriver(new URL(" http://127.0.0.1:4723"), options);
/*
 * 		So, URL is deprecated after JDK 20 so we can use it in a below manner as Appium accepts Driver
 * 		so we can create an object of URI and convert it into URL
		AndroidDriver driver = new AndroidDriver(new URI(" http://127.0.0.1:4723").toURL(), options);

*/
		//Actual Automation
		driver.quit();
	}

}
