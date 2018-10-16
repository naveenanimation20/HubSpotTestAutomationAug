package com.qa.xyz.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.xyz.pages.BasePage;
import com.qa.xyz.pages.HomePage;
import com.qa.xyz.pages.LoginPage;
import com.qa.xyz.util.Constants;

public class HomePageTest {
	
	public WebDriver driver;
	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initProperties();
		driver = basePage.init(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		Assert.assertEquals(homePage.getHomePageTitle(), Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyHomePageHeaderTest(){
		Assert.assertTrue(homePage.verifyHomePageHeader());
	}
	
	@Test(priority=3)
	public void verifyAccountNameTest(){
		String accountName = homePage.getAccountName();
		System.out.println("Account name is == "+ accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}
