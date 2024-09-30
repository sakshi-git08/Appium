package framework.pageObjects.android;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.AndroidActions;

public class CartPage extends AndroidActions {
	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;

	// driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"));
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
	// driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));

	// driver.findElement(By.id("android:id/button1")).click();
	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptButton;

	// driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;

	// driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkBox;

	public List<WebElement> getProductsList() {
		return productList;
	}

	public double getProductsSum() {
		int count = productList.size();
		double totalSum = 0;
		for (int i = 0; i < count; i++) {
			String amount = productList.get(i).getText();
			// $160.97 -> removing $
			Double price = getFormattedString(amount);
			totalSum = totalSum + price;
		}
		return totalSum;
	}

	public Double getTotalAmountDisplayed() {
		return getFormattedAmount(totalAmount.getText());
	}

	public void acceptTermsAndConditions() {
		longPressAction(terms);
		acceptButton.click();
	}

	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}

	public void submitOrder() {
		checkBox.click();
		proceed.click();
	}

}
