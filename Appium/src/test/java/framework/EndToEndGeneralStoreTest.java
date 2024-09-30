package framework;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import framework.pageObjects.android.CartPage;
import framework.pageObjects.android.FormPage;
import framework.pageObjects.android.ProductCatalogue;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class EndToEndGeneralStoreTest extends TestBase {
	@Test(dataProvider = "getData")
	public void hybridAppTest(String name, String gender, String country) throws InterruptedException {

		formPage.setNameField(name);
		formPage.setGender(gender);
		formPage.setCountrySelection(country);
		ProductCatalogue productCatalogue = formPage.submitForm();
		Thread.sleep(3000);
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCart();
		Thread.sleep(2000);

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
////		wait.until(ExpectedConditions.attributeContains(
////				driver.findElement(By.xpath("//android.widget.TextView[@text='Cart']")));
//		wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"),
//				"text", "Cart"));
		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsAndConditions();
		cartPage.submitOrder();
	}

//	@BeforeMethod
//	public void preSetUp() {
//		// screen to homepage
//		((JavascriptExecutor) driver).executeScript("mobile: intent",
//				ImmutableMap.of("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity"));
//	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "Sakshi Aggarwal", "female", "Aruba" }, { "Anju Lakshman", "female", "Argentina" } };
	}

}
