package org.springframework.samples.petclinic.seleniumWD;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;

public class ownersPageTest  extends baseTest{
	
	
	
	 /**
	  * Test to check the header of the page
	  */
	 @Test
	 public void getOwnersPageHeadingTest() {
		 driver.get("http://localhost:8080/owners/find");
		 assertEquals(driver.findElement(By.tagName("h2")).getText(),"Find Owners");
	 }
	 
	 @Test 
	 public void activeMenuLinkTest(){
		 driver.get("http://localhost:8080/owners/find");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[3]")).getAttribute("class"),"active");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[3]/a")).getCssValue("background-color"),"rgba(109, 179, 63, 1)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[2]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[4]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[5]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[3]/a")).getText(),"FIND OWNERS");
		 }
	 /**
	  * Test to check the menu has 5 links
	  */
	 @Test
	 public void menulinksOnPageTest(){
		 driver.get("http://localhost:8080/owners/find");
		  assertEquals(driver.findElements(By.tagName("li")).size(),5);
	 }
	 
	 @Test 
	 public void menuBackGroundImageTest(){
		 driver.get("http://localhost:8080/owners/find");
		 assertTrue(driver.findElement(By.xpath("/html/body/nav/div/div[1]/a/span")).getCssValue("background").contains("spring-logo-dataflow.png"));
		 
		 }
	 /*
	  * test case when no data is entered in the last name text field and find owners is clicked.
	  * testing if the search brings in all the available owners.
	  */
	 @Test
	  public void ownersPageRowsTest() throws InterruptedException{ 
		 driver.get("http://localhost:8080/owners/find");
		 driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		 assertTrue(driver.findElements(By.tagName("tr")).size() > 1); 
	 }
	 /*
	  * test to find if the find owners brings in correct owner , sends the surname and tests if the return result contains
	  * the same surname
	  */
	 @Test
	 public void findOwnersTest() {
		 driver.get("http://localhost:8080/owners/find");
		 driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Escobito");
		 driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		 //Test to check two tables are returned after the search 
		 assertEquals(driver.findElements(By.xpath("//table[@class='table table-striped']")).size(),2);
		 //Test to check headers for the two tables
		 assertEquals(driver.findElement(By.xpath("/html/body/div/div/h2[1]")).getText(),"Owner Information");
		 assertEquals(driver.findElement(By.xpath("/html/body/div/div/h2[2]")).getText(),"Pets and Visits");
		 //Test to check if the search returns the correct owner 
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr/td")).getText(),"Maria Escobito");
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr[2]/td")).getText(),"345 Maple St.");
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr[3]/td")).getText(),"Madison");
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr[4]/td")).getText(),"6085557683");
		 //Test to check if the Edit owner and add new pet are displayed
		 assertTrue(driver.findElement(By.linkText("Edit Owner")).isDisplayed());
		 assertTrue(driver.findElement(By.linkText("Add New Pet")).isDisplayed());
		 //The owner should have at least 1 pet
		 assertTrue(driver.findElements(By.xpath("//table[2]/tbody/tr")).size() > 0);
		 assertEquals(driver.findElements(By.xpath("//table[2]/tbody/tr/td/dl/dt")).size(),3);
		 assertEquals(driver.findElement(By.xpath("//table[2]/tbody/tr/td/dl/dt[1]")).getText(),"Name");
		 assertEquals(driver.findElement(By.xpath("//table[2]/tbody/tr/td/dl/dt[2]")).getText(),"Birth Date");
		 assertEquals(driver.findElement(By.xpath("//table[2]/tbody/tr/td/dl/dt[3]")).getText(),"Type");
		 assertEquals(driver.findElement(By.xpath("//table[2]/tbody/tr/td/dl/dd[1]")).getText(),"Mulligan");
		 assertEquals(driver.findElement(By.xpath("//table[2]/tbody/tr/td/dl/dd[2]")).getText(),"2007-02-24");
		 assertEquals(driver.findElement(By.xpath("//table[2]/tbody/tr/td/dl/dd[3]")).getText(),"dog");
		 assertTrue(driver.findElement(By.xpath("//table[@class='table-condensed']")).isDisplayed());
		 assertTrue(driver.findElement(By.linkText("Edit Pet")).isDisplayed());
		 assertTrue(driver.findElement(By.linkText("Add Visit")).isDisplayed());
	 }
	
	 /**
	  * Test to verify on clicking Edit owner, correct information is retrieved.
	  * 
	  */
	 @Test
	 public void editOwnerButtonClickTest(){
		 driver.get("http://localhost:8080/owners/find");
		 driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Black");
		 driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		 driver.findElement(By.linkText("Edit Owner")).click();
		 assertEquals(driver.findElement(By.tagName("h2")).getText(),"Owner");
		 assertEquals(driver.findElement(By.xpath("//input[@id='firstName']")).getAttribute("value"),"Jeff");
		 assertEquals(driver.findElement(By.xpath("//input[@id='lastName']")).getAttribute("value"),"Black");
		 assertEquals(driver.findElement(By.xpath("//input[@id='address']")).getAttribute("value"),"1450 Oak Blvd.");
		 assertEquals(driver.findElement(By.xpath("//input[@id='city']")).getAttribute("value"),"Monona");
		 assertEquals(driver.findElement(By.xpath("//input[@id='telephone']")).getAttribute("value"),"6085555387");
	 }
	 
	 /**
	  * Test update owner functionality 
	  */
	  @Test
	 public void updateOwnerTest(){
		  driver.get("http://localhost:8080/owners/find");
		 driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Schroeder");
		 driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		 driver.findElement(By.linkText("Edit Owner")).click();
		 driver.findElement(By.xpath("//input[@id='firstName']")).clear();
		 driver.findElement(By.xpath("//input[@id='lastName']")).clear();
		 driver.findElement(By.xpath("//input[@id='address']")).clear();
		 driver.findElement(By.xpath("//input[@id='city']")).clear();
		 driver.findElement(By.xpath("//input[@id='telephone']")).clear();
		 
		 driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Jefferson");
		 driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("White");
		 driver.findElement(By.xpath("//input[@id='address']")).sendKeys("1450 High Street");
		 driver.findElement(By.xpath("//input[@id='city']")).sendKeys("vevana");
		 driver.findElement(By.xpath("//input[@id='telephone']")).sendKeys("6085555388");
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		 
		 //Check that entered details are saved
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr/td")).getText(),"Jefferson White");
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr[2]/td")).getText(),"1450 High Street");
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr[3]/td")).getText(),"vevana");
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr[4]/td")).getText(),"6085555388");
		 
		// driver.findElement(By.linkText("Edit Owner")).click();
		 
		 
	 }
	 /**
	  * Test to add a new owner
	  */
	 @Test
	 public void addOwnerTest(){
		 driver.get("http://localhost:8080/owners/find");
		 driver.findElement(By.linkText("Add Owner")).click();
		 assertEquals(driver.findElement(By.tagName("h2")).getText(),"Owner");
		 assertEquals(driver.findElement(By.xpath("//input[@id='firstName']")).getAttribute("value"),"");
		 assertEquals(driver.findElement(By.xpath("//input[@id='lastName']")).getAttribute("value"),"");
		 assertEquals(driver.findElement(By.xpath("//input[@id='address']")).getAttribute("value"),"");
		 assertEquals(driver.findElement(By.xpath("//input[@id='city']")).getAttribute("value"),"");
		 assertEquals(driver.findElement(By.xpath("//input[@id='telephone']")).getAttribute("value"),"");
		 
		 //Sending new values
		 driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Juveriaa");
		 driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Sabaa");
		 driver.findElement(By.xpath("//input[@id='address']")).sendKeys("High Street");
		 driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Wesper");
		 driver.findElement(By.xpath("//input[@id='telephone']")).sendKeys("1234567899");
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		 
		 //Check that entered values are saved
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr/td")).getText(),"Juveriaa Sabaa");
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr[2]/td")).getText(),"High Street");
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr[3]/td")).getText(),"Wesper");
		 assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr[4]/td")).getText(),"1234567899");
		 
		 assertTrue(driver.findElement(By.linkText("Edit Owner")).isDisplayed());
		 assertTrue(driver.findElement(By.linkText("Add New Pet")).isDisplayed());
		 //No rows of pets should be there
		 assertTrue(driver.findElements(By.xpath("//table[2]/tbody/tr")).size() == 0);
		 
	 }
	 /**
	  * Test to see if error messages are displayed when blank values are submitted.
	  */
	 @Test
	 public void addOwnerSubmitBlankTest(){
		 driver.get("http://localhost:8080/owners/find");
		 driver.findElement(By.linkText("Add Owner")).click();
		 assertEquals(driver.findElement(By.tagName("h2")).getText(),"Owner");
		 assertEquals(driver.findElement(By.xpath("//input[@id='firstName']")).getAttribute("value"),"");
		 assertEquals(driver.findElement(By.xpath("//input[@id='lastName']")).getAttribute("value"),"");
		 assertEquals(driver.findElement(By.xpath("//input[@id='address']")).getAttribute("value"),"");
		 assertEquals(driver.findElement(By.xpath("//input[@id='city']")).getAttribute("value"),"");
		 assertEquals(driver.findElement(By.xpath("//input[@id='telephone']")).getAttribute("value"),"");
		 
		 
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		 
		 assertTrue(driver.findElement(By.xpath("//*[@id='add-owner-form']/div[1]/div[1]/div/span[2]")).isDisplayed());
		 assertEquals(driver.findElement(By.xpath("//*[@id='add-owner-form']/div[1]/div[1]/div/span[2]")).getText(),"may not be empty");
		 
		 assertTrue(driver.findElement(By.xpath("//*[@id='add-owner-form']/div[1]/div[2]/div/span[2]")).isDisplayed());
		 assertEquals(driver.findElement(By.xpath("//*[@id='add-owner-form']/div[1]/div[2]/div/span[2]")).getText(),"may not be empty");
		 
		 assertTrue(driver.findElement(By.xpath("//*[@id='add-owner-form']/div[1]/div[3]/div/span[2]")).isDisplayed());
		 assertEquals(driver.findElement(By.xpath("//*[@id='add-owner-form']/div[1]/div[3]/div/span[2]")).getText(),"may not be empty");
		 
		 assertTrue(driver.findElement(By.xpath("//*[@id='add-owner-form']/div[1]/div[4]/div/span[2]")).isDisplayed());
		 assertEquals(driver.findElement(By.xpath("//*[@id='add-owner-form']/div[1]/div[4]/div/span[2]")).getText(),"may not be empty");
		 
		 assertTrue(driver.findElement(By.xpath("//*[@id='add-owner-form']/div[1]/div[5]/div/span[2]")).isDisplayed());
		 assertTrue( driver.findElement(By.xpath("//*[@id='add-owner-form']/div[1]/div[5]/div/span[2]")).getText().contains("numeric value out of bounds (<10 digits>.<0 digits> expected)"));
	 }
	
	 /**
	  * Footer image test
	  */
	 @Test
	 public void footerImageTest(){
		 driver.get("http://localhost:8080/owners/find");
		 WebElement ImageFile = driver.findElement(By.xpath("//img[@alt='Sponsored by Pivotal']")); 
		 Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
		 assertTrue(ImagePresent);
		 }
	 
	 
	
	 
}
