package com.itstark.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.itstark.utilities.ExcelReader;
import com.itstark.utilities.ExtentManager;
//import com.itstark.utilities.ExcelReader;
//import com.itstark.utilities.ExtentManager;
import com.itstark.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {

	public static WebDriver driver;
	
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	// public static Logger log = Logger.getLogger("devpinouLogger");
	public static Logger log = Logger.getLogger(Page.class.getName());

	// System.getProperty("user.dir")
	public static ExcelReader excel = new ExcelReader("src\\test\\resources\\com\\itstark\\excel\\testdata.xlsx");
	public static WebDriverWait wait;

	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	
	public static String browser;
	
	public static LeftMenuWT menu;
	
	/*
	 * Logs,
	 * Properties - OR, Config
	 * Excel
	 * Implicit and Explicit Wait
	 * Extent Reports
	 * 
	 * 
	 */
	
	public Page() {
		
		if(driver==null) {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
			System.setProperty("current.date.time", dateFormat.format(new Date()));

			 Date d = new Date();

			 System.out.println(d.toString().replace(":", "_").replace(" ", "_"));
			 System.setProperty("current.date", d.toString().replace(":", "_").replace(" ", "_"));
			PropertyConfigurator.configure("src\\test\\resources\\com\\itstark\\properties\\log4j.properties");

			try {
				fis = new FileInputStream("src\\test\\resources\\com\\itstark\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config file loaded!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			try {
				fis = new FileInputStream("src\\test\\resources\\com\\itstark\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR file loaded!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Jenkins Browser Filter Configuration
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			}else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);
			
			if (config.getProperty("browser").contentEquals("firefox")) {

				// This is not required since we have WebDriverManager
				// System.setProperty("WebDriver.gecko.driver","gecho.exe // .exe path");

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox Launched!");
			} else if (config.getProperty("browser").contentEquals("chrome")) {

				WebDriverManager.chromedriver().setup();
				
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				
				//disable Developer Mode Extension in Chrome 
				//options.addArguments("chrome.switches","--disable-extensions");
				//options.addArguments("-disable-notifications");
				
				//Chrome is being controlled by automated test software
				//options.addArguments("disable-infobars");
				options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
				options.setExperimentalOption("useAutomationExtension", false);
				
				driver = new ChromeDriver(options);
				log.info("Chrome Launched!");
				
			} else if (config.getProperty("browser").contentEquals("ie")) {

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.info("IE Launched!");
			}
		
			driver.get(config.getProperty("testsiteurl"));
			log.info("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);

			menu = new LeftMenuWT(driver);
		}
	
	}
	
	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on" + locator);
	}

	static WebElement dropdown;

	public void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "Selecting from dropdown" + locator + ", value selected: " +value);

	}

	public void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(LogStatus.INFO, "Typing " + value + " on " + locator);
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void verifyEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			Utilities.captureScreenshot();

			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\"href=" + Utilities.screenshotPath+Utilities.screenshotName + "><img src=" + Utilities.screenshotPath+Utilities.screenshotName
					+ " height=200 width=200\"></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			//CustomListeners.testReport.get().log(Status.FAIL, " Verification failed with
			// exception : " + t.getMessage());
			test.log(LogStatus.FAIL, "Verification failed with exception : " + t.getMessage()/* .toUpperCase() */);
			System.out.println(Utilities.screenshotPath+Utilities.screenshotName);
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotPath+Utilities.screenshotName));
		}

	}
	
	public static void quit() {
		
		driver.quit();
	}
	
	
	
}
