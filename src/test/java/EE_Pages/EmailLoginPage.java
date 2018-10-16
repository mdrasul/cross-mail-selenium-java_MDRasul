package EE_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SupportSeleniumUIActions.WebUI;

public class EmailLoginPage {

	
	WebDriver driver;
	WebUI selenium;
	//String sLoginPageURL = "http://gmail.com";
	

	//###### Locators :-   *****
	@FindBy(xpath="//input[@name='identifier']")
	public WebElement emailTextBox;
	@FindBy(xpath="//input[@name='password']")
	public WebElement passwordTextBox;
	@FindBy(xpath="//span[contains(text(),'Next')]/../..")
	public WebElement NextButton;

	
	
	
	/****** Constructor ******************/ 
	public EmailLoginPage(WebDriver driver)
	{
		//==>> Loading All Web-Element Object Using Page Factory
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		//==>> Initialize SELENIUM wrapper that will help to perform all Web-UI Related Action  [ Click , Set ETC] 
		selenium = new WebUI(driver);
	}
	
	
	/****** Page Actions Functions ******************/ 
	
	public boolean doLoginWith(String ID, String Password) 
	{
		try
		{
			//==>> Navigating to GMAIL Page
			//selenium.NavigateToPage(sLoginPageURL);
			
			//==>> Enter ID And CLick Next
			selenium.EnterText( emailTextBox, ID);
			selenium.ClickButton(NextButton);
			
			//==>> Enter Password And CLick Next
			selenium.EnterText( passwordTextBox, Password);
			selenium.ClickButton(NextButton);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
}
