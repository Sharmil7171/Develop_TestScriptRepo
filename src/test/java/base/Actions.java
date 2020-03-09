package base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions {
	static WebDriver webDriver = Initialization.getInstance().getWebDriver();

	public static boolean isElementPresent(WebElement webElement) {
		try {
			return webElement.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void clickElement(WebElement webElement) {
		if (isElementPresent(webElement)) {
			webElement.click();
		} else {
			throw new NoSuchElementException("Element is not present on the page.");
		}
	}

	public static void addTextToInputBox(WebElement webElement, String key) {
		if (isElementPresent(webElement)) {
			webElement.sendKeys(key);
		} else {
			throw new NoSuchElementException("Element is not present on the page.");
		}
	}

	public static boolean waitUntilElementDisplayed(final WebElement webElement, int timeout) {
		webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(webDriver, timeout);
		ExpectedCondition<Boolean> elementIsDisplayed = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver arg0) {
				try {
					webElement.isDisplayed();
					return true;
				} catch (NoSuchElementException e) {
					return false;
				} catch (StaleElementReferenceException f) {
					return false;
				}
			}
		};

		boolean display = wait.until(elementIsDisplayed);
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return display;
	}

	public static boolean isTextVisibleOnPage(String text) {
		try {
			boolean visible = webDriver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")).isDisplayed();
			System.out.println(text + " is Visible on the page.");
			return visible;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public static void sleep(int timeout) {
		webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

}
