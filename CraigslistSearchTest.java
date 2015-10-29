import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * As a user,
 * I would like a search box
 * So that I can filter through posts
 */

public class CraigslistSearchTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://pittsburgh.craigslist.org/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  //Given you're on the main page
  //When you type in gibberish that is
  //not in the english dictionary into the search box
  //and press enter
  //Then I should get a page that says no results

  @Test
  public void testNoResults() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("query")).clear();
    driver.findElement(By.id("query")).sendKeys("jdhsbsj");
    driver.findElement(By.id("query")).sendKeys(Keys.RETURN);
    assertTrue(isElementPresent(By.cssSelector("div.noresults")));
  }
  
  //Given you're on the main page and there are toys for sale
  //When you type in toys in the search box and press enter
  //Then I should get a page with results
  @Test
  public void testSpecificResult() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.id("query")).clear();
	    driver.findElement(By.id("query")).sendKeys("toys");
	    driver.findElement(By.id("query")).sendKeys(Keys.RETURN);
	    assertTrue(!isElementPresent(By.cssSelector("div.noresults")));
	  }
  
  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}