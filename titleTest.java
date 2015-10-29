import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * As a user,
 * I would like a main Pittsburgh Page
 * So that I can see the different links available to the
 * Pittsburgh craigslist.  
 * @author 
 *
 */

public class titleTest {

	static WebDriver driver = new HtmlUnitDriver();
	
	// Start at the home page for craigslist for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://pittsburgh.craigslist.org");
	}
	
	// Given that I am on the main Pittsburgh page
	// When I view the title
	// Then I see that it contains the word "craigslist"
	@Test
	public void testShowsCorrectTitle() {
		
		// Simply check that the title contains the word "craigslist"
		
		String title = driver.getTitle();
		assertTrue(title.contains("craigslist"));
	}
	
	//Given that I am on the pittsburgh page
	//When I click on the "craigslist" title 
	//I return on the main craigslist page
	@Test
	public void testTitleLinks() {
		
		// find the "craigslist" Title and click on it
		// The page you are redirected should have "Gawker"
		// in the title
		
		driver.findElement(By.linkText("craigslist")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("craigslist"));
	}
	
	
	// Given that I am on the Pittsburgh Page
	// When I view the title
	// Then I see that it contains the word "Pittsburgh"
	@Test
	public void testShowsCorrectCity() {
		
		// Simply check that the title contains the word "Pittsburgh"
		
		String title = driver.getTitle();
		assertTrue(title.contains("pittsburgh"));
	}
	
	// Given that I am on the main page
	// When I view the header for each categories
	// Then I see that it contains "community", "housing", "jobs", "for sale"
	// "discussion forums", "services", "gigs", and "resume" links
	@Test
	public void testHasCorrectHeaderLinks() {
		
		// Check for "community", "housing", "jobs", "for sale"
		// "discussion forums", "services", "gigs", and "resume" - if any of
		// these is not found, fail the test
		
		try {
			driver.findElement(By.linkText("community"));
			driver.findElement(By.linkText("housing"));
			driver.findElement(By.linkText("jobs"));
			driver.findElement(By.linkText("for sale"));
			driver.findElement(By.linkText("discussion forums"));
			driver.findElement(By.linkText("services"));
			driver.findElement(By.linkText("gigs"));
			driver.findElement(By.linkText("resumes"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Given that I am on the main page
	// When I click on the "community" link
	// Then I should be redirected to the "community" page
	@Test
	public void testSeeCommunityLinks() {
		
		// find the "community" link and click on it
		// The page you go to should include "community"
		// in the title
			
			driver.findElement(By.linkText("community")).click();
			String newPageTitle = driver.getTitle();
			assertTrue(newPageTitle.contains("community"));
		}
	
	// Given that I am on the main page
	// When I click on the "new" link
	// Then I should be redirected to the "housing" page
	@Test
	public void testSeeHousingLinks() {
			
		// find the "Housing" link and click on it
		// The page you go to should include "Housing"
		// in the title
				
			driver.findElement(By.linkText("housing")).click();
			String newPageTitle = driver.getTitle();
			assertTrue(newPageTitle.contains("housing"));
		}
	
	// Given that I am on the main Pittsburgh page
	// When I view the Sidebar
	// Then I should see that it contains the phrase "event calendar"
	@Test
	public void testHasCalendar() {
		
		// Check that there is a remember-me element
		// that contains the text "remember me"
		// If it does not exist, or text is incorrect, fail test
		
		try {
			WebElement e = driver.findElement(By.id("leftbar"));
			String elementText = e.getText();
			assertTrue(elementText.contains("event calendar"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	
}
