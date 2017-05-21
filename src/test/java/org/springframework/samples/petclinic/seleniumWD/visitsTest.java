package org.springframework.samples.petclinic.seleniumWD;

import static org.testng.Assert.assertEquals;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class visitsTest extends baseTest{
	
	
	/**
	 * Test to check add visit functionality
	 */
	@Test
	public void addVisitTest()  {
		//Search the owner
		driver.get("http://localhost:8080/owners/find");
		driver.findElement(By.xpath("//input[@id ='lastName']")).sendKeys("McTavish");
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		
		//Get the original  number of visits
		WebElement el =  driver.findElement(By.xpath("//table[2]/tbody/tr[1]/td[2]/table/tbody"));
		int iOrigVisits = el.findElements(By.xpath("tr")).size();		
		el.findElement(By.partialLinkText("Add")).click();
		
		//Check the heading 
		assertEquals(driver.findElement(By.tagName("h2")).getText(),"New Visit");
		//Check the owner details
		assertEquals(driver.findElements(By.xpath("//table[1]/thead/tr/th")).size(),4);
		
		assertEquals(driver.findElement(By.xpath("//table[1]/thead/tr/th[1]")).getText(),"Name");
		assertEquals(driver.findElement(By.xpath("//table[1]/thead/tr/th[2]")).getText(),"Birth Date");
		assertEquals(driver.findElement(By.xpath("//table[1]/thead/tr/th[3]")).getText(),"Type");
		assertEquals(driver.findElement(By.xpath("//table[1]/thead/tr/th[4]")).getText(),"Owner");
		
		assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr/td[1]")).getText(),"George");
		assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr/td[2]")).getText(),"2010-01-20");
		assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr/td[3]")).getText(),"snake");
		assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr/td[4]")).getText(),"Peter McTavish");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		//Check if the date text field displays today's date
		assertEquals(driver.findElement(By.xpath("//input[@id='date']")).getAttribute("value"),dateFormat.format(date).toString());
		
		//Check no error messages are displayed
		assertEquals(driver.findElements(By.xpath("/html/body/div/div/form/div[1]/div[2]/div/span[2]")).size(),0);
		//Check for previous visits, starting from heading
		assertEquals(driver.findElement(By.xpath("//b[2]")).getText(),"Previous Visits");
		
		assertEquals(driver.findElement(By.xpath("//table[2]/tbody/tr[1]/th[1]")).getText(),"Date");
		assertEquals(driver.findElement(By.xpath("//table[2]/tbody/tr[1]/th[2]")).getText(),"Description");
		
		int prevVisitsOnAddVisitPage  = driver.findElements(By.xpath("//table[2]/tbody/tr")).size();
		assertEquals(iOrigVisits,prevVisitsOnAddVisitPage);
		
		driver.findElement(By.xpath("//input[@id='description']")).sendKeys("znew visit");
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		WebElement ele =  driver.findElement(By.xpath("//table[2]/tbody/tr[1]/td[2]/table/tbody"));
		int iRowsAfterAddVisits = ele.findElements(By.xpath("tr")).size();	
		
		
		assertEquals(iOrigVisits+1,iRowsAfterAddVisits);
		
		int iCounter = 1;
		//Check that the last row has details which were entered in the last page
		List<WebElement> lstAllPetRows = driver.findElements(By.xpath("//table[2]/tbody/tr[1]/td[2]/table/tbody/tr"));
		 for ( WebElement we: lstAllPetRows) { 
			if (iCounter == iRowsAfterAddVisits -1){
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				assertEquals(we.findElement(By.xpath("td[1]")).getText(),dateFormat.format(date).toString());
				assertEquals(we.findElement(By.xpath("td[2]")).getText(),"znew visit");
				
			}
			else
				continue;
			 iCounter++;
		 }
		
		
  }
}
