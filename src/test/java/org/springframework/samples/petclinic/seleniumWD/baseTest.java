package org.springframework.samples.petclinic.seleniumWD;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class baseTest {
	
protected WebDriver driver;


@BeforeMethod
public void startDriver() {
	
	//To Use IE browser
	//System.setProperty("webdriver.chrome.driver", "C:\\spring-petclinic\\src\\main\\resources\\static\\resources\\IEDriverServer.exe");
	//driver = new InternetExplorerDriver();
	
	//To Use FireFox Browser
	//System.setProperty("webdriver.gecko.driver", "C:\\spring-petclinic\\src\\main\\resources\\static\\resources\\geckodriver.exe");
	// driver = new FirefoxDriver();
	
	//For Chrome Browser
	System.setProperty("webdriver.chrome.driver", "C:\\spring-petclinic\\src\\main\\resources\\static\\resources\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	
	
  }


@AfterMethod
 public void closeDriver(){
	 driver.close();
 }
	 
	 
	 	
		 
		
}
