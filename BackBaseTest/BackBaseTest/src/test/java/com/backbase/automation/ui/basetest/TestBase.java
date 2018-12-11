package com.backbase.automation.ui.basetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.backbase.automation.ui.util.TestUtil;
import com.backbase.automation.ui.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/java/com/backbase"
					+ "/automation/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() throws InterruptedException{
		String browserName = prop.getProperty("browser");

		if(browserName.equals("CHROME")){
			if (isPlatformWindows())
				System.setProperty("webdriver.chrome.driver", "src/main/resources/bin/x32/win/chromedriver.exe");
			else if (isPlatformMAC())
				System.setProperty("webdriver.chrome.driver", "src/main/resources/bin/macosx/chromedriver");
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			if (isPlatformWindows())
				System.setProperty("webdriver.gecko.driver", "src/main/resources/bin/x32/win/geckodriver.exe");
			else if (isPlatformMAC())
				System.setProperty("webdriver.gecko.driver", "src/main/resources/bin/macosx/geckodriver");
			driver = new FirefoxDriver(); 
		}


		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	private static boolean isPlatformWindows() {
		return (System.getProperty("os.name").toLowerCase().startsWith("win".toLowerCase()));
	}

	private static boolean isPlatformMAC() {
		return (System.getProperty("os.name").toLowerCase().startsWith("mac".toLowerCase()));
	}
	
	@BeforeMethod()
	public void setUp() throws InterruptedException {
		initialization();
	}
	
	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

}
