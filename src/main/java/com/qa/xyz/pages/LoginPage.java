package com.qa.xyz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
	
	//webelements and actions(methods) -- features
	//for webelements -- pagefactory pattern
	
	@FindBy(id = "username")
	WebElement username;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "loginBtn")
	WebElement loginBtn;
	
	@FindBy(xpath = "//a/i18n-string[text()='Forgot my password']")
	WebElement forgotPwdLink;
	
	//initialize webelements:
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String userName, String pwd){
		username.sendKeys(userName);
		password.sendKeys(pwd);
		loginBtn.click();
		System.out.println("login is done");
		return new HomePage(driver);
	}
	
	public boolean verifyForgotPwdLink(){
		return forgotPwdLink.isDisplayed();
	}
	

}
