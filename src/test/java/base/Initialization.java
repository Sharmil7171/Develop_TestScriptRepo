package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Initialization {
	private static Initialization instance;
	public static WebDriver driver;
	public static WebDriverWait waitDriver;
	public static final int TIMEOUT = 30;
	public static final int PAGE_LOAD_TIMEOUT = 30;

	public static Initialization getInstance() {
		if (instance == null) {
			instance = new Initialization();
		}
		return instance;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public void initializeDrivers(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new ChromeDriver();

		}

		driver.manage().window().maximize();
		waitDriver = new WebDriverWait(driver, TIMEOUT);
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

	}

	public void openWebPage(String url) {
		driver.get(url);
	}

	public void tearDownDriver() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

}
