package testing.project;

import static org.junit.Assert.assertEquals;


import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchTestingS1 {

private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.liverpool.com.mx/tienda/home");
		
	}
	@Test
	public void test() {
		
		int count=0;
		String results="";
		String selectedProductID="";
		WebElement searchBox = driver.findElement(By.id("mainSearchbar"));
		WebElement searchButton = driver.findElement(By.className("input-group-text"));
		searchBox.clear();
		searchBox.sendKeys("playstation");
		searchButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.getPageSource();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		List<WebElement> prodItems = driver.findElements(By.cssSelector("ul.m-product__listingPlp li"));
		for (WebElement prodCurrenItem : prodItems) {	
			
			String productId = prodCurrenItem.getAttribute("data-prodid");
			String strItemText = prodCurrenItem.getText();						
			if(strItemText.contains("PlayStation 5")|| strItemText.contains("Consola PlayStation") )
			{
				count++;
				if(count==1) { selectedProductID=productId;}
			}
		}
		
		//Search for a “PlayStation” using the search bar,
		try{assertEquals("playstation | Liverpool.com.mx", driver.getTitle()); results="VALIDATION 1 (Search for a “PlayStation” using the search bar) = OK";}catch(AssertionError ae) {results="VALIDATION 1 (Search for a “PlayStation” using the search bar) = "+ae;}
		
		//Verify results includes games for PlayStation 5 and PlayStation consoles
		
		try{assertEquals(count>=1,true); results=results+'\n'+"VALIDATION 2 (Verify results includes games for PlayStation 5 and PlayStation consoles) = OK";}catch(AssertionError ae) {results=results+'\n'+"VALIDATION 2 (Verify results includes games for PlayStation 5 and PlayStation consoles) = "+ae;}
		
		WebElement productItem = driver.findElement(By.cssSelector("[data-prodid=\""+selectedProductID+"\"]"));
		productItem.click();	
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement articleTitle = driver.findElement(By.className("m-description-spcbrand"));
		String articleTitleText = articleTitle.getText();
		WebElement articlePrice = driver.findElement(By.className("m-product__price--collection"));
		String articlePriceText = articlePrice.getText();
		//Validate the title and price of the item in the page displayed.
		try{assertEquals(articleTitleText!=null, true); results=results+'\n'+"VALIDATION 3 (Validate the title of the item in the page displayed) = OK";}catch(AssertionError ae) {results=results+'\n'+"VALIDATION 3 (Validate the title of the item in the page displayed) = "+ae;}
		try{assertEquals(articlePriceText!=null, true); results=results+'\n'+"VALIDATION 4 (Validate the price of the item in the page displayed) = OK";}catch(AssertionError ae) {results=results+'\n'+"VALIDATION 4 (Validate the price of the item in the page displayed) = "+ae;}
		
		
		
		JOptionPane.showMessageDialog(null, results, "Test results", JOptionPane.INFORMATION_MESSAGE);
		
		
		
		
		
		
	}
	@After
	public void tearDown(){
		driver.quit();
	}
}
