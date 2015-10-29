import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * As a craigslist user
 * I would like to browse sellers' postings
 * So that I can see what is available to buy
 */
 
public class CraigslistPostTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://pittsburgh.craigslist.org/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  // Given a seller's posting
  // When I click the email to a friend button
  // Then I should be able to enter my friend's email
  
  @Test
  public void testEmailFriend() throws Exception {
    driver.get(baseUrl + "/atq/5277608780.html");
    driver.findElement(By.linkText("email to friend")).click();
    assertTrue(isElementPresent(By.xpath("//div[@id='postingbody']/blockquote/form/p[2]/label")));
  }
  
  // Given a seller's posting
  // Then I should see when it was last updated
  
  @Test
  public void testLastUpdated() throws Exception {
    driver.get(baseUrl + "/atq/5277608780.html");
    assertTrue(isElementPresent(By.xpath("//section[@id='pagecontainer']/section/section/div[2]/p[3]")));
  }
  
  // Given a seller's posting
  // When I click the next button
  // Then I should be moved to another posting
  
  @Test
  public void testNext() throws Exception {
    driver.get(baseUrl + "/atq/5277608780.html");
	String prevURL = driver.getCurrentUrl();
    driver.findElement(By.linkText("next ▶")).click();
	String currentURL = driver.getCurrentUrl();
	Assert.assertEquals(currentURL, prevURL);
  }
 
  // Given a seller's posting
  // Then I should see the post id
  
  @Test
  public void testPostID() throws Exception {
    driver.get(baseUrl + "/atq/5277608780.html");
    assertTrue(isElementPresent(By.cssSelector("div.postinginfos > p.postinginfo")));
  }
  
  // Given a seller's posting
  // Then I should see when it was posted
  
  @Test
  public void testPosted() throws Exception {
    driver.get(baseUrl + "/atq/5277608780.html");
    assertTrue(isElementPresent(By.xpath("//section[@id='pagecontainer']/section/section/div[2]/p[2]")));
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