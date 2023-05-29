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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;


public class SearchTestingS3 {
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
		
		WebElement meinMenu = driver.findElement(By.className("m-navDesktop__menuList"));
		meinMenu.click();
		
		WebElement bellezaElement = driver.findElement(By.xpath(".//*[@data-submenu-id='CAT5020010']/div[1]/a/div/div/span"));
		
		Actions action = new Actions(driver);
		action.moveToElement(bellezaElement).perform();
		
		WebElement perfumeElement = driver.findElement(By.xpath(".//a[contains(text(), 'Perfumes Hombre')]"));
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", perfumeElement);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.getPageSource();
		
		driver.get(driver.getCurrentUrl());

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		
	
		 
	}
	@After
	public void testEnd(){
		driver.quit();
	}
	
}
