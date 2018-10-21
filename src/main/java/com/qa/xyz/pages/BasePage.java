package com.qa.xyz.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.xyz.listener.WebEventListener;
import com.qa.xyz.util.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasePage {

	WebDriver driver;
	Properties prop;
	String browserName;
	EventFiringWebDriver eDriver;
	WebEventListener eventListener;
	

	public WebDriver init(Properties prop) {

		browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ 
					"/src/main/resources/drivers/geckodriver");
			//ChromeDriverManager.chromedriver().version("2.43").setup();	
			driver = new ChromeDriver();
			System.out.println("chrome is launched");
		}else if(browserName.equals("firefox")){
//			System.setProperty("webdriver.gecko.driver", 
//					System.getProperty("user.dir")+"/src/main/resources/drivers/geckodriver");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("firefox is launched");

		}

		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		eDriver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		eDriver.register(eventListener);
		
		driver = eDriver;
		driver.manage().window().fullscreen();
		driver.get(prop.getProperty("url"));

		return driver;
	}

	public Properties initProperties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/xyz/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
