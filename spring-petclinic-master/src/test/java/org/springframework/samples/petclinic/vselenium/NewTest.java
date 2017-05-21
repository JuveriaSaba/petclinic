package org.springframework.samples.petclinic.vselenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NewTest {
  @Test
  public void start() {
	  System.setProperty("webdriver.chrome.driver", "C:\\HCWH\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
		assertEquals(driver.getTitle(), "PetClinic :: a Spring Framework demonstration");
  }
  
 
}
