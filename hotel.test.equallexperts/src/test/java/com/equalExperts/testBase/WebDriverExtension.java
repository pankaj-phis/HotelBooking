package com.equalExperts.testBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverExtension {
	
	public static WebDriver driver;
	
	
	public static void IsElementVisible(WebDriver driver, By elementLocator) {
		WebElement ele = driver.findElement(elementLocator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        //use executeScript() method and pass the arguments 
        //Here i pass values based on css style. Yellow background color with solid red color border. 
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
	}
	
	

}
