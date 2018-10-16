package EE_Pages;

import java.util.concurrent.atomic.AtomicLong;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SupportSeleniumUIActions.WebUI;

public class InboxPage {

	
	WebDriver driver;
	WebUI selenium;
	
	public static String uniqsubject;
	public static String uniqMsg;


	
	
	//###### Locators :-   *****
	@FindBy(xpath="//table[@class='F cf zt']")
	public WebElement inboxtable;
	

	@FindBy(xpath="//*[@role='button' and text()='Compose']")
	public WebElement composeEmailButton;
	
	@FindBy(name="to")
	public WebElement emailto;
	
	@FindBy(name="subjectbox")
	public WebElement emailsubject;

	@FindBy(xpath=".//div[@aria-label='Message Body']")
	public WebElement emailBody;
	
	@FindBy(xpath=".//div[@aria-label='More options']")
	public WebElement EmailComposeMoreOption;
	
	@FindBy(xpath="//*[@role='button' and text()='Send']")
	public WebElement sendemailbutton;
	

	@FindBy(xpath=".//*[@role='menuitem']/div[text()='Label']")
	public WebElement LabelMenueItems;
	
	@FindBy(xpath=".//*[@role='menuitemcheckbox']/div[text()='Social']")
	public WebElement SocialMenueCheckBox;


	@FindBy(xpath="//h2[@class='hP']")
	public WebElement OpenEmailSubjectText;
	
	@FindBy(xpath="//div[contains(@class, 'a3s aXjCH') and not(contains(@class,\"undefined\"))]")
	public WebElement OpenEmailBodyText;
	
	
	
	/****** Constructor ******************/ 
	public InboxPage(WebDriver driver)
	{
		//==>> Loading All Web-Element Object Using Page Factory
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		//==>> Initialize SELENIUM wrapper that will help to perform all Web-UI Related Action  [ Click , Set ETC] 
		selenium = new WebUI(driver);
	}


	/****** Page Actions Functions ******************/ 
	
	
	public void ComposeEmailToRecipantUsingLabel(String SendtoEmail,String LabelName)
	{
		composeEmailButton.click();
		emailto.clear();
		emailto.sendKeys(SendtoEmail);
		
		uniqsubject = "Sample Subject" +uniqueCurrentTimeMS();
		emailsubject.sendKeys(uniqsubject);
		
		uniqMsg = "Sample Email Massage is " +uniqueCurrentTimeMS();
		emailBody.sendKeys(uniqMsg);
		
		
		EmailComposeMoreOption.click();
		LabelMenueItems.click();
		
		String LabeltoCheckXpath = ".//*[@role='menuitemcheckbox']/div[text()='"+LabelName+"']";
		driver.findElement(By.xpath(LabeltoCheckXpath)).click();

		
		
		sendemailbutton.click();
		
		
		selenium.hardWait();

	}
	

	

	
	
	
	/**** Utility Function : Generates a Uniq Long NUmber Using Date time..  */
	private static final AtomicLong LAST_TIME_MS = new AtomicLong();
	public static long uniqueCurrentTimeMS() {
	    long now = System.currentTimeMillis();
	    while(true) {
	        long lastTime = LAST_TIME_MS.get();
	        if (lastTime >= now)
	            now = lastTime+1;
	        if (LAST_TIME_MS.compareAndSet(lastTime, now))
	            return now;
	    }
	}
	
	
	public void movetoTab(String TabName)
	{
		
		String movotoTabXpath = "//div[@aria-label='"+TabName+"']";
		driver.findElement(By.xpath(movotoTabXpath)).click();
		selenium.hardWait();

	}
	
	public void markEmailAsstarred(String emailtoMark)
	{
			//Find The Email We need to MArk As Starrted 
			// Email Elements to Mark As aStarred 
			WebElement emailElement = driver.findElement(By.xpath("//table[@class='F cf zt']//span[contains(text(), '"+emailtoMark+"')]/../../..//td[@class='apU xY']"));
			emailElement.click();
			selenium.hardWait();
	}
	
	
	public boolean OpenEmailWithSubjectContain(String subjectLine) {
		try
		{
			selenium.ClickTableCellText(inboxtable, subjectLine);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}


	
	public String ReadEmailSubject()
	{
		String subjecttoReturn=OpenEmailSubjectText.getText();
		return subjecttoReturn;
	}
	
	public String ReadEmailBody()
	{
		String EmailBodytoReturn=OpenEmailBodyText.getText();
		return EmailBodytoReturn;
	}
	
	
	
}
