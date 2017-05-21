package org.springframework.samples.petclinic.seleniumWD;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class errorPageTest extends baseTest{
		
		
		 /**
		  * Test to check numbner of links on a page
		  */
		 @Test
		 public void menulinksOnPageTest(){
			 driver.get("http://localhost:8080/oups");
			assertEquals(driver.findElements(By.tagName("li")).size(),5);
		 }
		/**
		 * Test to check background image of the menu
		 */
		 @Test 
		 public void menuBackGroundImageTest(){
			 driver.get("http://localhost:8080/oups");
			 assertTrue(driver.findElement(By.xpath("/html/body/nav/div/div[1]/a/span")).getCssValue("background").contains("spring-logo-dataflow.png"));
			 
			 }
	/**
	 * Test to check the heading on error page
	 */
	@Test
	 public void errorPageheadingTest() {
		driver.get("http://localhost:8080/oups");
		 assertEquals(driver.findElement(By.tagName("h2")).getText(),"Something happened...");
	  }
	
	/**
	 * Test to check error page title
	 */
	 @Test
	  public void getErrorPageTitleTest() {
		 driver.get("http://localhost:8080/oups");
			assertEquals(driver.getTitle(), "PetClinic :: a Spring Framework demonstration");
	  }
	 
	
	 /**
	  * Test to check image on error page
	  * @throws Exception
	  */
	 @Test
	 public void checkErrorImageTest() throws Exception {
		 driver.get("http://localhost:8080/oups");
	 	WebElement ImageFile = driver.findElement(By.xpath("//img"));
	 	Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
	    assertTrue(ImagePresent);
	 	}
	 
	
	 /**
	  * Test to check the active menu link test, the text and glyphicon
	  */
	 @Test
	 public void activeMenuLinkTest(){
		driver.get("http://localhost:8080/oups");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[5]")).getAttribute("class"),"active");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[5]/a")).getCssValue("background-color"),"rgba(109, 179, 63, 1)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[2]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[3]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[4]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[5]/a")).getText(),"ERROR");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[5]/a/span")).getAttribute("class"),"glyphicon  glyphicon-warning-sign");
		 //Check default error message
		 assertEquals(driver.findElement(By.xpath("/html/body/div/div/p")).getText(),"Expected: controller used to showcase what happens when an exception is thrown");
	 }
	 
	 /**
	  * Test to check error functionality, go to non existing url
	  */
	 @Test
	 public void errorPageLinkTest(){
		 
		 driver.get("http://localhost:8080/wronglink");
		 assertEquals(driver.findElement(By.xpath("/html/body/div/div/p")).getText(),"No message available");
	 }
	
	 /**
	  * Test to check the footer message
	  */
	 @Test
	 public void footerImageTest(){
		 driver.get("http://localhost:8080/oups");
		 WebElement ImageFile = driver.findElement(By.xpath("//img[@alt='Sponsored by Pivotal']")); 
		 Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
		 assertTrue(ImagePresent);
		 }
}
