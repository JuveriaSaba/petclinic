package org.springframework.samples.petclinic.seleniumWD;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.samples.petclinic.vet.Vet;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.nio.charset.Charset;

public class vetsPageTest {
	
	private WebDriver driver;


	@BeforeMethod
	public void startDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\spring-petclinic\\src\\main\\resources\\static\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		
	  }


	@AfterMethod
	 public void closeDriver(){
		 driver.close();
	 }
		
		
	/**
	 * Test to check the heading
	 */
	@Test
	 public void getVetsPageHeadingTest() {
		driver.get("http://localhost:8080/vets.html");
		 assertEquals(driver.findElement(By.tagName("h2")).getText(),"Veterinarians");
	  } 
	/**
	 * Test to check the active link
	 */
	 @Test 
	 public void activeMenuLinkTest(){
		 driver.get("http://localhost:8080/vets.html");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[4]")).getAttribute("class"),"active");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[4]/a")).getCssValue("background-color"),"rgba(109, 179, 63, 1)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[2]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[3]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[5]/a")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
		 
		 assertEquals(driver.findElement(By.xpath("//ul[@class ='nav navbar-nav navbar-right']/li[4]/a")).getText(),"VETERINARIANS");
	 }
	 /**
	  * Test to check menu back ground image
	  */
	 @Test 
	 public void menuBackGroundImageTest(){
		 driver.get("http://localhost:8080/vets.html");
		 assertTrue(driver.findElement(By.xpath("/html/body/nav/div/div[1]/a/span")).getCssValue("background").contains("spring-logo-dataflow.png"));
		 
		 }
	 /**
	  * Test to check number of links
	  */
	 @Test
	 public void menulinksOnPageTest(){
		 driver.get("http://localhost:8080/vets.html");
		  assertEquals(driver.findElements(By.tagName("li")).size(),5);
	 }
	/**
	 * Test to check the table header
	 */
	 @Test
	 public void tableHeaderTest() {
		 driver.get("http://localhost:8080/vets.html");
		 assertEquals(driver.findElement(By.xpath("//table[1]/thead/tr/th[1]")).getText(),"Name");
		 assertEquals(driver.findElement(By.xpath("//table[1]/thead/tr/th[2]")).getText(),"Specialties");
	  }
	 
	 /**
	  *Test to check the total number of vets 
	  */
	
	 @Test
	 public void noOfVetsTest() {
		 driver.get("http://localhost:8080/vets.html");
		 assertEquals(driver.findElements(By.xpath("//table[1]/tbody/tr")).size(),6);
		
	  }
	 /**
	  * Test to check the details of the vets.
	  */
	 @Test
	 public void detailsOfVetsTest() {
		 //1st row
		 List<Vet> avets = getAllVets();
		 Iterator<Vet> vetsIter = avets.iterator();
		
		
		
		 List<WebElement> rows = driver.findElements(By.xpath("//table[1]/tbody/tr"));
		 Iterator<WebElement> iter = rows.iterator();

		 while(iter.hasNext()) {
		     WebElement we = iter.next();
		     Vet vet = vetsIter.next();
		     String vetFullNme = vet.getFirstName() + ' ' + vet.getLastName();
		     assertEquals(we.findElement(By.xpath("td[1]")).getText().trim(),vetFullNme);
		     List<Specialty> specilaities = vet.getSpecialties();
	    	 Iterator<Specialty> specilaitiesIter = specilaities.iterator();
	    	 Specialty spl = new Specialty();
	    	 while(specilaitiesIter.hasNext()) {
	    		
	    		 List<WebElement> spans = we.findElements(By.xpath("td[2]/span"));
	    		 Iterator<WebElement> iterSpans = spans.iterator();
	    		 while (iterSpans.hasNext()){
	    			 WebElement span = iterSpans.next(); 
	    			 spl = specilaitiesIter.next();
	    			 assertEquals(span.getText().trim(),spl.getName());
	    		 }
	    	 }	 
		 }
	  }
	 
	 /**
	  * Test to check the view as XML link is working correctly
	  */
	 @Test
	 public void viewXMLTest(){
		 driver.get("http://localhost:8080/vets.html");
		 driver.findElement(By.linkText("View as XML")).click();
		 assertEquals(driver.getCurrentUrl(),"http://localhost:8080/vets.xml");
		 driver.navigate().back();
		 assertEquals(driver.getCurrentUrl(),"http://localhost:8080/vets.html");
	 }

	 /**
	  * Test to check the view as JSOn link is working correctly
	  */
	 @Test
	 public void viewJSONTest(){
		 driver.get("http://localhost:8080/vets.html");
		 driver.findElement(By.linkText("View as JSON")).click();
		 assertEquals(driver.getCurrentUrl(),"http://localhost:8080/vets.json");
		 driver.navigate().back();
		 assertEquals(driver.getCurrentUrl(),"http://localhost:8080/vets.html");
	 }
	
	 /**
	  * Test to check the footer message
	  */
	 @Test
	 public void footerImageTest(){
		 driver.get("http://localhost:8080/vets.html");
		 WebElement ImageFile = driver.findElement(By.xpath("//img[@alt='Sponsored by Pivotal']")); 
		 Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
		 assertTrue(ImagePresent);
		 }
	/**
	 * Test to check if the View as XML returns valid data data.
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	 public void xmlDataTest() throws ParserConfigurationException, SAXException, IOException{
			driver.get("http://localhost:8080/vets.html");
		 	driver.findElement(By.linkText("View as XML")).click();
		 	
		 	 String url = "http://localhost:8080/vets.xml";
		 	 InputStream is = new URL(url).openStream();
		 	
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(is);

			//optional, but recommended
			doc.getDocumentElement().normalize();

			assertEquals(doc.getDocumentElement().getNodeName(),"vets");
			NodeList nList = doc.getElementsByTagName("vetList");
			
			assertEquals(nList.getLength(),6);
			List<Vet> avets = getAllVets();
			Iterator<Vet> vetsIter = avets.iterator();
		    Vet vet = new Vet();
		    
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String idVet = eElement.getElementsByTagName("id").item(0).getTextContent();
					String firstNameVet = eElement.getElementsByTagName("firstName").item(0).getTextContent();
					String lastNameVet = eElement.getElementsByTagName("lastName").item(0).getTextContent();
					NodeList splList =  eElement.getElementsByTagName("specialties");
					if (vetsIter.hasNext())
				    	  vet = vetsIter.next();
			    	 assertEquals(idVet,vet.getId().toString());
			    	 assertEquals(firstNameVet,vet.getFirstName());
			    	 assertEquals(lastNameVet,vet.getLastName());	
			    	 
			    	 List<Specialty> specilaities = vet.getSpecialties();
			    	 Iterator<Specialty> specilaitiesIter = specilaities.iterator();
			    	 Specialty spl = new Specialty();
			    	 for (int tempSpl = 0; tempSpl < splList.getLength(); tempSpl++) {
							Node splNode = splList.item(tempSpl);
							Element eSplElement = (Element) splNode;
							String splId =  eSplElement.getElementsByTagName("id").item(0).getTextContent();
							String splName = eSplElement.getElementsByTagName("name").item(0).getTextContent();
							spl = specilaitiesIter.next();
				    		assertEquals(splName, spl.getName());
				    		assertEquals(splId, spl.getId().toString());
						}
					}
			}		
			
			driver.navigate().back();
	 }
	 
	
	 /**
	  * Test to check if View as JSON returns valid data
	  * 
	  * @throws ParserConfigurationException
	  * @throws SAXException
	  * @throws IOException
	  * @throws JSONException
	  */
	 @Test
	 public void jsonDataTest() throws ParserConfigurationException, SAXException, IOException, JSONException{
		 driver.get("http://localhost:8080/vets.html");
		 	driver.findElement(By.linkText("View as JSON")).click();
		 	 String url = "http://localhost:8080/vets.json";
		 	 InputStream is = new URL(url).openStream();
		 	 BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		 	 JSONArray json = null;
		 	 StringBuilder sb = new StringBuilder();
		        int cp;
		        while ((cp = rd.read()) != -1) {
		            sb.append((char) cp);
		        }
		     JSONObject obj = new JSONObject(sb.toString());
		     json = obj.getJSONArray("vetList");
		     
		     List<Vet> avets = getAllVets();
		     
		     assertEquals(json.length(),6);
		     assertEquals(avets.size(),6);

		     Iterator<Vet> vetsIter = avets.iterator();
		     Vet vet = new Vet();

		     for (int i =0 ; i <json.length(); i++ ){
		    	 JSONObject temp = json.getJSONObject(i);
		    	 String idVet = temp.getString("id");
		    	 String firstNameVet = temp.getString("firstName");
		    	 String lastNameVet = temp.getString("lastName");
		    	 if (vetsIter.hasNext())
			    	  vet = vetsIter.next();
		    	 assertEquals(idVet,vet.getId().toString());
		    	 assertEquals(firstNameVet,vet.getFirstName());
		    	 assertEquals(lastNameVet,vet.getLastName());
		    	 JSONArray splArray =  temp.getJSONArray("specialties");
		    	 assertEquals(splArray.length(),vet.getNrOfSpecialties());
		    	 List<Specialty> specilaities = vet.getSpecialties();
		    	 Iterator<Specialty> specilaitiesIter = specilaities.iterator();
		    	 Specialty spl = new Specialty();
			     
		    	  for (int j =0 ; j <splArray.length(); j++ ) {
		    		  JSONObject splObject = splArray.getJSONObject(j);
		    		  String splId = splObject.getString("id");
		    		  String splName = splObject.getString("name");
		    		  spl = specilaitiesIter.next();
		    		  assertEquals(splName, spl.getName());
		    		  assertEquals(splId, spl.getId().toString());
		    	  }
		     }
		     
			driver.navigate().back();
	 }
	 
	 private List<Vet> getAllVets(){
		 List<Vet> vets = new ArrayList<>();
		  Vet vet = new Vet();
		  Specialty spl = new Specialty();
		   
		  vet.setFirstName("James");
	      vet.setLastName("Carter");
	      vet.setId(1);
	      vets.add(vet);
	      
	      vet = new Vet();
	      vet.setFirstName("Helen");
	      vet.setLastName("Leary");
	      vet.setId(2);
	      spl.setName("radiology");
	      spl.setId(1);
	      vet.addSpecialty(spl);
	      vets.add(vet);
	      
	      vet = new Vet();
	      spl = new Specialty();
	      vet.setFirstName("Linda");
	      vet.setLastName("Douglas");
	      vet.setId(3);
	      spl.setName("dentistry");
	      spl.setId(3);
	      vet.addSpecialty(spl);
	      spl = new Specialty();
	      spl.setName("surgery");
	      spl.setId(2);
	      vet.addSpecialty(spl);
	      vets.add(vet);
	      
	      vet = new Vet();
	      spl = new Specialty();
	      vet.setFirstName("Rafael");
	      vet.setLastName("Ortega");
	      vet.setId(4);
	      spl.setName("surgery");
	      spl.setId(2);
	      vet.addSpecialty(spl);
	      vets.add(vet);
	      
	      vet = new Vet();
	      spl = new Specialty();
	      vet.setFirstName("Henry");
	      vet.setLastName("Stevens");
	      vet.setId(5);
	      spl.setName("radiology");
	      spl.setId(1);
	      vet.addSpecialty(spl);
	      vets.add(vet);
	      vet = new Vet();
	      
	      vet.setFirstName("Sharon");
	      vet.setLastName("Jenkins");
	      vet.setId(6);
	      vets.add(vet);
	      
	      return vets;
	 }
}
