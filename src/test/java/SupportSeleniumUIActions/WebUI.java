package SupportSeleniumUIActions;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebUI 
{
	
	
	
	public WebDriver driver;

		
	public WebUI(WebDriver driver)
	{
		this.driver=driver;
	}
		
	public void NavigateToPage(String Url)
	{
		driver.get(Url);
	}
	
	public boolean ValidateElementExist(WebElement el)
	{
		WebElement TargetObject=el;
		try
		{
			if(!waitUntilClickable(TargetObject)) return false; // wait a Little bit for Object to Load
			if(TargetObject.isDisplayed()){
				highlightElement(TargetObject,"pass");
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean EnterText(WebElement el, String Input)
	{
	    		boolean Rtype = false;
	    		try
	    		{
	    			WebElement TargetObject = el;
	    			TargetObject.clear();
	    			hardWaitFor(1000);
	    			TargetObject.sendKeys(Input);
	    			Rtype= true;
	    		}
	    		catch(Exception e){
	    			Rtype= false;
	    			e.printStackTrace();
	    		}
			return Rtype;
	   }
	
	public boolean ClickButton(WebElement el)
    {
		boolean Rtype = false;
    		try{
    			if(!waitUntilClickable(el)) return false; // wait a Little bit for Object to Load
    			WebElement TargetObject = el;
    			TargetObject.click();
    			hardWait();
    			Rtype= true;
    		}
    		catch(Exception e){
	    		Rtype= false;
	    		e.printStackTrace();
    		}
		return Rtype;
    }
	
    public boolean ClickTableCellText(WebElement ObjectName, String Input)
    {
    		boolean Rtype = false;
        boolean matChFoundinTable=false;
		
    		try
    		{
    			WebElement table_element = ObjectName;
    	        List<WebElement> tr_collection = table_element.findElements(By.xpath("tbody/tr"));

    	        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
    	        TableRowLoop: for(WebElement trElement : tr_collection)
    	        {
    	            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
    	            String EachRowData = "";
    	            for(WebElement tdElement : td_collection)
    	            {
    	                System.out.print(tdElement.getText()+" ");
    	                EachRowData += tdElement.getText() + " ";
    	                if(StringUtils.containsIgnoreCase(EachRowData,Input))	
    	                {
    	                		tdElement.click();
    	                		hardWait();
    	                		Rtype= true;
    	                		matChFoundinTable=true;
						break TableRowLoop;
    	                }
    	                else
    	                {
        	            		matChFoundinTable= false;
    	                }
    	            }
    	            
    	            System.out.println("");
    	        } 		
    		}
    		catch(Exception e)
    		{
    			Rtype= false;
    		}
    		
	    	if(!matChFoundinTable)
	    	{
	    		Rtype= false;
	    	}
    	
    		return Rtype;
    }
    

    
//###################  Helper Functions  ##################################################################################################
     
 	

	//==== Highlight element 
	public void highlightElement(WebElement element, String flag) 
	{
           JavascriptExecutor js = (JavascriptExecutor) driver;
           
           if(flag.equalsIgnoreCase("pass"))
           {
               js.executeScript("arguments[0].style.border='2px groove green'", element);
           }
           else 
           {
               js.executeScript("arguments[0].style.border='2px solid red'", element);

           }

           try 
           {
			Thread.sleep(3000);
           } 
           catch (InterruptedException e) 
           {
			e.printStackTrace();
           }
                   
           // Take a Screen Shot and Reset the Element 
           //takeScreenShot();
           //js.executeScript("arguments[0].style.border=''", element);
    }
	public void highlightElementBackground(WebElement element, String flag)
	{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        if(flag.equalsIgnoreCase("pass"))
        {
            //js.executeScript("arguments[0].style.border='1px groove green'", element);
	        js.executeScript("arguments[0].style.backgroundColor = '"+"yellow"+"'",  element);
        }
        else 
        {
            //js.executeScript("arguments[0].style.border='4px solid red'", element);
	        js.executeScript("arguments[0].style.backgroundColor = '"+"red"+"'",  element);
        }

        try 
        {
			Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
        }
                
        //===> Take a Screen Shot and Reset the Element 
        	//takeScreenShot();
        //js.executeScript("arguments[0].style.border=''", element);
        //js.executeScript("arguments[0].style.backgroundColor = '"+""+"'",  element);
	}
	public void highlightElementBorder(WebElement element, String flag) 
	{
           JavascriptExecutor js = (JavascriptExecutor) driver;
           
           if(flag.equalsIgnoreCase("pass"))
           {
               js.executeScript("arguments[0].style.border='2px groove green'", element);
           }
           else 
           {
               js.executeScript("arguments[0].style.border='2px solid red'", element);

           }

           try 
           {
			Thread.sleep(1000);
           } 
           catch (InterruptedException e) 
           {
			e.printStackTrace();
           }
                   
           // Take a Screen Shot and Reset the Element 
           //takeScreenShot();
           //js.executeScript("arguments[0].style.border=''", element);
    }

	//=== Driver Wait
	public void waitForPageToLoad() throws InterruptedException 
	{
		//wait(2);
		this.hardWaitFor(500);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String state = (String)js.executeScript("return document.readyState");
		while(!state.equals("complete"))
		{
			//wait(2);
			this.hardWaitFor(500);
			state = (String)js.executeScript("return document.readyState");
		}
	}	
	public void hardWait()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void hardWaitFor(int Second)
	{
		try {
			Thread.sleep(Second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean waitUntilClickable(String ObjectName)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectName)));
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}
	public boolean waitUntilClickable(WebElement ObjectName)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,2);
			wait.until(ExpectedConditions.elementToBeClickable(ObjectName));
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
			
}
