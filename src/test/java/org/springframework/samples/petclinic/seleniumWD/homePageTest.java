package org.springframework.samples.petclinic.seleniumWD;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class homePageTest extends baseTest{
	
	 /**
	  * Test to check number of links on a page
	  */
	 @Test
	 public void menulinksOnPageTest(){
		 driver.get("http://localhost:8080");
		assertEquals(driver.findElements(By.tagName("li")).size(),5);
	 }
	/**
	 * Test to check background image of the menu
	 */
	 @Test 
	 public void menuBackGroundImageTest(){
		 driver.get("http://localhost:8080");
		 assertTrue(driver.findElement(By.xpath("/html/body/nav/div/div[1]/a/span")).getCssValue("background").contains("spring-logo-dataflow.png"));
		 
		 }
	
	 /**
	  * Test to check the heading to home page 
	  */
	@Test
	 public void homePageheadingTest() {
		driver.get("http://localhost:8080");
		 assertEquals(driver.findElement(By.tagName("h2")).getText(),"Welcome");
	  }
	
	
	/**
	 * Test to check home page title
	 */
	 @Test
	  public void getHomePageTitleTest() {
		 driver.get("http://localhost:8080");
			assertEquals(driver.getTitle(), "PetClinic :: a Spring Framework demonstration");
	  }
	 @Test
	 public void checkWelcomeImageTest() throws Exception {
		 driver.get("http://localhost:8080");
	 	WebElement ImageFile = driver.findElement(By.xpath("//img"));
	 	Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
	    assertTrue(ImagePresent);
	 	}
	 
	
	 
	@Test
	 public void activeMenuLinkTest(){
		 driver.get("http://localhost:8080");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[2]")).getAttribute("class"),"active");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[2]/a")).getCssValue("background-color"),"rgba(109, 179, 63, 1)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[3]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[4]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[5]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[2]/a")).getText(),"HOME");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[2]/a/span")).getAttribute("class"),"glyphicon  glyphicon-home");
		 
	 }
	 
	 /**
	  * Test to check the footer message
	  */
	 @Test
	 public void footerImageTest(){
		 driver.get("http://localhost:8080");
		 WebElement ImageFile = driver.findElement(By.xpath("//img[@alt='Sponsored by Pivotal']")); 
		 Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
		 assertTrue(ImagePresent);
		 }
	
	 /**
	  * Test to check owner menu link 
	  */
	 @Test
	 public void clickOnOwnersLinkTest() {
		 driver.get("http://localhost:8080");
		 driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[3]/a")).click();
		 assertEquals(driver.getCurrentUrl(),"http://localhost:8080/owners/find");
	 }
	 
	 /**
	  * Test to check vets menu link 
	  */
	 @Test
	 public void clickOnVetsLinlTest() {
		 driver.get("http://localhost:8080");
		 driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[4]/a")).click();
		 assertEquals(driver.getCurrentUrl(),"http://localhost:8080/vets.html");
	 }
	 
	 /**
	  * Test to check error menu link 
	  */
	 @Test
	 public void clickOnErrorLinklTest() {
		 driver.get("http://localhost:8080");
		 driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[5]/a")).click();
		 assertEquals(driver.getCurrentUrl(),"http://localhost:8080/oups");
	 }
	 
	 /**
	  * Test to check menu image click
	  */
	 public void clickonMenuImageTest(){
		 driver.findElement(By.xpath("/html/body/nav/div/div[1]/a/span")).click();
		 assertEquals(driver.getCurrentUrl(),"http://localhost:8080");
	 }
}
