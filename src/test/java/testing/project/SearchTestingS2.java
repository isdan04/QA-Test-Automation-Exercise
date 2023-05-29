package testing.project;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

public class SearchTestingS2 {

	private WebDriver driver;
	
	@Before
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.liverpool.com.mx/tienda/home");
		
	}
	@Test
	public void test2() {
		
		int count=0;
		String validations="";
		WebElement searchBox = driver.findElement(By.id("mainSearchbar"));
		WebElement searchButton = driver.findElement(By.className("input-group-text"));
		searchBox.clear();
		searchBox.sendKeys("smart tv");
		searchButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.getPageSource();
		try{assertEquals("smart tv | Liverpool.com.mx", driver.getTitle()); validations="VALIDATION 1 (Search for “smart tv” and navigate to the page.) = OK";}catch(AssertionError ae) {validations="VALIDATION 1 (Search for “smart tv” and navigate to the page.) = "+ae;}
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> filterItems = driver.findElements(By.className("a-title__filter"));
	for (WebElement filterCurrenItem : filterItems) {
		
			String strItemText = filterCurrenItem.getText();						
			if(strItemText.contains("Tamaño")|| strItemText.contains("Precios") )
			{
				count++;
			}
			
			
		}
		try{assertEquals(count>=2, true); validations=validations+'\n'+"VALIDATION 2 (Validate that the Size and Price filters are displayed) = OK";}catch(AssertionError ae) {validations=validations+'\n'+"VALIDATION 2 (Validate that the Size and Price filters are displayed) = "+ae;}
		WebElement sizeFilter = driver.findElement(By.id("variants.normalizedSize-55 pulgadas"));;
		sizeFilter.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.getPageSource();
		WebElement priceFilter = driver.findElement(By.id("variants.prices.sortPrice-10000-700000"));;
		priceFilter.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.getPageSource();
		WebElement brandFilter = driver.findElement(By.id("brand-SONY"));;
		brandFilter.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.getPageSource();
		
		int filterCount=0;
		List<WebElement> selectedFilters = driver.findElements(By.cssSelector("div.mdc-chip-set"));
		for (WebElement selectedCurrentItem : selectedFilters) {
			
			String text=selectedCurrentItem.getText();
			if(text.contains("55 Pulgadas") || text.contains("SONY") || text.contains("Mas De $10000.0"))
			{
				filterCount++;
			}
		
		}
		
		try{assertEquals(filterCount>=3, true); validations=validations+'\n'+"VALIDATION 3 (Filter the results by size: 55 inches, price: > 10,000, brand: sony.) = OK";}catch(AssertionError ae) {validations=validations+'\n'+"VALIDATION 3 (Filter the results by size: 55 inches, price: > 10,000, brand: sony.) = "+ae;}

	
	
		int resultCount=0;
		List<WebElement> prodItems = driver.findElements(By.cssSelector("div.o-listing__products ul.m-product__listingPlp li"));
		for (WebElement prodCurrentItem : prodItems) {
			
			String productId = prodCurrentItem.getAttribute("data-prodid");
			if(productId!=null) {
				resultCount++;
				
			}
		}
		validations=validations+'\n'+"Validate the result count: "+resultCount+" Articles";
		//List<WebElement> prodItems = driver.findElements(By.cssSelector("li.m-product__card card-masonry a"));
		JOptionPane.showMessageDialog(null, validations, "Test results", JOptionPane.INFORMATION_MESSAGE);
	}
	@After
	public void tearDown(){
		driver.quit();
	}
}
