import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * As a user,
 * I would like a search box
 * So that I can filter through posts  
 * @author 
 *
 */

public class searchTest {

	static WebDriver driver = new HtmlUnitDriver();
	
	// Start at the home page for craigslist for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://pittsburgh.craigslist.org");
	}
	
	//Given I'm on the homepage
	//When I view the left sidebar
	//Then I should see a search box
	@Test
	public void testHasSearchBar() {
		
		try {
			WebElement e = driver.findElement(By.id("leftbar"));
			String elementText = e.getText();
			assertTrue(elementText.contains("search craigslist"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I’m on the home page
	//When I click on the empty search bar and press enter
	//Then I should go to the for sale page
 
	@Test
	public void testEmptySearch() throws Exception {
	    driver.findElement(By.id("go")).click();
	    String title = driver.getTitle();
		assertTrue(title.contains("for sale"));
	}
}
