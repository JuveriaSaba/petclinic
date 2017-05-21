package org.springframework.samples.petclinic.seleniumWD;

import static org.testng.Assert.assertEquals;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class petTest extends baseTest {
	
	/**
	 * Send correct inputs to check if the add new pet functionality is working correctly
	 */
	@Test
	public void addNewPetTest() {
		driver.get("http://localhost:8080/owners/find");
		driver.findElement(By.xpath("//input[@id ='lastName']")).sendKeys("Franklin");
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		int origPets = driver.findElements(By.xpath("//table[2]/tbody/tr")).size();
		driver.findElement(By.linkText("Add New Pet")).click();
		assertEquals(driver.findElement(By.tagName("h2")).getText(),"New Pet");
		//Check that the owner is the same
		assertEquals(driver.findElement(By.xpath("/html/body/div/div/form/div[1]/div[1]/div/span")).getText(),"George Franklin");
		//Check that the pet type displayed on arrival on this page is original 
		Select petType = new Select(driver.findElement(By.id("type")));
		assertEquals(petType.getFirstSelectedOption().getText(),"bird");

		//Check that there are no error messages displayed
		assertEquals(driver.findElements(By.xpath("/html/body/div/div/form/div[1]/div[2]/div/span[2]")).size(),0);
		assertEquals(driver.findElements(By.xpath("/html/body/div/div/form/div[1]/div[3]/div/span[2]")).size(),0);
		
		//Check that the input boxes are empty
		assertEquals(driver.findElement(By.xpath("//input[@id='name']")).getAttribute("value"),"");
		assertEquals(driver.findElement(By.xpath("//input[@id='birthDate']")).getAttribute("value"),"");
		
		//Send the values		 
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Minis");
		driver.findElement(By.xpath("//input[@id='birthDate']")).sendKeys("2012/08/06");
		petType.selectByIndex(2);
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		
		int iRowsAfterAddPet = driver.findElements(By.xpath("//table[2]/tbody/tr")).size();
		//Check that there is one row extra added to the owners page
		assertEquals(iRowsAfterAddPet, origPets + 1);
		int iCounter = 1;
		//Check that the last row has details which were entered in the last page
		List<WebElement> lstAllPetRows = driver.findElements(By.xpath("//table[2]/tbody/tr"));
		 for ( WebElement we: lstAllPetRows) { 
			if (iCounter == iRowsAfterAddPet){
				assertEquals(we.findElement(By.xpath("td/dl/dd[1]")).getText(),"Minis");
				assertEquals(we.findElement(By.xpath("td/dl/dd[2]")).getText(),"2012-08-06");
				assertEquals(we.findElement(By.xpath("td/dl/dd[3]")).getText(),"dog");
			}
			else
				continue;
			 iCounter++;
		 }
	}
	/**
	 * This test checks if the error messages are displayed correctly if you try 
	 * to add the name of already existing pet name
	 * 
	 */
	@Test
	public void addExistingPetTest(){
		driver.get("http://localhost:8080/owners/find");
		driver.get("http://localhost:8080/owners/find");
		driver.findElement(By.xpath("//input[@id ='lastName']")).sendKeys("Estaban");
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		
		driver.findElement(By.linkText("Add New Pet")).click();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Sly");
		driver.findElement(By.xpath("//input[@id='birthDate']")).sendKeys("2012/08/06");
		Select petType = new Select(driver.findElement(By.id("type")));
		petType.selectByIndex(2);
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		assertTrue(driver.findElement(By.xpath("/html/body/div/div/form/div[1]/div[2]/div/span[2]")).isDisplayed());
		assertEquals(driver.findElement(By.xpath("/html/body/div/div/form/div[1]/div[2]/div/span[2]")).getText(),"is already in use");
		
	}
	/** 
	 * This test checks if the error messages are displayed when no values are entered in the name and date fields
	 * 
	 */
	@Test
	public void addPetSubmitBlankTest() {
		driver.get("http://localhost:8080/owners/find");
		driver.get("http://localhost:8080/owners/find");
		driver.findElement(By.xpath("//input[@id ='lastName']")).sendKeys("Estaban");
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		
		driver.findElement(By.linkText("Add New Pet")).click();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='birthDate']")).sendKeys("");
		Select petType = new Select(driver.findElement(By.id("type")));
		petType.selectByIndex(2);
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		assertTrue(driver.findElement(By.xpath("/html/body/div/div/form/div[1]/div[2]/div/span[2]")).isDisplayed());
		assertEquals(driver.findElement(By.xpath("/html/body/div/div/form/div[1]/div[2]/div/span[2]")).getText(),"is required");
	
		assertTrue(driver.findElement(By.xpath("/html/body/div/div/form/div[1]/div[3]/div/span[2]")).isDisplayed());
		assertEquals(driver.findElement(By.xpath("/html/body/div/div/form/div[1]/div[3]/div/span[2]")).getText(),"is required");

	}
	/**
	 * This test checks if the edit pet functionality.
	 */
	@Test
	public void editPetTest() {
		
		driver.get("http://localhost:8080/owners/find");
		driver.findElement(By.xpath("//input[@id ='lastName']")).sendKeys("Black");
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		driver.findElement(By.xpath("//table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td[1]/a")).click();
		
		
		assertEquals(driver.findElement(By.xpath("/html/body/div/div/form/div[1]/div[1]/div/span")).getText(),"Jeff Black");
		
		assertEquals(driver.findElement(By.xpath("//input[@id='name']")).getAttribute("value"),"Lucky");
		assertEquals(driver.findElement(By.xpath("//input[@id='birthDate']")).getAttribute("value"),"2011/08/06");
		
		Select petType = new Select(driver.findElement(By.id("type")));
		
		assertEquals(petType.getFirstSelectedOption().getText(),"bird");
		
		driver.findElement(By.xpath("//input[@id='name']")).clear();
		driver.findElement(By.xpath("//input[@id='birthDate']")).clear();
		
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Rocky");
		driver.findElement(By.xpath("//input[@id='birthDate']")).sendKeys("2013/08/06");
		petType.selectByIndex(1);
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		boolean bFlag = false;
		
		List<WebElement> lstAllPetRows = driver.findElements(By.xpath("//table[2]/tbody/tr"));
		 for ( WebElement we: lstAllPetRows) { 
			 
			 if (we.findElement(By.xpath("td/dl/dd[1]")).getText().equals("Rocky")){
			    assertEquals(we.findElement(By.xpath("td[1]/dl/dd[2]")).getText(),"2013-08-06");
				assertEquals(we.findElement(By.xpath("td[1]/dl/dd[3]")).getText(),"cat");
				bFlag = true;
			 }
			 else
				 continue;
			 
		 }
		assertTrue(bFlag);
		
		
	}
	
 
}
