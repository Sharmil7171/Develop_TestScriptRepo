package Framework;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {
	private static final Logger LOGGER = Logger.getLogger("");
	private static boolean isRemote = true;
	private static String hubIp = "";
	private static String stBrowserVersion = "";

	public static WebDriver getDriver(String browser, String testEngineHomeFolder, String hubIp,
			String browserVersion) throws Exception {
		hubIp = hubIp;
		stBrowserVersion = browserVersion;

		isRemote = false;
		return getLocalDriver(browser, testEngineHomeFolder);
	}

	
	public static WebDriver getLocalDriver(String browser, String testEngineHomeFolder) throws Exception{
		String executableChromeDriverPath = testEngineHomeFolder + File.separator + "chromedriver.exe";
		return null;

//		switch (browser) {
//		case "fireFox":
//			String executableGeckoDriverPath = testEngineHomeFolder + File.separator + "geckodriver.exe";
//			System.setProperty("webdriver.gecko.driver", executableGeckoDriverPath);
//			System.setProperty("webdriver.firefox.logfile", "firefox.log");
//			FirefoxProfile profile = new FirefoxProfile();
//			FirefoxOptions firefoxOptions = new FirefoxOptions();
//			profile.setPreference("browser.tabs.remote.autostart", false);
//			profile.setPreference("browser.tabs.remote.autostart.1", false);
//			profile.setPreference("browser.tabs.remote.autostart.2", false);
//			profile.setPreference("browser.tabs.remote.force-enable", false);
//			profile.setAcceptUntrustedCertificates(true);
//			profile.setAssumeUntrustedCertificateIssuer(false);
//			firefoxOptions.setCapability("acceptInsecureCerts", true);
//			firefoxOptions.setCapability("trustAllSSLCertificates", true);
//			firefoxOptions.setCapability("--ignore-certificate-errors", true);
//			firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
//			return new FirefoxDriver(firefoxOptions);
//		
//		case "chrome":
//			System.setProperty("webdriver.chrome.driver", executableChromeDriverPath);
//			System.setProperty("webdriver.chrome.silentOutput", "true");
//			ChromeOptions options = new ChromeOptions();
//			options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//			options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
//			options.setCapability("--ignore-certificate-errors", true);
//			return new ChromeDriver(options);
//			
//		default:
//			System.setProperty("webdriver.chrome.driver", executableChromeDriverPath);
//			System.setProperty("webdriver.chrome.silentOutput", "true");
//			ChromeOptions defaultOptions = new ChromeOptions();
//			defaultOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//			defaultOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//			defaultOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
//			defaultOptions.setCapability("--ignore-certificate-errors", true);
//			return new ChromeDriver(defaultOptions);
//		}

	}
	
	
	public static void setSize (WebDriver driver, String viewport) throws Exception{
		try{
//			switch(viewport){
//			case "large":
//				driver.manage().window().maximize();
//				break;
//			case "tablet":
//				driver.manage().window().setSize(new Dimension(768,1024));
//				break;
//			case "small":
//				driver.manage().window().setSize(new Dimension(414,736));
//				break;
//			case "mobile":
//				driver.manage().window().setSize(new Dimension(450,800));
//				break;
//			default:
//				driver.manage().window().maximize();
//			}	
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
